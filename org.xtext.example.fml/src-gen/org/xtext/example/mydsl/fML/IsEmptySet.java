/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Is Empty Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.IsEmptySet#getSet <em>Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getIsEmptySet()
 * @model
 * @generated
 */
public interface IsEmptySet extends BCommand, SetOperations
{
  /**
   * Returns the value of the '<em><b>Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Set</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Set</em>' containment reference.
   * @see #setSet(SetCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getIsEmptySet_Set()
   * @model containment="true"
   * @generated
   */
  SetCommand getSet();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.IsEmptySet#getSet <em>Set</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Set</em>' containment reference.
   * @see #getSet()
   * @generated
   */
  void setSet(SetCommand value);

} // IsEmptySet
