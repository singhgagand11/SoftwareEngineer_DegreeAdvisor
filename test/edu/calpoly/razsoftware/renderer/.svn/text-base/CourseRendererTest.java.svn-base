package edu.calpoly.razsoftware.renderer;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import edu.calpoly.razsoftware.Contingent;
import edu.calpoly.razsoftware.I_Requirement;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.TextComponent;
import java.util.HashSet;
import javax.swing.JTable;
import junit.framework.TestCase;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for CourseRendererTest
 * @author gagandeep
 */
public class CourseRendererTest extends TestCase
{
    CourseRenderer ren;
    JTable fakeJTable;
    FakeFlowchart2 fakeFlow;
    I_Requirement fakeReq;

    @Before
    public void setUp()
    {

        fakeReq = (I_Requirement) new FakeRequirement2();
        final I_Requirement[][] reqsTbl =
        {
            {
                fakeReq, fakeReq, fakeReq
            },
            {
                fakeReq, fakeReq, fakeReq
            }
        };


        String[] names =
        {
            "REQ", "REQ", " REQ"
        };

        fakeJTable = new JTable(reqsTbl, names);
        fakeFlow = new FakeFlowchart2()
        {
            public I_Requirement getValueAt(int row, int col)
            {
                return reqsTbl[0][0];
            }
        };
        fakeJTable.setModel(fakeFlow);
        ren = new CourseRenderer();
    }

    @After
    public void tearDown()
    {
        ren = null;
    }

    /**
     * Test of null component
     */
    @Test
    public void testNullComponent()
    {
        Component comp =
                ren.getTableCellRendererComponent(fakeJTable, null, true, true, 0, 0);
        //Check if the cell background is white for null requirement
        assertTrue(comp.getBackground().equals(Color.WHITE));
    }

    @Test
    public void testNullCourseAndSelection()
    {


        Component comp =
                ren.getTableCellRendererComponent(fakeJTable, fakeReq, true, true, 0, 0);

        //CHECK BACKGROUND for null course in the requirement
        assertTrue(comp.getBackground().equals(Color.YELLOW));
        //Check if the foreground is set to blue for due to being "selected"
        assertTrue(comp.getForeground().equals(Color.BLUE));

        comp =
                ren.getTableCellRendererComponent(fakeJTable, fakeReq, false, true, 0, 0);
        //Check if the foreground is set to blue for not being "selected"
        assertTrue(comp.getForeground().equals(Color.BLACK));
    }

    /**
     * Defect #278
     */
    @Test
    public void testTextFormat()
    {
        //A dummy componenet for checking background/foreground color setting
        Component dummyCell = new Button();

        //test missing course (null)
        String result = ren.formatCell(fakeJTable, dummyCell, fakeReq, 0);
        String expected = "<HTML><small>???</small><small>? FakeReqTitle</small></HTML>";
        assertTrue(result.equals(expected));
        //check that selected course in the table is not a prereq for the fakeReq,
        //then bg is yellow
        assertTrue(dummyCell.getBackground().equals(Color.YELLOW));


        fakeReq.setCourse(new FakeCourse2()
        {
            public boolean isComplete()
            {
                return true;
            }
        });
        result = ren.formatCell(fakeJTable, dummyCell, fakeReq, 0);
        //the background is dark green for being completed
        assertTrue(dummyCell.getBackground().equals(new Color(17, 102, 13)));
        expected = "<HTML><U><B><I>FakeCourse&gt;</I></B></U><small>...</small></HTML>";
        assertTrue(result.equals(expected));




    }

    @Test
    public void testPriorCourseBG()
    {
        Component dummyCell = new Button();
        //create a course that is not eligible
        fakeReq.setCourse(new FakeCourse2()
        {
            public boolean inSequence(HashSet<Contingent> prior)
            {
                return true;
            }

            public boolean isEligible(HashSet<Contingent> prior)
            {
                return false;
            }
        });


        String result = ren.formatCell(fakeJTable, dummyCell, fakeReq, 0);


        //test background, while requirement out of sequence
        ren.formatCell(fakeJTable, dummyCell, fakeReq, 0);
        assertTrue(dummyCell.getBackground().equals(Color.LIGHT_GRAY));

        fakeReq.setCourse(new FakeCourse2()
        {
            public boolean inSequence(HashSet<Contingent> prior)
            {
                return false;
            }
        });

        //test background, while requirement is out of sequence to be taken   
        result = ren.formatCell(fakeJTable, dummyCell, fakeReq, 0);
        assertTrue(dummyCell.getBackground().equals(Color.pink));
    }
}