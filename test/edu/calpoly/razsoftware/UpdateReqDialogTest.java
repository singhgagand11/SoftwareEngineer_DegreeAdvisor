/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.calpoly.razsoftware;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.awt.Component;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import junit.framework.TestCase;

/**
 *
 * @author george
 */
public class UpdateReqDialogTest extends TestCase
{
    private UpdateReqDialog instance;
    private Requirement req;
    private Course englishTest, pre, noPreReqTest;
    private Set<Set<I_Course>> preqs;

    public void setUp()
    {
        CourseList courseList = new CourseList();
        ArrayList<String> dept = new ArrayList<String>();
        dept.add("ENGL");
        englishTest = new Course(dept, 123, 4, "English Class", 
                "Course made for testing");
        noPreReqTest = new Course(dept, 123, 4, "English Class", 
                "Course made for testing");
        pre = new Course(dept, 101, 4, "English Class", 
                "Course made for testing");
        courseList.add(englishTest);
        Catalog catalog = new Catalog(courseList);
        Controller control = new Controller(catalog);
        instance = new UpdateReqDialog(new java.awt.Frame(), control,
                                       true);
        preqs = new HashSet<Set<I_Course>>();
        HashSet<I_Course> prereq = new HashSet<I_Course>();
        prereq.add(pre);
        preqs.add(prereq);
        englishTest.setPreRequisites(preqs);
        req = new Requirement(englishTest, 2);
        
        //DEFECT #295 - see manual tests on trac wiki
    }

    /**
     * Test of setRequirement method, of class UpdateReqDialog.
     */
    @Test
    public void testSetRequirement()
    {
        System.out.println("TEST: setRequirement");
        instance.setRequirement(req);
        String preReqs = instance.getPreReqsTest();
        String deptDir = instance.getDeptDirTest();
        assertEquals(preReqs.compareTo(req.getCourse().
                getPreRequisitesString()), 0);
        assertEquals(deptDir.compareTo(req.getDeptDirections()), 0);

        //#Defect #301 check to make sure no prereq display
        //Test Requirement with no preRequisites
        req = new Requirement(noPreReqTest, 2);
        instance.setRequirement(req);
        preReqs = instance.getPreReqsTest();
        deptDir = instance.getDeptDirTest();
        assertEquals("".compareTo(req.getCourse().getPreRequisitesString()), 0);

        
    }

    @Test
    public void testReturnStatus()
    {
        System.out.println("TEST: returnStatus and doClose");
        int a = instance.getReturnStatus();
        assertEquals(a, 0);
        instance.doClose(1);
        a = instance.getReturnStatus();
        assertEquals(a, 1);
    }

    @Test
    public void testOkButton()
    {
        System.out.println("TEST: OKButton");
        ActionEvent evt = new ActionEvent(new Object(), 2, "Test");
        instance.setRequirement(req);
        instance.okButtonTestable(evt);
        assertEquals(req.getCourse().isEligible((HashSet<Contingent>) 
                req.getPreRequisites()), false);

        // Now test after setting the satisfied check box
        // DEFECT #305 - make sure satisfied sets prereqs to done
        instance.setRequirement(req);
        instance.setSatisfied(true);
        instance.okButtonTestable(evt);
        assertEquals(req.getCourse().isEligible((HashSet<Contingent>) 
                req.getPreRequisites()), true);
    }
    /**
     * Test a requirement with prerequisites
     */
    @Test
    public void testReqWithPrereqs()
    {
        I_Course test2, test3;
        Requirement req2, hasPreReq;
        Set<Contingent> prereq;
        
        // init new reqs/courses with english variables 
        test2 = new Course(ImmutableList.of("English"), 
                111, 4, "English class", "Tasting this requirement");
        req2 = new Requirement("Take an english class", 
                ImmutableSet.of(test2), false, 1);
        // init prereq variable and add in req2
        prereq = new HashSet<Contingent>() ;
        prereq.add(req2);
        test3 = new Course(ImmutableList.of("2nd english"), 201, 4,
                "more english", "testing req with prereqs");
        // create requirement with prerequisites of req2
        hasPreReq = new Requirement("BigReq", ImmutableSet.of(test3), 
                false, 5, prereq, "This class requires english");
        instance.setRequirement(hasPreReq);
        assertEquals(instance.getPreReqsTest(), prereq.toString().
                substring(1,prereq.toString().length()-1));
    }
    
    @Test
    public void testNullReqDefect()
    {
        //DEFECT 344 UpdateReqDialog needs to check if blank cell
        // is selected
        instance.setRequirement(null);
        // insure nothing is getting set
        assertEquals(instance.getDeptDirTest(),null);
        // instance should never get initialized if null req
        assertFalse(instance.equals(null));
    }
}
