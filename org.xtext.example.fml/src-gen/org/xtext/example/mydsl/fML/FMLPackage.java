/**
 */
package org.xtext.example.mydsl.fML;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.xtext.example.mydsl.fML.FMLFactory
 * @model kind="package"
 * @generated
 */
public interface FMLPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "fML";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.xtext.org/example/mydsl/FML";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "fML";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  FMLPackage eINSTANCE = org.xtext.example.mydsl.fML.impl.FMLPackageImpl.init();

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.FamiliarScriptImpl <em>Familiar Script</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.FamiliarScriptImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getFamiliarScript()
   * @generated
   */
  int FAMILIAR_SCRIPT = 0;

  /**
   * The feature id for the '<em><b>Params</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FAMILIAR_SCRIPT__PARAMS = 0;

  /**
   * The feature id for the '<em><b>Cmds</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FAMILIAR_SCRIPT__CMDS = 1;

  /**
   * The feature id for the '<em><b>Exports</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FAMILIAR_SCRIPT__EXPORTS = 2;

  /**
   * The number of structural features of the '<em>Familiar Script</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FAMILIAR_SCRIPT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ScriptCommandImpl <em>Script Command</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ScriptCommandImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getScriptCommand()
   * @generated
   */
  int SCRIPT_COMMAND = 1;

  /**
   * The feature id for the '<em><b>Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCRIPT_COMMAND__VAR = 0;

  /**
   * The feature id for the '<em><b>Meta ID</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCRIPT_COMMAND__META_ID = 1;

  /**
   * The feature id for the '<em><b>Cmd</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCRIPT_COMMAND__CMD = 2;

  /**
   * The number of structural features of the '<em>Script Command</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCRIPT_COMMAND_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ComplexCommandImpl <em>Complex Command</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ComplexCommandImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getComplexCommand()
   * @generated
   */
  int COMPLEX_COMMAND = 2;

  /**
   * The feature id for the '<em><b>Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPLEX_COMMAND__VAR = SCRIPT_COMMAND__VAR;

  /**
   * The feature id for the '<em><b>Meta ID</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPLEX_COMMAND__META_ID = SCRIPT_COMMAND__META_ID;

  /**
   * The feature id for the '<em><b>Cmd</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPLEX_COMMAND__CMD = SCRIPT_COMMAND__CMD;

  /**
   * The feature id for the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPLEX_COMMAND__LEFT = SCRIPT_COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Not</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPLEX_COMMAND__NOT = SCRIPT_COMMAND_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Batom</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPLEX_COMMAND__BATOM = SCRIPT_COMMAND_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Complex Command</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPLEX_COMMAND_FEATURE_COUNT = SCRIPT_COMMAND_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.FMLAbstractCommandImpl <em>Abstract Command</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.FMLAbstractCommandImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getFMLAbstractCommand()
   * @generated
   */
  int FML_ABSTRACT_COMMAND = 15;

  /**
   * The number of structural features of the '<em>Abstract Command</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FML_ABSTRACT_COMMAND_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.CommandImpl <em>Command</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.CommandImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getCommand()
   * @generated
   */
  int COMMAND = 3;

  /**
   * The number of structural features of the '<em>Command</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMMAND_FEATURE_COUNT = FML_ABSTRACT_COMMAND_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.IntegerExprImpl <em>Integer Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.IntegerExprImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getIntegerExpr()
   * @generated
   */
  int INTEGER_EXPR = 4;

  /**
   * The number of structural features of the '<em>Integer Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTEGER_EXPR_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.BooleanExprImpl <em>Boolean Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.BooleanExprImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getBooleanExpr()
   * @generated
   */
  int BOOLEAN_EXPR = 5;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPR__VAL = COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Boolean Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPR_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.IdentifierExprImpl <em>Identifier Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.IdentifierExprImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getIdentifierExpr()
   * @generated
   */
  int IDENTIFIER_EXPR = 6;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IDENTIFIER_EXPR__VAL = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Meta ID</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IDENTIFIER_EXPR__META_ID = COMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Identifier Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IDENTIFIER_EXPR_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.StringExprImpl <em>String Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.StringExprImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getStringExpr()
   * @generated
   */
  int STRING_EXPR = 7;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_EXPR__VAL = COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>String Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_EXPR_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.SetExprImpl <em>Set Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.SetExprImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getSetExpr()
   * @generated
   */
  int SET_EXPR = 8;

  /**
   * The feature id for the '<em><b>E</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_EXPR__E = COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Set Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_EXPR_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.AtomicConstraintExprImpl <em>Atomic Constraint Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.AtomicConstraintExprImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getAtomicConstraintExpr()
   * @generated
   */
  int ATOMIC_CONSTRAINT_EXPR = 9;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATOMIC_CONSTRAINT_EXPR__EXPR = COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Atomic Constraint Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATOMIC_CONSTRAINT_EXPR_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ConstraintExprImpl <em>Constraint Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ConstraintExprImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getConstraintExpr()
   * @generated
   */
  int CONSTRAINT_EXPR = 10;

  /**
   * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRAINT_EXPR__CONSTRAINTS = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRAINT_EXPR__FM = COMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Constraint Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRAINT_EXPR_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.FeatureVariabilityOperatorImpl <em>Feature Variability Operator</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.FeatureVariabilityOperatorImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getFeatureVariabilityOperator()
   * @generated
   */
  int FEATURE_VARIABILITY_OPERATOR = 11;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_VARIABILITY_OPERATOR__VAL = COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Feature Variability Operator</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_VARIABILITY_OPERATOR_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.IfConditionImpl <em>If Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.IfConditionImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getIfCondition()
   * @generated
   */
  int IF_CONDITION = 12;

  /**
   * The feature id for the '<em><b>Bexpr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_CONDITION__BEXPR = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Then</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_CONDITION__THEN = COMMAND_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Else</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_CONDITION__ELSE = COMMAND_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>If Condition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_CONDITION_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ForeachSetImpl <em>Foreach Set</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ForeachSetImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getForeachSet()
   * @generated
   */
  int FOREACH_SET = 13;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOREACH_SET__VAL = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOREACH_SET__VAR = COMMAND_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Exprs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOREACH_SET__EXPRS = COMMAND_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Foreach Set</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOREACH_SET_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.lTypeImpl <em>lType</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.lTypeImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getlType()
   * @generated
   */
  int LTYPE = 14;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LTYPE__VAL = 0;

  /**
   * The number of structural features of the '<em>lType</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LTYPE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.FMCommandImpl <em>FM Command</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.FMCommandImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getFMCommand()
   * @generated
   */
  int FM_COMMAND = 16;

  /**
   * The number of structural features of the '<em>FM Command</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FM_COMMAND_FEATURE_COUNT = FML_ABSTRACT_COMMAND_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.FTCommandImpl <em>FT Command</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.FTCommandImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getFTCommand()
   * @generated
   */
  int FT_COMMAND = 17;

  /**
   * The number of structural features of the '<em>FT Command</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FT_COMMAND_FEATURE_COUNT = FML_ABSTRACT_COMMAND_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.BCommandImpl <em>BCommand</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.BCommandImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getBCommand()
   * @generated
   */
  int BCOMMAND = 18;

  /**
   * The number of structural features of the '<em>BCommand</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BCOMMAND_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.StrCommandImpl <em>Str Command</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.StrCommandImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getStrCommand()
   * @generated
   */
  int STR_COMMAND = 19;

  /**
   * The number of structural features of the '<em>Str Command</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STR_COMMAND_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ConfigurationCommandImpl <em>Configuration Command</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ConfigurationCommandImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getConfigurationCommand()
   * @generated
   */
  int CONFIGURATION_COMMAND = 20;

  /**
   * The number of structural features of the '<em>Configuration Command</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIGURATION_COMMAND_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.SetCommandImpl <em>Set Command</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.SetCommandImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getSetCommand()
   * @generated
   */
  int SET_COMMAND = 21;

  /**
   * The number of structural features of the '<em>Set Command</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_COMMAND_FEATURE_COUNT = FML_ABSTRACT_COMMAND_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.LeavesImpl <em>Leaves</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.LeavesImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getLeaves()
   * @generated
   */
  int LEAVES = 22;

  /**
   * The feature id for the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEAVES__FM = COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Leaves</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEAVES_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ConstraintCommandImpl <em>Constraint Command</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ConstraintCommandImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getConstraintCommand()
   * @generated
   */
  int CONSTRAINT_COMMAND = 23;

  /**
   * The number of structural features of the '<em>Constraint Command</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRAINT_COMMAND_FEATURE_COUNT = FML_ABSTRACT_COMMAND_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.GetConstraintsImpl <em>Get Constraints</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.GetConstraintsImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getGetConstraints()
   * @generated
   */
  int GET_CONSTRAINTS = 24;

  /**
   * The feature id for the '<em><b>Kind Of Get</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GET_CONSTRAINTS__KIND_OF_GET = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GET_CONSTRAINTS__FM = COMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Get Constraints</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GET_CONSTRAINTS_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ComputeConstraintsImpl <em>Compute Constraints</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ComputeConstraintsImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getComputeConstraints()
   * @generated
   */
  int COMPUTE_CONSTRAINTS = 25;

  /**
   * The feature id for the '<em><b>Kind Of Compute</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPUTE_CONSTRAINTS__KIND_OF_COMPUTE = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPUTE_CONSTRAINTS__FM = COMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Compute Constraints</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPUTE_CONSTRAINTS_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.GetFGroupsImpl <em>Get FGroups</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.GetFGroupsImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getGetFGroups()
   * @generated
   */
  int GET_FGROUPS = 26;

  /**
   * The feature id for the '<em><b>Kind Of Groups</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GET_FGROUPS__KIND_OF_GROUPS = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GET_FGROUPS__FM = COMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Get FGroups</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GET_FGROUPS_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ComputeFGroupsImpl <em>Compute FGroups</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ComputeFGroupsImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getComputeFGroups()
   * @generated
   */
  int COMPUTE_FGROUPS = 27;

  /**
   * The feature id for the '<em><b>Kind Of Groups</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPUTE_FGROUPS__KIND_OF_GROUPS = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPUTE_FGROUPS__FM = COMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Compute FGroups</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPUTE_FGROUPS_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.PairwiseCommandImpl <em>Pairwise Command</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.PairwiseCommandImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getPairwiseCommand()
   * @generated
   */
  int PAIRWISE_COMMAND = 28;

  /**
   * The feature id for the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PAIRWISE_COMMAND__FM = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Minimization</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PAIRWISE_COMMAND__MINIMIZATION = COMMAND_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Partial</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PAIRWISE_COMMAND__PARTIAL = COMMAND_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Pairwise Command</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PAIRWISE_COMMAND_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.IntegerCommandImpl <em>Integer Command</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.IntegerCommandImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getIntegerCommand()
   * @generated
   */
  int INTEGER_COMMAND = 29;

  /**
   * The number of structural features of the '<em>Integer Command</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTEGER_COMMAND_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.DoubleCommandImpl <em>Double Command</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.DoubleCommandImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getDoubleCommand()
   * @generated
   */
  int DOUBLE_COMMAND = 30;

  /**
   * The number of structural features of the '<em>Double Command</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOUBLE_COMMAND_FEATURE_COUNT = INTEGER_COMMAND_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.VariabilityOpCommandImpl <em>Variability Op Command</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.VariabilityOpCommandImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getVariabilityOpCommand()
   * @generated
   */
  int VARIABILITY_OP_COMMAND = 31;

  /**
   * The number of structural features of the '<em>Variability Op Command</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABILITY_OP_COMMAND_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.AnalysisOperationImpl <em>Analysis Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.AnalysisOperationImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getAnalysisOperation()
   * @generated
   */
  int ANALYSIS_OPERATION = 32;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANALYSIS_OPERATION__OP = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANALYSIS_OPERATION__FM = COMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Analysis Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANALYSIS_OPERATION_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.SetOperationsImpl <em>Set Operations</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.SetOperationsImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getSetOperations()
   * @generated
   */
  int SET_OPERATIONS = 33;

  /**
   * The number of structural features of the '<em>Set Operations</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_OPERATIONS_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.SetCardImpl <em>Set Card</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.SetCardImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getSetCard()
   * @generated
   */
  int SET_CARD = 34;

  /**
   * The feature id for the '<em><b>Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_CARD__SET = INTEGER_COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Set Card</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_CARD_FEATURE_COUNT = INTEGER_COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.SetBelongsImpl <em>Set Belongs</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.SetBelongsImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getSetBelongs()
   * @generated
   */
  int SET_BELONGS = 35;

  /**
   * The feature id for the '<em><b>Setl</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_BELONGS__SETL = BCOMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Setr</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_BELONGS__SETR = BCOMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Set Belongs</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_BELONGS_FEATURE_COUNT = BCOMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.SetUnionOrIntersectionImpl <em>Set Union Or Intersection</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.SetUnionOrIntersectionImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getSetUnionOrIntersection()
   * @generated
   */
  int SET_UNION_OR_INTERSECTION = 36;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_UNION_OR_INTERSECTION__OP = SET_COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Setl</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_UNION_OR_INTERSECTION__SETL = SET_COMMAND_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Setr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_UNION_OR_INTERSECTION__SETR = SET_COMMAND_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Set Union Or Intersection</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_UNION_OR_INTERSECTION_FEATURE_COUNT = SET_COMMAND_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.SetEmptyImpl <em>Set Empty</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.SetEmptyImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getSetEmpty()
   * @generated
   */
  int SET_EMPTY = 37;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_EMPTY__VAL = SET_COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Set Empty</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_EMPTY_FEATURE_COUNT = SET_COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.SetAddOrRemoveImpl <em>Set Add Or Remove</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.SetAddOrRemoveImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getSetAddOrRemove()
   * @generated
   */
  int SET_ADD_OR_REMOVE = 38;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_ADD_OR_REMOVE__OP = SET_OPERATIONS_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Setl</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_ADD_OR_REMOVE__SETL = SET_OPERATIONS_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_ADD_OR_REMOVE__VAR = SET_OPERATIONS_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Set Add Or Remove</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_ADD_OR_REMOVE_FEATURE_COUNT = SET_OPERATIONS_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.IsEmptySetImpl <em>Is Empty Set</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.IsEmptySetImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getIsEmptySet()
   * @generated
   */
  int IS_EMPTY_SET = 39;

  /**
   * The feature id for the '<em><b>Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IS_EMPTY_SET__SET = BCOMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Is Empty Set</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IS_EMPTY_SET_FEATURE_COUNT = BCOMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.SetToNamesImpl <em>Set To Names</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.SetToNamesImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getSetToNames()
   * @generated
   */
  int SET_TO_NAMES = 40;

  /**
   * The feature id for the '<em><b>Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_TO_NAMES__SET = SET_COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Set To Names</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_TO_NAMES_FEATURE_COUNT = SET_COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.FeatureOperationImpl <em>Feature Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.FeatureOperationImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getFeatureOperation()
   * @generated
   */
  int FEATURE_OPERATION = 41;

  /**
   * The feature id for the '<em><b>Op</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_OPERATION__OP = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Feature</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_OPERATION__FEATURE = COMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Feature Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_OPERATION_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.AncestorFeatureImpl <em>Ancestor Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.AncestorFeatureImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getAncestorFeature()
   * @generated
   */
  int ANCESTOR_FEATURE = 42;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANCESTOR_FEATURE__VAL = 0;

  /**
   * The number of structural features of the '<em>Ancestor Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANCESTOR_FEATURE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.DescendantFeatureImpl <em>Descendant Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.DescendantFeatureImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getDescendantFeature()
   * @generated
   */
  int DESCENDANT_FEATURE = 43;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESCENDANT_FEATURE__VAL = 0;

  /**
   * The number of structural features of the '<em>Descendant Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESCENDANT_FEATURE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ChildrenFeatureImpl <em>Children Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ChildrenFeatureImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getChildrenFeature()
   * @generated
   */
  int CHILDREN_FEATURE = 44;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CHILDREN_FEATURE__VAL = 0;

  /**
   * The number of structural features of the '<em>Children Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CHILDREN_FEATURE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.SiblingFeatureImpl <em>Sibling Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.SiblingFeatureImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getSiblingFeature()
   * @generated
   */
  int SIBLING_FEATURE = 45;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIBLING_FEATURE__VAL = 0;

  /**
   * The number of structural features of the '<em>Sibling Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIBLING_FEATURE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ParentFeatureImpl <em>Parent Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ParentFeatureImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getParentFeature()
   * @generated
   */
  int PARENT_FEATURE = 46;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARENT_FEATURE__VAL = 0;

  /**
   * The number of structural features of the '<em>Parent Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARENT_FEATURE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.NameFeatureImpl <em>Name Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.NameFeatureImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getNameFeature()
   * @generated
   */
  int NAME_FEATURE = 47;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_FEATURE__VAL = 0;

  /**
   * The number of structural features of the '<em>Name Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_FEATURE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.FMFeatureImpl <em>FM Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.FMFeatureImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getFMFeature()
   * @generated
   */
  int FM_FEATURE = 48;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FM_FEATURE__VAL = FM_COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>FM Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FM_FEATURE_FEATURE_COUNT = FM_COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.FeatureOperatorImpl <em>Feature Operator</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.FeatureOperatorImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getFeatureOperator()
   * @generated
   */
  int FEATURE_OPERATOR = 49;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_OPERATOR__VAL = 0;

  /**
   * The number of structural features of the '<em>Feature Operator</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_OPERATOR_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.StringOperationImpl <em>String Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.StringOperationImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getStringOperation()
   * @generated
   */
  int STRING_OPERATION = 50;

  /**
   * The number of structural features of the '<em>String Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_OPERATION_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.StringInitImpl <em>String Init</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.StringInitImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getStringInit()
   * @generated
   */
  int STRING_INIT = 51;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_INIT__VAL = STR_COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>String Init</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_INIT_FEATURE_COUNT = STR_COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.StringConcatImpl <em>String Concat</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.StringConcatImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getStringConcat()
   * @generated
   */
  int STRING_CONCAT = 52;

  /**
   * The feature id for the '<em><b>Lstr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_CONCAT__LSTR = STR_COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Rstr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_CONCAT__RSTR = STR_COMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>String Concat</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_CONCAT_FEATURE_COUNT = STR_COMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.StringSubstringImpl <em>String Substring</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.StringSubstringImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getStringSubstring()
   * @generated
   */
  int STRING_SUBSTRING = 53;

  /**
   * The feature id for the '<em><b>Str</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_SUBSTRING__STR = STR_COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Begin</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_SUBSTRING__BEGIN = STR_COMMAND_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>End</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_SUBSTRING__END = STR_COMMAND_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>String Substring</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_SUBSTRING_FEATURE_COUNT = STR_COMMAND_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.StringIndexOfImpl <em>String Index Of</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.StringIndexOfImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getStringIndexOf()
   * @generated
   */
  int STRING_INDEX_OF = 54;

  /**
   * The feature id for the '<em><b>Str</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_INDEX_OF__STR = INTEGER_COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Schar</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_INDEX_OF__SCHAR = INTEGER_COMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>String Index Of</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_INDEX_OF_FEATURE_COUNT = INTEGER_COMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.StringLengthImpl <em>String Length</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.StringLengthImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getStringLength()
   * @generated
   */
  int STRING_LENGTH = 55;

  /**
   * The feature id for the '<em><b>Str</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_LENGTH__STR = INTEGER_COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>String Length</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_LENGTH_FEATURE_COUNT = INTEGER_COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.CompareImpl <em>Compare</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.CompareImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getCompare()
   * @generated
   */
  int COMPARE = 56;

  /**
   * The feature id for the '<em><b>Fm left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPARE__FM_LEFT = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Fm right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPARE__FM_RIGHT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Compare</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPARE_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ParameterImpl <em>Parameter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ParameterImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getParameter()
   * @generated
   */
  int PARAMETER = 57;

  /**
   * The feature id for the '<em><b>Param</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__PARAM = 0;

  /**
   * The feature id for the '<em><b>Typed</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__TYPED = 1;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__TYPE = 2;

  /**
   * The number of structural features of the '<em>Parameter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.LoadGenericImpl <em>Load Generic</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.LoadGenericImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getLoadGeneric()
   * @generated
   */
  int LOAD_GENERIC = 58;

  /**
   * The feature id for the '<em><b>Stream</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOAD_GENERIC__STREAM = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Params</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOAD_GENERIC__PARAMS = COMMAND_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Ns</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOAD_GENERIC__NS = COMMAND_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Load Generic</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOAD_GENERIC_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.CTCRCommandImpl <em>CTCR Command</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.CTCRCommandImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getCTCRCommand()
   * @generated
   */
  int CTCR_COMMAND = 59;

  /**
   * The feature id for the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CTCR_COMMAND__FM = COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>CTCR Command</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CTCR_COMMAND_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.MergeImpl <em>Merge</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.MergeImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getMerge()
   * @generated
   */
  int MERGE = 60;

  /**
   * The feature id for the '<em><b>Backend</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MERGE__BACKEND = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Mode</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MERGE__MODE = COMMAND_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Lfms</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MERGE__LFMS = COMMAND_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Fms</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MERGE__FMS = COMMAND_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Merge</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MERGE_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.LFMArgsImpl <em>LFM Args</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.LFMArgsImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getLFMArgs()
   * @generated
   */
  int LFM_ARGS = 61;

  /**
   * The feature id for the '<em><b>Lfms</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LFM_ARGS__LFMS = 0;

  /**
   * The number of structural features of the '<em>LFM Args</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LFM_ARGS_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.AggregateMergeImpl <em>Aggregate Merge</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.AggregateMergeImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getAggregateMerge()
   * @generated
   */
  int AGGREGATE_MERGE = 62;

  /**
   * The feature id for the '<em><b>Hierarchy Specified</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATE_MERGE__HIERARCHY_SPECIFIED = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Hierarchy Strategy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATE_MERGE__HIERARCHY_STRATEGY = COMMAND_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Mode</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATE_MERGE__MODE = COMMAND_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Lfms</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATE_MERGE__LFMS = COMMAND_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Fms</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATE_MERGE__FMS = COMMAND_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>Aggregate Merge</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATE_MERGE_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.SynthesisImpl <em>Synthesis</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.SynthesisImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getSynthesis()
   * @generated
   */
  int SYNTHESIS = 63;

  /**
   * The feature id for the '<em><b>Interactive</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SYNTHESIS__INTERACTIVE = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SYNTHESIS__FM = COMMAND_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Kst</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SYNTHESIS__KST = COMMAND_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Synthesis</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SYNTHESIS_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.KnowledgeSpecificationImpl <em>Knowledge Specification</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.KnowledgeSpecificationImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getKnowledgeSpecification()
   * @generated
   */
  int KNOWLEDGE_SPECIFICATION = 64;

  /**
   * The feature id for the '<em><b>Hierarchy</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int KNOWLEDGE_SPECIFICATION__HIERARCHY = 0;

  /**
   * The feature id for the '<em><b>Groups</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int KNOWLEDGE_SPECIFICATION__GROUPS = 1;

  /**
   * The feature id for the '<em><b>Constraints</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int KNOWLEDGE_SPECIFICATION__CONSTRAINTS = 2;

  /**
   * The number of structural features of the '<em>Knowledge Specification</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int KNOWLEDGE_SPECIFICATION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.HierarchySpecificationImpl <em>Hierarchy Specification</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.HierarchySpecificationImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getHierarchySpecification()
   * @generated
   */
  int HIERARCHY_SPECIFICATION = 65;

  /**
   * The feature id for the '<em><b>Hierarchy</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HIERARCHY_SPECIFICATION__HIERARCHY = 0;

  /**
   * The feature id for the '<em><b>Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HIERARCHY_SPECIFICATION__FEATURES = 1;

  /**
   * The number of structural features of the '<em>Hierarchy Specification</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HIERARCHY_SPECIFICATION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.HProductionImpl <em>HProduction</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.HProductionImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getHProduction()
   * @generated
   */
  int HPRODUCTION = 66;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HPRODUCTION__NAME = 0;

  /**
   * The feature id for the '<em><b>Features</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HPRODUCTION__FEATURES = 1;

  /**
   * The number of structural features of the '<em>HProduction</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HPRODUCTION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.GroupsSpecificationImpl <em>Groups Specification</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.GroupsSpecificationImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getGroupsSpecification()
   * @generated
   */
  int GROUPS_SPECIFICATION = 67;

  /**
   * The feature id for the '<em><b>Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUPS_SPECIFICATION__GROUPS = 0;

  /**
   * The number of structural features of the '<em>Groups Specification</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUPS_SPECIFICATION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.GroupSpecImpl <em>Group Spec</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.GroupSpecImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getGroupSpec()
   * @generated
   */
  int GROUP_SPEC = 68;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_SPEC__NAME = 0;

  /**
   * The feature id for the '<em><b>Features</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_SPEC__FEATURES = 1;

  /**
   * The number of structural features of the '<em>Group Spec</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_SPEC_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.XorGroupSpecImpl <em>Xor Group Spec</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.XorGroupSpecImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getXorGroupSpec()
   * @generated
   */
  int XOR_GROUP_SPEC = 69;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XOR_GROUP_SPEC__NAME = GROUP_SPEC__NAME;

  /**
   * The feature id for the '<em><b>Features</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XOR_GROUP_SPEC__FEATURES = GROUP_SPEC__FEATURES;

  /**
   * The number of structural features of the '<em>Xor Group Spec</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XOR_GROUP_SPEC_FEATURE_COUNT = GROUP_SPEC_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.MtxGroupSpecImpl <em>Mtx Group Spec</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.MtxGroupSpecImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getMtxGroupSpec()
   * @generated
   */
  int MTX_GROUP_SPEC = 70;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MTX_GROUP_SPEC__NAME = GROUP_SPEC__NAME;

  /**
   * The feature id for the '<em><b>Features</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MTX_GROUP_SPEC__FEATURES = GROUP_SPEC__FEATURES;

  /**
   * The number of structural features of the '<em>Mtx Group Spec</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MTX_GROUP_SPEC_FEATURE_COUNT = GROUP_SPEC_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.OrGroupSpecImpl <em>Or Group Spec</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.OrGroupSpecImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getOrGroupSpec()
   * @generated
   */
  int OR_GROUP_SPEC = 71;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_GROUP_SPEC__NAME = GROUP_SPEC__NAME;

  /**
   * The feature id for the '<em><b>Features</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_GROUP_SPEC__FEATURES = GROUP_SPEC__FEATURES;

  /**
   * The number of structural features of the '<em>Or Group Spec</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_GROUP_SPEC_FEATURE_COUNT = GROUP_SPEC_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ConstraintsSpecificationImpl <em>Constraints Specification</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ConstraintsSpecificationImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getConstraintsSpecification()
   * @generated
   */
  int CONSTRAINTS_SPECIFICATION = 72;

  /**
   * The feature id for the '<em><b>Csts</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRAINTS_SPECIFICATION__CSTS = 0;

  /**
   * The number of structural features of the '<em>Constraints Specification</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRAINTS_SPECIFICATION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.SliceImpl <em>Slice</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.SliceImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getSlice()
   * @generated
   */
  int SLICE = 73;

  /**
   * The feature id for the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SLICE__FM = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Mode</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SLICE__MODE = COMMAND_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Fts</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SLICE__FTS = COMMAND_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Slice</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SLICE_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.AggregateImpl <em>Aggregate</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.AggregateImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getAggregate()
   * @generated
   */
  int AGGREGATE = 74;

  /**
   * The feature id for the '<em><b>Renamings</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATE__RENAMINGS = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Fms</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATE__FMS = COMMAND_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Sfms</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATE__SFMS = COMMAND_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Mapping</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATE__MAPPING = COMMAND_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Aggregate</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATE_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.FeatureModelOperationImpl <em>Feature Model Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.FeatureModelOperationImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getFeatureModelOperation()
   * @generated
   */
  int FEATURE_MODEL_OPERATION = 75;

  /**
   * The number of structural features of the '<em>Feature Model Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_MODEL_OPERATION_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.EditOperationImpl <em>Edit Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.EditOperationImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getEditOperation()
   * @generated
   */
  int EDIT_OPERATION = 76;

  /**
   * The number of structural features of the '<em>Edit Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EDIT_OPERATION_FEATURE_COUNT = FEATURE_MODEL_OPERATION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.InsertImpl <em>Insert</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.InsertImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getInsert()
   * @generated
   */
  int INSERT = 77;

  /**
   * The feature id for the '<em><b>Aspectfm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INSERT__ASPECTFM = BCOMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Baseft</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INSERT__BASEFT = BCOMMAND_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Op</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INSERT__OP = BCOMMAND_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Insert</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INSERT_FEATURE_COUNT = BCOMMAND_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.RemoveFeatureImpl <em>Remove Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.RemoveFeatureImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getRemoveFeature()
   * @generated
   */
  int REMOVE_FEATURE = 78;

  /**
   * The feature id for the '<em><b>Feature</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REMOVE_FEATURE__FEATURE = BCOMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Remove Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REMOVE_FEATURE_FEATURE_COUNT = BCOMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.RenameFeatureImpl <em>Rename Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.RenameFeatureImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getRenameFeature()
   * @generated
   */
  int RENAME_FEATURE = 79;

  /**
   * The feature id for the '<em><b>Feature</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RENAME_FEATURE__FEATURE = BCOMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Feature New</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RENAME_FEATURE__FEATURE_NEW = BCOMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Rename Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RENAME_FEATURE_FEATURE_COUNT = BCOMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ExtractImpl <em>Extract</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ExtractImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getExtract()
   * @generated
   */
  int EXTRACT = 80;

  /**
   * The feature id for the '<em><b>Rootfeature</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTRACT__ROOTFEATURE = FM_COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Extract</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTRACT_FEATURE_COUNT = FM_COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.AssertionImpl <em>Assertion</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.AssertionImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getAssertion()
   * @generated
   */
  int ASSERTION = 81;

  /**
   * The feature id for the '<em><b>Assertion</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSERTION__ASSERTION = COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Assertion</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSERTION_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.VariableNullImpl <em>Variable Null</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.VariableNullImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getVariableNull()
   * @generated
   */
  int VARIABLE_NULL = 82;

  /**
   * The feature id for the '<em><b>Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_NULL__VAR = COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Variable Null</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_NULL_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ExportImpl <em>Export</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ExportImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getExport()
   * @generated
   */
  int EXPORT = 83;

  /**
   * The feature id for the '<em><b>Arg</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPORT__ARG = 0;

  /**
   * The number of structural features of the '<em>Export</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPORT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.HiddenImpl <em>Hidden</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.HiddenImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getHidden()
   * @generated
   */
  int HIDDEN = 84;

  /**
   * The feature id for the '<em><b>Arg</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HIDDEN__ARG = 0;

  /**
   * The number of structural features of the '<em>Hidden</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HIDDEN_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.LVidentifierImpl <em>LVidentifier</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.LVidentifierImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getLVidentifier()
   * @generated
   */
  int LVIDENTIFIER = 85;

  /**
   * The feature id for the '<em><b>Args</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LVIDENTIFIER__ARGS = 0;

  /**
   * The number of structural features of the '<em>LVidentifier</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LVIDENTIFIER_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.DependencyImpl <em>Dependency</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.DependencyImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getDependency()
   * @generated
   */
  int DEPENDENCY = 86;

  /**
   * The feature id for the '<em><b>Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEPENDENCY__VAR = 0;

  /**
   * The number of structural features of the '<em>Dependency</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEPENDENCY_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ConfigurationCmdImpl <em>Configuration Cmd</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ConfigurationCmdImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getConfigurationCmd()
   * @generated
   */
  int CONFIGURATION_CMD = 87;

  /**
   * The number of structural features of the '<em>Configuration Cmd</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIGURATION_CMD_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.CreateConfigurationImpl <em>Create Configuration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.CreateConfigurationImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getCreateConfiguration()
   * @generated
   */
  int CREATE_CONFIGURATION = 88;

  /**
   * The feature id for the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CREATE_CONFIGURATION__FM = CONFIGURATION_COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Create Configuration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CREATE_CONFIGURATION_FEATURE_COUNT = CONFIGURATION_COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.CompleteConfigurationImpl <em>Complete Configuration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.CompleteConfigurationImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getCompleteConfiguration()
   * @generated
   */
  int COMPLETE_CONFIGURATION = 89;

  /**
   * The feature id for the '<em><b>Config</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPLETE_CONFIGURATION__CONFIG = BCOMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Complete Configuration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPLETE_CONFIGURATION_FEATURE_COUNT = BCOMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.SelectionFeatureImpl <em>Selection Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.SelectionFeatureImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getSelectionFeature()
   * @generated
   */
  int SELECTION_FEATURE = 90;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTION_FEATURE__OP = CONFIGURATION_CMD_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Fts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTION_FEATURE__FTS = CONFIGURATION_CMD_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Config</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTION_FEATURE__CONFIG = CONFIGURATION_CMD_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Selection Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTION_FEATURE_FEATURE_COUNT = CONFIGURATION_CMD_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.FeatureExpressionImpl <em>Feature Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.FeatureExpressionImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getFeatureExpression()
   * @generated
   */
  int FEATURE_EXPRESSION = 91;

  /**
   * The feature id for the '<em><b>Ft</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_EXPRESSION__FT = 0;

  /**
   * The number of structural features of the '<em>Feature Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_EXPRESSION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.AutoConfigurationImpl <em>Auto Configuration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.AutoConfigurationImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getAutoConfiguration()
   * @generated
   */
  int AUTO_CONFIGURATION = 92;

  /**
   * The feature id for the '<em><b>Config</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AUTO_CONFIGURATION__CONFIG = CONFIGURATION_CMD_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Mode</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AUTO_CONFIGURATION__MODE = CONFIGURATION_CMD_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Auto Configuration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AUTO_CONFIGURATION_FEATURE_COUNT = CONFIGURATION_CMD_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.SelectedConfigurationImpl <em>Selected Configuration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.SelectedConfigurationImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getSelectedConfiguration()
   * @generated
   */
  int SELECTED_CONFIGURATION = 93;

  /**
   * The feature id for the '<em><b>Config</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTED_CONFIGURATION__CONFIG = SET_COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Selected Configuration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTED_CONFIGURATION_FEATURE_COUNT = SET_COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.DeselectedConfigurationImpl <em>Deselected Configuration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.DeselectedConfigurationImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getDeselectedConfiguration()
   * @generated
   */
  int DESELECTED_CONFIGURATION = 94;

  /**
   * The feature id for the '<em><b>Config</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESELECTED_CONFIGURATION__CONFIG = SET_COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Deselected Configuration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESELECTED_CONFIGURATION_FEATURE_COUNT = SET_COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.UnselectedConfigurationImpl <em>Unselected Configuration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.UnselectedConfigurationImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getUnselectedConfiguration()
   * @generated
   */
  int UNSELECTED_CONFIGURATION = 95;

  /**
   * The feature id for the '<em><b>Config</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSELECTED_CONFIGURATION__CONFIG = CONFIGURATION_CMD_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Unselected Configuration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSELECTED_CONFIGURATION_FEATURE_COUNT = CONFIGURATION_CMD_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.AsFMImpl <em>As FM</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.AsFMImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getAsFM()
   * @generated
   */
  int AS_FM = 96;

  /**
   * The feature id for the '<em><b>Conf</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AS_FM__CONF = COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>As FM</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AS_FM_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.MapImpl <em>Map</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.MapImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getMap()
   * @generated
   */
  int MAP = 97;

  /**
   * The feature id for the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAP__FM = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Cst</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAP__CST = COMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Map</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAP_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.UnMapImpl <em>Un Map</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.UnMapImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getUnMap()
   * @generated
   */
  int UN_MAP = 98;

  /**
   * The feature id for the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UN_MAP__FM = COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Un Map</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UN_MAP_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.CleanUpImpl <em>Clean Up</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.CleanUpImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getCleanUp()
   * @generated
   */
  int CLEAN_UP = 99;

  /**
   * The feature id for the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLEAN_UP__FM = COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Clean Up</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLEAN_UP_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.CoresImpl <em>Cores</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.CoresImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getCores()
   * @generated
   */
  int CORES = 100;

  /**
   * The feature id for the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CORES__FM = COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Cores</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CORES_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.DeadsImpl <em>Deads</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.DeadsImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getDeads()
   * @generated
   */
  int DEADS = 101;

  /**
   * The feature id for the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEADS__FM = COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Deads</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEADS_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.FullMandatorysImpl <em>Full Mandatorys</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.FullMandatorysImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getFullMandatorys()
   * @generated
   */
  int FULL_MANDATORYS = 102;

  /**
   * The feature id for the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FULL_MANDATORYS__FM = COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Full Mandatorys</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FULL_MANDATORYS_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.CliquesImpl <em>Cliques</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.CliquesImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getCliques()
   * @generated
   */
  int CLIQUES = 103;

  /**
   * The feature id for the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLIQUES__FM = COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Cliques</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLIQUES_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ScriptDefinitionImpl <em>Script Definition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ScriptDefinitionImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getScriptDefinition()
   * @generated
   */
  int SCRIPT_DEFINITION = 104;

  /**
   * The feature id for the '<em><b>Params</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCRIPT_DEFINITION__PARAMS = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Cmds</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCRIPT_DEFINITION__CMDS = COMMAND_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Exports</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCRIPT_DEFINITION__EXPORTS = COMMAND_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Script Definition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCRIPT_DEFINITION_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ShellImpl <em>Shell</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ShellImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getShell()
   * @generated
   */
  int SHELL = 105;

  /**
   * The feature id for the '<em><b>Cmd</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SHELL__CMD = COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Shell</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SHELL_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ExitImpl <em>Exit</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ExitImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getExit()
   * @generated
   */
  int EXIT = 106;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXIT__VAL = 0;

  /**
   * The number of structural features of the '<em>Exit</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXIT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ExistImpl <em>Exist</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ExistImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getExist()
   * @generated
   */
  int EXIST = 107;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXIST__VAL = 0;

  /**
   * The feature id for the '<em><b>Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXIST__VAR = 1;

  /**
   * The number of structural features of the '<em>Exist</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXIST_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.IsConflictingImpl <em>Is Conflicting</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.IsConflictingImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getIsConflicting()
   * @generated
   */
  int IS_CONFLICTING = 108;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IS_CONFLICTING__VAL = BCOMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IS_CONFLICTING__VAR = BCOMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Is Conflicting</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IS_CONFLICTING_FEATURE_COUNT = BCOMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ListingImpl <em>Listing</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ListingImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getListing()
   * @generated
   */
  int LISTING = 109;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LISTING__VAL = 0;

  /**
   * The feature id for the '<em><b>Opt</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LISTING__OPT = 1;

  /**
   * The number of structural features of the '<em>Listing</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LISTING_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.StateImpl <em>State</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.StateImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getState()
   * @generated
   */
  int STATE = 110;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE__VAL = 0;

  /**
   * The number of structural features of the '<em>State</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.CopyVariableImpl <em>Copy Variable</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.CopyVariableImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getCopyVariable()
   * @generated
   */
  int COPY_VARIABLE = 111;

  /**
   * The feature id for the '<em><b>Vid</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COPY_VARIABLE__VID = COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Copy Variable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COPY_VARIABLE_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.RemoveVariableImpl <em>Remove Variable</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.RemoveVariableImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getRemoveVariable()
   * @generated
   */
  int REMOVE_VARIABLE = 112;

  /**
   * The feature id for the '<em><b>Vid</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REMOVE_VARIABLE__VID = COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Remove Variable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REMOVE_VARIABLE_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ConvertImpl <em>Convert</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ConvertImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getConvert()
   * @generated
   */
  int CONVERT = 113;

  /**
   * The feature id for the '<em><b>V</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONVERT__V = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Format</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONVERT__FORMAT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Convert</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONVERT_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.FMLSaveImpl <em>Save</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.FMLSaveImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getFMLSave()
   * @generated
   */
  int FML_SAVE = 114;

  /**
   * The feature id for the '<em><b>V</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FML_SAVE__V = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Format</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FML_SAVE__FORMAT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Save</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FML_SAVE_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.HierarchyImpl <em>Hierarchy</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.HierarchyImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getHierarchy()
   * @generated
   */
  int HIERARCHY = 115;

  /**
   * The feature id for the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HIERARCHY__FM = COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Hierarchy</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HIERARCHY_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.PrinterUtilityImpl <em>Printer Utility</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.PrinterUtilityImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getPrinterUtility()
   * @generated
   */
  int PRINTER_UTILITY = 116;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRINTER_UTILITY__OP = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Arg</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRINTER_UTILITY__ARG = COMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Printer Utility</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRINTER_UTILITY_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.LArgsImpl <em>LArgs</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.LArgsImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getLArgs()
   * @generated
   */
  int LARGS = 117;

  /**
   * The feature id for the '<em><b>Args</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LARGS__ARGS = 0;

  /**
   * The number of structural features of the '<em>LArgs</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LARGS_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.GDisplayImpl <em>GDisplay</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.GDisplayImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getGDisplay()
   * @generated
   */
  int GDISPLAY = 118;

  /**
   * The feature id for the '<em><b>Cmd Display</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GDISPLAY__CMD_DISPLAY = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GDISPLAY__VAR = COMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>GDisplay</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GDISPLAY_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.GListingImpl <em>GListing</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.GListingImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getGListing()
   * @generated
   */
  int GLISTING = 119;

  /**
   * The feature id for the '<em><b>Cmd</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GLISTING__CMD = COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>GListing</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GLISTING_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ModifyVOperatorImpl <em>Modify VOperator</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ModifyVOperatorImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getModifyVOperator()
   * @generated
   */
  int MODIFY_VOPERATOR = 120;

  /**
   * The number of structural features of the '<em>Modify VOperator</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODIFY_VOPERATOR_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.MandatoryEditImpl <em>Mandatory Edit</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.MandatoryEditImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getMandatoryEdit()
   * @generated
   */
  int MANDATORY_EDIT = 121;

  /**
   * The feature id for the '<em><b>Ft</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MANDATORY_EDIT__FT = MODIFY_VOPERATOR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Mandatory Edit</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MANDATORY_EDIT_FEATURE_COUNT = MODIFY_VOPERATOR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.OptionalEditImpl <em>Optional Edit</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.OptionalEditImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getOptionalEdit()
   * @generated
   */
  int OPTIONAL_EDIT = 122;

  /**
   * The feature id for the '<em><b>Ft</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPTIONAL_EDIT__FT = MODIFY_VOPERATOR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Optional Edit</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPTIONAL_EDIT_FEATURE_COUNT = MODIFY_VOPERATOR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.AlternativeEditImpl <em>Alternative Edit</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.AlternativeEditImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getAlternativeEdit()
   * @generated
   */
  int ALTERNATIVE_EDIT = 123;

  /**
   * The feature id for the '<em><b>Fts</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALTERNATIVE_EDIT__FTS = MODIFY_VOPERATOR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Alternative Edit</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALTERNATIVE_EDIT_FEATURE_COUNT = MODIFY_VOPERATOR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.OrEditImpl <em>Or Edit</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.OrEditImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getOrEdit()
   * @generated
   */
  int OR_EDIT = 124;

  /**
   * The feature id for the '<em><b>Fts</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_EDIT__FTS = MODIFY_VOPERATOR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Or Edit</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_EDIT_FEATURE_COUNT = MODIFY_VOPERATOR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.AddConstraintImpl <em>Add Constraint</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.AddConstraintImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getAddConstraint()
   * @generated
   */
  int ADD_CONSTRAINT = 125;

  /**
   * The feature id for the '<em><b>Cst</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADD_CONSTRAINT__CST = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADD_CONSTRAINT__FM = COMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Add Constraint</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADD_CONSTRAINT_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.RemoveConstraintImpl <em>Remove Constraint</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.RemoveConstraintImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getRemoveConstraint()
   * @generated
   */
  int REMOVE_CONSTRAINT = 126;

  /**
   * The feature id for the '<em><b>Cst</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REMOVE_CONSTRAINT__CST = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REMOVE_CONSTRAINT__FM = COMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Remove Constraint</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REMOVE_CONSTRAINT_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.CNFImpl <em>CNF</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.CNFImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getCNF()
   * @generated
   */
  int CNF = 127;

  /**
   * The number of structural features of the '<em>CNF</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CNF_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.CNFExpressionImpl <em>CNF Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.CNFExpressionImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getCNFExpression()
   * @generated
   */
  int CNF_EXPRESSION = 128;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CNF_EXPRESSION__NAME = CNF_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>CNF Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CNF_EXPRESSION_FEATURE_COUNT = CNF_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.Neg_exprImpl <em>Neg expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.Neg_exprImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getNeg_expr()
   * @generated
   */
  int NEG_EXPR = 129;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NEG_EXPR__NAME = CNF_EXPRESSION__NAME;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NEG_EXPR__EXPR = CNF_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Neg expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NEG_EXPR_FEATURE_COUNT = CNF_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.FeatureModelImpl <em>Feature Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.FeatureModelImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getFeatureModel()
   * @generated
   */
  int FEATURE_MODEL = 130;

  /**
   * The feature id for the '<em><b>Root</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_MODEL__ROOT = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_MODEL__FEATURES = COMMAND_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_MODEL__EXPR = COMMAND_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>File</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_MODEL__FILE = COMMAND_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Feature Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_MODEL_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ProductionImpl <em>Production</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ProductionImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getProduction()
   * @generated
   */
  int PRODUCTION = 131;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRODUCTION__NAME = 0;

  /**
   * The feature id for the '<em><b>Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRODUCTION__FEATURES = 1;

  /**
   * The number of structural features of the '<em>Production</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRODUCTION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ChildImpl <em>Child</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ChildImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getChild()
   * @generated
   */
  int CHILD = 132;

  /**
   * The number of structural features of the '<em>Child</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CHILD_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.MandatoryImpl <em>Mandatory</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.MandatoryImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getMandatory()
   * @generated
   */
  int MANDATORY = 133;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MANDATORY__NAME = CHILD_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Mandatory</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MANDATORY_FEATURE_COUNT = CHILD_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.OptionalImpl <em>Optional</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.OptionalImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getOptional()
   * @generated
   */
  int OPTIONAL = 134;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPTIONAL__NAME = CHILD_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Optional</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPTIONAL_FEATURE_COUNT = CHILD_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.XorgroupImpl <em>Xorgroup</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.XorgroupImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getXorgroup()
   * @generated
   */
  int XORGROUP = 135;

  /**
   * The feature id for the '<em><b>Features</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XORGROUP__FEATURES = CHILD_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Xorgroup</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XORGROUP_FEATURE_COUNT = CHILD_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.OrgroupImpl <em>Orgroup</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.OrgroupImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getOrgroup()
   * @generated
   */
  int ORGROUP = 136;

  /**
   * The feature id for the '<em><b>Features</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ORGROUP__FEATURES = CHILD_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Orgroup</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ORGROUP_FEATURE_COUNT = CHILD_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.MutexgroupImpl <em>Mutexgroup</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.MutexgroupImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getMutexgroup()
   * @generated
   */
  int MUTEXGROUP = 137;

  /**
   * The feature id for the '<em><b>Features</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MUTEXGROUP__FEATURES = CHILD_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Mutexgroup</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MUTEXGROUP_FEATURE_COUNT = CHILD_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.IntegerOperationImpl <em>Integer Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.IntegerOperationImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getIntegerOperation()
   * @generated
   */
  int INTEGER_OPERATION = 138;

  /**
   * The feature id for the '<em><b>Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTEGER_OPERATION__VAR = COMPLEX_COMMAND__VAR;

  /**
   * The feature id for the '<em><b>Meta ID</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTEGER_OPERATION__META_ID = COMPLEX_COMMAND__META_ID;

  /**
   * The feature id for the '<em><b>Cmd</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTEGER_OPERATION__CMD = COMPLEX_COMMAND__CMD;

  /**
   * The feature id for the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTEGER_OPERATION__LEFT = COMPLEX_COMMAND__LEFT;

  /**
   * The feature id for the '<em><b>Not</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTEGER_OPERATION__NOT = COMPLEX_COMMAND__NOT;

  /**
   * The feature id for the '<em><b>Batom</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTEGER_OPERATION__BATOM = COMPLEX_COMMAND__BATOM;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTEGER_OPERATION__OP = COMPLEX_COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTEGER_OPERATION__RIGHT = COMPLEX_COMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Integer Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTEGER_OPERATION_FEATURE_COUNT = COMPLEX_COMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.BoolOperationImpl <em>Bool Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.BoolOperationImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getBoolOperation()
   * @generated
   */
  int BOOL_OPERATION = 139;

  /**
   * The feature id for the '<em><b>Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOL_OPERATION__VAR = COMPLEX_COMMAND__VAR;

  /**
   * The feature id for the '<em><b>Meta ID</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOL_OPERATION__META_ID = COMPLEX_COMMAND__META_ID;

  /**
   * The feature id for the '<em><b>Cmd</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOL_OPERATION__CMD = COMPLEX_COMMAND__CMD;

  /**
   * The feature id for the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOL_OPERATION__LEFT = COMPLEX_COMMAND__LEFT;

  /**
   * The feature id for the '<em><b>Not</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOL_OPERATION__NOT = COMPLEX_COMMAND__NOT;

  /**
   * The feature id for the '<em><b>Batom</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOL_OPERATION__BATOM = COMPLEX_COMMAND__BATOM;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOL_OPERATION__OP = COMPLEX_COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOL_OPERATION__RIGHT = COMPLEX_COMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Bool Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOL_OPERATION_FEATURE_COUNT = COMPLEX_COMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.ComparisonOperationImpl <em>Comparison Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.ComparisonOperationImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getComparisonOperation()
   * @generated
   */
  int COMPARISON_OPERATION = 140;

  /**
   * The feature id for the '<em><b>Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPARISON_OPERATION__VAR = COMPLEX_COMMAND__VAR;

  /**
   * The feature id for the '<em><b>Meta ID</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPARISON_OPERATION__META_ID = COMPLEX_COMMAND__META_ID;

  /**
   * The feature id for the '<em><b>Cmd</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPARISON_OPERATION__CMD = COMPLEX_COMMAND__CMD;

  /**
   * The feature id for the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPARISON_OPERATION__LEFT = COMPLEX_COMMAND__LEFT;

  /**
   * The feature id for the '<em><b>Not</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPARISON_OPERATION__NOT = COMPLEX_COMMAND__NOT;

  /**
   * The feature id for the '<em><b>Batom</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPARISON_OPERATION__BATOM = COMPLEX_COMMAND__BATOM;

  /**
   * The feature id for the '<em><b>Cmp Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPARISON_OPERATION__CMP_OP = COMPLEX_COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPARISON_OPERATION__RIGHT = COMPLEX_COMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Comparison Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPARISON_OPERATION_FEATURE_COUNT = COMPLEX_COMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.SetOperationImpl <em>Set Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.SetOperationImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getSetOperation()
   * @generated
   */
  int SET_OPERATION = 141;

  /**
   * The feature id for the '<em><b>Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_OPERATION__VAR = COMPLEX_COMMAND__VAR;

  /**
   * The feature id for the '<em><b>Meta ID</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_OPERATION__META_ID = COMPLEX_COMMAND__META_ID;

  /**
   * The feature id for the '<em><b>Cmd</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_OPERATION__CMD = COMPLEX_COMMAND__CMD;

  /**
   * The feature id for the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_OPERATION__LEFT = COMPLEX_COMMAND__LEFT;

  /**
   * The feature id for the '<em><b>Not</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_OPERATION__NOT = COMPLEX_COMMAND__NOT;

  /**
   * The feature id for the '<em><b>Batom</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_OPERATION__BATOM = COMPLEX_COMMAND__BATOM;

  /**
   * The feature id for the '<em><b>Sop</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_OPERATION__SOP = COMPLEX_COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_OPERATION__RIGHT = COMPLEX_COMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Set Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_OPERATION_FEATURE_COUNT = COMPLEX_COMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.IntLiteralImpl <em>Int Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.IntLiteralImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getIntLiteral()
   * @generated
   */
  int INT_LITERAL = 142;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INT_LITERAL__VALUE = INTEGER_EXPR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Int Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INT_LITERAL_FEATURE_COUNT = INTEGER_EXPR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.Or_exprImpl <em>Or expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.Or_exprImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getOr_expr()
   * @generated
   */
  int OR_EXPR = 143;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_EXPR__NAME = CNF_EXPRESSION__NAME;

  /**
   * The feature id for the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_EXPR__LEFT = CNF_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_EXPR__RIGHT = CNF_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Or expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_EXPR_FEATURE_COUNT = CNF_EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.And_exprImpl <em>And expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.And_exprImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getAnd_expr()
   * @generated
   */
  int AND_EXPR = 144;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AND_EXPR__NAME = CNF_EXPRESSION__NAME;

  /**
   * The feature id for the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AND_EXPR__LEFT = CNF_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AND_EXPR__RIGHT = CNF_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>And expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AND_EXPR_FEATURE_COUNT = CNF_EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.Impl_exprImpl <em>Impl expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.Impl_exprImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getImpl_expr()
   * @generated
   */
  int IMPL_EXPR = 145;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPL_EXPR__NAME = CNF_EXPRESSION__NAME;

  /**
   * The feature id for the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPL_EXPR__LEFT = CNF_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPL_EXPR__RIGHT = CNF_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Impl expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPL_EXPR_FEATURE_COUNT = CNF_EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.impl.Biimpl_exprImpl <em>Biimpl expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.impl.Biimpl_exprImpl
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getBiimpl_expr()
   * @generated
   */
  int BIIMPL_EXPR = 146;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BIIMPL_EXPR__NAME = CNF_EXPRESSION__NAME;

  /**
   * The feature id for the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BIIMPL_EXPR__LEFT = CNF_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BIIMPL_EXPR__RIGHT = CNF_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Biimpl expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BIIMPL_EXPR_FEATURE_COUNT = CNF_EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.EditConstant <em>Edit Constant</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.EditConstant
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getEditConstant()
   * @generated
   */
  int EDIT_CONSTANT = 147;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.FeatureEdgeKind <em>Feature Edge Kind</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.FeatureEdgeKind
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getFeatureEdgeKind()
   * @generated
   */
  int FEATURE_EDGE_KIND = 148;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.KindOfGet <em>Kind Of Get</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.KindOfGet
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getKindOfGet()
   * @generated
   */
  int KIND_OF_GET = 149;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.KindOfCompute <em>Kind Of Compute</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.KindOfCompute
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getKindOfCompute()
   * @generated
   */
  int KIND_OF_COMPUTE = 150;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.KindOfGetGroups <em>Kind Of Get Groups</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.KindOfGetGroups
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getKindOfGetGroups()
   * @generated
   */
  int KIND_OF_GET_GROUPS = 151;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.KindOfComputeGroups <em>Kind Of Compute Groups</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.KindOfComputeGroups
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getKindOfComputeGroups()
   * @generated
   */
  int KIND_OF_COMPUTE_GROUPS = 152;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.BDDBackend <em>BDD Backend</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.BDDBackend
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getBDDBackend()
   * @generated
   */
  int BDD_BACKEND = 153;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.MergeMode <em>Merge Mode</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.MergeMode
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getMergeMode()
   * @generated
   */
  int MERGE_MODE = 154;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.HierarchyStrategy <em>Hierarchy Strategy</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.HierarchyStrategy
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getHierarchyStrategy()
   * @generated
   */
  int HIERARCHY_STRATEGY = 155;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.SliceMode <em>Slice Mode</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.SliceMode
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getSliceMode()
   * @generated
   */
  int SLICE_MODE = 156;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.ComparisonOperator <em>Comparison Operator</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.ComparisonOperator
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getComparisonOperator()
   * @generated
   */
  int COMPARISON_OPERATOR = 157;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.SetOperator <em>Set Operator</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.SetOperator
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getSetOperator()
   * @generated
   */
  int SET_OPERATOR = 158;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.OpSelection <em>Op Selection</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.OpSelection
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getOpSelection()
   * @generated
   */
  int OP_SELECTION = 159;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.AutoConfMode <em>Auto Conf Mode</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.AutoConfMode
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getAutoConfMode()
   * @generated
   */
  int AUTO_CONF_MODE = 160;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.OPT_LISTING <em>OPT LISTING</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.OPT_LISTING
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getOPT_LISTING()
   * @generated
   */
  int OPT_LISTING = 161;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.FMFormat <em>FM Format</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.FMFormat
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getFMFormat()
   * @generated
   */
  int FM_FORMAT = 162;

  /**
   * The meta object id for the '{@link org.xtext.example.mydsl.fML.BOOL_Operator <em>BOOL Operator</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.mydsl.fML.BOOL_Operator
   * @see org.xtext.example.mydsl.fML.impl.FMLPackageImpl#getBOOL_Operator()
   * @generated
   */
  int BOOL_OPERATOR = 163;


  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.FamiliarScript <em>Familiar Script</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Familiar Script</em>'.
   * @see org.xtext.example.mydsl.fML.FamiliarScript
   * @generated
   */
  EClass getFamiliarScript();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.mydsl.fML.FamiliarScript#getParams <em>Params</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Params</em>'.
   * @see org.xtext.example.mydsl.fML.FamiliarScript#getParams()
   * @see #getFamiliarScript()
   * @generated
   */
  EReference getFamiliarScript_Params();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.mydsl.fML.FamiliarScript#getCmds <em>Cmds</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Cmds</em>'.
   * @see org.xtext.example.mydsl.fML.FamiliarScript#getCmds()
   * @see #getFamiliarScript()
   * @generated
   */
  EReference getFamiliarScript_Cmds();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.mydsl.fML.FamiliarScript#getExports <em>Exports</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Exports</em>'.
   * @see org.xtext.example.mydsl.fML.FamiliarScript#getExports()
   * @see #getFamiliarScript()
   * @generated
   */
  EReference getFamiliarScript_Exports();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.ScriptCommand <em>Script Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Script Command</em>'.
   * @see org.xtext.example.mydsl.fML.ScriptCommand
   * @generated
   */
  EClass getScriptCommand();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.ScriptCommand#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Var</em>'.
   * @see org.xtext.example.mydsl.fML.ScriptCommand#getVar()
   * @see #getScriptCommand()
   * @generated
   */
  EAttribute getScriptCommand_Var();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.ScriptCommand#getMetaID <em>Meta ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Meta ID</em>'.
   * @see org.xtext.example.mydsl.fML.ScriptCommand#getMetaID()
   * @see #getScriptCommand()
   * @generated
   */
  EReference getScriptCommand_MetaID();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.ScriptCommand#getCmd <em>Cmd</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cmd</em>'.
   * @see org.xtext.example.mydsl.fML.ScriptCommand#getCmd()
   * @see #getScriptCommand()
   * @generated
   */
  EReference getScriptCommand_Cmd();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.ComplexCommand <em>Complex Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Complex Command</em>'.
   * @see org.xtext.example.mydsl.fML.ComplexCommand
   * @generated
   */
  EClass getComplexCommand();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.ComplexCommand#getLeft <em>Left</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left</em>'.
   * @see org.xtext.example.mydsl.fML.ComplexCommand#getLeft()
   * @see #getComplexCommand()
   * @generated
   */
  EReference getComplexCommand_Left();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.ComplexCommand#isNot <em>Not</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Not</em>'.
   * @see org.xtext.example.mydsl.fML.ComplexCommand#isNot()
   * @see #getComplexCommand()
   * @generated
   */
  EAttribute getComplexCommand_Not();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.ComplexCommand#getBatom <em>Batom</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Batom</em>'.
   * @see org.xtext.example.mydsl.fML.ComplexCommand#getBatom()
   * @see #getComplexCommand()
   * @generated
   */
  EReference getComplexCommand_Batom();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Command <em>Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Command</em>'.
   * @see org.xtext.example.mydsl.fML.Command
   * @generated
   */
  EClass getCommand();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.IntegerExpr <em>Integer Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Integer Expr</em>'.
   * @see org.xtext.example.mydsl.fML.IntegerExpr
   * @generated
   */
  EClass getIntegerExpr();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.BooleanExpr <em>Boolean Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Boolean Expr</em>'.
   * @see org.xtext.example.mydsl.fML.BooleanExpr
   * @generated
   */
  EClass getBooleanExpr();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.BooleanExpr#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.xtext.example.mydsl.fML.BooleanExpr#getVal()
   * @see #getBooleanExpr()
   * @generated
   */
  EAttribute getBooleanExpr_Val();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.IdentifierExpr <em>Identifier Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Identifier Expr</em>'.
   * @see org.xtext.example.mydsl.fML.IdentifierExpr
   * @generated
   */
  EClass getIdentifierExpr();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.IdentifierExpr#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.xtext.example.mydsl.fML.IdentifierExpr#getVal()
   * @see #getIdentifierExpr()
   * @generated
   */
  EAttribute getIdentifierExpr_Val();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.IdentifierExpr#getMetaID <em>Meta ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Meta ID</em>'.
   * @see org.xtext.example.mydsl.fML.IdentifierExpr#getMetaID()
   * @see #getIdentifierExpr()
   * @generated
   */
  EReference getIdentifierExpr_MetaID();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.StringExpr <em>String Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>String Expr</em>'.
   * @see org.xtext.example.mydsl.fML.StringExpr
   * @generated
   */
  EClass getStringExpr();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.StringExpr#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.xtext.example.mydsl.fML.StringExpr#getVal()
   * @see #getStringExpr()
   * @generated
   */
  EAttribute getStringExpr_Val();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.SetExpr <em>Set Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Set Expr</em>'.
   * @see org.xtext.example.mydsl.fML.SetExpr
   * @generated
   */
  EClass getSetExpr();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.mydsl.fML.SetExpr#getE <em>E</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>E</em>'.
   * @see org.xtext.example.mydsl.fML.SetExpr#getE()
   * @see #getSetExpr()
   * @generated
   */
  EReference getSetExpr_E();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.AtomicConstraintExpr <em>Atomic Constraint Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Atomic Constraint Expr</em>'.
   * @see org.xtext.example.mydsl.fML.AtomicConstraintExpr
   * @generated
   */
  EClass getAtomicConstraintExpr();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.AtomicConstraintExpr#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.xtext.example.mydsl.fML.AtomicConstraintExpr#getExpr()
   * @see #getAtomicConstraintExpr()
   * @generated
   */
  EReference getAtomicConstraintExpr_Expr();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.ConstraintExpr <em>Constraint Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Constraint Expr</em>'.
   * @see org.xtext.example.mydsl.fML.ConstraintExpr
   * @generated
   */
  EClass getConstraintExpr();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.mydsl.fML.ConstraintExpr#getConstraints <em>Constraints</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Constraints</em>'.
   * @see org.xtext.example.mydsl.fML.ConstraintExpr#getConstraints()
   * @see #getConstraintExpr()
   * @generated
   */
  EReference getConstraintExpr_Constraints();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.ConstraintExpr#getFm <em>Fm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fm</em>'.
   * @see org.xtext.example.mydsl.fML.ConstraintExpr#getFm()
   * @see #getConstraintExpr()
   * @generated
   */
  EReference getConstraintExpr_Fm();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.FeatureVariabilityOperator <em>Feature Variability Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature Variability Operator</em>'.
   * @see org.xtext.example.mydsl.fML.FeatureVariabilityOperator
   * @generated
   */
  EClass getFeatureVariabilityOperator();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.FeatureVariabilityOperator#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.xtext.example.mydsl.fML.FeatureVariabilityOperator#getVal()
   * @see #getFeatureVariabilityOperator()
   * @generated
   */
  EAttribute getFeatureVariabilityOperator_Val();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.IfCondition <em>If Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>If Condition</em>'.
   * @see org.xtext.example.mydsl.fML.IfCondition
   * @generated
   */
  EClass getIfCondition();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.IfCondition#getBexpr <em>Bexpr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Bexpr</em>'.
   * @see org.xtext.example.mydsl.fML.IfCondition#getBexpr()
   * @see #getIfCondition()
   * @generated
   */
  EReference getIfCondition_Bexpr();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.mydsl.fML.IfCondition#getThen <em>Then</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Then</em>'.
   * @see org.xtext.example.mydsl.fML.IfCondition#getThen()
   * @see #getIfCondition()
   * @generated
   */
  EReference getIfCondition_Then();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.mydsl.fML.IfCondition#getElse <em>Else</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Else</em>'.
   * @see org.xtext.example.mydsl.fML.IfCondition#getElse()
   * @see #getIfCondition()
   * @generated
   */
  EReference getIfCondition_Else();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.ForeachSet <em>Foreach Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Foreach Set</em>'.
   * @see org.xtext.example.mydsl.fML.ForeachSet
   * @generated
   */
  EClass getForeachSet();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.ForeachSet#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.xtext.example.mydsl.fML.ForeachSet#getVal()
   * @see #getForeachSet()
   * @generated
   */
  EAttribute getForeachSet_Val();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.ForeachSet#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Var</em>'.
   * @see org.xtext.example.mydsl.fML.ForeachSet#getVar()
   * @see #getForeachSet()
   * @generated
   */
  EAttribute getForeachSet_Var();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.mydsl.fML.ForeachSet#getExprs <em>Exprs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Exprs</em>'.
   * @see org.xtext.example.mydsl.fML.ForeachSet#getExprs()
   * @see #getForeachSet()
   * @generated
   */
  EReference getForeachSet_Exprs();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.lType <em>lType</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>lType</em>'.
   * @see org.xtext.example.mydsl.fML.lType
   * @generated
   */
  EClass getlType();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.lType#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.xtext.example.mydsl.fML.lType#getVal()
   * @see #getlType()
   * @generated
   */
  EAttribute getlType_Val();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.FMLAbstractCommand <em>Abstract Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Command</em>'.
   * @see org.xtext.example.mydsl.fML.FMLAbstractCommand
   * @generated
   */
  EClass getFMLAbstractCommand();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.FMCommand <em>FM Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>FM Command</em>'.
   * @see org.xtext.example.mydsl.fML.FMCommand
   * @generated
   */
  EClass getFMCommand();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.FTCommand <em>FT Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>FT Command</em>'.
   * @see org.xtext.example.mydsl.fML.FTCommand
   * @generated
   */
  EClass getFTCommand();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.BCommand <em>BCommand</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>BCommand</em>'.
   * @see org.xtext.example.mydsl.fML.BCommand
   * @generated
   */
  EClass getBCommand();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.StrCommand <em>Str Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Str Command</em>'.
   * @see org.xtext.example.mydsl.fML.StrCommand
   * @generated
   */
  EClass getStrCommand();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.ConfigurationCommand <em>Configuration Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Configuration Command</em>'.
   * @see org.xtext.example.mydsl.fML.ConfigurationCommand
   * @generated
   */
  EClass getConfigurationCommand();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.SetCommand <em>Set Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Set Command</em>'.
   * @see org.xtext.example.mydsl.fML.SetCommand
   * @generated
   */
  EClass getSetCommand();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Leaves <em>Leaves</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Leaves</em>'.
   * @see org.xtext.example.mydsl.fML.Leaves
   * @generated
   */
  EClass getLeaves();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Leaves#getFm <em>Fm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fm</em>'.
   * @see org.xtext.example.mydsl.fML.Leaves#getFm()
   * @see #getLeaves()
   * @generated
   */
  EReference getLeaves_Fm();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.ConstraintCommand <em>Constraint Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Constraint Command</em>'.
   * @see org.xtext.example.mydsl.fML.ConstraintCommand
   * @generated
   */
  EClass getConstraintCommand();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.GetConstraints <em>Get Constraints</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Get Constraints</em>'.
   * @see org.xtext.example.mydsl.fML.GetConstraints
   * @generated
   */
  EClass getGetConstraints();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.GetConstraints#getKindOfGet <em>Kind Of Get</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind Of Get</em>'.
   * @see org.xtext.example.mydsl.fML.GetConstraints#getKindOfGet()
   * @see #getGetConstraints()
   * @generated
   */
  EAttribute getGetConstraints_KindOfGet();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.GetConstraints#getFm <em>Fm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fm</em>'.
   * @see org.xtext.example.mydsl.fML.GetConstraints#getFm()
   * @see #getGetConstraints()
   * @generated
   */
  EReference getGetConstraints_Fm();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.ComputeConstraints <em>Compute Constraints</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Compute Constraints</em>'.
   * @see org.xtext.example.mydsl.fML.ComputeConstraints
   * @generated
   */
  EClass getComputeConstraints();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.ComputeConstraints#getKindOfCompute <em>Kind Of Compute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind Of Compute</em>'.
   * @see org.xtext.example.mydsl.fML.ComputeConstraints#getKindOfCompute()
   * @see #getComputeConstraints()
   * @generated
   */
  EAttribute getComputeConstraints_KindOfCompute();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.ComputeConstraints#getFm <em>Fm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fm</em>'.
   * @see org.xtext.example.mydsl.fML.ComputeConstraints#getFm()
   * @see #getComputeConstraints()
   * @generated
   */
  EReference getComputeConstraints_Fm();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.GetFGroups <em>Get FGroups</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Get FGroups</em>'.
   * @see org.xtext.example.mydsl.fML.GetFGroups
   * @generated
   */
  EClass getGetFGroups();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.GetFGroups#getKindOfGroups <em>Kind Of Groups</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind Of Groups</em>'.
   * @see org.xtext.example.mydsl.fML.GetFGroups#getKindOfGroups()
   * @see #getGetFGroups()
   * @generated
   */
  EAttribute getGetFGroups_KindOfGroups();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.GetFGroups#getFm <em>Fm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fm</em>'.
   * @see org.xtext.example.mydsl.fML.GetFGroups#getFm()
   * @see #getGetFGroups()
   * @generated
   */
  EReference getGetFGroups_Fm();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.ComputeFGroups <em>Compute FGroups</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Compute FGroups</em>'.
   * @see org.xtext.example.mydsl.fML.ComputeFGroups
   * @generated
   */
  EClass getComputeFGroups();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.ComputeFGroups#getKindOfGroups <em>Kind Of Groups</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind Of Groups</em>'.
   * @see org.xtext.example.mydsl.fML.ComputeFGroups#getKindOfGroups()
   * @see #getComputeFGroups()
   * @generated
   */
  EAttribute getComputeFGroups_KindOfGroups();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.ComputeFGroups#getFm <em>Fm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fm</em>'.
   * @see org.xtext.example.mydsl.fML.ComputeFGroups#getFm()
   * @see #getComputeFGroups()
   * @generated
   */
  EReference getComputeFGroups_Fm();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.PairwiseCommand <em>Pairwise Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pairwise Command</em>'.
   * @see org.xtext.example.mydsl.fML.PairwiseCommand
   * @generated
   */
  EClass getPairwiseCommand();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.PairwiseCommand#getFm <em>Fm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fm</em>'.
   * @see org.xtext.example.mydsl.fML.PairwiseCommand#getFm()
   * @see #getPairwiseCommand()
   * @generated
   */
  EReference getPairwiseCommand_Fm();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.PairwiseCommand#getMinimization <em>Minimization</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Minimization</em>'.
   * @see org.xtext.example.mydsl.fML.PairwiseCommand#getMinimization()
   * @see #getPairwiseCommand()
   * @generated
   */
  EReference getPairwiseCommand_Minimization();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.PairwiseCommand#getPartial <em>Partial</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Partial</em>'.
   * @see org.xtext.example.mydsl.fML.PairwiseCommand#getPartial()
   * @see #getPairwiseCommand()
   * @generated
   */
  EReference getPairwiseCommand_Partial();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.IntegerCommand <em>Integer Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Integer Command</em>'.
   * @see org.xtext.example.mydsl.fML.IntegerCommand
   * @generated
   */
  EClass getIntegerCommand();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.DoubleCommand <em>Double Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Double Command</em>'.
   * @see org.xtext.example.mydsl.fML.DoubleCommand
   * @generated
   */
  EClass getDoubleCommand();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.VariabilityOpCommand <em>Variability Op Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Variability Op Command</em>'.
   * @see org.xtext.example.mydsl.fML.VariabilityOpCommand
   * @generated
   */
  EClass getVariabilityOpCommand();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.AnalysisOperation <em>Analysis Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Analysis Operation</em>'.
   * @see org.xtext.example.mydsl.fML.AnalysisOperation
   * @generated
   */
  EClass getAnalysisOperation();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.AnalysisOperation#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Op</em>'.
   * @see org.xtext.example.mydsl.fML.AnalysisOperation#getOp()
   * @see #getAnalysisOperation()
   * @generated
   */
  EAttribute getAnalysisOperation_Op();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.AnalysisOperation#getFm <em>Fm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fm</em>'.
   * @see org.xtext.example.mydsl.fML.AnalysisOperation#getFm()
   * @see #getAnalysisOperation()
   * @generated
   */
  EReference getAnalysisOperation_Fm();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.SetOperations <em>Set Operations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Set Operations</em>'.
   * @see org.xtext.example.mydsl.fML.SetOperations
   * @generated
   */
  EClass getSetOperations();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.SetCard <em>Set Card</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Set Card</em>'.
   * @see org.xtext.example.mydsl.fML.SetCard
   * @generated
   */
  EClass getSetCard();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.SetCard#getSet <em>Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Set</em>'.
   * @see org.xtext.example.mydsl.fML.SetCard#getSet()
   * @see #getSetCard()
   * @generated
   */
  EReference getSetCard_Set();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.SetBelongs <em>Set Belongs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Set Belongs</em>'.
   * @see org.xtext.example.mydsl.fML.SetBelongs
   * @generated
   */
  EClass getSetBelongs();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.SetBelongs#getSetl <em>Setl</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Setl</em>'.
   * @see org.xtext.example.mydsl.fML.SetBelongs#getSetl()
   * @see #getSetBelongs()
   * @generated
   */
  EAttribute getSetBelongs_Setl();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.SetBelongs#getSetr <em>Setr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Setr</em>'.
   * @see org.xtext.example.mydsl.fML.SetBelongs#getSetr()
   * @see #getSetBelongs()
   * @generated
   */
  EAttribute getSetBelongs_Setr();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.SetUnionOrIntersection <em>Set Union Or Intersection</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Set Union Or Intersection</em>'.
   * @see org.xtext.example.mydsl.fML.SetUnionOrIntersection
   * @generated
   */
  EClass getSetUnionOrIntersection();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.SetUnionOrIntersection#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Op</em>'.
   * @see org.xtext.example.mydsl.fML.SetUnionOrIntersection#getOp()
   * @see #getSetUnionOrIntersection()
   * @generated
   */
  EAttribute getSetUnionOrIntersection_Op();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.SetUnionOrIntersection#getSetl <em>Setl</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Setl</em>'.
   * @see org.xtext.example.mydsl.fML.SetUnionOrIntersection#getSetl()
   * @see #getSetUnionOrIntersection()
   * @generated
   */
  EReference getSetUnionOrIntersection_Setl();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.SetUnionOrIntersection#getSetr <em>Setr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Setr</em>'.
   * @see org.xtext.example.mydsl.fML.SetUnionOrIntersection#getSetr()
   * @see #getSetUnionOrIntersection()
   * @generated
   */
  EReference getSetUnionOrIntersection_Setr();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.SetEmpty <em>Set Empty</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Set Empty</em>'.
   * @see org.xtext.example.mydsl.fML.SetEmpty
   * @generated
   */
  EClass getSetEmpty();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.SetEmpty#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.xtext.example.mydsl.fML.SetEmpty#getVal()
   * @see #getSetEmpty()
   * @generated
   */
  EAttribute getSetEmpty_Val();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.SetAddOrRemove <em>Set Add Or Remove</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Set Add Or Remove</em>'.
   * @see org.xtext.example.mydsl.fML.SetAddOrRemove
   * @generated
   */
  EClass getSetAddOrRemove();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.SetAddOrRemove#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Op</em>'.
   * @see org.xtext.example.mydsl.fML.SetAddOrRemove#getOp()
   * @see #getSetAddOrRemove()
   * @generated
   */
  EAttribute getSetAddOrRemove_Op();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.SetAddOrRemove#getSetl <em>Setl</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Setl</em>'.
   * @see org.xtext.example.mydsl.fML.SetAddOrRemove#getSetl()
   * @see #getSetAddOrRemove()
   * @generated
   */
  EReference getSetAddOrRemove_Setl();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.SetAddOrRemove#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see org.xtext.example.mydsl.fML.SetAddOrRemove#getVar()
   * @see #getSetAddOrRemove()
   * @generated
   */
  EReference getSetAddOrRemove_Var();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.IsEmptySet <em>Is Empty Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Is Empty Set</em>'.
   * @see org.xtext.example.mydsl.fML.IsEmptySet
   * @generated
   */
  EClass getIsEmptySet();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.IsEmptySet#getSet <em>Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Set</em>'.
   * @see org.xtext.example.mydsl.fML.IsEmptySet#getSet()
   * @see #getIsEmptySet()
   * @generated
   */
  EReference getIsEmptySet_Set();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.SetToNames <em>Set To Names</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Set To Names</em>'.
   * @see org.xtext.example.mydsl.fML.SetToNames
   * @generated
   */
  EClass getSetToNames();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.SetToNames#getSet <em>Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Set</em>'.
   * @see org.xtext.example.mydsl.fML.SetToNames#getSet()
   * @see #getSetToNames()
   * @generated
   */
  EReference getSetToNames_Set();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.FeatureOperation <em>Feature Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature Operation</em>'.
   * @see org.xtext.example.mydsl.fML.FeatureOperation
   * @generated
   */
  EClass getFeatureOperation();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.FeatureOperation#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Op</em>'.
   * @see org.xtext.example.mydsl.fML.FeatureOperation#getOp()
   * @see #getFeatureOperation()
   * @generated
   */
  EReference getFeatureOperation_Op();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.FeatureOperation#getFeature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Feature</em>'.
   * @see org.xtext.example.mydsl.fML.FeatureOperation#getFeature()
   * @see #getFeatureOperation()
   * @generated
   */
  EReference getFeatureOperation_Feature();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.AncestorFeature <em>Ancestor Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ancestor Feature</em>'.
   * @see org.xtext.example.mydsl.fML.AncestorFeature
   * @generated
   */
  EClass getAncestorFeature();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.AncestorFeature#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.xtext.example.mydsl.fML.AncestorFeature#getVal()
   * @see #getAncestorFeature()
   * @generated
   */
  EAttribute getAncestorFeature_Val();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.DescendantFeature <em>Descendant Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Descendant Feature</em>'.
   * @see org.xtext.example.mydsl.fML.DescendantFeature
   * @generated
   */
  EClass getDescendantFeature();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.DescendantFeature#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.xtext.example.mydsl.fML.DescendantFeature#getVal()
   * @see #getDescendantFeature()
   * @generated
   */
  EAttribute getDescendantFeature_Val();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.ChildrenFeature <em>Children Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Children Feature</em>'.
   * @see org.xtext.example.mydsl.fML.ChildrenFeature
   * @generated
   */
  EClass getChildrenFeature();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.ChildrenFeature#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.xtext.example.mydsl.fML.ChildrenFeature#getVal()
   * @see #getChildrenFeature()
   * @generated
   */
  EAttribute getChildrenFeature_Val();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.SiblingFeature <em>Sibling Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Sibling Feature</em>'.
   * @see org.xtext.example.mydsl.fML.SiblingFeature
   * @generated
   */
  EClass getSiblingFeature();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.SiblingFeature#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.xtext.example.mydsl.fML.SiblingFeature#getVal()
   * @see #getSiblingFeature()
   * @generated
   */
  EAttribute getSiblingFeature_Val();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.ParentFeature <em>Parent Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Parent Feature</em>'.
   * @see org.xtext.example.mydsl.fML.ParentFeature
   * @generated
   */
  EClass getParentFeature();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.ParentFeature#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.xtext.example.mydsl.fML.ParentFeature#getVal()
   * @see #getParentFeature()
   * @generated
   */
  EAttribute getParentFeature_Val();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.NameFeature <em>Name Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Name Feature</em>'.
   * @see org.xtext.example.mydsl.fML.NameFeature
   * @generated
   */
  EClass getNameFeature();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.NameFeature#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.xtext.example.mydsl.fML.NameFeature#getVal()
   * @see #getNameFeature()
   * @generated
   */
  EAttribute getNameFeature_Val();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.FMFeature <em>FM Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>FM Feature</em>'.
   * @see org.xtext.example.mydsl.fML.FMFeature
   * @generated
   */
  EClass getFMFeature();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.FMFeature#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.xtext.example.mydsl.fML.FMFeature#getVal()
   * @see #getFMFeature()
   * @generated
   */
  EAttribute getFMFeature_Val();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.FeatureOperator <em>Feature Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature Operator</em>'.
   * @see org.xtext.example.mydsl.fML.FeatureOperator
   * @generated
   */
  EClass getFeatureOperator();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.FeatureOperator#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.xtext.example.mydsl.fML.FeatureOperator#getVal()
   * @see #getFeatureOperator()
   * @generated
   */
  EAttribute getFeatureOperator_Val();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.StringOperation <em>String Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>String Operation</em>'.
   * @see org.xtext.example.mydsl.fML.StringOperation
   * @generated
   */
  EClass getStringOperation();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.StringInit <em>String Init</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>String Init</em>'.
   * @see org.xtext.example.mydsl.fML.StringInit
   * @generated
   */
  EClass getStringInit();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.StringInit#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.xtext.example.mydsl.fML.StringInit#getVal()
   * @see #getStringInit()
   * @generated
   */
  EAttribute getStringInit_Val();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.StringConcat <em>String Concat</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>String Concat</em>'.
   * @see org.xtext.example.mydsl.fML.StringConcat
   * @generated
   */
  EClass getStringConcat();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.StringConcat#getLstr <em>Lstr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Lstr</em>'.
   * @see org.xtext.example.mydsl.fML.StringConcat#getLstr()
   * @see #getStringConcat()
   * @generated
   */
  EReference getStringConcat_Lstr();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.StringConcat#getRstr <em>Rstr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Rstr</em>'.
   * @see org.xtext.example.mydsl.fML.StringConcat#getRstr()
   * @see #getStringConcat()
   * @generated
   */
  EReference getStringConcat_Rstr();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.StringSubstring <em>String Substring</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>String Substring</em>'.
   * @see org.xtext.example.mydsl.fML.StringSubstring
   * @generated
   */
  EClass getStringSubstring();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.StringSubstring#getStr <em>Str</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Str</em>'.
   * @see org.xtext.example.mydsl.fML.StringSubstring#getStr()
   * @see #getStringSubstring()
   * @generated
   */
  EReference getStringSubstring_Str();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.StringSubstring#getBegin <em>Begin</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Begin</em>'.
   * @see org.xtext.example.mydsl.fML.StringSubstring#getBegin()
   * @see #getStringSubstring()
   * @generated
   */
  EReference getStringSubstring_Begin();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.StringSubstring#getEnd <em>End</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>End</em>'.
   * @see org.xtext.example.mydsl.fML.StringSubstring#getEnd()
   * @see #getStringSubstring()
   * @generated
   */
  EReference getStringSubstring_End();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.StringIndexOf <em>String Index Of</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>String Index Of</em>'.
   * @see org.xtext.example.mydsl.fML.StringIndexOf
   * @generated
   */
  EClass getStringIndexOf();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.StringIndexOf#getStr <em>Str</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Str</em>'.
   * @see org.xtext.example.mydsl.fML.StringIndexOf#getStr()
   * @see #getStringIndexOf()
   * @generated
   */
  EReference getStringIndexOf_Str();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.StringIndexOf#getSchar <em>Schar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Schar</em>'.
   * @see org.xtext.example.mydsl.fML.StringIndexOf#getSchar()
   * @see #getStringIndexOf()
   * @generated
   */
  EReference getStringIndexOf_Schar();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.StringLength <em>String Length</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>String Length</em>'.
   * @see org.xtext.example.mydsl.fML.StringLength
   * @generated
   */
  EClass getStringLength();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.StringLength#getStr <em>Str</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Str</em>'.
   * @see org.xtext.example.mydsl.fML.StringLength#getStr()
   * @see #getStringLength()
   * @generated
   */
  EReference getStringLength_Str();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Compare <em>Compare</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Compare</em>'.
   * @see org.xtext.example.mydsl.fML.Compare
   * @generated
   */
  EClass getCompare();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Compare#getFm_left <em>Fm left</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fm left</em>'.
   * @see org.xtext.example.mydsl.fML.Compare#getFm_left()
   * @see #getCompare()
   * @generated
   */
  EReference getCompare_Fm_left();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Compare#getFm_right <em>Fm right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fm right</em>'.
   * @see org.xtext.example.mydsl.fML.Compare#getFm_right()
   * @see #getCompare()
   * @generated
   */
  EReference getCompare_Fm_right();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Parameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Parameter</em>'.
   * @see org.xtext.example.mydsl.fML.Parameter
   * @generated
   */
  EClass getParameter();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.Parameter#getParam <em>Param</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Param</em>'.
   * @see org.xtext.example.mydsl.fML.Parameter#getParam()
   * @see #getParameter()
   * @generated
   */
  EAttribute getParameter_Param();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.Parameter#getTyped <em>Typed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Typed</em>'.
   * @see org.xtext.example.mydsl.fML.Parameter#getTyped()
   * @see #getParameter()
   * @generated
   */
  EAttribute getParameter_Typed();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Parameter#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see org.xtext.example.mydsl.fML.Parameter#getType()
   * @see #getParameter()
   * @generated
   */
  EReference getParameter_Type();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.LoadGeneric <em>Load Generic</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Load Generic</em>'.
   * @see org.xtext.example.mydsl.fML.LoadGeneric
   * @generated
   */
  EClass getLoadGeneric();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.LoadGeneric#getStream <em>Stream</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Stream</em>'.
   * @see org.xtext.example.mydsl.fML.LoadGeneric#getStream()
   * @see #getLoadGeneric()
   * @generated
   */
  EAttribute getLoadGeneric_Stream();

  /**
   * Returns the meta object for the attribute list '{@link org.xtext.example.mydsl.fML.LoadGeneric#getParams <em>Params</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Params</em>'.
   * @see org.xtext.example.mydsl.fML.LoadGeneric#getParams()
   * @see #getLoadGeneric()
   * @generated
   */
  EAttribute getLoadGeneric_Params();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.LoadGeneric#getNs <em>Ns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ns</em>'.
   * @see org.xtext.example.mydsl.fML.LoadGeneric#getNs()
   * @see #getLoadGeneric()
   * @generated
   */
  EAttribute getLoadGeneric_Ns();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.CTCRCommand <em>CTCR Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>CTCR Command</em>'.
   * @see org.xtext.example.mydsl.fML.CTCRCommand
   * @generated
   */
  EClass getCTCRCommand();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.CTCRCommand#getFm <em>Fm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fm</em>'.
   * @see org.xtext.example.mydsl.fML.CTCRCommand#getFm()
   * @see #getCTCRCommand()
   * @generated
   */
  EReference getCTCRCommand_Fm();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Merge <em>Merge</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Merge</em>'.
   * @see org.xtext.example.mydsl.fML.Merge
   * @generated
   */
  EClass getMerge();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.Merge#getBackend <em>Backend</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Backend</em>'.
   * @see org.xtext.example.mydsl.fML.Merge#getBackend()
   * @see #getMerge()
   * @generated
   */
  EAttribute getMerge_Backend();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.Merge#getMode <em>Mode</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Mode</em>'.
   * @see org.xtext.example.mydsl.fML.Merge#getMode()
   * @see #getMerge()
   * @generated
   */
  EAttribute getMerge_Mode();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.mydsl.fML.Merge#getLfms <em>Lfms</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Lfms</em>'.
   * @see org.xtext.example.mydsl.fML.Merge#getLfms()
   * @see #getMerge()
   * @generated
   */
  EReference getMerge_Lfms();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Merge#getFms <em>Fms</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fms</em>'.
   * @see org.xtext.example.mydsl.fML.Merge#getFms()
   * @see #getMerge()
   * @generated
   */
  EReference getMerge_Fms();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.LFMArgs <em>LFM Args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>LFM Args</em>'.
   * @see org.xtext.example.mydsl.fML.LFMArgs
   * @generated
   */
  EClass getLFMArgs();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.mydsl.fML.LFMArgs#getLfms <em>Lfms</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Lfms</em>'.
   * @see org.xtext.example.mydsl.fML.LFMArgs#getLfms()
   * @see #getLFMArgs()
   * @generated
   */
  EReference getLFMArgs_Lfms();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.AggregateMerge <em>Aggregate Merge</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Aggregate Merge</em>'.
   * @see org.xtext.example.mydsl.fML.AggregateMerge
   * @generated
   */
  EClass getAggregateMerge();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.AggregateMerge#isHierarchySpecified <em>Hierarchy Specified</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Hierarchy Specified</em>'.
   * @see org.xtext.example.mydsl.fML.AggregateMerge#isHierarchySpecified()
   * @see #getAggregateMerge()
   * @generated
   */
  EAttribute getAggregateMerge_HierarchySpecified();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.AggregateMerge#getHierarchyStrategy <em>Hierarchy Strategy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Hierarchy Strategy</em>'.
   * @see org.xtext.example.mydsl.fML.AggregateMerge#getHierarchyStrategy()
   * @see #getAggregateMerge()
   * @generated
   */
  EAttribute getAggregateMerge_HierarchyStrategy();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.AggregateMerge#getMode <em>Mode</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Mode</em>'.
   * @see org.xtext.example.mydsl.fML.AggregateMerge#getMode()
   * @see #getAggregateMerge()
   * @generated
   */
  EAttribute getAggregateMerge_Mode();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.mydsl.fML.AggregateMerge#getLfms <em>Lfms</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Lfms</em>'.
   * @see org.xtext.example.mydsl.fML.AggregateMerge#getLfms()
   * @see #getAggregateMerge()
   * @generated
   */
  EReference getAggregateMerge_Lfms();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.AggregateMerge#getFms <em>Fms</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fms</em>'.
   * @see org.xtext.example.mydsl.fML.AggregateMerge#getFms()
   * @see #getAggregateMerge()
   * @generated
   */
  EReference getAggregateMerge_Fms();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Synthesis <em>Synthesis</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Synthesis</em>'.
   * @see org.xtext.example.mydsl.fML.Synthesis
   * @generated
   */
  EClass getSynthesis();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.Synthesis#isInteractive <em>Interactive</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Interactive</em>'.
   * @see org.xtext.example.mydsl.fML.Synthesis#isInteractive()
   * @see #getSynthesis()
   * @generated
   */
  EAttribute getSynthesis_Interactive();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Synthesis#getFm <em>Fm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fm</em>'.
   * @see org.xtext.example.mydsl.fML.Synthesis#getFm()
   * @see #getSynthesis()
   * @generated
   */
  EReference getSynthesis_Fm();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Synthesis#getKst <em>Kst</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Kst</em>'.
   * @see org.xtext.example.mydsl.fML.Synthesis#getKst()
   * @see #getSynthesis()
   * @generated
   */
  EReference getSynthesis_Kst();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.KnowledgeSpecification <em>Knowledge Specification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Knowledge Specification</em>'.
   * @see org.xtext.example.mydsl.fML.KnowledgeSpecification
   * @generated
   */
  EClass getKnowledgeSpecification();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.KnowledgeSpecification#getHierarchy <em>Hierarchy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Hierarchy</em>'.
   * @see org.xtext.example.mydsl.fML.KnowledgeSpecification#getHierarchy()
   * @see #getKnowledgeSpecification()
   * @generated
   */
  EReference getKnowledgeSpecification_Hierarchy();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.KnowledgeSpecification#getGroups <em>Groups</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Groups</em>'.
   * @see org.xtext.example.mydsl.fML.KnowledgeSpecification#getGroups()
   * @see #getKnowledgeSpecification()
   * @generated
   */
  EReference getKnowledgeSpecification_Groups();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.KnowledgeSpecification#getConstraints <em>Constraints</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Constraints</em>'.
   * @see org.xtext.example.mydsl.fML.KnowledgeSpecification#getConstraints()
   * @see #getKnowledgeSpecification()
   * @generated
   */
  EReference getKnowledgeSpecification_Constraints();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.HierarchySpecification <em>Hierarchy Specification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Hierarchy Specification</em>'.
   * @see org.xtext.example.mydsl.fML.HierarchySpecification
   * @generated
   */
  EClass getHierarchySpecification();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.HierarchySpecification#getHierarchy <em>Hierarchy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Hierarchy</em>'.
   * @see org.xtext.example.mydsl.fML.HierarchySpecification#getHierarchy()
   * @see #getHierarchySpecification()
   * @generated
   */
  EReference getHierarchySpecification_Hierarchy();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.mydsl.fML.HierarchySpecification#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Features</em>'.
   * @see org.xtext.example.mydsl.fML.HierarchySpecification#getFeatures()
   * @see #getHierarchySpecification()
   * @generated
   */
  EReference getHierarchySpecification_Features();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.HProduction <em>HProduction</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>HProduction</em>'.
   * @see org.xtext.example.mydsl.fML.HProduction
   * @generated
   */
  EClass getHProduction();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.HProduction#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.xtext.example.mydsl.fML.HProduction#getName()
   * @see #getHProduction()
   * @generated
   */
  EAttribute getHProduction_Name();

  /**
   * Returns the meta object for the attribute list '{@link org.xtext.example.mydsl.fML.HProduction#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Features</em>'.
   * @see org.xtext.example.mydsl.fML.HProduction#getFeatures()
   * @see #getHProduction()
   * @generated
   */
  EAttribute getHProduction_Features();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.GroupsSpecification <em>Groups Specification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Groups Specification</em>'.
   * @see org.xtext.example.mydsl.fML.GroupsSpecification
   * @generated
   */
  EClass getGroupsSpecification();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.mydsl.fML.GroupsSpecification#getGroups <em>Groups</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Groups</em>'.
   * @see org.xtext.example.mydsl.fML.GroupsSpecification#getGroups()
   * @see #getGroupsSpecification()
   * @generated
   */
  EReference getGroupsSpecification_Groups();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.GroupSpec <em>Group Spec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Group Spec</em>'.
   * @see org.xtext.example.mydsl.fML.GroupSpec
   * @generated
   */
  EClass getGroupSpec();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.GroupSpec#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.xtext.example.mydsl.fML.GroupSpec#getName()
   * @see #getGroupSpec()
   * @generated
   */
  EAttribute getGroupSpec_Name();

  /**
   * Returns the meta object for the attribute list '{@link org.xtext.example.mydsl.fML.GroupSpec#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Features</em>'.
   * @see org.xtext.example.mydsl.fML.GroupSpec#getFeatures()
   * @see #getGroupSpec()
   * @generated
   */
  EAttribute getGroupSpec_Features();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.XorGroupSpec <em>Xor Group Spec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Xor Group Spec</em>'.
   * @see org.xtext.example.mydsl.fML.XorGroupSpec
   * @generated
   */
  EClass getXorGroupSpec();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.MtxGroupSpec <em>Mtx Group Spec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mtx Group Spec</em>'.
   * @see org.xtext.example.mydsl.fML.MtxGroupSpec
   * @generated
   */
  EClass getMtxGroupSpec();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.OrGroupSpec <em>Or Group Spec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Or Group Spec</em>'.
   * @see org.xtext.example.mydsl.fML.OrGroupSpec
   * @generated
   */
  EClass getOrGroupSpec();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.ConstraintsSpecification <em>Constraints Specification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Constraints Specification</em>'.
   * @see org.xtext.example.mydsl.fML.ConstraintsSpecification
   * @generated
   */
  EClass getConstraintsSpecification();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.ConstraintsSpecification#getCsts <em>Csts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Csts</em>'.
   * @see org.xtext.example.mydsl.fML.ConstraintsSpecification#getCsts()
   * @see #getConstraintsSpecification()
   * @generated
   */
  EReference getConstraintsSpecification_Csts();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Slice <em>Slice</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Slice</em>'.
   * @see org.xtext.example.mydsl.fML.Slice
   * @generated
   */
  EClass getSlice();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Slice#getFm <em>Fm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fm</em>'.
   * @see org.xtext.example.mydsl.fML.Slice#getFm()
   * @see #getSlice()
   * @generated
   */
  EReference getSlice_Fm();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.Slice#getMode <em>Mode</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Mode</em>'.
   * @see org.xtext.example.mydsl.fML.Slice#getMode()
   * @see #getSlice()
   * @generated
   */
  EAttribute getSlice_Mode();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Slice#getFts <em>Fts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fts</em>'.
   * @see org.xtext.example.mydsl.fML.Slice#getFts()
   * @see #getSlice()
   * @generated
   */
  EReference getSlice_Fts();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Aggregate <em>Aggregate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Aggregate</em>'.
   * @see org.xtext.example.mydsl.fML.Aggregate
   * @generated
   */
  EClass getAggregate();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.Aggregate#isRenamings <em>Renamings</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Renamings</em>'.
   * @see org.xtext.example.mydsl.fML.Aggregate#isRenamings()
   * @see #getAggregate()
   * @generated
   */
  EAttribute getAggregate_Renamings();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.mydsl.fML.Aggregate#getFms <em>Fms</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Fms</em>'.
   * @see org.xtext.example.mydsl.fML.Aggregate#getFms()
   * @see #getAggregate()
   * @generated
   */
  EReference getAggregate_Fms();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Aggregate#getSfms <em>Sfms</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Sfms</em>'.
   * @see org.xtext.example.mydsl.fML.Aggregate#getSfms()
   * @see #getAggregate()
   * @generated
   */
  EReference getAggregate_Sfms();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Aggregate#getMapping <em>Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Mapping</em>'.
   * @see org.xtext.example.mydsl.fML.Aggregate#getMapping()
   * @see #getAggregate()
   * @generated
   */
  EReference getAggregate_Mapping();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.FeatureModelOperation <em>Feature Model Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature Model Operation</em>'.
   * @see org.xtext.example.mydsl.fML.FeatureModelOperation
   * @generated
   */
  EClass getFeatureModelOperation();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.EditOperation <em>Edit Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Edit Operation</em>'.
   * @see org.xtext.example.mydsl.fML.EditOperation
   * @generated
   */
  EClass getEditOperation();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Insert <em>Insert</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Insert</em>'.
   * @see org.xtext.example.mydsl.fML.Insert
   * @generated
   */
  EClass getInsert();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Insert#getAspectfm <em>Aspectfm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Aspectfm</em>'.
   * @see org.xtext.example.mydsl.fML.Insert#getAspectfm()
   * @see #getInsert()
   * @generated
   */
  EReference getInsert_Aspectfm();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Insert#getBaseft <em>Baseft</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Baseft</em>'.
   * @see org.xtext.example.mydsl.fML.Insert#getBaseft()
   * @see #getInsert()
   * @generated
   */
  EReference getInsert_Baseft();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Insert#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Op</em>'.
   * @see org.xtext.example.mydsl.fML.Insert#getOp()
   * @see #getInsert()
   * @generated
   */
  EReference getInsert_Op();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.RemoveFeature <em>Remove Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Remove Feature</em>'.
   * @see org.xtext.example.mydsl.fML.RemoveFeature
   * @generated
   */
  EClass getRemoveFeature();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.RemoveFeature#getFeature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Feature</em>'.
   * @see org.xtext.example.mydsl.fML.RemoveFeature#getFeature()
   * @see #getRemoveFeature()
   * @generated
   */
  EReference getRemoveFeature_Feature();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.RenameFeature <em>Rename Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Rename Feature</em>'.
   * @see org.xtext.example.mydsl.fML.RenameFeature
   * @generated
   */
  EClass getRenameFeature();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.RenameFeature#getFeature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Feature</em>'.
   * @see org.xtext.example.mydsl.fML.RenameFeature#getFeature()
   * @see #getRenameFeature()
   * @generated
   */
  EReference getRenameFeature_Feature();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.RenameFeature#getFeatureNew <em>Feature New</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Feature New</em>'.
   * @see org.xtext.example.mydsl.fML.RenameFeature#getFeatureNew()
   * @see #getRenameFeature()
   * @generated
   */
  EReference getRenameFeature_FeatureNew();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Extract <em>Extract</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Extract</em>'.
   * @see org.xtext.example.mydsl.fML.Extract
   * @generated
   */
  EClass getExtract();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Extract#getRootfeature <em>Rootfeature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Rootfeature</em>'.
   * @see org.xtext.example.mydsl.fML.Extract#getRootfeature()
   * @see #getExtract()
   * @generated
   */
  EReference getExtract_Rootfeature();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Assertion <em>Assertion</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Assertion</em>'.
   * @see org.xtext.example.mydsl.fML.Assertion
   * @generated
   */
  EClass getAssertion();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Assertion#getAssertion <em>Assertion</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Assertion</em>'.
   * @see org.xtext.example.mydsl.fML.Assertion#getAssertion()
   * @see #getAssertion()
   * @generated
   */
  EReference getAssertion_Assertion();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.VariableNull <em>Variable Null</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Variable Null</em>'.
   * @see org.xtext.example.mydsl.fML.VariableNull
   * @generated
   */
  EClass getVariableNull();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.VariableNull#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Var</em>'.
   * @see org.xtext.example.mydsl.fML.VariableNull#getVar()
   * @see #getVariableNull()
   * @generated
   */
  EAttribute getVariableNull_Var();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Export <em>Export</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Export</em>'.
   * @see org.xtext.example.mydsl.fML.Export
   * @generated
   */
  EClass getExport();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Export#getArg <em>Arg</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Arg</em>'.
   * @see org.xtext.example.mydsl.fML.Export#getArg()
   * @see #getExport()
   * @generated
   */
  EReference getExport_Arg();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Hidden <em>Hidden</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Hidden</em>'.
   * @see org.xtext.example.mydsl.fML.Hidden
   * @generated
   */
  EClass getHidden();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Hidden#getArg <em>Arg</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Arg</em>'.
   * @see org.xtext.example.mydsl.fML.Hidden#getArg()
   * @see #getHidden()
   * @generated
   */
  EReference getHidden_Arg();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.LVidentifier <em>LVidentifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>LVidentifier</em>'.
   * @see org.xtext.example.mydsl.fML.LVidentifier
   * @generated
   */
  EClass getLVidentifier();

  /**
   * Returns the meta object for the attribute list '{@link org.xtext.example.mydsl.fML.LVidentifier#getArgs <em>Args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Args</em>'.
   * @see org.xtext.example.mydsl.fML.LVidentifier#getArgs()
   * @see #getLVidentifier()
   * @generated
   */
  EAttribute getLVidentifier_Args();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Dependency <em>Dependency</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Dependency</em>'.
   * @see org.xtext.example.mydsl.fML.Dependency
   * @generated
   */
  EClass getDependency();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.Dependency#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Var</em>'.
   * @see org.xtext.example.mydsl.fML.Dependency#getVar()
   * @see #getDependency()
   * @generated
   */
  EAttribute getDependency_Var();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.ConfigurationCmd <em>Configuration Cmd</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Configuration Cmd</em>'.
   * @see org.xtext.example.mydsl.fML.ConfigurationCmd
   * @generated
   */
  EClass getConfigurationCmd();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.CreateConfiguration <em>Create Configuration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Create Configuration</em>'.
   * @see org.xtext.example.mydsl.fML.CreateConfiguration
   * @generated
   */
  EClass getCreateConfiguration();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.CreateConfiguration#getFm <em>Fm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fm</em>'.
   * @see org.xtext.example.mydsl.fML.CreateConfiguration#getFm()
   * @see #getCreateConfiguration()
   * @generated
   */
  EReference getCreateConfiguration_Fm();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.CompleteConfiguration <em>Complete Configuration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Complete Configuration</em>'.
   * @see org.xtext.example.mydsl.fML.CompleteConfiguration
   * @generated
   */
  EClass getCompleteConfiguration();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.CompleteConfiguration#getConfig <em>Config</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Config</em>'.
   * @see org.xtext.example.mydsl.fML.CompleteConfiguration#getConfig()
   * @see #getCompleteConfiguration()
   * @generated
   */
  EReference getCompleteConfiguration_Config();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.SelectionFeature <em>Selection Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Selection Feature</em>'.
   * @see org.xtext.example.mydsl.fML.SelectionFeature
   * @generated
   */
  EClass getSelectionFeature();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.SelectionFeature#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Op</em>'.
   * @see org.xtext.example.mydsl.fML.SelectionFeature#getOp()
   * @see #getSelectionFeature()
   * @generated
   */
  EAttribute getSelectionFeature_Op();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.mydsl.fML.SelectionFeature#getFts <em>Fts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Fts</em>'.
   * @see org.xtext.example.mydsl.fML.SelectionFeature#getFts()
   * @see #getSelectionFeature()
   * @generated
   */
  EReference getSelectionFeature_Fts();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.SelectionFeature#getConfig <em>Config</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Config</em>'.
   * @see org.xtext.example.mydsl.fML.SelectionFeature#getConfig()
   * @see #getSelectionFeature()
   * @generated
   */
  EReference getSelectionFeature_Config();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.FeatureExpression <em>Feature Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature Expression</em>'.
   * @see org.xtext.example.mydsl.fML.FeatureExpression
   * @generated
   */
  EClass getFeatureExpression();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.FeatureExpression#getFt <em>Ft</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ft</em>'.
   * @see org.xtext.example.mydsl.fML.FeatureExpression#getFt()
   * @see #getFeatureExpression()
   * @generated
   */
  EReference getFeatureExpression_Ft();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.AutoConfiguration <em>Auto Configuration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Auto Configuration</em>'.
   * @see org.xtext.example.mydsl.fML.AutoConfiguration
   * @generated
   */
  EClass getAutoConfiguration();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.AutoConfiguration#getConfig <em>Config</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Config</em>'.
   * @see org.xtext.example.mydsl.fML.AutoConfiguration#getConfig()
   * @see #getAutoConfiguration()
   * @generated
   */
  EReference getAutoConfiguration_Config();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.AutoConfiguration#getMode <em>Mode</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Mode</em>'.
   * @see org.xtext.example.mydsl.fML.AutoConfiguration#getMode()
   * @see #getAutoConfiguration()
   * @generated
   */
  EAttribute getAutoConfiguration_Mode();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.SelectedConfiguration <em>Selected Configuration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Selected Configuration</em>'.
   * @see org.xtext.example.mydsl.fML.SelectedConfiguration
   * @generated
   */
  EClass getSelectedConfiguration();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.SelectedConfiguration#getConfig <em>Config</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Config</em>'.
   * @see org.xtext.example.mydsl.fML.SelectedConfiguration#getConfig()
   * @see #getSelectedConfiguration()
   * @generated
   */
  EReference getSelectedConfiguration_Config();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.DeselectedConfiguration <em>Deselected Configuration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Deselected Configuration</em>'.
   * @see org.xtext.example.mydsl.fML.DeselectedConfiguration
   * @generated
   */
  EClass getDeselectedConfiguration();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.DeselectedConfiguration#getConfig <em>Config</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Config</em>'.
   * @see org.xtext.example.mydsl.fML.DeselectedConfiguration#getConfig()
   * @see #getDeselectedConfiguration()
   * @generated
   */
  EReference getDeselectedConfiguration_Config();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.UnselectedConfiguration <em>Unselected Configuration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Unselected Configuration</em>'.
   * @see org.xtext.example.mydsl.fML.UnselectedConfiguration
   * @generated
   */
  EClass getUnselectedConfiguration();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.UnselectedConfiguration#getConfig <em>Config</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Config</em>'.
   * @see org.xtext.example.mydsl.fML.UnselectedConfiguration#getConfig()
   * @see #getUnselectedConfiguration()
   * @generated
   */
  EReference getUnselectedConfiguration_Config();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.AsFM <em>As FM</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>As FM</em>'.
   * @see org.xtext.example.mydsl.fML.AsFM
   * @generated
   */
  EClass getAsFM();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.AsFM#getConf <em>Conf</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Conf</em>'.
   * @see org.xtext.example.mydsl.fML.AsFM#getConf()
   * @see #getAsFM()
   * @generated
   */
  EReference getAsFM_Conf();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Map <em>Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Map</em>'.
   * @see org.xtext.example.mydsl.fML.Map
   * @generated
   */
  EClass getMap();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Map#getFm <em>Fm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fm</em>'.
   * @see org.xtext.example.mydsl.fML.Map#getFm()
   * @see #getMap()
   * @generated
   */
  EReference getMap_Fm();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Map#getCst <em>Cst</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cst</em>'.
   * @see org.xtext.example.mydsl.fML.Map#getCst()
   * @see #getMap()
   * @generated
   */
  EReference getMap_Cst();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.UnMap <em>Un Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Un Map</em>'.
   * @see org.xtext.example.mydsl.fML.UnMap
   * @generated
   */
  EClass getUnMap();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.UnMap#getFm <em>Fm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fm</em>'.
   * @see org.xtext.example.mydsl.fML.UnMap#getFm()
   * @see #getUnMap()
   * @generated
   */
  EReference getUnMap_Fm();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.CleanUp <em>Clean Up</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Clean Up</em>'.
   * @see org.xtext.example.mydsl.fML.CleanUp
   * @generated
   */
  EClass getCleanUp();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.CleanUp#getFm <em>Fm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fm</em>'.
   * @see org.xtext.example.mydsl.fML.CleanUp#getFm()
   * @see #getCleanUp()
   * @generated
   */
  EReference getCleanUp_Fm();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Cores <em>Cores</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Cores</em>'.
   * @see org.xtext.example.mydsl.fML.Cores
   * @generated
   */
  EClass getCores();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Cores#getFm <em>Fm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fm</em>'.
   * @see org.xtext.example.mydsl.fML.Cores#getFm()
   * @see #getCores()
   * @generated
   */
  EReference getCores_Fm();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Deads <em>Deads</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Deads</em>'.
   * @see org.xtext.example.mydsl.fML.Deads
   * @generated
   */
  EClass getDeads();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Deads#getFm <em>Fm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fm</em>'.
   * @see org.xtext.example.mydsl.fML.Deads#getFm()
   * @see #getDeads()
   * @generated
   */
  EReference getDeads_Fm();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.FullMandatorys <em>Full Mandatorys</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Full Mandatorys</em>'.
   * @see org.xtext.example.mydsl.fML.FullMandatorys
   * @generated
   */
  EClass getFullMandatorys();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.FullMandatorys#getFm <em>Fm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fm</em>'.
   * @see org.xtext.example.mydsl.fML.FullMandatorys#getFm()
   * @see #getFullMandatorys()
   * @generated
   */
  EReference getFullMandatorys_Fm();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Cliques <em>Cliques</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Cliques</em>'.
   * @see org.xtext.example.mydsl.fML.Cliques
   * @generated
   */
  EClass getCliques();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Cliques#getFm <em>Fm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fm</em>'.
   * @see org.xtext.example.mydsl.fML.Cliques#getFm()
   * @see #getCliques()
   * @generated
   */
  EReference getCliques_Fm();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.ScriptDefinition <em>Script Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Script Definition</em>'.
   * @see org.xtext.example.mydsl.fML.ScriptDefinition
   * @generated
   */
  EClass getScriptDefinition();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.mydsl.fML.ScriptDefinition#getParams <em>Params</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Params</em>'.
   * @see org.xtext.example.mydsl.fML.ScriptDefinition#getParams()
   * @see #getScriptDefinition()
   * @generated
   */
  EReference getScriptDefinition_Params();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.mydsl.fML.ScriptDefinition#getCmds <em>Cmds</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Cmds</em>'.
   * @see org.xtext.example.mydsl.fML.ScriptDefinition#getCmds()
   * @see #getScriptDefinition()
   * @generated
   */
  EReference getScriptDefinition_Cmds();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.mydsl.fML.ScriptDefinition#getExports <em>Exports</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Exports</em>'.
   * @see org.xtext.example.mydsl.fML.ScriptDefinition#getExports()
   * @see #getScriptDefinition()
   * @generated
   */
  EReference getScriptDefinition_Exports();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Shell <em>Shell</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Shell</em>'.
   * @see org.xtext.example.mydsl.fML.Shell
   * @generated
   */
  EClass getShell();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Shell#getCmd <em>Cmd</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cmd</em>'.
   * @see org.xtext.example.mydsl.fML.Shell#getCmd()
   * @see #getShell()
   * @generated
   */
  EReference getShell_Cmd();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Exit <em>Exit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Exit</em>'.
   * @see org.xtext.example.mydsl.fML.Exit
   * @generated
   */
  EClass getExit();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.Exit#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.xtext.example.mydsl.fML.Exit#getVal()
   * @see #getExit()
   * @generated
   */
  EAttribute getExit_Val();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Exist <em>Exist</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Exist</em>'.
   * @see org.xtext.example.mydsl.fML.Exist
   * @generated
   */
  EClass getExist();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.Exist#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.xtext.example.mydsl.fML.Exist#getVal()
   * @see #getExist()
   * @generated
   */
  EAttribute getExist_Val();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.Exist#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Var</em>'.
   * @see org.xtext.example.mydsl.fML.Exist#getVar()
   * @see #getExist()
   * @generated
   */
  EAttribute getExist_Var();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.IsConflicting <em>Is Conflicting</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Is Conflicting</em>'.
   * @see org.xtext.example.mydsl.fML.IsConflicting
   * @generated
   */
  EClass getIsConflicting();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.IsConflicting#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.xtext.example.mydsl.fML.IsConflicting#getVal()
   * @see #getIsConflicting()
   * @generated
   */
  EAttribute getIsConflicting_Val();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.IsConflicting#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Var</em>'.
   * @see org.xtext.example.mydsl.fML.IsConflicting#getVar()
   * @see #getIsConflicting()
   * @generated
   */
  EAttribute getIsConflicting_Var();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Listing <em>Listing</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Listing</em>'.
   * @see org.xtext.example.mydsl.fML.Listing
   * @generated
   */
  EClass getListing();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.Listing#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.xtext.example.mydsl.fML.Listing#getVal()
   * @see #getListing()
   * @generated
   */
  EAttribute getListing_Val();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.Listing#getOpt <em>Opt</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Opt</em>'.
   * @see org.xtext.example.mydsl.fML.Listing#getOpt()
   * @see #getListing()
   * @generated
   */
  EAttribute getListing_Opt();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.State <em>State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>State</em>'.
   * @see org.xtext.example.mydsl.fML.State
   * @generated
   */
  EClass getState();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.State#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.xtext.example.mydsl.fML.State#getVal()
   * @see #getState()
   * @generated
   */
  EAttribute getState_Val();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.CopyVariable <em>Copy Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Copy Variable</em>'.
   * @see org.xtext.example.mydsl.fML.CopyVariable
   * @generated
   */
  EClass getCopyVariable();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.CopyVariable#getVid <em>Vid</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Vid</em>'.
   * @see org.xtext.example.mydsl.fML.CopyVariable#getVid()
   * @see #getCopyVariable()
   * @generated
   */
  EAttribute getCopyVariable_Vid();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.RemoveVariable <em>Remove Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Remove Variable</em>'.
   * @see org.xtext.example.mydsl.fML.RemoveVariable
   * @generated
   */
  EClass getRemoveVariable();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.RemoveVariable#getVid <em>Vid</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Vid</em>'.
   * @see org.xtext.example.mydsl.fML.RemoveVariable#getVid()
   * @see #getRemoveVariable()
   * @generated
   */
  EAttribute getRemoveVariable_Vid();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Convert <em>Convert</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Convert</em>'.
   * @see org.xtext.example.mydsl.fML.Convert
   * @generated
   */
  EClass getConvert();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Convert#getV <em>V</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>V</em>'.
   * @see org.xtext.example.mydsl.fML.Convert#getV()
   * @see #getConvert()
   * @generated
   */
  EReference getConvert_V();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.Convert#getFormat <em>Format</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Format</em>'.
   * @see org.xtext.example.mydsl.fML.Convert#getFormat()
   * @see #getConvert()
   * @generated
   */
  EAttribute getConvert_Format();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.FMLSave <em>Save</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Save</em>'.
   * @see org.xtext.example.mydsl.fML.FMLSave
   * @generated
   */
  EClass getFMLSave();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.FMLSave#getV <em>V</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>V</em>'.
   * @see org.xtext.example.mydsl.fML.FMLSave#getV()
   * @see #getFMLSave()
   * @generated
   */
  EReference getFMLSave_V();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.FMLSave#getFormat <em>Format</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Format</em>'.
   * @see org.xtext.example.mydsl.fML.FMLSave#getFormat()
   * @see #getFMLSave()
   * @generated
   */
  EAttribute getFMLSave_Format();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Hierarchy <em>Hierarchy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Hierarchy</em>'.
   * @see org.xtext.example.mydsl.fML.Hierarchy
   * @generated
   */
  EClass getHierarchy();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Hierarchy#getFm <em>Fm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fm</em>'.
   * @see org.xtext.example.mydsl.fML.Hierarchy#getFm()
   * @see #getHierarchy()
   * @generated
   */
  EReference getHierarchy_Fm();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.PrinterUtility <em>Printer Utility</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Printer Utility</em>'.
   * @see org.xtext.example.mydsl.fML.PrinterUtility
   * @generated
   */
  EClass getPrinterUtility();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.PrinterUtility#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Op</em>'.
   * @see org.xtext.example.mydsl.fML.PrinterUtility#getOp()
   * @see #getPrinterUtility()
   * @generated
   */
  EAttribute getPrinterUtility_Op();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.PrinterUtility#getArg <em>Arg</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Arg</em>'.
   * @see org.xtext.example.mydsl.fML.PrinterUtility#getArg()
   * @see #getPrinterUtility()
   * @generated
   */
  EReference getPrinterUtility_Arg();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.LArgs <em>LArgs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>LArgs</em>'.
   * @see org.xtext.example.mydsl.fML.LArgs
   * @generated
   */
  EClass getLArgs();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.mydsl.fML.LArgs#getArgs <em>Args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Args</em>'.
   * @see org.xtext.example.mydsl.fML.LArgs#getArgs()
   * @see #getLArgs()
   * @generated
   */
  EReference getLArgs_Args();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.GDisplay <em>GDisplay</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>GDisplay</em>'.
   * @see org.xtext.example.mydsl.fML.GDisplay
   * @generated
   */
  EClass getGDisplay();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.GDisplay#getCmdDisplay <em>Cmd Display</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Cmd Display</em>'.
   * @see org.xtext.example.mydsl.fML.GDisplay#getCmdDisplay()
   * @see #getGDisplay()
   * @generated
   */
  EAttribute getGDisplay_CmdDisplay();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.GDisplay#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see org.xtext.example.mydsl.fML.GDisplay#getVar()
   * @see #getGDisplay()
   * @generated
   */
  EReference getGDisplay_Var();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.GListing <em>GListing</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>GListing</em>'.
   * @see org.xtext.example.mydsl.fML.GListing
   * @generated
   */
  EClass getGListing();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.GListing#getCmd <em>Cmd</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Cmd</em>'.
   * @see org.xtext.example.mydsl.fML.GListing#getCmd()
   * @see #getGListing()
   * @generated
   */
  EAttribute getGListing_Cmd();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.ModifyVOperator <em>Modify VOperator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Modify VOperator</em>'.
   * @see org.xtext.example.mydsl.fML.ModifyVOperator
   * @generated
   */
  EClass getModifyVOperator();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.MandatoryEdit <em>Mandatory Edit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mandatory Edit</em>'.
   * @see org.xtext.example.mydsl.fML.MandatoryEdit
   * @generated
   */
  EClass getMandatoryEdit();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.MandatoryEdit#getFt <em>Ft</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ft</em>'.
   * @see org.xtext.example.mydsl.fML.MandatoryEdit#getFt()
   * @see #getMandatoryEdit()
   * @generated
   */
  EReference getMandatoryEdit_Ft();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.OptionalEdit <em>Optional Edit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Optional Edit</em>'.
   * @see org.xtext.example.mydsl.fML.OptionalEdit
   * @generated
   */
  EClass getOptionalEdit();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.OptionalEdit#getFt <em>Ft</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ft</em>'.
   * @see org.xtext.example.mydsl.fML.OptionalEdit#getFt()
   * @see #getOptionalEdit()
   * @generated
   */
  EReference getOptionalEdit_Ft();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.AlternativeEdit <em>Alternative Edit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Alternative Edit</em>'.
   * @see org.xtext.example.mydsl.fML.AlternativeEdit
   * @generated
   */
  EClass getAlternativeEdit();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.AlternativeEdit#getFts <em>Fts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fts</em>'.
   * @see org.xtext.example.mydsl.fML.AlternativeEdit#getFts()
   * @see #getAlternativeEdit()
   * @generated
   */
  EReference getAlternativeEdit_Fts();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.OrEdit <em>Or Edit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Or Edit</em>'.
   * @see org.xtext.example.mydsl.fML.OrEdit
   * @generated
   */
  EClass getOrEdit();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.OrEdit#getFts <em>Fts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fts</em>'.
   * @see org.xtext.example.mydsl.fML.OrEdit#getFts()
   * @see #getOrEdit()
   * @generated
   */
  EReference getOrEdit_Fts();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.AddConstraint <em>Add Constraint</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Add Constraint</em>'.
   * @see org.xtext.example.mydsl.fML.AddConstraint
   * @generated
   */
  EClass getAddConstraint();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.AddConstraint#getCst <em>Cst</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cst</em>'.
   * @see org.xtext.example.mydsl.fML.AddConstraint#getCst()
   * @see #getAddConstraint()
   * @generated
   */
  EReference getAddConstraint_Cst();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.AddConstraint#getFm <em>Fm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fm</em>'.
   * @see org.xtext.example.mydsl.fML.AddConstraint#getFm()
   * @see #getAddConstraint()
   * @generated
   */
  EReference getAddConstraint_Fm();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.RemoveConstraint <em>Remove Constraint</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Remove Constraint</em>'.
   * @see org.xtext.example.mydsl.fML.RemoveConstraint
   * @generated
   */
  EClass getRemoveConstraint();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.RemoveConstraint#getCst <em>Cst</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cst</em>'.
   * @see org.xtext.example.mydsl.fML.RemoveConstraint#getCst()
   * @see #getRemoveConstraint()
   * @generated
   */
  EReference getRemoveConstraint_Cst();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.RemoveConstraint#getFm <em>Fm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fm</em>'.
   * @see org.xtext.example.mydsl.fML.RemoveConstraint#getFm()
   * @see #getRemoveConstraint()
   * @generated
   */
  EReference getRemoveConstraint_Fm();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.CNF <em>CNF</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>CNF</em>'.
   * @see org.xtext.example.mydsl.fML.CNF
   * @generated
   */
  EClass getCNF();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.CNFExpression <em>CNF Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>CNF Expression</em>'.
   * @see org.xtext.example.mydsl.fML.CNFExpression
   * @generated
   */
  EClass getCNFExpression();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.CNFExpression#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.xtext.example.mydsl.fML.CNFExpression#getName()
   * @see #getCNFExpression()
   * @generated
   */
  EAttribute getCNFExpression_Name();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Neg_expr <em>Neg expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Neg expr</em>'.
   * @see org.xtext.example.mydsl.fML.Neg_expr
   * @generated
   */
  EClass getNeg_expr();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Neg_expr#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.xtext.example.mydsl.fML.Neg_expr#getExpr()
   * @see #getNeg_expr()
   * @generated
   */
  EReference getNeg_expr_Expr();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.FeatureModel <em>Feature Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature Model</em>'.
   * @see org.xtext.example.mydsl.fML.FeatureModel
   * @generated
   */
  EClass getFeatureModel();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.FeatureModel#getRoot <em>Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Root</em>'.
   * @see org.xtext.example.mydsl.fML.FeatureModel#getRoot()
   * @see #getFeatureModel()
   * @generated
   */
  EAttribute getFeatureModel_Root();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.mydsl.fML.FeatureModel#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Features</em>'.
   * @see org.xtext.example.mydsl.fML.FeatureModel#getFeatures()
   * @see #getFeatureModel()
   * @generated
   */
  EReference getFeatureModel_Features();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.mydsl.fML.FeatureModel#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expr</em>'.
   * @see org.xtext.example.mydsl.fML.FeatureModel#getExpr()
   * @see #getFeatureModel()
   * @generated
   */
  EReference getFeatureModel_Expr();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.FeatureModel#getFile <em>File</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>File</em>'.
   * @see org.xtext.example.mydsl.fML.FeatureModel#getFile()
   * @see #getFeatureModel()
   * @generated
   */
  EReference getFeatureModel_File();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Production <em>Production</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Production</em>'.
   * @see org.xtext.example.mydsl.fML.Production
   * @generated
   */
  EClass getProduction();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.Production#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.xtext.example.mydsl.fML.Production#getName()
   * @see #getProduction()
   * @generated
   */
  EAttribute getProduction_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.mydsl.fML.Production#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Features</em>'.
   * @see org.xtext.example.mydsl.fML.Production#getFeatures()
   * @see #getProduction()
   * @generated
   */
  EReference getProduction_Features();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Child <em>Child</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Child</em>'.
   * @see org.xtext.example.mydsl.fML.Child
   * @generated
   */
  EClass getChild();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Mandatory <em>Mandatory</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mandatory</em>'.
   * @see org.xtext.example.mydsl.fML.Mandatory
   * @generated
   */
  EClass getMandatory();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.Mandatory#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.xtext.example.mydsl.fML.Mandatory#getName()
   * @see #getMandatory()
   * @generated
   */
  EAttribute getMandatory_Name();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Optional <em>Optional</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Optional</em>'.
   * @see org.xtext.example.mydsl.fML.Optional
   * @generated
   */
  EClass getOptional();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.Optional#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.xtext.example.mydsl.fML.Optional#getName()
   * @see #getOptional()
   * @generated
   */
  EAttribute getOptional_Name();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Xorgroup <em>Xorgroup</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Xorgroup</em>'.
   * @see org.xtext.example.mydsl.fML.Xorgroup
   * @generated
   */
  EClass getXorgroup();

  /**
   * Returns the meta object for the attribute list '{@link org.xtext.example.mydsl.fML.Xorgroup#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Features</em>'.
   * @see org.xtext.example.mydsl.fML.Xorgroup#getFeatures()
   * @see #getXorgroup()
   * @generated
   */
  EAttribute getXorgroup_Features();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Orgroup <em>Orgroup</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Orgroup</em>'.
   * @see org.xtext.example.mydsl.fML.Orgroup
   * @generated
   */
  EClass getOrgroup();

  /**
   * Returns the meta object for the attribute list '{@link org.xtext.example.mydsl.fML.Orgroup#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Features</em>'.
   * @see org.xtext.example.mydsl.fML.Orgroup#getFeatures()
   * @see #getOrgroup()
   * @generated
   */
  EAttribute getOrgroup_Features();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Mutexgroup <em>Mutexgroup</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mutexgroup</em>'.
   * @see org.xtext.example.mydsl.fML.Mutexgroup
   * @generated
   */
  EClass getMutexgroup();

  /**
   * Returns the meta object for the attribute list '{@link org.xtext.example.mydsl.fML.Mutexgroup#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Features</em>'.
   * @see org.xtext.example.mydsl.fML.Mutexgroup#getFeatures()
   * @see #getMutexgroup()
   * @generated
   */
  EAttribute getMutexgroup_Features();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.IntegerOperation <em>Integer Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Integer Operation</em>'.
   * @see org.xtext.example.mydsl.fML.IntegerOperation
   * @generated
   */
  EClass getIntegerOperation();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.IntegerOperation#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Op</em>'.
   * @see org.xtext.example.mydsl.fML.IntegerOperation#getOp()
   * @see #getIntegerOperation()
   * @generated
   */
  EAttribute getIntegerOperation_Op();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.IntegerOperation#getRight <em>Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right</em>'.
   * @see org.xtext.example.mydsl.fML.IntegerOperation#getRight()
   * @see #getIntegerOperation()
   * @generated
   */
  EReference getIntegerOperation_Right();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.BoolOperation <em>Bool Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Bool Operation</em>'.
   * @see org.xtext.example.mydsl.fML.BoolOperation
   * @generated
   */
  EClass getBoolOperation();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.BoolOperation#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Op</em>'.
   * @see org.xtext.example.mydsl.fML.BoolOperation#getOp()
   * @see #getBoolOperation()
   * @generated
   */
  EAttribute getBoolOperation_Op();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.BoolOperation#getRight <em>Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right</em>'.
   * @see org.xtext.example.mydsl.fML.BoolOperation#getRight()
   * @see #getBoolOperation()
   * @generated
   */
  EReference getBoolOperation_Right();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.ComparisonOperation <em>Comparison Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Comparison Operation</em>'.
   * @see org.xtext.example.mydsl.fML.ComparisonOperation
   * @generated
   */
  EClass getComparisonOperation();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.ComparisonOperation#getCmpOp <em>Cmp Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Cmp Op</em>'.
   * @see org.xtext.example.mydsl.fML.ComparisonOperation#getCmpOp()
   * @see #getComparisonOperation()
   * @generated
   */
  EAttribute getComparisonOperation_CmpOp();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.ComparisonOperation#getRight <em>Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right</em>'.
   * @see org.xtext.example.mydsl.fML.ComparisonOperation#getRight()
   * @see #getComparisonOperation()
   * @generated
   */
  EReference getComparisonOperation_Right();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.SetOperation <em>Set Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Set Operation</em>'.
   * @see org.xtext.example.mydsl.fML.SetOperation
   * @generated
   */
  EClass getSetOperation();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.SetOperation#getSop <em>Sop</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Sop</em>'.
   * @see org.xtext.example.mydsl.fML.SetOperation#getSop()
   * @see #getSetOperation()
   * @generated
   */
  EAttribute getSetOperation_Sop();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.SetOperation#getRight <em>Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right</em>'.
   * @see org.xtext.example.mydsl.fML.SetOperation#getRight()
   * @see #getSetOperation()
   * @generated
   */
  EReference getSetOperation_Right();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.IntLiteral <em>Int Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Int Literal</em>'.
   * @see org.xtext.example.mydsl.fML.IntLiteral
   * @generated
   */
  EClass getIntLiteral();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.mydsl.fML.IntLiteral#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.xtext.example.mydsl.fML.IntLiteral#getValue()
   * @see #getIntLiteral()
   * @generated
   */
  EAttribute getIntLiteral_Value();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Or_expr <em>Or expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Or expr</em>'.
   * @see org.xtext.example.mydsl.fML.Or_expr
   * @generated
   */
  EClass getOr_expr();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Or_expr#getLeft <em>Left</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left</em>'.
   * @see org.xtext.example.mydsl.fML.Or_expr#getLeft()
   * @see #getOr_expr()
   * @generated
   */
  EReference getOr_expr_Left();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Or_expr#getRight <em>Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right</em>'.
   * @see org.xtext.example.mydsl.fML.Or_expr#getRight()
   * @see #getOr_expr()
   * @generated
   */
  EReference getOr_expr_Right();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.And_expr <em>And expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>And expr</em>'.
   * @see org.xtext.example.mydsl.fML.And_expr
   * @generated
   */
  EClass getAnd_expr();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.And_expr#getLeft <em>Left</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left</em>'.
   * @see org.xtext.example.mydsl.fML.And_expr#getLeft()
   * @see #getAnd_expr()
   * @generated
   */
  EReference getAnd_expr_Left();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.And_expr#getRight <em>Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right</em>'.
   * @see org.xtext.example.mydsl.fML.And_expr#getRight()
   * @see #getAnd_expr()
   * @generated
   */
  EReference getAnd_expr_Right();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Impl_expr <em>Impl expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Impl expr</em>'.
   * @see org.xtext.example.mydsl.fML.Impl_expr
   * @generated
   */
  EClass getImpl_expr();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Impl_expr#getLeft <em>Left</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left</em>'.
   * @see org.xtext.example.mydsl.fML.Impl_expr#getLeft()
   * @see #getImpl_expr()
   * @generated
   */
  EReference getImpl_expr_Left();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Impl_expr#getRight <em>Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right</em>'.
   * @see org.xtext.example.mydsl.fML.Impl_expr#getRight()
   * @see #getImpl_expr()
   * @generated
   */
  EReference getImpl_expr_Right();

  /**
   * Returns the meta object for class '{@link org.xtext.example.mydsl.fML.Biimpl_expr <em>Biimpl expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Biimpl expr</em>'.
   * @see org.xtext.example.mydsl.fML.Biimpl_expr
   * @generated
   */
  EClass getBiimpl_expr();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Biimpl_expr#getLeft <em>Left</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left</em>'.
   * @see org.xtext.example.mydsl.fML.Biimpl_expr#getLeft()
   * @see #getBiimpl_expr()
   * @generated
   */
  EReference getBiimpl_expr_Left();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.mydsl.fML.Biimpl_expr#getRight <em>Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right</em>'.
   * @see org.xtext.example.mydsl.fML.Biimpl_expr#getRight()
   * @see #getBiimpl_expr()
   * @generated
   */
  EReference getBiimpl_expr_Right();

  /**
   * Returns the meta object for enum '{@link org.xtext.example.mydsl.fML.EditConstant <em>Edit Constant</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Edit Constant</em>'.
   * @see org.xtext.example.mydsl.fML.EditConstant
   * @generated
   */
  EEnum getEditConstant();

  /**
   * Returns the meta object for enum '{@link org.xtext.example.mydsl.fML.FeatureEdgeKind <em>Feature Edge Kind</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Feature Edge Kind</em>'.
   * @see org.xtext.example.mydsl.fML.FeatureEdgeKind
   * @generated
   */
  EEnum getFeatureEdgeKind();

  /**
   * Returns the meta object for enum '{@link org.xtext.example.mydsl.fML.KindOfGet <em>Kind Of Get</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Kind Of Get</em>'.
   * @see org.xtext.example.mydsl.fML.KindOfGet
   * @generated
   */
  EEnum getKindOfGet();

  /**
   * Returns the meta object for enum '{@link org.xtext.example.mydsl.fML.KindOfCompute <em>Kind Of Compute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Kind Of Compute</em>'.
   * @see org.xtext.example.mydsl.fML.KindOfCompute
   * @generated
   */
  EEnum getKindOfCompute();

  /**
   * Returns the meta object for enum '{@link org.xtext.example.mydsl.fML.KindOfGetGroups <em>Kind Of Get Groups</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Kind Of Get Groups</em>'.
   * @see org.xtext.example.mydsl.fML.KindOfGetGroups
   * @generated
   */
  EEnum getKindOfGetGroups();

  /**
   * Returns the meta object for enum '{@link org.xtext.example.mydsl.fML.KindOfComputeGroups <em>Kind Of Compute Groups</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Kind Of Compute Groups</em>'.
   * @see org.xtext.example.mydsl.fML.KindOfComputeGroups
   * @generated
   */
  EEnum getKindOfComputeGroups();

  /**
   * Returns the meta object for enum '{@link org.xtext.example.mydsl.fML.BDDBackend <em>BDD Backend</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>BDD Backend</em>'.
   * @see org.xtext.example.mydsl.fML.BDDBackend
   * @generated
   */
  EEnum getBDDBackend();

  /**
   * Returns the meta object for enum '{@link org.xtext.example.mydsl.fML.MergeMode <em>Merge Mode</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Merge Mode</em>'.
   * @see org.xtext.example.mydsl.fML.MergeMode
   * @generated
   */
  EEnum getMergeMode();

  /**
   * Returns the meta object for enum '{@link org.xtext.example.mydsl.fML.HierarchyStrategy <em>Hierarchy Strategy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Hierarchy Strategy</em>'.
   * @see org.xtext.example.mydsl.fML.HierarchyStrategy
   * @generated
   */
  EEnum getHierarchyStrategy();

  /**
   * Returns the meta object for enum '{@link org.xtext.example.mydsl.fML.SliceMode <em>Slice Mode</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Slice Mode</em>'.
   * @see org.xtext.example.mydsl.fML.SliceMode
   * @generated
   */
  EEnum getSliceMode();

  /**
   * Returns the meta object for enum '{@link org.xtext.example.mydsl.fML.ComparisonOperator <em>Comparison Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Comparison Operator</em>'.
   * @see org.xtext.example.mydsl.fML.ComparisonOperator
   * @generated
   */
  EEnum getComparisonOperator();

  /**
   * Returns the meta object for enum '{@link org.xtext.example.mydsl.fML.SetOperator <em>Set Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Set Operator</em>'.
   * @see org.xtext.example.mydsl.fML.SetOperator
   * @generated
   */
  EEnum getSetOperator();

  /**
   * Returns the meta object for enum '{@link org.xtext.example.mydsl.fML.OpSelection <em>Op Selection</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Op Selection</em>'.
   * @see org.xtext.example.mydsl.fML.OpSelection
   * @generated
   */
  EEnum getOpSelection();

  /**
   * Returns the meta object for enum '{@link org.xtext.example.mydsl.fML.AutoConfMode <em>Auto Conf Mode</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Auto Conf Mode</em>'.
   * @see org.xtext.example.mydsl.fML.AutoConfMode
   * @generated
   */
  EEnum getAutoConfMode();

  /**
   * Returns the meta object for enum '{@link org.xtext.example.mydsl.fML.OPT_LISTING <em>OPT LISTING</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>OPT LISTING</em>'.
   * @see org.xtext.example.mydsl.fML.OPT_LISTING
   * @generated
   */
  EEnum getOPT_LISTING();

  /**
   * Returns the meta object for enum '{@link org.xtext.example.mydsl.fML.FMFormat <em>FM Format</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>FM Format</em>'.
   * @see org.xtext.example.mydsl.fML.FMFormat
   * @generated
   */
  EEnum getFMFormat();

  /**
   * Returns the meta object for enum '{@link org.xtext.example.mydsl.fML.BOOL_Operator <em>BOOL Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>BOOL Operator</em>'.
   * @see org.xtext.example.mydsl.fML.BOOL_Operator
   * @generated
   */
  EEnum getBOOL_Operator();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  FMLFactory getFMLFactory();

} //FMLPackage
