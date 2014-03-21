/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.calpoly.razsoftware;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author george
 */
public class CatalogTest
{
    private Course englishTest, noPreReqTest, pre;
    private Catalog catalog;
    private CourseList courseList;
    ArrayList<String> dept;
    List<I_Course> expRes;
    
    public CatalogTest()
    {
        courseList = new CourseList();
        dept = new ArrayList<String>();
        dept.add("ENG");
        englishTest = new Course(dept, 123, 4, "English Class", 
                "Course made for testing");
        courseList.add(englishTest);   
        expRes = new ArrayList<I_Course>();
        expRes.add(englishTest);
        catalog = new Catalog(courseList);
    }


    /**
     * Test of getAllCourses method, of class Catalog.
     */
    @Test
    public void testGetAllCourses()
    {
        System.out.println("getAllCourses");
        //catalog = new Catalog(courseList);
        CourseList result = catalog.getAllCourses();
        assertEquals(courseList, result);
    }

    /**
     * Test of getDeptNames method, of class Catalog.
     */
    @Test
    public void testGetDeptNames()
    {
        System.out.println("getDeptNames");
        Set result = catalog.getDeptNames();
        assertTrue(result.toString().contains("ENG"));
    }

    /**
     * Test of toString method, of class Catalog.
     */
    @Test
    public void testToString()
    {
        System.out.println("toString");
        String result = catalog.toString();
        assertTrue(catalog.toString().contains("CourseList"));
    }

    /**
     * Test of toOutputString method, of class Catalog.
     */
    @Test
    public void testToOutputString()
    {
        System.out.println("toOutputString");
        String expResult = "ENG123";
        String result = catalog.toOutputString();
        assertTrue(result.contains(expResult));
    }
}
