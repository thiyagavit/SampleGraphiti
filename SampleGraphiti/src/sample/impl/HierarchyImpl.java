/**
 */
package sample.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import sample.Hierarchy;
import sample.Person;
import sample.SamplePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Hierarchy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link sample.impl.HierarchyImpl#getManager <em>Manager</em>}</li>
 *   <li>{@link sample.impl.HierarchyImpl#getReportee <em>Reportee</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HierarchyImpl extends EObjectImpl implements Hierarchy {
	/**
	 * The cached value of the '{@link #getManager() <em>Manager</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManager()
	 * @generated
	 * @ordered
	 */
	protected Person manager;

	/**
	 * The cached value of the '{@link #getReportee() <em>Reportee</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReportee()
	 * @generated
	 * @ordered
	 */
	protected Person reportee;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HierarchyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SamplePackage.Literals.HIERARCHY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Person getManager() {
		if (manager != null && manager.eIsProxy()) {
			InternalEObject oldManager = (InternalEObject)manager;
			manager = (Person)eResolveProxy(oldManager);
			if (manager != oldManager) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SamplePackage.HIERARCHY__MANAGER, oldManager, manager));
			}
		}
		return manager;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Person basicGetManager() {
		return manager;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setManager(Person newManager) {
		Person oldManager = manager;
		manager = newManager;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SamplePackage.HIERARCHY__MANAGER, oldManager, manager));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Person getReportee() {
		if (reportee != null && reportee.eIsProxy()) {
			InternalEObject oldReportee = (InternalEObject)reportee;
			reportee = (Person)eResolveProxy(oldReportee);
			if (reportee != oldReportee) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SamplePackage.HIERARCHY__REPORTEE, oldReportee, reportee));
			}
		}
		return reportee;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Person basicGetReportee() {
		return reportee;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReportee(Person newReportee) {
		Person oldReportee = reportee;
		reportee = newReportee;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SamplePackage.HIERARCHY__REPORTEE, oldReportee, reportee));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SamplePackage.HIERARCHY__MANAGER:
				if (resolve) return getManager();
				return basicGetManager();
			case SamplePackage.HIERARCHY__REPORTEE:
				if (resolve) return getReportee();
				return basicGetReportee();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SamplePackage.HIERARCHY__MANAGER:
				setManager((Person)newValue);
				return;
			case SamplePackage.HIERARCHY__REPORTEE:
				setReportee((Person)newValue);
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
	public void eUnset(int featureID) {
		switch (featureID) {
			case SamplePackage.HIERARCHY__MANAGER:
				setManager((Person)null);
				return;
			case SamplePackage.HIERARCHY__REPORTEE:
				setReportee((Person)null);
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
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SamplePackage.HIERARCHY__MANAGER:
				return manager != null;
			case SamplePackage.HIERARCHY__REPORTEE:
				return reportee != null;
		}
		return super.eIsSet(featureID);
	}

} //HierarchyImpl
