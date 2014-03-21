package edu.calpoly.razsoftware;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * A utility application to change the CVS flowchart to a json file
 *
 * @author aspurgin
 * @version $Revision: 200 $
 */
public class DegreechartCsvToJsonConverter
{
    /**
     * index in split array that contains the course
     */
    public static final int kCourseIndex = 3;

    /**
     * Runs the application to convert the CVS file
     *
     * @param args the command line arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        Gson gson = new GsonBuilder().registerTypeAdapter(
                I_Requirement.class,
                new RequirementCreator()).registerTypeAdapter(
                I_Course.class, new CourseCreator(new CourseList())).create();
        File csvInput = new File("DegreeChart.csv");
        //IF there are command line arguments THEN
        if (args.length > 0)
        {
            csvInput = new File(args[0]);
        }//END IF
        File jsonOutput = new File("DegreeChart.json");
        Scanner csvReader = new Scanner(csvInput);
        FileWriter jsonWriter = new FileWriter(jsonOutput);

        // WHILE there is another line in the file
        while (csvReader.hasNextLine())
        {
            // parse the line
            String[] tokens = csvReader.nextLine().split(",");
            // assign the correct fields to the Requirement
            String req = tokens[0];
            int quarter = Integer.valueOf(tokens[1]);
            boolean mutex = tokens[2].contains("TR");
            HashSet<I_Course> options = new HashSet<I_Course>();
            //FOR each course available for the option
            for (int course = kCourseIndex; course < tokens.length; course++)
            {
                //Parse the courses and add it to the options
                ArrayList<String> tmplst = new ArrayList<String>();
                tmplst.add(tokens[course].split(" ")[0]);
                I_Course createdCourse =
                        new Course(tmplst,
                                   Integer.valueOf(tokens[course].split(" ")[1]), 0,
                                   "NA", "NA");
                options.add(createdCourse);
            }//END FOR
            Requirement co = new Requirement(req, options, mutex, quarter);
            jsonWriter.append(gson.toJson(co) + "\n");
        }// ENDWHILE
        jsonWriter.close();
        csvReader.close();
    }
}
