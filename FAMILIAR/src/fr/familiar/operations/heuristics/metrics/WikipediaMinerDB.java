package fr.familiar.operations.heuristics.metrics;

import java.io.File;

import org.wikipedia.miner.model.Wikipedia;
import org.wikipedia.miner.util.WikipediaConfiguration;

public class WikipediaMinerDB {

	private Wikipedia wikipedia;
	private String wikipediaDB;

	public WikipediaMinerDB(String wikipediaDB) {
		this.wikipediaDB = wikipediaDB;
	}
	

	/**
	 * Load the database
	 * closeDatabase must be called after the use of this database
	 * @throws Exception
	 */
	public void loadDatabase() throws Exception {
		WikipediaConfiguration conf= new WikipediaConfiguration(new File(wikipediaDB));
		wikipedia = new Wikipedia(conf, false);
	}
	
	/**
	 * Close the database
	 */
	public void closeDatabase() {
		if (isLoaded()) {
			wikipedia.close();	
		}
	}
	
	public Wikipedia getWikipedia() {
		return wikipedia;
	}
	
	public boolean isLoaded() {
		return wikipedia != null;
	}
}
