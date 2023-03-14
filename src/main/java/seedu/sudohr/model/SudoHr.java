package seedu.sudohr.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.department.DepartmentName;
import seedu.sudohr.model.department.UniqueDepartmentList;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.UniqueEmployeeList;

/**
 * Wraps all data at the sudohr-book level
 * Duplicates are not allowed (by .isSameEmployee comparison)
 */
public class SudoHr implements ReadOnlySudoHr {

    private final UniqueEmployeeList employees;
    private final UniqueDepartmentList departments;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        employees = new UniqueEmployeeList();
        departments = new UniqueDepartmentList();
    }

    public SudoHr() {}

    /**
     * Creates an SudoHr using the Employees in the {@code toBeCopied}
     */
    public SudoHr(ReadOnlySudoHr toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the employee list with {@code employees}.
     * {@code employees} must not contain duplicate employees.
     */
    public void setEmployees(List<Employee> employees) {
        this.employees.setEmployees(employees);
    }

    /**
     * Replaces the contents of the department list with {@code departments}.
     * {@code departments} must not contain duplicate departments.
     */
    public void setDepartments(List<Department> departments) {
        this.departments.setDepartments(departments);
    }

    /**
     * Resets the existing data of this {@code SudoHR} with {@code newData}.
     */
    public void resetData(ReadOnlySudoHr newData) {
        requireNonNull(newData);

        setEmployees(newData.getEmployeeList());
        setDepartments(newData.getDepartmentList());
    }

    //=========== Employee-Level Operations ============================

    /**
     * Returns true if an employee with the same identity as {@code employee} exists in SudoHR.
     */
    public boolean hasEmployee(Employee employee) {
        requireNonNull(employee);
        return employees.contains(employee);
    }

    /**
     * Returns true if an employee shares the same email with a different {@code employee} (different id).
     */
    boolean hasClashingEmail(Employee employee) {
        requireNonNull(employee);
        return employees.sharesEmail(employee);
    }

    /**
     * Returns true if an employee shares the same phone number with a different {@code employee} (different id).
     */
    boolean hasClashingPhoneNumber(Employee employee) {
        requireNonNull(employee);
        return employees.sharesPhoneNumber(employee);
    }

    /**
     * Adds an employee to SudoHR.
     * The employee must not already exist in SudoHR and
     * should not have any clashes with email or phone number fields
     */
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    /**
     * Replaces the given employee {@code target} in the list with {@code editedEmployee}.
     * {@code target} must exist in SudoHR.
     * The employee identity of {@code editedEmployee} must not be the same as
     * another existing employee in SudoHR.
     */
    public void setEmployee(Employee target, Employee editedEmployee) {
        requireNonNull(editedEmployee);

        employees.setEmployee(target, editedEmployee);
    }

    /**
     * Removes {@code key} from this {@code SudoHr}.
     * {@code key} must exist in SudoHR.
     */
    public void removeEmployee(Employee key) {
        employees.remove(key);
    }

    //=========== Department-Level Operations ==========================================================================

    /**
     * Returns true if a department with the same identity as {@code department} exists in SudoHR.
     */
    public boolean hasDepartment(Department department) {
        requireNonNull(department);
        return departments.contains(department);
    }

    /**
     * Returns the department with the given name.
     * @param name The department name to find.
     * @return The corresponding department.
     */
    public Department getDepartment(DepartmentName name) {
        return departments.getDepartment(name);
    }

    /**
     * Adds a department to the address book.
     * The department must not already exist in the address book.
     */
    public void addDepartment(Department d) {
        departments.add(d);
    }

    /**
     * Replaces the given department {@code target} in the list with {@code editedDepartment}.
     * {@code editedDepartment} must exist in SudoHR.
     */
    public void setDepartment(Department target, Department editedDepartment) {
        requireNonNull(editedDepartment);
        departments.setDepartment(target, editedDepartment);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeDepartment(Department key) {
        departments.remove(key);
    }

    /**
     * Adds a given employee from a given department
     * @param p The employee to add
     * @param d The department to add the employee to
     */
    public void addEmployeeToDepartment(Employee p, Department d) {
        requireNonNull(p);
        requireNonNull(d);

        d.addEmployee(p);
    }

    /**
     * Removes a given employee from a given department
     * @param p The employee to remove
     * @param d The department to remove the employee fro
     */
    public void removeEmployeeFromDepartment(Employee p, Department d) {
        requireNonNull(p);
        requireNonNull(d);

        d.removeEmployee(p);
    }

    //// event-level operations

    //// leave-level operations

    //// util methods

    @Override
    public String toString() {
        return employees.asUnmodifiableObservableList().size() + " employees";
        // TODO: refine later
    }

    @Override
    public ObservableList<Employee> getEmployeeList() {
        return employees.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Department> getDepartmentList() {
        return departments.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SudoHr // instanceof handles nulls
                && employees.equals(((SudoHr) other).employees));
    }

    @Override
    public int hashCode() {
        return employees.hashCode();
    }
}
