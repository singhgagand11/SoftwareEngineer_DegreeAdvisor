package edu.calpoly.razsoftware;

import java.util.LinkedList;
import org.uispec4j.Window;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.Collections;
import junit.framework.TestCase;
import org.jdesktop.application.SingleFrameApplication;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 *  @author  Gagandeep Singh Kohli (gasingh)
 */
public class ControllerIntTest extends TestCase
{
    private FlowcharterView view;
    private SingleFrameApplication app;
    private Controller control;
    private Catalog catalog;
    private CourseList list;
    private Window win;

    public ControllerIntTest()
    {
    }

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
        win = new Window(view.getFrame());

    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of mouseClicked method, of class Controller.
     */
    @Test
    public void testMouseClicked()
    {
        /**GUI TEST
         * https://wiki.csc.calpoly.edu/pineApple/wiki/controllerIntTest
         */
    }

    /**
     * Test of mouseRightClicked method, of class Controller.
     */
    @Test
    public void testMouseRightClicked()
    {
        //SET csc101 as taken
        control.mouseRightClicked(0, 0);
        assertTrue(list.lookUp("CSC", 101).isComplete());

        //SET csc101 as not taken
        control.mouseRightClicked(0, 0);
        assertFalse(list.lookUp("CSC", 101).isComplete());
    }

    /**
     * Test of moveCourse method, of class Controller.
     */
    @Test
    public void testMoveCourse()
    {
        //SET fc to flowchart model
        Flowchart fc = control.getModel();

        //get the course at 0 0
        I_Course course = fc.getValueAt(0, 0).getCourse();

        //move the course from 0, 0 -> 4 4
        fc.moveCourse(0, 0, 4, 4);

        //check if the course is at 4 4
        assertEquals(0, fc.getValueAt(4, 4).getCourse().compareTo(course));
    }

    /**
     * Test of getChartSize method, of class Controller.
     */
    @Test
    public void testGetChartSize()
    {
        //SET fc to flowchart model
        Flowchart fc = control.getModel();
        //check if controlls gets the number of quarters from fc
        assertEquals(fc.getNumQuarters(), control.getChartSize());
    }

    /**
     * Test of findCurrentQuarter method, of class Controller.
     */
    @Test
    public void testFindCurrentQuarter()
    {

        //SET fc to flowchart model
        Flowchart fc = control.getModel();

        //check if controls returns the write quarter
        assertEquals(fc.findCurrentQuarter(), control.findCurrentQuarter());
    }

    /**
     * Test of getCourseFromCatalog method, of class Controller.
     */
    @Test
    public void testGetCourseFromCatalog()
    {
        //check if controller can find csc101
        I_Course crc = control.getCourseFromCatalog("CSC101");
        //check if controller found the same reference of csc101 as in the course list
        assertEquals(list.lookUp("CSC", 101), crc);
    }

    /**
     * Test of getDeptsFromCatalog method, of class Controller.
     */
    @Test
    public void testGetDeptsFromCatalog()
    {

        Set<String> dep = control.getDeptsFromCatalog();

        //SET fc to flowchart model
        Flowchart fc = control.getModel();

        //SET act to deptsNames from catalog
        Set<String> act = catalog.getDeptNames();

        //check the size
        assertEquals(act.size(), dep.size());

        //FOR each department string from dep 
        for (String str : dep)
        {
            //CHECK if the str is in act[ual] catalog
            assertNotNull(act.contains(str));
        }

    }

    /**
     * //TODO: Might be a Defect 
     * Test of getNamesFromCatalog method, of class Controller.
     */
    @Test
    public void testGetNamesFromCatalog()
    {
        List<String> dep = control.getNamesFromCatalog("CSC");

        //SET fc to flowchart model
        Flowchart fc = control.getModel();

        //SET act to deptsNames from catalog
        List<String> act = new LinkedList<String>();

        act.add("CSC101");
        act.add("CSC102");
        act.add("CSC141");

        //Sort the list of strings
        Collections.sort(act);
        Collections.sort(dep);
        //compare the two list of names from catalog
        assertEquals(act, dep);

    }

    /**
     * Test of open method, of class Controller.
     */
    @Test
    public void testOpen()
    {
        /**GUI TEST
         * https://wiki.csc.calpoly.edu/pineApple/wiki/controllerIntTest
         */
    }

    /**
     * Test of save method, of class Controller.
     */
    @Test
    public void testSave()
    {
        /**GUI TEST
         * https://wiki.csc.calpoly.edu/pineApple/wiki/controllerIntTest
         */
    }

    /**
     * Test of saveAs method, of class Controller.
     */
    @Test
    public void testSaveAs()
    {
        /**GUI TEST
         * https://wiki.csc.calpoly.edu/pineApple/wiki/controllerIntTest
         */
    }

    /**
     * Test of getCompletedUnits method, of class Controller.
     */
    @Test
    public void testGetCompletedUnits()
    {
        //check no course are completed
        assertEquals(0, control.getCompletedUnits());

        int totalCredit = 0;
        //FOR each cours in out course list
        for (I_Course c : list.getCourses())
        {
            //set the course as completed through the controller
            control.getCourseFromCatalog(c.getName()).setCompleted(true);
            //add the number of units to totalCredit
            totalCredit += c.getUnits();
        }
        
        //check if the controller returns correct number of completed units
        assertEquals(totalCredit, control.getCompletedUnits());
    }

    /**
     * Test of getModel method, of class Controller.
     */
    @Test
    public void testGetModel()
    {
        //check that controller has created a model
        assertNotNull(control.getModel());
    }
}
