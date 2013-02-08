/**
 */
package org.xtext.example.mydsl.fML.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.xtext.example.mydsl.fML.FMLPackage;
import org.xtext.example.mydsl.fML.IntegerCommand;
import org.xtext.example.mydsl.fML.StrCommand;
import org.xtext.example.mydsl.fML.StringSubstring;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>String Substring</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.StringSubstringImpl#getStr <em>Str</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.StringSubstringImpl#getBegin <em>Begin</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.StringSubstringImpl#getEnd <em>End</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StringSubstringImpl extends StrCommandImpl implements StringSubstring
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
   * The cached value of the '{@link #getBegin() <em>Begin</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBegin()
   * @generated
   * @ordered
   */
  protected IntegerCommand begin;

  /**
   * The cached value of the '{@link #getEnd() <em>End</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEnd()
   * @generated
   * @ordered
   */
  protected IntegerCommand end;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected StringSubstringImpl()
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
    return FMLPackage.eINSTANCE.getStringSubstring();
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.STRING_SUBSTRING__STR, oldStr, newStr);
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
        msgs = ((InternalEObject)str).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.STRING_SUBSTRING__STR, null, msgs);
      if (newStr != null)
        msgs = ((InternalEObject)newStr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.STRING_SUBSTRING__STR, null, msgs);
      msgs = basicSetStr(newStr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.STRING_SUBSTRING__STR, newStr, newStr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntegerCommand getBegin()
  {
    return begin;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBegin(IntegerCommand newBegin, NotificationChain msgs)
  {
    IntegerCommand oldBegin = begin;
    begin = newBegin;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.STRING_SUBSTRING__BEGIN, oldBegin, newBegin);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBegin(IntegerCommand newBegin)
  {
    if (newBegin != begin)
    {
      NotificationChain msgs = null;
      if (begin != null)
        msgs = ((InternalEObject)begin).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.STRING_SUBSTRING__BEGIN, null, msgs);
      if (newBegin != null)
        msgs = ((InternalEObject)newBegin).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.STRING_SUBSTRING__BEGIN, null, msgs);
      msgs = basicSetBegin(newBegin, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.STRING_SUBSTRING__BEGIN, newBegin, newBegin));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntegerCommand getEnd()
  {
    return end;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetEnd(IntegerCommand newEnd, NotificationChain msgs)
  {
    IntegerCommand oldEnd = end;
    end = newEnd;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.STRING_SUBSTRING__END, oldEnd, newEnd);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEnd(IntegerCommand newEnd)
  {
    if (newEnd != end)
    {
      NotificationChain msgs = null;
      if (end != null)
        msgs = ((InternalEObject)end).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.STRING_SUBSTRING__END, null, msgs);
      if (newEnd != null)
        msgs = ((InternalEObject)newEnd).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.STRING_SUBSTRING__END, null, msgs);
      msgs = basicSetEnd(newEnd, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.STRING_SUBSTRING__END, newEnd, newEnd));
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
      case FMLPackage.STRING_SUBSTRING__STR:
        return basicSetStr(null, msgs);
      case FMLPackage.STRING_SUBSTRING__BEGIN:
        return basicSetBegin(null, msgs);
      case FMLPackage.STRING_SUBSTRING__END:
        return basicSetEnd(null, msgs);
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
      case FMLPackage.STRING_SUBSTRING__STR:
        return getStr();
      case FMLPackage.STRING_SUBSTRING__BEGIN:
        return getBegin();
      case FMLPackage.STRING_SUBSTRING__END:
        return getEnd();
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
      case FMLPackage.STRING_SUBSTRING__STR:
        setStr((StrCommand)newValue);
        return;
      case FMLPackage.STRING_SUBSTRING__BEGIN:
        setBegin((IntegerCommand)newValue);
        return;
      case FMLPackage.STRING_SUBSTRING__END:
        setEnd((IntegerCommand)newValue);
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
      case FMLPackage.STRING_SUBSTRING__STR:
        setStr((StrCommand)null);
        return;
      case FMLPackage.STRING_SUBSTRING__BEGIN:
        setBegin((IntegerCommand)null);
        return;
      case FMLPackage.STRING_SUBSTRING__END:
        setEnd((IntegerCommand)null);
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
      case FMLPackage.STRING_SUBSTRING__STR:
        return str != null;
      case FMLPackage.STRING_SUBSTRING__BEGIN:
        return begin != null;
      case FMLPackage.STRING_SUBSTRING__END:
        return end != null;
    }
    return super.eIsSet(featureID);
  }

} //StringSubstringImpl
