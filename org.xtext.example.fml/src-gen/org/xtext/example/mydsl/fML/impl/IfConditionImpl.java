/**
 */
package org.xtext.example.mydsl.fML.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.xtext.example.mydsl.fML.ComplexCommand;
import org.xtext.example.mydsl.fML.FMLPackage;
import org.xtext.example.mydsl.fML.IfCondition;
import org.xtext.example.mydsl.fML.ScriptCommand;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>If Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.IfConditionImpl#getBexpr <em>Bexpr</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.IfConditionImpl#getThen <em>Then</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.IfConditionImpl#getElse <em>Else</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IfConditionImpl extends CommandImpl implements IfCondition
{
  /**
   * The cached value of the '{@link #getBexpr() <em>Bexpr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBexpr()
   * @generated
   * @ordered
   */
  protected ComplexCommand bexpr;

  /**
   * The cached value of the '{@link #getThen() <em>Then</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getThen()
   * @generated
   * @ordered
   */
  protected EList<ScriptCommand> then;

  /**
   * The cached value of the '{@link #getElse() <em>Else</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElse()
   * @generated
   * @ordered
   */
  protected EList<ScriptCommand> else_;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IfConditionImpl()
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
    return FMLPackage.eINSTANCE.getIfCondition();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComplexCommand getBexpr()
  {
    return bexpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBexpr(ComplexCommand newBexpr, NotificationChain msgs)
  {
    ComplexCommand oldBexpr = bexpr;
    bexpr = newBexpr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.IF_CONDITION__BEXPR, oldBexpr, newBexpr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBexpr(ComplexCommand newBexpr)
  {
    if (newBexpr != bexpr)
    {
      NotificationChain msgs = null;
      if (bexpr != null)
        msgs = ((InternalEObject)bexpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.IF_CONDITION__BEXPR, null, msgs);
      if (newBexpr != null)
        msgs = ((InternalEObject)newBexpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.IF_CONDITION__BEXPR, null, msgs);
      msgs = basicSetBexpr(newBexpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.IF_CONDITION__BEXPR, newBexpr, newBexpr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ScriptCommand> getThen()
  {
    if (then == null)
    {
      then = new EObjectContainmentEList<ScriptCommand>(ScriptCommand.class, this, FMLPackage.IF_CONDITION__THEN);
    }
    return then;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ScriptCommand> getElse()
  {
    if (else_ == null)
    {
      else_ = new EObjectContainmentEList<ScriptCommand>(ScriptCommand.class, this, FMLPackage.IF_CONDITION__ELSE);
    }
    return else_;
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
      case FMLPackage.IF_CONDITION__BEXPR:
        return basicSetBexpr(null, msgs);
      case FMLPackage.IF_CONDITION__THEN:
        return ((InternalEList<?>)getThen()).basicRemove(otherEnd, msgs);
      case FMLPackage.IF_CONDITION__ELSE:
        return ((InternalEList<?>)getElse()).basicRemove(otherEnd, msgs);
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
      case FMLPackage.IF_CONDITION__BEXPR:
        return getBexpr();
      case FMLPackage.IF_CONDITION__THEN:
        return getThen();
      case FMLPackage.IF_CONDITION__ELSE:
        return getElse();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case FMLPackage.IF_CONDITION__BEXPR:
        setBexpr((ComplexCommand)newValue);
        return;
      case FMLPackage.IF_CONDITION__THEN:
        getThen().clear();
        getThen().addAll((Collection<? extends ScriptCommand>)newValue);
        return;
      case FMLPackage.IF_CONDITION__ELSE:
        getElse().clear();
        getElse().addAll((Collection<? extends ScriptCommand>)newValue);
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
      case FMLPackage.IF_CONDITION__BEXPR:
        setBexpr((ComplexCommand)null);
        return;
      case FMLPackage.IF_CONDITION__THEN:
        getThen().clear();
        return;
      case FMLPackage.IF_CONDITION__ELSE:
        getElse().clear();
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
      case FMLPackage.IF_CONDITION__BEXPR:
        return bexpr != null;
      case FMLPackage.IF_CONDITION__THEN:
        return then != null && !then.isEmpty();
      case FMLPackage.IF_CONDITION__ELSE:
        return else_ != null && !else_.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //IfConditionImpl
