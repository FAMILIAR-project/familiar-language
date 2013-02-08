/**
 */
package org.xtext.example.mydsl.fML;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Load Generic</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.LoadGeneric#getStream <em>Stream</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.LoadGeneric#getParams <em>Params</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.LoadGeneric#getNs <em>Ns</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getLoadGeneric()
 * @model
 * @generated
 */
public interface LoadGeneric extends Command
{
  /**
   * Returns the value of the '<em><b>Stream</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Stream</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Stream</em>' attribute.
   * @see #setStream(String)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getLoadGeneric_Stream()
   * @model
   * @generated
   */
  String getStream();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.LoadGeneric#getStream <em>Stream</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Stream</em>' attribute.
   * @see #getStream()
   * @generated
   */
  void setStream(String value);

  /**
   * Returns the value of the '<em><b>Params</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Params</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Params</em>' attribute list.
   * @see org.xtext.example.mydsl.fML.FMLPackage#getLoadGeneric_Params()
   * @model unique="false"
   * @generated
   */
  EList<String> getParams();

  /**
   * Returns the value of the '<em><b>Ns</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ns</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ns</em>' attribute.
   * @see #setNs(String)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getLoadGeneric_Ns()
   * @model
   * @generated
   */
  String getNs();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.LoadGeneric#getNs <em>Ns</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ns</em>' attribute.
   * @see #getNs()
   * @generated
   */
  void setNs(String value);

} // LoadGeneric
