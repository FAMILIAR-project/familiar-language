/**
 * 
 */
package fr.familiar.fm.converter;

import java.io.StringWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.map.LazyMap;
import org.apache.log4j.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import fr.familiar.fm.FeatureNodeUtils;
import fr.familiar.operations.FMLExpressionUtil;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.ExpressionUtil;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;

/**
 * @author mathieuacher
 * 
 */
public class SPLOTConverter {
	
	
	private static Logger _LOGGER = Logger.getLogger(SPLOTConverter.class);


	private FeatureModelVariable _fmv;

	Map<String, Integer> ftToId = new HashMap<String, Integer>();

	Map<FeatureNode<String>, String> groupToId = LazyMap.decorate(
			new HashMap<FeatureNode<String>, String>(), new Factory<String>() {

				private int groupId = 1;

				public String create() {
					return "G" + groupId++;
				}

			});

	/**
	 * increment for id generator
	 */
	private int _id = 0;

	/**
	 * the feature model to process internally (a clone/copy)
	 */
	private FeatureModel<String> _fm;

	public SPLOTConverter(FeatureModelVariable fmv) {
		_fmv = fmv;
		_fm = fmv.getFm(); // FeatureModelParser.parseString(fmv.getFm().toString());
	}

	/**
	 * @return a string representation of the feature model in the SPLOT format
	 */
	public String toStringRepresentation() {
		return fMLtoSPLOT();
		// return featureIDEtoSPLOT() ;
	}

	/**
	 * FML -> FeatureIDE -> SPLOT (certainly be deprecated in the future (rapid
	 * prototyping, it is better to directly transform from FML to SPLOT)
	 * 
	 * @return a string representation of the feature model in the SPLOT format
	 *         using FeatureIDE bridge
	 */
	@Deprecated
	private String featureIDEtoSPLOT() {
		return null;
	}

	/**
	 * FML -> SPLOT
	 * 
	 * @return a string representation of the feature model in the SPLOT format
	 */
	private String fMLtoSPLOT() {

		// Create Empty DOM Document
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setIgnoringComments(true);
		dbf.setIgnoringElementContentWhitespace(false);
		dbf.setCoalescing(false);
		dbf.setExpandEntityReferences(false);
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException pce) {
			System.err.println(pce);
			System.exit(1);
		}
		Document doc = db.newDocument();

		/****** create the Xml Representation ******/
		Element elem = createXmlDocHeader(doc); // elem is the root node of the
												// XML

		Node featTree = doc.createElement("feature_tree");
		elem.appendChild(featTree);
		featTree.appendChild(doc.createTextNode("\n"));

		// feature model (tree) content
		String fmTreeContent = transformFMLtoSPLOT();
		Node fTreeNode = doc.createTextNode(fmTreeContent);
		featTree.appendChild(fTreeNode);

		// constraints in CNF form
		Node propConstr = doc.createElement("constraints");
		elem.appendChild(propConstr);
		propConstr.appendChild(doc.createTextNode("\n"));
		Set<String> cnfs = transformFMLConstraintstoSPLOT();
		int ncst = 0;
		for (String cnf : cnfs) {
			Node code = doc.createTextNode("C" + ncst + ":" + cnf + "\n");
			propConstr.appendChild(code);
			ncst++;
		}

		/******* transform the Xml Representation into a String ******/
		Transformer transfo = null;
		try {
			transfo = TransformerFactory.newInstance().newTransformer();
		} catch (TransformerConfigurationException e) {
		} catch (TransformerFactoryConfigurationError e) {
			
		}
		transfo.setOutputProperty(OutputKeys.METHOD, "xml");
		transfo.setOutputProperty(OutputKeys.INDENT, "yes");
		transfo.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		StreamResult result = new StreamResult(new StringWriter());
		DOMSource source = new DOMSource(doc);
		try {
			transfo.transform(source, result);
		} catch (TransformerException e) {
			
		}
		return result.getWriter().toString();

	}

	private Set<String> transformFMLConstraintstoSPLOT() {
		Set<String> cnfs = new HashSet<String>();

		Set<Expression<String>> constraints = _fm.getConstraints();
		// convert each expression to CNF expression
		for (Expression<String> expression : constraints) {
			// _LOGGER.debug("expression=" +
			// expression);
			Expression<String> cnf = FMLExpressionUtil.toCNF(expression);

			Collection<Expression<String>> cnfsSplitted = ExpressionUtil
					.splitConjunction(cnf);
			for (Expression<String> cnfSplitted : cnfsSplitted) {
				cnfs.add(cnfToString(cnfSplitted));
			}

		}

		return cnfs;
	}

	private String cnfToString(Expression<String> cnf) {

		ExpressionType type = cnf.getType();
		switch (type) {
		case FEATURE:
			return featureID(cnf.getFeature());
		case TRUE:
			return featureID(_fmv.root().name());
		case FALSE:
			return "~" + featureID(_fmv.root().name());
		case NOT:
			return "~" + cnfToString(cnf.getLeft());
		default:
			StringBuffer sb = new StringBuffer();
			sb.append(cnfToString(cnf.getLeft()));
			sb.append(" " + typeValue(type) + " ");
			sb.append(cnfToString(cnf.getRight()));
			return sb.toString();
		}

	}

	/**
	 * supposed to be in conjonctive normal form
	 * 
	 * @param type
	 * @return
	 */
	private String typeValue(ExpressionType type) {
		assert (type == ExpressionType.AND || type == ExpressionType.OR);
		if (!(type == ExpressionType.AND || type == ExpressionType.OR))
			_LOGGER.debug(
					"Unknown type (should be in CNF!): " + type);
		return type == ExpressionType.AND ? "and"
				: type == ExpressionType.OR ? "or" : type.toString();
	}

	/**
	 * Inspired from FeatureModelSerializer
	 * 
	 * @return a FML, string-based representation of a feature model
	 */
	private String transformFMLtoSPLOT() {

		// transform Mutex-groups in the feature model
		normalizeMutexGroups();
		final FeatureGraph<String> fg = _fm.getDiagram();

		// _LOGGER.debug("After normalization (Mutex/splot):\nfm="
		// + _fm + "\nfg=" + fg);

		final StringBuilder sb = new StringBuilder();

		if (fg.isTop())
			return FeatureGraphFactory.DEFAULT_TOP_STRING;
		else if (fg.isBottom())
			return FeatureGraphFactory.DEFAULT_BOTTOM_STRING;

		// starting from the root
		String rootName = _fmv.root().getFtName();
		FeatureNode<String> rootNode = fg.findVertex(rootName);
		sb.append(processFt(rootNode, fg, ""));

		return sb.toString();
	}

	private String processFt(FeatureNode<String> v, FeatureGraph<String> fg,
			String indent) {

		StringBuilder sb = new StringBuilder();
		String newIndent = indent;

		if (v.equals(fg.getTopVertex()))
			return "";
		else if (FeatureVariable.isRoot(v, _fmv))
			sb.append(":r " + ftName(v) + "(" + featureID(v) + ")\n");

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
					sb.append(newIndent + ":m " + ftName(childFt) + "("
							+ featureID(childFt) + ")\n");
					sb.append(processFt(childFt, fg, newIndent));
				} else if (FeatureNodeUtils.isOptional(fg, childFt)) {
					sb.append(newIndent + ":o " + ftName(childFt) + "("
							+ featureID(childFt) + ")\n");
					sb.append(processFt(childFt, fg, newIndent));
				} else {
					/*
					 * _LOGGER.debug
					 * ("not optional and mandatory while being alone:" +
					 * " childFt=" + childFt );
					 */
					continue;
				}

			}

			switch (e.getType()) {
			case FeatureEdge.XOR:
				sb.append(newIndent + ":g [1,1]\n");
				newIndent = indent + "\t\t";
				for (FeatureNode<String> source : sources) {
					sb.append(newIndent + ": " + ftName(source) + "("
							+ featureID(source) + ")\n");
					sb.append(processFt(source, fg, newIndent));
				}
				break;
			case FeatureEdge.OR:
				sb.append(newIndent + ":g [1,*]\n");
				newIndent = indent + "\t\t";
				for (FeatureNode<String> source : sources) {
					sb.append(newIndent + ": " + ftName(source) + "("
							+ featureID(source) + ")\n");
					sb.append(processFt(source, fg, newIndent));
				}
				break;
			}

		}

		return sb.toString();
	}

	private String ftName(FeatureNode<String> v) {
		return v.getFeature();
	}

	private String featureID(String ftName) {
		if (!ftToId.containsKey(ftName))
			ftToId.put(ftName, _id++); // TODO: unique id
		return "_r" + ftToId.get(ftName).toString();
	}

	private String featureID(FeatureNode<String> v) {
		return featureID(v.getFeature());
	}

	/**
	 * normalize the feature model _fm (no mutex-groups)
	 */
	private void normalizeMutexGroups() {
		// _LOGGER.debug("(starting) Mutex normalization");
		FeatureGraph<String> fg = _fm.getDiagram();
		for (String ft : fg.features()) {

			FeatureNode<String> v = fg.findVertex(ft);
			// if (v == null)
			// _LOGGER.debug("v should not be null during the normalization");
			assert (v != null);
			if (FeatureNodeUtils.hasMutexGroup(fg, v)) {
				_LOGGER.debug(
						"MUTEX-group detected for v=" + v
								+ " -- refactoring needed");
				FeatureModelUtil.unfoldGroup(fg, v, FeatureEdge.MUTEX);

			}

		}
		// _LOGGER.debug("(ending) Mutex normalization");
	}

	/**
	 * Creates the DOM Document Representation from the feature model fmodel by
	 * using createXmlDocRec
	 * 
	 * @param doc
	 *            Document where the feature model is put
	 * @return
	 */
	private Element createXmlDocHeader(Document doc) {
		Element elem = doc.createElement("feature_model");
		// AM
		elem.setAttribute("name", _fmv.getIdentifier());
		doc.appendChild(elem);

		// AM (meta)
		Node meta = (Node) doc.createElement("meta");
		addMetaData(doc, meta, "description");
		addMetaData(doc, meta, "creator");
		addMetaData(doc, meta, "address");
		addMetaData(doc, meta, "email");
		addMetaData(doc, meta, "phone");
		addMetaData(doc, meta, "website");
		addMetaData(doc, meta, "organization");
		addMetaData(doc, meta, "department");
		addMetaData(doc, meta, "date");
		addMetaData(doc, meta, "reference");
		elem.appendChild((org.w3c.dom.Node) meta);
		return elem;

	}

	/**
	 * @param meta
	 */
	private void addMetaData(Document doc, Node meta, String description) {
		Element data = doc.createElement("data");
		Attr name = doc.createAttribute("name");
		name.setNodeValue(description);
		data.setAttributeNode(name);
		meta.appendChild(data);

	}

}
