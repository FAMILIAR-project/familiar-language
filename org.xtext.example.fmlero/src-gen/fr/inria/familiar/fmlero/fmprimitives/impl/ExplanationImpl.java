/**
 */
package fr.inria.familiar.fmlero.fmprimitives.impl;

import fr.inria.familiar.fmlero.fmprimitives.Explanation;
import fr.inria.familiar.fmlero.fmprimitives.FeatureModelPrimitive;
import fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Explanation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.ExplanationImpl#getPrimitives <em>Primitives</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExplanationImpl extends MinimalEObjectImpl.Container implements Explanation
{
  /**
   * The cached value of the '{@link #getPrimitives() <em>Primitives</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrimitives()
   * @generated
   * @ordered
   */
  protected EList<FeatureModelPrimitive> primitives;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ExplanationImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return FmprimitivesPackage.Literals.EXPLANATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<FeatureModelPrimitive> getPrimitives()
  {
    if (primitives == null)
    {
      primitives = new EObjectResolvingEList<FeatureModelPrimitive>(FeatureModelPrimitive.class, this, FmprimitivesPackage.EXPLANATION__PRIMITIVES);
    }
    return primitives;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case FmprimitivesPackage.EXPLANATION__PRIMITIVES:
        return getPrimitives();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case FmprimitivesPackage.EXPLANATION__PRIMITIVES:
        getPrimitives().clear();
        getPrimitives().addAll((Collection<? extends FeatureModelPrimitive>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case FmprimitivesPackage.EXPLANATION__PRIMITIVES:
        getPrimitives().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case FmprimitivesPackage.EXPLANATION__PRIMITIVES:
        return primitives != null && !primitives.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //ExplanationImpl
