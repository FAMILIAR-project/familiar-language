/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Insert</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.Insert#getAspectfm <em>Aspectfm</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.Insert#getBaseft <em>Baseft</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.Insert#getOp <em>Op</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getInsert()
 * @model
 * @generated
 */
public interface Insert extends BCommand, FeatureModelOperation
{
  /**
   * Returns the value of the '<em><b>Aspectfm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Aspectfm</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Aspectfm</em>' containment reference.
   * @see #setAspectfm(FMCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getInsert_Aspectfm()
   * @model containment="true"
   * @generated
   */
  FMCommand getAspectfm();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Insert#getAspectfm <em>Aspectfm</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Aspectfm</em>' containment reference.
   * @see #getAspectfm()
   * @generated
   */
  void setAspectfm(FMCommand value);

  /**
   * Returns the value of the '<em><b>Baseft</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Baseft</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Baseft</em>' containment reference.
   * @see #setBaseft(FTCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getInsert_Baseft()
   * @model containment="true"
   * @generated
   */
  FTCommand getBaseft();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Insert#getBaseft <em>Baseft</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Baseft</em>' containment reference.
   * @see #getBaseft()
   * @generated
   */
  void setBaseft(FTCommand value);

  /**
   * Returns the value of the '<em><b>Op</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op</em>' containment reference.
   * @see #setOp(VariabilityOpCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getInsert_Op()
   * @model containment="true"
   * @generated
   */
  VariabilityOpCommand getOp();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Insert#getOp <em>Op</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op</em>' containment reference.
   * @see #getOp()
   * @generated
   */
  void setOp(VariabilityOpCommand value);

} // Insert
