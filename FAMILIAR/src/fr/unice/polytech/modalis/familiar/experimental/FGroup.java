package fr.unice.polytech.modalis.familiar.experimental;

import java.util.HashSet;
import java.util.Set;

import fr.unice.polytech.modalis.familiar.fm.FGroupBuilder;
import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.variable.RType;
import fr.unice.polytech.modalis.familiar.variable.Variable;
import fr.unice.polytech.modalis.familiar.variable.VariableImpl;
import gsd.synthesis.Expression;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureNode;

public abstract class FGroup extends VariableImpl {

	protected Set<FeatureNode<String>> _sources;

	protected FeatureNode<String> _target;

	protected int _edgeType;

	public boolean isXor() {
		return _edgeType == FeatureEdge.XOR;
	}

	public boolean isOr() {
		return _edgeType == FeatureEdge.OR;
	}

	public boolean isMutex() {
		return _edgeType == FeatureEdge.MUTEX;
	}

	@Override
	public String toString() {
		return _sources + " -> " + _target + " (" + edgeTypeToString(_edgeType)
				+ ")";
	}
	
	public abstract Expression<String> toExpression() ;
			
	
	private String edgeTypeToString(int edgeType) {
		switch (edgeType) {
		case FeatureEdge.XOR:
			return "XOR";
		case FeatureEdge.OR:
			return "OR";
		case FeatureEdge.MUTEX:
			return "MUTEX";
		default:
			break;
		}
		return "UNKNOWN";
	}

	public boolean sameSources(FGroup group) {
		Set<FeatureNode<String>> oSources = group.getSources();
		return _sources.equals(oSources);
	}

	public boolean sameTarget(FGroup group) {
		FeatureNode<String> oTarget = group.getTarget();
		return _target.equals(oTarget);
	}

	public Set<FeatureNode<String>> getSources() {
		return _sources;
	}

	public FeatureNode<String> getTarget() {
		return _target;
	}

	public Set<FeatureNode<String>> nodes() {
		Set<FeatureNode<String>> nodes = new HashSet<FeatureNode<String>>();
		nodes.addAll(_sources);
		nodes.add(_target);
		return nodes;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof FGroup))
			return false; 
		FGroup oGroup = (FGroup) o ;	
		return getSources().equals(oGroup.getSources()) 
		&& getTarget().equals(oGroup.getTarget())
		&& getEdgeType() == oGroup.getEdgeType() ; 
		
	}

	@Override
	public RType getRType() {
		return RType.FEATURE_GROUP ; 
	}

	@Override
	public Variable copy() {
		return FGroupBuilder.make(_sources, _target, _edgeType);
	}

	@Override
	public void setValue(Variable vari) {
		if (vari instanceof FGroup) {
			FGroup fg = (FGroup) vari;
			setSources(fg.getSources());
			setTarget(fg.getTarget());
			setEdgeType(fg.getEdgeType());
			return;
		}
		FMLShell.getInstance().setError("Setting value type is not correct");
		
	}

	private int getEdgeType() {
		return _edgeType ; 
	}

	private void setEdgeType(int edgeType) {
		_edgeType = edgeType ; 
		
	}

	private void setTarget(FeatureNode<String> target) {
		_target = target ; 
		
	}

	private void setSources(Set<FeatureNode<String>> sources) {
		_sources = sources ;		
	}

	@Override
	public String getSpecificValue() {
		return toString() ; 
	}
	
	
	
	
}
