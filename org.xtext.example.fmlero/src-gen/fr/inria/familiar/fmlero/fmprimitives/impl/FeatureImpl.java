/**
 */
package fr.inria.familiar.fmlero.fmprimitives.impl;

import fr.inria.familiar.fmlero.fmprimitives.DirectedRelationship;
import fr.inria.familiar.fmlero.fmprimitives.EliminatedFeature;
import fr.inria.familiar.fmlero.fmprimitives.Feature;
import fr.inria.familiar.fmlero.fmprimitives.FeatureHasSubfeature;
import fr.inria.familiar.fmlero.fmprimitives.FeatureIsRoot;
import fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage;
import fr.inria.familiar.fmlero.fmprimitives.GroupHasChild;
import fr.inria.familiar.fmlero.fmprimitives.GroupHasParent;
import fr.inria.familiar.fmlero.fmprimitives.SelectedFeature;
import fr.inria.familiar.fmlero.fmprimitives.UndirectedRelationship;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureImpl#getId <em>Id</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureImpl#getName <em>Name</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureImpl#getGroupHasParent <em>Group Has Parent</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureImpl#getGroupHasChild <em>Group Has Child</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureImpl#getFeatureHasParent <em>Feature Has Parent</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureImpl#getFeatureHasSubfeature <em>Feature Has Subfeature</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureImpl#getSelectedFeature <em>Selected Feature</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureImpl#getEliminatedFeature <em>Eliminated Feature</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureImpl#getUndirectedRelationships <em>Undirected Relationships</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureImpl#getIncomingDirectedRelationships <em>Incoming Directed Relationships</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureImpl#getOutgoingDirectedRelationships <em>Outgoing Directed Relationships</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureImpl#getFeatureIsRoot <em>Feature Is Root</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureImpl extends MinimalEObjectImpl.Container implements Feature
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
   * The cached value of the '{@link #getGroupHasParent() <em>Group Has Parent</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGroupHasParent()
   * @generated
   * @ordered
   */
  protected EList<GroupHasParent> groupHasParent;

  /**
   * The cached value of the '{@link #getGroupHasChild() <em>Group Has Child</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGroupHasChild()
   * @generated
   * @ordered
   */
  protected EList<GroupHasChild> groupHasChild;

  /**
   * The cached value of the '{@link #getFeatureHasParent() <em>Feature Has Parent</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeatureHasParent()
   * @generated
   * @ordered
   */
  protected FeatureHasSubfeature featureHasParent;

  /**
   * The cached value of the '{@link #getFeatureHasSubfeature() <em>Feature Has Subfeature</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeatureHasSubfeature()
   * @generated
   * @ordered
   */
  protected EList<FeatureHasSubfeature> featureHasSubfeature;

  /**
   * The cached value of the '{@link #getSelectedFeature() <em>Selected Feature</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSelectedFeature()
   * @generated
   * @ordered
   */
  protected EList<SelectedFeature> selectedFeature;

  /**
   * The cached value of the '{@link #getEliminatedFeature() <em>Eliminated Feature</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEliminatedFeature()
   * @generated
   * @ordered
   */
  protected EList<EliminatedFeature> eliminatedFeature;

  /**
   * The cached value of the '{@link #getUndirectedRelationships() <em>Undirected Relationships</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUndirectedRelationships()
   * @generated
   * @ordered
   */
  protected EList<UndirectedRelationship> undirectedRelationships;

  /**
   * The cached value of the '{@link #getIncomingDirectedRelationships() <em>Incoming Directed Relationships</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIncomingDirectedRelationships()
   * @generated
   * @ordered
   */
  protected EList<DirectedRelationship> incomingDirectedRelationships;

  /**
   * The cached value of the '{@link #getOutgoingDirectedRelationships() <em>Outgoing Directed Relationships</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOutgoingDirectedRelationships()
   * @generated
   * @ordered
   */
  protected EList<DirectedRelationship> outgoingDirectedRelationships;

  /**
   * The cached value of the '{@link #getFeatureIsRoot() <em>Feature Is Root</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeatureIsRoot()
   * @generated
   * @ordered
   */
  protected FeatureIsRoot featureIsRoot;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FeatureImpl()
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
    return FmprimitivesPackage.Literals.FEATURE;
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
      eNotify(new ENotificationImpl(this, Notification.SET, FmprimitivesPackage.FEATURE__ID, oldId, id));
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
      eNotify(new ENotificationImpl(this, Notification.SET, FmprimitivesPackage.FEATURE__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<GroupHasParent> getGroupHasParent()
  {
    if (groupHasParent == null)
    {
      groupHasParent = new EObjectResolvingEList<GroupHasParent>(GroupHasParent.class, this, FmprimitivesPackage.FEATURE__GROUP_HAS_PARENT);
    }
    return groupHasParent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<GroupHasChild> getGroupHasChild()
  {
    if (groupHasChild == null)
    {
      groupHasChild = new EObjectResolvingEList<GroupHasChild>(GroupHasChild.class, this, FmprimitivesPackage.FEATURE__GROUP_HAS_CHILD);
    }
    return groupHasChild;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureHasSubfeature getFeatureHasParent()
  {
    if (featureHasParent != null && featureHasParent.eIsProxy())
    {
      InternalEObject oldFeatureHasParent = (InternalEObject)featureHasParent;
      featureHasParent = (FeatureHasSubfeature)eResolveProxy(oldFeatureHasParent);
      if (featureHasParent != oldFeatureHasParent)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FmprimitivesPackage.FEATURE__FEATURE_HAS_PARENT, oldFeatureHasParent, featureHasParent));
      }
    }
    return featureHasParent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureHasSubfeature basicGetFeatureHasParent()
  {
    return featureHasParent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFeatureHasParent(FeatureHasSubfeature newFeatureHasParent)
  {
    FeatureHasSubfeature oldFeatureHasParent = featureHasParent;
    featureHasParent = newFeatureHasParent;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FmprimitivesPackage.FEATURE__FEATURE_HAS_PARENT, oldFeatureHasParent, featureHasParent));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<FeatureHasSubfeature> getFeatureHasSubfeature()
  {
    if (featureHasSubfeature == null)
    {
      featureHasSubfeature = new EObjectResolvingEList<FeatureHasSubfeature>(FeatureHasSubfeature.class, this, FmprimitivesPackage.FEATURE__FEATURE_HAS_SUBFEATURE);
    }
    return featureHasSubfeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<SelectedFeature> getSelectedFeature()
  {
    if (selectedFeature == null)
    {
      selectedFeature = new EObjectResolvingEList<SelectedFeature>(SelectedFeature.class, this, FmprimitivesPackage.FEATURE__SELECTED_FEATURE);
    }
    return selectedFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EliminatedFeature> getEliminatedFeature()
  {
    if (eliminatedFeature == null)
    {
      eliminatedFeature = new EObjectResolvingEList<EliminatedFeature>(EliminatedFeature.class, this, FmprimitivesPackage.FEATURE__ELIMINATED_FEATURE);
    }
    return eliminatedFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<UndirectedRelationship> getUndirectedRelationships()
  {
    if (undirectedRelationships == null)
    {
      undirectedRelationships = new EObjectResolvingEList<UndirectedRelationship>(UndirectedRelationship.class, this, FmprimitivesPackage.FEATURE__UNDIRECTED_RELATIONSHIPS);
    }
    return undirectedRelationships;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<DirectedRelationship> getIncomingDirectedRelationships()
  {
    if (incomingDirectedRelationships == null)
    {
      incomingDirectedRelationships = new EObjectResolvingEList<DirectedRelationship>(DirectedRelationship.class, this, FmprimitivesPackage.FEATURE__INCOMING_DIRECTED_RELATIONSHIPS);
    }
    return incomingDirectedRelationships;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<DirectedRelationship> getOutgoingDirectedRelationships()
  {
    if (outgoingDirectedRelationships == null)
    {
      outgoingDirectedRelationships = new EObjectResolvingEList<DirectedRelationship>(DirectedRelationship.class, this, FmprimitivesPackage.FEATURE__OUTGOING_DIRECTED_RELATIONSHIPS);
    }
    return outgoingDirectedRelationships;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureIsRoot getFeatureIsRoot()
  {
    if (featureIsRoot != null && featureIsRoot.eIsProxy())
    {
      InternalEObject oldFeatureIsRoot = (InternalEObject)featureIsRoot;
      featureIsRoot = (FeatureIsRoot)eResolveProxy(oldFeatureIsRoot);
      if (featureIsRoot != oldFeatureIsRoot)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FmprimitivesPackage.FEATURE__FEATURE_IS_ROOT, oldFeatureIsRoot, featureIsRoot));
      }
    }
    return featureIsRoot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureIsRoot basicGetFeatureIsRoot()
  {
    return featureIsRoot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFeatureIsRoot(FeatureIsRoot newFeatureIsRoot)
  {
    FeatureIsRoot oldFeatureIsRoot = featureIsRoot;
    featureIsRoot = newFeatureIsRoot;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FmprimitivesPackage.FEATURE__FEATURE_IS_ROOT, oldFeatureIsRoot, featureIsRoot));
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
      case FmprimitivesPackage.FEATURE__ID:
        return getId();
      case FmprimitivesPackage.FEATURE__NAME:
        return getName();
      case FmprimitivesPackage.FEATURE__GROUP_HAS_PARENT:
        return getGroupHasParent();
      case FmprimitivesPackage.FEATURE__GROUP_HAS_CHILD:
        return getGroupHasChild();
      case FmprimitivesPackage.FEATURE__FEATURE_HAS_PARENT:
        if (resolve) return getFeatureHasParent();
        return basicGetFeatureHasParent();
      case FmprimitivesPackage.FEATURE__FEATURE_HAS_SUBFEATURE:
        return getFeatureHasSubfeature();
      case FmprimitivesPackage.FEATURE__SELECTED_FEATURE:
        return getSelectedFeature();
      case FmprimitivesPackage.FEATURE__ELIMINATED_FEATURE:
        return getEliminatedFeature();
      case FmprimitivesPackage.FEATURE__UNDIRECTED_RELATIONSHIPS:
        return getUndirectedRelationships();
      case FmprimitivesPackage.FEATURE__INCOMING_DIRECTED_RELATIONSHIPS:
        return getIncomingDirectedRelationships();
      case FmprimitivesPackage.FEATURE__OUTGOING_DIRECTED_RELATIONSHIPS:
        return getOutgoingDirectedRelationships();
      case FmprimitivesPackage.FEATURE__FEATURE_IS_ROOT:
        if (resolve) return getFeatureIsRoot();
        return basicGetFeatureIsRoot();
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
      case FmprimitivesPackage.FEATURE__ID:
        setId((String)newValue);
        return;
      case FmprimitivesPackage.FEATURE__NAME:
        setName((String)newValue);
        return;
      case FmprimitivesPackage.FEATURE__GROUP_HAS_PARENT:
        getGroupHasParent().clear();
        getGroupHasParent().addAll((Collection<? extends GroupHasParent>)newValue);
        return;
      case FmprimitivesPackage.FEATURE__GROUP_HAS_CHILD:
        getGroupHasChild().clear();
        getGroupHasChild().addAll((Collection<? extends GroupHasChild>)newValue);
        return;
      case FmprimitivesPackage.FEATURE__FEATURE_HAS_PARENT:
        setFeatureHasParent((FeatureHasSubfeature)newValue);
        return;
      case FmprimitivesPackage.FEATURE__FEATURE_HAS_SUBFEATURE:
        getFeatureHasSubfeature().clear();
        getFeatureHasSubfeature().addAll((Collection<? extends FeatureHasSubfeature>)newValue);
        return;
      case FmprimitivesPackage.FEATURE__SELECTED_FEATURE:
        getSelectedFeature().clear();
        getSelectedFeature().addAll((Collection<? extends SelectedFeature>)newValue);
        return;
      case FmprimitivesPackage.FEATURE__ELIMINATED_FEATURE:
        getEliminatedFeature().clear();
        getEliminatedFeature().addAll((Collection<? extends EliminatedFeature>)newValue);
        return;
      case FmprimitivesPackage.FEATURE__UNDIRECTED_RELATIONSHIPS:
        getUndirectedRelationships().clear();
        getUndirectedRelationships().addAll((Collection<? extends UndirectedRelationship>)newValue);
        return;
      case FmprimitivesPackage.FEATURE__INCOMING_DIRECTED_RELATIONSHIPS:
        getIncomingDirectedRelationships().clear();
        getIncomingDirectedRelationships().addAll((Collection<? extends DirectedRelationship>)newValue);
        return;
      case FmprimitivesPackage.FEATURE__OUTGOING_DIRECTED_RELATIONSHIPS:
        getOutgoingDirectedRelationships().clear();
        getOutgoingDirectedRelationships().addAll((Collection<? extends DirectedRelationship>)newValue);
        return;
      case FmprimitivesPackage.FEATURE__FEATURE_IS_ROOT:
        setFeatureIsRoot((FeatureIsRoot)newValue);
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
      case FmprimitivesPackage.FEATURE__ID:
        setId(ID_EDEFAULT);
        return;
      case FmprimitivesPackage.FEATURE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case FmprimitivesPackage.FEATURE__GROUP_HAS_PARENT:
        getGroupHasParent().clear();
        return;
      case FmprimitivesPackage.FEATURE__GROUP_HAS_CHILD:
        getGroupHasChild().clear();
        return;
      case FmprimitivesPackage.FEATURE__FEATURE_HAS_PARENT:
        setFeatureHasParent((FeatureHasSubfeature)null);
        return;
      case FmprimitivesPackage.FEATURE__FEATURE_HAS_SUBFEATURE:
        getFeatureHasSubfeature().clear();
        return;
      case FmprimitivesPackage.FEATURE__SELECTED_FEATURE:
        getSelectedFeature().clear();
        return;
      case FmprimitivesPackage.FEATURE__ELIMINATED_FEATURE:
        getEliminatedFeature().clear();
        return;
      case FmprimitivesPackage.FEATURE__UNDIRECTED_RELATIONSHIPS:
        getUndirectedRelationships().clear();
        return;
      case FmprimitivesPackage.FEATURE__INCOMING_DIRECTED_RELATIONSHIPS:
        getIncomingDirectedRelationships().clear();
        return;
      case FmprimitivesPackage.FEATURE__OUTGOING_DIRECTED_RELATIONSHIPS:
        getOutgoingDirectedRelationships().clear();
        return;
      case FmprimitivesPackage.FEATURE__FEATURE_IS_ROOT:
        setFeatureIsRoot((FeatureIsRoot)null);
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
      case FmprimitivesPackage.FEATURE__ID:
        return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
      case FmprimitivesPackage.FEATURE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case FmprimitivesPackage.FEATURE__GROUP_HAS_PARENT:
        return groupHasParent != null && !groupHasParent.isEmpty();
      case FmprimitivesPackage.FEATURE__GROUP_HAS_CHILD:
        return groupHasChild != null && !groupHasChild.isEmpty();
      case FmprimitivesPackage.FEATURE__FEATURE_HAS_PARENT:
        return featureHasParent != null;
      case FmprimitivesPackage.FEATURE__FEATURE_HAS_SUBFEATURE:
        return featureHasSubfeature != null && !featureHasSubfeature.isEmpty();
      case FmprimitivesPackage.FEATURE__SELECTED_FEATURE:
        return selectedFeature != null && !selectedFeature.isEmpty();
      case FmprimitivesPackage.FEATURE__ELIMINATED_FEATURE:
        return eliminatedFeature != null && !eliminatedFeature.isEmpty();
      case FmprimitivesPackage.FEATURE__UNDIRECTED_RELATIONSHIPS:
        return undirectedRelationships != null && !undirectedRelationships.isEmpty();
      case FmprimitivesPackage.FEATURE__INCOMING_DIRECTED_RELATIONSHIPS:
        return incomingDirectedRelationships != null && !incomingDirectedRelationships.isEmpty();
      case FmprimitivesPackage.FEATURE__OUTGOING_DIRECTED_RELATIONSHIPS:
        return outgoingDirectedRelationships != null && !outgoingDirectedRelationships.isEmpty();
      case FmprimitivesPackage.FEATURE__FEATURE_IS_ROOT:
        return featureIsRoot != null;
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
    result.append(')');
    return result.toString();
  }

} //FeatureImpl
