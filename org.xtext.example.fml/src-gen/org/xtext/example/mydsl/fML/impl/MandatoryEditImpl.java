/**
 */
package org.xtext.example.mydsl.fML.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.xtext.example.mydsl.fML.FMLPackage;
import org.xtext.example.mydsl.fML.FTCommand;
import org.xtext.example.mydsl.fML.MandatoryEdit;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mandatory Edit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.MandatoryEditImpl#getFt <em>Ft</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MandatoryEditImpl extends ModifyVOperatorImpl implements MandatoryEdit
{
  /**
   * The cached value of the '{@link #getFt() <em>Ft</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFt()
   * @generated
   * @ordered
   */
  protected FTCommand ft;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MandatoryEditImpl()
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
    return FMLPackage.eINSTANCE.getMandatoryEdit();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FTCommand getFt()
  {
    return ft;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFt(FTCommand newFt, NotificationChain msgs)
  {
    FTCommand oldFt = ft;
    ft = newFt;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.MANDATORY_EDIT__FT, oldFt, newFt);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFt(FTCommand newFt)
  {
    if (newFt != ft)
    {
      NotificationChain msgs = null;
      if (ft != null)
        msgs = ((InternalEObject)ft).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.MANDATORY_EDIT__FT, null, msgs);
      if (newFt != null)
        msgs = ((InternalEObject)newFt).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.MANDATORY_EDIT__FT, null, msgs);
      msgs = basicSetFt(newFt, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.MANDATORY_EDIT__FT, newFt, newFt));
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
      case FMLPackage.MANDATORY_EDIT__FT:
        return basicSetFt(null, msgs);
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
      case FMLPackage.MANDATORY_EDIT__FT:
        return getFt();
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
      case FMLPackage.MANDATORY_EDIT__FT:
        setFt((FTCommand)newValue);
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
      case FMLPackage.MANDATORY_EDIT__FT:
        setFt((FTCommand)null);
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
      case FMLPackage.MANDATORY_EDIT__FT:
        return ft != null;
    }
    return super.eIsSet(featureID);
  }

} //MandatoryEditImpl
