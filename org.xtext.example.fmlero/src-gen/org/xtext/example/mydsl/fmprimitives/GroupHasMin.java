/**
 */
package org.xtext.example.mydsl.fmprimitives;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Group Has Min</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.GroupHasMin#getMin <em>Min</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.GroupHasMin#getGroup <em>Group</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getGroupHasMin()
 * @model
 * @generated
 */
public interface GroupHasMin extends FeatureModelPrimitive
{
  /**
   * Returns the value of the '<em><b>Min</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Min</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Min</em>' attribute.
   * @see #setMin(String)
   * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getGroupHasMin_Min()
   * @model
   * @generated
   */
  String getMin();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fmprimitives.GroupHasMin#getMin <em>Min</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Min</em>' attribute.
   * @see #getMin()
   * @generated
   */
  void setMin(String value);

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
   * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getGroupHasMin_Group()
   * @model
   * @generated
   */
  FeatureGroup getGroup();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fmprimitives.GroupHasMin#getGroup <em>Group</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Group</em>' reference.
   * @see #getGroup()
   * @generated
   */
  void setGroup(FeatureGroup value);

} // GroupHasMin
