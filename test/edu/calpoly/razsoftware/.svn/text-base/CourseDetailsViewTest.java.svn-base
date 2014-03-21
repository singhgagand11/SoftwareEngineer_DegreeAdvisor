/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.calpoly.razsoftware;

import org.uispec4j.Panel;
import org.uispec4j.UISpecTestCase;

/**
 *
 * @author gagandeep
 */
public class CourseDetailsViewTest extends UISpecTestCase
{
    I_Requirement fakeReq;
    I_Course fakeCourse;
    Panel panel;

    public void setUp()
    {

        fakeReq = new FakeRequirement3();
        fakeCourse = fakeReq.getCourse();
        panel = new Panel(CourseDetailsView.getPanel(fakeReq));

    }

    public void tearDown()
    {
    }

    /**
     * Test of getPanel method, of class CourseDetailsView.
     */
    public void testCourseLabel()
    {
        String txt = fakeCourse.getTitle() + "  COMPLETED";
        assertTrue(this.panel.containsLabel(txt));
    }

    public void testPreReqLabel()
    {

 
        String txt = "FakeCourse Preq";
        assertEquals(txt, panel.getInputTextBox("prereqs").getText());

    }

    public void testDescripLabel()
    {
        String txt = "Fake Description";
        assertEquals(txt, panel.getInputTextBox("descrip").getText());
    }

    public void testRequirementLabel()
    {
        String txt = "FakeReq";
        assertEquals(txt, panel.getInputTextBox("reqs").getText());
    }

    /**
     * Defect # 279
     */
    public void testOptionsLabel()
    {
        String txt = "FakeCourse,  FakeCourse,  FakeCourse,  FakeCourse,  FakeCour...";
        assertTrue(this.panel.containsLabel(txt));
    }

    /**
     * Defect # 335
     */
     public void testDepLabel()
    {
        String txt = "Dep Dir";
        assertTrue(this.panel.containsLabel(txt));
    }

}
