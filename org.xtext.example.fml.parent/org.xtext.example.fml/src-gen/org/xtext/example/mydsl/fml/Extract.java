/**
 * generated by Xtext 2.9.1
 */
package org.xtext.example.mydsl.fml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extract</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fml.Extract#getRootfeature <em>Rootfeature</em>}</li>
 * </ul>
 *
 * @see org.xtext.example.mydsl.fml.FmlPackage#getExtract()
 * @model
 * @generated
 */
public interface Extract extends FMCommand, FeatureModelOperation
{
  /**
   * Returns the value of the '<em><b>Rootfeature</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Rootfeature</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rootfeature</em>' containment reference.
   * @see #setRootfeature(FTCommand)
   * @see org.xtext.example.mydsl.fml.FmlPackage#getExtract_Rootfeature()
   * @model containment="true"
   * @generated
   */
  FTCommand getRootfeature();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fml.Extract#getRootfeature <em>Rootfeature</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rootfeature</em>' containment reference.
   * @see #getRootfeature()
   * @generated
   */
  void setRootfeature(FTCommand value);

} // Extract
