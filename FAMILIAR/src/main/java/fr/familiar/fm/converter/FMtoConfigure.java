/*
 * TODO à améliorer / traduire
 */

/**
 * @author OLIVIER Julien
 */

package fr.familiar.fm.converter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.collections15.Closure;
import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.map.LazyMap;

import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureName;
import fr.familiar.variable.Variable;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;
import gsd.synthesis.FeatureType;

public class FMtoConfigure {
	private FeatureModelVariable _FM;
	Map<FeatureNode<String>, String> groupToId = LazyMap.decorate(
			new HashMap<FeatureNode<String>, String>(), new Factory<String>() {

				private int groupId = 1;

				public String create() {
					return "G" + groupId++;
				}

			});
	private final FeatureGraphFactory<String> _fgf = new FeatureGraphFactory<String>(null, null);
	
	public FMtoConfigure(){
		this._FM = new FeatureModelVariable("",null);
	}
	
	public String getConfigureFromFML(FeatureModelVariable FM){
		/*
		 * Initialisation des variables utilisées
		 */
		_FM = FM;
		return "{\"tree\":[" + this.treatDiagram(_FM.getFm()) +"],\"constraint\":[" + this.treatConstraint(_FM.getAllConstraints()) + "]}";
	}
	
	private String treatDiagram(FeatureModel<String> featureModel) {
		final StringBuilder sb = new StringBuilder();

		final FeatureGraph<String> g = featureModel.getDiagram();
		if (g.isTop())
			return FeatureGraphFactory.DEFAULT_TOP_STRING;
		else if (g.isBottom())
			return FeatureGraphFactory.DEFAULT_BOTTOM_STRING;

		final Queue<FeatureNode<String>> rest = new LinkedList<FeatureNode<String>>();

		Closure<FeatureNode<String>> processNode = new Closure<FeatureNode<String>>() {
			private String spec = "";
			private final String NAME = "\"name\":";
			private final String SPECIF = "\"specif\":";
			private final String CHILDREN = "\"children\":";
			private final String OPTIONAL = "!";
			private final String PLUS = "+";
			private final String OR = "o";
			private final String EMPTY = "null";
			private boolean ok=false;
			
			
			public void execute(FeatureNode<String> v) {
				execChildren(v,"");
			}
			public void execChildren(FeatureNode<String> v, String str){
				Collection<FeatureNode<String>> children = g.children(v);
				//verif();
				for (FeatureEdge e : g.incomingEdges(v)) {
					Set<FeatureNode<String>> sources = g.getSources(e);
					if (sources.size() == 1){
						ok=false;
						continue;
					}
					
					switch (e.getType()) {
						case FeatureEdge.MUTEX:
							spec = "?" + OR;
							break;
						case FeatureEdge.OR:
							spec = PLUS + OR;
							break;
						default:
							spec = OR;
							break;
					}
					ok =true;
				}
				String resulSpecif = "\""+spec+str+"\"";
				if(resulSpecif.equals("\"\""))
					resulSpecif="\""+EMPTY+"\"";
				if (!v.equals(g.getTopVertex()))
					sb.append("{"
							+NAME
							+"\""
							+(v.getType() == FeatureType.AND_GROUP ? "TOTOTOTOT"/*groupToId.get(v)*/ : FeatureName.unquote(v.getFeature().toString()))
							+"\""
							+","
							+SPECIF
							+resulSpecif);
				spec = "";
				
				remainingChildren(children,v);
			}
			public void andGroup(Collection<FeatureNode<String>> children){
				// First, process AND-Groups
				Iterator<FeatureNode<String>> iter = children.iterator();
				while (iter.hasNext()) {
					FeatureNode<String> child = iter.next();
					if (child.getType() == FeatureType.AND_GROUP) {
						String groupId = groupToId.get(child);
						groupToId.put(child, groupId);

						sb.append(groupId).append("=(");
						for (String f : child.features()) {
							sb.append(f.toString()).append("&");
						}
						sb.deleteCharAt(sb.length() - 1);
						sb.append(")");

						iter.remove();
					}
				}
			}
			public void processGroup(Collection<FeatureNode<String>> children, FeatureNode<String> v){
				// Process Groups
				for (FeatureEdge e : g.incomingEdges(v)) {
					Set<FeatureNode<String>> sources = g.getSources(e);

					if (sources.size() == 1)
						continue;
					remainingChildren(children,g.getSources(e).iterator().next());
					
					children.removeAll(sources);
				}
			}
			public void remainingChildren(Collection<FeatureNode<String>> children, FeatureNode<String> v){
				// Process remaining children
				andGroup(children);
				processGroup(children,v);
				if(v.equals(g.getTopVertex())){
					for (FeatureNode<String> child : children) {
						if (g.findEdge(child, v, FeatureEdge.MANDATORY) == null && !ok) {
							execChildren(child,OPTIONAL);
						} else{
							execChildren(child,"");
						}
						sb.append("}");
					}
				}
				else if(!children.isEmpty()){
					sb.append(","+CHILDREN+"[");
					String copyOfSpec=spec;
					Iterator<FeatureNode<String>> it = children.iterator();
					FeatureNode<String> child;
					while (it.hasNext()) {
						child = it.next();
						if (g.findEdge(child, v, FeatureEdge.MANDATORY) == null && !ok) {
							execChildren(child,OPTIONAL);
						} else{
							execChildren(child,"");
						}
						spec = copyOfSpec;
						if(!it.hasNext())
							sb.append("}");
						else
							sb.append("},");
					}
					spec = "";
					sb.append("]");
					return;
				}
				if(g.children(v).isEmpty()){
					sb.append(","+CHILDREN+"[]");
				}
			}
		};

		rest.add(g.getTopVertex());
		while (!rest.isEmpty())
			processNode.execute(rest.poll());
		
		return sb.toString();
	}

	private String treatConstraint(Set<Expression<String>> cons){
		Iterator<Expression<String>> it = cons.iterator();
		Expression<String> exp;
		String resul="";
		while(it.hasNext()){
			exp = it.next();
			resul += "{\"cons\":\""+exp.getType().toString() +"\",\"param1\":["+ goLeft(exp) + "],\"param2\":[" + goRight(exp) + "]}";
			if(it.hasNext())
				resul+=",";
		}
		return resul;
		
	}
	private String goLeft(Expression<String> exp){
		exp = exp.getLeft();
		if(exp.getRight() == null){
			//LEAF
			if(exp.getType() == ExpressionType.FEATURE)
				return "{\"name\":\""+exp.getFeature()+"\",\"specif\":\""+ exp.getType() +"\"}";
			else
				return "{\"name\":\""+exp.getLeft().getFeature()+"\",\"specif\":\""+ exp.getType() +"\"}";
		}
		else{
			return "{\"cons\":\""+exp.getType() + "\",\"param1\":[" + goLeft(exp) + "],\"param2\":[" + goRight(exp) + "]}";
		}
	}
	private String goRight(Expression<String> exp){
		exp = exp.getRight();
		if(exp.getRight() == null){
			//LEAF
			if(exp.getType() == ExpressionType.FEATURE)
				return "{\"name\":\""+exp.getFeature()+"\",\"specif\":\""+ exp.getType() +"\"}";
			else
				return "{\"name\":\""+exp.getLeft().getFeature()+"\",\"specif\":\""+ exp.getType() +"\"}";
		}
		else{
			return "{\"cons\":"+exp.getType() + "\",\"param1\":[" + goLeft(exp) + "],\"param2\":[" + goRight(exp) + "]}";
		}
	}
}
