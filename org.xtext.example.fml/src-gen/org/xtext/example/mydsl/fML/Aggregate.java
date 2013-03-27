/**
 */
package org.xtext.example.mydsl.fML;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Aggregate</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.Aggregate#isRenamings <em>Renamings</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.Aggregate#getFms <em>Fms</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.Aggregate#getSfms <em>Sfms</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.Aggregate#getMapping <em>Mapping</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getAggregate()
 * @model
 * @generated
 */
public interface Aggregate extends Command, FMCommand
{
  /**
   * Returns the value of the '<em><b>Renamings</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Renamings</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Renamings</em>' attribute.
   * @see #setRenamings(boolean)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getAggregate_Renamings()
   * @model
   * @generated
   */
  boolean isRenamings();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Aggregate#isRenamings <em>Renamings</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Renamings</em>' attribute.
   * @see #isRenamings()
   * @generated
   */
  void setRenamings(boolean value);

  /**
   * Returns the value of the '<em><b>Fms</b></em>' containment reference list.
   * The list contents are of type {@link org.xtext.example.mydsl.fML.FMCommand}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fms</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fms</em>' containment reference list.
   * @see org.xtext.example.mydsl.fML.FMLPackage#getAggregate_Fms()
   * @model containment="true"
   * @generated
   */
  EList<FMCommand> getFms();

  /**
   * Returns the value of the '<em><b>Sfms</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sfms</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sfms</em>' containment reference.
   * @see #setSfms(IdentifierExpr)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getAggregate_Sfms()
   * @model containment="true"
   * @generated
   */
  IdentifierExpr getSfms();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Aggregate#getSfms <em>Sfms</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sfms</em>' containment reference.
   * @see #getSfms()
   * @generated
   */
  void setSfms(IdentifierExpr value);

  /**
   * Returns the value of the '<em><b>Mapping</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mapping</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mapping</em>' containment reference.
   * @see #setMapping(SetCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getAggregate_Mapping()
   * @model containment="true"
   * @generated
   */
  SetCommand getMapping();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Aggregate#getMapping <em>Mapping</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mapping</em>' containment reference.
   * @see #getMapping()
   * @generated
   */
  void setMapping(SetCommand value);

} // Aggregate
