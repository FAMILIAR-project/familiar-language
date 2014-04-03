/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
 * http://familiar-project.github.com/
 *
 * FAMILIAR is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

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
