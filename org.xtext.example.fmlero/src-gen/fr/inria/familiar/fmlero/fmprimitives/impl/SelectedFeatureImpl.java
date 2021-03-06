/**
 */
package fr.inria.familiar.fmlero.fmprimitives.impl;

import fr.inria.familiar.fmlero.fmprimitives.Feature;
import fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage;
import fr.inria.familiar.fmlero.fmprimitives.SelectedFeature;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Selected Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.SelectedFeatureImpl#getFeature <em>Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SelectedFeatureImpl extends FeatureModelPrimitiveImpl implements SelectedFeature
{
  /**
   * The cached value of the '{@link #getFeature() <em>Feature</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeature()
   * @generated
   * @ordered
   */
  protected Feature feature;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SelectedFeatureImpl()
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
    return FmprimitivesPackage.Literals.SELECTED_FEATURE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Feature getFeature()
  {
    if (feature != null && feature.eIsProxy())
    {
      InternalEObject oldFeature = (InternalEObject)feature;
      feature = (Feature)eResolveProxy(oldFeature);
      if (feature != oldFeature)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FmprimitivesPackage.SELECTED_FEATURE__FEATURE, oldFeature, feature));
      }
    }
    return feature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Feature basicGetFeature()
  {
    return feature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFeature(Feature newFeature)
  {
    Feature oldFeature = feature;
    feature = newFeature;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FmprimitivesPackage.SELECTED_FEATURE__FEATURE, oldFeature, feature));
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
      case FmprimitivesPackage.SELECTED_FEATURE__FEATURE:
        if (resolve) return getFeature();
        return basicGetFeature();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case FmprimitivesPackage.SELECTED_FEATURE__FEATURE:
        setFeature((Feature)newValue);
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
      case FmprimitivesPackage.SELECTED_FEATURE__FEATURE:
        setFeature((Feature)null);
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
      case FmprimitivesPackage.SELECTED_FEATURE__FEATURE:
        return feature != null;
    }
    return super.eIsSet(featureID);
  }

} //SelectedFeatureImpl
