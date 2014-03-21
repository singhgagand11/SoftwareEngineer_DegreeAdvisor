/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.calpoly.razsoftware;

import java.util.Arrays;
import com.google.common.collect.ImmutableList;
import javax.swing.JTable;
import junit.framework.TestCase;
import org.jdesktop.application.SingleFrameApplication;
import org.junit.Before;
import org.junit.Test;

/**
 *  
 * @author  Gagandeep Singh Kohli (gasingh)
 */
public class FlowcharterViewIntTest extends TestCase
{
    private FlowcharterView view;
    private SingleFrameApplication app;
    private Controller control;
    private Catalog catalog;
    private CourseList list;

    @Before
    public void setUp()
    {

        I_Course[] courses =
        {
            new Course(ImmutableList.of("CSC"), 102, 4, "Fund of CS 2", "CSC 102"),
            new Course(ImmutableList.of("CSC"), 101, 4, "Fund of CS 1", "CSC 101"),
            new Course(ImmutableList.of("MATH"), 101, 4, "Math for n00bs", "MATH 101"),
            new Course(ImmutableList.of("CSC"), 141, 4, "Disc Struct", "CSC 141")
        };
        list = new CourseList(Arrays.asList(courses));
        catalog = new Catalog(list);
        control = new Controller(catalog);

        view = new FlowcharterView(new FlowcharterApp(), control);
        control.setView(view);


    }

    /**
     * Test of currentCol method, of class FlowcharterView.
     */
    @Test
    public void testCurrentColRow()
    {
        //get the flowchart from the model
        Flowchart fc = control.getModel();
        //create a jtable with the flowchart
        JTable table = new JTable(fc);
        //check selectedCol
        assertEquals(table.getSelectedColumn(), view.currentCol());
        //check selectedRow
        assertEquals(table.getSelectedRow(), view.currentRow());

    }

    /**
     * Test of showUpdateDialog method, of class FlowcharterView.
     */
    @Test
    public void testShowUpdateDialog()
    {
        //GUI TEST
    }

    /**
     * Test of updateDetails method, of class FlowcharterView.
     */
    @Test
    public void testUpdateDetails()
    {
        //GUI TEST
    }

    /**
     * Test of moveCursor method, of class FlowcharterView.
     */
    @Test
    public void testMoveCursor()
    {
        //move the cursor to 1 2
        view.moveCursor(1, 2);

        //get the flowchart from the model
        Flowchart fc = control.getModel();
        //create a jtable with the flowchart
        JTable table = new JTable(fc);
        //check if the selected column is 2
        assertEquals(2, view.currentCol());
        //check if the selected Row is 1
        assertEquals(1, view.currentRow());

    }

    /**
     * Test of actionPerformed method, of class FlowcharterView.
     */
    @Test
    public void testActionPerformed()
    {
        /** MANUAL GUI TEST @
         * https://wiki.csc.calpoly.edu/pineApple/wiki/manualflowchartViewtest
         */

    }


    /**
     * Test of mousePressed method, of class FlowcharterView.
     */
    @Test
    public void testMousePressed()
    {
        /** MANUAL GUI TEST @
         * https://wiki.csc.calpoly.edu/pineApple/wiki/manualflowchartViewtest
         */

    }

    /**
     * Test of mouseReleased method, of class FlowcharterView.
     */
    @Test
    public void testMouseReleased()
    {
        /** MANUAL GUI TEST @
         * https://wiki.csc.calpoly.edu/pineApple/wiki/manualflowchartViewtest
         */

    }
}