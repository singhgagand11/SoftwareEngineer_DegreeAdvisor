package edu.calpoly.razsoftware;

import junit.framework.TestCase;
import java.util.HashSet;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import static org.junit.Assert.*;

/**
 * Tests to see if a Course isEligible function handles
 * multiple prerequisites. Defect #337
 * 
 * @author gagandeep
 */
public class CourseDefect
        extends TestCase
{
    /**
     * Defect #337
     *
     */
    public void testIsEligible()
    {
        Course csc102 = new Course(ImmutableList.of("CSC"), 102, 4, "Fund of CS 2", "CSC 102");
        Course csc101 = new Course(ImmutableList.of("CSC"), 101, 4, "Fund of CS 1", "CSC 101");
        Course math141 = new Course(ImmutableList.of("MATH"), 141, 4, "Calculus 1", "MATH 141");
        HashSet<Contingent> testSet = new HashSet<Contingent>();


        testSet.add(csc101);
        csc102.getPreRequisites().add(ImmutableSet.<I_Course>of(csc101, math141));


        assertFalse(csc102.isEligible(testSet));
        assertFalse(csc102.isEligible(new HashSet<Contingent>()));


        csc101.setCompleted(true);


        assertFalse(csc102.isEligible(testSet));
        testSet.add(math141);

        math141.setCompleted(true);

        assertTrue(csc102.isEligible(testSet));

    }
}
