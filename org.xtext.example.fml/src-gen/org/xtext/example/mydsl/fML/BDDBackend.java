/**
 */
package org.xtext.example.mydsl.fML;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>BDD Backend</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.xtext.example.mydsl.fML.FMLPackage#getBDDBackend()
 * @model
 * @generated
 */
public enum BDDBackend implements Enumerator
{
  /**
   * The '<em><b>BDD DEFAULT</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #BDD_DEFAULT_VALUE
   * @generated
   * @ordered
   */
  BDD_DEFAULT(0, "BDD_DEFAULT", "@backend=DEFAULT"),

  /**
   * The '<em><b>BDD BASIC</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #BDD_BASIC_VALUE
   * @generated
   * @ordered
   */
  BDD_BASIC(1, "BDD_BASIC", "@backend=BDD"),

  /**
   * The '<em><b>BDD SPLOT</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #BDD_SPLOT_VALUE
   * @generated
   * @ordered
   */
  BDD_SPLOT(2, "BDD_SPLOT", "@backend=BDD_SPLOT");

  /**
   * The '<em><b>BDD DEFAULT</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>BDD DEFAULT</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #BDD_DEFAULT
   * @model literal="@backend=DEFAULT"
   * @generated
   * @ordered
   */
  public static final int BDD_DEFAULT_VALUE = 0;

  /**
   * The '<em><b>BDD BASIC</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>BDD BASIC</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #BDD_BASIC
   * @model literal="@backend=BDD"
   * @generated
   * @ordered
   */
  public static final int BDD_BASIC_VALUE = 1;

  /**
   * The '<em><b>BDD SPLOT</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>BDD SPLOT</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #BDD_SPLOT
   * @model literal="@backend=BDD_SPLOT"
   * @generated
   * @ordered
   */
  public static final int BDD_SPLOT_VALUE = 2;

  /**
   * An array of all the '<em><b>BDD Backend</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final BDDBackend[] VALUES_ARRAY =
    new BDDBackend[]
    {
      BDD_DEFAULT,
      BDD_BASIC,
      BDD_SPLOT,
    };

  /**
   * A public read-only list of all the '<em><b>BDD Backend</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<BDDBackend> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>BDD Backend</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static BDDBackend get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      BDDBackend result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>BDD Backend</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static BDDBackend getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      BDDBackend result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>BDD Backend</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static BDDBackend get(int value)
  {
    switch (value)
    {
      case BDD_DEFAULT_VALUE: return BDD_DEFAULT;
      case BDD_BASIC_VALUE: return BDD_BASIC;
      case BDD_SPLOT_VALUE: return BDD_SPLOT;
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
  private BDDBackend(int value, String name, String literal)
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
  
} //BDDBackend
