/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Compare</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.Compare#getFm_left <em>Fm left</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.Compare#getFm_right <em>Fm right</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getCompare()
 * @model
 * @generated
 */
public interface Compare extends Command
{
  /**
   * Returns the value of the '<em><b>Fm left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fm left</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fm left</em>' containment reference.
   * @see #setFm_left(FMCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getCompare_Fm_left()
   * @model containment="true"
   * @generated
   */
  FMCommand getFm_left();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Compare#getFm_left <em>Fm left</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fm left</em>' containment reference.
   * @see #getFm_left()
   * @generated
   */
  void setFm_left(FMCommand value);

  /**
   * Returns the value of the '<em><b>Fm right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fm right</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fm right</em>' containment reference.
   * @see #setFm_right(FMCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getCompare_Fm_right()
   * @model containment="true"
   * @generated
   */
  FMCommand getFm_right();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Compare#getFm_right <em>Fm right</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fm right</em>' containment reference.
   * @see #getFm_right()
   * @generated
   */
  void setFm_right(FMCommand value);

} // Compare
