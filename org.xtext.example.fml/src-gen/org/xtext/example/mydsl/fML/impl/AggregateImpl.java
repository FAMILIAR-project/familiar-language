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

import org.xtext.example.mydsl.fML.Aggregate;
import org.xtext.example.mydsl.fML.FMCommand;
import org.xtext.example.mydsl.fML.FMLPackage;
import org.xtext.example.mydsl.fML.IdentifierExpr;
import org.xtext.example.mydsl.fML.SetCommand;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Aggregate</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.AggregateImpl#isRenamings <em>Renamings</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.AggregateImpl#getFms <em>Fms</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.AggregateImpl#getSfms <em>Sfms</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.AggregateImpl#getMapping <em>Mapping</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AggregateImpl extends CommandImpl implements Aggregate
{
  /**
   * The default value of the '{@link #isRenamings() <em>Renamings</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isRenamings()
   * @generated
   * @ordered
   */
  protected static final boolean RENAMINGS_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isRenamings() <em>Renamings</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isRenamings()
   * @generated
   * @ordered
   */
  protected boolean renamings = RENAMINGS_EDEFAULT;

  /**
   * The cached value of the '{@link #getFms() <em>Fms</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFms()
   * @generated
   * @ordered
   */
  protected EList<FMCommand> fms;

  /**
   * The cached value of the '{@link #getSfms() <em>Sfms</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSfms()
   * @generated
   * @ordered
   */
  protected IdentifierExpr sfms;

  /**
   * The cached value of the '{@link #getMapping() <em>Mapping</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMapping()
   * @generated
   * @ordered
   */
  protected SetCommand mapping;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AggregateImpl()
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
    return FMLPackage.eINSTANCE.getAggregate();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isRenamings()
  {
    return renamings;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRenamings(boolean newRenamings)
  {
    boolean oldRenamings = renamings;
    renamings = newRenamings;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.AGGREGATE__RENAMINGS, oldRenamings, renamings));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<FMCommand> getFms()
  {
    if (fms == null)
    {
      fms = new EObjectContainmentEList<FMCommand>(FMCommand.class, this, FMLPackage.AGGREGATE__FMS);
    }
    return fms;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IdentifierExpr getSfms()
  {
    return sfms;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSfms(IdentifierExpr newSfms, NotificationChain msgs)
  {
    IdentifierExpr oldSfms = sfms;
    sfms = newSfms;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.AGGREGATE__SFMS, oldSfms, newSfms);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSfms(IdentifierExpr newSfms)
  {
    if (newSfms != sfms)
    {
      NotificationChain msgs = null;
      if (sfms != null)
        msgs = ((InternalEObject)sfms).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.AGGREGATE__SFMS, null, msgs);
      if (newSfms != null)
        msgs = ((InternalEObject)newSfms).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.AGGREGATE__SFMS, null, msgs);
      msgs = basicSetSfms(newSfms, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.AGGREGATE__SFMS, newSfms, newSfms));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SetCommand getMapping()
  {
    return mapping;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMapping(SetCommand newMapping, NotificationChain msgs)
  {
    SetCommand oldMapping = mapping;
    mapping = newMapping;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.AGGREGATE__MAPPING, oldMapping, newMapping);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMapping(SetCommand newMapping)
  {
    if (newMapping != mapping)
    {
      NotificationChain msgs = null;
      if (mapping != null)
        msgs = ((InternalEObject)mapping).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.AGGREGATE__MAPPING, null, msgs);
      if (newMapping != null)
        msgs = ((InternalEObject)newMapping).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.AGGREGATE__MAPPING, null, msgs);
      msgs = basicSetMapping(newMapping, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.AGGREGATE__MAPPING, newMapping, newMapping));
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
      case FMLPackage.AGGREGATE__FMS:
        return ((InternalEList<?>)getFms()).basicRemove(otherEnd, msgs);
      case FMLPackage.AGGREGATE__SFMS:
        return basicSetSfms(null, msgs);
      case FMLPackage.AGGREGATE__MAPPING:
        return basicSetMapping(null, msgs);
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
      case FMLPackage.AGGREGATE__RENAMINGS:
        return isRenamings();
      case FMLPackage.AGGREGATE__FMS:
        return getFms();
      case FMLPackage.AGGREGATE__SFMS:
        return getSfms();
      case FMLPackage.AGGREGATE__MAPPING:
        return getMapping();
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
      case FMLPackage.AGGREGATE__RENAMINGS:
        setRenamings((Boolean)newValue);
        return;
      case FMLPackage.AGGREGATE__FMS:
        getFms().clear();
        getFms().addAll((Collection<? extends FMCommand>)newValue);
        return;
      case FMLPackage.AGGREGATE__SFMS:
        setSfms((IdentifierExpr)newValue);
        return;
      case FMLPackage.AGGREGATE__MAPPING:
        setMapping((SetCommand)newValue);
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
      case FMLPackage.AGGREGATE__RENAMINGS:
        setRenamings(RENAMINGS_EDEFAULT);
        return;
      case FMLPackage.AGGREGATE__FMS:
        getFms().clear();
        return;
      case FMLPackage.AGGREGATE__SFMS:
        setSfms((IdentifierExpr)null);
        return;
      case FMLPackage.AGGREGATE__MAPPING:
        setMapping((SetCommand)null);
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
      case FMLPackage.AGGREGATE__RENAMINGS:
        return renamings != RENAMINGS_EDEFAULT;
      case FMLPackage.AGGREGATE__FMS:
        return fms != null && !fms.isEmpty();
      case FMLPackage.AGGREGATE__SFMS:
        return sfms != null;
      case FMLPackage.AGGREGATE__MAPPING:
        return mapping != null;
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
    result.append(" (renamings: ");
    result.append(renamings);
    result.append(')');
    return result.toString();
  }

} //AggregateImpl
