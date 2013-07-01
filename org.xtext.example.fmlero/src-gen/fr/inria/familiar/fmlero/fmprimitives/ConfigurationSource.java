/**
 */
package fr.inria.familiar.fmlero.fmprimitives;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Configuration Source</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getConfigurationSource()
 * @model
 * @generated
 */
public enum ConfigurationSource implements Enumerator
{
  /**
   * The '<em><b>MODEL</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #MODEL_VALUE
   * @generated
   * @ordered
   */
  MODEL(0, "MODEL", "MODEL"),

  /**
   * The '<em><b>MODELCONSEQUENCE</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #MODELCONSEQUENCE_VALUE
   * @generated
   * @ordered
   */
  MODELCONSEQUENCE(1, "MODELCONSEQUENCE", "MODELCONSEQUENCE"),

  /**
   * The '<em><b>USER</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #USER_VALUE
   * @generated
   * @ordered
   */
  USER(2, "USER", "USER"),

  /**
   * The '<em><b>USERCONSEQUENCE</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #USERCONSEQUENCE_VALUE
   * @generated
   * @ordered
   */
  USERCONSEQUENCE(3, "USERCONSEQUENCE", "USERCONSEQUENCE");

  /**
   * The '<em><b>MODEL</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>MODEL</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #MODEL
   * @model
   * @generated
   * @ordered
   */
  public static final int MODEL_VALUE = 0;

  /**
   * The '<em><b>MODELCONSEQUENCE</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>MODELCONSEQUENCE</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #MODELCONSEQUENCE
   * @model
   * @generated
   * @ordered
   */
  public static final int MODELCONSEQUENCE_VALUE = 1;

  /**
   * The '<em><b>USER</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>USER</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #USER
   * @model
   * @generated
   * @ordered
   */
  public static final int USER_VALUE = 2;

  /**
   * The '<em><b>USERCONSEQUENCE</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>USERCONSEQUENCE</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #USERCONSEQUENCE
   * @model
   * @generated
   * @ordered
   */
  public static final int USERCONSEQUENCE_VALUE = 3;

  /**
   * An array of all the '<em><b>Configuration Source</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final ConfigurationSource[] VALUES_ARRAY =
    new ConfigurationSource[]
    {
      MODEL,
      MODELCONSEQUENCE,
      USER,
      USERCONSEQUENCE,
    };

  /**
   * A public read-only list of all the '<em><b>Configuration Source</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<ConfigurationSource> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Configuration Source</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ConfigurationSource get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      ConfigurationSource result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Configuration Source</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ConfigurationSource getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      ConfigurationSource result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Configuration Source</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ConfigurationSource get(int value)
  {
    switch (value)
    {
      case MODEL_VALUE: return MODEL;
      case MODELCONSEQUENCE_VALUE: return MODELCONSEQUENCE;
      case USER_VALUE: return USER;
      case USERCONSEQUENCE_VALUE: return USERCONSEQUENCE;
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
  private ConfigurationSource(int value, String name, String literal)
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
  
} //ConfigurationSource
