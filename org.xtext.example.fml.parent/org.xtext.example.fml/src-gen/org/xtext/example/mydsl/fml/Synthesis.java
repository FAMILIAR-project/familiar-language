/**
 * generated by Xtext 2.9.1
 */
package org.xtext.example.mydsl.fml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Synthesis</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fml.Synthesis#isInteractive <em>Interactive</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fml.Synthesis#getFm <em>Fm</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fml.Synthesis#isOver <em>Over</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fml.Synthesis#getFts <em>Fts</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fml.Synthesis#getKst <em>Kst</em>}</li>
 * </ul>
 *
 * @see org.xtext.example.mydsl.fml.FmlPackage#getSynthesis()
 * @model
 * @generated
 */
public interface Synthesis extends Command, FMCommand
{
  /**
   * Returns the value of the '<em><b>Interactive</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Interactive</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Interactive</em>' attribute.
   * @see #setInteractive(boolean)
   * @see org.xtext.example.mydsl.fml.FmlPackage#getSynthesis_Interactive()
   * @model
   * @generated
   */
  boolean isInteractive();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fml.Synthesis#isInteractive <em>Interactive</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Interactive</em>' attribute.
   * @see #isInteractive()
   * @generated
   */
  void setInteractive(boolean value);

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
   * @see org.xtext.example.mydsl.fml.FmlPackage#getSynthesis_Fm()
   * @model containment="true"
   * @generated
   */
  FMCommand getFm();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fml.Synthesis#getFm <em>Fm</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fm</em>' containment reference.
   * @see #getFm()
   * @generated
   */
  void setFm(FMCommand value);

  /**
   * Returns the value of the '<em><b>Over</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Over</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Over</em>' attribute.
   * @see #setOver(boolean)
   * @see org.xtext.example.mydsl.fml.FmlPackage#getSynthesis_Over()
   * @model
   * @generated
   */
  boolean isOver();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fml.Synthesis#isOver <em>Over</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Over</em>' attribute.
   * @see #isOver()
   * @generated
   */
  void setOver(boolean value);

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
   * @see org.xtext.example.mydsl.fml.FmlPackage#getSynthesis_Fts()
   * @model containment="true"
   * @generated
   */
  SetCommand getFts();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fml.Synthesis#getFts <em>Fts</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fts</em>' containment reference.
   * @see #getFts()
   * @generated
   */
  void setFts(SetCommand value);

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
   * @see org.xtext.example.mydsl.fml.FmlPackage#getSynthesis_Kst()
   * @model containment="true"
   * @generated
   */
  KnowledgeSpecification getKst();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fml.Synthesis#getKst <em>Kst</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kst</em>' containment reference.
   * @see #getKst()
   * @generated
   */
  void setKst(KnowledgeSpecification value);

} // Synthesis