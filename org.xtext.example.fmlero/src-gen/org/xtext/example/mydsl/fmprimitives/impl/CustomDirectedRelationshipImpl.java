/**
 */
package org.xtext.example.mydsl.fmprimitives.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.xtext.example.mydsl.fmprimitives.CustomDirectedRelationship;
import org.xtext.example.mydsl.fmprimitives.Feature;
import org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Custom Directed Relationship</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.impl.CustomDirectedRelationshipImpl#getStereotype <em>Stereotype</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.impl.CustomDirectedRelationshipImpl#getSources <em>Sources</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.impl.CustomDirectedRelationshipImpl#getTargets <em>Targets</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CustomDirectedRelationshipImpl extends FeatureModelPrimitiveImpl implements CustomDirectedRelationship
{
  /**
   * The default value of the '{@link #getStereotype() <em>Stereotype</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStereotype()
   * @generated
   * @ordered
   */
  protected static final String STEREOTYPE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getStereotype() <em>Stereotype</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStereotype()
   * @generated
   * @ordered
   */
  protected String stereotype = STEREOTYPE_EDEFAULT;

  /**
   * The cached value of the '{@link #getSources() <em>Sources</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSources()
   * @generated
   * @ordered
   */
  protected EList<Feature> sources;

  /**
   * The cached value of the '{@link #getTargets() <em>Targets</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargets()
   * @generated
   * @ordered
   */
  protected EList<Feature> targets;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CustomDirectedRelationshipImpl()
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
    return FmprimitivesPackage.Literals.CUSTOM_DIRECTED_RELATIONSHIP;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getStereotype()
  {
    return stereotype;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStereotype(String newStereotype)
  {
    String oldStereotype = stereotype;
    stereotype = newStereotype;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FmprimitivesPackage.CUSTOM_DIRECTED_RELATIONSHIP__STEREOTYPE, oldStereotype, stereotype));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Feature> getSources()
  {
    if (sources == null)
    {
      sources = new EObjectResolvingEList<Feature>(Feature.class, this, FmprimitivesPackage.CUSTOM_DIRECTED_RELATIONSHIP__SOURCES);
    }
    return sources;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Feature> getTargets()
  {
    if (targets == null)
    {
      targets = new EObjectResolvingEList<Feature>(Feature.class, this, FmprimitivesPackage.CUSTOM_DIRECTED_RELATIONSHIP__TARGETS);
    }
    return targets;
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
      case FmprimitivesPackage.CUSTOM_DIRECTED_RELATIONSHIP__STEREOTYPE:
        return getStereotype();
      case FmprimitivesPackage.CUSTOM_DIRECTED_RELATIONSHIP__SOURCES:
        return getSources();
      case FmprimitivesPackage.CUSTOM_DIRECTED_RELATIONSHIP__TARGETS:
        return getTargets();
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
      case FmprimitivesPackage.CUSTOM_DIRECTED_RELATIONSHIP__STEREOTYPE:
        setStereotype((String)newValue);
        return;
      case FmprimitivesPackage.CUSTOM_DIRECTED_RELATIONSHIP__SOURCES:
        getSources().clear();
        getSources().addAll((Collection<? extends Feature>)newValue);
        return;
      case FmprimitivesPackage.CUSTOM_DIRECTED_RELATIONSHIP__TARGETS:
        getTargets().clear();
        getTargets().addAll((Collection<? extends Feature>)newValue);
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
      case FmprimitivesPackage.CUSTOM_DIRECTED_RELATIONSHIP__STEREOTYPE:
        setStereotype(STEREOTYPE_EDEFAULT);
        return;
      case FmprimitivesPackage.CUSTOM_DIRECTED_RELATIONSHIP__SOURCES:
        getSources().clear();
        return;
      case FmprimitivesPackage.CUSTOM_DIRECTED_RELATIONSHIP__TARGETS:
        getTargets().clear();
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
      case FmprimitivesPackage.CUSTOM_DIRECTED_RELATIONSHIP__STEREOTYPE:
        return STEREOTYPE_EDEFAULT == null ? stereotype != null : !STEREOTYPE_EDEFAULT.equals(stereotype);
      case FmprimitivesPackage.CUSTOM_DIRECTED_RELATIONSHIP__SOURCES:
        return sources != null && !sources.isEmpty();
      case FmprimitivesPackage.CUSTOM_DIRECTED_RELATIONSHIP__TARGETS:
        return targets != null && !targets.isEmpty();
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
    result.append(" (stereotype: ");
    result.append(stereotype);
    result.append(')');
    return result.toString();
  }

} //CustomDirectedRelationshipImpl
