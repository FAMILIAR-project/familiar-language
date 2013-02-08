/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Get Constraints</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.GetConstraints#getKindOfGet <em>Kind Of Get</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.GetConstraints#getFm <em>Fm</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getGetConstraints()
 * @model
 * @generated
 */
public interface GetConstraints extends Command, SetCommand
{
  /**
   * Returns the value of the '<em><b>Kind Of Get</b></em>' attribute.
   * The literals are from the enumeration {@link org.xtext.example.mydsl.fML.KindOfGet}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Kind Of Get</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Kind Of Get</em>' attribute.
   * @see org.xtext.example.mydsl.fML.KindOfGet
   * @see #setKindOfGet(KindOfGet)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getGetConstraints_KindOfGet()
   * @model
   * @generated
   */
  KindOfGet getKindOfGet();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.GetConstraints#getKindOfGet <em>Kind Of Get</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kind Of Get</em>' attribute.
   * @see org.xtext.example.mydsl.fML.KindOfGet
   * @see #getKindOfGet()
   * @generated
   */
  void setKindOfGet(KindOfGet value);

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
   * @see org.xtext.example.mydsl.fML.FMLPackage#getGetConstraints_Fm()
   * @model containment="true"
   * @generated
   */
  FMCommand getFm();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.GetConstraints#getFm <em>Fm</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fm</em>' containment reference.
   * @see #getFm()
   * @generated
   */
  void setFm(FMCommand value);

} // GetConstraints
