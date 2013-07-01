/**
 */
package fr.inria.familiar.fmlero.fmprimitives.impl;

import fr.inria.familiar.fmlero.fmprimitives.Feature;
import fr.inria.familiar.fmlero.fmprimitives.FeatureHasMandatorySubfeature;
import fr.inria.familiar.fmlero.fmprimitives.FeatureHasSubfeature;
import fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Has Mandatory Subfeature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureHasMandatorySubfeatureImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureHasMandatorySubfeatureImpl#getSubfeature <em>Subfeature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureHasMandatorySubfeatureImpl extends FeatureModelPrimitiveImpl implements FeatureHasMandatorySubfeature
{
  /**
   * The cached value of the '{@link #getParent() <em>Parent</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParent()
   * @generated
   * @ordered
   */
  protected Feature parent;

  /**
   * The cached value of the '{@link #getSubfeature() <em>Subfeature</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSubfeature()
   * @generated
   * @ordered
   */
  protected Feature subfeature;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FeatureHasMandatorySubfeatureImpl()
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
    return FmprimitivesPackage.Literals.FEATURE_HAS_MANDATORY_SUBFEATURE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Feature getParent()
  {
    if (parent != null && parent.eIsProxy())
    {
      InternalEObject oldParent = (InternalEObject)parent;
      parent = (Feature)eResolveProxy(oldParent);
      if (parent != oldParent)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FmprimitivesPackage.FEATURE_HAS_MANDATORY_SUBFEATURE__PARENT, oldParent, parent));
      }
    }
    return parent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Feature basicGetParent()
  {
    return parent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParent(Feature newParent)
  {
    Feature oldParent = parent;
    parent = newParent;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FmprimitivesPackage.FEATURE_HAS_MANDATORY_SUBFEATURE__PARENT, oldParent, parent));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Feature getSubfeature()
  {
    if (subfeature != null && subfeature.eIsProxy())
    {
      InternalEObject oldSubfeature = (InternalEObject)subfeature;
      subfeature = (Feature)eResolveProxy(oldSubfeature);
      if (subfeature != oldSubfeature)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FmprimitivesPackage.FEATURE_HAS_MANDATORY_SUBFEATURE__SUBFEATURE, oldSubfeature, subfeature));
      }
    }
    return subfeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Feature basicGetSubfeature()
  {
    return subfeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSubfeature(Feature newSubfeature)
  {
    Feature oldSubfeature = subfeature;
    subfeature = newSubfeature;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FmprimitivesPackage.FEATURE_HAS_MANDATORY_SUBFEATURE__SUBFEATURE, oldSubfeature, subfeature));
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
      case FmprimitivesPackage.FEATURE_HAS_MANDATORY_SUBFEATURE__PARENT:
        if (resolve) return getParent();
        return basicGetParent();
      case FmprimitivesPackage.FEATURE_HAS_MANDATORY_SUBFEATURE__SUBFEATURE:
        if (resolve) return getSubfeature();
        return basicGetSubfeature();
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
      case FmprimitivesPackage.FEATURE_HAS_MANDATORY_SUBFEATURE__PARENT:
        setParent((Feature)newValue);
        return;
      case FmprimitivesPackage.FEATURE_HAS_MANDATORY_SUBFEATURE__SUBFEATURE:
        setSubfeature((Feature)newValue);
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
      case FmprimitivesPackage.FEATURE_HAS_MANDATORY_SUBFEATURE__PARENT:
        setParent((Feature)null);
        return;
      case FmprimitivesPackage.FEATURE_HAS_MANDATORY_SUBFEATURE__SUBFEATURE:
        setSubfeature((Feature)null);
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
      case FmprimitivesPackage.FEATURE_HAS_MANDATORY_SUBFEATURE__PARENT:
        return parent != null;
      case FmprimitivesPackage.FEATURE_HAS_MANDATORY_SUBFEATURE__SUBFEATURE:
        return subfeature != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
  {
    if (baseClass == FeatureHasSubfeature.class)
    {
      switch (derivedFeatureID)
      {
        case FmprimitivesPackage.FEATURE_HAS_MANDATORY_SUBFEATURE__PARENT: return FmprimitivesPackage.FEATURE_HAS_SUBFEATURE__PARENT;
        case FmprimitivesPackage.FEATURE_HAS_MANDATORY_SUBFEATURE__SUBFEATURE: return FmprimitivesPackage.FEATURE_HAS_SUBFEATURE__SUBFEATURE;
        default: return -1;
      }
    }
    return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
  {
    if (baseClass == FeatureHasSubfeature.class)
    {
      switch (baseFeatureID)
      {
        case FmprimitivesPackage.FEATURE_HAS_SUBFEATURE__PARENT: return FmprimitivesPackage.FEATURE_HAS_MANDATORY_SUBFEATURE__PARENT;
        case FmprimitivesPackage.FEATURE_HAS_SUBFEATURE__SUBFEATURE: return FmprimitivesPackage.FEATURE_HAS_MANDATORY_SUBFEATURE__SUBFEATURE;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }

} //FeatureHasMandatorySubfeatureImpl
