package edu.calpoly.razsoftware;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import junit.framework.TestCase;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jdalbey
 */
public class FlowchartTest extends TestCase
{
    private static I_Course course;
    private static final I_Catalog catalog = new FakeCatalog()
    {
        //FakeCatalog
        @Override
        public CourseList getAllCourses()
        {
            CourseList list = super.getAllCourses();
            course = new FakeCourse()
            {
                @Override
                public boolean equals(Object obj)
                {
                    return obj instanceof FakeCourse;
                }
            };
            list.add(course);
            return list;
        }
    };
    private static final List<I_Requirement> reqList = new ArrayList<I_Requirement>();

    static
    {
        reqList.add(new FakeRequirement(new FakeCourse()));

    }
    private static Flowchart flowchart;

    @Before
    public void setUp()
    {
        flowchart = new Flowchart(catalog, reqList);
    }

    @Test
    public void testBuild()
    {
        //System.out.println(flowchart);
        // TODO: assert needed here
    }

    @Test
    public void testBuildFromDatabase()
    {
    }

    @Test
    public void testAddQuarter()
    {
        flowchart = new Flowchart(catalog, reqList);
        assertEquals(1, flowchart.getNumQuarters());
        flowchart.addQuarter(1);
        assertEquals(2, flowchart.getNumQuarters());
        assertEquals("",flowchart.getColumnName(1));
    }

    @Test
    public void testRenameQuarter()
    {
        flowchart.renameQuarter(0, "Q");
        assertEquals("Q", flowchart.getColumnName(0));
    }

    @Test
    public void testDeleteQuarter()
    {
        assertEquals(1, flowchart.getNumQuarters());
        flowchart.deleteQuarter(0);
        assertEquals(0, flowchart.getNumQuarters());
    }

    @Test
    public void testGetList()
    {
        List<I_Requirement> list = flowchart.getList(0);
        System.out.println(list);
        assertEquals(1, list.size());
        assertEquals(flowchart.getValueAt(0, 0), list.get(0));
    }

    @Test
    public void testGetValueAt()
    {
        assertNotNull(flowchart.getValueAt(0, 0));
    }

    @Test
    public void testSetValueAt()
    {
        I_Requirement requirement = new FakeRequirement(new FakeCourse());
        flowchart.addQuarter(1);
        flowchart.setValueAt(requirement, 1, 0);
        assertEquals(requirement, flowchart.getValueAt(1, 0));
    }

    @Test
    public void testMoveCourseToColEnd()
    {
        flowchart.addQuarter(1);
        flowchart.moveCourseToColEnd(0, 0, 1);
        assertEquals(reqList.get(0), flowchart.getValueAt(0, 1));
    }

    @Test
    public void testMoveCourse()
    {
        I_Requirement req = flowchart.getValueAt(0, 0);
        flowchart.addQuarter(1);
        flowchart.moveCourse(0, 0, 0, 1);
        assertEquals(req, flowchart.getValueAt(0, 1));
    }

    @Test
    public void testGetNumQuarters()
    {
        assertEquals(1, flowchart.getNumQuarters());
        flowchart.addQuarter(1);
        assertEquals(2, flowchart.getNumQuarters());

    }

    @Test
    public void testGetColumnCount()
    {
        assertEquals(1, flowchart.getColumnCount());
        flowchart.addQuarter(1);
        assertEquals(2, flowchart.getColumnCount());
    }

    @Test
    public void testGetRowCount()
    {
        assertEquals(7, flowchart.getRowCount());
    }

    @Test
    public void testGetColumnClass()
    {
        assertEquals(Requirement.class, flowchart.getColumnClass(0));
    }

    @Test
    public void testFindCurrentQuarter()
    {
        flowchart.getValueAt(0, 0).setCompleted(false);
        assertEquals(0, flowchart.findCurrentQuarter());
        flowchart.getValueAt(0, 0).setCompleted(true);
        assertEquals(-1, flowchart.findCurrentQuarter());
    }

    @Test
    public void testBuildPriorCourses()
    {
        HashSet<Contingent> set = flowchart.buildPriorCourses(0, false);
        assertEquals(0, set.size());
        flowchart.addQuarter(1);
        set = flowchart.buildPriorCourses(1, false);
        assertEquals(1, set.size());
    }

    @Test
    public void testInSequence()
    {
        assertTrue(flowchart.inSequence(flowchart.getValueAt(0, 0), 0));
    }

    @Test
    public void testIsEligible()
    {
        assertTrue(flowchart.isEligible(flowchart.getValueAt(0, 0), 0));
    }

    @Test
    public void testUpdateRequirement()
    {
        //??
    }

    @Test
    public void testGetRequirements()
    {
        List<I_Requirement> list = flowchart.getRequirements();
        assertEquals(1, list.size());
        assertEquals(reqList.get(0), list.get(0));
    }

    @Test
    public void testUnitsCompleted()
    {
        flowchart.getValueAt(0, 0).setCompleted(false);
        assertEquals(0, flowchart.unitsCompleted());
        flowchart.getValueAt(0, 0).setCompleted(true);
        assertEquals(4, flowchart.unitsCompleted());
    }
}
