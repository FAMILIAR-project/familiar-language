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
import org.xtext.example.mydsl.fML.StringConcat;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>String Concat</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.StringConcatImpl#getLstr <em>Lstr</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.StringConcatImpl#getRstr <em>Rstr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StringConcatImpl extends StrCommandImpl implements StringConcat
{
  /**
   * The cached value of the '{@link #getLstr() <em>Lstr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLstr()
   * @generated
   * @ordered
   */
  protected StrCommand lstr;

  /**
   * The cached value of the '{@link #getRstr() <em>Rstr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRstr()
   * @generated
   * @ordered
   */
  protected StrCommand rstr;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected StringConcatImpl()
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
    return FMLPackage.eINSTANCE.getStringConcat();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StrCommand getLstr()
  {
    return lstr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLstr(StrCommand newLstr, NotificationChain msgs)
  {
    StrCommand oldLstr = lstr;
    lstr = newLstr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.STRING_CONCAT__LSTR, oldLstr, newLstr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLstr(StrCommand newLstr)
  {
    if (newLstr != lstr)
    {
      NotificationChain msgs = null;
      if (lstr != null)
        msgs = ((InternalEObject)lstr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.STRING_CONCAT__LSTR, null, msgs);
      if (newLstr != null)
        msgs = ((InternalEObject)newLstr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.STRING_CONCAT__LSTR, null, msgs);
      msgs = basicSetLstr(newLstr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.STRING_CONCAT__LSTR, newLstr, newLstr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StrCommand getRstr()
  {
    return rstr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRstr(StrCommand newRstr, NotificationChain msgs)
  {
    StrCommand oldRstr = rstr;
    rstr = newRstr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.STRING_CONCAT__RSTR, oldRstr, newRstr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRstr(StrCommand newRstr)
  {
    if (newRstr != rstr)
    {
      NotificationChain msgs = null;
      if (rstr != null)
        msgs = ((InternalEObject)rstr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.STRING_CONCAT__RSTR, null, msgs);
      if (newRstr != null)
        msgs = ((InternalEObject)newRstr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.STRING_CONCAT__RSTR, null, msgs);
      msgs = basicSetRstr(newRstr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.STRING_CONCAT__RSTR, newRstr, newRstr));
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
      case FMLPackage.STRING_CONCAT__LSTR:
        return basicSetLstr(null, msgs);
      case FMLPackage.STRING_CONCAT__RSTR:
        return basicSetRstr(null, msgs);
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
      case FMLPackage.STRING_CONCAT__LSTR:
        return getLstr();
      case FMLPackage.STRING_CONCAT__RSTR:
        return getRstr();
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
      case FMLPackage.STRING_CONCAT__LSTR:
        setLstr((StrCommand)newValue);
        return;
      case FMLPackage.STRING_CONCAT__RSTR:
        setRstr((StrCommand)newValue);
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
      case FMLPackage.STRING_CONCAT__LSTR:
        setLstr((StrCommand)null);
        return;
      case FMLPackage.STRING_CONCAT__RSTR:
        setRstr((StrCommand)null);
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
      case FMLPackage.STRING_CONCAT__LSTR:
        return lstr != null;
      case FMLPackage.STRING_CONCAT__RSTR:
        return rstr != null;
    }
    return super.eIsSet(featureID);
  }

} //StringConcatImpl
