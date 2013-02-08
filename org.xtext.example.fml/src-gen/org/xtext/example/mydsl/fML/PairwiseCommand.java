/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pairwise Command</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.PairwiseCommand#getFm <em>Fm</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.PairwiseCommand#getMinimization <em>Minimization</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.PairwiseCommand#getPartial <em>Partial</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getPairwiseCommand()
 * @model
 * @generated
 */
public interface PairwiseCommand extends Command, SetCommand
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
   * @see org.xtext.example.mydsl.fML.FMLPackage#getPairwiseCommand_Fm()
   * @model containment="true"
   * @generated
   */
  FMCommand getFm();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.PairwiseCommand#getFm <em>Fm</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fm</em>' containment reference.
   * @see #getFm()
   * @generated
   */
  void setFm(FMCommand value);

  /**
   * Returns the value of the '<em><b>Minimization</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Minimization</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Minimization</em>' containment reference.
   * @see #setMinimization(IntegerCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getPairwiseCommand_Minimization()
   * @model containment="true"
   * @generated
   */
  IntegerCommand getMinimization();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.PairwiseCommand#getMinimization <em>Minimization</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Minimization</em>' containment reference.
   * @see #getMinimization()
   * @generated
   */
  void setMinimization(IntegerCommand value);

  /**
   * Returns the value of the '<em><b>Partial</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Partial</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Partial</em>' containment reference.
   * @see #setPartial(IntegerCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getPairwiseCommand_Partial()
   * @model containment="true"
   * @generated
   */
  IntegerCommand getPartial();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.PairwiseCommand#getPartial <em>Partial</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Partial</em>' containment reference.
   * @see #getPartial()
   * @generated
   */
  void setPartial(IntegerCommand value);

} // PairwiseCommand
