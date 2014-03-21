/*
 * Test for the DegreeChartReader Class
 * George Donegan - Team pineApple
 */
package edu.calpoly.razsoftware;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import junit.framework.TestCase;

/**
 *
 * @author george
 */
public class DegreechartReaderTest extends TestCase
{
    private UpdateReqDialog instance;
    private Requirement req;
    private Course courseTest;
    private Set<Set<I_Course>> preqs;
    private List<I_Requirement> degreeChart;
    private List<I_Requirement> degreeChartTest;
    private CourseList courseList;
    
    public DegreechartReaderTest()
    {
        courseList = new CourseList(getClass().getResourceAsStream("Catalog.json"));
        Catalog catalog = new Catalog(courseList);
        degreeChart  =
                DegreechartReader.readFlowchart(
                        getClass().getResourceAsStream("DegreeChart.json"),
                        courseList);
        degreeChartTest  =
                DegreechartReader.readFlowchart(
                        getClass().getResourceAsStream("DegreeChart.json"),
                        courseList);
    }

    /**
     * Test of readFlowchart method, of class DegreechartReader.
     */
    @Test
    public void testReadFlowchart()
    {
        System.out.println("readFlowchart");
        assertEquals(degreeChart.isEmpty(),false);
        // Test to make sure that the requirements getting added are the same as 
        // the requirements youre looking to add.
        assertEquals(degreeChart.toString().contains(degreeChartTest.get(0).toString()),true);
        assertEquals(degreeChart.toString().contains(degreeChartTest.get(1).toString()),true);
    }
}
