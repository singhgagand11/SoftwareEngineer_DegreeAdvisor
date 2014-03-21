package edu.calpoly.razsoftware;

import java.io.FileReader;
import java.io.IOException;
import java.lang.String;
import java.lang.String;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Flowchart represents a student's chart of courses needed to complete their
 * degree. It is organized as a table where the columns are quarters and each
 * quarter contains a list of courses to be completed during that term.
 *
 * @author jdalbey
 * @author Jeremy Rabaino (jrabaino) - pseudocode
 */
public class Flowchart extends AbstractTableModel implements I_Flowchart
{
    /**
     * courses per quarter *
     */
    public final static int kMaxRows = 7;
    /**
     * limit for table *
     */
    public final static int kMaxQuarters = 99;
    //private final static int kNumQuarters = 20;  // default table width
    /**
     * default table width *
     */
    final static int kNumQuarters = 12;
    /**
     * number of semesters in a given school year *
     */
    public final static int kSemesters = 3;
    /**
     * Starting year for the flowchart i.e 2008 - 20012, stating year is 08 *
     */
    public final static int kBaseYear = 8;
    private List<List<I_Requirement>> chart;
    private String[] quarterNames = new String[kMaxQuarters];  // column names
    private I_Catalog catalog;
    private HashSet<Contingent> prior = new HashSet<Contingent>();

    /**
     * Construct a flow chart using a specified catalog of courses.
     *
     * @param cat catalog of courses offered
     */
    public Flowchart(I_Catalog cat)
    {
        this.catalog = cat;
        // Ask the database if there is a saved flowchart.
        // IF there is a previously saved flowchart THEN
        if (Database.loadMostRecent() != null)
        {
            // CALL the database to fetch the data for this flowchart
            buildFromDatabase();
        }
        // ELSE we need to build the chart from the initial dept recommendations
        else
        {
            build();
        }
    }

    /**
     * Construct a flow chart using a specified catalog of courses.
     *
     * @param cat catalog of courses offered
     * @param reqs list of requirements
     */
    public Flowchart(I_Catalog cat, List<I_Requirement> reqs)
    {
        this.catalog = cat;
        fillChart(reqs);
        fillNames();
    }

    /**
     * If there is a saved flowchart, build it form the database
     *
     */
    @Override
    public void buildFromDatabase()
    {
        // SAVE return from CALL to Database.loadMostRecent() AS mostRecent
        String mostRecent = Database.loadMostRecent();
        // SAVE return from CALL to loadChart() with mostRecent AS degreeChart
        List<I_Requirement> degreeChart = Database.loadChart(mostRecent, catalog);

        try
        {
            FileReader reader = new FileReader(mostRecent + ".quarters");
            quarterNames = Database.loadQuarters(reader);
            reader.close();
        }
        catch (IOException ex)
        {
            fillNames();
        }


        // CALL fillChart() with degreeChart
        fillChart(degreeChart);
    }

    /**
     * Build a flowchart from a Degree
     */
    @Override
    public void build()
    {
        List<I_Requirement> degreeChart =
                DegreechartReader.readFlowchart(
                getClass().getResourceAsStream("DegreeChart.json"),
                catalog.getAllCourses());
        fillChart(degreeChart);
        fillNames();
    }

    /**
     * Fills the flowchart with requirements. Protected access for testing
     * purposes.
     *
     * @param degreeChart requirements of the flowchart @pre ideal quarters are
     * 1 based (not 0 based)
     */
    protected void fillChart(List<I_Requirement> degreeChart)
    {
        chart = new ArrayList<List<I_Requirement>>();
        //FOR each requirement in thre degree chart
        for (I_Requirement req : degreeChart)
        {
            int idealQtr = req.getQuarter();
            fill(idealQtr - 1);
            chart.get(idealQtr - 1).add(req);
        }
    }

    private void fillNames()
    {
        final DecimalFormat df = new DecimalFormat("00");
        final String[] names =
        {
            "F", "W", "S"
        };
        //FOR every column in the chart
        for (int col = 0; col < chart.size(); col++)
        {
            try
            {
                quarterNames[col] =
                        names[col % kSemesters]
                        + df.parse("" + (kBaseYear + ((col + 2) / kSemesters)));
            }
            catch (ParseException ex)
            {
                quarterNames[col] = null;
            }


        }
    }

    /**
     * Fills out the chart to be of the given size.
     *
     * @param size @post the chart will have an list at size
     */
    private void fill(int size)
    {
        //WHILE the size of the chart is less than fill size
        while (chart.size() <= size)
        {
            chart.add(new ArrayList<I_Requirement>());
        }
    }

    /**
     * Fills out the chart to be of the given size.
     *
     * @param col column to fill
     * @param row row to fill @postcondition the chart will have an list at size
     */
    private void fill(int col, int row)
    {
        fill(col);
        List<I_Requirement> quarter = chart.get(col);
        //WHILE there are less quarter than rows to fill
        while (quarter.size() <= row)
        {
            quarter.add(null);
        }
    }

    /**
     * Add another quarter at the specified position. give the quarter a default
     * name, and shift each quarter from index forward to the right.
     *
     * @param index the position of the added quarter @pre 0 < index <= number
     * of quarters plus one.
     */
    @Override
    public void addQuarter(int index)
    {
        chart.add(index, new ArrayList<I_Requirement>());
        addQuarterName(index);
        fireTableStructureChanged();
    }

    /**
     * Rename the quarter at index to the name given
     *
     * @param index the index of the changed quarter
     * @param name The name to change qthe quarter to @pre Index is greater than
     * 0 and less than or equal to the number of quarters
     */
    @Override
    public void renameQuarter(int index, String name)
    {
        // SET quarter array at index to name
        quarterNames[index] = name;
    }

    /**
     * Deletes the quarter at the given index.
     *
     * @param index the index of the quarter to delete @pre the quarter to
     * delete doesn't have any classes in it
     */
    @Override
    public void deleteQuarter(int index)
    {
        chart.remove(index);
        deleteQuarterName(index);
        fireTableStructureChanged();

    }

    /**
     * gets the name of the column
     *
     * @param column column index
     * @return name of the column
     */
    @Override
    public String getColumnName(int column)
    {
        return quarterNames[column];
    }

    /**
     * gets list of requirement at the column index
     *
     * @param col column index
     * @return list of requirement at the column index
     */
    @Override
    public List<I_Requirement> getList(int col)
    {
        return chart.get(col);
    }

    /**
     * Accessor to the cell in the chart located at row, col (zero based)
     *
     * @param row row index to get the requirement from
     * @param col col index to get the requirement from
     * @return requirement at the given row col
     */
    @Override
    public I_Requirement getValueAt(int row, int col)
    {
        I_Requirement result = null; //new Requirement("");

        //IF the given row and col is within the chart size THEN
        if (col >= 0 && row >= 0 && chart.size() > col && chart.get(col).size() > row)
        {
            return chart.get(col).get(row);
        }
        return null;
    }

    /**
     * Mutator to the cell in the chart located at row, col (zero based) @pre
     * value should be
     *
     * @param value cell to mutate
     * @param col column of the cell
     * @param row row of the cell
     */
    @Override
    public void setValueAt(Object value, int row, int col)
    {
        I_Requirement crs = (I_Requirement) value;
        fill(col, row);
        chart.get(col).set(row, crs);
    }
    // OBSOLETE: for move to bottom of column

    /**
     * Move a course to the end in the table. Checks for validity and returns -1
     * if no move is allowed.
     *
     * @param fromRow row to move from
     * @param fromCol column to move from
     * @param destCol column to move to
     * @return -1 if no move is allowed.
     */
    @Override
    public int moveCourseToColEnd(int fromRow, int fromCol, int destCol)
    {
        //IF the row to move from is less than the total number of row's THEN
        if (fromRow < chart.get(fromCol).size())
        {
            //IF the row to move to is less than max number of row's THEN
            if (chart.get(destCol).size() < kMaxRows)
            {
                I_Requirement crs = chart.get(fromCol).remove(fromRow);
                chart.get(destCol).add(crs);
                return chart.get(destCol).size() - 1;  // return where we put it
            }
        }
        return -1;
    }

    /**
     * Move a course to a new position in the table. Checks for validity and
     * returns -1 if no move is allowed.
     *
     * @param fromRow row to move from
     * @param fromCol column to move from
     * @param destRow row to move to
     * @param destCol col to move to
     * @return -1 if no move is allowed.
     */
    @Override
    public int moveCourse(int fromRow, int fromCol, int destRow, int destCol)
    {
        //IF row to move from is less than size of row THEN
        if (fromRow < chart.get(fromCol).size())
        {
            //IF row to move is less than max rows
            if (chart.get(destCol).size() < kMaxRows)
            {
                I_Requirement item = chart.get(fromCol).remove(fromRow);
                // if dest out of bounds, put it in last spot
                if (destRow > chart.get(destCol).size())
                {
                    destRow = chart.get(destCol).size();
                }
                chart.get(destCol).add(destRow, item);
                item.setQuarter(destCol + 1);
                return destRow;  // return where we put it
            }
        }
        return -1;  // didn't move anything
    }

    /**
     * gets total number of quarters
     *
     * @return total number of quarters
     */
    @Override
    public int getNumQuarters()
    {
        return chart.size();
    }

    /**
     * Accessor to the number of columns on the board. Assumes all rows have the
     * same number of columns.
     *
     * @return total number of column
     */
    @Override
    public int getColumnCount()
    {
        return chart.size();  // assuming all rows have same number of columns
    }

    /**
     * Accessor to the number of rows on the board.
     *
     * @return total number of rows
     */
    @Override
    public int getRowCount()
    {
        return kMaxRows;
    }

    /**
     * Accessor to the type of data in a column of the board.
     *
     * @param col column index
     * @return class of the column at col
     */
    @Override
    public Class getColumnClass(int col)
    {
        return Requirement.class;
    }

    /**
     * Find the current quarter, i.e., the first quarter that contains a not
     * completed course.
     *
     * @return the column of the current quarter in the table. (0-based), or -1
     * if there are no quarters, or all quarters are complete.
     */
    @Override
    public int findCurrentQuarter()
    {
        int result = -1; // default is first column
        //FOR every quarter in the chart
        for (int count = 0; count < chart.size(); count++)
        {
            List<I_Requirement> list = chart.get(count);
            //IF there are list of requirement at the current quarter THEN
            if (list.size() > 0)
            {
                //FOR every requirement
                for (I_Requirement req : list)
                {
                    //IF requirement is not completed THEN
                    if (!req.isCompleted())
                    {
                        return count;
                    }
                    //END IF
                }
                //END FOR
            }
            //END IF
        }
        //END FOR
        return result;
    }

    /**
     * Build a set of courses prior to a given quarter. Also, specify if the
     * courses need to be completed or not. For sequence checking, completion
     * doesn't matter. For eligibility checking, completion matters. Having a
     * boolean control flag is high coupling, better would be a Strategy
     * pattern.
     *
     * @param col the column in the table representing the desired quarter
     * @param considerCompletion should we check completion status
     * @return set of contingent
     * @see Contingent
     */
    @Override
    public HashSet<Contingent> buildPriorCourses(int col,
                                                 boolean considerCompletion)
    {
        HashSet<Contingent> result = new HashSet<Contingent>();
        // Add the courses taken in all prior quarterNames to a set
        for (int qtr = 0; qtr < col; qtr++)
        {
            // Consider all cells a quarter
            for (I_Requirement req : chart.get(qtr))
            {
                I_Course crc = req.getCourse();
                //IF there is a course for the requirement
                if (crc != null)
                {
                    //IF not to consider completion
                    if (!considerCompletion)
                    {
                        // Completion status doesn't matter: just add it
                        result.add(crc);
                    }
                    //END IF
                    else
                    {
                        // Completion status matters: add it only if taken
                        if (crc.isComplete())
                        {
                            result.add(crc);
                        }
                        //END IF
                    }
                    //END IFELSE
                }//END IF
                else // it doesn't have a selected course
                {
                    //IF not to consider completion
                    if (!considerCompletion)
                    {
                        // Completion status doesn't matter: just add it
                        result.add(req);
                    }
                    //END IF
                }//END ELSE
            }//END FOR
        }//END FOR
        return result;
    }

    /**
     * Are the prereqs for this course met by this flowchart? I.e., is this
     * course in a valid sequence? Currently it looks at all courses prior to it
     * in the flowchart (regaardless of completion status). But consider that in
     * some special cases, like 315, the prereq could actually be another
     * requirement, e.g. Comp Dsg & Assy Lang which itself could be satisfied by
     * one of two courses. Currently, if the user hasn't indicated a choice of
     * 225 or 229, 315 will show pink. It would be nice if 315 showed white as
     * long as the Yellow req was in correct sequence, even if user hasn't made
     * a choice yet. This suggests that the comparison should be at the
     * requirement level of abstraction, not course. Unfortunately, our file
     * format doesn't provide "prereqs" at the requirement level, only at the
     * course level. Our current file format is consistent (I think) with the
     * curriculum, because it lists the prereqs for 315 by course. Similarly
     * with 300; the prereqs are 307 or 309, not "Software Engineering". Leaving
     * it pink would not be horrible, although it's misleading, it still
     * indicates generally that something needs attention. However, there are a
     * few GE requirements that list other GE's as prereqs: ENGL 149 (to fulfill
     * GE A2) shows Prerequisite: Completion of GE Area A1
     *
     * @param req the requirement to investigate
     * @param col the column in the table
     * @return true if ok, false if not
     */
    @Override
    public boolean inSequence(I_Requirement req, int col)
    {
        return req.inSequence(buildPriorCourses(col, false));
    }

    /**
     * Is the student eligible to take this course (Are all the prereqs
     * satisfied?)
     *
     * @param req the requirement to investigate
     * @param col the column in the table
     * @return true if student can now enroll in this course, false if not
     */
    @Override
    public boolean isEligible(I_Requirement req, int col)
    {
        return req.isEligible(buildPriorCourses(col, false));
    }

    /**
     * updates the requirement
     *
     * @param req requirement to update
     */
    @Override
    public void updateRequirement(I_Requirement req)
    {
        /*
         * int pos = required.indexOf(req); if (pos != -1) { Requirement old =
         * required.getReq(pos); String options = req.getFulfillment();
         * old.setFulfillment(options); if (options.contains("*")) { String
         * chosen = CourseOptionList.extractChosen(options);
         * old.setCourse(catalog.getCourse(chosen)); } else {
         * old.setCourse(null); } }
         */
    }

    /**
     * Return a sequential list of all the requirements in this flowchart ready
     * to be serialized.
     *
     * @return a list of requirements (not guaranteed to be in any order)
     */
    @Override
    public List<I_Requirement> getRequirements()
    {
        List<I_Requirement> list = new ArrayList<I_Requirement>();

        //FOR every quarter
        for (List<I_Requirement> qtr : chart)
        {
            // get a copy of this qtr's list and add it to the big list.
            list.addAll(qtr);
        }

        return list;
    }

    /**
     * a printable representation of this flowchart. E.g., F07=14,16
     * W08=1,2,5,15,17 S08=3,4,8,18
     *
     * @return a printable representation of this flowchart.
     */
    @Override
    public String toString()
    {
        StringBuilder buffer = new StringBuilder();
        int count = 0;
        //FOR every list of requirement in the chart
        for (List<I_Requirement> lst : chart)
        {
            //IF there is a list THEN
            if (lst != null)
            {
                buffer.append(quarterNames[count++] + "=");
                //FOR every requirement in the list
                for (I_Requirement req : lst)
                {
//                    int index = 1 + required.indexOf(r);//.get(r);
                    //   Course c = r.getCourse();
//                    buffer.append(index).append(",");
                    String reqname = req.getTitle();
                    buffer.append(reqname).append(",");
                }//END FOR

                int length = buffer.length();
                buffer.deleteCharAt(length - 1);
                buffer.append("\n");

            }//END IF
        }//END FOR
        return buffer.toString();
    }

    /**
     * Calculates the number of units completed on the current flowchart.
     *
     * @return an int of the number of units
     */
    @Override
    public int unitsCompleted()
    {
        // INIT sum to zero
        int sum = 0;
        // FOR each requirement FROM getRequirements()
        for (I_Requirement requirement : getRequirements())
        {
            // SAVE return from CALL to requirement.selectedCourse() as course
            I_Course course = requirement.getCourse();
            // IF course.isComplete()
            if (requirement.isCompleted() && course != null)
            {
                // INCREMENT sum BY course.getUnits()
                sum += course.getUnits();
            } // ENDIF
        } // ENDFOR
        // RETURN sum
        return sum;
    }

    /**
     * Returns an array of all the quarters names
     *
     * @return array of all the quarters names
     */
    public String[] getQuarterNames()
    {
        return quarterNames;
    }

    private void addQuarterName(int index)
    {
        // SAVE old array
        String[] old = quarterNames.clone();
        // INIT new array
        quarterNames = new String[quarterNames.length + 1];
        // starting from zero..
        int current = 0;
        // ADD all quarters up until new quarter
        while (current < index)
        {
            quarterNames[current] = old[current];
            current++;
        }
        // ADD new quarter
        quarterNames[current++] = "";
        // ADD all quarters beyond new quarter
        while (current < old.length)
        {
            quarterNames[current] = old[current - 1];
            current++;
        }
    }

    private void deleteQuarterName(int index)
    {
        // SAVE old array
        String[] old = quarterNames.clone();
        // INIT new array
        quarterNames = new String[quarterNames.length - 1];
        // starting from zero..
        int current = 0;
        // ADD all quarters up until indexed quarter
        while (current < index)
        {
            quarterNames[current] = old[current];
            current++;
        }
        // SKIP indexed quarter.
        current++;
        // ADD all quarters beyond indexed quarter
        while (current < old.length)
        {
            quarterNames[current - 1] = old[current];
            current++;
        }
    }
}
