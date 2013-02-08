/**
 */
package org.xtext.example.mydsl.fML;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.FeatureModel#getRoot <em>Root</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.FeatureModel#getFeatures <em>Features</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.FeatureModel#getExpr <em>Expr</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.FeatureModel#getFile <em>File</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getFeatureModel()
 * @model
 * @generated
 */
public interface FeatureModel extends Command, FMCommand
{
  /**
   * Returns the value of the '<em><b>Root</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Root</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Root</em>' attribute.
   * @see #setRoot(String)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getFeatureModel_Root()
   * @model
   * @generated
   */
  String getRoot();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.FeatureModel#getRoot <em>Root</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Root</em>' attribute.
   * @see #getRoot()
   * @generated
   */
  void setRoot(String value);

  /**
   * Returns the value of the '<em><b>Features</b></em>' containment reference list.
   * The list contents are of type {@link org.xtext.example.mydsl.fML.Production}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Features</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Features</em>' containment reference list.
   * @see org.xtext.example.mydsl.fML.FMLPackage#getFeatureModel_Features()
   * @model containment="true"
   * @generated
   */
  EList<Production> getFeatures();

  /**
   * Returns the value of the '<em><b>Expr</b></em>' containment reference list.
   * The list contents are of type {@link org.xtext.example.mydsl.fML.CNF}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expr</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr</em>' containment reference list.
   * @see org.xtext.example.mydsl.fML.FMLPackage#getFeatureModel_Expr()
   * @model containment="true"
   * @generated
   */
  EList<CNF> getExpr();

  /**
   * Returns the value of the '<em><b>File</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>File</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>File</em>' containment reference.
   * @see #setFile(StringExpr)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getFeatureModel_File()
   * @model containment="true"
   * @generated
   */
  StringExpr getFile();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.FeatureModel#getFile <em>File</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>File</em>' containment reference.
   * @see #getFile()
   * @generated
   */
  void setFile(StringExpr value);

} // FeatureModel
