/**
 */
package fr.inria.familiar.fmlero.fmprimitives.impl;

import fr.inria.familiar.fmlero.fmprimitives.Feature;
import fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage;
import fr.inria.familiar.fmlero.fmprimitives.MutualExclusive;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mutual Exclusive</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.MutualExclusiveImpl#getRelatedFeatures <em>Related Features</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MutualExclusiveImpl extends FeatureModelPrimitiveImpl implements MutualExclusive
{
  /**
   * The cached value of the '{@link #getRelatedFeatures() <em>Related Features</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRelatedFeatures()
   * @generated
   * @ordered
   */
  protected EList<Feature> relatedFeatures;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MutualExclusiveImpl()
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
    return FmprimitivesPackage.Literals.MUTUAL_EXCLUSIVE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Feature> getRelatedFeatures()
  {
    if (relatedFeatures == null)
    {
      relatedFeatures = new EObjectResolvingEList<Feature>(Feature.class, this, FmprimitivesPackage.MUTUAL_EXCLUSIVE__RELATED_FEATURES);
    }
    return relatedFeatures;
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
      case FmprimitivesPackage.MUTUAL_EXCLUSIVE__RELATED_FEATURES:
        return getRelatedFeatures();
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
      case FmprimitivesPackage.MUTUAL_EXCLUSIVE__RELATED_FEATURES:
        getRelatedFeatures().clear();
        getRelatedFeatures().addAll((Collection<? extends Feature>)newValue);
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
      case FmprimitivesPackage.MUTUAL_EXCLUSIVE__RELATED_FEATURES:
        getRelatedFeatures().clear();
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
      case FmprimitivesPackage.MUTUAL_EXCLUSIVE__RELATED_FEATURES:
        return relatedFeatures != null && !relatedFeatures.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //MutualExclusiveImpl
