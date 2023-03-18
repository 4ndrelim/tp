package seedu.sudohr.logic.parser;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DEPARTMENT_NAME;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_ID;

import java.util.stream.Stream;

import seedu.sudohr.logic.commands.AddEmployeeToDepartmentCommand;
import seedu.sudohr.logic.parser.exceptions.ParseException;
import seedu.sudohr.model.department.DepartmentName;
import seedu.sudohr.model.employee.Id;

/**
 * Parses input arguments and creates a new AddEmployeeToDepartmentCommand object
 */
public class AddEmployeeToDepartmentCommandParser implements Parser<AddEmployeeToDepartmentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddEmployeeToDepartmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_DEPARTMENT_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_DEPARTMENT_NAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddEmployeeToDepartmentCommand.MESSAGE_USAGE));
        }

        Id id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        DepartmentName name = ParserUtil.parseDepartmentName(argMultimap.getValue(PREFIX_DEPARTMENT_NAME).get());

        return new AddEmployeeToDepartmentCommand(id, name);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
