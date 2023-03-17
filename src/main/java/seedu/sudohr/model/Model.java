package seedu.sudohr.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.sudohr.commons.core.GuiSettings;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.department.DepartmentName;
import seedu.sudohr.model.employee.Employee;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Employee> PREDICATE_SHOW_ALL_EMPLOYEES = unused -> true;

    Predicate<Department> PREDICATE_SHOW_ALL_DEPARTMENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' SudoHR file path.
     */
    Path getSudoHrFilePath();

    /**
     * Sets the user prefs' SudoHR file path.
     */
    void setSudoHrFilePath(Path sudoHrFilePath);

    /**
     * Replaces SudoHR data with the data in {@code sudoHr}.
     */
    void setSudoHr(ReadOnlySudoHr sudoHr);

    /** Returns the SudoHr */
    ReadOnlySudoHr getSudoHr();

    //=========== Employee-Level Operations ===========================================

    /**
     * Returns true if an employee with the same identity as {@code employee} exists in SudoHR.
     */
    boolean hasEmployee(Employee employee);

    /**
     * Returns true if an employee with the same identity as {@code employee} exists in SudoHR,
     * excluding the specified employee/
     */
    boolean hasEmployee(Employee employee, Employee excludeFromCheck);

    /**
     * Returns true if an employee shares the same email with a different {@code employee} (different id).
     */
    boolean hasClashingEmail(Employee employee);

    /**
     * Returns true if an employee shares the same email with a different {@code employee} (different id),
     * excluding the specified employee.
     */
    boolean hasClashingEmail(Employee employee, Employee excludeFromCheck);

    /**
     * Returns true if an employee shares the same phone number with a different {@code employee} (different id).
     */
    boolean hasClashingPhoneNumber(Employee employee);

    /**
     * Returns true if an employee shares the same phone number with a different {@code employee} (different id),
     * excluding the specified employee.
     */
    boolean hasClashingPhoneNumber(Employee employee, Employee excludeFromCheck);

    /**
     * Deletes the given employee.
     * The employee must exist in SudoHR.
     */
    void deleteEmployee(Employee target);

    /**
     * Adds the given employee.
     * {@code employee} must not already exist in the SoduHR.
     */
    void addEmployee(Employee employee);

    /**
     * Replaces the given employee {@code target} with {@code editedEmployee}.
     * {@code target} must exist in the SudoHR.
     * The employee identity of {@code editedEmployee} must not be the same as another existing
     * employee in SudoHR.
     */
    void setEmployee(Employee target, Employee editedEmployee);

    /** Returns an unmodifiable view of the filtered employee list */
    ObservableList<Employee> getFilteredEmployeeList();

    /**
     * Updates the filter of the filtered employee list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEmployeeList(Predicate<Employee> predicate);

    //=========== Department-Level Operations ==========================================================================

    public Department getDepartment(DepartmentName name);

    /**
     * Returns true if a department with the same identity as {@code department} exists in SudoHR.
     */
    public boolean hasDepartment(Department department);

    /**
     * Adds a department to SudoHR.
     * The department must not already exist in SudoHR.
     */
    void addDepartment(Department d);

    /**
     * Replaces the given department {@code target} in the list with {@code editedDepartment}.
     * {@code target} must exist in SudoHR.
     * The department identity of {@code editedDepartment} must not be the same as another existing
     * department in SudoHR.
     */
    void setDepartment(Department target, Department editedDepartment);

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in SudoHR.
     */
    void removeDepartment(Department key);

    /**
     * Adds a given employee from a given department
     * @param p The employee to add
     * @param d The department to add the employee to
     */
    void addEmployeeToDepartment(Employee p, Department d);

    /**
     * Removes a given employee from a given department
     * @param p The employee to remove
     * @param d The department to remove the employee fro
     */
    void removeEmployeeFromDepartment(Employee p, Department d);

    /** Returns an unmodifiable view of the filtered department list */
    ObservableList<Department> getFilteredDepartmentList();

    /**
     * Updates the filter of the filtered department list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredDepartmentList(Predicate<Department> predicate);
}
