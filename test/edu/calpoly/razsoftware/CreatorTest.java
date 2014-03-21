/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.calpoly.razsoftware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author jeremy
 */
public class CreatorTest extends TestCase
{
    private final Gson gson;
    private final CourseList list;
    private I_Course course;

    public CreatorTest()
    {
        list = new CourseList();
        course = new Course(Arrays.asList("CSC"), 0, 0, "title", "desc");
        list.add(course);
        gson = new GsonBuilder().registerTypeAdapter(I_Course.class, new CourseCreator(list)).
                registerTypeAdapter(I_Requirement.class, new RequirementCreator()).create();
    }

    @Test
    public void testDeserializeCourse()
    {
        //Will throw error if it fails
        course = gson.fromJson("{\"dept\":[\"ABC\"],\"number\":104,\"units\":0,\"name\":\"NA\",\"description\":\"NA\",\"preRequisites\":[],\"coRequisites\":[], \"completed\":true}", I_Course.class);
        assertNotNull(course);
        assertTrue(course.isComplete());
        gson.fromJson("{\"dept\":[\"CSC\"],\"number\":102}", I_Course.class);

    }

    @Test
    public void testDeserializeRequirement()
    {
        course = new Course(Arrays.asList("CSC"), 101, 0, "title", "desc");
        //Will throw error if it fails
        I_Requirement req = gson.fromJson("{\"requirementName\":\"CSC 101\",\"fulfillmentOptions\":[{\"dept\":[\"CSC\"],\"number\":101,\"units\":0,\"name\":\"NA\",\"description\":\"NA\",\"preRequisites\":[],\"coRequisites\":[]}],\"mutuallyExclusive\":true,\"preRequisites\":[{}],\"quarter\":3}", I_Requirement.class);
        assertEquals(3,req.getQuarter());
        gson.fromJson("{\"requirementName\":\"CSC 101\",\"fulfillmentOptions\":[{\"dept\":[\"CSC\"],\"number\":101,\"units\":0,\"name\":\"NA\",\"description\":\"NA\"}],\"preRequisites\":[{}],\"mutuallyExclusive\":true,\"quarter\":1}", I_Requirement.class);

    }

    @Test
    public void testDefect()
    {
        gson.fromJson("{\"requirementName\":\"CSC 101\",\"fulfillmentOptions\":[{}],\"preRequisites\":[],\"mutuallyExclusive\":true,\"quarter\":1,\"selectedCourse\":{\"dept\":[\"CSC\",\"CPE\"],\"number\":101,\"units\":4,\"title\":\" Fundamentals of Computer Science I \",\"description\":\"Basic principles of algorithmic problem solving and programming usingmethods of top-down design, stepwise refinement and proceduralabstraction. Basic control structures, data types, and input/output.Introduction to the software development process: design, implementation,testing and documentation. The syntax and semantics of a modernprogramming language. Credit not available for students who have takenCSC/CPE 108. 3 lectures, 1 laboratory. \",\"preRequisites\":[[]],\"coRequisites\":[],\"completed\":true,\"satisfied\":false},\"deptDirections\":\"\",\"completed\":false}", I_Requirement.class);
    }
}
