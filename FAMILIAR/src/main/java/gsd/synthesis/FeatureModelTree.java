package gsd.synthesis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import fr.familiar.variable.FeatureModelVariable;

public class FeatureModelTree {

	private FeatureGraph<String> graph;
	
	private Node root;
	
	private HashMap<String, Node> nodes;
	
	private Set<Expression<String>> constraints;
	
	public FeatureModelTree()
	{
		this.nodes = new HashMap<String, Node>();
	}
	
	/*
	public FeatureModelTree(FeatureGraph<String> graph)
	{
		this.nodes = new HashMap<String, Node<String>>();
		this.graph = graph;
		this.buildTreeFromGraph(graph);
	}*/
	
	public void buildTreeFromFMV(FeatureModelVariable fm)
	{
		//Set the graph
		this.graph =fm.getFm().getDiagram();
		
		//Set the contraints
		this.constraints = fm.getFm().getConstraints();
		
		//Set the root
		this.root = new Node(this.graph.getTopVertex().getFeature());
		
		//Save the root in the node list
		this.nodes.put(graph.getTopVertex().getFeature(), this.root);
		
		//Get the nodes
		Set<FeatureNode<String>> verticesList = this.graph.vertices();
		
		//Iterator on each featureNode
		Iterator<FeatureNode<String>> it = verticesList.iterator();
		
		while(it.hasNext())
		{
			//Saves the feature node
			FeatureNode<String> fn = it.next();
			
			//Make it a node in the tree
			Node node = new Node(fn.getFeature());
			
			//System.out.println("Discovered node : "+fn.getFeature());
			
			//Put it in the nodeList
			this.nodes.put(fn.getFeature(), node);
		}
		
		//Get the edges
		Set<FeatureEdge> edgeList = this.graph.edges();
		
		//Iterator on each edge
		Iterator<FeatureEdge> it2 = edgeList.iterator();
		
		//For each edge, add the relation between the nodes
		while(it2.hasNext())
		{
			
			//Next edge
			FeatureEdge edge = it2.next();
			
			//System.out.println("Discover new edge "+edge.toString());
			
			//Get the target for this edge
			FeatureNode<String> target = this.graph.getTarget(edge);
			
			//System.out.println("Target : "+target.getFeature());
			//System.out.println("Type :"+edge.getType());
			
			Node nodeTarget = null;
			
			//Get the corresponding node we already created in our nodes array
			if(target.isTop())
				nodeTarget = this.root;
			else
				nodeTarget = this.nodes.get(target.getFeature());
				
			//If it's binary (i.e MANDATORY or HIERARCHY)
			if(edge.isBinary())
			{
				//Get the source for this edge
				FeatureNode<String> source = this.graph.getSource(edge);
				
				//System.out.println("Source : "+source.getFeature());
				
				//Get the corresponding node we already created in our nodes array
				Node nodeSource = this.nodes.get(source.getFeature());
				
				//If this edge precise that this is not optionnal, set it
				if(edge.getType() == FeatureEdge.MANDATORY)
					nodeSource.setNotOptionnal();

				//Add the logic children - parent only if it's not already done
				if(!nodeTarget.getChildren().contains(nodeSource))
				{
					//Add the parent to the node source
					nodeSource.setParent(nodeTarget);
					
					//Add the child to the parent
					nodeTarget.addChildren(nodeSource);
				}
				
			}else{
				//If it's a logical operation between several nodes
				
				//Then we have several sources
				Set<FeatureNode<String>> sources = this.graph.getSources(edge);
				Iterator<FeatureNode<String>> it3 = sources.iterator();
				
				//Save all the features in a local array
				ArrayList<String> sourcesFeature = new ArrayList<String>();
				while(it3.hasNext())
					sourcesFeature.add(it3.next().getFeature());
				
				//Reset the iterator
				it3 = sources.iterator();
				
				//For each source
				while(it3.hasNext())
				{
					//Set the source and get the corresponding source node
					FeatureNode<String> source = it3.next();
					Node nodeSource = this.nodes.get(source.getFeature());
					
					//Set his relation
					nodeSource.setRelationType(edge.getType());
					nodeSource.setRelationTargets(sourcesFeature);
					
					if(!nodeTarget.getChildren().contains(nodeSource))
					{
						//Set his parent
						nodeSource.setParent(nodeTarget);
						
						//Add the child to the parent
						nodeTarget.addChildren(nodeSource);
					}
				}
			}
		}
	}
	
	
	public String toJson()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("{\"tree\": ");
		this.nodeToString(root, sb);
		
		if(this.constraints.size() > 0){
			sb.append(", ");
			this.constraintsToString(this.constraints, sb);
		}
		
		sb.append("}");
		
		return sb.toString();
	}
	
	private void constraintsToString(Set<Expression<String>> constraints,  StringBuilder sb)
	{
		sb.append("\"constraints\": [");
		Iterator<Expression<String>> it = constraints.iterator();
		while(it.hasNext())
		{
			Expression<String> e = it.next();
			sb.append("{\"left\": \""+e.getLeft().toString()+"\", ");
			sb.append("\"right\": \""+e.getRight().toString()+"\", ");
			sb.append("\"type\": \""+e.getType()+"\"}, ");
			
		}
		
		sb.deleteCharAt(sb.length() -1);
		sb.deleteCharAt(sb.length() -1);
		
		sb.append("]");
	}
	
	private void nodeToString(Node node, StringBuilder sb)
	{
		//Set global informations
		sb.append("{");
		sb.append("\"id\": \""+node.getValue()+"\", ");
		sb.append("\"optionnal\": "+node.isOptionnal()+", ");
		sb.append("\"relation\": "+node.getRelationType()+", ");
		
		
		
		//---------------TARGET ID----------------//
		//Set the target ids for the relation
		sb.append("\"relationTargetIds\": [");
			
		if(node.getRelationTargets().size()>0)
		{
			Iterator<String> it = node.getRelationTargets().iterator();
			while(it.hasNext())
				sb.append("\""+ it.next()+"\", ");
			
			
			//Remove the last comma
			sb.deleteCharAt(sb.length() -1);
			sb.deleteCharAt(sb.length() -1);
		}	
		
		//Close this array
		sb.append("], ");
		
		
		
		//--------------CHILDREN----------------//
		//SET THE CHILDREN ARRAY
		sb.append("\"children\": [");
		
		if(node.getChildren().size() > 0){
		
			//Iterate on the children
			Iterator<Node> it2 = node.getChildren().iterator();
			
			//Call recursive for each child
			while(it2.hasNext()){
				this.nodeToString(it2.next(), sb);
				sb.append(", ");
			}
			
			//Remove last comma (cause end of array)
			sb.deleteCharAt(sb.length() -1);
			sb.deleteCharAt(sb.length() -1);
		}
		
		//Close the children array
		sb.append("]");
		
		
		
		//Close the whole object array
		sb.append("}");
	}
	
	
	public class Node
	{
		private String val;
		
		private boolean optionnal = true;
		
		private int relationType = -1;
		
		private ArrayList<String> relationTargets;
		
		private List<Node> children;
	
		private Node parent = null;
		
		public Node(String value)
		{
			this.val = value;
			this.relationTargets = new ArrayList<String>();
			this.children = new ArrayList<Node>();
		}
		
		public void addChildren(Node child)
		{
			this.children.add(child);
		}
		
		public void setParent(Node parent)
		{
			this.parent = parent;
		}
		
		public Node getParent()
		{
			return this.parent;
		}
		
		public boolean isOptionnal()
		{
			return this.optionnal;
		}
		
		public void setNotOptionnal()
		{
			this.optionnal = false;
		}
		
		public String getValue()
		{
			return this.val;
		}

		public int getRelationType() {
			return relationType;
		}

		public void setRelationType(int relationType) {
			this.relationType = relationType;
		}
		
		public void setRelationTargets(ArrayList<String> relationTargets){
			this.relationTargets = relationTargets;
		}
		
		public ArrayList<String> getRelationTargets()
		{
			return this.relationTargets;
		}
		
		public List<Node> getChildren()
		{
			return this.children;
		}
	}
	
}
