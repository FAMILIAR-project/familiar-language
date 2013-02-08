/**
 */
package org.xtext.example.mydsl.fML.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.xtext.example.mydsl.fML.AlternativeEdit;
import org.xtext.example.mydsl.fML.FMLPackage;
import org.xtext.example.mydsl.fML.SetCommand;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Alternative Edit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.AlternativeEditImpl#getFts <em>Fts</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AlternativeEditImpl extends ModifyVOperatorImpl implements AlternativeEdit
{
  /**
   * The cached value of the '{@link #getFts() <em>Fts</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFts()
   * @generated
   * @ordered
   */
  protected SetCommand fts;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AlternativeEditImpl()
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
    return FMLPackage.eINSTANCE.getAlternativeEdit();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SetCommand getFts()
  {
    return fts;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFts(SetCommand newFts, NotificationChain msgs)
  {
    SetCommand oldFts = fts;
    fts = newFts;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.ALTERNATIVE_EDIT__FTS, oldFts, newFts);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFts(SetCommand newFts)
  {
    if (newFts != fts)
    {
      NotificationChain msgs = null;
      if (fts != null)
        msgs = ((InternalEObject)fts).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.ALTERNATIVE_EDIT__FTS, null, msgs);
      if (newFts != null)
        msgs = ((InternalEObject)newFts).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.ALTERNATIVE_EDIT__FTS, null, msgs);
      msgs = basicSetFts(newFts, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.ALTERNATIVE_EDIT__FTS, newFts, newFts));
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
      case FMLPackage.ALTERNATIVE_EDIT__FTS:
        return basicSetFts(null, msgs);
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
      case FMLPackage.ALTERNATIVE_EDIT__FTS:
        return getFts();
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
      case FMLPackage.ALTERNATIVE_EDIT__FTS:
        setFts((SetCommand)newValue);
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
      case FMLPackage.ALTERNATIVE_EDIT__FTS:
        setFts((SetCommand)null);
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
      case FMLPackage.ALTERNATIVE_EDIT__FTS:
        return fts != null;
    }
    return super.eIsSet(featureID);
  }

} //AlternativeEditImpl
