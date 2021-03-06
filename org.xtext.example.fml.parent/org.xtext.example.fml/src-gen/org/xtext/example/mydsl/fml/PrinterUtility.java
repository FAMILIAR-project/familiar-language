/**
 * generated by Xtext 2.9.1
 */
package org.xtext.example.mydsl.fml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Printer Utility</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fml.PrinterUtility#getOp <em>Op</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fml.PrinterUtility#getArg <em>Arg</em>}</li>
 * </ul>
 *
 * @see org.xtext.example.mydsl.fml.FmlPackage#getPrinterUtility()
 * @model
 * @generated
 */
public interface PrinterUtility extends Command
{
  /**
   * Returns the value of the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op</em>' attribute.
   * @see #setOp(String)
   * @see org.xtext.example.mydsl.fml.FmlPackage#getPrinterUtility_Op()
   * @model
   * @generated
   */
  String getOp();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fml.PrinterUtility#getOp <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op</em>' attribute.
   * @see #getOp()
   * @generated
   */
  void setOp(String value);

  /**
   * Returns the value of the '<em><b>Arg</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Arg</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Arg</em>' containment reference.
   * @see #setArg(LArgs)
   * @see org.xtext.example.mydsl.fml.FmlPackage#getPrinterUtility_Arg()
   * @model containment="true"
   * @generated
   */
  LArgs getArg();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fml.PrinterUtility#getArg <em>Arg</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Arg</em>' containment reference.
   * @see #getArg()
   * @generated
   */
  void setArg(LArgs value);

} // PrinterUtility
