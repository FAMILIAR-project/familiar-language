/**
 */
package org.xtext.example.mydsl.fmprimitives.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.xtext.example.mydsl.fmprimitives.CustomUndirectedRelationship;
import org.xtext.example.mydsl.fmprimitives.Feature;
import org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Custom Undirected Relationship</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.impl.CustomUndirectedRelationshipImpl#getStereotype <em>Stereotype</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.impl.CustomUndirectedRelationshipImpl#getRelatedFeatures <em>Related Features</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CustomUndirectedRelationshipImpl extends FeatureModelPrimitiveImpl implements CustomUndirectedRelationship
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
   * The cached value of the '{@link #getRelatedFeatures() <em>Related Features</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRelatedFeatures()
   * @generated
   * @ordered
   */
  protected EList<Feature> relatedFeatures;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CustomUndirectedRelationshipImpl()
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
    return FmprimitivesPackage.Literals.CUSTOM_UNDIRECTED_RELATIONSHIP;
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
      eNotify(new ENotificationImpl(this, Notification.SET, FmprimitivesPackage.CUSTOM_UNDIRECTED_RELATIONSHIP__STEREOTYPE, oldStereotype, stereotype));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Feature> getRelatedFeatures()
  {
    if (relatedFeatures == null)
    {
      relatedFeatures = new EObjectResolvingEList<Feature>(Feature.class, this, FmprimitivesPackage.CUSTOM_UNDIRECTED_RELATIONSHIP__RELATED_FEATURES);
    }
    return relatedFeatures;
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
      case FmprimitivesPackage.CUSTOM_UNDIRECTED_RELATIONSHIP__STEREOTYPE:
        return getStereotype();
      case FmprimitivesPackage.CUSTOM_UNDIRECTED_RELATIONSHIP__RELATED_FEATURES:
        return getRelatedFeatures();
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
      case FmprimitivesPackage.CUSTOM_UNDIRECTED_RELATIONSHIP__STEREOTYPE:
        setStereotype((String)newValue);
        return;
      case FmprimitivesPackage.CUSTOM_UNDIRECTED_RELATIONSHIP__RELATED_FEATURES:
        getRelatedFeatures().clear();
        getRelatedFeatures().addAll((Collection<? extends Feature>)newValue);
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
      case FmprimitivesPackage.CUSTOM_UNDIRECTED_RELATIONSHIP__STEREOTYPE:
        setStereotype(STEREOTYPE_EDEFAULT);
        return;
      case FmprimitivesPackage.CUSTOM_UNDIRECTED_RELATIONSHIP__RELATED_FEATURES:
        getRelatedFeatures().clear();
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
      case FmprimitivesPackage.CUSTOM_UNDIRECTED_RELATIONSHIP__STEREOTYPE:
        return STEREOTYPE_EDEFAULT == null ? stereotype != null : !STEREOTYPE_EDEFAULT.equals(stereotype);
      case FmprimitivesPackage.CUSTOM_UNDIRECTED_RELATIONSHIP__RELATED_FEATURES:
        return relatedFeatures != null && !relatedFeatures.isEmpty();
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

} //CustomUndirectedRelationshipImpl
