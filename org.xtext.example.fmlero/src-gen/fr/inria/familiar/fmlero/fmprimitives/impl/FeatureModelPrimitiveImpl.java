/**
 */
package fr.inria.familiar.fmlero.fmprimitives.impl;

import fr.inria.familiar.fmlero.fmprimitives.ConfigurationSource;
import fr.inria.familiar.fmlero.fmprimitives.Explanation;
import fr.inria.familiar.fmlero.fmprimitives.FeatureModelPrimitive;
import fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Model Primitive</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureModelPrimitiveImpl#getId <em>Id</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureModelPrimitiveImpl#getName <em>Name</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureModelPrimitiveImpl#getConfigurationSource <em>Configuration Source</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureModelPrimitiveImpl#getExplanations <em>Explanations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureModelPrimitiveImpl extends MinimalEObjectImpl.Container implements FeatureModelPrimitive
{
  /**
   * The default value of the '{@link #getId() <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected static final String ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected String id = ID_EDEFAULT;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getConfigurationSource() <em>Configuration Source</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConfigurationSource()
   * @generated
   * @ordered
   */
  protected static final ConfigurationSource CONFIGURATION_SOURCE_EDEFAULT = ConfigurationSource.MODEL;

  /**
   * The cached value of the '{@link #getConfigurationSource() <em>Configuration Source</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConfigurationSource()
   * @generated
   * @ordered
   */
  protected ConfigurationSource configurationSource = CONFIGURATION_SOURCE_EDEFAULT;

  /**
   * The cached value of the '{@link #getExplanations() <em>Explanations</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExplanations()
   * @generated
   * @ordered
   */
  protected EList<Explanation> explanations;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FeatureModelPrimitiveImpl()
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
    return FmprimitivesPackage.Literals.FEATURE_MODEL_PRIMITIVE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getId()
  {
    return id;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setId(String newId)
  {
    String oldId = id;
    id = newId;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FmprimitivesPackage.FEATURE_MODEL_PRIMITIVE__ID, oldId, id));
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
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FmprimitivesPackage.FEATURE_MODEL_PRIMITIVE__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConfigurationSource getConfigurationSource()
  {
    return configurationSource;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConfigurationSource(ConfigurationSource newConfigurationSource)
  {
    ConfigurationSource oldConfigurationSource = configurationSource;
    configurationSource = newConfigurationSource == null ? CONFIGURATION_SOURCE_EDEFAULT : newConfigurationSource;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FmprimitivesPackage.FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE, oldConfigurationSource, configurationSource));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Explanation> getExplanations()
  {
    if (explanations == null)
    {
      explanations = new EObjectResolvingEList<Explanation>(Explanation.class, this, FmprimitivesPackage.FEATURE_MODEL_PRIMITIVE__EXPLANATIONS);
    }
    return explanations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case FmprimitivesPackage.FEATURE_MODEL_PRIMITIVE__ID:
        return getId();
      case FmprimitivesPackage.FEATURE_MODEL_PRIMITIVE__NAME:
        return getName();
      case FmprimitivesPackage.FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE:
        return getConfigurationSource();
      case FmprimitivesPackage.FEATURE_MODEL_PRIMITIVE__EXPLANATIONS:
        return getExplanations();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case FmprimitivesPackage.FEATURE_MODEL_PRIMITIVE__ID:
        setId((String)newValue);
        return;
      case FmprimitivesPackage.FEATURE_MODEL_PRIMITIVE__NAME:
        setName((String)newValue);
        return;
      case FmprimitivesPackage.FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE:
        setConfigurationSource((ConfigurationSource)newValue);
        return;
      case FmprimitivesPackage.FEATURE_MODEL_PRIMITIVE__EXPLANATIONS:
        getExplanations().clear();
        getExplanations().addAll((Collection<? extends Explanation>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case FmprimitivesPackage.FEATURE_MODEL_PRIMITIVE__ID:
        setId(ID_EDEFAULT);
        return;
      case FmprimitivesPackage.FEATURE_MODEL_PRIMITIVE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case FmprimitivesPackage.FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE:
        setConfigurationSource(CONFIGURATION_SOURCE_EDEFAULT);
        return;
      case FmprimitivesPackage.FEATURE_MODEL_PRIMITIVE__EXPLANATIONS:
        getExplanations().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case FmprimitivesPackage.FEATURE_MODEL_PRIMITIVE__ID:
        return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
      case FmprimitivesPackage.FEATURE_MODEL_PRIMITIVE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case FmprimitivesPackage.FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE:
        return configurationSource != CONFIGURATION_SOURCE_EDEFAULT;
      case FmprimitivesPackage.FEATURE_MODEL_PRIMITIVE__EXPLANATIONS:
        return explanations != null && !explanations.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (id: ");
    result.append(id);
    result.append(", name: ");
    result.append(name);
    result.append(", configurationSource: ");
    result.append(configurationSource);
    result.append(')');
    return result.toString();
  }

} //FeatureModelPrimitiveImpl
