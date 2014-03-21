package edu.calpoly.razsoftware;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import java.util.ArrayList;

/**
 * Converts the file of a flowchart into a Flowchart object to be
 *         used by the application
 * @author aspurgin gdonegan
 * @version $Revision: 200 $
 *
 */
public class DegreechartReader
{
    private static Gson gson;
    // This field gives text explanations for complex prerequisites.
    private String deptDirections;
    // This field provides prerequisite information for requirements.
    private Set<Set<I_Course>> preRequisites;

    /**
     * provides a degreechart (Ideal dept flowchart) based off a inputstream 
     * of a JSON file containing newline-delineated "Requirement" classes
     *
     * @param iStream
     *            the stream of the JSON file
     * @param courses
     *            the catalog of courses
     * @return a list of the requirements in the depts degree chart.
     */
    public static List<I_Requirement> readFlowchart(InputStream iStream,
                                                    CourseList courses)
    {
        // INITIALIZE Gson
        gson = new GsonBuilder().registerTypeAdapter(I_Requirement.class,
                new RequirementCreator()).registerTypeAdapter(
                I_Course.class, new CourseCreator(courses)).create();
        // INITIALIZE the set of Requirements to the empty set
        List<I_Requirement> degreeChart = new ArrayList<I_Requirement>();
        // CREATE new scanner
        Scanner flowScanner = new Scanner(iStream);

        // WHILE the input has another line DO
        while (flowScanner.hasNextLine())
        {
            // INITIALIZE tmpCourseList to the empty set
            HashSet<I_Course> tmpCourseList = new HashSet<I_Course>();
            // PARSE a Requirement from the file
            I_Requirement req =
                    gson.fromJson(flowScanner.nextLine(), I_Requirement.class);

            // System.out.println("--");
            // ADD co to the course options set
            degreeChart.add(req);
        }
        return degreeChart;
    }
}
