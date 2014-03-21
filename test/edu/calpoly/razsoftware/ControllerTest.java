package edu.calpoly.razsoftware;

import junit.framework.TestCase;
import org.jdesktop.application.SingleFrameApplication;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joe Walsh <jkwalsh@calpoly.edu>
 */
public class ControllerTest extends TestCase
{
    private FlowcharterView view;
    private SingleFrameApplication app;
    private Controller control;
    private FakeCatalog1 catalog;
    
    public ControllerTest()
    {
        
        catalog = new FakeCatalog1();
        control = new Controller(catalog);
        app = new FlowcharterApp();
        view = new FlowcharterView(app, control);
        control.setView(view);
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }
    
    @Before
    public void setUp()
    {
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
        System.out.println("mouseClicked");
        // TODO: manually verify GUI shows update dialog
        control.mouseClicked(2);
    }

    /**
     * Test of mouseRightClicked method, of class Controller.
     */
    @Test
    public void testMouseRightClicked()
    {
        System.out.println("mouseRightClicked");
        int row = 0;
        int col = 0;
        control.mouseRightClicked(row, col);
    }

    /**
     * Test of moveCourse method, of class Controller.
     */
    @Test
    public void testMoveCourse()
    {
        System.out.println("moveCourse");
        int fromRow = 0;
        int fromCol = 0;
        int toRow = 1;
        int toCol = 1;
        control.setView(view);
        control.moveCourse(fromRow, fromCol, toRow, toCol);
        // TODO: manually verify that course moved
    }

    /**
     * Test of getChartSize method, of class Controller.
     */
    @Test
    public void testGetChartSize()
    {
        System.out.println("getChartSize");
        int expResult = 12;
        int result = control.getChartSize();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of findCurrentQuarter method, of class Controller.
     */
    @Test
    public void testFindCurrentQuarter()
    {
        System.out.println("findCurrentQuarter");
        // TODO: manually verify
    }

    /**
     * Test of getCourseFromCatalog method, of class Controller.
     */
    @Test
    public void testGetCourseFromCatalog()
    {
        System.out.println("getCourseFromCatalog");
        List<I_Course> courses = catalog.getCoursesByDept("CPE");
        I_Course expResult = courses.get(0);
        String name = expResult.getName();
        I_Course result = control.getCourseFromCatalog(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeptsFromCatalog method, of class Controller.
     */
    @Test
    public void testGetDeptsFromCatalog()
    {
        System.out.println("getDeptsFromCatalog");
        Set<String> expResult = catalog.getDeptNames();
        Set result = control.getDeptsFromCatalog();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNamesFromCatalog method, of class Controller.
     */
    @Test
    public void testGetNamesFromCatalog()
    {
        System.out.println("getNamesFromCatalog");
        String dept = "CPE";
        List<String> expResult = new java.util.ArrayList<String>();
        expResult.add("CPE101");
        expResult.add("CPE102");

        List result = control.getNamesFromCatalog(dept);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCompletedUnits method, of class Controller.
     */
    @Test
    public void testGetCompletedUnits()
    {
        System.out.println("getCompletedUnits");
        int expResult = 0;
        int result = control.getCompletedUnits();
        assertEquals(expResult, result);
        for (I_Course crs : catalog.getAllCourses().getCourses())
        {
            crs.setCompleted(true);
            expResult += crs.getUnits();
        }
        result = control.getCompletedUnits();
        assertEquals(expResult, result);
    }
}
