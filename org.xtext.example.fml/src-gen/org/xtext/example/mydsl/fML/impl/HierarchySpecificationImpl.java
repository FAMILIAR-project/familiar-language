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
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.xtext.example.mydsl.fML.FMLPackage;
import org.xtext.example.mydsl.fML.HProduction;
import org.xtext.example.mydsl.fML.Hierarchy;
import org.xtext.example.mydsl.fML.HierarchySpecification;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Hierarchy Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.HierarchySpecificationImpl#getHierarchy <em>Hierarchy</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.HierarchySpecificationImpl#getFeatures <em>Features</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HierarchySpecificationImpl extends MinimalEObjectImpl.Container implements HierarchySpecification
{
  /**
   * The cached value of the '{@link #getHierarchy() <em>Hierarchy</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHierarchy()
   * @generated
   * @ordered
   */
  protected Hierarchy hierarchy;

  /**
   * The cached value of the '{@link #getFeatures() <em>Features</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeatures()
   * @generated
   * @ordered
   */
  protected EList<HProduction> features;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected HierarchySpecificationImpl()
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
    return FMLPackage.eINSTANCE.getHierarchySpecification();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Hierarchy getHierarchy()
  {
    return hierarchy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetHierarchy(Hierarchy newHierarchy, NotificationChain msgs)
  {
    Hierarchy oldHierarchy = hierarchy;
    hierarchy = newHierarchy;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.HIERARCHY_SPECIFICATION__HIERARCHY, oldHierarchy, newHierarchy);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHierarchy(Hierarchy newHierarchy)
  {
    if (newHierarchy != hierarchy)
    {
      NotificationChain msgs = null;
      if (hierarchy != null)
        msgs = ((InternalEObject)hierarchy).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.HIERARCHY_SPECIFICATION__HIERARCHY, null, msgs);
      if (newHierarchy != null)
        msgs = ((InternalEObject)newHierarchy).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.HIERARCHY_SPECIFICATION__HIERARCHY, null, msgs);
      msgs = basicSetHierarchy(newHierarchy, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.HIERARCHY_SPECIFICATION__HIERARCHY, newHierarchy, newHierarchy));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<HProduction> getFeatures()
  {
    if (features == null)
    {
      features = new EObjectContainmentEList<HProduction>(HProduction.class, this, FMLPackage.HIERARCHY_SPECIFICATION__FEATURES);
    }
    return features;
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
      case FMLPackage.HIERARCHY_SPECIFICATION__HIERARCHY:
        return basicSetHierarchy(null, msgs);
      case FMLPackage.HIERARCHY_SPECIFICATION__FEATURES:
        return ((InternalEList<?>)getFeatures()).basicRemove(otherEnd, msgs);
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
      case FMLPackage.HIERARCHY_SPECIFICATION__HIERARCHY:
        return getHierarchy();
      case FMLPackage.HIERARCHY_SPECIFICATION__FEATURES:
        return getFeatures();
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
      case FMLPackage.HIERARCHY_SPECIFICATION__HIERARCHY:
        setHierarchy((Hierarchy)newValue);
        return;
      case FMLPackage.HIERARCHY_SPECIFICATION__FEATURES:
        getFeatures().clear();
        getFeatures().addAll((Collection<? extends HProduction>)newValue);
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
      case FMLPackage.HIERARCHY_SPECIFICATION__HIERARCHY:
        setHierarchy((Hierarchy)null);
        return;
      case FMLPackage.HIERARCHY_SPECIFICATION__FEATURES:
        getFeatures().clear();
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
      case FMLPackage.HIERARCHY_SPECIFICATION__HIERARCHY:
        return hierarchy != null;
      case FMLPackage.HIERARCHY_SPECIFICATION__FEATURES:
        return features != null && !features.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //HierarchySpecificationImpl
