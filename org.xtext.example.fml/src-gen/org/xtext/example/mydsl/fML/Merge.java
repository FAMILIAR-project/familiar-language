/**
 */
package org.xtext.example.mydsl.fML;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Merge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.Merge#getBackend <em>Backend</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.Merge#getMode <em>Mode</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.Merge#getLfms <em>Lfms</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.Merge#getFms <em>Fms</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getMerge()
 * @model
 * @generated
 */
public interface Merge extends Command, FMCommand
{
  /**
   * Returns the value of the '<em><b>Backend</b></em>' attribute.
   * The literals are from the enumeration {@link org.xtext.example.mydsl.fML.BDDBackend}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Backend</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Backend</em>' attribute.
   * @see org.xtext.example.mydsl.fML.BDDBackend
   * @see #setBackend(BDDBackend)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getMerge_Backend()
   * @model
   * @generated
   */
  BDDBackend getBackend();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Merge#getBackend <em>Backend</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Backend</em>' attribute.
   * @see org.xtext.example.mydsl.fML.BDDBackend
   * @see #getBackend()
   * @generated
   */
  void setBackend(BDDBackend value);

  /**
   * Returns the value of the '<em><b>Mode</b></em>' attribute.
   * The literals are from the enumeration {@link org.xtext.example.mydsl.fML.MergeMode}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mode</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mode</em>' attribute.
   * @see org.xtext.example.mydsl.fML.MergeMode
   * @see #setMode(MergeMode)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getMerge_Mode()
   * @model
   * @generated
   */
  MergeMode getMode();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Merge#getMode <em>Mode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mode</em>' attribute.
   * @see org.xtext.example.mydsl.fML.MergeMode
   * @see #getMode()
   * @generated
   */
  void setMode(MergeMode value);

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
   * @see org.xtext.example.mydsl.fML.FMLPackage#getMerge_Lfms()
   * @model containment="true"
   * @generated
   */
  EList<FMCommand> getLfms();

  /**
   * Returns the value of the '<em><b>Fms</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fms</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fms</em>' containment reference.
   * @see #setFms(LFMArgs)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getMerge_Fms()
   * @model containment="true"
   * @generated
   */
  LFMArgs getFms();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Merge#getFms <em>Fms</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fms</em>' containment reference.
   * @see #getFms()
   * @generated
   */
  void setFms(LFMArgs value);

} // Merge
