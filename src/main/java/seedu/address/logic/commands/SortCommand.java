package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attribute.AutoCorrectionUtil;
import seedu.address.model.person.AttributeBasedPersonComparator;

/**
 * Represents a sort command that sorts the list of persons based on a specified attribute comparator.
 * Subclasses should provide the appropriate comparator and any relevant warning message.
 */
public abstract class SortCommand extends Command {
    private static final String MESSAGE_WARNING_MISSING_ATTRIBUTE =
            "WARNING! Only entries up to index %1$d have the specified attribute name.\n";

    protected final String attributeName;
    protected Optional<String> adjustedAttributeName;
    protected boolean hasNothingToSort;
    private final boolean isAscending;

    /**
     * Initializes an instance with the comparator based on the attribute name.
     *
     * @param attributeName The name of the attribute to sort the user input.
     */
    public SortCommand(String attributeName, boolean isAscending) {
        requireNonNull(attributeName);
        this.attributeName = attributeName;
        this.isAscending = isAscending;
        this.hasNothingToSort = false;
    }

    /**
     * Returns the Comparator to use for this sort command, assuming ascending order
     */
    public abstract AttributeBasedPersonComparator getComparator(boolean isDescending);

    /**
     * Returns the warning message produced by this sort command.
     * If there is no warning, an empty String is returned.
     */
    public String getWarningMessage(Model model) {
        Optional<String> missingAttributeWarning =
            AutoCorrectionUtil.getWarningForName(attributeName, adjustedAttributeName);
        String message = "";
        if (missingAttributeWarning.isPresent()) {
            message += missingAttributeWarning.get() + "\n";
        }
        if (adjustedAttributeName.isEmpty()) {
            hasNothingToSort = true;
            return message;
        }
        Optional<Long> count = model.numOfPersonsWithAttribute(this.adjustedAttributeName.orElse(attributeName));
        return message + count.map(val -> String.format(MESSAGE_WARNING_MISSING_ATTRIBUTE, val)).orElse("");
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        this.adjustedAttributeName = model.autocorrectAttributeName(this.attributeName);
        model.sortFilteredPersonList(this.getComparator(this.isAscending));
        String message = this.getWarningMessage(model);
        if (hasNothingToSort) {
            return new CommandResult(message);
        }
        if (this.isAscending) {
            message += String.format(Messages.MESSAGE_PERSONS_SORTED_OVERVIEW, "ascending");
        } else {
            message += String.format(Messages.MESSAGE_PERSONS_SORTED_OVERVIEW, "descending");
        }
        return new CommandResult(message);
    }

    @Override
    public boolean equals(Object other) {
        if (this.getClass() != other.getClass()) {
            return false;
        } else {
            SortCommand otherCommand = (SortCommand) other;
            return this.attributeName.equals(otherCommand.attributeName)
                    && this.isAscending == otherCommand.isAscending;
        }
    }
}
