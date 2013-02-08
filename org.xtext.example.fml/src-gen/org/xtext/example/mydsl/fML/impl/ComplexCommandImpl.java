/**
 */
package org.xtext.example.mydsl.fML.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.ComplexCommand;
import org.xtext.example.mydsl.fML.FMLPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Complex Command</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.ComplexCommandImpl#getLeft <em>Left</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.ComplexCommandImpl#isNot <em>Not</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.ComplexCommandImpl#getBatom <em>Batom</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComplexCommandImpl extends ScriptCommandImpl implements ComplexCommand
{
  /**
   * The cached value of the '{@link #getLeft() <em>Left</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLeft()
   * @generated
   * @ordered
   */
  protected Command left;

  /**
   * The default value of the '{@link #isNot() <em>Not</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isNot()
   * @generated
   * @ordered
   */
  protected static final boolean NOT_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isNot() <em>Not</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isNot()
   * @generated
   * @ordered
   */
  protected boolean not = NOT_EDEFAULT;

  /**
   * The cached value of the '{@link #getBatom() <em>Batom</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBatom()
   * @generated
   * @ordered
   */
  protected ComplexCommand batom;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ComplexCommandImpl()
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
    return FMLPackage.eINSTANCE.getComplexCommand();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Command getLeft()
  {
    return left;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLeft(Command newLeft, NotificationChain msgs)
  {
    Command oldLeft = left;
    left = newLeft;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.COMPLEX_COMMAND__LEFT, oldLeft, newLeft);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLeft(Command newLeft)
  {
    if (newLeft != left)
    {
      NotificationChain msgs = null;
      if (left != null)
        msgs = ((InternalEObject)left).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.COMPLEX_COMMAND__LEFT, null, msgs);
      if (newLeft != null)
        msgs = ((InternalEObject)newLeft).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.COMPLEX_COMMAND__LEFT, null, msgs);
      msgs = basicSetLeft(newLeft, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.COMPLEX_COMMAND__LEFT, newLeft, newLeft));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isNot()
  {
    return not;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNot(boolean newNot)
  {
    boolean oldNot = not;
    not = newNot;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.COMPLEX_COMMAND__NOT, oldNot, not));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComplexCommand getBatom()
  {
    return batom;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBatom(ComplexCommand newBatom, NotificationChain msgs)
  {
    ComplexCommand oldBatom = batom;
    batom = newBatom;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.COMPLEX_COMMAND__BATOM, oldBatom, newBatom);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBatom(ComplexCommand newBatom)
  {
    if (newBatom != batom)
    {
      NotificationChain msgs = null;
      if (batom != null)
        msgs = ((InternalEObject)batom).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.COMPLEX_COMMAND__BATOM, null, msgs);
      if (newBatom != null)
        msgs = ((InternalEObject)newBatom).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.COMPLEX_COMMAND__BATOM, null, msgs);
      msgs = basicSetBatom(newBatom, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.COMPLEX_COMMAND__BATOM, newBatom, newBatom));
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
      case FMLPackage.COMPLEX_COMMAND__LEFT:
        return basicSetLeft(null, msgs);
      case FMLPackage.COMPLEX_COMMAND__BATOM:
        return basicSetBatom(null, msgs);
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
      case FMLPackage.COMPLEX_COMMAND__LEFT:
        return getLeft();
      case FMLPackage.COMPLEX_COMMAND__NOT:
        return isNot();
      case FMLPackage.COMPLEX_COMMAND__BATOM:
        return getBatom();
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
      case FMLPackage.COMPLEX_COMMAND__LEFT:
        setLeft((Command)newValue);
        return;
      case FMLPackage.COMPLEX_COMMAND__NOT:
        setNot((Boolean)newValue);
        return;
      case FMLPackage.COMPLEX_COMMAND__BATOM:
        setBatom((ComplexCommand)newValue);
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
      case FMLPackage.COMPLEX_COMMAND__LEFT:
        setLeft((Command)null);
        return;
      case FMLPackage.COMPLEX_COMMAND__NOT:
        setNot(NOT_EDEFAULT);
        return;
      case FMLPackage.COMPLEX_COMMAND__BATOM:
        setBatom((ComplexCommand)null);
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
      case FMLPackage.COMPLEX_COMMAND__LEFT:
        return left != null;
      case FMLPackage.COMPLEX_COMMAND__NOT:
        return not != NOT_EDEFAULT;
      case FMLPackage.COMPLEX_COMMAND__BATOM:
        return batom != null;
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
    result.append(" (not: ");
    result.append(not);
    result.append(')');
    return result.toString();
  }

} //ComplexCommandImpl
