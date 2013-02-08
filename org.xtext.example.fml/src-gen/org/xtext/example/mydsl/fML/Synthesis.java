/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Synthesis</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.Synthesis#getFm <em>Fm</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.Synthesis#getKst <em>Kst</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getSynthesis()
 * @model
 * @generated
 */
public interface Synthesis extends Command, FMCommand
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
   * @see org.xtext.example.mydsl.fML.FMLPackage#getSynthesis_Fm()
   * @model containment="true"
   * @generated
   */
  FMCommand getFm();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Synthesis#getFm <em>Fm</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fm</em>' containment reference.
   * @see #getFm()
   * @generated
   */
  void setFm(FMCommand value);

  /**
   * Returns the value of the '<em><b>Kst</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Kst</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Kst</em>' containment reference.
   * @see #setKst(KnowledgeSpecification)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getSynthesis_Kst()
   * @model containment="true"
   * @generated
   */
  KnowledgeSpecification getKst();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Synthesis#getKst <em>Kst</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kst</em>' containment reference.
   * @see #getKst()
   * @generated
   */
  void setKst(KnowledgeSpecification value);

} // Synthesis
