/**
 */
package org.xtext.example.mydsl.fML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Familiar Script</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.FamiliarScript#getParams <em>Params</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.FamiliarScript#getCmds <em>Cmds</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.FamiliarScript#getExports <em>Exports</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getFamiliarScript()
 * @model
 * @generated
 */
public interface FamiliarScript extends EObject
{
  /**
   * Returns the value of the '<em><b>Params</b></em>' containment reference list.
   * The list contents are of type {@link org.xtext.example.mydsl.fML.Parameter}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Params</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Params</em>' containment reference list.
   * @see org.xtext.example.mydsl.fML.FMLPackage#getFamiliarScript_Params()
   * @model containment="true"
   * @generated
   */
  EList<Parameter> getParams();

  /**
   * Returns the value of the '<em><b>Cmds</b></em>' containment reference list.
   * The list contents are of type {@link org.xtext.example.mydsl.fML.ScriptCommand}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cmds</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cmds</em>' containment reference list.
   * @see org.xtext.example.mydsl.fML.FMLPackage#getFamiliarScript_Cmds()
   * @model containment="true"
   * @generated
   */
  EList<ScriptCommand> getCmds();

  /**
   * Returns the value of the '<em><b>Exports</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Exports</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Exports</em>' containment reference list.
   * @see org.xtext.example.mydsl.fML.FMLPackage#getFamiliarScript_Exports()
   * @model containment="true"
   * @generated
   */
  EList<EObject> getExports();

} // FamiliarScript
