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
import org.xtext.example.mydsl.fML.RenameFeature;
import org.xtext.example.mydsl.fML.StrCommand;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rename Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.RenameFeatureImpl#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.RenameFeatureImpl#getFeatureNew <em>Feature New</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RenameFeatureImpl extends BCommandImpl implements RenameFeature
{
  /**
   * The cached value of the '{@link #getFeature() <em>Feature</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeature()
   * @generated
   * @ordered
   */
  protected FTCommand feature;

  /**
   * The cached value of the '{@link #getFeatureNew() <em>Feature New</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeatureNew()
   * @generated
   * @ordered
   */
  protected StrCommand featureNew;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RenameFeatureImpl()
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
    return FMLPackage.eINSTANCE.getRenameFeature();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FTCommand getFeature()
  {
    return feature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFeature(FTCommand newFeature, NotificationChain msgs)
  {
    FTCommand oldFeature = feature;
    feature = newFeature;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.RENAME_FEATURE__FEATURE, oldFeature, newFeature);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFeature(FTCommand newFeature)
  {
    if (newFeature != feature)
    {
      NotificationChain msgs = null;
      if (feature != null)
        msgs = ((InternalEObject)feature).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.RENAME_FEATURE__FEATURE, null, msgs);
      if (newFeature != null)
        msgs = ((InternalEObject)newFeature).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.RENAME_FEATURE__FEATURE, null, msgs);
      msgs = basicSetFeature(newFeature, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.RENAME_FEATURE__FEATURE, newFeature, newFeature));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StrCommand getFeatureNew()
  {
    return featureNew;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFeatureNew(StrCommand newFeatureNew, NotificationChain msgs)
  {
    StrCommand oldFeatureNew = featureNew;
    featureNew = newFeatureNew;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.RENAME_FEATURE__FEATURE_NEW, oldFeatureNew, newFeatureNew);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFeatureNew(StrCommand newFeatureNew)
  {
    if (newFeatureNew != featureNew)
    {
      NotificationChain msgs = null;
      if (featureNew != null)
        msgs = ((InternalEObject)featureNew).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.RENAME_FEATURE__FEATURE_NEW, null, msgs);
      if (newFeatureNew != null)
        msgs = ((InternalEObject)newFeatureNew).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.RENAME_FEATURE__FEATURE_NEW, null, msgs);
      msgs = basicSetFeatureNew(newFeatureNew, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.RENAME_FEATURE__FEATURE_NEW, newFeatureNew, newFeatureNew));
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
      case FMLPackage.RENAME_FEATURE__FEATURE:
        return basicSetFeature(null, msgs);
      case FMLPackage.RENAME_FEATURE__FEATURE_NEW:
        return basicSetFeatureNew(null, msgs);
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
      case FMLPackage.RENAME_FEATURE__FEATURE:
        return getFeature();
      case FMLPackage.RENAME_FEATURE__FEATURE_NEW:
        return getFeatureNew();
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
      case FMLPackage.RENAME_FEATURE__FEATURE:
        setFeature((FTCommand)newValue);
        return;
      case FMLPackage.RENAME_FEATURE__FEATURE_NEW:
        setFeatureNew((StrCommand)newValue);
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
      case FMLPackage.RENAME_FEATURE__FEATURE:
        setFeature((FTCommand)null);
        return;
      case FMLPackage.RENAME_FEATURE__FEATURE_NEW:
        setFeatureNew((StrCommand)null);
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
      case FMLPackage.RENAME_FEATURE__FEATURE:
        return feature != null;
      case FMLPackage.RENAME_FEATURE__FEATURE_NEW:
        return featureNew != null;
    }
    return super.eIsSet(featureID);
  }

} //RenameFeatureImpl
