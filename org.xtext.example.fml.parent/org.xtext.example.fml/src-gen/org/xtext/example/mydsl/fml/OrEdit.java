/**
 * generated by Xtext 2.9.1
 */
package org.xtext.example.mydsl.fml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Or Edit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fml.OrEdit#getFts <em>Fts</em>}</li>
 * </ul>
 *
 * @see org.xtext.example.mydsl.fml.FmlPackage#getOrEdit()
 * @model
 * @generated
 */
public interface OrEdit extends ModifyVOperator
{
  /**
   * Returns the value of the '<em><b>Fts</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fts</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fts</em>' containment reference.
   * @see #setFts(SetCommand)
   * @see org.xtext.example.mydsl.fml.FmlPackage#getOrEdit_Fts()
   * @model containment="true"
   * @generated
   */
  SetCommand getFts();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fml.OrEdit#getFts <em>Fts</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fts</em>' containment reference.
   * @see #getFts()
   * @generated
   */
  void setFts(SetCommand value);

} // OrEdit
