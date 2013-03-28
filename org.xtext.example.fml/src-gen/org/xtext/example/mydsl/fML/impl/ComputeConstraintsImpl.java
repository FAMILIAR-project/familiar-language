/**
 */
package org.xtext.example.mydsl.fML.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.xtext.example.mydsl.fML.ComputeConstraints;
import org.xtext.example.mydsl.fML.FMCommand;
import org.xtext.example.mydsl.fML.FMLPackage;
import org.xtext.example.mydsl.fML.KindOfCompute;
import org.xtext.example.mydsl.fML.SetCommand;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Compute Constraints</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.ComputeConstraintsImpl#getKindOfCompute <em>Kind Of Compute</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.ComputeConstraintsImpl#getFm <em>Fm</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.ComputeConstraintsImpl#isOver <em>Over</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.ComputeConstraintsImpl#getFts <em>Fts</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComputeConstraintsImpl extends CommandImpl implements ComputeConstraints
{
  /**
   * The default value of the '{@link #getKindOfCompute() <em>Kind Of Compute</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKindOfCompute()
   * @generated
   * @ordered
   */
  protected static final KindOfCompute KIND_OF_COMPUTE_EDEFAULT = KindOfCompute.IMPLIES;

  /**
   * The cached value of the '{@link #getKindOfCompute() <em>Kind Of Compute</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKindOfCompute()
   * @generated
   * @ordered
   */
  protected KindOfCompute kindOfCompute = KIND_OF_COMPUTE_EDEFAULT;

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
   * The default value of the '{@link #isOver() <em>Over</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isOver()
   * @generated
   * @ordered
   */
  protected static final boolean OVER_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isOver() <em>Over</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isOver()
   * @generated
   * @ordered
   */
  protected boolean over = OVER_EDEFAULT;

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
  protected ComputeConstraintsImpl()
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
    return FMLPackage.eINSTANCE.getComputeConstraints();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public KindOfCompute getKindOfCompute()
  {
    return kindOfCompute;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setKindOfCompute(KindOfCompute newKindOfCompute)
  {
    KindOfCompute oldKindOfCompute = kindOfCompute;
    kindOfCompute = newKindOfCompute == null ? KIND_OF_COMPUTE_EDEFAULT : newKindOfCompute;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.COMPUTE_CONSTRAINTS__KIND_OF_COMPUTE, oldKindOfCompute, kindOfCompute));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.COMPUTE_CONSTRAINTS__FM, oldFm, newFm);
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
        msgs = ((InternalEObject)fm).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.COMPUTE_CONSTRAINTS__FM, null, msgs);
      if (newFm != null)
        msgs = ((InternalEObject)newFm).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.COMPUTE_CONSTRAINTS__FM, null, msgs);
      msgs = basicSetFm(newFm, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.COMPUTE_CONSTRAINTS__FM, newFm, newFm));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isOver()
  {
    return over;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOver(boolean newOver)
  {
    boolean oldOver = over;
    over = newOver;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.COMPUTE_CONSTRAINTS__OVER, oldOver, over));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.COMPUTE_CONSTRAINTS__FTS, oldFts, newFts);
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
        msgs = ((InternalEObject)fts).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.COMPUTE_CONSTRAINTS__FTS, null, msgs);
      if (newFts != null)
        msgs = ((InternalEObject)newFts).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.COMPUTE_CONSTRAINTS__FTS, null, msgs);
      msgs = basicSetFts(newFts, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.COMPUTE_CONSTRAINTS__FTS, newFts, newFts));
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
      case FMLPackage.COMPUTE_CONSTRAINTS__FM:
        return basicSetFm(null, msgs);
      case FMLPackage.COMPUTE_CONSTRAINTS__FTS:
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
      case FMLPackage.COMPUTE_CONSTRAINTS__KIND_OF_COMPUTE:
        return getKindOfCompute();
      case FMLPackage.COMPUTE_CONSTRAINTS__FM:
        return getFm();
      case FMLPackage.COMPUTE_CONSTRAINTS__OVER:
        return isOver();
      case FMLPackage.COMPUTE_CONSTRAINTS__FTS:
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
      case FMLPackage.COMPUTE_CONSTRAINTS__KIND_OF_COMPUTE:
        setKindOfCompute((KindOfCompute)newValue);
        return;
      case FMLPackage.COMPUTE_CONSTRAINTS__FM:
        setFm((FMCommand)newValue);
        return;
      case FMLPackage.COMPUTE_CONSTRAINTS__OVER:
        setOver((Boolean)newValue);
        return;
      case FMLPackage.COMPUTE_CONSTRAINTS__FTS:
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
      case FMLPackage.COMPUTE_CONSTRAINTS__KIND_OF_COMPUTE:
        setKindOfCompute(KIND_OF_COMPUTE_EDEFAULT);
        return;
      case FMLPackage.COMPUTE_CONSTRAINTS__FM:
        setFm((FMCommand)null);
        return;
      case FMLPackage.COMPUTE_CONSTRAINTS__OVER:
        setOver(OVER_EDEFAULT);
        return;
      case FMLPackage.COMPUTE_CONSTRAINTS__FTS:
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
      case FMLPackage.COMPUTE_CONSTRAINTS__KIND_OF_COMPUTE:
        return kindOfCompute != KIND_OF_COMPUTE_EDEFAULT;
      case FMLPackage.COMPUTE_CONSTRAINTS__FM:
        return fm != null;
      case FMLPackage.COMPUTE_CONSTRAINTS__OVER:
        return over != OVER_EDEFAULT;
      case FMLPackage.COMPUTE_CONSTRAINTS__FTS:
        return fts != null;
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
    result.append(" (kindOfCompute: ");
    result.append(kindOfCompute);
    result.append(", over: ");
    result.append(over);
    result.append(')');
    return result.toString();
  }

} //ComputeConstraintsImpl
