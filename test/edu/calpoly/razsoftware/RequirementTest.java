package edu.calpoly.razsoftware;

import junit.framework.TestCase;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;
import static org.junit.Assert.*;

public class RequirementTest extends TestCase
{
    @Test
    public void testConstructor()
    {
        I_Course c1, c2, c3;
        c1 = new Course(ImmutableList.of("CSC", "CPE"), 101, 4, "Fund of CS 1", "Learn C");
        c2 = new Course(ImmutableList.of("CSC", "CPE"), 102, 4, "Fund of CS 2", "Learn Java");
        c3 = new Course(ImmutableList.of("CSC", "CPE"), 103, 4, "Fund of CS 3", "Do Java Faster");

        Requirement co = new Requirement("Take a CS class", ImmutableSet.of(c1, c2, c3), false, 1);

        assertEquals("CSC101 or CSC102 or CSC103", co.toString());

        I_Course b1, b2, b3;
        b1 = new Course(ImmutableList.of("CSC", "CPE"), 201, 4, "Mastering CS 1", "Learn Compilers");
        b2 = new Course(ImmutableList.of("CSC", "CPE"), 202, 4, "Mastering CS 2", "Learn OS's");
        b3 = new Course(ImmutableList.of("CSC", "CPE"), 203, 4, "Mastering CS 3", "Learn OS's Faster");

        Set<Contingent> prereq = new HashSet<Contingent>() ;

        prereq.add(co);

        Requirement big = new Requirement("BigReq", ImmutableSet.of(b1, b2, b3), false, 5, prereq, "Take me, or drop out!");

        assertEquals(big.toString(), "CSC201 or CSC202 or CSC203");
        assertEquals(big.getDeptDirections(), "Take me, or drop out!");
        assertEquals(big.getPreRequisites(), prereq);
    }

    @Test
    public void quarterTest()
    {
        I_Course c1, c2, c3;
        c1 = new Course(ImmutableList.of("CSC", "CPE"), 101, 4, "Fund of CS 1", "Learn C");
        c2 = new Course(ImmutableList.of("CSC", "CPE"), 102, 4, "Fund of CS 2", "Learn Java");
        c3 = new Course(ImmutableList.of("CSC", "CPE"), 103, 4, "Fund of CS 3", "Do Java Faster");

        Requirement co = new Requirement("Take a CS class", ImmutableSet.of(c1, c2, c3), false, 1);

        assertEquals(co.getQuarter(), 1);
    }

    /**
     * Test of getDeptDirections method, of class Requirement.
     */
    @Test
    public void testGetDeptDirections()
    {
        System.out.println("getDeptDirections");
        Requirement instance = new Requirement(new Course(ImmutableList.of("CSC", "CPE"), 103, 4, "Fund of CS 3", "Do Java Faster"), 1);

        String expResult = "";
        String result = instance.getDeptDirections();
        assertEquals(expResult, result);

        instance.setDeptDirections("Take me!");

        assertEquals(instance.getDeptDirections(), "Take me!");
    }

    /**
     * Test of setDeptDirections method, of class Requirement.
     */
    @Test
    public void testSetDeptDirections()
    {
        System.out.println("setDeptDirections");
        Requirement instance = new Requirement(new Course(ImmutableList.of("CSC", "CPE"), 103, 4, "Fund of CS 3", "Do Java Faster"), 1);


        instance.setDeptDirections("Take me!");

        assertEquals(instance.getDeptDirections(), "Take me!");
    }

    /**
     * Test of isMutuallyExclusive method, of class Requirement.
     */
    @Test
    public void testIsMutuallyExclusive()
    {
        System.out.println("isMutuallyExclusive");
        Requirement instance = new Requirement(new Course(ImmutableList.of("CSC", "CPE"), 103, 4, "Fund of CS 3", "Do Java Faster"), 1);
        boolean expResult = true;
        boolean result = instance.isMutuallyExclusive();
        assertEquals(expResult, result);

        I_Course c1, c2, c3;
        c1 = new Course(ImmutableList.of("CSC", "CPE"), 101, 4, "Fund of CS 1", "Learn C");
        c2 = new Course(ImmutableList.of("CSC", "CPE"), 102, 4, "Fund of CS 2", "Learn Java");
        c3 = new Course(ImmutableList.of("CSC", "CPE"), 103, 4, "Fund of CS 3", "Do Java Faster");

        instance = new Requirement("Take a CS class", ImmutableSet.of(c1, c2, c3), false, 1);

        assertEquals(instance.isMutuallyExclusive(), false);

        instance = new Requirement("Take a CS class", ImmutableSet.of(c1, c2, c3), true, 1);

        assertEquals(instance.isMutuallyExclusive(), true);


    }

    /**
     * Test of getFulfillmentOptions method, of class Requirement.
     */
    @Test
    public void testGetFulfillmentOptions()
    {
        System.out.println("getFulfillmentOptions");
        Requirement instance = new Requirement(new Course(ImmutableList.of("CSC", "CPE"), 103, 4, "Fund of CS 3", "Do Java Faster"), 1);

        Set result = instance.getFulfillmentOptions();

        TreeSet<I_Course> tree = new TreeSet<I_Course>();

        tree.add(new Course(ImmutableList.of("CSC", "CPE"), 103, 4, "Fund of CS 3", "Do Java Faster"));

        assertEquals(tree, result);

        I_Course c1, c2, c3;
        c1 = new Course(ImmutableList.of("CSC", "CPE"), 101, 4, "Fund of CS 1", "Learn C");
        c2 = new Course(ImmutableList.of("CSC", "CPE"), 102, 4, "Fund of CS 2", "Learn Java");
        c3 = new Course(ImmutableList.of("CSC", "CPE"), 103, 4, "Fund of CS 3", "Do Java Faster");

        instance = new Requirement("Take a CS class", ImmutableSet.of(c1, c2, c3), false, 1);

        tree.clear();
        tree.add(c3);
        tree.add(c2);
        tree.add(c1);

        assertEquals(tree, instance.getFulfillmentOptions());

    }

    /**
     * Test of getTitle method, of class Requirement.
     */
    @Test
    public void testGetTitle()
    {
        System.out.println("getTitle");
        Requirement instance = new Requirement(new Course(ImmutableList.of("CSC", "CPE"), 103, 4, "Fund of CS 3", "Do Java Faster"), 1);

        assertEquals(instance.getTitle(), "CSC103");


        I_Course c1, c2, c3;
        c1 = new Course(ImmutableList.of("CSC", "CPE"), 101, 4, "Fund of CS 1", "Learn C");
        c2 = new Course(ImmutableList.of("CSC", "CPE"), 102, 4, "Fund of CS 2", "Learn Java");
        c3 = new Course(ImmutableList.of("CSC", "CPE"), 103, 4, "Fund of CS 3", "Do Java Faster");

        instance = new Requirement("Take a CS class", ImmutableSet.of(c1, c2, c3), false, 1);

        assertEquals(instance.getTitle(), "Take a CS class");

    }

    /**
     * Test of getCourse method, of class Requirement.
     */
    @Test
    public void testGetCourse()
    {
        System.out.println("getCourse");
        Course expResult = new Course(ImmutableList.of("CSC", "CPE"), 103, 4, "Fund of CS 3", "Do Java Faster");
        Requirement instance = new Requirement(expResult, 1);


        I_Course result = instance.getCourse();
        assertEquals(expResult, result);

        Course crs = new Course(ImmutableList.of("CSC", "CPE"), 102, 4, "Fund of CS 2", "Learn Java");

        instance.setCourse(crs);

        result = instance.getCourse();
        assertEquals(crs, result);
    }

    /**
     * Test of setCourse method, of class Requirement.
     */
    @Test
    public void testSetCourse()
    {
        System.out.println("setCourse");
        Course expResult = new Course(ImmutableList.of("CSC", "CPE"), 103, 4, "Fund of CS 3", "Do Java Faster");
        Requirement instance = new Requirement(expResult, 1);


        I_Course result = instance.getCourse();
        assertEquals(expResult, result);

        Course crs = new Course(ImmutableList.of("CSC", "CPE"), 102, 4, "Fund of CS 2", "Learn Java");

        instance.setCourse(crs);

        result = instance.getCourse();
        assertEquals(crs, result);
    }

    /**
     * Test of getQuarter method, of class Requirement.
     */
    @Test
    public void testGetQuarter()
    {
        System.out.println("getQuarter");
        Requirement instance = new Requirement(new Course(ImmutableList.of("CSC", "CPE"), 103, 4, "Fund of CS 3", "Do Java Faster"), 1);

        assertEquals(instance.getQuarter(), 1);

        I_Course c1, c2, c3;
        c1 = new Course(ImmutableList.of("CSC", "CPE"), 101, 4, "Fund of CS 1", "Learn C");
        c2 = new Course(ImmutableList.of("CSC", "CPE"), 102, 4, "Fund of CS 2", "Learn Java");
        c3 = new Course(ImmutableList.of("CSC", "CPE"), 103, 4, "Fund of CS 3", "Do Java Faster");

        instance = new Requirement("Take a CS class", ImmutableSet.of(c1, c2, c3), false, 0);

        assertEquals(instance.getQuarter(), 0);

    }

    /**
     * Test of inSequence method, of class Requirement.
     */
    @Test
    public void testInSequence()
    {
        System.out.println("inSequence");


        I_Requirement instance = new Requirement(new Course(ImmutableList.of("CSC", "CPE"), 103, 4, "Fund of CS 3", "Do Java Faster"), 1);
        I_Requirement prereq = new Requirement(new Course(ImmutableList.of("CSC", "CPE"), 102, 4, "Fund of CS 2", "Do Java"), 1);
        I_Requirement prior = prereq;

        Requirement csc102 = new Requirement(new Course(ImmutableList.of("CSC"),102, 4,"Fund of CS 2", "CSC 102"), 1);
        Course csc101 = new Course(ImmutableList.of("CSC"),101, 4,"Fund of CS 1", "CSC 101");
        Course math101 = new Course(ImmutableList.of("MATH"),101, 4,"Math for n00bs", "MATH 101");
        Course csc141 = new Course(ImmutableList.of("CSC"),141, 4,"Disc Struct", "CSC 141");
        HashSet<Contingent> testSet = new HashSet<Contingent>();


        csc102.setPreRequisites(new HashSet(new HashSet<HashSet<I_Course>>()));
        assertTrue(csc102.inSequence(testSet));


        csc102.getPreRequisites().add(csc101);

        testSet.add(csc101);

        assertTrue(csc102.inSequence(testSet));

        testSet.clear();
        testSet.add(math101);
        assertFalse(csc102.inSequence(testSet));

        testSet.add(csc101);

        assertTrue(csc102.inSequence(testSet));

        csc102.getPreRequisites().clear();
        assertTrue(csc102.inSequence(testSet));

        Course reqCourse = csc141;

        Requirement testReq = new Requirement(reqCourse, 1);

        testSet.add(testReq);

        assertTrue(csc102.inSequence(testSet));


        HashSet<Contingent> prereqSet = new HashSet<Contingent>();
        prereqSet.add(prereq);

        HashSet<Contingent> priorSet = new HashSet<Contingent>();
        priorSet.add(prior);


        instance.setPreRequisites(prereqSet);

        assertTrue(instance.inSequence(priorSet));

        priorSet.clear();
        assertFalse(instance.inSequence(priorSet));


        //Course and Requirement test
        prereqSet.add(csc101);
        instance.setPreRequisites(prereqSet);
        assertFalse(instance.inSequence(priorSet));

        priorSet = prereqSet;
        assertTrue(instance.inSequence(priorSet));

        priorSet.add(csc101);
        priorSet.add(math101);
        priorSet.add(csc141);
        priorSet.add(instance);

        assertTrue(instance.inSequence(priorSet));
    }

    /**
     * Test of isEligible method, of class Requirement.
     */
    @Test
    public void testIsEligible()
    {
        System.out.println("isEligible");

        Requirement instance = new Requirement(new Course(ImmutableList.of("CSC", "CPE"), 103, 4, "Fund of CS 3", "Do Java Faster"), 1);
        Requirement prereq = new Requirement(new Course(ImmutableList.of("CSC", "CPE"), 102, 4, "Fund of CS 2", "Do Java"), 1);
        Requirement prereq2 = new Requirement(new Course(ImmutableList.of("CSC", "CPE"), 101, 4, "Fund of CS 1", "Do C"), 1);

        HashSet<Contingent> prereqSet = new HashSet<Contingent>();
        prereqSet.add(prereq);

        HashSet<Contingent> priorSet = new HashSet<Contingent>();
        priorSet.add(prereq);

        //Empty test
        assertTrue(instance.isEligible(priorSet));

        //Single, uncompleted prereq test
        instance.setPreRequisites(prereqSet);
        assertFalse(instance.isEligible(priorSet));

        prereq.setCompleted(true);
        prereqSet.clear();
        priorSet.add(prereq);

        //Single, completed prereq test
        assertTrue(instance.isEligible(priorSet));

        //Single prereq, two priors
        priorSet.add(prereq2);

        assertTrue(instance.isEligible(priorSet));

        prereqSet.add(prereq2);
        instance.setPreRequisites(prereqSet);
        assertFalse(instance.isEligible(priorSet));

        priorSet.clear();

        assertFalse(instance.isEligible(prereqSet));

        Course cs = new Course(ImmutableList.of("CSC", "CPE"), 101, 4, "Fund of CS 1", "Do C");
        cs.setCompleted(true);
        prereqSet.add(cs);

        assertFalse(instance.isEligible(prereqSet));

        //Course tests
        Requirement csc102 = new Requirement(new Course(ImmutableList.of("CSC"),102, 4,"Fund of CS 2", "CSC 102"), 1);
        Course csc101 = new Course(ImmutableList.of("CSC"),101, 4,"Fund of CS 1", "CSC 101");

        HashSet<Contingent> testSet = new HashSet<Contingent>();


        testSet.add(csc101);
        csc102.getPreRequisites().add(csc101);

        assertFalse(csc102.isEligible(testSet));
        assertFalse(csc102.isEligible(new HashSet<Contingent>()));

        testSet.clear();
        csc101.setCompleted(true);
        testSet.add(csc101);

        assertTrue(csc102.isEligible(testSet));

        csc102.setPreRequisites(new HashSet<Contingent>());
        assertTrue(csc102.isEligible(testSet));
    }

    /**
     * Test of toString method, of class Requirement.
     */
    @Test
    public void testToString()
    {
        System.out.println("toString");
        Requirement instance = new Requirement(new Course(ImmutableList.of("CSC", "CPE"), 103, 4, "Fund of CS 3", "Do Java Faster"), 1);

        assertEquals(instance.toString(), "CSC103");


        I_Course c1, c2, c3;
        c1 = new Course(ImmutableList.of("CSC", "CPE"), 101, 4, "Fund of CS 1", "Learn C");
        c2 = new Course(ImmutableList.of("CSC", "CPE"), 102, 4, "Fund of CS 2", "Learn Java");
        c3 = new Course(ImmutableList.of("CSC", "CPE"), 103, 4, "Fund of CS 3", "Do Java Faster");

        instance = new Requirement("Take a CS class", ImmutableSet.of(c1, c2, c3), false, 1);

        assertEquals(instance.toString(), "CSC101 or CSC102 or CSC103");
    }

    /**
     * Test of isCompleted method, of class Requirement.
     */
    @Test
    public void testIsCompleted()
    {
        System.out.println("isCompleted");
        I_Requirement instance = new Requirement(new Course(ImmutableList.of("CSC", "CPE"), 103, 4, "Fund of CS 3", "Do Java Faster"), 1);

        boolean expResult = false;
        boolean result = instance.isCompleted();
        assertEquals(expResult, result);

        instance.setCompleted(true);

        assertEquals(instance.isCompleted(), true);
    }

    /**
     * Test of setCompleted method, of class Requirement.
     */
    @Test
    public void testSetCompleted()
    {
        System.out.println("setCompleted");

        Requirement instance = new Requirement(new Course(ImmutableList.of("CSC", "CPE"), 103, 4, "Fund of CS 3", "Do Java Faster"), 1);

        assertFalse(instance.isCompleted());

        instance.setCompleted(true);

        instance.setCompleted(true);
        assertTrue(instance.isCompleted());
    }

    /**
     * Test of getPreRequisites method, of class Requirement.
     */
    @Test
    public void testGetPreRequisites()
    {
        System.out.println("getPreRequisites");

        I_Requirement instance = new Requirement(new Course(ImmutableList.of("CSC", "CPE"), 103, 4, "Fund of CS 3", "Do Java Faster"), 1);

        assertTrue(instance.getPreRequisites().isEmpty());

        Requirement prereq = new Requirement(new Course(ImmutableList.of("CSC", "CPE"), 102, 4, "Fund of CS 2", "Do Java"), 1);
        Requirement prereq2 = new Requirement(new Course(ImmutableList.of("CSC", "CPE"), 101, 4, "Fund of CS 1", "Do C"), 1);

        Set<Contingent> prereqSet = new HashSet<Contingent>();
        prereqSet.add(prereq);

        instance.setPreRequisites(prereqSet);
        assertEquals(instance.getPreRequisites(), prereqSet);

        instance.setPreRequisites(null);
        assertFalse(prereqSet.equals(instance.getPreRequisites()));
    }

    /**
     * Test of setPreRequisites method, of class Requirement.
     */
    @Test
    public void testSetPreRequisites()
    {
        System.out.println("setPreRequisites");

        I_Requirement instance = new Requirement(new Course(ImmutableList.of("CSC", "CPE"), 103, 4, "Fund of CS 3", "Do Java Faster"), 1);

        assertTrue(instance.getPreRequisites().isEmpty());

        I_Requirement prereq = new Requirement(new Course(ImmutableList.of("CSC", "CPE"), 102, 4, "Fund of CS 2", "Do Java"), 1);
        I_Requirement prereq2 = new Requirement(new Course(ImmutableList.of("CSC", "CPE"), 101, 4, "Fund of CS 1", "Do C"), 1);

        Set<Contingent> prereqSet = new HashSet<Contingent>();
        prereqSet.add(prereq);

        instance.setPreRequisites(prereqSet);
        assertEquals(instance.getPreRequisites(), prereqSet);

        instance.setPreRequisites(null);
        assertFalse(prereqSet.equals(instance.getPreRequisites()));
    }
}
