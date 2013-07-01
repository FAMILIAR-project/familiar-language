/**
 */
package fr.inria.familiar.fmlero.fmprimitives;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Alternative Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.AlternativeGroup#getGroupHasParent <em>Group Has Parent</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.AlternativeGroup#getGroupHasChild <em>Group Has Child</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.AlternativeGroup#getGroupHasMax <em>Group Has Max</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.AlternativeGroup#getGroupHasMin <em>Group Has Min</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getAlternativeGroup()
 * @model
 * @generated
 */
public interface AlternativeGroup extends FeatureModelPrimitive, FeatureGroup
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
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getAlternativeGroup_GroupHasParent()
   * @model
   * @generated
   */
  GroupHasParent getGroupHasParent();

  /**
   * Sets the value of the '{@link fr.inria.familiar.fmlero.fmprimitives.AlternativeGroup#getGroupHasParent <em>Group Has Parent</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Group Has Parent</em>' reference.
   * @see #getGroupHasParent()
   * @generated
   */
  void setGroupHasParent(GroupHasParent value);

  /**
   * Returns the value of the '<em><b>Group Has Child</b></em>' reference list.
   * The list contents are of type {@link fr.inria.familiar.fmlero.fmprimitives.GroupHasChild}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Group Has Child</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Group Has Child</em>' reference list.
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getAlternativeGroup_GroupHasChild()
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
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getAlternativeGroup_GroupHasMax()
   * @model
   * @generated
   */
  GroupHasMax getGroupHasMax();

  /**
   * Sets the value of the '{@link fr.inria.familiar.fmlero.fmprimitives.AlternativeGroup#getGroupHasMax <em>Group Has Max</em>}' reference.
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
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getAlternativeGroup_GroupHasMin()
   * @model
   * @generated
   */
  GroupHasMin getGroupHasMin();

  /**
   * Sets the value of the '{@link fr.inria.familiar.fmlero.fmprimitives.AlternativeGroup#getGroupHasMin <em>Group Has Min</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Group Has Min</em>' reference.
   * @see #getGroupHasMin()
   * @generated
   */
  void setGroupHasMin(GroupHasMin value);

} // AlternativeGroup
