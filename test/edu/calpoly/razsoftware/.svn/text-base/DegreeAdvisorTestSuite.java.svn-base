/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.calpoly.razsoftware;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import edu.calpoly.razsoftware.renderer.CourseRendererTest;

/**
 *
 * @author Joe Walsh <jkwalsh@calpoly.edu>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
{
    edu.calpoly.razsoftware.FlowcharterViewTest.class, edu.calpoly.razsoftware.DegreechartReaderTest.class, edu.calpoly.razsoftware.UpdateReqDialogTest.class, edu.calpoly.razsoftware.DatabaseTest.class, edu.calpoly.razsoftware.CourseListTest.class, edu.calpoly.razsoftware.ControllerTest.class, edu.calpoly.razsoftware.CourseTest.class, edu.calpoly.razsoftware.CourseDetailsViewOldTest.class, edu.calpoly.razsoftware.FlowchartTest.class, edu.calpoly.razsoftware.RequirementTest.class
})
public class DegreeAdvisorTestSuite
{
    public static Test getSuite()
    {
        TestSuite suite = new TestSuite();

        // Add all the tests to the suite
        suite.addTestSuite(ControllerDefect.class);
        suite.addTestSuite(ControllerTest.class);
        suite.addTestSuite(CourseDefect.class);
        suite.addTestSuite(CourseDetailsViewOldTest.class);
        //UI SPEC TEST suite.addTestSuite(CourseDetailsViewTest.class);
        suite.addTestSuite(CourseListTest.class);
        suite.addTestSuite(CourseTest.class);
        
        suite.addTestSuite(DatabaseTest.class);
        suite.addTestSuite(DegreechartReaderTest.class);
        suite.addTestSuite(FlowchartTest.class);
        suite.addTestSuite(FlowcharterViewTest.class);
        suite.addTestSuite(RequirementTest.class);
        suite.addTestSuite(UpdateReqDialogTest.class);
        suite.addTestSuite(CourseRendererTest.class);
        suite.addTestSuite(CreatorTest.class);

        return suite;
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
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }

    /** Runs the tests using a graphical user interface. */
    public static void main(String[] args)
    {
        // Run all the tests in the suite
        junit.textui.TestRunner.run(getSuite());
    }
}
