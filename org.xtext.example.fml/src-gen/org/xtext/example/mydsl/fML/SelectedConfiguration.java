/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Selected Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.SelectedConfiguration#getConfig <em>Config</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getSelectedConfiguration()
 * @model
 * @generated
 */
public interface SelectedConfiguration extends SetCommand, ConfigurationCmd
{
  /**
   * Returns the value of the '<em><b>Config</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Config</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Config</em>' containment reference.
   * @see #setConfig(ConfigurationCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getSelectedConfiguration_Config()
   * @model containment="true"
   * @generated
   */
  ConfigurationCommand getConfig();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.SelectedConfiguration#getConfig <em>Config</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Config</em>' containment reference.
   * @see #getConfig()
   * @generated
   */
  void setConfig(ConfigurationCommand value);

} // SelectedConfiguration
