/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>As FM</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.AsFM#getConf <em>Conf</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getAsFM()
 * @model
 * @generated
 */
public interface AsFM extends Command, FMCommand
{
  /**
   * Returns the value of the '<em><b>Conf</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Conf</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Conf</em>' containment reference.
   * @see #setConf(ConfigurationCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getAsFM_Conf()
   * @model containment="true"
   * @generated
   */
  ConfigurationCommand getConf();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.AsFM#getConf <em>Conf</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Conf</em>' containment reference.
   * @see #getConf()
   * @generated
   */
  void setConf(ConfigurationCommand value);

} // AsFM
