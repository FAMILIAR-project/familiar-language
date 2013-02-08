/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rename Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.RenameFeature#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.RenameFeature#getFeatureNew <em>Feature New</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getRenameFeature()
 * @model
 * @generated
 */
public interface RenameFeature extends BCommand, EditOperation
{
  /**
   * Returns the value of the '<em><b>Feature</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Feature</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Feature</em>' containment reference.
   * @see #setFeature(FTCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getRenameFeature_Feature()
   * @model containment="true"
   * @generated
   */
  FTCommand getFeature();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.RenameFeature#getFeature <em>Feature</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Feature</em>' containment reference.
   * @see #getFeature()
   * @generated
   */
  void setFeature(FTCommand value);

  /**
   * Returns the value of the '<em><b>Feature New</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Feature New</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Feature New</em>' containment reference.
   * @see #setFeatureNew(StrCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getRenameFeature_FeatureNew()
   * @model containment="true"
   * @generated
   */
  StrCommand getFeatureNew();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.RenameFeature#getFeatureNew <em>Feature New</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Feature New</em>' containment reference.
   * @see #getFeatureNew()
   * @generated
   */
  void setFeatureNew(StrCommand value);

} // RenameFeature
