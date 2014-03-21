/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.calpoly.razsoftware;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.uispec4j.Panel;
import org.uispec4j.UISpecTestCase;

/**
 * Integration test for CourseDetailsView
 * @author gagandeep
 */
public class CourseDetailsViewIntTest extends UISpecTestCase
{
    I_Requirement req;
    I_Course course;
    Panel panel;

    public void setUp()
    {
        course = new Course(Arrays.asList("CSC"), 102);
        Set<Set<I_Course>> pres = new HashSet<Set<I_Course>>();
        Set<I_Course> pre = new HashSet<I_Course>();
        pre.add(new Course(Arrays.asList("CSC"), 101));
        pres.add(pre);
        course = new Course(Arrays.asList("CSC"), 102, 4, "CmptrSci", "CSC 102");
        course.setPreRequisites(pres);
        course.setCompleted(true);
        req = new Requirement(course, 1);
        panel = new Panel(CourseDetailsView.getPanel(req));

    }

    public void tearDown()
    {
    }

    /**
     * Test of getPanel method, of class CourseDetailsView.
     */
    public void testCourseLabel()
    {
        String txt =  course.getName() + "  COMPLETED";
        assertEquals(txt, panel.getTextBox("courseTitle").getText());
    }

    public void testPreReqLabel()
    {
        String txt = "CSC101";
        assertEquals(txt, panel.getInputTextBox("prereq").getText());
    }

    public void testDescripLabel()
    {
        String txt = "CSC 102";
        assertEquals(txt, panel.getInputTextBox("descrip").getText());
    }

    public void testRequirementLabel()
    {
        String txt = "CSC102";
        assertEquals(txt, panel.getInputTextBox("reqs").getText());
    }

    public void testOptionsLabel()
    {
        String txt = "CSC102";
        assertTrue(panel.containsLabel(txt));
    }
    public void testDepLabel()
    {
        String txt = "Dept Directions:";
        assertTrue(panel.containsLabel(txt));
    }
}
