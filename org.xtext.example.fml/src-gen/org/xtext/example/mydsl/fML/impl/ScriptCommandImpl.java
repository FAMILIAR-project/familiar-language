/**
 */
package org.xtext.example.mydsl.fML.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.xtext.example.mydsl.fML.ComplexCommand;
import org.xtext.example.mydsl.fML.FMLPackage;
import org.xtext.example.mydsl.fML.ScriptCommand;
import org.xtext.example.mydsl.fML.StringExpr;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Script Command</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.ScriptCommandImpl#getVar <em>Var</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.ScriptCommandImpl#getMetaID <em>Meta ID</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.ScriptCommandImpl#getCmd <em>Cmd</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScriptCommandImpl extends MinimalEObjectImpl.Container implements ScriptCommand
{
  /**
   * The default value of the '{@link #getVar() <em>Var</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVar()
   * @generated
   * @ordered
   */
  protected static final String VAR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getVar() <em>Var</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVar()
   * @generated
   * @ordered
   */
  protected String var = VAR_EDEFAULT;

  /**
   * The cached value of the '{@link #getMetaID() <em>Meta ID</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMetaID()
   * @generated
   * @ordered
   */
  protected StringExpr metaID;

  /**
   * The cached value of the '{@link #getCmd() <em>Cmd</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCmd()
   * @generated
   * @ordered
   */
  protected ComplexCommand cmd;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ScriptCommandImpl()
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
    return FMLPackage.eINSTANCE.getScriptCommand();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getVar()
  {
    return var;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVar(String newVar)
  {
    String oldVar = var;
    var = newVar;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.SCRIPT_COMMAND__VAR, oldVar, var));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StringExpr getMetaID()
  {
    return metaID;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMetaID(StringExpr newMetaID, NotificationChain msgs)
  {
    StringExpr oldMetaID = metaID;
    metaID = newMetaID;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.SCRIPT_COMMAND__META_ID, oldMetaID, newMetaID);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMetaID(StringExpr newMetaID)
  {
    if (newMetaID != metaID)
    {
      NotificationChain msgs = null;
      if (metaID != null)
        msgs = ((InternalEObject)metaID).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.SCRIPT_COMMAND__META_ID, null, msgs);
      if (newMetaID != null)
        msgs = ((InternalEObject)newMetaID).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.SCRIPT_COMMAND__META_ID, null, msgs);
      msgs = basicSetMetaID(newMetaID, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.SCRIPT_COMMAND__META_ID, newMetaID, newMetaID));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComplexCommand getCmd()
  {
    return cmd;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCmd(ComplexCommand newCmd, NotificationChain msgs)
  {
    ComplexCommand oldCmd = cmd;
    cmd = newCmd;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.SCRIPT_COMMAND__CMD, oldCmd, newCmd);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCmd(ComplexCommand newCmd)
  {
    if (newCmd != cmd)
    {
      NotificationChain msgs = null;
      if (cmd != null)
        msgs = ((InternalEObject)cmd).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.SCRIPT_COMMAND__CMD, null, msgs);
      if (newCmd != null)
        msgs = ((InternalEObject)newCmd).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.SCRIPT_COMMAND__CMD, null, msgs);
      msgs = basicSetCmd(newCmd, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.SCRIPT_COMMAND__CMD, newCmd, newCmd));
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
      case FMLPackage.SCRIPT_COMMAND__META_ID:
        return basicSetMetaID(null, msgs);
      case FMLPackage.SCRIPT_COMMAND__CMD:
        return basicSetCmd(null, msgs);
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
      case FMLPackage.SCRIPT_COMMAND__VAR:
        return getVar();
      case FMLPackage.SCRIPT_COMMAND__META_ID:
        return getMetaID();
      case FMLPackage.SCRIPT_COMMAND__CMD:
        return getCmd();
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
      case FMLPackage.SCRIPT_COMMAND__VAR:
        setVar((String)newValue);
        return;
      case FMLPackage.SCRIPT_COMMAND__META_ID:
        setMetaID((StringExpr)newValue);
        return;
      case FMLPackage.SCRIPT_COMMAND__CMD:
        setCmd((ComplexCommand)newValue);
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
      case FMLPackage.SCRIPT_COMMAND__VAR:
        setVar(VAR_EDEFAULT);
        return;
      case FMLPackage.SCRIPT_COMMAND__META_ID:
        setMetaID((StringExpr)null);
        return;
      case FMLPackage.SCRIPT_COMMAND__CMD:
        setCmd((ComplexCommand)null);
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
      case FMLPackage.SCRIPT_COMMAND__VAR:
        return VAR_EDEFAULT == null ? var != null : !VAR_EDEFAULT.equals(var);
      case FMLPackage.SCRIPT_COMMAND__META_ID:
        return metaID != null;
      case FMLPackage.SCRIPT_COMMAND__CMD:
        return cmd != null;
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
    result.append(" (var: ");
    result.append(var);
    result.append(')');
    return result.toString();
  }

} //ScriptCommandImpl
