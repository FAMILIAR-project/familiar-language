/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Remove Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.RemoveVariable#getVid <em>Vid</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getRemoveVariable()
 * @model
 * @generated
 */
public interface RemoveVariable extends Command, BCommand
{
  /**
   * Returns the value of the '<em><b>Vid</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Vid</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Vid</em>' attribute.
   * @see #setVid(String)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getRemoveVariable_Vid()
   * @model
   * @generated
   */
  String getVid();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.RemoveVariable#getVid <em>Vid</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Vid</em>' attribute.
   * @see #getVid()
   * @generated
   */
  void setVid(String value);

} // RemoveVariable
