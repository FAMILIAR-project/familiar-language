package be.ac.fundp.info.TVLParser.Util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import be.ac.fundp.info.TVLParser.symbolTables.AttributeSymbol;
import be.ac.fundp.info.TVLParser.symbolTables.FeatureSymbol;
import be.ac.fundp.info.TVLParser.symbolTables.Symbol;

/**
 * An object from this class is a singleton which allows to assign numerical ID
 * to features, attributes and artificial variables. A dictionary ("numericalIDtoSymbol")
 * allows to easily retrieve the feature/attribute corresponding to a numerical ID.
 * 
 */
public class IDGenerator {
	
	// The next numerical ID which will be assign.
	private int nextID;
	
	// Dictionary between the numerical ID and the features or attributes.
	private Map<String, Symbol> numericalIDtoSymbol =  new HashMap<String, Symbol>();
	
	// The instance from the class.
	private static IDGenerator instance;
	
	/**
	 * A private constructor (because of the singleton pattern). It initializes 
	 * "nextID" to 2.
	 */
	private IDGenerator() {
		this.nextID = 2;
	}
	
	/**
	 * Return the instance of the class.
	 * 
	 * @return The instance from the class (if the instance doesn't exist, the method 
	 * creates a new instance and return it.). 
	 */
	public static IDGenerator getInstance() {
		if (instance == null) {
			return instance = new IDGenerator();
		}
		else {
			return instance;
		}
	}
	
	/**
	 * Generate a new numerical ID. In the dictionary "numericalIDtoSymbol", a link is 
	 * created between the new ID and between "symbol".
	 * 
	 * @param symbol
	 * 		The feature/attribute for which a new ID will be generated.
	 * 
	 * @return
	 * 		A new numerical ID.
	 */
	public int getNewID(Symbol symbol) {
		int newID = this.nextID;
		this.nextID = this.nextID + 1;
		this.numericalIDtoSymbol.put(new Integer(newID).toString(), symbol);
		return newID;
	}
	
	/**
	 * Return the feature/attribute corresponding to "numericalID" in "numericalIDtoSymbol".
	 * 
	 * @param numericalID
	 * 		The numerical ID for which a feature/attribute will be returned.
	 * 
	 * @return
	 * 		The symbol corresponding to "numericalID" in "numericalIDtoSymbol".
	 */
	public Symbol getSymbol(int numericalID) {
		return this.numericalIDtoSymbol.get(new Integer(numericalID).toString());
	}
	
	/** 
	 * Allow to set "nextID" ( the next numerical ID that will be attributed ) to "newID"
	 *  
	 * @param newID
	 * 		The new ID that will attributed to "nextID";
	 */
	public void setNextID(int newID) {
		this.nextID = newID;
	}
	
	/**
	 * This method allows to reset the generator. "nextID" is re-initialized to 2 and the
	 * "numericalIDtoSymbol" is cleaned out from all its values.
	 */
	public void reset() {
		this.nextID = 2;
		this.numericalIDtoSymbol = new HashMap<String, Symbol>();
	}
	
	/**
	 * Stores the mapping between numerical IDs and features in a file.
	 * @return The number of entries.
	 */
	public int storeDictionnary(String filepath) {
		Iterator<Entry<String, Symbol>> iterate = this.numericalIDtoSymbol.entrySet().iterator();
		Entry<String, Symbol> mapping;
		int ctr = 0;
		String key;
		Symbol value;
		String ID = null;
		FeatureSymbol feature;
		AttributeSymbol attribute;
		ArrayList<String> textualIDs = new ArrayList<String>();
		try {
			PrintWriter pw =  new PrintWriter(new BufferedWriter(new FileWriter(filepath)));
			while(iterate.hasNext()) {
				mapping = (Entry<String, Symbol>) iterate.next();
				key = (String) mapping.getKey();
				value = (Symbol) mapping.getValue();
				if (value instanceof FeatureSymbol) {
					feature = (FeatureSymbol) value;
					ID = feature.getID();
					pw.println(key+" "+ID);
					ctr++;
				}
				else if (value instanceof AttributeSymbol) {
					attribute = (AttributeSymbol) value;
					ID = attribute.getID();
					pw.println(key+" "+ID);
					ctr++;
				}
				else if(value == null) {
					pw.println(key);
					ctr++;
					ID = null;
				}
				if(ID != null) {
					for(int i = 0; i < textualIDs.size(); i++) {
						if(ID.equals(textualIDs.get(i)))
							return -1;
					}
					textualIDs.add(ID);
				}
			}
			pw.close();
			return ctr;
		}
		catch(IOException e) {
			System.out.println("Could not store the dictionnary in the file "+filepath+".\n");
			return -1;
		}
	}
}
