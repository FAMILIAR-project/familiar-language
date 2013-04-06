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
import org.xtext.example.mydsl.fML.KnowledgeSpecification;
import org.xtext.example.mydsl.fML.SetCommand;
import org.xtext.example.mydsl.fML.Synthesis;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Synthesis</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.SynthesisImpl#isInteractive <em>Interactive</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.SynthesisImpl#getFm <em>Fm</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.SynthesisImpl#isOver <em>Over</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.SynthesisImpl#getFts <em>Fts</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.SynthesisImpl#getKst <em>Kst</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SynthesisImpl extends CommandImpl implements Synthesis
{
  /**
   * The default value of the '{@link #isInteractive() <em>Interactive</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isInteractive()
   * @generated
   * @ordered
   */
  protected static final boolean INTERACTIVE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isInteractive() <em>Interactive</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isInteractive()
   * @generated
   * @ordered
   */
  protected boolean interactive = INTERACTIVE_EDEFAULT;

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
   * The cached value of the '{@link #getKst() <em>Kst</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKst()
   * @generated
   * @ordered
   */
  protected KnowledgeSpecification kst;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SynthesisImpl()
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
    return FMLPackage.eINSTANCE.getSynthesis();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isInteractive()
  {
    return interactive;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInteractive(boolean newInteractive)
  {
    boolean oldInteractive = interactive;
    interactive = newInteractive;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.SYNTHESIS__INTERACTIVE, oldInteractive, interactive));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.SYNTHESIS__FM, oldFm, newFm);
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
        msgs = ((InternalEObject)fm).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.SYNTHESIS__FM, null, msgs);
      if (newFm != null)
        msgs = ((InternalEObject)newFm).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.SYNTHESIS__FM, null, msgs);
      msgs = basicSetFm(newFm, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.SYNTHESIS__FM, newFm, newFm));
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
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.SYNTHESIS__OVER, oldOver, over));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.SYNTHESIS__FTS, oldFts, newFts);
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
        msgs = ((InternalEObject)fts).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.SYNTHESIS__FTS, null, msgs);
      if (newFts != null)
        msgs = ((InternalEObject)newFts).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.SYNTHESIS__FTS, null, msgs);
      msgs = basicSetFts(newFts, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.SYNTHESIS__FTS, newFts, newFts));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public KnowledgeSpecification getKst()
  {
    return kst;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetKst(KnowledgeSpecification newKst, NotificationChain msgs)
  {
    KnowledgeSpecification oldKst = kst;
    kst = newKst;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.SYNTHESIS__KST, oldKst, newKst);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setKst(KnowledgeSpecification newKst)
  {
    if (newKst != kst)
    {
      NotificationChain msgs = null;
      if (kst != null)
        msgs = ((InternalEObject)kst).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.SYNTHESIS__KST, null, msgs);
      if (newKst != null)
        msgs = ((InternalEObject)newKst).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.SYNTHESIS__KST, null, msgs);
      msgs = basicSetKst(newKst, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.SYNTHESIS__KST, newKst, newKst));
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
      case FMLPackage.SYNTHESIS__FM:
        return basicSetFm(null, msgs);
      case FMLPackage.SYNTHESIS__FTS:
        return basicSetFts(null, msgs);
      case FMLPackage.SYNTHESIS__KST:
        return basicSetKst(null, msgs);
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
      case FMLPackage.SYNTHESIS__INTERACTIVE:
        return isInteractive();
      case FMLPackage.SYNTHESIS__FM:
        return getFm();
      case FMLPackage.SYNTHESIS__OVER:
        return isOver();
      case FMLPackage.SYNTHESIS__FTS:
        return getFts();
      case FMLPackage.SYNTHESIS__KST:
        return getKst();
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
      case FMLPackage.SYNTHESIS__INTERACTIVE:
        setInteractive((Boolean)newValue);
        return;
      case FMLPackage.SYNTHESIS__FM:
        setFm((FMCommand)newValue);
        return;
      case FMLPackage.SYNTHESIS__OVER:
        setOver((Boolean)newValue);
        return;
      case FMLPackage.SYNTHESIS__FTS:
        setFts((SetCommand)newValue);
        return;
      case FMLPackage.SYNTHESIS__KST:
        setKst((KnowledgeSpecification)newValue);
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
      case FMLPackage.SYNTHESIS__INTERACTIVE:
        setInteractive(INTERACTIVE_EDEFAULT);
        return;
      case FMLPackage.SYNTHESIS__FM:
        setFm((FMCommand)null);
        return;
      case FMLPackage.SYNTHESIS__OVER:
        setOver(OVER_EDEFAULT);
        return;
      case FMLPackage.SYNTHESIS__FTS:
        setFts((SetCommand)null);
        return;
      case FMLPackage.SYNTHESIS__KST:
        setKst((KnowledgeSpecification)null);
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
      case FMLPackage.SYNTHESIS__INTERACTIVE:
        return interactive != INTERACTIVE_EDEFAULT;
      case FMLPackage.SYNTHESIS__FM:
        return fm != null;
      case FMLPackage.SYNTHESIS__OVER:
        return over != OVER_EDEFAULT;
      case FMLPackage.SYNTHESIS__FTS:
        return fts != null;
      case FMLPackage.SYNTHESIS__KST:
        return kst != null;
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
    result.append(" (interactive: ");
    result.append(interactive);
    result.append(", over: ");
    result.append(over);
    result.append(')');
    return result.toString();
  }

} //SynthesisImpl
