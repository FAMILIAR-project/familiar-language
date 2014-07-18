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

package fr.familiar.operations;

import fr.familiar.fm.FeatureNodeUtils;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

import org.apache.log4j.Logger;
import org.xtext.example.mydsl.fML.SliceMode;

import com.google.common.collect.Sets;
import com.google.common.collect.TreeMultimap;

public abstract class FMSlicer {
	
	protected static Logger _LOGGER = Logger.getLogger(FMSlicer.class);

	
	public static String FAKE_ROOT_NAME = "fakeRoot";
	
	public abstract FeatureModelVariable sliceFM(FeatureModelVariable fmToSlice, Collection<String> fts, SliceMode sliceMode) ;
	
	
	
	
	/**
	 * @param fla
	 * @param hierarchy
	 * @param slideMode
	 * @return
	 */
	public FeatureGraph<String> sliceHierarchy(FeatureModelVariable fmToSlice, Collection<String> fts,
			SliceMode sliceMode) {
		if (sliceMode == SliceMode.EXCLUDING)
			return sliceExclusiveHierarchy(fmToSlice, fts);
		else if (sliceMode == SliceMode.INCLUDING) {
			Set<String> allFts = fmToSlice.features().names();
			Set<String> excludingFts = Sets.difference(allFts, new HashSet<String>(fts));
			return sliceExclusiveHierarchy(fmToSlice, excludingFts);
		} else {
			FMLShell.getInstance().printError(
					"Unexepected slice mode=" + sliceMode);
			return null;
		}
	}
	
	/**
	 * 
	 * slice hierarchy
	 * @param fm
	 * @param features
	 * @return same hierarchy without features (nodes) and related edges
	 */
	private FeatureGraph<String> sliceExclusiveHierarchy(
			FeatureModelVariable fm, Collection<String> features) {

		
		FeatureGraph<String> fg = fm.getFm().getDiagram().clone();
		FeatureGraph<String> rfg = safeRemoveOfFeatures(fg, features);
		
		/*
		 * fix the root
		 * 
		 */
		Set<FeatureNode<String>> cRoots = KSynthesis.findRoots(rfg);
		
		Set<FeatureNode<String>> fns = rfg.vertices() ;

		TreeMultimap<Integer, String> nodesByDepth = TreeMultimap.create();//new TreeMultimap<Integer, String>();
		for (FeatureNode<String> fn : fns) {
			if (fn.isTop())
				continue ; 
			if (fn.isBottom())
				continue ; 
			int cDepth = fm.depth(fn) ; 
			nodesByDepth.put(cDepth, fn.getFeature());
		}
		_LOGGER.debug("nodesByDepth=" + nodesByDepth);
		SortedSet<Integer> keys = nodesByDepth.keySet() ;
		Integer minDepth = keys.last() ; 
		
		
		
		FeatureNode<String> root = null ;
		SortedSet<String> candidates = nodesByDepth.get(minDepth) ; 
		if (candidates.size() > 1) {
			root = new FeatureNode<String>(FMSlicer.FAKE_ROOT_NAME) ;
			rfg.addVertex(root);
		}
		else 
			root = rfg.findVertex(candidates.first()); 
		
		
		
		_LOGGER.debug("(elected) root=" + root);
		for (FeatureNode<String> cRoot : cRoots) {
			if (cRoot != root) {
				rfg.addEdge(cRoot, root, FeatureEdge.HIERARCHY);
				//rfg.removeEdge(rfg.findEdge(cRoot, rfg.getTopVertex(), FeatureEdge.HIERARCHY));				
			}
			
		}
		
		forceNewRoot(rfg, root.getFeature());
		
		return rfg ; 
		
		
	}
	
	private FeatureGraph<String> safeRemoveOfFeatures(FeatureGraph<String> fg, Collection<String> features) {

		// HACK
		// we basically copy and paste the original feature graph 
	    // by considering only relevant features
		FeatureGraph<String> rfg = FeatureGraphFactory.mkStringFactory().mkTop() ; 
	
		Set<FeatureNode<String>> vertices = fg.vertices() ;
		for (FeatureNode<String> vtx : vertices) {
			if (!features.contains(vtx.getFeature()))
				rfg.addVertex(new FeatureNode<String>(vtx.getFeature()));
		}
		
		Set<FeatureEdge> edges = fg.selectEdges(FeatureEdge.HIERARCHY);
		for (FeatureEdge fe : edges) {
			FeatureNode<String> source = fg.getSource(fe);
			FeatureNode<String> target = fg.getTarget(fe);
			if (FeatureNodeUtils.isTop(source) ||
			 FeatureNodeUtils.isBottom(source)) continue ;
			if (FeatureNodeUtils.isTop(target) ||
			 FeatureNodeUtils.isBottom(target)) continue ;
			
			if (!features.contains(source.getFeature()) && 
					!features.contains(target.getFeature())) {
				rfg.addEdge(source, target, FeatureEdge.HIERARCHY) ; 
			}
			
			
			
		}	
		
	
		return rfg ; 
}
	
	protected void forceNewRoot(FeatureGraph<String> hierarchy,	String rootName) {
		
			
		Set<FeatureNode<String>> currentRoots = findRoots(hierarchy); // roots of the current hierarchy
		
		FeatureNode<String> newRoot = hierarchy.findVertex(rootName);
		if (newRoot == null) {
			FMLShell.getInstance().printError("Does not exist!");
			return ; 
		}
		
		hierarchy.addEdge(newRoot, hierarchy.getTopVertex(), FeatureEdge.HIERARCHY);
		hierarchy.addEdge(newRoot, hierarchy.getTopVertex(), FeatureEdge.MANDATORY);
		
		if (currentRoots.size() == 0) {
			return ; 
		}
		
		//hierarchy.removeVertex(newRoot);
		
		FeatureNode<String> electedRoot = currentRoots.iterator().next() ; 
		_LOGGER.debug("electedRoot=" + rootName + " vs " + electedRoot);
		// TODO if source contains target
		
		// if (!electedRoot.getFeature().equals(SliceAnalyzer.FAKE_ROOT_NAME)) {
		if (!electedRoot.getFeature().equals(rootName)) {

			hierarchy.removeEdge(hierarchy.findEdge(newRoot, electedRoot, FeatureEdge.HIERARCHY));
			
			hierarchy.replaceVertex(electedRoot, newRoot);
			hierarchy.addVertex(electedRoot);
		}
		//hierarchy.addEdge(electedRoot, newRoot, FeatureEdge.HIERARCHY);
		
		// FIXME
		for (FeatureNode<String> root : currentRoots) {
			hierarchy.addEdge(root, newRoot, FeatureEdge.HIERARCHY); // current roots become children
		}
		
		
		/*
		Collection<FeatureEdge> ie = hierarchy.incidentEdges(newRoot);
		hierarchy.removeAllEdges(ie);
		*/
		
	}
	
	protected void cleanUpFakeNode(FeatureGraph<String> hierarchy) {
		try {
			FeatureNode<String> fn = hierarchy.findVertex(FAKE_ROOT_NAME);
			// is leaf when this method is called
			hierarchy.removeVertex(fn);
		}
		catch (Exception e) {
			
		}
		
	}

	
	protected Set<FeatureNode<String>> findRoots(FeatureGraph<String> fgraph) {
		FeatureNode<String> ntop = fgraph.getTopVertex();
		Set<FeatureNode<String>> childs = fgraph.children(ntop);
		return childs;
	}
	
	protected void addSyntheticRoot(FeatureGraph<String> hierarchy,	String rootName) {
		
		
		FeatureNode<String> fnTop = hierarchy.getTopVertex();
		Set<FeatureNode<String>> roots = findRoots(hierarchy);
		FeatureNode<String> fnFakeNode = null ; 	
		try { 
			fnFakeNode = hierarchy.findVertex(rootName) ; // TODO: avoid this situation?
		}
		catch (Exception e) {
			// else
			fnFakeNode = new FeatureNode<String>(rootName);
			hierarchy.addVertex(fnFakeNode);
		}
		
		hierarchy.addEdge(fnFakeNode, fnTop, FeatureEdge.HIERARCHY);
		hierarchy.addEdge(fnFakeNode, fnTop, FeatureEdge.MANDATORY);
		
		for (FeatureNode<String> root : roots) {
			if (root == fnFakeNode) {
				_LOGGER.debug("root equal to fakeNode => " + root);
				continue ; 
			}
			hierarchy.addEdge(root, fnFakeNode, FeatureEdge.HIERARCHY);
		}
		
		
		Collection<FeatureEdge> edges = new ArrayList<FeatureEdge>();
		for (FeatureNode<String> root : roots) {
			if (root != fnFakeNode)
				edges.add(hierarchy.findEdge(root, fnTop, FeatureEdge.HIERARCHY));
		}
		
		hierarchy.removeAllEdges(edges);
		/*
		Collection<FeatureEdge> oes = hierarchy.outgoingEdges(fnFakeNode);
		for (FeatureEdge oe : oes) {
			hierarchy.removeEdge(oe);
		}*/

	}

}
