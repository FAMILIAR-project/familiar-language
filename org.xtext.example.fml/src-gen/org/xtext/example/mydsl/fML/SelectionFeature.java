/**
 */
package org.xtext.example.mydsl.fML;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Selection Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.SelectionFeature#getOp <em>Op</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.SelectionFeature#getFts <em>Fts</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.SelectionFeature#getConfig <em>Config</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getSelectionFeature()
 * @model
 * @generated
 */
public interface SelectionFeature extends ConfigurationCmd
{
  /**
   * Returns the value of the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op</em>' attribute.
   * @see #setOp(String)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getSelectionFeature_Op()
   * @model
   * @generated
   */
  String getOp();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.SelectionFeature#getOp <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op</em>' attribute.
   * @see #getOp()
   * @generated
   */
  void setOp(String value);

  /**
   * Returns the value of the '<em><b>Fts</b></em>' containment reference list.
   * The list contents are of type {@link org.xtext.example.mydsl.fML.FeatureExpression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fts</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fts</em>' containment reference list.
   * @see org.xtext.example.mydsl.fML.FMLPackage#getSelectionFeature_Fts()
   * @model containment="true"
   * @generated
   */
  EList<FeatureExpression> getFts();

  /**
   * Returns the value of the '<em><b>Config</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Config</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Config</em>' containment reference.
   * @see #setConfig(ConfigurationCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getSelectionFeature_Config()
   * @model containment="true"
   * @generated
   */
  ConfigurationCommand getConfig();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.SelectionFeature#getConfig <em>Config</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Config</em>' containment reference.
   * @see #getConfig()
   * @generated
   */
  void setConfig(ConfigurationCommand value);

} // SelectionFeature
