package be.ac.fundp.info.TVLParser.SyntaxTree;

import java.util.Vector;

public class Data implements FeatureBodyItem {

	Vector<DataPair> dataPairs;
	
	public Data(DataPairList dataPairList) {
		this.dataPairs = dataPairList.getDataPairs();;
	}

	/**
	 * @return the dataPairList
	 */
	public Vector<DataPair> getDataPairs() {
		return dataPairs;
	}
	
	// Utilisé pour la construction d'une feature
	public boolean isAData() {
		return true;
	}
	
	public boolean isAnAttribute() {
		return false;
	}
	
	public boolean isAConstraint() {
		return false;
	}
	
	public boolean isAFeatureGroup() {
		return false;
	}
	
	@Override
	public String toString() {
		String result = "data {\n";
		for (DataPair d : dataPairs) {
			result += d.toString()+"\n";
		}
		result += "}\n";
		return result;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
	
}
