/**
 */
package org.xtext.example.mydsl.fML.impl;

import org.eclipse.emf.ecore.EClass;

import org.xtext.example.mydsl.fML.DoubleCommand;
import org.xtext.example.mydsl.fML.FMLPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Double Command</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class DoubleCommandImpl extends IntegerCommandImpl implements DoubleCommand
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DoubleCommandImpl()
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
    return FMLPackage.eINSTANCE.getDoubleCommand();
  }

} //DoubleCommandImpl
