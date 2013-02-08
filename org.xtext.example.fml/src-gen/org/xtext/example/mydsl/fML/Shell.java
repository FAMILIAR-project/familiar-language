/**
 */
package org.xtext.example.mydsl.fML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Shell</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.Shell#getCmd <em>Cmd</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getShell()
 * @model
 * @generated
 */
public interface Shell extends Command
{
  /**
   * Returns the value of the '<em><b>Cmd</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cmd</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cmd</em>' containment reference.
   * @see #setCmd(EObject)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getShell_Cmd()
   * @model containment="true"
   * @generated
   */
  EObject getCmd();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Shell#getCmd <em>Cmd</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Cmd</em>' containment reference.
   * @see #getCmd()
   * @generated
   */
  void setCmd(EObject value);

} // Shell
