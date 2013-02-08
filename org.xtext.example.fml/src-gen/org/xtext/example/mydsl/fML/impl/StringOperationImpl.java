/**
 */
package org.xtext.example.mydsl.fML.impl;

import org.eclipse.emf.ecore.EClass;

import org.xtext.example.mydsl.fML.FMLPackage;
import org.xtext.example.mydsl.fML.StringOperation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>String Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class StringOperationImpl extends CommandImpl implements StringOperation
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected StringOperationImpl()
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
    return FMLPackage.eINSTANCE.getStringOperation();
  }

} //StringOperationImpl
