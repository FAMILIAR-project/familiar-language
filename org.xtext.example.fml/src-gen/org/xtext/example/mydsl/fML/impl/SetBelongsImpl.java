/**
 */
package org.xtext.example.mydsl.fML.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.xtext.example.mydsl.fML.FMLPackage;
import org.xtext.example.mydsl.fML.SetBelongs;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Set Belongs</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.SetBelongsImpl#getSetl <em>Setl</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.SetBelongsImpl#getSetr <em>Setr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SetBelongsImpl extends BCommandImpl implements SetBelongs
{
  /**
   * The default value of the '{@link #getSetl() <em>Setl</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSetl()
   * @generated
   * @ordered
   */
  protected static final String SETL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSetl() <em>Setl</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSetl()
   * @generated
   * @ordered
   */
  protected String setl = SETL_EDEFAULT;

  /**
   * The default value of the '{@link #getSetr() <em>Setr</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSetr()
   * @generated
   * @ordered
   */
  protected static final String SETR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSetr() <em>Setr</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSetr()
   * @generated
   * @ordered
   */
  protected String setr = SETR_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SetBelongsImpl()
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
    return FMLPackage.eINSTANCE.getSetBelongs();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSetl()
  {
    return setl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSetl(String newSetl)
  {
    String oldSetl = setl;
    setl = newSetl;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.SET_BELONGS__SETL, oldSetl, setl));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSetr()
  {
    return setr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSetr(String newSetr)
  {
    String oldSetr = setr;
    setr = newSetr;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.SET_BELONGS__SETR, oldSetr, setr));
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
      case FMLPackage.SET_BELONGS__SETL:
        return getSetl();
      case FMLPackage.SET_BELONGS__SETR:
        return getSetr();
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
      case FMLPackage.SET_BELONGS__SETL:
        setSetl((String)newValue);
        return;
      case FMLPackage.SET_BELONGS__SETR:
        setSetr((String)newValue);
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
      case FMLPackage.SET_BELONGS__SETL:
        setSetl(SETL_EDEFAULT);
        return;
      case FMLPackage.SET_BELONGS__SETR:
        setSetr(SETR_EDEFAULT);
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
      case FMLPackage.SET_BELONGS__SETL:
        return SETL_EDEFAULT == null ? setl != null : !SETL_EDEFAULT.equals(setl);
      case FMLPackage.SET_BELONGS__SETR:
        return SETR_EDEFAULT == null ? setr != null : !SETR_EDEFAULT.equals(setr);
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
    result.append(" (setl: ");
    result.append(setl);
    result.append(", setr: ");
    result.append(setr);
    result.append(')');
    return result.toString();
  }

} //SetBelongsImpl
