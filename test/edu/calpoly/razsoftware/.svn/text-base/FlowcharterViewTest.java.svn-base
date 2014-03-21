/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.calpoly.razsoftware;

import junit.framework.TestCase;
import org.jdesktop.application.SingleFrameApplication;
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
public class FlowcharterViewTest extends TestCase
{
    private FlowcharterView view;
    private SingleFrameApplication app;
    private Controller control;
    private I_Catalog catalog;
    private CourseList list;

    public FlowcharterViewTest()
    {
    }

    @BeforeClass
    public void setUpClass() throws Exception
    {
    }

    @AfterClass
    public void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp()
    {
        list = new CourseList();
        catalog = new Catalog(list);
        control = new Controller(catalog);
        app = new FlowcharterApp();
        view = new FlowcharterView(app, control);
        control.setView(view);
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of init method, of class FlowcharterView.
     */
    @Test
    public void testInit()
    {
        System.out.println("init");
        int expectedCol = control.findCurrentQuarter();
        int expectedRow = 0;
        view.init();
        assertEquals(expectedRow, view.currentRow());
        assertEquals(expectedCol, view.currentCol());
    }

    /**
     * Test of showAboutBox method, of class FlowcharterView.
     */
    @Test
    public void testShowAboutBox()
    {
        System.out.println("showAboutBox");
        // TODO: must be done manually
    }

    /**
     * Test of currentRow method, of class FlowcharterView.
     */
    @Test
    public void testCurrentRow()
    {
        System.out.println("currentRow");
        int expResult = 1;
        view.moveCursor(1, 1);
        int result = view.currentRow();
        assertEquals(expResult, result);
        expResult = 2;
        view.moveCursor(2, 2);
        result = view.currentRow();
        assertEquals(expResult, result);
    }

    /**
     * Test of currentCol method, of class FlowcharterView.
     */
    @Test
    public void testCurrentCol()
    {
        System.out.println("currentCol");
        int expResult = 1;
        view.moveCursor(1, 1);
        int result = view.currentCol();
        assertEquals(expResult, result);
        expResult = 2;
        view.moveCursor(1, 2);
        result = view.currentCol();
        assertEquals(expResult, result);
    }

    /**
     * Test of showUpdateDialog method, of class FlowcharterView.
     */
    @Test
    public void testShowUpdateDialog()
    {
        System.out.println("showUpdateDialog");
        // TODO: must be done manually
    }

    /**
     * Test of updateDetails method, of class FlowcharterView.
     */
    @Test
    public void testUpdateDetails()
    {
        System.out.println("updateDetails");
        // TODO: must be done manually
    }

    /**
     * Test of moveCursor method, of class FlowcharterView.
     */
    @Test
    public void testMoveCursor()
    {
        System.out.println("moveCursor");
        int targetRow = 3;
        int targetCol = 3;
        view.moveCursor(targetRow, targetCol);
        assertEquals(targetRow, view.currentRow());
        assertEquals(targetCol, view.currentCol());
    }

    /**
     * Defect #360
     * Manual GUI Test @ https://wiki.csc.calpoly.edu/pineApple/wiki/manualguitests
     */
    @Test
    public void testMenuItems()
    {
    }
}
