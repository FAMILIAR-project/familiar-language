/**
 */
package org.xtext.example.mydsl.fML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>LFM Args</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.LFMArgs#getLfms <em>Lfms</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getLFMArgs()
 * @model
 * @generated
 */
public interface LFMArgs extends EObject
{
  /**
   * Returns the value of the '<em><b>Lfms</b></em>' containment reference list.
   * The list contents are of type {@link org.xtext.example.mydsl.fML.FMCommand}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Lfms</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lfms</em>' containment reference list.
   * @see org.xtext.example.mydsl.fML.FMLPackage#getLFMArgs_Lfms()
   * @model containment="true"
   * @generated
   */
  EList<FMCommand> getLfms();

} // LFMArgs
