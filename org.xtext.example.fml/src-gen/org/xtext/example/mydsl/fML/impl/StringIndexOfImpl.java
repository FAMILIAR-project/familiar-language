/**
 */
package org.xtext.example.mydsl.fML.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.xtext.example.mydsl.fML.FMLPackage;
import org.xtext.example.mydsl.fML.StrCommand;
import org.xtext.example.mydsl.fML.StringIndexOf;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>String Index Of</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.StringIndexOfImpl#getStr <em>Str</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.StringIndexOfImpl#getSchar <em>Schar</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StringIndexOfImpl extends IntegerCommandImpl implements StringIndexOf
{
  /**
   * The cached value of the '{@link #getStr() <em>Str</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStr()
   * @generated
   * @ordered
   */
  protected StrCommand str;

  /**
   * The cached value of the '{@link #getSchar() <em>Schar</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSchar()
   * @generated
   * @ordered
   */
  protected StrCommand schar;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected StringIndexOfImpl()
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
    return FMLPackage.eINSTANCE.getStringIndexOf();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StrCommand getStr()
  {
    return str;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetStr(StrCommand newStr, NotificationChain msgs)
  {
    StrCommand oldStr = str;
    str = newStr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.STRING_INDEX_OF__STR, oldStr, newStr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStr(StrCommand newStr)
  {
    if (newStr != str)
    {
      NotificationChain msgs = null;
      if (str != null)
        msgs = ((InternalEObject)str).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.STRING_INDEX_OF__STR, null, msgs);
      if (newStr != null)
        msgs = ((InternalEObject)newStr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.STRING_INDEX_OF__STR, null, msgs);
      msgs = basicSetStr(newStr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.STRING_INDEX_OF__STR, newStr, newStr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StrCommand getSchar()
  {
    return schar;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSchar(StrCommand newSchar, NotificationChain msgs)
  {
    StrCommand oldSchar = schar;
    schar = newSchar;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.STRING_INDEX_OF__SCHAR, oldSchar, newSchar);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSchar(StrCommand newSchar)
  {
    if (newSchar != schar)
    {
      NotificationChain msgs = null;
      if (schar != null)
        msgs = ((InternalEObject)schar).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.STRING_INDEX_OF__SCHAR, null, msgs);
      if (newSchar != null)
        msgs = ((InternalEObject)newSchar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.STRING_INDEX_OF__SCHAR, null, msgs);
      msgs = basicSetSchar(newSchar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.STRING_INDEX_OF__SCHAR, newSchar, newSchar));
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
      case FMLPackage.STRING_INDEX_OF__STR:
        return basicSetStr(null, msgs);
      case FMLPackage.STRING_INDEX_OF__SCHAR:
        return basicSetSchar(null, msgs);
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
      case FMLPackage.STRING_INDEX_OF__STR:
        return getStr();
      case FMLPackage.STRING_INDEX_OF__SCHAR:
        return getSchar();
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
      case FMLPackage.STRING_INDEX_OF__STR:
        setStr((StrCommand)newValue);
        return;
      case FMLPackage.STRING_INDEX_OF__SCHAR:
        setSchar((StrCommand)newValue);
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
      case FMLPackage.STRING_INDEX_OF__STR:
        setStr((StrCommand)null);
        return;
      case FMLPackage.STRING_INDEX_OF__SCHAR:
        setSchar((StrCommand)null);
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
      case FMLPackage.STRING_INDEX_OF__STR:
        return str != null;
      case FMLPackage.STRING_INDEX_OF__SCHAR:
        return schar != null;
    }
    return super.eIsSet(featureID);
  }

} //StringIndexOfImpl
