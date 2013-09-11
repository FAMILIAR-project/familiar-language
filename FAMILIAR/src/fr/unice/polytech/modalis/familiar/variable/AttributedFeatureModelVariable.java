package fr.unice.polytech.modalis.familiar.variable;

import java.util.Set;

import org.xtext.example.mydsl.fML.SliceMode;

import fr.unice.polytech.modalis.familiar.fm.basic.FMLFeatureModel;
import fr.unice.polytech.modalis.familiar.fm.basic.IFeature;
import fr.unice.polytech.modalis.familiar.operations.KnowledgeSynthesis;
import gsd.graph.ImplicationGraph;

public class AttributedFeatureModelVariable extends VariableImpl implements FMLFeatureModel {

	@Override
	public FeatureVariable root() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SetVariable features() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setMandatory(FeatureVariable ft) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setOptional(FeatureVariable ft) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setAlternative(FeatureVariable ft) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setOr(FeatureVariable ft) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double counting() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<String> cores() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> deads() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double CTCR() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ImplicationGraph<String> computeImplicationGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FeatureModelVariable slice(SliceMode sliceMode, String... fts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FeatureModelVariable slice(SliceMode sliceMode, Set<String> fts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FeatureModelVariable ksynthesis(KnowledgeSynthesis kn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFeatureAttribute(FeatureVariable ft, String attributeID,
			Variable rVar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RType getRType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Variable copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(Variable vari) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSpecificValue() {
		// TODO Auto-generated method stub
		return null;
	}

	

	

	

}
