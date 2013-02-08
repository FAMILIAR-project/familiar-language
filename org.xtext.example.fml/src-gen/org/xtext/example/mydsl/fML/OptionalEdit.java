/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Optional Edit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.OptionalEdit#getFt <em>Ft</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getOptionalEdit()
 * @model
 * @generated
 */
public interface OptionalEdit extends ModifyVOperator
{
  /**
   * Returns the value of the '<em><b>Ft</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ft</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ft</em>' containment reference.
   * @see #setFt(FTCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getOptionalEdit_Ft()
   * @model containment="true"
   * @generated
   */
  FTCommand getFt();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.OptionalEdit#getFt <em>Ft</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ft</em>' containment reference.
   * @see #getFt()
   * @generated
   */
  void setFt(FTCommand value);

} // OptionalEdit
