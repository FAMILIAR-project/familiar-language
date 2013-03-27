/**
 */
package org.xtext.example.mydsl.fML;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Aggregate Merge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.AggregateMerge#isHierarchySpecified <em>Hierarchy Specified</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.AggregateMerge#getHierarchyStrategy <em>Hierarchy Strategy</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.AggregateMerge#getMode <em>Mode</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.AggregateMerge#getLfms <em>Lfms</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.AggregateMerge#getFms <em>Fms</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getAggregateMerge()
 * @model
 * @generated
 */
public interface AggregateMerge extends Command, FMCommand
{
  /**
   * Returns the value of the '<em><b>Hierarchy Specified</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Hierarchy Specified</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Hierarchy Specified</em>' attribute.
   * @see #setHierarchySpecified(boolean)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getAggregateMerge_HierarchySpecified()
   * @model
   * @generated
   */
  boolean isHierarchySpecified();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.AggregateMerge#isHierarchySpecified <em>Hierarchy Specified</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Hierarchy Specified</em>' attribute.
   * @see #isHierarchySpecified()
   * @generated
   */
  void setHierarchySpecified(boolean value);

  /**
   * Returns the value of the '<em><b>Hierarchy Strategy</b></em>' attribute.
   * The literals are from the enumeration {@link org.xtext.example.mydsl.fML.HierarchyStrategy}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Hierarchy Strategy</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Hierarchy Strategy</em>' attribute.
   * @see org.xtext.example.mydsl.fML.HierarchyStrategy
   * @see #setHierarchyStrategy(HierarchyStrategy)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getAggregateMerge_HierarchyStrategy()
   * @model
   * @generated
   */
  HierarchyStrategy getHierarchyStrategy();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.AggregateMerge#getHierarchyStrategy <em>Hierarchy Strategy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Hierarchy Strategy</em>' attribute.
   * @see org.xtext.example.mydsl.fML.HierarchyStrategy
   * @see #getHierarchyStrategy()
   * @generated
   */
  void setHierarchyStrategy(HierarchyStrategy value);

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
   * @see org.xtext.example.mydsl.fML.FMLPackage#getAggregateMerge_Mode()
   * @model
   * @generated
   */
  MergeMode getMode();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.AggregateMerge#getMode <em>Mode</em>}' attribute.
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
   * @see org.xtext.example.mydsl.fML.FMLPackage#getAggregateMerge_Lfms()
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
   * @see org.xtext.example.mydsl.fML.FMLPackage#getAggregateMerge_Fms()
   * @model containment="true"
   * @generated
   */
  LFMArgs getFms();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.AggregateMerge#getFms <em>Fms</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fms</em>' containment reference.
   * @see #getFms()
   * @generated
   */
  void setFms(LFMArgs value);

} // AggregateMerge
