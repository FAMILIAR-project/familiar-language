/**
 */
package org.xtext.example.mydsl.fmprimitives;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Or Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.OrGroup#getGroupHasParent <em>Group Has Parent</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.OrGroup#getGroupHasChild <em>Group Has Child</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.OrGroup#getGroupHasMax <em>Group Has Max</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.OrGroup#getGroupHasMin <em>Group Has Min</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getOrGroup()
 * @model
 * @generated
 */
public interface OrGroup extends FeatureModelPrimitive, FeatureGroup
{
  /**
   * Returns the value of the '<em><b>Group Has Parent</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Group Has Parent</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Group Has Parent</em>' reference.
   * @see #setGroupHasParent(GroupHasParent)
   * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getOrGroup_GroupHasParent()
   * @model
   * @generated
   */
  GroupHasParent getGroupHasParent();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fmprimitives.OrGroup#getGroupHasParent <em>Group Has Parent</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Group Has Parent</em>' reference.
   * @see #getGroupHasParent()
   * @generated
   */
  void setGroupHasParent(GroupHasParent value);

  /**
   * Returns the value of the '<em><b>Group Has Child</b></em>' reference list.
   * The list contents are of type {@link org.xtext.example.mydsl.fmprimitives.GroupHasChild}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Group Has Child</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Group Has Child</em>' reference list.
   * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getOrGroup_GroupHasChild()
   * @model
   * @generated
   */
  EList<GroupHasChild> getGroupHasChild();

  /**
   * Returns the value of the '<em><b>Group Has Max</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Group Has Max</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Group Has Max</em>' reference.
   * @see #setGroupHasMax(GroupHasMax)
   * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getOrGroup_GroupHasMax()
   * @model
   * @generated
   */
  GroupHasMax getGroupHasMax();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fmprimitives.OrGroup#getGroupHasMax <em>Group Has Max</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Group Has Max</em>' reference.
   * @see #getGroupHasMax()
   * @generated
   */
  void setGroupHasMax(GroupHasMax value);

  /**
   * Returns the value of the '<em><b>Group Has Min</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Group Has Min</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Group Has Min</em>' reference.
   * @see #setGroupHasMin(GroupHasMin)
   * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getOrGroup_GroupHasMin()
   * @model
   * @generated
   */
  GroupHasMin getGroupHasMin();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fmprimitives.OrGroup#getGroupHasMin <em>Group Has Min</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Group Has Min</em>' reference.
   * @see #getGroupHasMin()
   * @generated
   */
  void setGroupHasMin(GroupHasMin value);

} // OrGroup
