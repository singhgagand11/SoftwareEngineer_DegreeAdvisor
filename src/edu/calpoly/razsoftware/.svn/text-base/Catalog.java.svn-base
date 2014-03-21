package edu.calpoly.razsoftware;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Catalog represents the university's catalog of course offerings.
 * @author jdalbey
 */
public class Catalog implements I_Catalog
{
    /** Size of a department name. */
    public static final int kDeptNameSize = 3;
    private CourseList catalog;

    /**
     * Creates a catalog using a list of courses.
     * @param catalog the possible courses
     */
    public Catalog(CourseList catalog)
    {
        this.catalog = catalog;
    }

    /**
     * Gets the underlying list of courses for the catalog.
     * @return list of courses
     */
    @Override
    public CourseList getAllCourses()
    {
        return catalog;
    }

    /** Return all the course names in the catalog for a specific department.
     * @param dept The name of the department to search
     * @return a list of all courses under the department
     */
    @Override
    public List<I_Course> getCoursesByDept(String dept)
    {
        ArrayList<I_Course> list = new ArrayList<I_Course>();
        // FOR all courses
        for (I_Course course : catalog.getCourses())
        {
            // for now, assume all course numbers are 3 digit
            String prefix = course.getTitle().substring(0,
                    course.getTitle().length() - kDeptNameSize);

            // IF course is of selected department
            if (prefix.equals(dept))
            {
                list.add(course);
            }
        }
        return list;
    }

    /**
     * Gets a set of all names the names of all departments, ie CSC, CPE.
     * @return a set of all department names.
     */
    @Override
    public Set<String> getDeptNames()
    {
        TreeSet<String> names = new TreeSet<String>();
        ArrayList<Course> list = new ArrayList<Course>();
        // FOR all courses
        for (I_Course course : catalog.getCourses())
        {
            // Needs to return .getName, not .getTitle
            String prefix = course.getName().substring(0,
                    course.getName().length() - kDeptNameSize);
            names.add(prefix);
        }
        return names;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String toString()
    {
        return catalog.toString();
    }

    /**
     * Creates a formatted string, with each course on a newline.
     * @return a formatted string.
     */
    @Override
    public String toOutputString()
    {
        StringBuilder result = new StringBuilder();
        // FOR all courses
        for (I_Course crs : catalog.getCourses())
        {
            result.append(crs.toString() + "\n");
        }
        return result.toString();
    }
}
