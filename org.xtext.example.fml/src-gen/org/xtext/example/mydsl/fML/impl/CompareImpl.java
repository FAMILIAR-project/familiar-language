/**
 */
package org.xtext.example.mydsl.fML.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.xtext.example.mydsl.fML.Compare;
import org.xtext.example.mydsl.fML.FMCommand;
import org.xtext.example.mydsl.fML.FMLPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Compare</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.CompareImpl#getFm_left <em>Fm left</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.CompareImpl#getFm_right <em>Fm right</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CompareImpl extends CommandImpl implements Compare
{
  /**
   * The cached value of the '{@link #getFm_left() <em>Fm left</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFm_left()
   * @generated
   * @ordered
   */
  protected FMCommand fm_left;

  /**
   * The cached value of the '{@link #getFm_right() <em>Fm right</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFm_right()
   * @generated
   * @ordered
   */
  protected FMCommand fm_right;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CompareImpl()
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
    return FMLPackage.eINSTANCE.getCompare();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FMCommand getFm_left()
  {
    return fm_left;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFm_left(FMCommand newFm_left, NotificationChain msgs)
  {
    FMCommand oldFm_left = fm_left;
    fm_left = newFm_left;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.COMPARE__FM_LEFT, oldFm_left, newFm_left);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFm_left(FMCommand newFm_left)
  {
    if (newFm_left != fm_left)
    {
      NotificationChain msgs = null;
      if (fm_left != null)
        msgs = ((InternalEObject)fm_left).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.COMPARE__FM_LEFT, null, msgs);
      if (newFm_left != null)
        msgs = ((InternalEObject)newFm_left).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.COMPARE__FM_LEFT, null, msgs);
      msgs = basicSetFm_left(newFm_left, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.COMPARE__FM_LEFT, newFm_left, newFm_left));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FMCommand getFm_right()
  {
    return fm_right;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFm_right(FMCommand newFm_right, NotificationChain msgs)
  {
    FMCommand oldFm_right = fm_right;
    fm_right = newFm_right;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.COMPARE__FM_RIGHT, oldFm_right, newFm_right);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFm_right(FMCommand newFm_right)
  {
    if (newFm_right != fm_right)
    {
      NotificationChain msgs = null;
      if (fm_right != null)
        msgs = ((InternalEObject)fm_right).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.COMPARE__FM_RIGHT, null, msgs);
      if (newFm_right != null)
        msgs = ((InternalEObject)newFm_right).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.COMPARE__FM_RIGHT, null, msgs);
      msgs = basicSetFm_right(newFm_right, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.COMPARE__FM_RIGHT, newFm_right, newFm_right));
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
      case FMLPackage.COMPARE__FM_LEFT:
        return basicSetFm_left(null, msgs);
      case FMLPackage.COMPARE__FM_RIGHT:
        return basicSetFm_right(null, msgs);
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
      case FMLPackage.COMPARE__FM_LEFT:
        return getFm_left();
      case FMLPackage.COMPARE__FM_RIGHT:
        return getFm_right();
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
      case FMLPackage.COMPARE__FM_LEFT:
        setFm_left((FMCommand)newValue);
        return;
      case FMLPackage.COMPARE__FM_RIGHT:
        setFm_right((FMCommand)newValue);
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
      case FMLPackage.COMPARE__FM_LEFT:
        setFm_left((FMCommand)null);
        return;
      case FMLPackage.COMPARE__FM_RIGHT:
        setFm_right((FMCommand)null);
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
      case FMLPackage.COMPARE__FM_LEFT:
        return fm_left != null;
      case FMLPackage.COMPARE__FM_RIGHT:
        return fm_right != null;
    }
    return super.eIsSet(featureID);
  }

} //CompareImpl
