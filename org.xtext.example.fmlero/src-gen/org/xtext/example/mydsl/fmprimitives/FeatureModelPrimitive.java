/**
 */
package org.xtext.example.mydsl.fmprimitives;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Model Primitive</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.FeatureModelPrimitive#getId <em>Id</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.FeatureModelPrimitive#getName <em>Name</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.FeatureModelPrimitive#getConfigurationSource <em>Configuration Source</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.FeatureModelPrimitive#getExplanations <em>Explanations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getFeatureModelPrimitive()
 * @model
 * @generated
 */
public interface FeatureModelPrimitive extends EObject
{
  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(String)
   * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getFeatureModelPrimitive_Id()
   * @model
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fmprimitives.FeatureModelPrimitive#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getFeatureModelPrimitive_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fmprimitives.FeatureModelPrimitive#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Configuration Source</b></em>' attribute.
   * The literals are from the enumeration {@link org.xtext.example.mydsl.fmprimitives.ConfigurationSource}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Configuration Source</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Configuration Source</em>' attribute.
   * @see org.xtext.example.mydsl.fmprimitives.ConfigurationSource
   * @see #setConfigurationSource(ConfigurationSource)
   * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getFeatureModelPrimitive_ConfigurationSource()
   * @model
   * @generated
   */
  ConfigurationSource getConfigurationSource();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fmprimitives.FeatureModelPrimitive#getConfigurationSource <em>Configuration Source</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Configuration Source</em>' attribute.
   * @see org.xtext.example.mydsl.fmprimitives.ConfigurationSource
   * @see #getConfigurationSource()
   * @generated
   */
  void setConfigurationSource(ConfigurationSource value);

  /**
   * Returns the value of the '<em><b>Explanations</b></em>' reference list.
   * The list contents are of type {@link org.xtext.example.mydsl.fmprimitives.Explanation}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Explanations</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Explanations</em>' reference list.
   * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getFeatureModelPrimitive_Explanations()
   * @model
   * @generated
   */
  EList<Explanation> getExplanations();

} // FeatureModelPrimitive
