/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Identifier Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.IdentifierExpr#getVal <em>Val</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.IdentifierExpr#getMetaID <em>Meta ID</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getIdentifierExpr()
 * @model
 * @generated
 */
public interface IdentifierExpr extends Command, FMCommand, FTCommand, BCommand, StrCommand, ConfigurationCommand, SetCommand, ConstraintCommand, IntegerCommand, VariabilityOpCommand
{
  /**
   * Returns the value of the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Val</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Val</em>' attribute.
   * @see #setVal(String)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getIdentifierExpr_Val()
   * @model
   * @generated
   */
  String getVal();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.IdentifierExpr#getVal <em>Val</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Val</em>' attribute.
   * @see #getVal()
   * @generated
   */
  void setVal(String value);

  /**
   * Returns the value of the '<em><b>Meta ID</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Meta ID</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Meta ID</em>' containment reference.
   * @see #setMetaID(StringExpr)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getIdentifierExpr_MetaID()
   * @model containment="true"
   * @generated
   */
  StringExpr getMetaID();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.IdentifierExpr#getMetaID <em>Meta ID</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Meta ID</em>' containment reference.
   * @see #getMetaID()
   * @generated
   */
  void setMetaID(StringExpr value);

} // IdentifierExpr
