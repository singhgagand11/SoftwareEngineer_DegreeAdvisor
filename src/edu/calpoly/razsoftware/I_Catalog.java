package edu.calpoly.razsoftware;

import java.util.List;
import java.util.Set;

/**
 * An interface for catalog
 * @author jeremy
 */
public interface I_Catalog
{
    /**
     * Gets the underlying list of courses for the catalog.
     * @return list of courses
     */
    CourseList getAllCourses();

     /** Return all the course names in the catalog for a specific department.
     * @param dept The name of the department to search
     * @return a list of all courses under the department
     */
    List<I_Course> getCoursesByDept(String dept);

     /**
     * Gets a set of all names the names of all departments, ie CSC, CPE.
     * @return a set of all department names.
     */
    Set<String> getDeptNames();

     /**
     * Creates a formatted string, with each course on a newline.
     * @return a formatted string.
     */
    String toOutputString();

     /**
      * gets the names of the catalog
      * @return name of the catalog
      */
    String toString();
}
