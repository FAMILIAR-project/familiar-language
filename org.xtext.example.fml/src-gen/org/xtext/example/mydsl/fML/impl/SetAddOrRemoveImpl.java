/**
 */
package org.xtext.example.mydsl.fML.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.FMLPackage;
import org.xtext.example.mydsl.fML.SetAddOrRemove;
import org.xtext.example.mydsl.fML.SetCommand;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Set Add Or Remove</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.SetAddOrRemoveImpl#getOp <em>Op</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.SetAddOrRemoveImpl#getSetl <em>Setl</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.SetAddOrRemoveImpl#getVar <em>Var</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SetAddOrRemoveImpl extends SetOperationsImpl implements SetAddOrRemove
{
  /**
   * The default value of the '{@link #getOp() <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOp()
   * @generated
   * @ordered
   */
  protected static final String OP_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getOp() <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOp()
   * @generated
   * @ordered
   */
  protected String op = OP_EDEFAULT;

  /**
   * The cached value of the '{@link #getSetl() <em>Setl</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSetl()
   * @generated
   * @ordered
   */
  protected SetCommand setl;

  /**
   * The cached value of the '{@link #getVar() <em>Var</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVar()
   * @generated
   * @ordered
   */
  protected Command var;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SetAddOrRemoveImpl()
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
    return FMLPackage.eINSTANCE.getSetAddOrRemove();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getOp()
  {
    return op;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOp(String newOp)
  {
    String oldOp = op;
    op = newOp;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.SET_ADD_OR_REMOVE__OP, oldOp, op));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SetCommand getSetl()
  {
    return setl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSetl(SetCommand newSetl, NotificationChain msgs)
  {
    SetCommand oldSetl = setl;
    setl = newSetl;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.SET_ADD_OR_REMOVE__SETL, oldSetl, newSetl);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSetl(SetCommand newSetl)
  {
    if (newSetl != setl)
    {
      NotificationChain msgs = null;
      if (setl != null)
        msgs = ((InternalEObject)setl).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.SET_ADD_OR_REMOVE__SETL, null, msgs);
      if (newSetl != null)
        msgs = ((InternalEObject)newSetl).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.SET_ADD_OR_REMOVE__SETL, null, msgs);
      msgs = basicSetSetl(newSetl, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.SET_ADD_OR_REMOVE__SETL, newSetl, newSetl));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Command getVar()
  {
    return var;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVar(Command newVar, NotificationChain msgs)
  {
    Command oldVar = var;
    var = newVar;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.SET_ADD_OR_REMOVE__VAR, oldVar, newVar);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVar(Command newVar)
  {
    if (newVar != var)
    {
      NotificationChain msgs = null;
      if (var != null)
        msgs = ((InternalEObject)var).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.SET_ADD_OR_REMOVE__VAR, null, msgs);
      if (newVar != null)
        msgs = ((InternalEObject)newVar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.SET_ADD_OR_REMOVE__VAR, null, msgs);
      msgs = basicSetVar(newVar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.SET_ADD_OR_REMOVE__VAR, newVar, newVar));
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
      case FMLPackage.SET_ADD_OR_REMOVE__SETL:
        return basicSetSetl(null, msgs);
      case FMLPackage.SET_ADD_OR_REMOVE__VAR:
        return basicSetVar(null, msgs);
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
      case FMLPackage.SET_ADD_OR_REMOVE__OP:
        return getOp();
      case FMLPackage.SET_ADD_OR_REMOVE__SETL:
        return getSetl();
      case FMLPackage.SET_ADD_OR_REMOVE__VAR:
        return getVar();
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
      case FMLPackage.SET_ADD_OR_REMOVE__OP:
        setOp((String)newValue);
        return;
      case FMLPackage.SET_ADD_OR_REMOVE__SETL:
        setSetl((SetCommand)newValue);
        return;
      case FMLPackage.SET_ADD_OR_REMOVE__VAR:
        setVar((Command)newValue);
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
      case FMLPackage.SET_ADD_OR_REMOVE__OP:
        setOp(OP_EDEFAULT);
        return;
      case FMLPackage.SET_ADD_OR_REMOVE__SETL:
        setSetl((SetCommand)null);
        return;
      case FMLPackage.SET_ADD_OR_REMOVE__VAR:
        setVar((Command)null);
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
      case FMLPackage.SET_ADD_OR_REMOVE__OP:
        return OP_EDEFAULT == null ? op != null : !OP_EDEFAULT.equals(op);
      case FMLPackage.SET_ADD_OR_REMOVE__SETL:
        return setl != null;
      case FMLPackage.SET_ADD_OR_REMOVE__VAR:
        return var != null;
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
    result.append(" (op: ");
    result.append(op);
    result.append(')');
    return result.toString();
  }

} //SetAddOrRemoveImpl
