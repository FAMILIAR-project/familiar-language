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

package fr.familiar.fm.converter.tvl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import be.ac.fundp.info.TVLParser.Util.IDGenerator;
import be.ac.fundp.info.TVLParser.symbolTables.AttributeSymbol;
import be.ac.fundp.info.TVLParser.symbolTables.FeatureSymbol;

/**
 * Representation of the TVL <-> FAMILIAR name space. This object contains the
 * conversions between TVL and FAMILIAR names. FAMILIAR don't support multiple
 * features with the same name and with this table of conversion it's now
 * possible to emulate it.
 * 
 * @author Charles Vanbeneden
 * 
 */
public class NameSpaceTVLtoFAMILIARMap {

	// <<idTVL, nameTVL>, nameFAMILIAR> table of conversion
	private Map<Couple<Integer, String>, String> _nameSpaceTVLtoFAMILIAR;
	// <nameFAMILIAR> Used to get unique FAMILIAR names
	private Set<String> _nameSpaceFAMILIAR;
	// Counter for temp feature names.
	private Integer _tempFeatureCounter;
	private MyTVLParser _parser;

	public NameSpaceTVLtoFAMILIARMap(MyTVLParser parser) {
		_nameSpaceTVLtoFAMILIAR = new HashMap<Couple<Integer, String>, String>();
		_nameSpaceFAMILIAR = new HashSet<String>();
		_tempFeatureCounter = 0;
		_parser = parser;
	}

	/**
	 * 
	 * @param name
	 *            Name of the TVL feature.
	 * @param path
	 *            Path of the TVL feature.
	 * @return The FAMILIAR name related to the TVL name. In most case, the
	 *         FAMILIAR name is equivalent to the TVL name.
	 * @throws NameProblemException
	 *             Thrown if there was a problem getting TVL feature information
	 *             in the TVL internal representation. You probably entered a
	 *             bad name or path.
	 */
	public String addName(String name, String path) throws NameProblemException {
		Integer idTVL;
		String path2 = "";
		// TODO added with boolean form
		name.replace("ArtificialParent", "");
		path.replace("ArtificialParent", "");

		if (path == "")
			path2 = name;
		else
			path2 = path + "." + name;
		try {
			FeatureSymbol ft = _parser.getFeatureSymbol(path2) ; 
			idTVL = ft.getDIMACS_ID();
		} catch (Exception e) {
			throw new NameProblemException(e);
		}
		if (IDGenerator.getInstance().getSymbol(idTVL).getID().compareTo(name) != 0)
			throw new NameProblemException("The symbol name from IDGenerator doesn't match the initial feature name! " + path2 + " - "
					+ IDGenerator.getInstance().getSymbol(idTVL).getID());

		Couple<Integer, String> coupleTVL = new Couple<Integer, String>(idTVL, name);
		String temp = _nameSpaceTVLtoFAMILIAR.get(coupleTVL);
		if (temp != null)
			return temp;
		else {

			String nameFAMILIAR = name;
			// The FAMILIAR name already exists.
			if (!_nameSpaceFAMILIAR.add(nameFAMILIAR)) {
				nameFAMILIAR = path.replace(".", "_") + "_" + nameFAMILIAR;
				_nameSpaceFAMILIAR.add(nameFAMILIAR);
			}
			_nameSpaceTVLtoFAMILIAR.put(coupleTVL, nameFAMILIAR);
			return nameFAMILIAR;
		}
	}

	/**
	 * You change the type attribute according to the type of structure you
	 * have.
	 * 
	 * @param name
	 *            Name of the TVL feature.
	 * @param path
	 *            Path of the TVL feature.
	 * @param type
	 *            0 -> Normal feature,1 -> Temp
	 *            feature, 2-> attribute
	 * @return The FAMILIAR name related to the TVL name. In most case, the
	 *         FAMILIAR name is equivalent to the TVL name.
	 * @throws NameProblemException
	 *             Thrown if there was a problem getting TVL feature information
	 *             in the TVL internal representation. You probably entered a
	 *             bad name or path.
	 */
	public String addName(String name, String path, Integer type) throws NameProblemException {
		// TODO added with boolean form
		name.replace("ArtificialParent", "");
		path.replace("ArtificialParent", "");

		// Normal features
		if (type == 0)
			return addName(name, path);
		// Temp features
		else if (type == 1) {
			Couple<Integer, String> coupleTVL = new Couple<Integer, String>(-_tempFeatureCounter, name);
			String tmpStr = "Temp_Feature_" + _tempFeatureCounter + "_";
			_tempFeatureCounter = _tempFeatureCounter + 1;
			String nameFAMILIAR = tmpStr + name;
			// The FAMILIAR name already exists.
			if (!_nameSpaceFAMILIAR.add(nameFAMILIAR)) {
				nameFAMILIAR = tmpStr + path.replace(".", "_") + "_" + name;
				_nameSpaceFAMILIAR.add(nameFAMILIAR);
			}
			_nameSpaceTVLtoFAMILIAR.put(coupleTVL, nameFAMILIAR);
			return nameFAMILIAR;
		}
		// attributes
		else if (type == 2) {
			FeatureSymbol ftSymbol = null;
			try {
				ftSymbol = _parser.getFeatureSymbol(path);
			} catch (Exception e) {
			}
			if (ftSymbol == null || !ftSymbol.hasAttributesSymbols())
				throw new NameProblemException("Parent feature symbol not found ! " + path);
			AttributeSymbol attrSymbol = ftSymbol.getAttributeSymbol(name);
			if (attrSymbol == null)
				throw new NameProblemException("Attribute symbol not found ! " + path + "." + name);
			// try {
			System.out.println(path + "." + name + " - " + attrSymbol.getDIMACS_ID());
			System.out.println("Name : " + IDGenerator.getInstance().getSymbol(attrSymbol.getDIMACS_ID()).getID());
			if (IDGenerator.getInstance().getSymbol(attrSymbol.getDIMACS_ID()).getID().compareTo(name) != 0) {
				System.out.println("thrown");
				throw new NameProblemException("Attribute not in IDGenerator! " + path + "." + name);
			}
			// } catch (Exception e) {
			// throw new NameProblemException("Attribute not in IDGenerator! " +
			// path + "." + name);
			// }

			Couple<Integer, String> coupleTVL = new Couple<Integer, String>(attrSymbol.getDIMACS_ID(), name);
			String temp = _nameSpaceTVLtoFAMILIAR.get(coupleTVL);
			if (temp != null)
				return temp;
			else {
				String nameFAMILIAR = "A_Boolean_" + name;
				_nameSpaceFAMILIAR.add(nameFAMILIAR);
				_nameSpaceTVLtoFAMILIAR.put(coupleTVL, nameFAMILIAR);
				return nameFAMILIAR;
			}

		} else
			// Unknow type
			throw new NameProblemException("Type entered isn't valid.");
	}

	/**
	 * 
	 * @param path
	 *            TVL path of the feature.
	 * @param name
	 *            TVL name of the feature.
	 * @return A key couple that permit to get the FAMILIAR name in the
	 *         nameSpace.
	 * @throws NameProblemException
	 *             Thrown if there was a problem getting TVL feature information
	 *             in the TVL internal representation. You probably entered a
	 *             bad name or path.
	 */
	private Couple<Integer, String> findRef(String path, String name) throws NameProblemException {
		// TODO added with boolean form
		name.replace("ArtificialParent", "");
		path.replace("ArtificialParent", "");

		FeatureSymbol featureSymbol = null;
		Integer idTVL;
		try {
			featureSymbol = _parser.getFeatureSymbol(path + "." + name);
			if (featureSymbol != null) {
				idTVL = featureSymbol.getDIMACS_ID();
				if (IDGenerator.getInstance().getSymbol(idTVL).getID().compareTo(name) != 0) {
					throw new NameProblemException(idTVL + " not found or don't match in the dictionnary! " + path + "." + name);
				}
				Couple<Integer, String> couple = new Couple<Integer, String>(idTVL, name);
				return couple;
			}
			return null;

		} catch (Exception e) {
			try {
				featureSymbol = _parser.getFeatureSymbol(path);

				// Path root et name == rootFeatureName
				if (path.compareTo("root") == 0 && featureSymbol.getID().compareTo(name) == 0) {
					if (IDGenerator.getInstance().getSymbol(featureSymbol.getDIMACS_ID()).getID().compareTo(name) != 0) {
						throw new NameProblemException(featureSymbol.getDIMACS_ID() + " not found or don't match in the dictionnary! "
								+ path + "." + name);
					}
					return new Couple<Integer, String>(featureSymbol.getDIMACS_ID(), name);
				}

				if (!featureSymbol.hasChildrenFeatures())
					return null;
				Map<String, FeatureSymbol> childrenFeatures = featureSymbol.getChildrenFeatures();
				Set<String> keySet = childrenFeatures.keySet();
				Iterator<String> iterator = keySet.iterator();
				while (iterator.hasNext()) {
					Couple<Integer, String> returned = findRef(path + "." + childrenFeatures.get(iterator.next()).getID(), name);
					if (returned != null)
						return returned;
				}
				return null;
			} catch (Exception e1) {
				throw new NameProblemException("Down.");
			}
		}

	}

	/**
	 * Used to search the FAMILIAR name of a TVL feature.
	 * 
	 * @param path
	 *            TVL path of the feature.
	 * @param name
	 *            TVL name of the feature.
	 * @return FAMILIAR name of the TVL feature.
	 * @throws NameProblemException
	 *             Thrown if there was a problem getting TVL feature information
	 *             in the TVL internal representation. You probably entered a
	 *             bad name or path.
	 */
	public String getFAMILIARName(String path, String name) throws NameProblemException {
		// TODO added with boolean form
		name.replace("ArtificialParent", "");
		path.replace("ArtificialParent", "");

		Boolean isAttribute = true;
		if (Character.isUpperCase(name.charAt(0)))
			isAttribute = false;

		// TODO: I am tempting to comment this very strange hack
		if (path == "")
			path = "root";

		AttributeSymbol attributeSymbol;
		Couple<Integer, String> couple = null;
		if (!isAttribute) {
			couple = findRef(path, name);
			if (couple == null)
				throw new NameProblemException("Not found! " + path + "." + name);
			String out = _nameSpaceTVLtoFAMILIAR.get(couple);
			if (out == null)
				throw new NameProblemException("Problem in getting the FAMILIAR name! " + String.valueOf(couple.getFirst()) + " "
						+ couple.getSecond());
			return out;
		} else {
			FeatureSymbol featureSymbol;
			try {
				featureSymbol = _parser.getFeatureSymbol(path);
			} catch (Exception e) {
				throw new NameProblemException(e);
			}
			if (featureSymbol == null || !featureSymbol.hasAttributesSymbols())
				throw new NameProblemException("No feature symbol for " + path + "." + name);
			attributeSymbol = featureSymbol.getAttributesSymbols().get(name);
			if (attributeSymbol == null)
				throw new NameProblemException("No attribute symbol for " + path + "." + name);
			Integer idTVL = attributeSymbol.getDIMACS_ID();
			if (IDGenerator.getInstance().getSymbol(idTVL).getID().compareTo(name) != 0)
				throw new NameProblemException(idTVL + " not found or don't match in the dictionnary! " + path + "." + name);
			couple = new Couple<Integer, String>(idTVL, name);
			String out = _nameSpaceTVLtoFAMILIAR.get(couple);
			if (out == null)
				throw new NameProblemException("Problem in getting the FAMILIAR name! " + String.valueOf(couple.getFirst()) + " "
						+ couple.getSecond());
			return out;
		}
	}

	/**
	 * 
	 * @return The number of feature names recorded.
	 */
	public Integer getNbFeature() {
		return _nameSpaceFAMILIAR.size();
	}
}
