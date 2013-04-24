/**
 */
package org.xtext.example.mydsl.fML;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>FM Format</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.xtext.example.mydsl.fML.FMLPackage#getFMFormat()
 * @model
 * @generated
 */
public enum FMFormat implements Enumerator
{
  /**
   * The '<em><b>DIMACS</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #DIMACS_VALUE
   * @generated
   * @ordered
   */
  DIMACS(0, "DIMACS", "DIMACS"),

  /**
   * The '<em><b>FMLCONSTRAINT</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #FMLCONSTRAINT_VALUE
   * @generated
   * @ordered
   */
  FMLCONSTRAINT(1, "FMLCONSTRAINT", "fmlconstraints"),

  /**
   * The '<em><b>FMLBDD</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #FMLBDD_VALUE
   * @generated
   * @ordered
   */
  FMLBDD(2, "FMLBDD", "fmlbdd"),

  /**
   * The '<em><b>FIDE</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #FIDE_VALUE
   * @generated
   * @ordered
   */
  FIDE(3, "FIDE", "featureide"),

  /**
   * The '<em><b>FCALC</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #FCALC_VALUE
   * @generated
   * @ordered
   */
  FCALC(4, "FCALC", "fmcalc"),

  /**
   * The '<em><b>FFML</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #FFML_VALUE
   * @generated
   * @ordered
   */
  FFML(5, "FFML", "fml"),

  /**
   * The '<em><b>FSPLOT</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #FSPLOT_VALUE
   * @generated
   * @ordered
   */
  FSPLOT(6, "FSPLOT", "SPLOT"),

  /**
   * The '<em><b>FTVL</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #FTVL_VALUE
   * @generated
   * @ordered
   */
  FTVL(7, "FTVL", "TVL"),

  /**
   * The '<em><b>FTRISKELL</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #FTRISKELL_VALUE
   * @generated
   * @ordered
   */
  FTRISKELL(8, "FTRISKELL", "fd"),

  /**
   * The '<em><b>FFML2</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #FFML2_VALUE
   * @generated
   * @ordered
   */
  FFML2(9, "FFML2", "xmi"),

  /**
   * The '<em><b>S2T2</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #S2T2_VALUE
   * @generated
   * @ordered
   */
  S2T2(10, "S2T2", "S2T2"),

  /**
   * The '<em><b>FMLBDD ONLY</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #FMLBDD_ONLY_VALUE
   * @generated
   * @ordered
   */
  FMLBDD_ONLY(11, "FMLBDD_ONLY", "bdd");

  /**
   * The '<em><b>DIMACS</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>DIMACS</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #DIMACS
   * @model
   * @generated
   * @ordered
   */
  public static final int DIMACS_VALUE = 0;

  /**
   * The '<em><b>FMLCONSTRAINT</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>FMLCONSTRAINT</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #FMLCONSTRAINT
   * @model literal="fmlconstraints"
   * @generated
   * @ordered
   */
  public static final int FMLCONSTRAINT_VALUE = 1;

  /**
   * The '<em><b>FMLBDD</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>FMLBDD</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #FMLBDD
   * @model literal="fmlbdd"
   * @generated
   * @ordered
   */
  public static final int FMLBDD_VALUE = 2;

  /**
   * The '<em><b>FIDE</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>FIDE</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #FIDE
   * @model literal="featureide"
   * @generated
   * @ordered
   */
  public static final int FIDE_VALUE = 3;

  /**
   * The '<em><b>FCALC</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>FCALC</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #FCALC
   * @model literal="fmcalc"
   * @generated
   * @ordered
   */
  public static final int FCALC_VALUE = 4;

  /**
   * The '<em><b>FFML</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>FFML</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #FFML
   * @model literal="fml"
   * @generated
   * @ordered
   */
  public static final int FFML_VALUE = 5;

  /**
   * The '<em><b>FSPLOT</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>FSPLOT</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #FSPLOT
   * @model literal="SPLOT"
   * @generated
   * @ordered
   */
  public static final int FSPLOT_VALUE = 6;

  /**
   * The '<em><b>FTVL</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>FTVL</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #FTVL
   * @model literal="TVL"
   * @generated
   * @ordered
   */
  public static final int FTVL_VALUE = 7;

  /**
   * The '<em><b>FTRISKELL</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>FTRISKELL</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #FTRISKELL
   * @model literal="fd"
   * @generated
   * @ordered
   */
  public static final int FTRISKELL_VALUE = 8;

  /**
   * The '<em><b>FFML2</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>FFML2</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #FFML2
   * @model literal="xmi"
   * @generated
   * @ordered
   */
  public static final int FFML2_VALUE = 9;

  /**
   * The '<em><b>S2T2</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>S2T2</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #S2T2
   * @model
   * @generated
   * @ordered
   */
  public static final int S2T2_VALUE = 10;

  /**
   * The '<em><b>FMLBDD ONLY</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>FMLBDD ONLY</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #FMLBDD_ONLY
   * @model literal="bdd"
   * @generated
   * @ordered
   */
  public static final int FMLBDD_ONLY_VALUE = 11;

  /**
   * An array of all the '<em><b>FM Format</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final FMFormat[] VALUES_ARRAY =
    new FMFormat[]
    {
      DIMACS,
      FMLCONSTRAINT,
      FMLBDD,
      FIDE,
      FCALC,
      FFML,
      FSPLOT,
      FTVL,
      FTRISKELL,
      FFML2,
      S2T2,
      FMLBDD_ONLY,
    };

  /**
   * A public read-only list of all the '<em><b>FM Format</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<FMFormat> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>FM Format</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static FMFormat get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      FMFormat result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>FM Format</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static FMFormat getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      FMFormat result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>FM Format</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static FMFormat get(int value)
  {
    switch (value)
    {
      case DIMACS_VALUE: return DIMACS;
      case FMLCONSTRAINT_VALUE: return FMLCONSTRAINT;
      case FMLBDD_VALUE: return FMLBDD;
      case FIDE_VALUE: return FIDE;
      case FCALC_VALUE: return FCALC;
      case FFML_VALUE: return FFML;
      case FSPLOT_VALUE: return FSPLOT;
      case FTVL_VALUE: return FTVL;
      case FTRISKELL_VALUE: return FTRISKELL;
      case FFML2_VALUE: return FFML2;
      case S2T2_VALUE: return S2T2;
      case FMLBDD_ONLY_VALUE: return FMLBDD_ONLY;
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
  private FMFormat(int value, String name, String literal)
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
  
} //FMFormat
