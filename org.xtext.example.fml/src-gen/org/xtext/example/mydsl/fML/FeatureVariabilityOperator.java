/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Variability Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.FeatureVariabilityOperator#getVal <em>Val</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getFeatureVariabilityOperator()
 * @model
 * @generated
 */
public interface FeatureVariabilityOperator extends Command, VariabilityOpCommand
{
  /**
   * Returns the value of the '<em><b>Val</b></em>' attribute.
   * The literals are from the enumeration {@link org.xtext.example.mydsl.fML.FeatureEdgeKind}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Val</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Val</em>' attribute.
   * @see org.xtext.example.mydsl.fML.FeatureEdgeKind
   * @see #setVal(FeatureEdgeKind)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getFeatureVariabilityOperator_Val()
   * @model
   * @generated
   */
  FeatureEdgeKind getVal();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.FeatureVariabilityOperator#getVal <em>Val</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Val</em>' attribute.
   * @see org.xtext.example.mydsl.fML.FeatureEdgeKind
   * @see #getVal()
   * @generated
   */
  void setVal(FeatureEdgeKind value);

} // FeatureVariabilityOperator
