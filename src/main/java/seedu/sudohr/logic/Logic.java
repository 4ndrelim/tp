package seedu.sudohr.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.sudohr.commons.core.GuiSettings;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.logic.parser.exceptions.ParseException;
import seedu.sudohr.model.ReadOnlySudoHr;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.person.Person;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the SudoHr.
     *
     * @see seedu.sudohr.model.Model#getSudoHr()
     */
    ReadOnlySudoHr getSudoHr();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of departments */
    ObservableList<Department> getFilteredDepartmentList();

    /**
     * Returns the user prefs' sudohr book file path.
     */
    Path getSudoHrFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
