package edu.calpoly.razsoftware.systemtests;

import edu.calpoly.razsoftware.FlowcharterApp;
import org.uispec4j.ComboBox;
import org.uispec4j.Panel;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;
import org.uispec4j.Table;
import org.uispec4j.Trigger;
import org.uispec4j.interception.WindowHandler;
import org.uispec4j.interception.WindowInterceptor;

/**
 * Purpose : To verify that requirement details are shown in Course Detail View.
 * @author gagandeep
 */
public class TestCase4 extends UISpecTestCase
{
    Window window;
    Table table;
    Panel coursePane;

    @Override
    protected void setUp() throws Exception
    {
        setAdapter(new org.uispec4j.interception.MainClassAdapter(
                FlowcharterApp.class, new String[0]));
        this.window = this.getMainWindow();
        this.table = window.getTable();
        coursePane = window.getPanel("courseDetails");
    }

    public void testCaseFour()
    {
        String courseName = (String) table.getContentAt(2, 0);
        String expected = "<HTML><small>???</small><small>? GE A1</small></HTML>";
        assertEquals(expected, courseName);

        //double click on ??? GE A1
        table.click(2, 0);

        WindowInterceptor aboutPopup =
                WindowInterceptor.init(table.triggerDoubleClick(2, 0));

        aboutPopup.process(new WindowHandler()
        {
            @Override
            public Trigger process(Window win) throws Exception
            {
                ComboBox crcs = win.getComboBox();
                //check if ENGL134 is in the options box
                assertTrue(crcs.contains("ENGL133"));
                //select ENGL 134
                crcs.select("ENGL133");

                return win.getButton("OK").triggerClick();
            }
        });
        aboutPopup.run();

        String descrip = "Writing and stylistic analysis of expository papers. "
                + "Study and application oftechniques of exposition. Critical "
                + "reading of models of effective writing.Additional emphasis "
                + "on grammar and writing issues appropriate for Englishas a "
                + "Second Language students. 4 lectures. ";

        //check course detail view

        coursePane = window.getPanel("courseDetails");

        assertTrue(coursePane.containsLabel("Course:"));
        assertTrue(coursePane.containsLabel("ENGL133"));
        assertTrue(coursePane.containsLabel("Description:"));

        assertTrue(coursePane.containsLabel("Prereqs:"));
        assertEquals("ENGL111", coursePane.getInputTextBox("prereqs").getText());


        String reqText = "ENGL133 or ENGL134";
        assertEquals(reqText, coursePane.getInputTextBox("reqs").getText());

        //option is "Options: ENGL134, ENGL134" ???

        String opText = "ENGL133,  ENGL134";
        assertTrue(coursePane.containsLabel(opText));


        assertEquals(descrip, coursePane.getInputTextBox("descrip").getText());

    }
}
