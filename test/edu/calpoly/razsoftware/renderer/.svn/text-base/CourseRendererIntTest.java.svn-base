package edu.calpoly.razsoftware.renderer;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import edu.calpoly.razsoftware.*;
import java.awt.Color;
import java.awt.Component;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JTable;
import junit.framework.TestCase;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author gagandeep
 */
public class CourseRendererIntTest extends TestCase
{
    CourseRenderer renderer;
    JTable table;
    Flowchart flowchart;
    I_Requirement req1; // 101
    I_Requirement req2; // 108 or 102
    I_Requirement req3; // 141
    private Course course1; // 101
    private Course course2; // 102
    private Course course4; // 141

    @Before
    public void setUp()
    {
        CourseList list = new CourseList();
        course1 = new Course(Arrays.asList("CSC"), 101);
        course2 = new Course(Arrays.asList("CSC"), 102);
        Course course3 = new Course(Arrays.asList("CSC"), 108);
        Set<Set<I_Course>> pres = new HashSet<Set<I_Course>>();
        course2.setPreRequisites(pres);
        list.add(course1);
        list.add(course2);
        list.add(course3);
        Catalog catalog = new Catalog(list);

        req1 = new Requirement(course1, 1);

        Set<I_Course> req2courses = new HashSet<I_Course>();
        req2courses.add(course2);
        req2courses.add(course3);
        req2 = new Requirement("CSC intro", req2courses, true, 2);

        course4 = new Course(Arrays.asList("CSC"), 141);
        Set<Set<I_Course>> course4pres = new HashSet<Set<I_Course>>();
        Set<I_Course> course4pre = new HashSet<I_Course>();
        course4pre.add(course4);
        course4.setPreRequisites(course4pres);
        req3 = new Requirement(course4, 3);

        flowchart = new Flowchart(catalog, Arrays.asList((I_Requirement) req1, req2, req3));

        table = new JTable(flowchart);
        renderer = new CourseRenderer();
    }

    @After
    public void tearDown()
    {
        renderer = null;
    }

    /**
     * Test of null component
     */
    @Test
    public void testNullComponent()
    {
        Component comp =
                renderer.getTableCellRendererComponent(table, null, true, true, 0, 0);

        assertTrue(comp.getBackground().equals(Color.WHITE));
    }

    @Test
    public void testNullCourseAndSelection()
    {
        table.setRowSelectionInterval(0, 0);
        table.setColumnSelectionInterval(1, 1);

        Component comp =
                renderer.getTableCellRendererComponent(table, req2, true, true, 0, 1);

        //CHECK BACKGROUND
        assertTrue(comp.getBackground().equals(Color.YELLOW));
        assertTrue(comp.getForeground().equals(Color.BLUE));

        comp =
                renderer.getTableCellRendererComponent(table, req1, false, true, 0, 1);

        assertTrue(comp.getForeground().equals(Color.BLACK));
    }

    @Test
    public void testTextFormat()
    {

        //test missing course (null)
        String result = renderer.formatCell(table, renderer, req2, 0);
        String expected = "<HTML><small>???</small><small>? CSC intro</small></HTML>";
        assertEquals(expected, result);

        assertEquals(Color.YELLOW, renderer.getBackground());

        // set req3 as selected course
        table.setRowSelectionInterval(0, 0);
        table.setColumnSelectionInterval(2, 2);

        // add course2 as prereq of course4, which is in req3
        Set<Set<I_Course>> c4pres = new HashSet<Set<I_Course>>();
        Set<I_Course> c4pre = new HashSet<I_Course>();
        c4pre.add(course2);
        c4pres.add(c4pre);
        course4.setPreRequisites(c4pres);

        // set req2.course4 to render
        req2.setCourse(course2);
        course2.setCompleted(true);
        req2.setCompleted(true);

        //render req2
        result = renderer.formatCell(table, renderer, req2, 0);
        // assert dark green bg 
        assertEquals(new Color(17, 102, 13), renderer.getBackground());
        expected = "<HTML><U><B><I>CSC102&gt;</I></B></U><small>...</small></HTML>";
        assertEquals(expected, result);




    }

    @Test
    public void testPriorCourseBG()
    {
        Set<Set<I_Course>> c4pres = new HashSet<Set<I_Course>>();
        Set<I_Course> c4pre = new HashSet<I_Course>();
        c4pre.add(course1);
        c4pres.add(c4pre);
        course4.setPreRequisites(c4pres);

        //test background, while requirement out of sequence
        renderer.formatCell(table, renderer, req3, 0);
        assertEquals(Color.pink, renderer.getBackground());

        course1.setCompleted(false);
        
        //test background, while requirement is not eligible to be taken   
        renderer.formatCell(table, renderer, req3, 3);
        assertEquals(Color.LIGHT_GRAY, renderer.getBackground());
    }
}