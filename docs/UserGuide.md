---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# TalentFolio User Guide

TalentFolio is an application for hiring managers to manage information on job candidates. It is optimized for users who prefer a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, TalentFolio lets you handle your recruitment tasks more efficiently compared to traditional GUI applications!

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

1. Download the latest `.jar` file from [here](https://github.com/AY2425S2-CS2103T-T10-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for TalentFolio.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar talentfolio.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all candidates.

   * `add n/John Doe p/98765432 e/johnd@example.com` : Adds a candidate named `John Doe` to the database.

   * `filter a/Major=Computer Science` : Filters all candidates who major in Computer Science.

   * `sort a/Graduation Year` : Sorts candidates in ascending order of Graduation Year.

   * `delete 3` : Deletes the 3rd candidate shown in the current list.

   * `clear` : Deletes all candidates.

   * `undo` : Undoes the last command.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/Excel` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/Excel`, `t/C++ t/Java` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Showing a person's details: `show`

Shows the specified person's details in the right panel.

Format: `show INDEX`

* You can also view a person's details by clicking on them in the left panel.

Example:
* `show 1` shows the details of the 1st person.<br>
  ![result for 'show 1'](images/showAlexYeohResult.png)

### Adding a person: `add`

Adds a person to the database.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]…​ [a/ATTRIBUTE_NAME=ATTRIBUTE_VALUE]…​`

<box type="tip" seamless>

**Tip:** A person can have any number of tags, and any number of attributes (including 0)
</box>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com`
* `add n/Betsy Crowe t/C++ e/betsycrowe@example.com p/1234567 t/Java a/Major=Data Science`

### Listing all persons: `list`

Shows a list of all persons in the database.

Format: `list`

### Editing a person: `edit`

Edits an existing person in the database.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [t/TAG]… [a/ATTRIBUTE_NAME=ATTRIBUTE_VALUE]… [ra/ATTRIBUTE_NAME]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

For tags:
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without specifying any tags after it.

For attributes:
* Update of attributes must specify both the attribute name and attribute value.
* Update of attributes will update an existing attribute if it exists, else it will add it as a new attribute.
* Removal of attributes only requires specifying the attribute name.
* Regardless of the order that update attribute and remove attribute parameters are provided in, all attribute updates will be processed and executed first, before attribute removal.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
*  `edit 1 a/Graduation Year=2027` Adds or edits an attribute named "Graduation Year" to the 1st person.
*  `edit 1 ra/Graduation Year` Removes the attribute named "Graduation Year" from the 1st person.

### Linking an attribute name to a site URL: `link`

Adds a mapping between an attribute name and a site URL. By doing so, you can visit the website by simply clicking on the attribute in the individual view of the candidate, which can be shown by clicking on them or by entering the `show` command.

Format: `link a/ATTRIBUTE_NAME=SITE_LINK`

* Adds a mapping between the `ATTRIBUTE_NAME` (attribute name) and the `SITE_LINK` (site URL). This change applies to all candidates with an attribute that has the given name.
* The attribute name is treated case-insensitively.
* To remove an existing mapping, leave the `SITE_LINK` empty.

Examples:
* `link a/github=https://github.com/`
  * You can now visit the `https://github.com/ATTRIBUTE_VALUE` (i.e., the username) by clicking on the attribute.
  * For example, if a candidate has an attribute with the name `GitHub` and the value (i.e., usename) `pochitaro2025`, you will be redirected to `https://github.com/pochitaro2025`! Note the case-insensitivity.
* `link a/github=`
  * Conversely, this will delete the mapping between `github` and `https://github.com/`.
  * You will no longer be redirected to the GitHub page from now on.

<box type="tip" seamless>

**Tip:** Mappings for `github` and `linkedin` (`https://github.com/` and `https://www.linkedin.com/in/`) are added by default!

Enjoy the easy management of websites!!
</box>

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person: `delete`

Deletes the specified person from the database.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the database.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries: `clear`

Clears all entries from the database.

Format: `clear`

### Filtering candidates by attributes: `filter`

Filters the candidates based on whether they have a specific attribute or not.

Format: `filter [a/ATTRIBUTE_NAME=ATTRIBUTE_VALUE]…`

* You must specify at least one attribute.
* You can specify more than one attribute:
  1. If you specify multiple attributes of the **SAME** name, candidates who meet **ANY** one of them will be shown.
  2. If you specify multiple attributes of **DIFFERENT** names, candidates who meet **ALL** of them will be shown.
  3. You can specify attributes of different names, with multiple values of each. In this case, the first rule will be applied first, followed by the second rule (see Examples).
  4. The order of the given attributes does not matter (see Examples).
* `ATTRIBUTE_NAME` is matched case-insensitively, while `ATTRIBUTE_VALUE` is matched case-sensitively.
* The input names and values are tolerant of typos–the app automatically corrects them.
  * For example, `GraduatOIn year` will be corrected to `GraduatIOn year` automatically, if only `Graduation year` exists as an attribute name.

Examples:
* `filter a/Major=Computer Science a/Graduation year=2028` filters all the candidates who major in Computer Science **AND** will graduate in 2028.
* `filter a/Major=Computer Science a/Major=Mathematics` filters all the candidates who major in Computer Science **OR** Mathematics.
* `filter a/Major=Computer Science a/Major=Mathematics a/Graduation year=2028` filters all the candidates who major in either Computer Science **OR** Mathematics, **AND** also graduating in 2028. That is:
  * A candidate majoring in Compute Science and graduating in 2028 will be matched.
  * A candidate majoring in Mathematics and graduating in 2028 will be matched.
  * A candidate majoring in Engineering and graduating in 2028 will **NOT** be matched, since they do not meet the first condition.
  * A candidate majoring in Compute Science and graduating in 2027 will **NOT** be matched, since they do not meet the second condition.
  * Please note that, in this case, candidates who are missing any of the attributes (i.e., do not have an attribute with the name Major or Graduation year) will **NOT** be matched. For instance, a candidate with no attributes will not be matched.
  * You can also obtain the same result with `filter a/Major=Computer Science a/Graduation year=2028 a/Major=Mathematics`, because the order of the arguments does not matter.

### Sorting entries by an attribute: `sort`

Sorts the current view of entries by the value of the specified attribute name in lexicographically ascending order.

Format: `sort a/ATTRIBUTE_NAME`

*  Entries without the specified attribute will be placed at the back while preserving their internal order prior to the command.
* `ATTRIBUTE_NAME` is matched case-insensitively. For instance, a command `sort a/graduation year` can sort all entries that have an attribute with name `Graduation Year`.
* The input names and values are tolerant of typos–the app automatically corrects them.

Example:
* `sort a/major` sorts all entries with the "Major" attribute by lexicographical order of the attribute value of "Major".

### Sorting entries by the numerical value of an attribute: `sort-num`

Sorts the current view of entries by the numeric value of the specified attribute name in ascending order.

Format: `sort-num a/ATTRIBUTE_NAME`

* For each attribute value which can be parsed into a number, its numerical value will be stored.
* Entries without the specified attribute will be placed at the back while preserving their internal order prior to the command.
* Among entries with the specified attribute name, those without a valid numerical value is placed at the back without altering their internal order.
* If not all entries have a valid numerical value for the specified attribute name, a warning will be displayed. 
* `ATTRIBUTE_NAME` is matched case-insensitively. For instance, a command `sort a/graduation year` can sort all entries that have an attribute with name `Graduation Year`.
* The input names and values are tolerant of typos–the app automatically corrects them.

Example:
* `sort-num a/GPA` sorts all entries with the "GPA" attribute by ascending order of the numerical attribute value of "GPA".

### Undoing the last data change: `undo`

Undoes the last data change. For instance, if you deleted the wrong entry accidentally, you can use this command to revert the data.

Format: `undo`
* Only changes since the app was opened can be undone.
* Changes that are undone cannot be redone.
* `undo` can be used multiple times in succession to undo more changes.
* Does not change any applied filters. If a filter was accidentally applied, you can use `list` to clear any existing filters.
* Does not work after exiting and re-opening the app. Using `undo` on a freshly opened app will not change anything.

### Navigating past commands: `↑` `↓`

Navigates through command history, replacing the text in the command box with the past executed command.

Format: Press the up arrow (`↑`) or down arrow (`↓`) key while the command box is selected.
* The up arrow (`↑`) key shows the previous executed command while the down arrow (`↓`) key shows the next executed command.
* `ENTER` is not required to be pressed for navigation.
* No error message will be shown if attempting to navigate beyond the first and last executed commands.
* If attempting to navigate beyond the first executed command, the first executed command will remain shown.
* If attempting to navigate beyond the last executed command, the command box will be emptied.
* Executing any valid command will reset the last executed command to the command that was just executed.
* Editing the command shown without execution will not change the previous and next executed commands.
* After editing the command shown, navigating to previous or next executed commands will discard any edits done (these edits will not be maintained when navigating back).

### Exiting the program: `exit`

Exits the program.

Format: `exit`

### Saving the data

TalentFolio data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TalentFolio data is saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, TalentFolio will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause TalentFolio to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TalentFolio home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]… [a/ATTRIBUTE_NAME=ATTRIBUTE_VALUE]…` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com t/C++ t/Java a/Major=Data Science`
**Clear**  | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [t/TAG]… [a/ATTRIBUTE_NAME=ATTRIBUTE_VALUE]… [ra/ATTRIBUTE_NAME]…` <br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Link**   | `link a/ATTRIBUTE_NAME=SITE_LINK`
**Exit**   | `exit`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List**   | `list`
**Sort** | `sort a/ATTRIBUTE_NAME`<br> e.g., `sort a/Degree`
**Numerical Sort** | `sort-num a/ATTRIBUTE_NAME`<br> e.g., `sort-num a/Expected Salary`
**Show**   | `show INDEX` <br> e.g., `show 1`
**Filter** | `filter [a/ATTRIBUTE_NAME=ATTRIBUTE_VALUE]…` <br> e.g., `filter a/Major=Computer Science`
**Undo**   | `undo`
**Help**   | `help`
