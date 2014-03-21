/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.calpoly.razsoftware;

import java.util.HashSet;
import java.util.List;

/**
 * Interface for flowchart
 * @author Gagandeep
 */
public interface I_Flowchart
{
    /**
     * Add another quarter at the specified position.
     * give the quarter a default name, and shift each
     * quarter from index forward to the right.
     *
     * @param index the position of the added quarter
     */
    void addQuarter(int index);

    /**
     * Build a flowchart from a Degree
     */
    void build();

    /**
     * If there is a saved flowchart, build it form the database
     *
     */
    void buildFromDatabase();

    /**
     * Build a set of courses prior to a given quarter.
     * Also, specify if the courses need to be completed or not.
     * For sequence checking, completion doesn't matter.
     * For eligibility checking, completion matters.
     * Having a boolean control flag is high coupling, better would be
     * a Strategy pattern.
     * @param col the column in the table representing the desired quarter
     * @param considerCompletion true if completion status should be taken into
     * consideration, otherwise false
     * @return set of contingent @see contingent
     */
    HashSet<Contingent> buildPriorCourses(int col, boolean considerCompletion);

    /**
     * Deletes the quarter at the given index.
     *
     * @param index the index of the quarter to delete
     */
    void deleteQuarter(int index);

    /**
     * Find the current quarter, i.e., the first quarter that contains
     * a not completed course.
     * @return the column of the current quarter in the table. (0-based)
     */
    int findCurrentQuarter();

    /**
     * Accessor to the type of data in a column of the board.
     * @param col column index
     * @return class of the column at col
     */
    Class getColumnClass(int col);

    /**
     * Accessor to the number of columns on the board.
     * Assumes all rows have the same number of columns.
     * @return total number of column
     */
    int getColumnCount();

    /**
     * gets the name of the column
     * @param column column index
     * @return name of the column
     */
    String getColumnName(int column);

    /**
     * gets list of requirement at the column index
     * @param col column index
     * @return list of requirement at the column index
     */
    List<I_Requirement> getList(int col);

    /**
     * gets total number of quarters
     * @return total number of quarters
     */
    int getNumQuarters();

    /**
     * Return a sequential list of all the requirements in this flowchart ready
     * to be serialized.
     * @return a list of requirements (not guaranteed to be in any order)
     */
    List<I_Requirement> getRequirements();

    /**
     * Accessor to the number of rows on the board.
     * @return total number of rows
     */
    int getRowCount();

    /**
     * Accessor to the cell in the chart located at row, col (zero based)
     * @param row row index
     * @param col column index
     * @return requirement at row col
     */
    I_Requirement getValueAt(int row, int col);

    /**
     * Checks to see if requirement is in sequence
     * @param req requirement to check if it is in sequence
     * @param col column index of where the requirement is in
     * @return true if the requirement is sequence, otherwise false
     */
    boolean inSequence(I_Requirement req, int col);

    /**
     * Is the student eligible to take this course
     * (Are all the prereqs satisfied?)
     * @param req the requirement to investigate
     * @param col the column in the table
     * @return true if student can now enroll in this course, false if not
     */
    boolean isEligible(I_Requirement req, int col);

    /**
     * Move a course to a new position in the table.
     * Checks for validity and returns -1 if no move is allowed.
     * @param fromRow row to move from
     * @param fromCol column to move from
     * @param destRow row to move to
     * @param destCol column to move to
     * @return -1 if no move is allowed.
     */
    int moveCourse(int fromRow, int fromCol, int destRow, int destCol);

    /**
     * Move a course to the end in the table.
     * Checks for validity and returns -1 if no move is allowed.
     * @param fromRow row to move from
     * @param fromCol column to move from
     * @param destCol column to move to
     * @return -1 if no move is allowed.
     */
    int moveCourseToColEnd(int fromRow, int fromCol, int destCol);

    /**
     * Rename the quarter at index to the name given
     *
     * @param index the index of the changed quarter
     * @param name The name to change qthe quarter to

     */
    void renameQuarter(int index, String name);

    /**
     * Mutator to the cell in the chart located at row, col (zero based) @pre
     * value should be
     * @param value cell to mutate
     * @param col column of the cell
     * @param row row of the cell
     */
    void setValueAt(Object value, int row, int col);

    /**
     * Return a printable representation of this flowchart. E.g.,
     * F07=14,16
     * W08=1,2,5,15,17
     * S08=3,4,8,18
     * @return a printable representation of this flowchart
     */
    String toString();

    /**
     * Calculates the number of units completed on the current flowchart.
     *
     * @return an int of the number of units
     */
    int unitsCompleted();

    /**
     * updates the requirement
     *
     * @param req requirement to update
     */
    void updateRequirement(I_Requirement req);

    /**
     * Gets names of all the quarters
     * @return names of all the quarters
     */
    String[] getQuarterNames();
}
