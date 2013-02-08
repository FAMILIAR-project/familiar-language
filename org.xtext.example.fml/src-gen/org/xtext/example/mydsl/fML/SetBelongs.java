/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Set Belongs</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.SetBelongs#getSetl <em>Setl</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.SetBelongs#getSetr <em>Setr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getSetBelongs()
 * @model
 * @generated
 */
public interface SetBelongs extends BCommand, SetOperations
{
  /**
   * Returns the value of the '<em><b>Setl</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Setl</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Setl</em>' attribute.
   * @see #setSetl(String)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getSetBelongs_Setl()
   * @model
   * @generated
   */
  String getSetl();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.SetBelongs#getSetl <em>Setl</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Setl</em>' attribute.
   * @see #getSetl()
   * @generated
   */
  void setSetl(String value);

  /**
   * Returns the value of the '<em><b>Setr</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Setr</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Setr</em>' attribute.
   * @see #setSetr(String)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getSetBelongs_Setr()
   * @model
   * @generated
   */
  String getSetr();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.SetBelongs#getSetr <em>Setr</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Setr</em>' attribute.
   * @see #getSetr()
   * @generated
   */
  void setSetr(String value);

} // SetBelongs
