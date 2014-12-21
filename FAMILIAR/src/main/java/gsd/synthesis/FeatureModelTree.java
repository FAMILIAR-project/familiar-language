package gsd.synthesis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FeatureModelTree<T> {

	private FeatureGraph<T> graph;
	
	private Node<T> root;
	
	private HashMap<T, Node<T>> nodes;
	
	public FeatureModelTree()
	{
		this.nodes = new HashMap<T, Node<T>>();
	}
	
	public FeatureModelTree(FeatureGraph<T> graph)
	{
		this.nodes = new HashMap<T, Node<T>>();
		this.graph = graph;
		this.buildTreeFromGraph(graph);
	}
	
	public void buildTreeFromGraph(FeatureGraph<T> graph)
	{
		//Set the graph
		this.graph = graph;
		
		//Set the root
		this.root = new Node(this.graph.getTopVertex());
		
		//Save the root in the node list
		this.nodes.put(graph.getTopVertex().getFeature(), this.root);
		
		//Get the nodes
		Set<FeatureNode<T>> verticesList = this.graph.vertices();
		
		//Iterator on each featureNode
		Iterator<FeatureNode<T>> it = verticesList.iterator();
		
		while(it.hasNext())
		{
			//Saves the feature node
			FeatureNode<T> fn = it.next();
			
			//Make it a node in the tree
			Node<T> node = new Node(fn.getFeature());
			
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
			
			//Get the target for this edge
			FeatureNode<T> target = this.graph.getTarget(edge);
			
			//Get the corresponding node we already created in our nodes array
			Node<T> nodeTarget = this.nodes.get(target.getFeature());
			
			//If it's binary (i.e MANDATORY or HIERARCHY)
			if(edge.isBinary())
			{
				//Get the source for this edge
				FeatureNode<T> source = this.graph.getSource(edge);
				
				
				//Get the corresponding node we already created in our nodes array
				Node<T> nodeSource = this.nodes.get(source.getFeature());

				
				//Add the relation logic between these nodes
				nodeSource.setParent(nodeTarget);
				nodeSource.setOptionnal(edge.getType() != FeatureEdge.MANDATORY);
				
				//Add the child to the parent
				nodeTarget.addChildren(nodeSource);
				
			}else{
				//If it's a logical operation between several nodes
				
				//Then we have several sources
				Set<FeatureNode<T>> sources = this.graph.getSources(edge);
				Iterator<FeatureNode<T>> it3 = sources.iterator();
				
				//Save all the features in a local array
				ArrayList<T> sourcesFeature = new ArrayList<T>();
				while(it3.hasNext())
					sourcesFeature.add(it3.next().getFeature());
				
				//Reset the iterator
				it3 = sources.iterator();
				
				//For each source
				while(it3.hasNext())
				{
					//Set the source and get the corresponding source node
					FeatureNode<T> source = it3.next();
					Node<T> nodeSource = this.nodes.get(source.getFeature());
					
					//Set his relation
					nodeSource.setRelationType(edge.getType());
					nodeSource.setRelationTargets(sourcesFeature);
					
					//Set his parent
					nodeSource.setParent(nodeTarget);
					
					//Add the child to the parent
					nodeTarget.addChildren(nodeSource);
					
				}
			}
		}
	}
	
	
	public String toJson()
	{
		StringBuilder sb = new StringBuilder();
		
		this.nodeToString(root, sb);
		
		return sb.toString();
	}
	
	public void nodeToString(Node<T> node, StringBuilder sb)
	{
		//Set global informations
		sb.append("{");
		sb.append("\"id\": \""+node.getValue()+"\", ");
		sb.append("\"optionnal\": "+node.isOptionnal()+", ");
		sb.append("\"relation\": "+node.getRelationType()+", ");
		
		
		
		//---------------TARGET ID----------------//
		//Set the target ids for the relation
		sb.append("\"relationTargetIds\": [");
			
		Iterator<T> it = node.getRelationTargets().iterator();
		while(it.hasNext())
			sb.append("\""+ it.next()+"\", ");
		
		//Remove the last comma
		sb.deleteCharAt(sb.length() -1);
		sb.deleteCharAt(sb.length() -1);
		
		//Close this array
		sb.append("], ");
		
		
		
		//--------------CHILDREN----------------//
		//SET THE CHILDREN ARRAY
		sb.append("\"children\": [");
		
		//Iterate on the children
		Iterator<Node<T>> it2 = node.getChildren().iterator();
		
		//Call recursive for each child
		while(it2.hasNext()){
			this.nodeToString(it2.next(), sb);
			sb.append(", ");
		}
		
		//Remove last comma (cause end of array)
		sb.deleteCharAt(sb.length() -1);
		sb.deleteCharAt(sb.length() -1);
		
		//Close the children array
		sb.append("] ");
		
		
		
		//Close the whole object array
		sb.append("}");
	}
	
	
	public class Node<T>
	{
		private T val;
		
		private boolean optionnal = false;
		
		private int relationType = -1;
		
		private ArrayList<T> relationTargets;
		
		private List<Node<T>> children;
	
		private Node<T> parent = null;
		
		public Node(T value)
		{
			this.val = value;
			this.relationTargets = new ArrayList<T>();
			this.children = new ArrayList<Node<T>>();
		}
		
		public void addChildren(Node<T> child)
		{
			this.children.add(child);
		}
		
		public void setParent(Node<T> parent)
		{
			this.parent = parent;
		}
		
		public Node<T> getParent()
		{
			return this.parent;
		}
		
		public boolean isOptionnal()
		{
			return this.optionnal;
		}
		
		public void setOptionnal(boolean o)
		{
			this.optionnal = o;
		}
		
		public T getValue()
		{
			return this.val;
		}

		public int getRelationType() {
			return relationType;
		}

		public void setRelationType(int relationType) {
			this.relationType = relationType;
		}
		
		public void setRelationTargets(ArrayList<T> relationTargets){
			this.relationTargets = relationTargets;
		}
		
		public ArrayList<T> getRelationTargets()
		{
			return this.relationTargets;
		}
		
		public List<Node<T>> getChildren()
		{
			return this.children;
		}
	}
	
}
