/**
 */
package org.xtext.example.mydsl.fML;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Kind Of Get</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.xtext.example.mydsl.fML.FMLPackage#getKindOfGet()
 * @model
 * @generated
 */
public enum KindOfGet implements Enumerator
{
  /**
   * The '<em><b>HIERARCHY IMPLIES</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #HIERARCHY_IMPLIES_VALUE
   * @generated
   * @ordered
   */
  HIERARCHY_IMPLIES(0, "HIERARCHY_IMPLIES", "getImpliesHierarchy"),

  /**
   * The '<em><b>HIERARCHY EXCLUDES</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #HIERARCHY_EXCLUDES_VALUE
   * @generated
   * @ordered
   */
  HIERARCHY_EXCLUDES(1, "HIERARCHY_EXCLUDES", "getExcludesHierarchy"),

  /**
   * The '<em><b>HIERARCHY BIIMPLIES</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #HIERARCHY_BIIMPLIES_VALUE
   * @generated
   * @ordered
   */
  HIERARCHY_BIIMPLIES(2, "HIERARCHY_BIIMPLIES", "getBiimpliesHierarchy"),

  /**
   * The '<em><b>CONSTRAINT IMPLIES</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #CONSTRAINT_IMPLIES_VALUE
   * @generated
   * @ordered
   */
  CONSTRAINT_IMPLIES(3, "CONSTRAINT_IMPLIES", "getImpliesConstraint"),

  /**
   * The '<em><b>CONSTRAINT EXCLUDES</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #CONSTRAINT_EXCLUDES_VALUE
   * @generated
   * @ordered
   */
  CONSTRAINT_EXCLUDES(4, "CONSTRAINT_EXCLUDES", "getExcludesConstraint"),

  /**
   * The '<em><b>CONSTRAINT BIIMPLIES</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #CONSTRAINT_BIIMPLIES_VALUE
   * @generated
   * @ordered
   */
  CONSTRAINT_BIIMPLIES(5, "CONSTRAINT_BIIMPLIES", "getBiimpliesConstraint");

  /**
   * The '<em><b>HIERARCHY IMPLIES</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>HIERARCHY IMPLIES</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #HIERARCHY_IMPLIES
   * @model literal="getImpliesHierarchy"
   * @generated
   * @ordered
   */
  public static final int HIERARCHY_IMPLIES_VALUE = 0;

  /**
   * The '<em><b>HIERARCHY EXCLUDES</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>HIERARCHY EXCLUDES</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #HIERARCHY_EXCLUDES
   * @model literal="getExcludesHierarchy"
   * @generated
   * @ordered
   */
  public static final int HIERARCHY_EXCLUDES_VALUE = 1;

  /**
   * The '<em><b>HIERARCHY BIIMPLIES</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>HIERARCHY BIIMPLIES</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #HIERARCHY_BIIMPLIES
   * @model literal="getBiimpliesHierarchy"
   * @generated
   * @ordered
   */
  public static final int HIERARCHY_BIIMPLIES_VALUE = 2;

  /**
   * The '<em><b>CONSTRAINT IMPLIES</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>CONSTRAINT IMPLIES</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #CONSTRAINT_IMPLIES
   * @model literal="getImpliesConstraint"
   * @generated
   * @ordered
   */
  public static final int CONSTRAINT_IMPLIES_VALUE = 3;

  /**
   * The '<em><b>CONSTRAINT EXCLUDES</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>CONSTRAINT EXCLUDES</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #CONSTRAINT_EXCLUDES
   * @model literal="getExcludesConstraint"
   * @generated
   * @ordered
   */
  public static final int CONSTRAINT_EXCLUDES_VALUE = 4;

  /**
   * The '<em><b>CONSTRAINT BIIMPLIES</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>CONSTRAINT BIIMPLIES</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #CONSTRAINT_BIIMPLIES
   * @model literal="getBiimpliesConstraint"
   * @generated
   * @ordered
   */
  public static final int CONSTRAINT_BIIMPLIES_VALUE = 5;

  /**
   * An array of all the '<em><b>Kind Of Get</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final KindOfGet[] VALUES_ARRAY =
    new KindOfGet[]
    {
      HIERARCHY_IMPLIES,
      HIERARCHY_EXCLUDES,
      HIERARCHY_BIIMPLIES,
      CONSTRAINT_IMPLIES,
      CONSTRAINT_EXCLUDES,
      CONSTRAINT_BIIMPLIES,
    };

  /**
   * A public read-only list of all the '<em><b>Kind Of Get</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<KindOfGet> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Kind Of Get</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static KindOfGet get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      KindOfGet result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Kind Of Get</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static KindOfGet getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      KindOfGet result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Kind Of Get</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static KindOfGet get(int value)
  {
    switch (value)
    {
      case HIERARCHY_IMPLIES_VALUE: return HIERARCHY_IMPLIES;
      case HIERARCHY_EXCLUDES_VALUE: return HIERARCHY_EXCLUDES;
      case HIERARCHY_BIIMPLIES_VALUE: return HIERARCHY_BIIMPLIES;
      case CONSTRAINT_IMPLIES_VALUE: return CONSTRAINT_IMPLIES;
      case CONSTRAINT_EXCLUDES_VALUE: return CONSTRAINT_EXCLUDES;
      case CONSTRAINT_BIIMPLIES_VALUE: return CONSTRAINT_BIIMPLIES;
    }
    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final int value;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String name;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String literal;

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private KindOfGet(int value, String name, String literal)
  {
    this.value = value;
    this.name = name;
    this.literal = literal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLiteral()
  {
    return literal;
  }

  /**
   * Returns the literal value of the enumerator, which is its string representation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    return literal;
  }
  
} //KindOfGet
