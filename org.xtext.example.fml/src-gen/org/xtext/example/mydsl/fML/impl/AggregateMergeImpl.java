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

import org.xtext.example.mydsl.fML.AggregateMerge;
import org.xtext.example.mydsl.fML.FMCommand;
import org.xtext.example.mydsl.fML.FMLPackage;
import org.xtext.example.mydsl.fML.HierarchyStrategy;
import org.xtext.example.mydsl.fML.LFMArgs;
import org.xtext.example.mydsl.fML.MergeMode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Aggregate Merge</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.AggregateMergeImpl#isHierarchySpecified <em>Hierarchy Specified</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.AggregateMergeImpl#getHierarchyStrategy <em>Hierarchy Strategy</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.AggregateMergeImpl#getMode <em>Mode</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.AggregateMergeImpl#getLfms <em>Lfms</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.AggregateMergeImpl#getFms <em>Fms</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AggregateMergeImpl extends CommandImpl implements AggregateMerge
{
  /**
   * The default value of the '{@link #isHierarchySpecified() <em>Hierarchy Specified</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isHierarchySpecified()
   * @generated
   * @ordered
   */
  protected static final boolean HIERARCHY_SPECIFIED_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isHierarchySpecified() <em>Hierarchy Specified</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isHierarchySpecified()
   * @generated
   * @ordered
   */
  protected boolean hierarchySpecified = HIERARCHY_SPECIFIED_EDEFAULT;

  /**
   * The default value of the '{@link #getHierarchyStrategy() <em>Hierarchy Strategy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHierarchyStrategy()
   * @generated
   * @ordered
   */
  protected static final HierarchyStrategy HIERARCHY_STRATEGY_EDEFAULT = HierarchyStrategy.BASIC;

  /**
   * The cached value of the '{@link #getHierarchyStrategy() <em>Hierarchy Strategy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHierarchyStrategy()
   * @generated
   * @ordered
   */
  protected HierarchyStrategy hierarchyStrategy = HIERARCHY_STRATEGY_EDEFAULT;

  /**
   * The default value of the '{@link #getMode() <em>Mode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMode()
   * @generated
   * @ordered
   */
  protected static final MergeMode MODE_EDEFAULT = MergeMode.CROSS;

  /**
   * The cached value of the '{@link #getMode() <em>Mode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMode()
   * @generated
   * @ordered
   */
  protected MergeMode mode = MODE_EDEFAULT;

  /**
   * The cached value of the '{@link #getLfms() <em>Lfms</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLfms()
   * @generated
   * @ordered
   */
  protected EList<FMCommand> lfms;

  /**
   * The cached value of the '{@link #getFms() <em>Fms</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFms()
   * @generated
   * @ordered
   */
  protected LFMArgs fms;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AggregateMergeImpl()
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
    return FMLPackage.eINSTANCE.getAggregateMerge();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isHierarchySpecified()
  {
    return hierarchySpecified;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHierarchySpecified(boolean newHierarchySpecified)
  {
    boolean oldHierarchySpecified = hierarchySpecified;
    hierarchySpecified = newHierarchySpecified;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.AGGREGATE_MERGE__HIERARCHY_SPECIFIED, oldHierarchySpecified, hierarchySpecified));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public HierarchyStrategy getHierarchyStrategy()
  {
    return hierarchyStrategy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHierarchyStrategy(HierarchyStrategy newHierarchyStrategy)
  {
    HierarchyStrategy oldHierarchyStrategy = hierarchyStrategy;
    hierarchyStrategy = newHierarchyStrategy == null ? HIERARCHY_STRATEGY_EDEFAULT : newHierarchyStrategy;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.AGGREGATE_MERGE__HIERARCHY_STRATEGY, oldHierarchyStrategy, hierarchyStrategy));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MergeMode getMode()
  {
    return mode;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMode(MergeMode newMode)
  {
    MergeMode oldMode = mode;
    mode = newMode == null ? MODE_EDEFAULT : newMode;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.AGGREGATE_MERGE__MODE, oldMode, mode));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<FMCommand> getLfms()
  {
    if (lfms == null)
    {
      lfms = new EObjectContainmentEList<FMCommand>(FMCommand.class, this, FMLPackage.AGGREGATE_MERGE__LFMS);
    }
    return lfms;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LFMArgs getFms()
  {
    return fms;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFms(LFMArgs newFms, NotificationChain msgs)
  {
    LFMArgs oldFms = fms;
    fms = newFms;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.AGGREGATE_MERGE__FMS, oldFms, newFms);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFms(LFMArgs newFms)
  {
    if (newFms != fms)
    {
      NotificationChain msgs = null;
      if (fms != null)
        msgs = ((InternalEObject)fms).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.AGGREGATE_MERGE__FMS, null, msgs);
      if (newFms != null)
        msgs = ((InternalEObject)newFms).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.AGGREGATE_MERGE__FMS, null, msgs);
      msgs = basicSetFms(newFms, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.AGGREGATE_MERGE__FMS, newFms, newFms));
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
      case FMLPackage.AGGREGATE_MERGE__LFMS:
        return ((InternalEList<?>)getLfms()).basicRemove(otherEnd, msgs);
      case FMLPackage.AGGREGATE_MERGE__FMS:
        return basicSetFms(null, msgs);
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
      case FMLPackage.AGGREGATE_MERGE__HIERARCHY_SPECIFIED:
        return isHierarchySpecified();
      case FMLPackage.AGGREGATE_MERGE__HIERARCHY_STRATEGY:
        return getHierarchyStrategy();
      case FMLPackage.AGGREGATE_MERGE__MODE:
        return getMode();
      case FMLPackage.AGGREGATE_MERGE__LFMS:
        return getLfms();
      case FMLPackage.AGGREGATE_MERGE__FMS:
        return getFms();
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
      case FMLPackage.AGGREGATE_MERGE__HIERARCHY_SPECIFIED:
        setHierarchySpecified((Boolean)newValue);
        return;
      case FMLPackage.AGGREGATE_MERGE__HIERARCHY_STRATEGY:
        setHierarchyStrategy((HierarchyStrategy)newValue);
        return;
      case FMLPackage.AGGREGATE_MERGE__MODE:
        setMode((MergeMode)newValue);
        return;
      case FMLPackage.AGGREGATE_MERGE__LFMS:
        getLfms().clear();
        getLfms().addAll((Collection<? extends FMCommand>)newValue);
        return;
      case FMLPackage.AGGREGATE_MERGE__FMS:
        setFms((LFMArgs)newValue);
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
      case FMLPackage.AGGREGATE_MERGE__HIERARCHY_SPECIFIED:
        setHierarchySpecified(HIERARCHY_SPECIFIED_EDEFAULT);
        return;
      case FMLPackage.AGGREGATE_MERGE__HIERARCHY_STRATEGY:
        setHierarchyStrategy(HIERARCHY_STRATEGY_EDEFAULT);
        return;
      case FMLPackage.AGGREGATE_MERGE__MODE:
        setMode(MODE_EDEFAULT);
        return;
      case FMLPackage.AGGREGATE_MERGE__LFMS:
        getLfms().clear();
        return;
      case FMLPackage.AGGREGATE_MERGE__FMS:
        setFms((LFMArgs)null);
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
      case FMLPackage.AGGREGATE_MERGE__HIERARCHY_SPECIFIED:
        return hierarchySpecified != HIERARCHY_SPECIFIED_EDEFAULT;
      case FMLPackage.AGGREGATE_MERGE__HIERARCHY_STRATEGY:
        return hierarchyStrategy != HIERARCHY_STRATEGY_EDEFAULT;
      case FMLPackage.AGGREGATE_MERGE__MODE:
        return mode != MODE_EDEFAULT;
      case FMLPackage.AGGREGATE_MERGE__LFMS:
        return lfms != null && !lfms.isEmpty();
      case FMLPackage.AGGREGATE_MERGE__FMS:
        return fms != null;
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
    result.append(" (hierarchySpecified: ");
    result.append(hierarchySpecified);
    result.append(", hierarchyStrategy: ");
    result.append(hierarchyStrategy);
    result.append(", mode: ");
    result.append(mode);
    result.append(')');
    return result.toString();
  }

} //AggregateMergeImpl
