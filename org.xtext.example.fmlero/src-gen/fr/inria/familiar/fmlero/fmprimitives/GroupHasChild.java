/**
 */
package fr.inria.familiar.fmlero.fmprimitives;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Group Has Child</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.GroupHasChild#getChild <em>Child</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.GroupHasChild#getGroup <em>Group</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getGroupHasChild()
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
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getGroupHasChild_Child()
   * @model
   * @generated
   */
  Feature getChild();

  /**
   * Sets the value of the '{@link fr.inria.familiar.fmlero.fmprimitives.GroupHasChild#getChild <em>Child</em>}' reference.
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
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getGroupHasChild_Group()
   * @model
   * @generated
   */
  FeatureGroup getGroup();

  /**
   * Sets the value of the '{@link fr.inria.familiar.fmlero.fmprimitives.GroupHasChild#getGroup <em>Group</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Group</em>' reference.
   * @see #getGroup()
   * @generated
   */
  void setGroup(FeatureGroup value);

} // GroupHasChild
