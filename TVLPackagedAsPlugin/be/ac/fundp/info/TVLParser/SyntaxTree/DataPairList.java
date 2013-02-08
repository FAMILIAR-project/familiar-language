package be.ac.fundp.info.TVLParser.SyntaxTree;

import java.util.Vector;

public class DataPairList {
	
	Vector<DataPair> dataPairs;
	
	public DataPairList(DataPair dataPair) {
		this.dataPairs = new Vector<DataPair>();
		this.dataPairs.add(dataPair);
	}
	
	public DataPairList(DataPair dataPair, DataPairList dataPairs) {
		this.dataPairs = dataPairs.getDataPairs();
		this.dataPairs.add(dataPair);
	}

	/**
	 * @return the dataPairList
	 */
	public Vector<DataPair> getDataPairs() {
		return this.dataPairs;
	}
	
}
