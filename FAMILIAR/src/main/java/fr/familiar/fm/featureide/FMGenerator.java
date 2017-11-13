/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for
 * manIpulation and Automatic Reasoning) project (2010-2017)
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */
package fr.familiar.fm.featureide;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.prop4j.Implies;
import org.prop4j.Literal;
import org.prop4j.Node;

import de.ovgu.featureide.fm.core.Feature;
import de.ovgu.featureide.fm.core.FeatureModel;
import de.ovgu.featureide.fm.core.editing.evaluation.Generator;
import de.ovgu.featureide.fm.core.io.AbstractFeatureModelWriter;
import de.ovgu.featureide.fm.core.io.guidsl.GuidslWriter;
import de.ovgu.featureide.fm.core.io.sxfm.SXFMWriter;

/**
 * @author mathieuacher
 * 
 */

public class FMGenerator extends Generator {

	public static int[] nFMs = new int[] { 2, 5, 10, 20, 30, 40, 50, 60, 80,
			100, 125, 150, 175, 200, 225, 250,
	// 300,
	// 400,
	// 1000,

	};

	public static int[] nFeatures = new int[] {
	/*
	 * 10, 15, 20, 25, 30, 35,
	 */
	// 40,

	// 45,

	// 50,

	// 60,
	// 70,
	// 80,
	// 90,
	100,
	/*
	 * 125, 150,
	 */
	/*
	 * 175, 200, 250,
	 */

	// 300,
	// 400
	};

	public static int[] percents = new int[] { 100,
			// 95,
			90,
			// 85,
			80,
			// 75,
			70,
			// 65,
			60,
			// 55,
			50 };

	private static int ndebug = 1;

	private static int nsave = 1;

	public static String path = "/Users/macher1/Documents/SANDBOX/MODELS13/" ; // "/Users/mathieuacher/Desktop/PhD/DEV/workspace/JOT2011/";

	/**
	 * @param nFM
	 *            number of feature models generated
	 * @param ncommon
	 *            percentage of common features shared by feature models (e.g.,
	 *            80 means 80%)
	 * @param nFeatures
	 *            number of features in each FM
	 * @return a list of nFM feature models sharing a percentage ncommon of
	 *         common features
	 */
	public List<FeatureModel> genShare(int nFM, int nFeatures, int nCommon) {

		List<FeatureModel> r = new ArrayList<FeatureModel>();

		int nCommonFeatures = (nCommon * nFeatures) / 100; // number of
															// commonFeatures
		int toRemove = nFeatures - nCommonFeatures; // number of features to
													// remove
		// System.out.println("Number of common features: " + nCommonFeatures +
		// " Number of features to remove: " + toRemove);
		// int nFeaturesToConsider = nCommonFeatures + (nFM * toRemove) * 2; //
		// number of features to consider
		// System.out.println("Number of Features to consider: " +
		// nFeaturesToConsider);
		int nFeaturesToAdd = numberOfFeaturesToAdd(nFM, nFeatures, nCommon);

		System.out.println("nFM: " + nFM + " nComm: " + nFeatures + " per: "
				+ nCommon + "\t #" + nFeaturesToAdd);

		FeatureModel fm = genFeatureModel(nCommonFeatures);

		// randomly add features
		int current = nCommonFeatures + 1;
		for (int j = 0; j < nFM; j++) {
			FeatureModel cfm = (FeatureModel) fm.clone();
			cfm = ourgeneralization(cfm, nFeatures / 4);

			int i = 0;
			while (i < nFeaturesToAdd) {
				// System.out.println("Adding " + current);
				randomlyAddFeature(cfm, current++);
				// current = current + 1;
				i++;
			}
			// debug(cfm);
			cfm.performRenamings();

			if (cfm.getRoot().isOr() || cfm.getRoot().isAlternative()) {
				// System.out.println("before:" + new
				// FeatureModelWriter(cfm).writeToString());
				// System.out.println("Root is OR / Alternative: " +
				// cfm.getRoot().getName());
				cfm.getRoot().changeToAnd();
				// System.out.println("after:" + new
				// FeatureModelWriter(cfm).writeToString());

			}

			assert (!cfm.getRoot().isOr());
			assert (!cfm.getRoot().isAlternative());

			r.add(cfm);
		}

		return r;
	}

	// private boolean removeFeature(int current, FeatureModel fm) {
	//
	//
	//
	// Feature f = fm.getFeature("C" + current);
	// if (f == null) {
	// System.err.println("No feature: " + "C" + current);
	// return false;
	// }
	//
	// System.out.println("Removal of feature: " + f);
	//
	// if (!fm.deleteFeature(f)) { System.err.println("Cannot delete feature");
	// return false; }
	//
	// return true;
	//
	// }

	public FeatureModel genFeatureModel(int numberOfFeatures) {

		Random random = new Random();
		FeatureModel fm = new FeatureModel();
		List<Feature> leaves = new LinkedList<Feature>();
		leaves.add(fm.getFeature("C1"));
		int count = 1;
		while (count < numberOfFeatures) {
			int parentIndex = random.nextInt(leaves.size());
			Feature parent = leaves.remove(parentIndex);
			fm.renameFeature(parent.getName(), "A"
					+ parent.getName().substring(1));
			int childrenCount = random.nextInt(maxChildren) + 1;
			childrenCount = Math.min(childrenCount, numberOfFeatures - count);
			for (int i = 1; i <= childrenCount; i++) {
				Feature child = new Feature(fm, "C" + (count + i));
				fm.addFeature(child);
				parent.addChild(child);
				leaves.add(child);
			}
			if (random.nextBoolean()) {
				parent.changeToAnd();
				for (Feature child : parent.getChildren())
					child.setMandatory(random.nextBoolean());
			} else if (random.nextBoolean())
				parent.changeToOr();
			count += childrenCount;
		}
		fm.performRenamings();
		return fm;

	}

	public static void save(FeatureModel fm, String session, int id) {

		String featureide = "featureide" + File.separator;
		String p = path + session + featureide;

		if (!new File(path + session).isDirectory())
			new File(path + session).mkdir();
		if (!new File(p).isDirectory())
			new File(p).mkdir();

		try {
			AbstractFeatureModelWriter writer = new GuidslWriter(fm);
			File f = new File(p + "fm" + id + ".m");
			if (f.exists()) {
				f.delete();
				f.createNewFile();
			}

			FileOutputStream output = new FileOutputStream(f);
			output.write(writer.writeToString().getBytes());
			output.flush();
			output.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void debug(FeatureModel fm) {

		ndebug++;

		try {
			AbstractFeatureModelWriter writer = new GuidslWriter(fm);
			System.out.println(fm.getFeatures().size());
			System.out.println(writer.writeToString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void randomlyAddFeature(FeatureModel fm, int n) {

		// (randomly) add a feature to feature model fm
		Random random = new Random();
		List<Feature> list = new LinkedList<Feature>(fm.getFeatures());
		Feature feature = list.get(random.nextInt(list.size()));
		// if (feature.hasChildren()) {
		Feature child = new Feature(fm, "C" + n);
		child.setMandatory(true);
		int al = 1 + random.nextInt(3);
		// if (al == 2)
		// child.setMandatory(false);
		if (!fm.addFeature(child))
			System.err.println("Error " + child);

		feature.addChild(child);
		child.setMandatory(true);
		// if (al == 2)
		// child.setMandatory(false);

		// break;
		// }

	}

	public static FeatureModel ourgeneralization(FeatureModel originalFM,
			int numberOfEdits) {
		FeatureModel fm = (FeatureModel) originalFM.clone();
		Random random = new Random();

		for (int i = 0; i < numberOfEdits; i++) {
			List<Feature> list = new LinkedList<Feature>(fm.getFeatures());
			List<Feature> randomizedList = new LinkedList<Feature>();
			while (!list.isEmpty())
				randomizedList.add(list.remove(random.nextInt(list.size())));
			int r = 1 + random.nextInt(7);
			if (r == 1) {
				// Alternative to Or
				for (Feature feature : randomizedList)
					if (feature.getChildrenCount() > 1
							&& feature.isAlternative()) {
						feature.changeToOr();
						break;
					}
			}

			else if (r == 2) {
				// move Optional into Or
				r2: for (Feature feature : randomizedList) {
					Feature parent = feature.getParent();
					if (parent != null && parent.isAnd()
							&& feature.isMandatory() && feature.isOr()) {
						for (Feature child : parent.getChildren())
							if (!child.isMandatory()) {
								parent.removeChild(child);
								feature.addChild(child);
								break r2;
							}
					}
				}
			} else if (r == 3) {
				// And to Or
				for (Feature feature : randomizedList)
					if (feature.getChildrenCount() > 1 && feature.isAnd()) {
						feature.changeToOr();
						break;
					}
			} else if (r == 4) {
				// Or to And
				for (Feature feature : randomizedList)
					if (feature.getChildrenCount() > 1 && feature.isOr()) {
						Feature parent = feature.getParent();
						if (parent != null && !parent.isFirstChild(feature)
								&& parent.isAnd()) {
							parent.removeChild(feature);
							for (Feature child : feature.getChildren()) {
								parent.addChild(child);
								child.setMandatory(false);
							}
							break;
						}
					}
			} else if (r == 5) {
				// Mandatory to Optional
				for (Feature feature : randomizedList) {
					Feature parent = feature.getParent();
					if (parent != null && parent.isAnd()
							&& !parent.isFirstChild(feature)
							&& feature.isMandatory()) {
						feature.setMandatory(false);
						fm.addPropositionalNode(new Implies(new Literal(parent
								.getName()), new Literal(feature.getName())));
						break;
					}
				}
			} else if (r == 6) {
				// Alternative to And
				for (Feature feature : randomizedList)
					if (feature.getChildrenCount() > 1
							&& feature.isAlternative()) {
						Feature parent = feature.getParent();
						if (parent != null && !parent.isFirstChild(feature)
								&& parent.isAnd()) {
							parent.removeChild(feature);
							for (Feature child : feature.getChildren()) {
								parent.addChild(child);
								child.setMandatory(false);
							}
							break;
						}
					}
			} else if (r == 7) {
				// Or to Alternative
				for (Feature feature : randomizedList)
					if (feature.getChildrenCount() > 1 && feature.isOr()) {
						feature.changeToAlternative();
						break;
					}
			}

			else {
				// remove Constraint
				List<Node> nodes = fm.getPropositionalNodes();
				if (!nodes.isEmpty()) {
					int index = random.nextInt(nodes.size());
					fm.removePropositionalNode(nodes.get(index));
				}
			}
		}
		return fm;
	}

	private static String printFeature(Feature feat, String mode, boolean last,
			boolean first) {

		String r = "";

		if (feat == null)
			return r;
		String fName = feat.getName();
		if (feat.isRoot()) {
			r += fName + ": ";
		} else if (mode.equals("AND")) {

			if (feat.isMandatory()) {
				r += fName + " ";
			} else {
				r += fName + "? ";
			}
			if (last)
				r += "; ";
		} else if (mode.equals("ANDopt")) {
			r += fName + "? ";
			if (last)
				r += "; ";
		} else if (mode.equals("OR")) {
			if (first)
				r += "(";
			if (!last)
				r += fName + "|";
			if (last) {
				r += fName + ")+ ; ";
			}
		}

		else if (mode.equals("XOR")) {
			if (first)
				r += "(";
			if (!last)
				r += fName + "|";
			if (last) {
				r += fName + ") ; ";
			}

		} else { // possible??
			System.err.println(("Can't determine Connectiontype of feature"));
			r += (fName + " ");

		}

		return r;

	}

	private static String treatRecursivelyFeature(Feature feat, String mode) {
		String r = "";

		LinkedList<Feature> children;

		String fName = feat.getName();
		r += fName + ": ";

		children = feat.getChildren();
		if (children.isEmpty())
			return r;
		if (feat.isAnd()) {
			mode = "AND";
		} else if (feat.isOr()) {
			mode = "OR";
			if (feat.getChildrenCount() == 1)
				mode = "AND";
		} else if (feat.isAlternative()) {
			mode = "XOR";
			Random ra = new Random();
			if (feat.getChildrenCount() == 1)
				if (ra.nextInt(2) == 1)
					mode = "AND";
				else
					mode = "ANDopt";
		} else
			throw new IllegalStateException("Can't determine "
					+ "Connectiontype of Rootfeature");

		Iterator<Feature> i = children.iterator();
		boolean first = true;
		while (i.hasNext()) {

			Feature f = i.next();
			boolean lastL = !i.hasNext();
			r += printFeature(f, mode, lastL, first);
			first = false;
		}

		Iterator<Feature> rec = children.iterator();
		while (rec.hasNext()) {
			Feature f = rec.next();
			if (f.hasChildren())
				r += treatRecursivelyFeature(f, mode);
		}

		return r;
	}

	public static void featureIDEtoFMCalc(FeatureModel fm, String session,
			int id) {

		String strFM = "fm" + id;
		String buffer = "";

		Feature froot = fm.getRoot();

		buffer += "" + strFM + " = { ";
		buffer += treatRecursivelyFeature(froot, "");
		buffer += treatConstraints(fm);
		buffer += "}";

		// System.out.println(buffer);

		String fmcalc = "fmcalc" + File.separator;
		String p = path + session + fmcalc;

		if (!new File(path + session).isDirectory())
			new File(path + session).mkdir();
		if (!new File(p).isDirectory())
			new File(p).mkdir();

		try {

			File f = new File(p + "fm" + id + ".fmcalc");
			FileOutputStream output = new FileOutputStream(f);
			output.write(buffer.getBytes());
			output.flush();
			output.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String treatConstraints(FeatureModel fm) {
		// TODO Auto-generated method stub
		List<Node> cts = fm.getPropositionalNodes();
		String r = "";
		for (Node constraint : cts) {
			r += printConstraint(constraint) + " ; ";
		}

		return r;
	}

	private static String printConstraint(Node constraint) {
		// TODO Auto-generated method stub
		// System.err.println("Constraint!");
		String cstInFMCalc = constraint.toCNF().toString();
		cstInFMCalc = cstInFMCalc.replace("-", "!");
		String cnf = "(" + cstInFMCalc + ")";

		// System.err.println(cnf);
		return cnf;

	}

	public static void main(String[] args) {

		String[] sessions = new String[] { "ten", "twenty", "thirty", "fourty",
				"sixty", "hundred", "hundredbis", "tenbis", "hugecommonality", // all
																				// features
																				// are
																				// commonly
																				// shared
				"limitcommonality" };

		String sess = "";
		// FMCalcMerge merger = new FMCalcMerge();

		for (int i = 0; i < nFMs.length; i++) {
			int nFM = nFMs[i];
			// generate
			for (int j = 0; j < nFeatures.length; j++) {
				int nFeature = nFeatures[j];
				for (int k = 0; k < percents.length; k++) {
					int percent = percents[k];
					gen(nFM, nFeature, percent,
							targetPathStr(sess, nFM, nFeature, percent));
					// String targ = targetPathStr(sess, nFM, nFeature, percent)
					// + "fmcalc" + File.separator;
					// merger.setTarget(targ);
					// merger.merge();

				}
			}
		}

	}

	public static String targetPathStr(String str, int nFM, int nFeatures,
			int percent) {
		return str + "nFM=" + nFM + "nFeatures=" + nFeatures + "per=" + percent
				+ File.separator;
	}

	public static void gen(int nFMs, int nFeatures, int percent, String session) {

		reset();
		List<FeatureModel> lfm = new FMGenerator().genShare(nFMs, nFeatures,
				percent);
		for (FeatureModel fm : lfm) {

			save(fm, session, nsave); // featureide
			featureIDEtoFMCalc(fm, session, nsave);
			serializeAsSPLOT(fm, session, nsave);
			nsave++;
			// debug(fm);
		}

	}

	private static void serializeAsSPLOT(FeatureModel fm, String session, int id) {
		
		String featureide = "splot" + File.separator;
		String p = path + session + featureide;

		if (!new File(path + session).isDirectory())
			new File(path + session).mkdir();
		if (!new File(p).isDirectory())
			new File(p).mkdir();

		try {
			AbstractFeatureModelWriter writer = new SXFMWriter(fm) ; 
			File f = new File(p + "fm" + id + ".xml");
			if (f.exists()) {
				f.delete();
				f.createNewFile();
			}

			FileOutputStream output = new FileOutputStream(f);
			output.write(writer.writeToString().getBytes());
			output.flush();
			output.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}

	private static void reset() {
		nsave = 1;
		ndebug = 1;
	}

	public static int numberOfFeaturesToAdd(int nFM, int nFeature, int percent) {
		return (numberOfFeaturesToConsider(nFM, nFeature, percent) - nFeature)
				/ nFM;
	}

	public static int numberOfFeaturesToConsider(int nFM, int nFeature,
			int percent) {
		double per = percent;
		double nComm = nFeature;

		return new Double(nComm + (nFM * (nComm * ((100 - per) / per))))
				.intValue();
	}

}