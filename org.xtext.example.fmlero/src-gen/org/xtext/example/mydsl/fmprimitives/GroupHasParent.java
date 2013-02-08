/**
 */
package org.xtext.example.mydsl.fmprimitives;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Group Has Parent</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.GroupHasParent#getParent <em>Parent</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.GroupHasParent#getGroup <em>Group</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getGroupHasParent()
 * @model
 * @generated
 */
public interface GroupHasParent extends FeatureModelPrimitive
{
  /**
   * Returns the value of the '<em><b>Parent</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parent</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parent</em>' reference.
   * @see #setParent(Feature)
   * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getGroupHasParent_Parent()
   * @model
   * @generated
   */
  Feature getParent();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fmprimitives.GroupHasParent#getParent <em>Parent</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parent</em>' reference.
   * @see #getParent()
   * @generated
   */
  void setParent(Feature value);

  /**
   * Returns the value of the '<em><b>Group</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Group</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Group</em>' reference.
   * @see #setGroup(FeatureGroup)
   * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getGroupHasParent_Group()
   * @model
   * @generated
   */
  FeatureGroup getGroup();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fmprimitives.GroupHasParent#getGroup <em>Group</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Group</em>' reference.
   * @see #getGroup()
   * @generated
   */
  void setGroup(FeatureGroup value);

} // GroupHasParent
