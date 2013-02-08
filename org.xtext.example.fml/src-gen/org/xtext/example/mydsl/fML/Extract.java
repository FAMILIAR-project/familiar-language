/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extract</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.Extract#getRootfeature <em>Rootfeature</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getExtract()
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
   * @see org.xtext.example.mydsl.fML.FMLPackage#getExtract_Rootfeature()
   * @model containment="true"
   * @generated
   */
  FTCommand getRootfeature();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Extract#getRootfeature <em>Rootfeature</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rootfeature</em>' containment reference.
   * @see #getRootfeature()
   * @generated
   */
  void setRootfeature(FTCommand value);

} // Extract
