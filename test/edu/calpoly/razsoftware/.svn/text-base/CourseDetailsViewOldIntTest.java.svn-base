/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.calpoly.razsoftware;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import junit.framework.TestCase;
import org.uispec4j.Panel;
import org.uispec4j.TextBox;

/**
 *
 * @author gagandeep
 */
public class CourseDetailsViewOldIntTest extends TestCase
{
    I_Requirement req;
    I_Course course;
    Panel panel;

    public void setUp()
    {
        course = new Course(Arrays.asList("CSC"),102);
        Set<Set<I_Course>> pres = new HashSet<Set<I_Course>>();
        Set<I_Course> pre = new HashSet<I_Course>();
        pre.add(new Course(Arrays.asList("CSC"), 101));
        pres.add(pre);
        course = new Course(Arrays.asList("CSC"),102,4,"CmptrSci","CSC 102");
        course.setPreRequisites(pres);
        course.setCompleted(true);
        req = new Requirement(course, 1);
        panel = new Panel(CourseDetailsViewOld.getPanel(req));



    }

    public void tearDown()
    {
    }

    /**
     * Test of getPanel method, of class CourseDetailsViewOld.
     */
    public void testCourseLabel()
    {

        TextBox courseName = panel.getTextBox("Course: ");

        String txt = "Course: " + course.getName() + "  COMPLETED";
        assertEquals(txt, courseName.getText());
    }

    public void testPreReqLabel()
    {

        TextBox courseName = panel.getTextBox("Prereqs: ");

        String txt = "Prereqs: " + "CSC101";
        assertEquals(txt, courseName.getText());
    }

    public void testDescripLabel()
    {

        TextBox courseName = panel.getTextBox("Description: ");

        String txt = "Description: " + "CSC 102";
        assertEquals(txt, courseName.getText());
    }
    public void testRequirementLabel()
    {

        TextBox courseName = panel.getTextBox("Requirement: ");

        String txt = "Requirement: " + "CSC102";
        assertEquals(txt, courseName.getText());
    }

    public void testOptionsLabel()
    {

         
        TextBox courseName = panel.getTextBox("Options: ");

        //TODO: FIX THE FORMATTING OF CourseDetailsViewOld 
        String txt =  "Options: " + "CSC102";
         
   
        assertEquals(txt, courseName.getText());
    }
}
