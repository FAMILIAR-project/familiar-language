/**
 */
package org.xtext.example.mydsl.fML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Hierarchy Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.HierarchySpecification#getHierarchy <em>Hierarchy</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.HierarchySpecification#getFeatures <em>Features</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getHierarchySpecification()
 * @model
 * @generated
 */
public interface HierarchySpecification extends EObject
{
  /**
   * Returns the value of the '<em><b>Hierarchy</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Hierarchy</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Hierarchy</em>' containment reference.
   * @see #setHierarchy(Hierarchy)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getHierarchySpecification_Hierarchy()
   * @model containment="true"
   * @generated
   */
  Hierarchy getHierarchy();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.HierarchySpecification#getHierarchy <em>Hierarchy</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Hierarchy</em>' containment reference.
   * @see #getHierarchy()
   * @generated
   */
  void setHierarchy(Hierarchy value);

  /**
   * Returns the value of the '<em><b>Features</b></em>' containment reference list.
   * The list contents are of type {@link org.xtext.example.mydsl.fML.HProduction}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Features</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Features</em>' containment reference list.
   * @see org.xtext.example.mydsl.fML.FMLPackage#getHierarchySpecification_Features()
   * @model containment="true"
   * @generated
   */
  EList<HProduction> getFeatures();

} // HierarchySpecification
