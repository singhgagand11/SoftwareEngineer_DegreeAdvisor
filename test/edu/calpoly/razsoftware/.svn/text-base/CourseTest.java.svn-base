
package edu.calpoly.razsoftware;


import junit.framework.TestCase;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;
import static org.junit.Assert.*;

public class CourseTest extends TestCase
{
    @Test
    public void testConstructor()
    {
        Course c = new Course(ImmutableList.of("CSC", "CPE"), 101, 4,
                              "Fund of CS 1","Hello, World!");
        assertEquals(ImmutableList.of("CSC", "CPE"), c.getDept());
        assertEquals(101, c.getNumber());
        assertEquals("Fund of CS 1", c.getTitle());
        assertEquals(4, c.getUnits());
        assertEquals("Hello, World!", c.getDescription());
        assertEquals(ImmutableSet.<Set<I_Course>>of(), c.getPreRequisites());
        assertEquals(ImmutableSet.<Set<I_Course>>of(), c.getCoRequisites());
        assertEquals("CSC101",c.toString());
        assertEquals("",c.getPreRequisitesString());
    }
    
    @Test
    public void testEmptyPrerequisitesShouldAlwaysMatch()
    {
        final Course c1 = new Course(ImmutableList.of("CSC"), 101, 4,"Fund of CS 1", "101");
        final Course c2 = new Course(ImmutableList.of("CSC"), 102, 4,"Fund of CS 2", "102");

        assertTrue(c1.preRecsMet(ImmutableSet.<I_Course>of()));
        assertTrue(c1.preRecsMet(ImmutableSet.<I_Course>of(c2)));
    }

    @Test
    public void testEmptyShouldNotMatch()
    {
        final Course c1 = new Course(ImmutableList.of("CSC"), 101, 4, "Fund of CS 1", "101");
        final Course c2 = new Course(ImmutableList.of("CSC"), 102, 4, "Fund of CS 2", "102");

        c2.getPreRequisites().add(ImmutableSet.<I_Course>of(c1));

        assertFalse(c2.preRecsMet(ImmutableSet.<I_Course>of()));
    }

    @Test
    public void testSubsetShouldNotMatch()
    {
        final Course c1 = new Course(ImmutableList.of("CSC"), 141, 4, "Fund of CS 1", "141");
        final Course c2 = new Course(ImmutableList.of("CSC"), 102, 4, "Fund of CS 2", "102");
        final Course c3 = new Course(ImmutableList.of("CSC"), 103, 4, "Fund of CS 3", "103");

        c3.getPreRequisites().add(ImmutableSet.<I_Course>of(c1, c2));

        assertFalse(c3.preRecsMet(ImmutableSet.<I_Course>of(c1)));
    }

    @Test
    public void testExactShouldMatch()
    {
        final Course c1 = new Course(ImmutableList.of("CSC"), 101, 4, "Fund of CS 1", "101");
        final Course c2 = new Course(ImmutableList.of("CSC"), 102, 4, "Fund of CS 2", "102");

        c2.getPreRequisites().add(ImmutableSet.<I_Course>of(c1));

        assertTrue(c2.preRecsMet(ImmutableSet.<I_Course>of(c1)));
        assertEquals("CSC101", c2.getPreRequisitesString());
    }

    @Test
    public void testSupersetShouldMatch()
    {
        final Course c1 = new Course(ImmutableList.of("CSC"), 101, 4,"Fund of CS 1",  "101");
        final Course c2 = new Course(ImmutableList.of("CSC"), 102, 4,"Fund of CS 2", "102");
        final Course c3 = new Course(ImmutableList.of("CSC"), 141, 4,"Fund of CS 3", "141");

        c2.getPreRequisites().add(ImmutableSet.<I_Course>of(c1));

        assertTrue(c2.preRecsMet(ImmutableSet.<I_Course>of(c1, c3)));
    }

    @Test
    public void testOptionShouldMatch()
    {
        final Course m1 = new Course(ImmutableList.of("MATH"),101, 4,"Calc 1", "MATH 101");
        final Course c1 = new Course(ImmutableList.of("CSC"),101, 4,"Fund of CS 1", "CSC 101");
        final Course c2 = new Course(ImmutableList.of("CSC"),102, 4,"Fund of CS 2", "CSC 102");
        final Course c3 = new Course(ImmutableList.of("CSC"),103, 4,"Fund of CS 3", "CSC 103");

        c3.getPreRequisites().add(ImmutableSet.<I_Course>of(m1,c1));
        c3.getPreRequisites().add(ImmutableSet.<I_Course>of(m1,c2));

        assertFalse(c3.preRecsMet(ImmutableSet.<I_Course>of()));
        assertFalse(c3.preRecsMet(ImmutableSet.<I_Course>of(m1)));
        assertFalse(c3.preRecsMet(ImmutableSet.<I_Course>of(c1)));
        assertFalse(c3.preRecsMet(ImmutableSet.<I_Course>of(c2)));
        assertTrue(c3.preRecsMet(ImmutableSet.<I_Course>of(m1, c1)));
        assertTrue(c3.preRecsMet(ImmutableSet.<I_Course>of(m1, c2)));
        assertFalse(c3.preRecsMet(ImmutableSet.<I_Course>of(c1, c2)));
        assertTrue(c3.preRecsMet(ImmutableSet.<I_Course>of(m1, c1, c2)));

        assertTrue(c3.getPreRequisitesString().equals("CSC101&MATH101 or CSC102&MATH101") ||
                   c3.getPreRequisitesString().equals("CSC102&MATH101 or CSC101&MATH101") ||
                   c3.getPreRequisitesString().equals("MATH101&CSC101 or CSC102&MATH101") ||
                   c3.getPreRequisitesString().equals("MATH101&CSC102 or CSC101&MATH101") ||
                   c3.getPreRequisitesString().equals("CSC101&MATH101 or MATH101&CSC102") ||
                   c3.getPreRequisitesString().equals("CSC102&MATH101 or MATH101&CSC101") ||
                   c3.getPreRequisitesString().equals("MATH101&CSC101 or MATH101&CSC102") ||
                   c3.getPreRequisitesString().equals("MATH101&CSC102 or MATH101&CSC101") );
    }

    @Test
    public void testCompareTo()
    {
        final Course m1 = new Course(ImmutableList.of("MATH"),101, 4,"Calc 1", "MATH 101");
        final Course c1 = new Course(ImmutableList.of("CSC"),101, 4,"Fund of CS 1", "CSC 101");
        final Course c2 = new Course(ImmutableList.of("CSC"),102, 4,"Fund of CS 2", "CSC 102");

        assertTrue(c1.compareTo(c2) < 0);
        assertTrue(c2.compareTo(c1) > 0);
        assertTrue(m1.compareTo(c1) > 0);

    }

    @Test
    public void testIsComplete()
    {
       Course crs = new Course(ImmutableList.of("MATH"),101, 4,"Calc 1", "MATH 101");

       assertFalse(crs.isComplete());

       crs.setCompleted(true);

       assertTrue(crs.isComplete());

       crs.setCompleted(false);

       assertFalse(crs.isComplete());
    }

    @Test
    public void testInSequence()
    {
        Course csc102 = new Course(ImmutableList.of("CSC"),102, 4,"Fund of CS 2", "CSC 102");
        Course csc101 = new Course(ImmutableList.of("CSC"),101, 4,"Fund of CS 1", "CSC 101");
        Course math101 = new Course(ImmutableList.of("MATH"),101, 4,"Math for n00bs", "MATH 101");
        Course csc141 = new Course(ImmutableList.of("CSC"),141, 4,"Disc Struct", "CSC 141");
        HashSet<Contingent> testSet = new HashSet<Contingent>();


        csc102.setPreRequisites(new HashSet(new HashSet<HashSet<I_Course>>()));
        assertTrue(csc102.inSequence(testSet));


        csc102.getPreRequisites().add(ImmutableSet.<I_Course>of(csc101));

        testSet.add(csc101);

        assertTrue(csc102.inSequence(testSet));

        testSet.clear();
        testSet.add(math101);
        assertFalse(csc102.inSequence(testSet));
        
        //DEFECT #305 check to make sure updatereqdialog overrides prereqs
        csc102.setEligible(true);
        assertTrue(csc102.inSequence(testSet));
        // make sure it doesn't remove prereqs
        csc102.setEligible(false);
        assertFalse(csc102.inSequence(testSet));

        testSet.add(csc101);

        assertTrue(csc102.inSequence(testSet));

        csc102.getPreRequisites().clear();
        assertTrue(csc102.inSequence(testSet));

        Course reqCourse = csc141;

        Requirement testReq = new Requirement(reqCourse, 1);

        testSet.add(testReq);

        assertTrue(csc102.inSequence(testSet));
    }

      @Test
    public void testIsEligible()
    {
        Course csc102 = new Course(ImmutableList.of("CSC"),102, 4,"Fund of CS 2", "CSC 102");
        Course csc101 = new Course(ImmutableList.of("CSC"),101, 4,"Fund of CS 1", "CSC 101");

        HashSet<Contingent> testSet = new HashSet<Contingent>();


        testSet.add(csc101);
        csc102.getPreRequisites().add(ImmutableSet.<I_Course>of(csc101));

        assertFalse(csc102.isEligible(testSet));
        assertFalse(csc102.isEligible(new HashSet<Contingent>()));
        
        //DEFECT #305 check to make sure updatereqdialog overrides prereqs
        csc102.setEligible(true);
        assertTrue(csc102.isEligible(testSet));
        // make sure it doesn't remove prereqs
        csc102.setEligible(false);
        assertFalse(csc102.isEligible(testSet));
        
        testSet.clear();
        csc101.setCompleted(true);
        testSet.add(csc101);

        assertTrue(csc102.isEligible(testSet));

        csc102.setPreRequisites(new HashSet<Set<I_Course>>());
        assertTrue(csc102.isEligible(testSet));
        
    }

  @Test
    public void testToString()
    {
        Course csc102 = new Course(ImmutableList.of("CSC"),102, 4,"Fund of CS 2", "CSC 102");

        assertEquals(csc102.toString(), "CSC102");
    }

  @Test
    public void testGetUnits()
    {
        Course csc102 = new Course(ImmutableList.of("CSC"),102, 4,"Fund of CS 2", "CSC 102");

        assertEquals(csc102.getUnits(), 4);
    }

  @Test
    public void testGetNumber()
    {
        Course csc102 = new Course(ImmutableList.of("CSC"),102, 4,"Fund of CS 2", "CSC 102");

        assertEquals(csc102.getNumber(), 102);
    }

  @Test
    public void testGetDept()
    {
        Course csc102 = new Course(ImmutableList.of("CSC"),102, 4,"Fund of CS 2", "CSC 102");

        List<String> depts = new ArrayList<String>();

        depts.add("CSC");

        assertEquals(csc102.getDept(), depts);

        Course csc103 = new Course(ImmutableList.of("CSC", "CPE"),103, 4,"Fund of CS 3", "This is the description");

        depts.add("CPE");

        assertEquals(csc103.getDept(), depts);
    }

  @Test
    public void testDescription()
    {
        Course csc102 = new Course(ImmutableList.of("CSC"),102, 4,"Fund of CS 2", "This is the description");

        assertEquals(csc102.getDescription(), "This is the description");
    }

  @Test
    public void testGetTitle()
    {
        Course csc102 = new Course(ImmutableList.of("CSC"),102, 4,"Fund of CS 2", "This is the description");

        assertEquals(csc102.getTitle(), "Fund of CS 2");
    }

  @Test
    public void testCoRequisites()
    {
        Course csc102 = new Course(ImmutableList.of("CSC"),102, 4,"Fund of CS 2", "This is the description");
        Course csc101 = new Course(ImmutableList.of("CSC"),101, 4,"Fund of CS 1", "CSC 101");

        HashSet<I_Course> testSet = new HashSet<I_Course>();

        testSet.add(csc101);

        Set<Set<I_Course>> coreqs = new HashSet<Set<I_Course>>();

        coreqs.add(testSet);

        csc102.setCoRequisites(coreqs);

        assertEquals(csc102.getCoRequisites(), coreqs);
    }

  @Test
    public void testPreRequisites()
    {
        Course csc102 = new Course(ImmutableList.of("CSC"),102, 4,"Fund of CS 2", "This is the description");
        Course csc101 = new Course(ImmutableList.of("CSC"),101, 4,"Fund of CS 1", "CSC 101");

        HashSet<I_Course> testSet = new HashSet<I_Course>();

        testSet.add(csc101);

        Set<Set<I_Course>> prereqs = new HashSet<Set<I_Course>>();

        prereqs.add(testSet);

        csc102.setPreRequisites(prereqs);

        assertEquals(csc102.getPreRequisites(), prereqs);
    }

  @Test
    public void testIsClass()
    {
        Course csc102 = new Course(ImmutableList.of("CSC", "CPE"),102, 4,"Fund of CS 2", "This is the description");
        Course csc101 = new Course(ImmutableList.of("CSC"),101, 4,"Fund of CS 1", "CSC 101");

        assertTrue(csc102.isClass("CSC", 102));

        assertFalse(csc102.isClass("CRP", 102));

        assertTrue(csc102.isClass("CPE", 102));

        assertFalse(csc102.isClass("CPE", 101));
    }

  @Test
    public void testGetName()
    {
        Course csc102 = new Course(ImmutableList.of("CSC", "CPE"),102, 4,"Fund of CS 2", "This is the description");
        Course csc101 = new Course(ImmutableList.of("CSC"),101, 4,"Fund of CS 1", "CSC 101");

        assertEquals(csc102.getName(), "CSC102");

        assertEquals(csc101.getName(), "CSC101");
    }
}
