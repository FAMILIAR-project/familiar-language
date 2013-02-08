/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mandatory Edit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.MandatoryEdit#getFt <em>Ft</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getMandatoryEdit()
 * @model
 * @generated
 */
public interface MandatoryEdit extends ModifyVOperator
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
   * @see org.xtext.example.mydsl.fML.FMLPackage#getMandatoryEdit_Ft()
   * @model containment="true"
   * @generated
   */
  FTCommand getFt();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.MandatoryEdit#getFt <em>Ft</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ft</em>' containment reference.
   * @see #getFt()
   * @generated
   */
  void setFt(FTCommand value);

} // MandatoryEdit
