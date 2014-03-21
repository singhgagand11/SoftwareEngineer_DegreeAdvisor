package edu.calpoly.razsoftware;

import edu.calpoly.razsoftware.renderer.CourseRendererIntTest;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Joe Walsh <jkwalsh@calpoly.edu>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
{
    edu.calpoly.razsoftware.FlowcharterViewTest.class, edu.calpoly.razsoftware.DegreechartReaderTest.class, edu.calpoly.razsoftware.UpdateReqDialogTest.class, edu.calpoly.razsoftware.DatabaseTest.class, edu.calpoly.razsoftware.CourseListTest.class, edu.calpoly.razsoftware.ControllerTest.class, edu.calpoly.razsoftware.CourseTest.class, edu.calpoly.razsoftware.CourseDetailsViewOldTest.class, edu.calpoly.razsoftware.FlowchartTest.class, edu.calpoly.razsoftware.RequirementTest.class
})
public class DegreeAdvisorIntTestSuite
{
    public static Test getSuite()
    {
        TestSuite suite = new TestSuite();

        // Add all the tests to the suite
        suite.addTestSuite(ControllerIntTest.class);
        suite.addTestSuite(FlowcharterViewIntTest.class);
        suite.addTestSuite(CourseDetailsViewOldIntTest.class);
        //suite.addTestSuite(CourseDetailsViewIntTest.class);
        suite.addTestSuite(DatabaseIntTest.class);
        suite.addTestSuite(FlowchartIntTest.class);
        //suite.addTestSuite(FlowcharterAppIntTest.class);
         suite.addTestSuite(FlowcharterViewIntTest.class);
        suite.addTestSuite(CourseRendererIntTest.class);
 
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
