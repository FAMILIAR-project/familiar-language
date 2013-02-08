/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Map</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.Map#getFm <em>Fm</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.Map#getCst <em>Cst</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getMap()
 * @model
 * @generated
 */
public interface Map extends Command
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
   * @see org.xtext.example.mydsl.fML.FMLPackage#getMap_Fm()
   * @model containment="true"
   * @generated
   */
  FMCommand getFm();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Map#getFm <em>Fm</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fm</em>' containment reference.
   * @see #getFm()
   * @generated
   */
  void setFm(FMCommand value);

  /**
   * Returns the value of the '<em><b>Cst</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cst</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cst</em>' containment reference.
   * @see #setCst(SetCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getMap_Cst()
   * @model containment="true"
   * @generated
   */
  SetCommand getCst();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Map#getCst <em>Cst</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Cst</em>' containment reference.
   * @see #getCst()
   * @generated
   */
  void setCst(SetCommand value);

} // Map
