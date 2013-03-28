/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Compute Constraints</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.ComputeConstraints#getKindOfCompute <em>Kind Of Compute</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.ComputeConstraints#getFm <em>Fm</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.ComputeConstraints#isOver <em>Over</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.ComputeConstraints#getFts <em>Fts</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getComputeConstraints()
 * @model
 * @generated
 */
public interface ComputeConstraints extends Command, SetCommand
{
  /**
   * Returns the value of the '<em><b>Kind Of Compute</b></em>' attribute.
   * The literals are from the enumeration {@link org.xtext.example.mydsl.fML.KindOfCompute}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Kind Of Compute</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Kind Of Compute</em>' attribute.
   * @see org.xtext.example.mydsl.fML.KindOfCompute
   * @see #setKindOfCompute(KindOfCompute)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getComputeConstraints_KindOfCompute()
   * @model
   * @generated
   */
  KindOfCompute getKindOfCompute();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.ComputeConstraints#getKindOfCompute <em>Kind Of Compute</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kind Of Compute</em>' attribute.
   * @see org.xtext.example.mydsl.fML.KindOfCompute
   * @see #getKindOfCompute()
   * @generated
   */
  void setKindOfCompute(KindOfCompute value);

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
   * @see org.xtext.example.mydsl.fML.FMLPackage#getComputeConstraints_Fm()
   * @model containment="true"
   * @generated
   */
  FMCommand getFm();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.ComputeConstraints#getFm <em>Fm</em>}' containment reference.
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
   * @see org.xtext.example.mydsl.fML.FMLPackage#getComputeConstraints_Over()
   * @model
   * @generated
   */
  boolean isOver();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.ComputeConstraints#isOver <em>Over</em>}' attribute.
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
   * @see org.xtext.example.mydsl.fML.FMLPackage#getComputeConstraints_Fts()
   * @model containment="true"
   * @generated
   */
  SetCommand getFts();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.ComputeConstraints#getFts <em>Fts</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fts</em>' containment reference.
   * @see #getFts()
   * @generated
   */
  void setFts(SetCommand value);

} // ComputeConstraints
