/**
 */
package fr.inria.familiar.fmlero.fmprimitives;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.FeatureModel#getId <em>Id</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.FeatureModel#getName <em>Name</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.FeatureModel#getFeatures <em>Features</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.FeatureModel#getPrimitives <em>Primitives</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getFeatureModel()
 * @model
 * @generated
 */
public interface FeatureModel extends EObject
{
  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(String)
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getFeatureModel_Id()
   * @model
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link fr.inria.familiar.fmlero.fmprimitives.FeatureModel#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getFeatureModel_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link fr.inria.familiar.fmlero.fmprimitives.FeatureModel#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Features</b></em>' containment reference list.
   * The list contents are of type {@link fr.inria.familiar.fmlero.fmprimitives.Feature}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Features</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Features</em>' containment reference list.
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getFeatureModel_Features()
   * @model containment="true"
   * @generated
   */
  EList<Feature> getFeatures();

  /**
   * Returns the value of the '<em><b>Primitives</b></em>' containment reference list.
   * The list contents are of type {@link fr.inria.familiar.fmlero.fmprimitives.FeatureModelPrimitive}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Primitives</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Primitives</em>' containment reference list.
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getFeatureModel_Primitives()
   * @model containment="true"
   * @generated
   */
  EList<FeatureModelPrimitive> getPrimitives();

} // FeatureModel
