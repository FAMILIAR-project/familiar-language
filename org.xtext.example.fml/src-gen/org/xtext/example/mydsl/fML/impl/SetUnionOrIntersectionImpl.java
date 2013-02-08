/**
 */
package org.xtext.example.mydsl.fML.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.xtext.example.mydsl.fML.FMLPackage;
import org.xtext.example.mydsl.fML.SetCommand;
import org.xtext.example.mydsl.fML.SetUnionOrIntersection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Set Union Or Intersection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.SetUnionOrIntersectionImpl#getOp <em>Op</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.SetUnionOrIntersectionImpl#getSetl <em>Setl</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.SetUnionOrIntersectionImpl#getSetr <em>Setr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SetUnionOrIntersectionImpl extends SetCommandImpl implements SetUnionOrIntersection
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
   * The cached value of the '{@link #getSetr() <em>Setr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSetr()
   * @generated
   * @ordered
   */
  protected SetCommand setr;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SetUnionOrIntersectionImpl()
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
    return FMLPackage.eINSTANCE.getSetUnionOrIntersection();
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
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.SET_UNION_OR_INTERSECTION__OP, oldOp, op));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.SET_UNION_OR_INTERSECTION__SETL, oldSetl, newSetl);
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
        msgs = ((InternalEObject)setl).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.SET_UNION_OR_INTERSECTION__SETL, null, msgs);
      if (newSetl != null)
        msgs = ((InternalEObject)newSetl).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.SET_UNION_OR_INTERSECTION__SETL, null, msgs);
      msgs = basicSetSetl(newSetl, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.SET_UNION_OR_INTERSECTION__SETL, newSetl, newSetl));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SetCommand getSetr()
  {
    return setr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSetr(SetCommand newSetr, NotificationChain msgs)
  {
    SetCommand oldSetr = setr;
    setr = newSetr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.SET_UNION_OR_INTERSECTION__SETR, oldSetr, newSetr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSetr(SetCommand newSetr)
  {
    if (newSetr != setr)
    {
      NotificationChain msgs = null;
      if (setr != null)
        msgs = ((InternalEObject)setr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.SET_UNION_OR_INTERSECTION__SETR, null, msgs);
      if (newSetr != null)
        msgs = ((InternalEObject)newSetr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.SET_UNION_OR_INTERSECTION__SETR, null, msgs);
      msgs = basicSetSetr(newSetr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.SET_UNION_OR_INTERSECTION__SETR, newSetr, newSetr));
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
      case FMLPackage.SET_UNION_OR_INTERSECTION__SETL:
        return basicSetSetl(null, msgs);
      case FMLPackage.SET_UNION_OR_INTERSECTION__SETR:
        return basicSetSetr(null, msgs);
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
      case FMLPackage.SET_UNION_OR_INTERSECTION__OP:
        return getOp();
      case FMLPackage.SET_UNION_OR_INTERSECTION__SETL:
        return getSetl();
      case FMLPackage.SET_UNION_OR_INTERSECTION__SETR:
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
      case FMLPackage.SET_UNION_OR_INTERSECTION__OP:
        setOp((String)newValue);
        return;
      case FMLPackage.SET_UNION_OR_INTERSECTION__SETL:
        setSetl((SetCommand)newValue);
        return;
      case FMLPackage.SET_UNION_OR_INTERSECTION__SETR:
        setSetr((SetCommand)newValue);
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
      case FMLPackage.SET_UNION_OR_INTERSECTION__OP:
        setOp(OP_EDEFAULT);
        return;
      case FMLPackage.SET_UNION_OR_INTERSECTION__SETL:
        setSetl((SetCommand)null);
        return;
      case FMLPackage.SET_UNION_OR_INTERSECTION__SETR:
        setSetr((SetCommand)null);
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
      case FMLPackage.SET_UNION_OR_INTERSECTION__OP:
        return OP_EDEFAULT == null ? op != null : !OP_EDEFAULT.equals(op);
      case FMLPackage.SET_UNION_OR_INTERSECTION__SETL:
        return setl != null;
      case FMLPackage.SET_UNION_OR_INTERSECTION__SETR:
        return setr != null;
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

} //SetUnionOrIntersectionImpl
