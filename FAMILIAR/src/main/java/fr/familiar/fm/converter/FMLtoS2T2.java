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

package fr.familiar.fm.converter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.map.LazyMap;

import fr.familiar.fm.FeatureModelCloner;
import fr.familiar.fm.FeatureNodeUtils;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;
import fr.inria.familiar.fmlero.fmprimitives.AlternativeGroup;
import fr.inria.familiar.fmlero.fmprimitives.ConfigurationSource;
import fr.inria.familiar.fmlero.fmprimitives.Feature;
import fr.inria.familiar.fmlero.fmprimitives.FeatureGroup;
import fr.inria.familiar.fmlero.fmprimitives.FeatureHasSubfeature;
import fr.inria.familiar.fmlero.fmprimitives.FeatureModelPrimitive;
import fr.inria.familiar.fmlero.fmprimitives.FmprimitivesFactory;
import fr.inria.familiar.fmlero.fmprimitives.GroupHasChild;
import fr.inria.familiar.fmlero.fmprimitives.GroupHasMin;
import fr.inria.familiar.fmlero.fmprimitives.GroupHasParent;
import fr.inria.familiar.fmlero.fmprimitives.MutualExclusive;
import fr.inria.familiar.fmlero.fmprimitives.OrGroup;
import fr.inria.familiar.fmlero.fmprimitives.Requires;
import fr.inria.familiar.fmlero.fmprimitives.impl.FeatureModelImpl;
import fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesFactoryImpl;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;



/**
 * @author mathieuacher
 *
 */
public class FMLtoS2T2 {
	
	
	private int gcID = 0;
	private int gID = 0;
	private int gParent = 0;
	
	private FeatureModelImpl _rFM ;
	
	private FmprimitivesFactory _fmprimitivesFactory;
		
	private FeatureModelVariable _fmv;

	Map<String, Integer> ftToId = new HashMap<String, Integer>();

	Map<FeatureNode<String>, String> groupToId
	= LazyMap.decorate(new HashMap<FeatureNode<String>, String>(), new Factory<String>() {

		private int groupId = 1;
	
		public String create() {
			return "G" + groupId++;
		}

	});


	/**
	 * the feature model to process internally (a clone/copy)
	 */
	private FeatureModel<String> _fm;

	public FMLtoS2T2(FeatureModelVariable fmv) {
		_fmv = fmv ;
		_fm = FeatureModelCloner.clone(fmv.getFm());
		

		_fmprimitivesFactory =  new FmprimitivesFactoryImpl(); 
	}
	
	private String ftName(FeatureVariable fv) {
		return fv.getFtName();
	}
	
	
	
	
	public fr.inria.familiar.fmlero.fmprimitives.FeatureModel process() {
		
		// transform multi-groups in the feature model (Thuem refactorings)
		FeatureModelUtil.normalizeMultiGroups(_fm);
		_fmv = new FeatureModelVariable (_fmv.getIdentifier(), _fm);
		
		final FeatureGraph<String> fg = _fm.getDiagram();
		FMLShell.getInstance().printDebugMessage("After normalization (S2T2):\nfm=" + _fm + "\nfg=" + fg);
	
		
		/* initializing the construction of the resulting S2T2 feature model */
		_rFM = (FeatureModelImpl) _fmprimitivesFactory.createFeatureModel();
		
		_rFM.setId(_fmv.getIdentifier());
		_rFM.setName(_fmv.getIdentifier());
		
		/**** adding all features ****/
		SetVariable sv = _fmv.features();
		for (Variable v : sv.getVars()) {
			assert (v instanceof FeatureVariable);
			FeatureVariable fv = (FeatureVariable) v ;
			fr.inria.familiar.fmlero.fmprimitives.Feature ft = _fmprimitivesFactory.createFeature();
			String ftName = ftName(fv);
			ft.setId(ftName);
			ft.setName(ftName);
			_rFM.getFeatures().add(ft);
		}
		
		/*** setting the root ****/
		String rootName = _fmv.root().getFtName();
		// (seems not necessary or not correctly managed)
		/*
		FeatureIsRootImpl froot = (FeatureIsRootImpl) _fmprimitivesFactory.createFeatureIsRoot();
		
		froot.setId(rootName);
		froot.setName(rootName);
		fr.inria.familiar.fmlero.fmprimitives.Feature ftRoot = getFtByName(rootName);
		froot.setFeature(ftRoot);
		((fr.inria.familiar.fmlero.fmprimitives.impl.FeatureImpl) ftRoot).setFeatureIsRoot(froot);
		
		_rFM.getPrimitives().add(froot);
		*/
		// HACK: root feature should be the first
		_rFM.getFeatures().move(0, getFtByName(rootName));
		
		
		


		/***** setting  (mandatory or optional) relations ******/

		Set<FeatureEdge> pchildEdges = fg.selectEdges(FeatureEdge.HIERARCHY);
		int edgeIDs = 0 ; 
		for (FeatureEdge fe : pchildEdges) {
			FeatureNode<String> source = fg.getSource(fe);
			FeatureNode<String> target = fg.getTarget(fe);
			Feature parent = getFtByName(target.getFeature());
			Feature subFt = getFtByName(source.getFeature()) ;
			if (parent == null || subFt == null)
				continue ;
			
			
			if (FeatureNodeUtils.isInOR(fg, source) || FeatureNodeUtils.isInXOR(fg, source)) 
				continue ;
			FeatureHasSubfeature hasSubFt ; 
			if (fg.findEdge(source, target, FeatureEdge.MANDATORY) == null) 
				hasSubFt = _fmprimitivesFactory.createFeatureHasOptionalSubfeature() ;
			else
				hasSubFt =  _fmprimitivesFactory.createFeatureHasMandatorySubfeature();
			
					
			String idEdge = "edge" + edgeIDs++;
			hasSubFt.setId(idEdge);
			hasSubFt.setName(idEdge);
			hasSubFt.setConfigurationSource(ConfigurationSource.MODEL); // let say
			hasSubFt.setParent(parent);
			hasSubFt.setSubfeature(subFt);
			
			subFt.setFeatureHasParent(hasSubFt);	
			parent.getFeatureHasSubfeature().add(hasSubFt);
			
			_rFM.getPrimitives().add(hasSubFt);
				
			
		}
		
		
		Set<FeatureEdge> groups = fg.selectEdges(FeatureEdge.GROUPS);
		for (FeatureEdge fe : groups) {
			
			Set<FeatureNode<String>> sources = fg.getSources(fe);
			FeatureNode<String> target = fg.getTarget(fe);
			
			Feature parent = getFtByName(target.getFeature());
			if (parent == null)
				continue ;
			
			FeatureModelPrimitive gr ;

			switch (fe.getType()) {
			
			case FeatureEdge.XOR:
				 gr = _fmprimitivesFactory.createAlternativeGroup();
				 break ; 
						 
			case FeatureEdge.OR:
				gr = _fmprimitivesFactory.createOrGroup();
				break ; 
			default:
				FMLShell.getInstance().printError("Unrecognized group=" + fe);
				continue ;
			}
			
			
			setGroup(gr, sources, parent);
			
		
			
			
			
		}
		
		
		// constraints
		// it seems that S2T2 only supports "Requires" constraints
		int cstID = 0;
		for (Expression<String> cst : _fmv.getFm().getConstraints()) {
			
			boolean S2T2supported = false ;
			ExpressionType cstType = cst.getType() ; 
			if (cstType == ExpressionType.IMPLIES || cstType == ExpressionType.IFF) {
				
				Expression<String> cstLeft = cst.getLeft();
				Expression<String> cstRight = cst.getRight();
				
				
				if ((cstLeft.getType() == ExpressionType.FEATURE) && (cstRight.getType() == ExpressionType.FEATURE)) {
					S2T2supported = true ;
					Requires req = _fmprimitivesFactory.createRequires();
					
					String cstIdentifier = "cst" + cstID++;
					req.setName(cstIdentifier);
					req.setId(cstIdentifier);
					req.setConfigurationSource(ConfigurationSource.MODEL);
					
					String cstLeftName = cstLeft.getFeature();
					String cstRightName = cstRight.getFeature();
					
					req.getSources().add(getFtByName(cstLeftName));
					req.getTargets().add(getFtByName(cstRightName));
					_rFM.getPrimitives().add(req);
					
					
					if (cstType == ExpressionType.IFF) {
						Requires req2 = _fmprimitivesFactory.createRequires();
						
						String cstIdentifier2 = "cst" + cstID++;
						req2.setName(cstIdentifier2);
						req2.setId(cstIdentifier2);
						req2.setConfigurationSource(ConfigurationSource.MODEL);
						
						req2.getSources().add(getFtByName(cstRightName));
						req2.getTargets().add(getFtByName(cstLeftName));
						_rFM.getPrimitives().add(req2);
					}
					
					
					
					
				}
				// A -> !B ===> mutex A B 
				// TODO: more generic
				else if ((cstLeft.getType() == ExpressionType.FEATURE) && (cstRight.getType() == ExpressionType.NOT)) {
					S2T2supported = true ;
					MutualExclusive req = _fmprimitivesFactory.createMutualExclusive();
					
					String cstIdentifier = "cst" + cstID++;
					req.setName(cstIdentifier);
					req.setId(cstIdentifier);
					req.setConfigurationSource(ConfigurationSource.MODEL);
					
					String cstLeftName = cstLeft.getFeature();
					String cstRightName = cstRight.getLeft().getFeature(); // NOT
					
					req.getRelatedFeatures().add(getFtByName(cstLeftName));
					req.getRelatedFeatures().add(getFtByName(cstRightName));
					_rFM.getPrimitives().add(req);
				}
				
							
			}
			
			if (!S2T2supported) {
				FMLShell.getInstance().printWarning("Constraint not supported by S2T2: " + cst);
			}
			
		}
		
		return (fr.inria.familiar.fmlero.fmprimitives.FeatureModel) _rFM;
	}
	
	
	private void setGroup(FeatureModelPrimitive gr, Set<FeatureNode<String>> sources, Feature parent) {
		assert (gr instanceof AlternativeGroup || gr instanceof OrGroup);
		 if (gr instanceof AlternativeGroup) {
			 
			 AlternativeGroup xgr = (AlternativeGroup) gr ;
				
			 for (FeatureNode<String> source : sources) {
				
				 Feature ft = getFtByName(source.getFeature());
				 GroupHasChild gc = _fmprimitivesFactory.createGroupHasChild();
				 String groupChildID = "groupchild" + gcID++;
				 gc.setId(groupChildID);
				 gc.setName(groupChildID);
				 gc.setChild(ft);
				 gc.setConfigurationSource(ConfigurationSource.MODEL);
				 gc.setGroup(xgr);
				
				 
				 xgr.getGroupHasChild().add(gc);
					
			  	 ft.getGroupHasChild().add(gc);
			  	 
			  	 _rFM.getPrimitives().add(gc);
			  	 _rFM.getPrimitives().add(xgr);
				
				 
			 }
		
			GroupHasParent gparent = _fmprimitivesFactory.createGroupHasParent();
			gparent.setGroup((FeatureGroup) gr);
			gparent.setParent(parent);
			String gparentID = "gparent" + gParent++;
			gparent.setId(gparentID);
			gparent.setName(gparentID);
			gparent.setConfigurationSource(ConfigurationSource.MODEL); // let say
			 _rFM.getPrimitives().add(gparent);
			parent.getGroupHasParent().add(gparent);
			xgr.setGroupHasParent(gparent);
			
			GroupHasMin gmin = _fmprimitivesFactory.createGroupHasMin();
			gmin.setGroup(xgr);
			xgr.setGroupHasMin(gmin);	
			 _rFM.getPrimitives().add(gmin);

		
		 }
		 
		 else  if (gr instanceof OrGroup) {
			 
			 OrGroup ogr = (OrGroup) gr ;
				
			 for (FeatureNode<String> source : sources) {
				
				 Feature ft = getFtByName(source.getFeature());
				 GroupHasChild gc = _fmprimitivesFactory.createGroupHasChild();
				 String groupChildID = "groupchild" + gcID++;
				 gc.setId(groupChildID);
				 gc.setName(groupChildID);
				 gc.setChild(ft);
				 gc.setConfigurationSource(ConfigurationSource.MODEL);
				 gc.setGroup(ogr);
				
				 
				 ogr.getGroupHasChild().add(gc);
					
			  	 ft.getGroupHasChild().add(gc);
			  	 
			  	 _rFM.getPrimitives().add(gc);
			  	 _rFM.getPrimitives().add(ogr);
				
				 
			 }
		
			GroupHasParent gparent = _fmprimitivesFactory.createGroupHasParent();
			gparent.setGroup((FeatureGroup) gr);
			gparent.setParent(parent);
			String gparentID = "gparent" + gParent++;
			gparent.setId(gparentID);
			gparent.setName(gparentID);
			gparent.setConfigurationSource(ConfigurationSource.MODEL); // let say
			 _rFM.getPrimitives().add(gparent);
			parent.getGroupHasParent().add(gparent);
			ogr.setGroupHasParent(gparent);
			
			GroupHasMin gmin = _fmprimitivesFactory.createGroupHasMin();
			gmin.setGroup(ogr);
			ogr.setGroupHasMin(gmin);	
			 _rFM.getPrimitives().add(gmin);

		
		 }
		 
		
		String idGroup = "group" + gID++;
		gr.setId(idGroup);
		gr.setName(idGroup);
		gr.setConfigurationSource(ConfigurationSource.MODEL); // let say
		_rFM.getPrimitives().add(gr);
		
	}




	private Feature getFtByName(String rootName) {
		for (fr.inria.familiar.fmlero.fmprimitives.Feature ft : _rFM.getFeatures()) {
			if (ft.getName().equals(rootName)) 
				return ft ;
		}
		
		return null ;
	}


   

}


