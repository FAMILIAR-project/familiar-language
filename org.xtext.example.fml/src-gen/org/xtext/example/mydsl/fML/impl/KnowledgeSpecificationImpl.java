/**
 */
package org.xtext.example.mydsl.fML.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.xtext.example.mydsl.fML.ConstraintsSpecification;
import org.xtext.example.mydsl.fML.FMLPackage;
import org.xtext.example.mydsl.fML.GroupsSpecification;
import org.xtext.example.mydsl.fML.HierarchySpecification;
import org.xtext.example.mydsl.fML.KnowledgeSpecification;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Knowledge Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.KnowledgeSpecificationImpl#getHierarchy <em>Hierarchy</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.KnowledgeSpecificationImpl#getGroups <em>Groups</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.KnowledgeSpecificationImpl#getConstraints <em>Constraints</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KnowledgeSpecificationImpl extends MinimalEObjectImpl.Container implements KnowledgeSpecification
{
  /**
   * The cached value of the '{@link #getHierarchy() <em>Hierarchy</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHierarchy()
   * @generated
   * @ordered
   */
  protected HierarchySpecification hierarchy;

  /**
   * The cached value of the '{@link #getGroups() <em>Groups</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGroups()
   * @generated
   * @ordered
   */
  protected GroupsSpecification groups;

  /**
   * The cached value of the '{@link #getConstraints() <em>Constraints</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstraints()
   * @generated
   * @ordered
   */
  protected ConstraintsSpecification constraints;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected KnowledgeSpecificationImpl()
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
    return FMLPackage.eINSTANCE.getKnowledgeSpecification();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public HierarchySpecification getHierarchy()
  {
    return hierarchy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetHierarchy(HierarchySpecification newHierarchy, NotificationChain msgs)
  {
    HierarchySpecification oldHierarchy = hierarchy;
    hierarchy = newHierarchy;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.KNOWLEDGE_SPECIFICATION__HIERARCHY, oldHierarchy, newHierarchy);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHierarchy(HierarchySpecification newHierarchy)
  {
    if (newHierarchy != hierarchy)
    {
      NotificationChain msgs = null;
      if (hierarchy != null)
        msgs = ((InternalEObject)hierarchy).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.KNOWLEDGE_SPECIFICATION__HIERARCHY, null, msgs);
      if (newHierarchy != null)
        msgs = ((InternalEObject)newHierarchy).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.KNOWLEDGE_SPECIFICATION__HIERARCHY, null, msgs);
      msgs = basicSetHierarchy(newHierarchy, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.KNOWLEDGE_SPECIFICATION__HIERARCHY, newHierarchy, newHierarchy));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GroupsSpecification getGroups()
  {
    return groups;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetGroups(GroupsSpecification newGroups, NotificationChain msgs)
  {
    GroupsSpecification oldGroups = groups;
    groups = newGroups;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.KNOWLEDGE_SPECIFICATION__GROUPS, oldGroups, newGroups);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGroups(GroupsSpecification newGroups)
  {
    if (newGroups != groups)
    {
      NotificationChain msgs = null;
      if (groups != null)
        msgs = ((InternalEObject)groups).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.KNOWLEDGE_SPECIFICATION__GROUPS, null, msgs);
      if (newGroups != null)
        msgs = ((InternalEObject)newGroups).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.KNOWLEDGE_SPECIFICATION__GROUPS, null, msgs);
      msgs = basicSetGroups(newGroups, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.KNOWLEDGE_SPECIFICATION__GROUPS, newGroups, newGroups));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConstraintsSpecification getConstraints()
  {
    return constraints;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetConstraints(ConstraintsSpecification newConstraints, NotificationChain msgs)
  {
    ConstraintsSpecification oldConstraints = constraints;
    constraints = newConstraints;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.KNOWLEDGE_SPECIFICATION__CONSTRAINTS, oldConstraints, newConstraints);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConstraints(ConstraintsSpecification newConstraints)
  {
    if (newConstraints != constraints)
    {
      NotificationChain msgs = null;
      if (constraints != null)
        msgs = ((InternalEObject)constraints).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.KNOWLEDGE_SPECIFICATION__CONSTRAINTS, null, msgs);
      if (newConstraints != null)
        msgs = ((InternalEObject)newConstraints).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.KNOWLEDGE_SPECIFICATION__CONSTRAINTS, null, msgs);
      msgs = basicSetConstraints(newConstraints, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.KNOWLEDGE_SPECIFICATION__CONSTRAINTS, newConstraints, newConstraints));
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
      case FMLPackage.KNOWLEDGE_SPECIFICATION__HIERARCHY:
        return basicSetHierarchy(null, msgs);
      case FMLPackage.KNOWLEDGE_SPECIFICATION__GROUPS:
        return basicSetGroups(null, msgs);
      case FMLPackage.KNOWLEDGE_SPECIFICATION__CONSTRAINTS:
        return basicSetConstraints(null, msgs);
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
      case FMLPackage.KNOWLEDGE_SPECIFICATION__HIERARCHY:
        return getHierarchy();
      case FMLPackage.KNOWLEDGE_SPECIFICATION__GROUPS:
        return getGroups();
      case FMLPackage.KNOWLEDGE_SPECIFICATION__CONSTRAINTS:
        return getConstraints();
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
      case FMLPackage.KNOWLEDGE_SPECIFICATION__HIERARCHY:
        setHierarchy((HierarchySpecification)newValue);
        return;
      case FMLPackage.KNOWLEDGE_SPECIFICATION__GROUPS:
        setGroups((GroupsSpecification)newValue);
        return;
      case FMLPackage.KNOWLEDGE_SPECIFICATION__CONSTRAINTS:
        setConstraints((ConstraintsSpecification)newValue);
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
      case FMLPackage.KNOWLEDGE_SPECIFICATION__HIERARCHY:
        setHierarchy((HierarchySpecification)null);
        return;
      case FMLPackage.KNOWLEDGE_SPECIFICATION__GROUPS:
        setGroups((GroupsSpecification)null);
        return;
      case FMLPackage.KNOWLEDGE_SPECIFICATION__CONSTRAINTS:
        setConstraints((ConstraintsSpecification)null);
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
      case FMLPackage.KNOWLEDGE_SPECIFICATION__HIERARCHY:
        return hierarchy != null;
      case FMLPackage.KNOWLEDGE_SPECIFICATION__GROUPS:
        return groups != null;
      case FMLPackage.KNOWLEDGE_SPECIFICATION__CONSTRAINTS:
        return constraints != null;
    }
    return super.eIsSet(featureID);
  }

} //KnowledgeSpecificationImpl
