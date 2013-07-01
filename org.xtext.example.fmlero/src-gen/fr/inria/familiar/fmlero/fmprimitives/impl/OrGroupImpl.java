/**
 */
package fr.inria.familiar.fmlero.fmprimitives.impl;

import fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage;
import fr.inria.familiar.fmlero.fmprimitives.GroupHasChild;
import fr.inria.familiar.fmlero.fmprimitives.GroupHasMax;
import fr.inria.familiar.fmlero.fmprimitives.GroupHasMin;
import fr.inria.familiar.fmlero.fmprimitives.GroupHasParent;
import fr.inria.familiar.fmlero.fmprimitives.OrGroup;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Or Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.OrGroupImpl#getGroupHasParent <em>Group Has Parent</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.OrGroupImpl#getGroupHasChild <em>Group Has Child</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.OrGroupImpl#getGroupHasMax <em>Group Has Max</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.OrGroupImpl#getGroupHasMin <em>Group Has Min</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OrGroupImpl extends FeatureModelPrimitiveImpl implements OrGroup
{
  /**
   * The cached value of the '{@link #getGroupHasParent() <em>Group Has Parent</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGroupHasParent()
   * @generated
   * @ordered
   */
  protected GroupHasParent groupHasParent;

  /**
   * The cached value of the '{@link #getGroupHasChild() <em>Group Has Child</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGroupHasChild()
   * @generated
   * @ordered
   */
  protected EList<GroupHasChild> groupHasChild;

  /**
   * The cached value of the '{@link #getGroupHasMax() <em>Group Has Max</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGroupHasMax()
   * @generated
   * @ordered
   */
  protected GroupHasMax groupHasMax;

  /**
   * The cached value of the '{@link #getGroupHasMin() <em>Group Has Min</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGroupHasMin()
   * @generated
   * @ordered
   */
  protected GroupHasMin groupHasMin;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected OrGroupImpl()
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
    return FmprimitivesPackage.Literals.OR_GROUP;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GroupHasParent getGroupHasParent()
  {
    if (groupHasParent != null && groupHasParent.eIsProxy())
    {
      InternalEObject oldGroupHasParent = (InternalEObject)groupHasParent;
      groupHasParent = (GroupHasParent)eResolveProxy(oldGroupHasParent);
      if (groupHasParent != oldGroupHasParent)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FmprimitivesPackage.OR_GROUP__GROUP_HAS_PARENT, oldGroupHasParent, groupHasParent));
      }
    }
    return groupHasParent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GroupHasParent basicGetGroupHasParent()
  {
    return groupHasParent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGroupHasParent(GroupHasParent newGroupHasParent)
  {
    GroupHasParent oldGroupHasParent = groupHasParent;
    groupHasParent = newGroupHasParent;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FmprimitivesPackage.OR_GROUP__GROUP_HAS_PARENT, oldGroupHasParent, groupHasParent));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<GroupHasChild> getGroupHasChild()
  {
    if (groupHasChild == null)
    {
      groupHasChild = new EObjectResolvingEList<GroupHasChild>(GroupHasChild.class, this, FmprimitivesPackage.OR_GROUP__GROUP_HAS_CHILD);
    }
    return groupHasChild;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GroupHasMax getGroupHasMax()
  {
    if (groupHasMax != null && groupHasMax.eIsProxy())
    {
      InternalEObject oldGroupHasMax = (InternalEObject)groupHasMax;
      groupHasMax = (GroupHasMax)eResolveProxy(oldGroupHasMax);
      if (groupHasMax != oldGroupHasMax)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FmprimitivesPackage.OR_GROUP__GROUP_HAS_MAX, oldGroupHasMax, groupHasMax));
      }
    }
    return groupHasMax;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GroupHasMax basicGetGroupHasMax()
  {
    return groupHasMax;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGroupHasMax(GroupHasMax newGroupHasMax)
  {
    GroupHasMax oldGroupHasMax = groupHasMax;
    groupHasMax = newGroupHasMax;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FmprimitivesPackage.OR_GROUP__GROUP_HAS_MAX, oldGroupHasMax, groupHasMax));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GroupHasMin getGroupHasMin()
  {
    if (groupHasMin != null && groupHasMin.eIsProxy())
    {
      InternalEObject oldGroupHasMin = (InternalEObject)groupHasMin;
      groupHasMin = (GroupHasMin)eResolveProxy(oldGroupHasMin);
      if (groupHasMin != oldGroupHasMin)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FmprimitivesPackage.OR_GROUP__GROUP_HAS_MIN, oldGroupHasMin, groupHasMin));
      }
    }
    return groupHasMin;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GroupHasMin basicGetGroupHasMin()
  {
    return groupHasMin;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGroupHasMin(GroupHasMin newGroupHasMin)
  {
    GroupHasMin oldGroupHasMin = groupHasMin;
    groupHasMin = newGroupHasMin;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FmprimitivesPackage.OR_GROUP__GROUP_HAS_MIN, oldGroupHasMin, groupHasMin));
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
      case FmprimitivesPackage.OR_GROUP__GROUP_HAS_PARENT:
        if (resolve) return getGroupHasParent();
        return basicGetGroupHasParent();
      case FmprimitivesPackage.OR_GROUP__GROUP_HAS_CHILD:
        return getGroupHasChild();
      case FmprimitivesPackage.OR_GROUP__GROUP_HAS_MAX:
        if (resolve) return getGroupHasMax();
        return basicGetGroupHasMax();
      case FmprimitivesPackage.OR_GROUP__GROUP_HAS_MIN:
        if (resolve) return getGroupHasMin();
        return basicGetGroupHasMin();
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
      case FmprimitivesPackage.OR_GROUP__GROUP_HAS_PARENT:
        setGroupHasParent((GroupHasParent)newValue);
        return;
      case FmprimitivesPackage.OR_GROUP__GROUP_HAS_CHILD:
        getGroupHasChild().clear();
        getGroupHasChild().addAll((Collection<? extends GroupHasChild>)newValue);
        return;
      case FmprimitivesPackage.OR_GROUP__GROUP_HAS_MAX:
        setGroupHasMax((GroupHasMax)newValue);
        return;
      case FmprimitivesPackage.OR_GROUP__GROUP_HAS_MIN:
        setGroupHasMin((GroupHasMin)newValue);
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
      case FmprimitivesPackage.OR_GROUP__GROUP_HAS_PARENT:
        setGroupHasParent((GroupHasParent)null);
        return;
      case FmprimitivesPackage.OR_GROUP__GROUP_HAS_CHILD:
        getGroupHasChild().clear();
        return;
      case FmprimitivesPackage.OR_GROUP__GROUP_HAS_MAX:
        setGroupHasMax((GroupHasMax)null);
        return;
      case FmprimitivesPackage.OR_GROUP__GROUP_HAS_MIN:
        setGroupHasMin((GroupHasMin)null);
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
      case FmprimitivesPackage.OR_GROUP__GROUP_HAS_PARENT:
        return groupHasParent != null;
      case FmprimitivesPackage.OR_GROUP__GROUP_HAS_CHILD:
        return groupHasChild != null && !groupHasChild.isEmpty();
      case FmprimitivesPackage.OR_GROUP__GROUP_HAS_MAX:
        return groupHasMax != null;
      case FmprimitivesPackage.OR_GROUP__GROUP_HAS_MIN:
        return groupHasMin != null;
    }
    return super.eIsSet(featureID);
  }

} //OrGroupImpl
