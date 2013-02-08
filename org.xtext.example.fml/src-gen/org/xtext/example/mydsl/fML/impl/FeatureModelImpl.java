/**
 */
package org.xtext.example.mydsl.fML.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.xtext.example.mydsl.fML.CNF;
import org.xtext.example.mydsl.fML.FMLPackage;
import org.xtext.example.mydsl.fML.FeatureModel;
import org.xtext.example.mydsl.fML.Production;
import org.xtext.example.mydsl.fML.StringExpr;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.FeatureModelImpl#getRoot <em>Root</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.FeatureModelImpl#getFeatures <em>Features</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.FeatureModelImpl#getExpr <em>Expr</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.FeatureModelImpl#getFile <em>File</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureModelImpl extends CommandImpl implements FeatureModel
{
  /**
   * The default value of the '{@link #getRoot() <em>Root</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRoot()
   * @generated
   * @ordered
   */
  protected static final String ROOT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getRoot() <em>Root</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRoot()
   * @generated
   * @ordered
   */
  protected String root = ROOT_EDEFAULT;

  /**
   * The cached value of the '{@link #getFeatures() <em>Features</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeatures()
   * @generated
   * @ordered
   */
  protected EList<Production> features;

  /**
   * The cached value of the '{@link #getExpr() <em>Expr</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpr()
   * @generated
   * @ordered
   */
  protected EList<CNF> expr;

  /**
   * The cached value of the '{@link #getFile() <em>File</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFile()
   * @generated
   * @ordered
   */
  protected StringExpr file;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FeatureModelImpl()
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
    return FMLPackage.eINSTANCE.getFeatureModel();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getRoot()
  {
    return root;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRoot(String newRoot)
  {
    String oldRoot = root;
    root = newRoot;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.FEATURE_MODEL__ROOT, oldRoot, root));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Production> getFeatures()
  {
    if (features == null)
    {
      features = new EObjectContainmentEList<Production>(Production.class, this, FMLPackage.FEATURE_MODEL__FEATURES);
    }
    return features;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<CNF> getExpr()
  {
    if (expr == null)
    {
      expr = new EObjectContainmentEList<CNF>(CNF.class, this, FMLPackage.FEATURE_MODEL__EXPR);
    }
    return expr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StringExpr getFile()
  {
    return file;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFile(StringExpr newFile, NotificationChain msgs)
  {
    StringExpr oldFile = file;
    file = newFile;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.FEATURE_MODEL__FILE, oldFile, newFile);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFile(StringExpr newFile)
  {
    if (newFile != file)
    {
      NotificationChain msgs = null;
      if (file != null)
        msgs = ((InternalEObject)file).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.FEATURE_MODEL__FILE, null, msgs);
      if (newFile != null)
        msgs = ((InternalEObject)newFile).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.FEATURE_MODEL__FILE, null, msgs);
      msgs = basicSetFile(newFile, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.FEATURE_MODEL__FILE, newFile, newFile));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case FMLPackage.FEATURE_MODEL__FEATURES:
        return ((InternalEList<?>)getFeatures()).basicRemove(otherEnd, msgs);
      case FMLPackage.FEATURE_MODEL__EXPR:
        return ((InternalEList<?>)getExpr()).basicRemove(otherEnd, msgs);
      case FMLPackage.FEATURE_MODEL__FILE:
        return basicSetFile(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case FMLPackage.FEATURE_MODEL__ROOT:
        return getRoot();
      case FMLPackage.FEATURE_MODEL__FEATURES:
        return getFeatures();
      case FMLPackage.FEATURE_MODEL__EXPR:
        return getExpr();
      case FMLPackage.FEATURE_MODEL__FILE:
        return getFile();
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
      case FMLPackage.FEATURE_MODEL__ROOT:
        setRoot((String)newValue);
        return;
      case FMLPackage.FEATURE_MODEL__FEATURES:
        getFeatures().clear();
        getFeatures().addAll((Collection<? extends Production>)newValue);
        return;
      case FMLPackage.FEATURE_MODEL__EXPR:
        getExpr().clear();
        getExpr().addAll((Collection<? extends CNF>)newValue);
        return;
      case FMLPackage.FEATURE_MODEL__FILE:
        setFile((StringExpr)newValue);
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
      case FMLPackage.FEATURE_MODEL__ROOT:
        setRoot(ROOT_EDEFAULT);
        return;
      case FMLPackage.FEATURE_MODEL__FEATURES:
        getFeatures().clear();
        return;
      case FMLPackage.FEATURE_MODEL__EXPR:
        getExpr().clear();
        return;
      case FMLPackage.FEATURE_MODEL__FILE:
        setFile((StringExpr)null);
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
      case FMLPackage.FEATURE_MODEL__ROOT:
        return ROOT_EDEFAULT == null ? root != null : !ROOT_EDEFAULT.equals(root);
      case FMLPackage.FEATURE_MODEL__FEATURES:
        return features != null && !features.isEmpty();
      case FMLPackage.FEATURE_MODEL__EXPR:
        return expr != null && !expr.isEmpty();
      case FMLPackage.FEATURE_MODEL__FILE:
        return file != null;
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
    result.append(" (root: ");
    result.append(root);
    result.append(')');
    return result.toString();
  }

} //FeatureModelImpl
