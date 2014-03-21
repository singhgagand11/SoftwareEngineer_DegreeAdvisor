package edu.calpoly.razsoftware;

import com.google.gson.Gson;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * this class is a tool used to convert the plain text from the catalog given by
 * the university into a json file usable by the main program
 *
 * this is not meant to be used by the end user, and is purely a dev tool
 *
 * @author Adam Spurgin
 */
public class CatalogTxtToJsonConverter
{
    private static final int kMAJORINDEX = 3;

    /**
     * this method is the entry point, this methods manages all the courses read
     * from the file, and writes all the elevent JSON data to the specified
     * output file.
     * @param args arguments passed to the program, first one is the source for
     * the data, the second is an optional destination name.
     * @throws IOException if the course file could not be found
     * @precondition the specified file exists and follows the conventions
     * specified in the user manual
     *
     */
    public static void main(String[] args) throws IOException
    {
        //IF argurments are valid THEN
        if (validArgs(args))
        {
            File input = new File(args[0]); //file to be read
            File output = new File("Catalog.json"); //file to write to

            Scanner reader; //does the reading
            FileWriter outWriter; //does the writing
            Gson gson = new Gson(); //encodes the objects to JSON strings
            ArrayList<I_Course> courses = new ArrayList<I_Course>(); //holds the parsed
            //courses

            //IF the command line arguments contain a specified
            //destination name THEN
            if (args.length == 2)
            {
                //SET the name of the output file to it
                output = new File(args[1]);
            }
            //ENDIF
            //TRY
            try
            {
                //SET the scanner to read from the input file
                reader = new Scanner(input);
            }
            catch (FileNotFoundException ex)
            {
                System.out.println("Could not open file " + args[0]);
                return;
            }
            //TRY
            try
            {
                //SET the writer to wrtie to the output file
                outWriter = new FileWriter(output);
            }
            catch (IOException ex)
            {
                System.out.println("Could not create file " + args[0]);
                return;
            }

            //make the first pass across the catalog
            parseTextToCourse(reader, courses);

            //CALL writeCourses to write out all the courses
            writeCourses(null, outWriter, gson);
        }

    }

    /**
     * write set of courses our to gson file
     * @param courses set of courses to write out to a file
     * @param outWriter writes the json content to
     * @param gson encodes the objects to JSON strings
     * @throws IOException if FileWriter files to write the file
     */
    private static void writeCourses(Set<I_Course> courses,
                                     FileWriter outWriter,
                                     Gson gson) throws IOException
    {
        //FOR each course generated
        for (I_Course currentCourse : courses)
        {
            // IF the current course description starts with "(Also listed as"
            // THEN
            if (currentCourse.getDescription().startsWith("(Also listed as"))
            {
                // split the string after the closing parenthases
                String[] spl = currentCourse.getDescription().split("\\)", 2);
                // set the description to the last half of the split
                currentCourse = new Course(currentCourse.getDept(),
                                           currentCourse.getNumber(),
                                           currentCourse.getUnits(),
                                           currentCourse.getTitle(),
                                           spl[1]);
                String[] spl2 = spl[0].split(" ");
                // add the alternative major listing to the course
                currentCourse.getDept().add(spl2[kMAJORINDEX]);
            }
            cleanLeftoverReqStructs(currentCourse);
            // ENDIF
            // IF the course's description contains "prerequisite:"
            if (currentCourse.getDescription().contains("prerequisite:"))
            {
                //split the string at "prerequisite:"
                String[] tokens =
                        currentCourse.getDescription().split("prerequisite:");
                //SET the course's description to the part that came beore the
                //split
                currentCourse = new Course(currentCourse.getDept(),
                                           currentCourse.getNumber(),
                                           currentCourse.getUnits(),
                                           currentCourse.getTitle(),
                                           tokens[0]);
                // IF the second half contains "Corequisite" THEN
                if (tokens[1].contains("corequisite"))
                {
                    //split the string at "corequisite"
                    String[] spl2 = tokens[1].split("corequisite:");
                    //parse the first half for Prerequisites
                    currentCourse.setPreRequisites(parseReq(spl2[0]));
                    //parse the second half for Corequisites
                    currentCourse.setCoRequisites(parseReq(spl2[1]));
                }
                //ELSE
                else
                {
                    //parse the string for Prerequisites
                    currentCourse.setPreRequisites(parseReq(tokens[1]));
                }
            }
            // export the course to the file
            outWriter.append(gson.toJson(currentCourse) + "\n");
        }

        //close the writer
        outWriter.close();
    }

    private static boolean validArgs(String[] args)
    {
        //IF there are no command line arguments THEN
        if (args.length == 0)
        {
            System.out.println("Invalid command line arguments,"
                    + " please specify file name");
            System.out.println("proper use is \"CatalogTxtToJsonConverter "
                    + "<input file> <output file>\"");
            return false;
        }
        //ENDIF
        //RETURN true
        return true;
    }

    /**
     * sometimes the formatting of the description leaves behind portions of the
     * prerequisite information, this takes care of that
     * @param currentCourse the course to be cleaned up
     */
    private static void cleanLeftoverReqStructs(I_Course currentCourse)
    {
        // IF the description contains the word "Prerequisite:" THEN
        if (currentCourse.getDescription().contains("Prerequisite:"))
        {
            // split the description at "Prerequisite:"
            String[] tokens =
                    currentCourse.getDescription().split("Prerequisite:");
            // set the description to the first half
            currentCourse = new Course(currentCourse.getDept(),
                                       currentCourse.getNumber(),
                                       currentCourse.getUnits(),
                                       currentCourse.getTitle(),
                                       tokens[0]);
            // IF the second half contains "Corequisite" THEN
            if (tokens[1].contains("Corequisite"))
            {
                // split the description at "Corequisite"
                String[] tokenTokens = tokens[1].split("Corequisite:");
                // parse first half of second part as prerequisites
                currentCourse.setPreRequisites(parseReq(tokenTokens[0]));
                // parse second half as of second part corequisites
                currentCourse.setCoRequisites(parseReq(tokenTokens[1]));
            }
            // ELSE
            else
            {
                // parse second half as prerequisites
                currentCourse.setPreRequisites(parseReq(tokens[1]));
            }
        }
    }

    /**
     * this performs the initial pass over the catalog and chunks out the text
     * into individual courses
     * @param scan the scanner tied to the plain english source file
     * @param catConvCourses the arraylist to store the rough courses in
     */
    private static void parseTextToCourse(Scanner scan,
                                          ArrayList<I_Course> catConvCourses)
    {
        // WHILE there are still lines left to be scanned
        while (scan.hasNextLine())
        {
            // SET currentline to the next line
            String currentLine = scan.nextLine();
            // IF the current line matches the pattern signifying a new entry
            // THEN
            if (currentLine.matches("[A-Za-z]{3,4} [0-9]{3} [^(]* .*[(].*"))
            {
                String name = "";
                String[] spl;
                int number = 0, units = 0;
                ArrayList<String> major = new ArrayList<String>();

                spl = currentLine.split(" ");
                major.add(spl[0]);
                number = Integer.valueOf(spl[1]);

                try
                {
                    int tokenIndex = 2;
                    StringBuilder nameBuilder = new StringBuilder("");
                    //FOR every spelling token starting with '('
                    for (; !spl[tokenIndex].startsWith("("); tokenIndex++)
                    {
                        nameBuilder.append(spl[tokenIndex]).append(" ");
                    }
                    //END FOR
                    units = (int) Integer.valueOf(spl[tokenIndex].charAt(1)
                            - '0');
                }
                catch (Exception anyException)
                {
                    System.out.println(currentLine);
                }
                // IF the current line indicates the class has more listings
                // THEN
                if (currentLine.contains("(Also listed as"))
                {
                    // add the alternate listings to the major list
                    String[] altListing =
                            currentLine.split("\\(Also listed as", 2);
                    major.add(altListing[1].split(" ")[1]);
                }
                // add the current course to the list
                catConvCourses.add(new Course(major, number, units, name, ""));
            }
            // ELSE IF the list of courses is not empty
            else if (catConvCourses.size() > 0)
            {
                //CALL moveCourses to move courses to the top of the list
                moveCourses(catConvCourses, currentLine);
            }
        }

    }

    /**
     * Helper function to move courses to the top
     * @param catConvCourses the arraylist to store the rough courses in
     * @param currentLine line that is currently being parsed
     */
    private static void moveCourses(ArrayList<I_Course> catConvCourses, String
            currentLine)
    {
        String name, description;
        int number = 0, units = 0;
        ArrayList<String> major = new ArrayList<String>();
        // set current course to the course on top of the list
        I_Course course = catConvCourses.remove(catConvCourses.size()
                - 1);
        I_Course newCourse =
                new Course(course.getDept(), course.getNumber(),
                           course.getUnits(), course.getTitle(),
                           course.getDescription() + currentLine);
        // append description to the course  put it back
        catConvCourses.add(newCourse);
    }

    /**
     * this parses a given string containing pre/coreq data into the
     * appropriate data structure
     * @param in the string containing pre/coreq data
     * @return a set of properly constructed requisites
     */
    public static Set<Set<I_Course>> parseReq(String in)
    {
        // initialize the pattern to check
        Pattern coursePattern = Pattern.compile("[A-Z]{3,4} [0-9]{3}");
        // INITIALIZE the hashset to rebuild
        HashSet<Set<I_Course>> req = new HashSet<Set<I_Course>>();
        // split the plain english statement up by common deliniators
        String[] stage1 = in.split("and|,|;");

        // FOR every string that was just generated
        for (String str : stage1)
        {
            // INITIALIZE a new set of courses
            HashSet<I_Course> out = new HashSet<I_Course>();


            Matcher regexMatch = coursePattern.matcher(str);
            // WHILE there is a pattern that matches a class, create a new class
            //and add it to the list
            while (regexMatch.find())
            {
                ArrayList<String> major = new ArrayList<String>();
                major.add(regexMatch.group().split(" ")[0]);
                int number = Integer.valueOf(regexMatch.group().split(" ")[1]);
                out.add(new Course(major, number, 0, "", ""));
            }
            // ENDFOR

            // IF a class was added
            if (!out.isEmpty())
            {
                // Add the requisite to the hashset
                req.add(out);
            }
        }
        // ENDFOR
        // IF the requisite list is empty THEN
        if (req.isEmpty())
        {
            return null;
        }
        // ELSE
        else
        {
            //INITIALIZE a set of set of courses
            HashSet<Set<I_Course>> permutations =
                    new HashSet<Set<I_Course>>();
            permutations = reformRequisites(req);
            req = permutations;
        }
        return req;
    }

    /**
     * this takes a set of requirements formatted the way they would be in the
     * plain english document, and converts them into the more logically
     * robust form used in fast requisite checking.
     * @param req a requirement data structure
     * @return a reformed data structure
     */
    private static HashSet<Set<I_Course>> reformRequisites(HashSet<Set<I_Course>> req)
    {
        HashSet<Set<I_Course>> permutations =
                new HashSet<Set<I_Course>>();
        //FOR every set in the requirements structure
        for (Set<I_Course> subSet : req)
        {
            //IF the set is empty
            if (permutations.isEmpty())
            {
                //FOR every course in the set
                for (I_Course course : subSet)
                {
                    //INITIALIZE a new set of courses
                    HashSet<I_Course> newitem = new HashSet<I_Course>();
                    //add the course to that set
                    newitem.add(course);
                    //add this set to the set of sets
                    permutations.add(newitem);
                }
                //ENDFOR
            }
            //ELSE IF the set is not empty
            else
            {
                //INITIALIZE a new set of sets of courses
                HashSet<Set<I_Course>> newPerm = new HashSet<Set<I_Course>>();
                //FOR each set in the permutations
                for (Set<I_Course> s2 : permutations)
                {
                    //FOR each course in the original set
                    for (I_Course course : subSet)
                    {
                        //create a new set duplicating the set in the
                        //original set
                        HashSet<I_Course> dupeset = new HashSet<I_Course>(s2);
                        //add the current course to the set
                        dupeset.add(course);
                        //add the set to the original set
                        newPerm.add(dupeset);
                    }
                    //ENDFOR
                }
                //ENDFOR
                permutations = new HashSet<Set<I_Course>>(newPerm);

            }

        }
        return permutations;
    }
}
