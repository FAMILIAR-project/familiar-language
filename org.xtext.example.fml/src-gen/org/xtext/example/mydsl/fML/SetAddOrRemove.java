/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Set Add Or Remove</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.SetAddOrRemove#getOp <em>Op</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.SetAddOrRemove#getSetl <em>Setl</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.SetAddOrRemove#getVar <em>Var</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getSetAddOrRemove()
 * @model
 * @generated
 */
public interface SetAddOrRemove extends SetOperations
{
  /**
   * Returns the value of the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op</em>' attribute.
   * @see #setOp(String)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getSetAddOrRemove_Op()
   * @model
   * @generated
   */
  String getOp();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.SetAddOrRemove#getOp <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op</em>' attribute.
   * @see #getOp()
   * @generated
   */
  void setOp(String value);

  /**
   * Returns the value of the '<em><b>Setl</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Setl</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Setl</em>' containment reference.
   * @see #setSetl(SetCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getSetAddOrRemove_Setl()
   * @model containment="true"
   * @generated
   */
  SetCommand getSetl();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.SetAddOrRemove#getSetl <em>Setl</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Setl</em>' containment reference.
   * @see #getSetl()
   * @generated
   */
  void setSetl(SetCommand value);

  /**
   * Returns the value of the '<em><b>Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Var</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Var</em>' containment reference.
   * @see #setVar(Command)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getSetAddOrRemove_Var()
   * @model containment="true"
   * @generated
   */
  Command getVar();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.SetAddOrRemove#getVar <em>Var</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Var</em>' containment reference.
   * @see #getVar()
   * @generated
   */
  void setVar(Command value);

} // SetAddOrRemove
