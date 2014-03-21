package edu.calpoly.razsoftware;

import java.util.HashSet;
import java.util.List;
import junit.framework.TestCase;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Integration test for Flowchart.java
 * @author jkwalsh
 */
public class FlowchartIntTest extends TestCase
{
    private I_Course course;
    private List<I_Course> courses;
    private I_Catalog catalog;
    private Flowchart flowchart;

    public FlowchartIntTest()
    {
        List<String> depts1 = new java.util.ArrayList<String>();
        depts1.add("CPE");
        depts1.add("CSC");
        List<String> depts2 = new java.util.ArrayList<String>();
        depts2.add("HUM");
        courses = new java.util.ArrayList<I_Course>();
        course = new Course(depts1, 101, 4, "CompSci 101", "DummyDesc");
        courses.add(course);
        courses.add(new Course(depts1, 102));
        courses.add(new Course(depts2, 310));
        catalog = new Catalog(new CourseList(courses));
        //{"dept":["CSC"],"number":0,"units":0,"title":"title","description":"desc","preRequisites":[],"coRequisites":[],"completed":false}
        //{"dept":["CSC"],"number":101,"units":0,"name":"NA","description":"NA","preRequisites":[],"coRequisites":[]}
        flowchart = new Flowchart(catalog);
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
        flowchart = new Flowchart(catalog);
        // default number of quarters should be 12
        int expected = 12;
        assertEquals(expected, flowchart.getNumQuarters());
        flowchart.addQuarter(1);
        // we've added a quarter, should now have 13
        expected = 13;
        assertEquals(expected, flowchart.getNumQuarters());

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
        flowchart = new Flowchart(catalog);
        // default number of quarters should be 12
        int expected = 12;
        assertEquals(expected, flowchart.getNumQuarters());
        flowchart.deleteQuarter(0);
        // we've deleted a quarter, should now have 11
        expected = 11;
        assertEquals(expected, flowchart.getNumQuarters());
    }

    @Test
    public void testGetList()
    {
        flowchart = new Flowchart(catalog);
        List<I_Requirement> list = flowchart.getList(0);
        // Catalog was given three dummy courses: should have list of size 3
        int expectedSize = 3;
        assertEquals(expectedSize, list.size());
        // Check to make sure first course matches
        assertEquals(course, list.get(0).getCourse());
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
        assertEquals(course, flowchart.getValueAt(0, 1).getCourse());
    }

    @Test
    public void testMoveCourse()
    {
        flowchart.moveCourse(0, 0, 0, 1);
        assertEquals(course, flowchart.getValueAt(0, 1).getCourse());
    }

    @Test
    public void testGetNumQuarters()
    {
        flowchart = new Flowchart(catalog);
        int expected = 12;
        assertEquals(expected, flowchart.getNumQuarters());
        flowchart.addQuarter(1);
        expected = 13;
        assertEquals(expected, flowchart.getNumQuarters());

    }

    @Test
    public void testGetColumnCount()
    {
        flowchart = new Flowchart(catalog);
        int expected = 12;
        assertEquals(expected, flowchart.getColumnCount());
        flowchart.addQuarter(1);
        expected = 13;
        assertEquals(expected, flowchart.getColumnCount());
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
        flowchart.getValueAt(1, 0).setCompleted(true);
        flowchart.getValueAt(2, 0).setCompleted(true);
        assertEquals(1, flowchart.findCurrentQuarter());
    }

    @Test
    public void testBuildPriorCourses()
    {
        HashSet<Contingent> set = flowchart.buildPriorCourses(0, false);
        assertEquals(0, set.size());
        flowchart.addQuarter(1);
        set = flowchart.buildPriorCourses(1, false);
        assertEquals(3, set.size());
    }

    @Test
    public void testInSequence()
    {
        flowchart = new Flowchart(catalog);
        assertTrue(flowchart.inSequence(flowchart.getValueAt(0, 0), 0));
    }

    @Test
    public void testIsEligible()
    {
        flowchart = new Flowchart(catalog);
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
        flowchart = new Flowchart(catalog);
        List<I_Requirement> list = flowchart.getRequirements();
        // There should be 51 requirements in the flowchart
        // BECAUSE OF FREE ELEC
        assertEquals(51, list.size());
        assertEquals(course, list.get(0).getCourse());
    }

    @Test
    public void testUnitsCompleted()
    {
        I_Requirement req = flowchart.getValueAt(0, 0);
        req.setCompleted(false);
        assertEquals(0, flowchart.unitsCompleted());
        req.setCompleted(true);
        assertEquals(4, flowchart.unitsCompleted());
    }
}
