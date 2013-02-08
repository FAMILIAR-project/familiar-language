/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Assertion</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.Assertion#getAssertion <em>Assertion</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getAssertion()
 * @model
 * @generated
 */
public interface Assertion extends Command
{
  /**
   * Returns the value of the '<em><b>Assertion</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Assertion</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Assertion</em>' containment reference.
   * @see #setAssertion(ComplexCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getAssertion_Assertion()
   * @model containment="true"
   * @generated
   */
  ComplexCommand getAssertion();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Assertion#getAssertion <em>Assertion</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Assertion</em>' containment reference.
   * @see #getAssertion()
   * @generated
   */
  void setAssertion(ComplexCommand value);

} // Assertion
