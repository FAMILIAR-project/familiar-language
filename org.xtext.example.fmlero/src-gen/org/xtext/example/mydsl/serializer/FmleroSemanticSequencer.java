package org.xtext.example.mydsl.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.xtext.example.mydsl.fmprimitives.AlternativeGroup;
import org.xtext.example.mydsl.fmprimitives.AutoComplete;
import org.xtext.example.mydsl.fmprimitives.CustomDirectedRelationship;
import org.xtext.example.mydsl.fmprimitives.CustomUndirectedRelationship;
import org.xtext.example.mydsl.fmprimitives.EliminatedFeature;
import org.xtext.example.mydsl.fmprimitives.Explanation;
import org.xtext.example.mydsl.fmprimitives.Feature;
import org.xtext.example.mydsl.fmprimitives.FeatureHasMandatorySubfeature;
import org.xtext.example.mydsl.fmprimitives.FeatureHasOptionalSubfeature;
import org.xtext.example.mydsl.fmprimitives.FeatureHasSubfeature;
import org.xtext.example.mydsl.fmprimitives.FeatureIsRoot;
import org.xtext.example.mydsl.fmprimitives.FeatureModel;
import org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage;
import org.xtext.example.mydsl.fmprimitives.GroupHasChild;
import org.xtext.example.mydsl.fmprimitives.GroupHasMax;
import org.xtext.example.mydsl.fmprimitives.GroupHasMin;
import org.xtext.example.mydsl.fmprimitives.GroupHasParent;
import org.xtext.example.mydsl.fmprimitives.MutualExclusive;
import org.xtext.example.mydsl.fmprimitives.OrGroup;
import org.xtext.example.mydsl.fmprimitives.Requires;
import org.xtext.example.mydsl.fmprimitives.SelectedFeature;
import org.xtext.example.mydsl.fmprimitives.TemporalOrderingSequential;
import org.xtext.example.mydsl.services.FmleroGrammarAccess;

@SuppressWarnings("all")
public class FmleroSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private FmleroGrammarAccess grammarAccess;
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == FmprimitivesPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case FmprimitivesPackage.ALTERNATIVE_GROUP:
				if(context == grammarAccess.getAlternativeGroupRule() ||
				   context == grammarAccess.getFeatureGroupRule() ||
				   context == grammarAccess.getFeatureModelPrimitiveRule()) {
					sequence_AlternativeGroup(context, (AlternativeGroup) semanticObject); 
					return; 
				}
				else break;
			case FmprimitivesPackage.AUTO_COMPLETE:
				if(context == grammarAccess.getAutoCompleteRule() ||
				   context == grammarAccess.getFeatureModelPrimitiveRule()) {
					sequence_AutoComplete(context, (AutoComplete) semanticObject); 
					return; 
				}
				else break;
			case FmprimitivesPackage.CUSTOM_DIRECTED_RELATIONSHIP:
				if(context == grammarAccess.getCustomDirectedRelationshipRule() ||
				   context == grammarAccess.getDirectedRelationshipRule() ||
				   context == grammarAccess.getFeatureModelPrimitiveRule()) {
					sequence_CustomDirectedRelationship(context, (CustomDirectedRelationship) semanticObject); 
					return; 
				}
				else break;
			case FmprimitivesPackage.CUSTOM_UNDIRECTED_RELATIONSHIP:
				if(context == grammarAccess.getCustomUndirectedRelationshipRule() ||
				   context == grammarAccess.getFeatureModelPrimitiveRule() ||
				   context == grammarAccess.getUndirectedRelationshipRule()) {
					sequence_CustomUndirectedRelationship(context, (CustomUndirectedRelationship) semanticObject); 
					return; 
				}
				else break;
			case FmprimitivesPackage.ELIMINATED_FEATURE:
				if(context == grammarAccess.getEliminatedFeatureRule() ||
				   context == grammarAccess.getFeatureModelPrimitiveRule()) {
					sequence_EliminatedFeature(context, (EliminatedFeature) semanticObject); 
					return; 
				}
				else break;
			case FmprimitivesPackage.EXPLANATION:
				if(context == grammarAccess.getExplanationRule()) {
					sequence_Explanation(context, (Explanation) semanticObject); 
					return; 
				}
				else break;
			case FmprimitivesPackage.FEATURE:
				if(context == grammarAccess.getFeatureRule()) {
					sequence_Feature(context, (Feature) semanticObject); 
					return; 
				}
				else break;
			case FmprimitivesPackage.FEATURE_HAS_MANDATORY_SUBFEATURE:
				if(context == grammarAccess.getFeatureHasMandatorySubfeatureRule() ||
				   context == grammarAccess.getFeatureHasSubfeatureRule() ||
				   context == grammarAccess.getFeatureModelPrimitiveRule()) {
					sequence_FeatureHasMandatorySubfeature(context, (FeatureHasMandatorySubfeature) semanticObject); 
					return; 
				}
				else break;
			case FmprimitivesPackage.FEATURE_HAS_OPTIONAL_SUBFEATURE:
				if(context == grammarAccess.getFeatureHasOptionalSubfeatureRule() ||
				   context == grammarAccess.getFeatureHasSubfeatureRule() ||
				   context == grammarAccess.getFeatureModelPrimitiveRule()) {
					sequence_FeatureHasOptionalSubfeature(context, (FeatureHasOptionalSubfeature) semanticObject); 
					return; 
				}
				else break;
			case FmprimitivesPackage.FEATURE_HAS_SUBFEATURE:
				if(context == grammarAccess.getFeatureHasSubfeatureRule() ||
				   context == grammarAccess.getFeatureHasSubfeature_ImplRule() ||
				   context == grammarAccess.getFeatureModelPrimitiveRule()) {
					sequence_FeatureHasSubfeature_Impl(context, (FeatureHasSubfeature) semanticObject); 
					return; 
				}
				else break;
			case FmprimitivesPackage.FEATURE_IS_ROOT:
				if(context == grammarAccess.getFeatureIsRootRule() ||
				   context == grammarAccess.getFeatureModelPrimitiveRule()) {
					sequence_FeatureIsRoot(context, (FeatureIsRoot) semanticObject); 
					return; 
				}
				else break;
			case FmprimitivesPackage.FEATURE_MODEL:
				if(context == grammarAccess.getFeatureModelRule()) {
					sequence_FeatureModel(context, (FeatureModel) semanticObject); 
					return; 
				}
				else break;
			case FmprimitivesPackage.GROUP_HAS_CHILD:
				if(context == grammarAccess.getFeatureModelPrimitiveRule() ||
				   context == grammarAccess.getGroupHasChildRule()) {
					sequence_GroupHasChild(context, (GroupHasChild) semanticObject); 
					return; 
				}
				else break;
			case FmprimitivesPackage.GROUP_HAS_MAX:
				if(context == grammarAccess.getFeatureModelPrimitiveRule() ||
				   context == grammarAccess.getGroupHasMaxRule()) {
					sequence_GroupHasMax(context, (GroupHasMax) semanticObject); 
					return; 
				}
				else break;
			case FmprimitivesPackage.GROUP_HAS_MIN:
				if(context == grammarAccess.getFeatureModelPrimitiveRule() ||
				   context == grammarAccess.getGroupHasMinRule()) {
					sequence_GroupHasMin(context, (GroupHasMin) semanticObject); 
					return; 
				}
				else break;
			case FmprimitivesPackage.GROUP_HAS_PARENT:
				if(context == grammarAccess.getFeatureModelPrimitiveRule() ||
				   context == grammarAccess.getGroupHasParentRule()) {
					sequence_GroupHasParent(context, (GroupHasParent) semanticObject); 
					return; 
				}
				else break;
			case FmprimitivesPackage.MUTUAL_EXCLUSIVE:
				if(context == grammarAccess.getFeatureModelPrimitiveRule() ||
				   context == grammarAccess.getMutualExclusiveRule() ||
				   context == grammarAccess.getUndirectedRelationshipRule()) {
					sequence_MutualExclusive(context, (MutualExclusive) semanticObject); 
					return; 
				}
				else break;
			case FmprimitivesPackage.OR_GROUP:
				if(context == grammarAccess.getFeatureGroupRule() ||
				   context == grammarAccess.getFeatureModelPrimitiveRule() ||
				   context == grammarAccess.getOrGroupRule()) {
					sequence_OrGroup(context, (OrGroup) semanticObject); 
					return; 
				}
				else break;
			case FmprimitivesPackage.REQUIRES:
				if(context == grammarAccess.getDirectedRelationshipRule() ||
				   context == grammarAccess.getFeatureModelPrimitiveRule() ||
				   context == grammarAccess.getRequiresRule()) {
					sequence_Requires(context, (Requires) semanticObject); 
					return; 
				}
				else break;
			case FmprimitivesPackage.SELECTED_FEATURE:
				if(context == grammarAccess.getFeatureModelPrimitiveRule() ||
				   context == grammarAccess.getSelectedFeatureRule()) {
					sequence_SelectedFeature(context, (SelectedFeature) semanticObject); 
					return; 
				}
				else break;
			case FmprimitivesPackage.TEMPORAL_ORDERING_SEQUENTIAL:
				if(context == grammarAccess.getDirectedRelationshipRule() ||
				   context == grammarAccess.getFeatureModelPrimitiveRule() ||
				   context == grammarAccess.getTemporalOrderingSequentialRule()) {
					sequence_TemporalOrderingSequential(context, (TemporalOrderingSequential) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (
	 *         id=EString 
	 *         name=EString 
	 *         configurationSource=ConfigurationSource 
	 *         (explanations+=[Explanation|EString] explanations+=[Explanation|EString]*)? 
	 *         groupHasParent=[GroupHasParent|EString] 
	 *         (groupHasChild+=[GroupHasChild|EString] groupHasChild+=[GroupHasChild|EString]*)? 
	 *         groupHasMax=[GroupHasMax|EString]? 
	 *         groupHasMin=[GroupHasMin|EString]?
	 *     )
	 */
	protected void sequence_AlternativeGroup(EObject context, AlternativeGroup semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (id=EString name=EString configurationSource=ConfigurationSource (explanations+=[Explanation|EString] explanations+=[Explanation|EString]*)?)
	 */
	protected void sequence_AutoComplete(EObject context, AutoComplete semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=EString 
	 *         name=EString 
	 *         configurationSource=ConfigurationSource 
	 *         stereotype=EString 
	 *         (explanations+=[Explanation|EString] explanations+=[Explanation|EString]*)? 
	 *         (sources+=[Feature|EString] sources+=[Feature|EString]*)? 
	 *         (targets+=[Feature|EString] targets+=[Feature|EString]*)?
	 *     )
	 */
	protected void sequence_CustomDirectedRelationship(EObject context, CustomDirectedRelationship semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=EString 
	 *         name=EString 
	 *         configurationSource=ConfigurationSource 
	 *         stereotype=EString 
	 *         (explanations+=[Explanation|EString] explanations+=[Explanation|EString]*)? 
	 *         (relatedFeatures+=[Feature|EString] relatedFeatures+=[Feature|EString]*)?
	 *     )
	 */
	protected void sequence_CustomUndirectedRelationship(EObject context, CustomUndirectedRelationship semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=EString 
	 *         name=EString 
	 *         configurationSource=ConfigurationSource 
	 *         (explanations+=[Explanation|EString] explanations+=[Explanation|EString]*)? 
	 *         feature=[Feature|EString]
	 *     )
	 */
	protected void sequence_EliminatedFeature(EObject context, EliminatedFeature semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((primitives+=[FeatureModelPrimitive|EString] primitives+=[FeatureModelPrimitive|EString]*)?)
	 */
	protected void sequence_Explanation(EObject context, Explanation semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=EString 
	 *         name=EString 
	 *         configurationSource=ConfigurationSource 
	 *         (explanations+=[Explanation|EString] explanations+=[Explanation|EString]*)? 
	 *         parent=[Feature|EString] 
	 *         subfeature=[Feature|EString]
	 *     )
	 */
	protected void sequence_FeatureHasMandatorySubfeature(EObject context, FeatureHasMandatorySubfeature semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=EString 
	 *         name=EString 
	 *         configurationSource=ConfigurationSource 
	 *         (explanations+=[Explanation|EString] explanations+=[Explanation|EString]*)? 
	 *         parent=[Feature|EString] 
	 *         subfeature=[Feature|EString]
	 *     )
	 */
	protected void sequence_FeatureHasOptionalSubfeature(EObject context, FeatureHasOptionalSubfeature semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=EString 
	 *         name=EString 
	 *         configurationSource=ConfigurationSource 
	 *         (explanations+=[Explanation|EString] explanations+=[Explanation|EString]*)? 
	 *         parent=[Feature|EString] 
	 *         subfeature=[Feature|EString]
	 *     )
	 */
	protected void sequence_FeatureHasSubfeature_Impl(EObject context, FeatureHasSubfeature semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=EString 
	 *         name=EString 
	 *         configurationSource=ConfigurationSource 
	 *         (explanations+=[Explanation|EString] explanations+=[Explanation|EString]*)? 
	 *         feature=[Feature|EString]
	 *     )
	 */
	protected void sequence_FeatureIsRoot(EObject context, FeatureIsRoot semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (id=EString name=EString (features+=Feature features+=Feature*)? (primitives+=FeatureModelPrimitive primitives+=FeatureModelPrimitive*)?)
	 */
	protected void sequence_FeatureModel(EObject context, FeatureModel semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=EString 
	 *         name=EString 
	 *         (groupHasParent+=[GroupHasParent|EString] groupHasParent+=[GroupHasParent|EString]*)? 
	 *         (groupHasChild+=[GroupHasChild|EString] groupHasChild+=[GroupHasChild|EString]*)? 
	 *         featureHasParent=[FeatureHasSubfeature|EString]? 
	 *         (featureHasSubfeature+=[FeatureHasSubfeature|EString] featureHasSubfeature+=[FeatureHasSubfeature|EString]*)? 
	 *         (selectedFeature+=[SelectedFeature|EString] selectedFeature+=[SelectedFeature|EString]*)? 
	 *         (eliminatedFeature+=[EliminatedFeature|EString] eliminatedFeature+=[EliminatedFeature|EString]*)? 
	 *         (undirectedRelationships+=[UndirectedRelationship|EString] undirectedRelationships+=[UndirectedRelationship|EString]*)? 
	 *         (incomingDirectedRelationships+=[DirectedRelationship|EString] incomingDirectedRelationships+=[DirectedRelationship|EString]*)? 
	 *         (outgoingDirectedRelationships+=[DirectedRelationship|EString] outgoingDirectedRelationships+=[DirectedRelationship|EString]*)? 
	 *         featureIsRoot=[FeatureIsRoot|EString]?
	 *     )
	 */
	protected void sequence_Feature(EObject context, Feature semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=EString 
	 *         name=EString 
	 *         configurationSource=ConfigurationSource 
	 *         (explanations+=[Explanation|EString] explanations+=[Explanation|EString]*)? 
	 *         child=[Feature|EString] 
	 *         group=[FeatureGroup|EString]
	 *     )
	 */
	protected void sequence_GroupHasChild(EObject context, GroupHasChild semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=EString 
	 *         name=EString 
	 *         configurationSource=ConfigurationSource 
	 *         max=EInt 
	 *         (explanations+=[Explanation|EString] explanations+=[Explanation|EString]*)? 
	 *         group=[FeatureGroup|EString]
	 *     )
	 */
	protected void sequence_GroupHasMax(EObject context, GroupHasMax semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=EString 
	 *         name=EString 
	 *         configurationSource=ConfigurationSource 
	 *         min=EInt 
	 *         (explanations+=[Explanation|EString] explanations+=[Explanation|EString]*)? 
	 *         group=[FeatureGroup|EString]
	 *     )
	 */
	protected void sequence_GroupHasMin(EObject context, GroupHasMin semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=EString 
	 *         name=EString 
	 *         configurationSource=ConfigurationSource 
	 *         (explanations+=[Explanation|EString] explanations+=[Explanation|EString]*)? 
	 *         parent=[Feature|EString] 
	 *         group=[FeatureGroup|EString]
	 *     )
	 */
	protected void sequence_GroupHasParent(EObject context, GroupHasParent semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=EString 
	 *         name=EString 
	 *         configurationSource=ConfigurationSource 
	 *         (explanations+=[Explanation|EString] explanations+=[Explanation|EString]*)? 
	 *         (relatedFeatures+=[Feature|EString] relatedFeatures+=[Feature|EString]*)?
	 *     )
	 */
	protected void sequence_MutualExclusive(EObject context, MutualExclusive semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=EString 
	 *         name=EString 
	 *         configurationSource=ConfigurationSource 
	 *         (explanations+=[Explanation|EString] explanations+=[Explanation|EString]*)? 
	 *         groupHasParent=[GroupHasParent|EString] 
	 *         (groupHasChild+=[GroupHasChild|EString] groupHasChild+=[GroupHasChild|EString]*)? 
	 *         groupHasMax=[GroupHasMax|EString]? 
	 *         groupHasMin=[GroupHasMin|EString]?
	 *     )
	 */
	protected void sequence_OrGroup(EObject context, OrGroup semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=EString 
	 *         name=EString 
	 *         configurationSource=ConfigurationSource 
	 *         (explanations+=[Explanation|EString] explanations+=[Explanation|EString]*)? 
	 *         (sources+=[Feature|EString] sources+=[Feature|EString]*)? 
	 *         (targets+=[Feature|EString] targets+=[Feature|EString]*)?
	 *     )
	 */
	protected void sequence_Requires(EObject context, Requires semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=EString 
	 *         name=EString 
	 *         configurationSource=ConfigurationSource 
	 *         (explanations+=[Explanation|EString] explanations+=[Explanation|EString]*)? 
	 *         feature=[Feature|EString]
	 *     )
	 */
	protected void sequence_SelectedFeature(EObject context, SelectedFeature semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=EString 
	 *         name=EString 
	 *         configurationSource=ConfigurationSource 
	 *         (explanations+=[Explanation|EString] explanations+=[Explanation|EString]*)? 
	 *         (sources+=[Feature|EString] sources+=[Feature|EString]*)? 
	 *         (targets+=[Feature|EString] targets+=[Feature|EString]*)?
	 *     )
	 */
	protected void sequence_TemporalOrderingSequential(EObject context, TemporalOrderingSequential semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
