/**
 */
package org.xtext.example.mydsl.fmprimitives;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Group Has Child</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.GroupHasChild#getChild <em>Child</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.GroupHasChild#getGroup <em>Group</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getGroupHasChild()
 * @model
 * @generated
 */
public interface GroupHasChild extends FeatureModelPrimitive
{
  /**
   * Returns the value of the '<em><b>Child</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Child</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Child</em>' reference.
   * @see #setChild(Feature)
   * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getGroupHasChild_Child()
   * @model
   * @generated
   */
  Feature getChild();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fmprimitives.GroupHasChild#getChild <em>Child</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Child</em>' reference.
   * @see #getChild()
   * @generated
   */
  void setChild(Feature value);

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
   * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getGroupHasChild_Group()
   * @model
   * @generated
   */
  FeatureGroup getGroup();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fmprimitives.GroupHasChild#getGroup <em>Group</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Group</em>' reference.
   * @see #getGroup()
   * @generated
   */
  void setGroup(FeatureGroup value);

} // GroupHasChild
