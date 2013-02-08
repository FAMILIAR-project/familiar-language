/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Convert</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.Convert#getV <em>V</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.Convert#getFormat <em>Format</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getConvert()
 * @model
 * @generated
 */
public interface Convert extends Command, StrCommand
{
  /**
   * Returns the value of the '<em><b>V</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>V</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>V</em>' containment reference.
   * @see #setV(FMCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getConvert_V()
   * @model containment="true"
   * @generated
   */
  FMCommand getV();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Convert#getV <em>V</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>V</em>' containment reference.
   * @see #getV()
   * @generated
   */
  void setV(FMCommand value);

  /**
   * Returns the value of the '<em><b>Format</b></em>' attribute.
   * The literals are from the enumeration {@link org.xtext.example.mydsl.fML.FMFormat}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Format</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Format</em>' attribute.
   * @see org.xtext.example.mydsl.fML.FMFormat
   * @see #setFormat(FMFormat)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getConvert_Format()
   * @model
   * @generated
   */
  FMFormat getFormat();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Convert#getFormat <em>Format</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Format</em>' attribute.
   * @see org.xtext.example.mydsl.fML.FMFormat
   * @see #getFormat()
   * @generated
   */
  void setFormat(FMFormat value);

} // Convert
