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

} // ComputeConstraints
