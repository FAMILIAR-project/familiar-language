/**
 */
package org.xtext.example.mydsl.fmprimitives.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.xtext.example.mydsl.fmprimitives.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage
 * @generated
 */
public class FmprimitivesSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static FmprimitivesPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FmprimitivesSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = FmprimitivesPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @parameter ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case FmprimitivesPackage.FEATURE_MODEL:
      {
        FeatureModel featureModel = (FeatureModel)theEObject;
        T result = caseFeatureModel(featureModel);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FmprimitivesPackage.FEATURE_MODEL_PRIMITIVE:
      {
        FeatureModelPrimitive featureModelPrimitive = (FeatureModelPrimitive)theEObject;
        T result = caseFeatureModelPrimitive(featureModelPrimitive);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FmprimitivesPackage.FEATURE_HAS_SUBFEATURE:
      {
        FeatureHasSubfeature featureHasSubfeature = (FeatureHasSubfeature)theEObject;
        T result = caseFeatureHasSubfeature(featureHasSubfeature);
        if (result == null) result = caseFeatureModelPrimitive(featureHasSubfeature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FmprimitivesPackage.UNDIRECTED_RELATIONSHIP:
      {
        UndirectedRelationship undirectedRelationship = (UndirectedRelationship)theEObject;
        T result = caseUndirectedRelationship(undirectedRelationship);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FmprimitivesPackage.DIRECTED_RELATIONSHIP:
      {
        DirectedRelationship directedRelationship = (DirectedRelationship)theEObject;
        T result = caseDirectedRelationship(directedRelationship);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FmprimitivesPackage.FEATURE_GROUP:
      {
        FeatureGroup featureGroup = (FeatureGroup)theEObject;
        T result = caseFeatureGroup(featureGroup);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FmprimitivesPackage.FEATURE:
      {
        Feature feature = (Feature)theEObject;
        T result = caseFeature(feature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FmprimitivesPackage.GROUP_HAS_PARENT:
      {
        GroupHasParent groupHasParent = (GroupHasParent)theEObject;
        T result = caseGroupHasParent(groupHasParent);
        if (result == null) result = caseFeatureModelPrimitive(groupHasParent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FmprimitivesPackage.GROUP_HAS_CHILD:
      {
        GroupHasChild groupHasChild = (GroupHasChild)theEObject;
        T result = caseGroupHasChild(groupHasChild);
        if (result == null) result = caseFeatureModelPrimitive(groupHasChild);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FmprimitivesPackage.SELECTED_FEATURE:
      {
        SelectedFeature selectedFeature = (SelectedFeature)theEObject;
        T result = caseSelectedFeature(selectedFeature);
        if (result == null) result = caseFeatureModelPrimitive(selectedFeature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FmprimitivesPackage.ELIMINATED_FEATURE:
      {
        EliminatedFeature eliminatedFeature = (EliminatedFeature)theEObject;
        T result = caseEliminatedFeature(eliminatedFeature);
        if (result == null) result = caseFeatureModelPrimitive(eliminatedFeature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FmprimitivesPackage.FEATURE_IS_ROOT:
      {
        FeatureIsRoot featureIsRoot = (FeatureIsRoot)theEObject;
        T result = caseFeatureIsRoot(featureIsRoot);
        if (result == null) result = caseFeatureModelPrimitive(featureIsRoot);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FmprimitivesPackage.EXPLANATION:
      {
        Explanation explanation = (Explanation)theEObject;
        T result = caseExplanation(explanation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FmprimitivesPackage.GROUP_HAS_MAX:
      {
        GroupHasMax groupHasMax = (GroupHasMax)theEObject;
        T result = caseGroupHasMax(groupHasMax);
        if (result == null) result = caseFeatureModelPrimitive(groupHasMax);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FmprimitivesPackage.GROUP_HAS_MIN:
      {
        GroupHasMin groupHasMin = (GroupHasMin)theEObject;
        T result = caseGroupHasMin(groupHasMin);
        if (result == null) result = caseFeatureModelPrimitive(groupHasMin);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FmprimitivesPackage.ALTERNATIVE_GROUP:
      {
        AlternativeGroup alternativeGroup = (AlternativeGroup)theEObject;
        T result = caseAlternativeGroup(alternativeGroup);
        if (result == null) result = caseFeatureModelPrimitive(alternativeGroup);
        if (result == null) result = caseFeatureGroup(alternativeGroup);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FmprimitivesPackage.OR_GROUP:
      {
        OrGroup orGroup = (OrGroup)theEObject;
        T result = caseOrGroup(orGroup);
        if (result == null) result = caseFeatureModelPrimitive(orGroup);
        if (result == null) result = caseFeatureGroup(orGroup);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FmprimitivesPackage.FEATURE_HAS_OPTIONAL_SUBFEATURE:
      {
        FeatureHasOptionalSubfeature featureHasOptionalSubfeature = (FeatureHasOptionalSubfeature)theEObject;
        T result = caseFeatureHasOptionalSubfeature(featureHasOptionalSubfeature);
        if (result == null) result = caseFeatureHasSubfeature(featureHasOptionalSubfeature);
        if (result == null) result = caseFeatureModelPrimitive(featureHasOptionalSubfeature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FmprimitivesPackage.FEATURE_HAS_MANDATORY_SUBFEATURE:
      {
        FeatureHasMandatorySubfeature featureHasMandatorySubfeature = (FeatureHasMandatorySubfeature)theEObject;
        T result = caseFeatureHasMandatorySubfeature(featureHasMandatorySubfeature);
        if (result == null) result = caseFeatureHasSubfeature(featureHasMandatorySubfeature);
        if (result == null) result = caseFeatureModelPrimitive(featureHasMandatorySubfeature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FmprimitivesPackage.MUTUAL_EXCLUSIVE:
      {
        MutualExclusive mutualExclusive = (MutualExclusive)theEObject;
        T result = caseMutualExclusive(mutualExclusive);
        if (result == null) result = caseFeatureModelPrimitive(mutualExclusive);
        if (result == null) result = caseUndirectedRelationship(mutualExclusive);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FmprimitivesPackage.CUSTOM_UNDIRECTED_RELATIONSHIP:
      {
        CustomUndirectedRelationship customUndirectedRelationship = (CustomUndirectedRelationship)theEObject;
        T result = caseCustomUndirectedRelationship(customUndirectedRelationship);
        if (result == null) result = caseFeatureModelPrimitive(customUndirectedRelationship);
        if (result == null) result = caseUndirectedRelationship(customUndirectedRelationship);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FmprimitivesPackage.REQUIRES:
      {
        Requires requires = (Requires)theEObject;
        T result = caseRequires(requires);
        if (result == null) result = caseFeatureModelPrimitive(requires);
        if (result == null) result = caseDirectedRelationship(requires);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FmprimitivesPackage.TEMPORAL_ORDERING_SEQUENTIAL:
      {
        TemporalOrderingSequential temporalOrderingSequential = (TemporalOrderingSequential)theEObject;
        T result = caseTemporalOrderingSequential(temporalOrderingSequential);
        if (result == null) result = caseFeatureModelPrimitive(temporalOrderingSequential);
        if (result == null) result = caseDirectedRelationship(temporalOrderingSequential);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FmprimitivesPackage.CUSTOM_DIRECTED_RELATIONSHIP:
      {
        CustomDirectedRelationship customDirectedRelationship = (CustomDirectedRelationship)theEObject;
        T result = caseCustomDirectedRelationship(customDirectedRelationship);
        if (result == null) result = caseFeatureModelPrimitive(customDirectedRelationship);
        if (result == null) result = caseDirectedRelationship(customDirectedRelationship);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FmprimitivesPackage.AUTO_COMPLETE:
      {
        AutoComplete autoComplete = (AutoComplete)theEObject;
        T result = caseAutoComplete(autoComplete);
        if (result == null) result = caseFeatureModelPrimitive(autoComplete);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Feature Model</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature Model</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFeatureModel(FeatureModel object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Feature Model Primitive</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature Model Primitive</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFeatureModelPrimitive(FeatureModelPrimitive object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Feature Has Subfeature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature Has Subfeature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFeatureHasSubfeature(FeatureHasSubfeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Undirected Relationship</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Undirected Relationship</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseUndirectedRelationship(UndirectedRelationship object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Directed Relationship</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Directed Relationship</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDirectedRelationship(DirectedRelationship object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Feature Group</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature Group</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFeatureGroup(FeatureGroup object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFeature(Feature object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Group Has Parent</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Group Has Parent</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGroupHasParent(GroupHasParent object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Group Has Child</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Group Has Child</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGroupHasChild(GroupHasChild object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Selected Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Selected Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSelectedFeature(SelectedFeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Eliminated Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Eliminated Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEliminatedFeature(EliminatedFeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Feature Is Root</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature Is Root</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFeatureIsRoot(FeatureIsRoot object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Explanation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Explanation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExplanation(Explanation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Group Has Max</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Group Has Max</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGroupHasMax(GroupHasMax object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Group Has Min</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Group Has Min</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGroupHasMin(GroupHasMin object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Alternative Group</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Alternative Group</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAlternativeGroup(AlternativeGroup object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Or Group</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Or Group</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOrGroup(OrGroup object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Feature Has Optional Subfeature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature Has Optional Subfeature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFeatureHasOptionalSubfeature(FeatureHasOptionalSubfeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Feature Has Mandatory Subfeature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature Has Mandatory Subfeature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFeatureHasMandatorySubfeature(FeatureHasMandatorySubfeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Mutual Exclusive</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Mutual Exclusive</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMutualExclusive(MutualExclusive object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Custom Undirected Relationship</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Custom Undirected Relationship</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCustomUndirectedRelationship(CustomUndirectedRelationship object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Requires</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Requires</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRequires(Requires object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Temporal Ordering Sequential</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Temporal Ordering Sequential</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTemporalOrderingSequential(TemporalOrderingSequential object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Custom Directed Relationship</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Custom Directed Relationship</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCustomDirectedRelationship(CustomDirectedRelationship object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Auto Complete</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Auto Complete</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAutoComplete(AutoComplete object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object)
  {
    return null;
  }

} //FmprimitivesSwitch
