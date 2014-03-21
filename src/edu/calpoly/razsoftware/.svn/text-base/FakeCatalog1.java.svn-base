package edu.calpoly.razsoftware;

import java.util.List;
import java.util.Set;

/**
 * Used for the ControllerTest
 * @author Joe Walsh <jkwalsh@calpoly.edu>
 */
public class FakeCatalog1 implements I_Catalog
{
    List<I_Course> courses1;
    List<I_Course> courses2;
    public FakeCatalog1()
    {
        List<String> depts1 = new java.util.ArrayList<String>();
        depts1.add("CPE");
        depts1.add("CSC");
        courses1 = new java.util.ArrayList<I_Course>();
        courses1.add(new Course(depts1, 102, 4, "Intro to Programming", "DummyDesc"));
        courses1.add(new Course(depts1, 101, 4, "Intro to Programming 2", "DummyDesc"));
        List<String> depts2 = new java.util.ArrayList<String>();
        depts2.add("HUM");
        courses2 = new java.util.ArrayList<I_Course>();
        courses2.add(new Course(depts2, 310, 4, "HUM", "DummyDesc"));
    }
    public CourseList getAllCourses()
    {
        List<I_Course> list = new java.util.ArrayList<I_Course>();
        list.addAll(courses1);
        list.addAll(courses2);
        return new CourseList(list);
    }

    public List<I_Course> getCoursesByDept(String dept)
    {
        if (dept.equals("CPE"))
        {
            return courses1;
        }
        else
        {
            return courses2;
        }
    }

    public Set<String> getDeptNames()
    {
        Set<String> names = new java.util.TreeSet<String>();
        names.add("CPE");
        names.add("CSC");
        names.add("HUM");
        return names;
    }

    public String toOutputString()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

