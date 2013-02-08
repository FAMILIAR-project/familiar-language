/**
 */
package org.xtext.example.mydsl.fmprimitives.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.xtext.example.mydsl.fmprimitives.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage
 * @generated
 */
public class FmprimitivesAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static FmprimitivesPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FmprimitivesAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = FmprimitivesPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FmprimitivesSwitch<Adapter> modelSwitch =
    new FmprimitivesSwitch<Adapter>()
    {
      @Override
      public Adapter caseFeatureModel(FeatureModel object)
      {
        return createFeatureModelAdapter();
      }
      @Override
      public Adapter caseFeatureModelPrimitive(FeatureModelPrimitive object)
      {
        return createFeatureModelPrimitiveAdapter();
      }
      @Override
      public Adapter caseFeatureHasSubfeature(FeatureHasSubfeature object)
      {
        return createFeatureHasSubfeatureAdapter();
      }
      @Override
      public Adapter caseUndirectedRelationship(UndirectedRelationship object)
      {
        return createUndirectedRelationshipAdapter();
      }
      @Override
      public Adapter caseDirectedRelationship(DirectedRelationship object)
      {
        return createDirectedRelationshipAdapter();
      }
      @Override
      public Adapter caseFeatureGroup(FeatureGroup object)
      {
        return createFeatureGroupAdapter();
      }
      @Override
      public Adapter caseFeature(Feature object)
      {
        return createFeatureAdapter();
      }
      @Override
      public Adapter caseGroupHasParent(GroupHasParent object)
      {
        return createGroupHasParentAdapter();
      }
      @Override
      public Adapter caseGroupHasChild(GroupHasChild object)
      {
        return createGroupHasChildAdapter();
      }
      @Override
      public Adapter caseSelectedFeature(SelectedFeature object)
      {
        return createSelectedFeatureAdapter();
      }
      @Override
      public Adapter caseEliminatedFeature(EliminatedFeature object)
      {
        return createEliminatedFeatureAdapter();
      }
      @Override
      public Adapter caseFeatureIsRoot(FeatureIsRoot object)
      {
        return createFeatureIsRootAdapter();
      }
      @Override
      public Adapter caseExplanation(Explanation object)
      {
        return createExplanationAdapter();
      }
      @Override
      public Adapter caseGroupHasMax(GroupHasMax object)
      {
        return createGroupHasMaxAdapter();
      }
      @Override
      public Adapter caseGroupHasMin(GroupHasMin object)
      {
        return createGroupHasMinAdapter();
      }
      @Override
      public Adapter caseAlternativeGroup(AlternativeGroup object)
      {
        return createAlternativeGroupAdapter();
      }
      @Override
      public Adapter caseOrGroup(OrGroup object)
      {
        return createOrGroupAdapter();
      }
      @Override
      public Adapter caseFeatureHasOptionalSubfeature(FeatureHasOptionalSubfeature object)
      {
        return createFeatureHasOptionalSubfeatureAdapter();
      }
      @Override
      public Adapter caseFeatureHasMandatorySubfeature(FeatureHasMandatorySubfeature object)
      {
        return createFeatureHasMandatorySubfeatureAdapter();
      }
      @Override
      public Adapter caseMutualExclusive(MutualExclusive object)
      {
        return createMutualExclusiveAdapter();
      }
      @Override
      public Adapter caseCustomUndirectedRelationship(CustomUndirectedRelationship object)
      {
        return createCustomUndirectedRelationshipAdapter();
      }
      @Override
      public Adapter caseRequires(Requires object)
      {
        return createRequiresAdapter();
      }
      @Override
      public Adapter caseTemporalOrderingSequential(TemporalOrderingSequential object)
      {
        return createTemporalOrderingSequentialAdapter();
      }
      @Override
      public Adapter caseCustomDirectedRelationship(CustomDirectedRelationship object)
      {
        return createCustomDirectedRelationshipAdapter();
      }
      @Override
      public Adapter caseAutoComplete(AutoComplete object)
      {
        return createAutoCompleteAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.FeatureModel <em>Feature Model</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.FeatureModel
   * @generated
   */
  public Adapter createFeatureModelAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.FeatureModelPrimitive <em>Feature Model Primitive</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.FeatureModelPrimitive
   * @generated
   */
  public Adapter createFeatureModelPrimitiveAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.FeatureHasSubfeature <em>Feature Has Subfeature</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.FeatureHasSubfeature
   * @generated
   */
  public Adapter createFeatureHasSubfeatureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.UndirectedRelationship <em>Undirected Relationship</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.UndirectedRelationship
   * @generated
   */
  public Adapter createUndirectedRelationshipAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.DirectedRelationship <em>Directed Relationship</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.DirectedRelationship
   * @generated
   */
  public Adapter createDirectedRelationshipAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.FeatureGroup <em>Feature Group</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.FeatureGroup
   * @generated
   */
  public Adapter createFeatureGroupAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.Feature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.Feature
   * @generated
   */
  public Adapter createFeatureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.GroupHasParent <em>Group Has Parent</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.GroupHasParent
   * @generated
   */
  public Adapter createGroupHasParentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.GroupHasChild <em>Group Has Child</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.GroupHasChild
   * @generated
   */
  public Adapter createGroupHasChildAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.SelectedFeature <em>Selected Feature</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.SelectedFeature
   * @generated
   */
  public Adapter createSelectedFeatureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.EliminatedFeature <em>Eliminated Feature</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.EliminatedFeature
   * @generated
   */
  public Adapter createEliminatedFeatureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.FeatureIsRoot <em>Feature Is Root</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.FeatureIsRoot
   * @generated
   */
  public Adapter createFeatureIsRootAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.Explanation <em>Explanation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.Explanation
   * @generated
   */
  public Adapter createExplanationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.GroupHasMax <em>Group Has Max</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.GroupHasMax
   * @generated
   */
  public Adapter createGroupHasMaxAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.GroupHasMin <em>Group Has Min</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.GroupHasMin
   * @generated
   */
  public Adapter createGroupHasMinAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.AlternativeGroup <em>Alternative Group</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.AlternativeGroup
   * @generated
   */
  public Adapter createAlternativeGroupAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.OrGroup <em>Or Group</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.OrGroup
   * @generated
   */
  public Adapter createOrGroupAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.FeatureHasOptionalSubfeature <em>Feature Has Optional Subfeature</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.FeatureHasOptionalSubfeature
   * @generated
   */
  public Adapter createFeatureHasOptionalSubfeatureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.FeatureHasMandatorySubfeature <em>Feature Has Mandatory Subfeature</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.FeatureHasMandatorySubfeature
   * @generated
   */
  public Adapter createFeatureHasMandatorySubfeatureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.MutualExclusive <em>Mutual Exclusive</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.MutualExclusive
   * @generated
   */
  public Adapter createMutualExclusiveAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.CustomUndirectedRelationship <em>Custom Undirected Relationship</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.CustomUndirectedRelationship
   * @generated
   */
  public Adapter createCustomUndirectedRelationshipAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.Requires <em>Requires</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.Requires
   * @generated
   */
  public Adapter createRequiresAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.TemporalOrderingSequential <em>Temporal Ordering Sequential</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.TemporalOrderingSequential
   * @generated
   */
  public Adapter createTemporalOrderingSequentialAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.CustomDirectedRelationship <em>Custom Directed Relationship</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.CustomDirectedRelationship
   * @generated
   */
  public Adapter createCustomDirectedRelationshipAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fmprimitives.AutoComplete <em>Auto Complete</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fmprimitives.AutoComplete
   * @generated
   */
  public Adapter createAutoCompleteAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //FmprimitivesAdapterFactory
