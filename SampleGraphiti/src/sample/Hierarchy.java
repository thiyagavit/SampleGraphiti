/**
 */
package sample;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Hierarchy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sample.Hierarchy#getManager <em>Manager</em>}</li>
 *   <li>{@link sample.Hierarchy#getReportee <em>Reportee</em>}</li>
 * </ul>
 * </p>
 *
 * @see sample.SamplePackage#getHierarchy()
 * @model
 * @generated
 */
public interface Hierarchy extends EObject {
	/**
	 * Returns the value of the '<em><b>Manager</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Manager</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Manager</em>' reference.
	 * @see #setManager(Person)
	 * @see sample.SamplePackage#getHierarchy_Manager()
	 * @model required="true"
	 * @generated
	 */
	Person getManager();

	/**
	 * Sets the value of the '{@link sample.Hierarchy#getManager <em>Manager</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Manager</em>' reference.
	 * @see #getManager()
	 * @generated
	 */
	void setManager(Person value);

	/**
	 * Returns the value of the '<em><b>Reportee</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reportee</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reportee</em>' reference.
	 * @see #setReportee(Person)
	 * @see sample.SamplePackage#getHierarchy_Reportee()
	 * @model required="true"
	 * @generated
	 */
	Person getReportee();

	/**
	 * Sets the value of the '{@link sample.Hierarchy#getReportee <em>Reportee</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reportee</em>' reference.
	 * @see #getReportee()
	 * @generated
	 */
	void setReportee(Person value);

} // Hierarchy
