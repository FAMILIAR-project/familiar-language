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
import org.xtext.example.mydsl.fML.FTCommand;
import org.xtext.example.mydsl.fML.Insert;
import org.xtext.example.mydsl.fML.VariabilityOpCommand;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Insert</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.InsertImpl#getAspectfm <em>Aspectfm</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.InsertImpl#getBaseft <em>Baseft</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.InsertImpl#getOp <em>Op</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InsertImpl extends BCommandImpl implements Insert
{
  /**
   * The cached value of the '{@link #getAspectfm() <em>Aspectfm</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAspectfm()
   * @generated
   * @ordered
   */
  protected FMCommand aspectfm;

  /**
   * The cached value of the '{@link #getBaseft() <em>Baseft</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBaseft()
   * @generated
   * @ordered
   */
  protected FTCommand baseft;

  /**
   * The cached value of the '{@link #getOp() <em>Op</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOp()
   * @generated
   * @ordered
   */
  protected VariabilityOpCommand op;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected InsertImpl()
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
    return FMLPackage.eINSTANCE.getInsert();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FMCommand getAspectfm()
  {
    return aspectfm;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAspectfm(FMCommand newAspectfm, NotificationChain msgs)
  {
    FMCommand oldAspectfm = aspectfm;
    aspectfm = newAspectfm;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.INSERT__ASPECTFM, oldAspectfm, newAspectfm);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAspectfm(FMCommand newAspectfm)
  {
    if (newAspectfm != aspectfm)
    {
      NotificationChain msgs = null;
      if (aspectfm != null)
        msgs = ((InternalEObject)aspectfm).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.INSERT__ASPECTFM, null, msgs);
      if (newAspectfm != null)
        msgs = ((InternalEObject)newAspectfm).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.INSERT__ASPECTFM, null, msgs);
      msgs = basicSetAspectfm(newAspectfm, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.INSERT__ASPECTFM, newAspectfm, newAspectfm));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FTCommand getBaseft()
  {
    return baseft;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBaseft(FTCommand newBaseft, NotificationChain msgs)
  {
    FTCommand oldBaseft = baseft;
    baseft = newBaseft;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.INSERT__BASEFT, oldBaseft, newBaseft);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBaseft(FTCommand newBaseft)
  {
    if (newBaseft != baseft)
    {
      NotificationChain msgs = null;
      if (baseft != null)
        msgs = ((InternalEObject)baseft).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.INSERT__BASEFT, null, msgs);
      if (newBaseft != null)
        msgs = ((InternalEObject)newBaseft).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.INSERT__BASEFT, null, msgs);
      msgs = basicSetBaseft(newBaseft, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.INSERT__BASEFT, newBaseft, newBaseft));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VariabilityOpCommand getOp()
  {
    return op;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOp(VariabilityOpCommand newOp, NotificationChain msgs)
  {
    VariabilityOpCommand oldOp = op;
    op = newOp;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.INSERT__OP, oldOp, newOp);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOp(VariabilityOpCommand newOp)
  {
    if (newOp != op)
    {
      NotificationChain msgs = null;
      if (op != null)
        msgs = ((InternalEObject)op).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.INSERT__OP, null, msgs);
      if (newOp != null)
        msgs = ((InternalEObject)newOp).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.INSERT__OP, null, msgs);
      msgs = basicSetOp(newOp, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.INSERT__OP, newOp, newOp));
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
      case FMLPackage.INSERT__ASPECTFM:
        return basicSetAspectfm(null, msgs);
      case FMLPackage.INSERT__BASEFT:
        return basicSetBaseft(null, msgs);
      case FMLPackage.INSERT__OP:
        return basicSetOp(null, msgs);
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
      case FMLPackage.INSERT__ASPECTFM:
        return getAspectfm();
      case FMLPackage.INSERT__BASEFT:
        return getBaseft();
      case FMLPackage.INSERT__OP:
        return getOp();
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
      case FMLPackage.INSERT__ASPECTFM:
        setAspectfm((FMCommand)newValue);
        return;
      case FMLPackage.INSERT__BASEFT:
        setBaseft((FTCommand)newValue);
        return;
      case FMLPackage.INSERT__OP:
        setOp((VariabilityOpCommand)newValue);
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
      case FMLPackage.INSERT__ASPECTFM:
        setAspectfm((FMCommand)null);
        return;
      case FMLPackage.INSERT__BASEFT:
        setBaseft((FTCommand)null);
        return;
      case FMLPackage.INSERT__OP:
        setOp((VariabilityOpCommand)null);
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
      case FMLPackage.INSERT__ASPECTFM:
        return aspectfm != null;
      case FMLPackage.INSERT__BASEFT:
        return baseft != null;
      case FMLPackage.INSERT__OP:
        return op != null;
    }
    return super.eIsSet(featureID);
  }

} //InsertImpl
