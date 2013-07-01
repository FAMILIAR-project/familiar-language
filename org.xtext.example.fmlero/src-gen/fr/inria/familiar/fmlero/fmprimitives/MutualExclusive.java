/**
 */
package fr.inria.familiar.fmlero.fmprimitives;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mutual Exclusive</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.MutualExclusive#getRelatedFeatures <em>Related Features</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getMutualExclusive()
 * @model
 * @generated
 */
public interface MutualExclusive extends FeatureModelPrimitive, UndirectedRelationship
{
  /**
   * Returns the value of the '<em><b>Related Features</b></em>' reference list.
   * The list contents are of type {@link fr.inria.familiar.fmlero.fmprimitives.Feature}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Related Features</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Related Features</em>' reference list.
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getMutualExclusive_RelatedFeatures()
   * @model
   * @generated
   */
  EList<Feature> getRelatedFeatures();

} // MutualExclusive
