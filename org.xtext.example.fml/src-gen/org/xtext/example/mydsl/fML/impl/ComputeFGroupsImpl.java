/**
 */
package org.xtext.example.mydsl.fML.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.xtext.example.mydsl.fML.ComputeFGroups;
import org.xtext.example.mydsl.fML.FMCommand;
import org.xtext.example.mydsl.fML.FMLPackage;
import org.xtext.example.mydsl.fML.KindOfComputeGroups;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Compute FGroups</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.ComputeFGroupsImpl#getKindOfGroups <em>Kind Of Groups</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.ComputeFGroupsImpl#getFm <em>Fm</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComputeFGroupsImpl extends CommandImpl implements ComputeFGroups
{
  /**
   * The default value of the '{@link #getKindOfGroups() <em>Kind Of Groups</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKindOfGroups()
   * @generated
   * @ordered
   */
  protected static final KindOfComputeGroups KIND_OF_GROUPS_EDEFAULT = KindOfComputeGroups.OR;

  /**
   * The cached value of the '{@link #getKindOfGroups() <em>Kind Of Groups</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKindOfGroups()
   * @generated
   * @ordered
   */
  protected KindOfComputeGroups kindOfGroups = KIND_OF_GROUPS_EDEFAULT;

  /**
   * The cached value of the '{@link #getFm() <em>Fm</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFm()
   * @generated
   * @ordered
   */
  protected FMCommand fm;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ComputeFGroupsImpl()
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
    return FMLPackage.eINSTANCE.getComputeFGroups();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public KindOfComputeGroups getKindOfGroups()
  {
    return kindOfGroups;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setKindOfGroups(KindOfComputeGroups newKindOfGroups)
  {
    KindOfComputeGroups oldKindOfGroups = kindOfGroups;
    kindOfGroups = newKindOfGroups == null ? KIND_OF_GROUPS_EDEFAULT : newKindOfGroups;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.COMPUTE_FGROUPS__KIND_OF_GROUPS, oldKindOfGroups, kindOfGroups));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FMCommand getFm()
  {
    return fm;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFm(FMCommand newFm, NotificationChain msgs)
  {
    FMCommand oldFm = fm;
    fm = newFm;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.COMPUTE_FGROUPS__FM, oldFm, newFm);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFm(FMCommand newFm)
  {
    if (newFm != fm)
    {
      NotificationChain msgs = null;
      if (fm != null)
        msgs = ((InternalEObject)fm).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.COMPUTE_FGROUPS__FM, null, msgs);
      if (newFm != null)
        msgs = ((InternalEObject)newFm).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.COMPUTE_FGROUPS__FM, null, msgs);
      msgs = basicSetFm(newFm, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.COMPUTE_FGROUPS__FM, newFm, newFm));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case FMLPackage.COMPUTE_FGROUPS__FM:
        return basicSetFm(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case FMLPackage.COMPUTE_FGROUPS__KIND_OF_GROUPS:
        return getKindOfGroups();
      case FMLPackage.COMPUTE_FGROUPS__FM:
        return getFm();
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
      case FMLPackage.COMPUTE_FGROUPS__KIND_OF_GROUPS:
        setKindOfGroups((KindOfComputeGroups)newValue);
        return;
      case FMLPackage.COMPUTE_FGROUPS__FM:
        setFm((FMCommand)newValue);
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
      case FMLPackage.COMPUTE_FGROUPS__KIND_OF_GROUPS:
        setKindOfGroups(KIND_OF_GROUPS_EDEFAULT);
        return;
      case FMLPackage.COMPUTE_FGROUPS__FM:
        setFm((FMCommand)null);
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
      case FMLPackage.COMPUTE_FGROUPS__KIND_OF_GROUPS:
        return kindOfGroups != KIND_OF_GROUPS_EDEFAULT;
      case FMLPackage.COMPUTE_FGROUPS__FM:
        return fm != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (kindOfGroups: ");
    result.append(kindOfGroups);
    result.append(')');
    return result.toString();
  }

} //ComputeFGroupsImpl
