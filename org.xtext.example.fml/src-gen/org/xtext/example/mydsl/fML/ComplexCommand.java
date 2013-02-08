/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Complex Command</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.ComplexCommand#getLeft <em>Left</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.ComplexCommand#isNot <em>Not</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.ComplexCommand#getBatom <em>Batom</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getComplexCommand()
 * @model
 * @generated
 */
public interface ComplexCommand extends ScriptCommand, Command
{
  /**
   * Returns the value of the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Left</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Left</em>' containment reference.
   * @see #setLeft(Command)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getComplexCommand_Left()
   * @model containment="true"
   * @generated
   */
  Command getLeft();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.ComplexCommand#getLeft <em>Left</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Left</em>' containment reference.
   * @see #getLeft()
   * @generated
   */
  void setLeft(Command value);

  /**
   * Returns the value of the '<em><b>Not</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Not</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Not</em>' attribute.
   * @see #setNot(boolean)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getComplexCommand_Not()
   * @model
   * @generated
   */
  boolean isNot();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.ComplexCommand#isNot <em>Not</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Not</em>' attribute.
   * @see #isNot()
   * @generated
   */
  void setNot(boolean value);

  /**
   * Returns the value of the '<em><b>Batom</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Batom</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Batom</em>' containment reference.
   * @see #setBatom(ComplexCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getComplexCommand_Batom()
   * @model containment="true"
   * @generated
   */
  ComplexCommand getBatom();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.ComplexCommand#getBatom <em>Batom</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Batom</em>' containment reference.
   * @see #getBatom()
   * @generated
   */
  void setBatom(ComplexCommand value);

} // ComplexCommand
