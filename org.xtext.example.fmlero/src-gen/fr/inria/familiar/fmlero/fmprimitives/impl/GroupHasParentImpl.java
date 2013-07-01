/**
 */
package fr.inria.familiar.fmlero.fmprimitives.impl;

import fr.inria.familiar.fmlero.fmprimitives.Feature;
import fr.inria.familiar.fmlero.fmprimitives.FeatureGroup;
import fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage;
import fr.inria.familiar.fmlero.fmprimitives.GroupHasParent;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Group Has Parent</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.GroupHasParentImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.GroupHasParentImpl#getGroup <em>Group</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GroupHasParentImpl extends FeatureModelPrimitiveImpl implements GroupHasParent
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
   * The cached value of the '{@link #getGroup() <em>Group</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGroup()
   * @generated
   * @ordered
   */
  protected FeatureGroup group;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected GroupHasParentImpl()
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
    return FmprimitivesPackage.Literals.GROUP_HAS_PARENT;
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
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FmprimitivesPackage.GROUP_HAS_PARENT__PARENT, oldParent, parent));
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
      eNotify(new ENotificationImpl(this, Notification.SET, FmprimitivesPackage.GROUP_HAS_PARENT__PARENT, oldParent, parent));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureGroup getGroup()
  {
    if (group != null && group.eIsProxy())
    {
      InternalEObject oldGroup = (InternalEObject)group;
      group = (FeatureGroup)eResolveProxy(oldGroup);
      if (group != oldGroup)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FmprimitivesPackage.GROUP_HAS_PARENT__GROUP, oldGroup, group));
      }
    }
    return group;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureGroup basicGetGroup()
  {
    return group;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGroup(FeatureGroup newGroup)
  {
    FeatureGroup oldGroup = group;
    group = newGroup;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FmprimitivesPackage.GROUP_HAS_PARENT__GROUP, oldGroup, group));
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
      case FmprimitivesPackage.GROUP_HAS_PARENT__PARENT:
        if (resolve) return getParent();
        return basicGetParent();
      case FmprimitivesPackage.GROUP_HAS_PARENT__GROUP:
        if (resolve) return getGroup();
        return basicGetGroup();
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
      case FmprimitivesPackage.GROUP_HAS_PARENT__PARENT:
        setParent((Feature)newValue);
        return;
      case FmprimitivesPackage.GROUP_HAS_PARENT__GROUP:
        setGroup((FeatureGroup)newValue);
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
      case FmprimitivesPackage.GROUP_HAS_PARENT__PARENT:
        setParent((Feature)null);
        return;
      case FmprimitivesPackage.GROUP_HAS_PARENT__GROUP:
        setGroup((FeatureGroup)null);
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
      case FmprimitivesPackage.GROUP_HAS_PARENT__PARENT:
        return parent != null;
      case FmprimitivesPackage.GROUP_HAS_PARENT__GROUP:
        return group != null;
    }
    return super.eIsSet(featureID);
  }

} //GroupHasParentImpl
