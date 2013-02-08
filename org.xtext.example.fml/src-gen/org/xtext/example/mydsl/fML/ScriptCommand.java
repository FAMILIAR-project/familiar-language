/**
 */
package org.xtext.example.mydsl.fML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Script Command</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.ScriptCommand#getVar <em>Var</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.ScriptCommand#getMetaID <em>Meta ID</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.ScriptCommand#getCmd <em>Cmd</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getScriptCommand()
 * @model
 * @generated
 */
public interface ScriptCommand extends EObject
{
  /**
   * Returns the value of the '<em><b>Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Var</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Var</em>' attribute.
   * @see #setVar(String)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getScriptCommand_Var()
   * @model
   * @generated
   */
  String getVar();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.ScriptCommand#getVar <em>Var</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Var</em>' attribute.
   * @see #getVar()
   * @generated
   */
  void setVar(String value);

  /**
   * Returns the value of the '<em><b>Meta ID</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Meta ID</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Meta ID</em>' containment reference.
   * @see #setMetaID(StringExpr)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getScriptCommand_MetaID()
   * @model containment="true"
   * @generated
   */
  StringExpr getMetaID();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.ScriptCommand#getMetaID <em>Meta ID</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Meta ID</em>' containment reference.
   * @see #getMetaID()
   * @generated
   */
  void setMetaID(StringExpr value);

  /**
   * Returns the value of the '<em><b>Cmd</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cmd</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cmd</em>' containment reference.
   * @see #setCmd(ComplexCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getScriptCommand_Cmd()
   * @model containment="true"
   * @generated
   */
  ComplexCommand getCmd();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.ScriptCommand#getCmd <em>Cmd</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Cmd</em>' containment reference.
   * @see #getCmd()
   * @generated
   */
  void setCmd(ComplexCommand value);

} // ScriptCommand
