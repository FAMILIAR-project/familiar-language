/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Slice</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.Slice#getFm <em>Fm</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.Slice#getMode <em>Mode</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.Slice#getFts <em>Fts</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getSlice()
 * @model
 * @generated
 */
public interface Slice extends Command, FMCommand
{
  /**
   * Returns the value of the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fm</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fm</em>' containment reference.
   * @see #setFm(FMCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getSlice_Fm()
   * @model containment="true"
   * @generated
   */
  FMCommand getFm();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Slice#getFm <em>Fm</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fm</em>' containment reference.
   * @see #getFm()
   * @generated
   */
  void setFm(FMCommand value);

  /**
   * Returns the value of the '<em><b>Mode</b></em>' attribute.
   * The literals are from the enumeration {@link org.xtext.example.mydsl.fML.SliceMode}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mode</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mode</em>' attribute.
   * @see org.xtext.example.mydsl.fML.SliceMode
   * @see #setMode(SliceMode)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getSlice_Mode()
   * @model
   * @generated
   */
  SliceMode getMode();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Slice#getMode <em>Mode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mode</em>' attribute.
   * @see org.xtext.example.mydsl.fML.SliceMode
   * @see #getMode()
   * @generated
   */
  void setMode(SliceMode value);

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
   * @see org.xtext.example.mydsl.fML.FMLPackage#getSlice_Fts()
   * @model containment="true"
   * @generated
   */
  SetCommand getFts();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Slice#getFts <em>Fts</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fts</em>' containment reference.
   * @see #getFts()
   * @generated
   */
  void setFts(SetCommand value);

} // Slice
