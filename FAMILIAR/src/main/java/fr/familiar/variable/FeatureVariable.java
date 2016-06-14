/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) project (https://nyx.unice.fr/projects/familiar/).
 *
 * Copyright (C) 2010 University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory
 *
 * FAMILIAR is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  (See file COPYING)  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package fr.familiar.variable;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.xtext.example.mydsl.fml.FeatureEdgeKind;

import fr.familiar.fm.basic.FMLFeature;
import fr.familiar.fm.basic.VEdge;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.interpreter.NSFactory;
import fr.familiar.interpreter.VariableNotExistingException;
import fr.familiar.parser.FeatureOperationAnalyzer;
import fr.familiar.parser.NameSpace;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;
import gsd.synthesis.FeatureType;
import gsd.synthesis.Requires;

public class FeatureVariable extends VariableImpl implements FMLFeature {
	
	private static Logger _LOGGER = Logger.getLogger(FeatureVariable.class);

	public static final String SEPARATOR = "."; // TODO: @deprecated "::";
	private FeatureModelVariable _fmv;
	private String _ftName;

	public FeatureVariable(String name, String ftName,
			FeatureModelVariable fmw, NameSpace ns) {
		this.name = name;
		this._ftName = ftName;
		this._fmv = fmw;
		this.ns = ns;
		this.vid = new VariableIdentifier(name, ns);
	}

	public FeatureVariable(String name, String ftName, FeatureModelVariable fmw) {
		this(name, ftName, fmw, NSFactory.mkEmpty());
	}

	public FeatureVariable(String ftName, FeatureModelVariable fmw) {
		this (null, ftName, fmw);
		// set feature attributes
		_mapFeatureAttributes() ; 
	}

	private void _mapFeatureAttributes() {
		Map<String, List<FeatureAttribute>> ftAttrs = _fmv.getFeatureAttributes() ; 
		if (ftAttrs.containsKey(_ftName)) {
			List<FeatureAttribute> attr = ftAttrs.get(_ftName) ; 
			for (FeatureAttribute featureAttribute : attr) {
				String attrName = featureAttribute.getName() ; 
				Variable var = featureAttribute.getValue() ; 
				super.put(attrName, var);
			}			
		}
	}
	
	public Variable put (String attributeID, Variable var) {
		_fmv.setFeatureAttribute(this, attributeID, var);
		_mapFeatureAttributes() ; 
		return super.put(attributeID, var);
	}
	
	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.variable.VariableAttributeHandler#lookup(java.lang.String)
	 */
	@Override
	public Variable lookup(String attributeID) throws VariableNotExistingException {
		_mapFeatureAttributes() ; 
		return super.lookup(attributeID);
	}
	
		

	@Override
	public Variable copy() {
		return new FeatureVariable(this.name, this._ftName,
				(FeatureModelVariable) _fmv.copy(), ns);
	}

	@Override
	public RType getRType() {
		return RType.FEATURE;
	}

	@Override
	public String getSpecificValue() {
		return _ftName;
	}

	@Override
	public void setValue(Variable vari) {
		if (vari instanceof FeatureVariable) {
			FeatureVariable fw = (FeatureVariable) vari;
			setFtName(fw.getFtName());
			setFmw(fw.getFeatureModel());
			return;
		}
		FMLShell.getInstance().setError("Setting value type is not correct");

	}

	/**
	 * @return the fmw
	 */
	public FeatureModelVariable getFeatureModel() {
		return _fmv;
	}

	/**
	 * @param fmw
	 *            the fmw to set
	 */
	public void setFmw(FeatureModelVariable fmw) {
		this._fmv = fmw;
	}

	/**
	 * @return the ftName
	 */
	public String getFtName() {
		return _ftName;
	}

	/**
	 * @param ftName
	 *            the ftName to set
	 */
	public void setFtName(String ftName) {
		this._ftName = ftName;
	}

	public boolean setOptionalStatus() {

		FeatureModel<String> fm = _fmv.getFm();
		FeatureGraph<String> fg = fm.getDiagram();
		FeatureNode<String> fn = fg.findVertex(getFtName());

		fg.incomingEdges(fn);

		if (FeatureOperationAnalyzer.isRoot(fn, fg)) {
			FMLShell.getInstance().setError(
					"Unable to change the variability operator of root "
							+ getFtName());
			return false;
		}

		Collection<FeatureEdge> edgesFromParents = fg.outgoingEdges(fn);
		_LOGGER.debug(
				"outgoing: " + edgesFromParents);

		FeatureEdgeKind fk = FeatureEdgeKind.OPTIONAL; // default
		for (FeatureEdge fe : edgesFromParents) {
			if (fe.getType() == FeatureEdge.MANDATORY) {
				_LOGGER.debug(
						"mandatory --> optional ");
				fg.removeEdge(fe);
				fk = FeatureEdgeKind.MANDATORY;
			} else if (fe.getType() == FeatureEdge.OR) {
				_LOGGER.debug("OR --> optional ");
				fg.removeEdge(fe);
				fk = FeatureEdgeKind.OR;
			} else if (fe.getType() == FeatureEdge.XOR) {
				_LOGGER.debug("XOR --> optional ");
				fg.removeEdge(fe);
				fk = FeatureEdgeKind.ALTERNATIVE;
			}
		}

		if (fk == FeatureEdgeKind.OPTIONAL) {
			_LOGGER.debug(
					"The optional status is already set!");
		}

		return true;

	}

	public boolean setMandatoryStatus() {

		FeatureModel<String> fm = _fmv.getFm();
		FeatureGraph<String> fg = fm.getDiagram();
		FeatureNode<String> fn = fg.findVertex(getFtName());

		fg.incomingEdges(fn);

		if (FeatureOperationAnalyzer.isRoot(fn, fg)) {
			FMLShell.getInstance().setError(
					"Unable to change the variability operator of root "
							+ getFtName());
			return false;
		}

		Collection<FeatureEdge> edgesFromParents = fg.outgoingEdges(fn);
		_LOGGER.debug(
				"outgoing: " + edgesFromParents);

		FeatureEdgeKind fk = FeatureEdgeKind.OPTIONAL; // default
		FeatureNode<String> lastTarget = null;
		for (FeatureEdge fe : edgesFromParents) {

			if (fe.getType() == FeatureEdge.MANDATORY) {
				fk = FeatureEdgeKind.MANDATORY;
				FMLShell.getInstance().printWarning(
						"The mandatory status is already set for " + fn);
				return true;
			} else if (fe.getType() == FeatureEdge.OR) {
				_LOGGER.debug("OR --> mandatory");
				lastTarget = fg.getTarget(fe);
				fg.removeEdge(fe);
				fg.addEdge(fn, lastTarget, FeatureEdge.MANDATORY);

			} else if (fe.getType() == FeatureEdge.XOR) {
				_LOGGER.debug("XOR --> mandatory");
				lastTarget = fg.getTarget(fe);
				fg.removeEdge(fe);
				fg.addEdge(fn, lastTarget, FeatureEdge.MANDATORY);
			} else {
				lastTarget = fg.getTarget(fe);
			}

			if (lastTarget != null) {
				String ft = fn.getFeature();
				String ftParent = lastTarget.getFeature();
				fm.addConstraint(new Requires<String>(ft, ftParent));
				fm.addConstraint(new Requires<String>(ftParent, ft));
			}

		}

		if (fk == FeatureEdgeKind.OPTIONAL) {
			fg.addEdge(fn, lastTarget, FeatureEdge.MANDATORY);
		}

		return true;

	}

	public String name() {
		return _ftName;
	}

	public FeatureVariable parent() {
		FeatureNode<String> fn = node();
		FeatureGraph<String> fgraph = getGraph();

		if (isRoot()) {
			_LOGGER.debug("root has no parent!");
			return null;
		}

		Set<FeatureNode<String>> parents = fgraph.parents(fn);
		if (parents.size() > 1)
			_LOGGER.debug(
					"more than one parent: " + parents.toString());
		if (parents.size() == 0) {
			FMLShell.getInstance().setError("No parent... ");
			return null;
		}
		FeatureNode<String> parent = parents.iterator().next(); // first one
		return new FeatureVariable(parent.toString(), getFeatureModel());
	}

	/**
	 * TODO: integrate into FML language
	 * 
	 * @return
	 */
	public SetVariable descendants() {

		Set<FeatureNode<String>> descs = descendants_();
		Set<Variable> vars = new HashSet<Variable>();
		for (FeatureNode<String> ft : descs) {
			if (ft.isTop() || ft.isBottom())
				continue;
			FeatureVariable ftv = new FeatureVariable(ft.toString(), getFeatureModel());
			vars.add(ftv);
		}
		SetVariable sv = new SetVariable(vars);
		return sv;
	}

	/**
	 * @return
	 */
	private Set<FeatureNode<String>> descendants_() {
		FeatureNode<String> fn = node();
		FeatureGraph<String> fgraph = getGraph();
		return fgraph.descendants(fn);
	}

	/**
	 * TODO: integrate into FML language
	 * 
	 * @return
	 */
	public SetVariable ancestors() {

		Set<FeatureNode<String>> ancs = ancestors_();
		Set<Variable> vars = new HashSet<Variable>();
		for (FeatureNode<String> ft : ancs) {
			if (ft.isTop() || ft.isBottom())
				continue;
			FeatureVariable ftv = new FeatureVariable(ft.toString(), getFeatureModel());
			vars.add(ftv);
		}
		SetVariable sv = new SetVariable(vars);
		return sv;
	}

	/**
	 * @return
	 */
	private Set<FeatureNode<String>> ancestors_() {
		FeatureNode<String> fn = node();
		FeatureGraph<String> fgraph = getGraph();

		if (isRoot()) {
			_LOGGER.debug("root has no ancestor!");
			return null;
		}
		return fgraph.ancestors(fn);
	}

	public SetVariable children() {
		FeatureNode<String> fn = node();
		FeatureGraph<String> fgraph = getGraph();
		Set<FeatureNode<String>> nodes = fgraph.children(fn);

		Set<Variable> vars = new HashSet<Variable>();
		for (FeatureNode<String> cnode : nodes) {

			String explicitName = ""
					+ VariableIdentifier.completeName(getFeatureModel().getVid())
					+ FeatureVariable.SEPARATOR + cnode.getFeature();
			FeatureVariable ftw = new FeatureVariable(explicitName,
					cnode.getFeature(), getFeatureModel());
			vars.add(ftw);
		}

		return new SetVariable(vars);
	}

	public FeatureModelVariable whichfm() {
		return _fmv;
	}

	public VEdge operator() {
		// TODO Auto-generated method stub
		return null;
	}

	private FeatureNode<String> node() {

		final FeatureGraph<String> fgraph = getGraph();
		FeatureNode<String> fn = fgraph.findVertex(getFtName());
		_LOGGER.debug(
				"feature node to treat: " + fn.getFeature());
		return fn;

	}

	private FeatureGraph<String> getGraph() {
		final FeatureModelVariable fmw = getFeatureModel();
		final FeatureModel<String> fm = fmw.getFm();
		final FeatureGraph<String> fgraph = fm.getDiagram();
		return fgraph;
	}

	public boolean isRoot() {

		FeatureNode<String> fn = node();
		FeatureGraph<String> fgraph = getGraph();
		return isRoot(fn, fgraph);

	}

	// TODO: buggy since for A : B C, B is root!
	// be careful about "feature hierarchy" edge
	public static boolean isRoot (FeatureNode<String> fn,
			FeatureGraph<String> fgraph) {
		
		
		if (fn.isTop())
			return true;
		
		Set<FeatureNode<String>> parents = fgraph.parents(fn);
		if (parents.size() == 0)
			return true;

		FeatureNode<String> parent = parents.iterator().next(); // first one
		if (parent.isTop())
			return true;
		return false;
	}

	public static boolean isRoot(FeatureNode<String> fn,
			FeatureModelVariable fmv) {
		assert (fn.getType() == FeatureType.SOLITARY);
		String rootName = fmv.root().name();
		return fn.getFeature().equals(rootName);
	}
	
	
	
}
