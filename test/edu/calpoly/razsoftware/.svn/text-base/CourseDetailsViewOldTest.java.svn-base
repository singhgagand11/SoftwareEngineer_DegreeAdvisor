/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.calpoly.razsoftware;

import junit.framework.TestCase;
import org.uispec4j.Panel;
import org.uispec4j.TextBox;

/**
 *
 * @author gagandeep
 */
public class CourseDetailsViewOldTest extends TestCase
{
    I_Requirement fakeReq;
    I_Course fakeCourse;
    Panel panel;

    public void setUp()
    {

        fakeReq = new FakeRequirement3();
        fakeCourse = fakeReq.getCourse();

        panel = new Panel(CourseDetailsViewOld.getPanel(fakeReq));



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

        String txt = "Course: " + fakeCourse.getTitle() + "  COMPLETED";
        assertEquals(txt, courseName.getText());
    }

    public void testPreReqLabel()
    {

        TextBox courseName = panel.getTextBox("Prereqs: ");

        String txt = "Prereqs: " + "FakeCourse Preq";
        assertEquals(txt, courseName.getText());
    }

    public void testDescripLabel()
    {

        TextBox courseName = panel.getTextBox("Description: ");

        String txt = "Description: " + "Fake Description";
        assertEquals(txt, courseName.getText());
    }

    public void testRequirementLabel()
    {

        TextBox courseName = panel.getTextBox("Requirement: ");

        String txt = "Requirement: " + "FakeReq";
        assertEquals(txt, courseName.getText());
    }

    public void testOptionsLabel()
    {


        TextBox courseName = panel.getTextBox("Options: ");

        //TODO: FIX THE FORMATTING OF CourseDetailsViewOld 
        String txt = "Options: " + "FakeCourse,  FakeCourse,  FakeCourse,  FakeCourse,  FakeCour...";


        assertEquals(txt, courseName.getText());
    }

    /**
     * Defect #280
     */
    public void testNullCrc()
    {
        fakeReq = new FakeRequirement3()
        {
            public Course getCourse()
            {
                return null;
            }
        };
        panel = new Panel(CourseDetailsViewOld.getPanel(fakeReq));

         assertEquals("Description: ", panel.getTextBox("Description: ").getText());
    }
}
