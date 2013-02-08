/**
 */
package org.xtext.example.mydsl.fML.impl;

import org.eclipse.emf.ecore.EClass;

import org.xtext.example.mydsl.fML.FMLPackage;
import org.xtext.example.mydsl.fML.SetCommand;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Set Command</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class SetCommandImpl extends FMLAbstractCommandImpl implements SetCommand
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SetCommandImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return FMLPackage.eINSTANCE.getSetCommand();
  }

} //SetCommandImpl
