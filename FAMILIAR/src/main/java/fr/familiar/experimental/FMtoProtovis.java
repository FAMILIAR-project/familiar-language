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

package fr.familiar.experimental;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.map.LazyMap;

import fr.familiar.fm.FeatureNodeUtils;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.utils.FileSerializer;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;

public class FMtoProtovis {

	private FeatureModelVariable _fmv;

	private static final String PROTOVIS_LOCATION = "../protovis/protovis-r3.2.js";

	private static final String TEMPLATE_END = "		    </script>\n"
			+ "		  </div></div></body>\n" + "		</html>";

	public FMtoProtovis(FeatureModelVariable fmv) {
		_fmv = fmv;
	}

	public String convert() {

		serializeData(); // content of flare.js
		String layout = getLayout(); // layout instructions in provis
		return getHeader(PROTOVIS_LOCATION, "../output/" + _fmv.getIdentifier()
				+ ".js", "800px", "700px")
				+ layout + TEMPLATE_END;
	}

	private String getHeader(String protovisLocation, String dataFileName,
			String width, String height) {

		String TEMPLATE_START = "<html>\n" + "		  <head>\n" + "		    <title>"
				+ "Feature Model Visualization"
				+ _fmv.getIdentifier()
				+ "</title>\n"
				+ "		    <link type=\"text/css\" rel=\"stylesheet\" href=\"ex.css?3.2\"/>\n"
				+ "		    <script type=\"text/javascript\" src=\""
				+ protovisLocation
				+ "\"></script>\n"
				+ "		    <script type=\"text/javascript\" src=\""
				+ dataFileName
				+ "\"></script>\n"
				+ "		    <style type=\"text/css\">\n"
				+ "\n"
				+ "		#fig {\n"
				+ "		  width: "
				+ width
				+ ";\n"
				+ "		  height: "
				+ height
				+ " ;\n"
				+ "		}\n"
				+ "\n"
				+ "		    </style>\n"
				+ "		  </head>\n"
				+ "		  <body><div id=\"center\"><div id=\"fig\">\n"
				+ "		    <script type=\"text/javascript+protovis\">\n" + "\n";

		return TEMPLATE_START;
	}

	private void serializeData() {

		final String DATA = "output/" + _fmv.getIdentifier() + ".js";
		try {
			FileSerializer.write(DATA, convertInProtovisDataRepresentation());
		} catch (IOException e) {
			FMLShell.getInstance().printError(
					"Unable to convert to Protovis " + e.getMessage());
		}

	}

	private String convertInProtovisDataRepresentation() {

		String fmJS = new FMtoJS(_fmv).toString();
		return "var flare = {\n" + fmJS + "\n};\n";
	}

	private String getLayout() {
		// return activateLayout(ProtovisLayout.ICICLE);
		return getAllLayout(ProtovisLayout.DENDOGRAMS);
		// return activateLayout(ProtovisLayout.TREEMAP);

	}

	private String getAllLayout(ProtovisLayout defaultLayout) {
		StringBuffer sb = new StringBuffer();

		for (ProtovisLayout layout : ProtovisLayout.values()) {
			String functionName = functionLayoutName(layout);
			String layoutContent = getLayout(layout);

			sb.append("function " + functionName + "() {\n" + layoutContent
					+ "}\n\n");
		}

		// sb.append(functionLayoutName(defaultLayout) + "();\n"); // call to
		// the function

		// hack
		String layoutSelector = "</script> <div class=\"header\">\n"
				+ "      <b>Layout:</b>\n"
				+ "      <select onchange=\"layoutValue = this.value; eval(layoutValue);\">\n";

		for (ProtovisLayout layout : ProtovisLayout.values()) {
			String functionName = functionLayoutName(layout);
			String selected = layout == defaultLayout ? "selected=\"true\""
					: "";
			layoutSelector += "        <option value=\"" + functionName
					+ "();\" " + selected + ">" + functionName + "</option>\n";
		}

		layoutSelector += "      </select>\n" + "    </div><script>\n" + // hack!
				"";

		sb.append(layoutSelector);

		return sb.toString();
	}

	private static String functionLayoutName(ProtovisLayout layout) {
		return layout.name().toLowerCase() + "Layout";
	}

	private String activateLayout(ProtovisLayout layout) {
		String functionName = functionLayoutName(layout);
		String layoutContent = getLayout(layout);
		return constructFunctionLayout(layoutContent, functionName);
	}

	private String getLayout(ProtovisLayout layout) {
		return ProtovisLayoutFactory.mkLayout(layout);
	}

	private String constructFunctionLayout(String layoutContent,
			String layoutFunctionName) {
		return "function " + layoutFunctionName + "() {\n" + layoutContent
				+ "}" // end bracket for the function
				+ "\n\n" + layoutFunctionName + "();\n" // call to the function
		;
	}

	class FMtoJS {

		Map<String, Integer> ftToId = new HashMap<String, Integer>();

		Map<FeatureNode<String>, String> groupToId = LazyMap.decorate(
				new HashMap<FeatureNode<String>, String>(),
				new Factory<String>() {

					private int groupId = 1;

					public String create() {
						return "G" + groupId++;
					}

				});

		/**
		 * increment for id generator
		 */
		private int _id = 10000;

		private FeatureModel<String> _fm;

		private FeatureModelVariable _fmv;

		public FMtoJS(FeatureModelVariable fmv) {
			_fmv = fmv;
			_fm = fmv.getFm();
		}

		/**
		 * Inspired from SPLOT converter
		 * 
		 * @return a JavaScript variable-based representation of a feature model
		 */
		public String toString() {

			final FeatureGraph<String> fg = _fm.getDiagram();

			final StringBuilder sb = new StringBuilder();

			if (fg.isTop())
				return FeatureGraphFactory.DEFAULT_TOP_STRING;
			else if (fg.isBottom())
				return FeatureGraphFactory.DEFAULT_BOTTOM_STRING;

			// starting from the root
			String rootName = _fmv.root().getFtName();
			FeatureNode<String> rootNode = fg.findVertex(rootName);
			sb.append(processFt(rootNode, fg, "") + "}\n");

			return sb.toString();
		}

		private String processFt(FeatureNode<String> v,
				FeatureGraph<String> fg, String indent) {

			StringBuilder sb = new StringBuilder();
			String newIndent = indent;

			if (v.equals(fg.getTopVertex()))
				return "";
			else if (FeatureVariable.isRoot(v, _fmv))
				sb.append("" + ftName(v) + ": {" + "\n");

			// process groups

			for (FeatureEdge e : fg.incomingEdges(v)) {

				if (e.getType() == FeatureEdge.MUTEX)
					throw new UnsupportedOperationException(
							"Does not support MUTEX group -- refactoring needed !");

				Set<FeatureNode<String>> sources = fg.getSources(e);

				if (sources.size() == 1) {
					newIndent = indent + "\t";
					// opt or mand
					FeatureNode<String> childFt = sources.iterator().next();
					if (FeatureNodeUtils.isMandatory(fg, childFt)
							&& e.getType() != FeatureEdge.HIERARCHY) {
						sb.append(newIndent + " " + ftName(childFt));

						boolean hasChildren = hasChildren(childFt, fg);
						if (hasChildren)
							sb.append(": {\n");
						else
							sb.append(": " + featureID(childFt) + ",\n");
						sb.append(processFt(childFt, fg, newIndent));
						if (hasChildren)
							sb.append(newIndent + "},\n");
					} else if (FeatureNodeUtils.isOptional(fg, childFt)) {
						sb.append(newIndent + " " + ftName(childFt));

						boolean hasChildren = hasChildren(childFt, fg);
						if (hasChildren)
							sb.append(": {\n");
						else
							sb.append(": " + featureID(childFt) + ",\n");
						sb.append(processFt(childFt, fg, newIndent));
						if (hasChildren)
							sb.append(newIndent + "},\n");
					} else {
						/*
						 * FMLShell.getInstance().printDebugMessage
						 * ("not optional and mandatory while being alone:" +
						 * " childFt=" + childFt );
						 */
						continue;
					}

				}

				switch (e.getType()) {
				case FeatureEdge.XOR:
					// sb.append(newIndent + ":g [1,1]\n");
					newIndent = indent + "\t\t";
					for (FeatureNode<String> source : sources) {
						sb.append(newIndent + " " + ftName(source));

						boolean hasChildren = hasChildren(source, fg);
						if (hasChildren)
							sb.append(": {\n");
						else
							sb.append(": " + featureID(source) + " ,\n");
						sb.append(processFt(source, fg, newIndent));
						if (hasChildren)
							sb.append(newIndent + "},\n");
					}
					break;
				case FeatureEdge.OR:
					// sb.append(newIndent + ":g [1,*]\n");
					newIndent = indent + "\t\t";
					for (FeatureNode<String> source : sources) {
						sb.append(newIndent + " " + ftName(source));

						boolean hasChildren = hasChildren(source, fg);
						if (hasChildren)
							sb.append(": {\n");
						else {
							sb.append(": " + featureID(source) + " ,\n");

						}
						sb.append(processFt(source, fg, newIndent));
						if (hasChildren)
							sb.append(newIndent + "},\n");

					}
					break;
				}

				// sb.append(",\n");

			}

			return sb.toString();
		}

		private boolean hasChildren(FeatureNode<String> childFt,
				FeatureGraph<String> fg) {
			return fg.incomingEdges(childFt).size() >= 1;
		}

		private String ftName(FeatureNode<String> v) {
			return v.getFeature();
		}

		private String featureID(String ftName) {
			if (!ftToId.containsKey(ftName))
				ftToId.put(ftName, _id++); // TODO: unique id
			return "" + ftToId.get(ftName).toString();
		}

		private String featureID(FeatureNode<String> v) {
			return featureID(v.getFeature());
		}

	}

}
