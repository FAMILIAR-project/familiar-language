/**
 */
package org.xtext.example.mydsl.fML.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.xtext.example.mydsl.fML.Extract;
import org.xtext.example.mydsl.fML.FMLPackage;
import org.xtext.example.mydsl.fML.FTCommand;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extract</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.ExtractImpl#getRootfeature <em>Rootfeature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExtractImpl extends FMCommandImpl implements Extract
{
  /**
   * The cached value of the '{@link #getRootfeature() <em>Rootfeature</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRootfeature()
   * @generated
   * @ordered
   */
  protected FTCommand rootfeature;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ExtractImpl()
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
    return FMLPackage.eINSTANCE.getExtract();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FTCommand getRootfeature()
  {
    return rootfeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRootfeature(FTCommand newRootfeature, NotificationChain msgs)
  {
    FTCommand oldRootfeature = rootfeature;
    rootfeature = newRootfeature;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.EXTRACT__ROOTFEATURE, oldRootfeature, newRootfeature);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRootfeature(FTCommand newRootfeature)
  {
    if (newRootfeature != rootfeature)
    {
      NotificationChain msgs = null;
      if (rootfeature != null)
        msgs = ((InternalEObject)rootfeature).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.EXTRACT__ROOTFEATURE, null, msgs);
      if (newRootfeature != null)
        msgs = ((InternalEObject)newRootfeature).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.EXTRACT__ROOTFEATURE, null, msgs);
      msgs = basicSetRootfeature(newRootfeature, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.EXTRACT__ROOTFEATURE, newRootfeature, newRootfeature));
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
      case FMLPackage.EXTRACT__ROOTFEATURE:
        return basicSetRootfeature(null, msgs);
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
      case FMLPackage.EXTRACT__ROOTFEATURE:
        return getRootfeature();
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
      case FMLPackage.EXTRACT__ROOTFEATURE:
        setRootfeature((FTCommand)newValue);
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
      case FMLPackage.EXTRACT__ROOTFEATURE:
        setRootfeature((FTCommand)null);
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
      case FMLPackage.EXTRACT__ROOTFEATURE:
        return rootfeature != null;
    }
    return super.eIsSet(featureID);
  }

} //ExtractImpl
