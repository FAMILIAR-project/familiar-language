/**
 */
package org.xtext.example.mydsl.fML.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.xtext.example.mydsl.fML.FMCommand;
import org.xtext.example.mydsl.fML.FMLPackage;
import org.xtext.example.mydsl.fML.GetConstraints;
import org.xtext.example.mydsl.fML.KindOfGet;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Get Constraints</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.GetConstraintsImpl#getKindOfGet <em>Kind Of Get</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.GetConstraintsImpl#getFm <em>Fm</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GetConstraintsImpl extends CommandImpl implements GetConstraints
{
  /**
   * The default value of the '{@link #getKindOfGet() <em>Kind Of Get</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKindOfGet()
   * @generated
   * @ordered
   */
  protected static final KindOfGet KIND_OF_GET_EDEFAULT = KindOfGet.HIERARCHY_IMPLIES;

  /**
   * The cached value of the '{@link #getKindOfGet() <em>Kind Of Get</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKindOfGet()
   * @generated
   * @ordered
   */
  protected KindOfGet kindOfGet = KIND_OF_GET_EDEFAULT;

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
  protected GetConstraintsImpl()
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
    return FMLPackage.eINSTANCE.getGetConstraints();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public KindOfGet getKindOfGet()
  {
    return kindOfGet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setKindOfGet(KindOfGet newKindOfGet)
  {
    KindOfGet oldKindOfGet = kindOfGet;
    kindOfGet = newKindOfGet == null ? KIND_OF_GET_EDEFAULT : newKindOfGet;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.GET_CONSTRAINTS__KIND_OF_GET, oldKindOfGet, kindOfGet));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.GET_CONSTRAINTS__FM, oldFm, newFm);
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
        msgs = ((InternalEObject)fm).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.GET_CONSTRAINTS__FM, null, msgs);
      if (newFm != null)
        msgs = ((InternalEObject)newFm).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.GET_CONSTRAINTS__FM, null, msgs);
      msgs = basicSetFm(newFm, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.GET_CONSTRAINTS__FM, newFm, newFm));
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
      case FMLPackage.GET_CONSTRAINTS__FM:
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
      case FMLPackage.GET_CONSTRAINTS__KIND_OF_GET:
        return getKindOfGet();
      case FMLPackage.GET_CONSTRAINTS__FM:
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
      case FMLPackage.GET_CONSTRAINTS__KIND_OF_GET:
        setKindOfGet((KindOfGet)newValue);
        return;
      case FMLPackage.GET_CONSTRAINTS__FM:
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
      case FMLPackage.GET_CONSTRAINTS__KIND_OF_GET:
        setKindOfGet(KIND_OF_GET_EDEFAULT);
        return;
      case FMLPackage.GET_CONSTRAINTS__FM:
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
      case FMLPackage.GET_CONSTRAINTS__KIND_OF_GET:
        return kindOfGet != KIND_OF_GET_EDEFAULT;
      case FMLPackage.GET_CONSTRAINTS__FM:
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
    result.append(" (kindOfGet: ");
    result.append(kindOfGet);
    result.append(')');
    return result.toString();
  }

} //GetConstraintsImpl
