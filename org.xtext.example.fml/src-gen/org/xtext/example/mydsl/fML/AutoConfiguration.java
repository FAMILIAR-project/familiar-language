/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Auto Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.AutoConfiguration#getConfig <em>Config</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.AutoConfiguration#getMode <em>Mode</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getAutoConfiguration()
 * @model
 * @generated
 */
public interface AutoConfiguration extends ConfigurationCmd
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
   * @see org.xtext.example.mydsl.fML.FMLPackage#getAutoConfiguration_Config()
   * @model containment="true"
   * @generated
   */
  ConfigurationCommand getConfig();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.AutoConfiguration#getConfig <em>Config</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Config</em>' containment reference.
   * @see #getConfig()
   * @generated
   */
  void setConfig(ConfigurationCommand value);

  /**
   * Returns the value of the '<em><b>Mode</b></em>' attribute.
   * The literals are from the enumeration {@link org.xtext.example.mydsl.fML.AutoConfMode}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mode</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mode</em>' attribute.
   * @see org.xtext.example.mydsl.fML.AutoConfMode
   * @see #setMode(AutoConfMode)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getAutoConfiguration_Mode()
   * @model
   * @generated
   */
  AutoConfMode getMode();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.AutoConfiguration#getMode <em>Mode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mode</em>' attribute.
   * @see org.xtext.example.mydsl.fML.AutoConfMode
   * @see #getMode()
   * @generated
   */
  void setMode(AutoConfMode value);

} // AutoConfiguration
