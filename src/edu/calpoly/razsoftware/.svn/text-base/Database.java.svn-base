package edu.calpoly.razsoftware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Database is a utility class of file operations. It provides operations to
 * save/load user's flowchart to/from disk. The external data must be human
 * editable, so the file format can be XML, gson, CSV, plain text, etc, but NOT
 * binary.
 *
 * Since the course catalog never changes, it is NOT part of the user data.
 *
 * The user data is the table (chart) of requirements arranged by quarter, and
 * the quarter names (column headers in the table).
 *
 * When saving requirements any references to Course is saved as an index into
 * the Catalog (not the Course object itself).
 *
 * @author Jeremy Rabaino (jrabaino) - pseudocode
 */
public class Database
{
    static private final String kQuartersSuffix = ".quarters";
    static private Gson gson = new GsonBuilder().registerTypeAdapter(
            I_Requirement.class,
            new RequirementCreator()).registerTypeAdapter(
            I_Course.class, new CourseCreator(null)).create();

    /**
     * Save the inputted flowchart into a GSON file File format should contain:
     * Requirement Quarter# FullfillmentOptionsSet CourseSelection
     *
     * @param filename name of the file to save the flowchart to
     * @param flowchart the flowchart to save
     */
    public static void saveFlowchart(String filename,
                                     I_Flowchart flowchart) throws IOException
    {
        // INIT file writer using most recent file
        FileWriter writer = new FileWriter(filename);
        // CALL saveToWriter
        saveToWriter(writer, flowchart);
        // CLOSE writer
        writer.close();
        // INIT file writer for quarters
        writer = new FileWriter(filename + kQuartersSuffix);
        // CALL saveQuarters()
        saveQuarters(writer, flowchart.getQuarterNames());
        // CLOSE writer
        writer.close();
        // CALL saveMostRecent();
        saveMostRecent(filename);
    }

    /**
     * Load the flowchart in the given database filename. To load the most
     * recent flowchart, use loadMostRecent.
     *
     * @param loadFile The URL of the database to load
     * @param catalog The classes to load from
     * @return The list of requirements in the database file
     */
    public static List<I_Requirement> loadChart(String loadFile, I_Catalog catalog)
    {
        // SAVE catalog
        try
        {
            // RETURN CALL from loadFromStream()
            InputStream stream = null;
            //IF loadfile is available THEN
            if (loadFile != null)
            {
                File file = new File(loadFile);
                stream = new FileInputStream(file);
            }
            List<I_Requirement> requirements = loadFromStream(stream, catalog);
            return requirements;
        }
        catch (FileNotFoundException ex)
        {
            System.err.println("Unable to load flowchart.");
            System.err.println(ex);
        }
        return null;
    }

    /**
     * Protected for testing purposes. Saves the given filename in a file named
     * recent.save.
     *
     */
    protected static void saveMostRecent(String mostRecent)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("recent.save"));
            writer.write(mostRecent);
            writer.flush();
            writer.close();
        }
        catch (IOException ex)
        {
            System.err.println("Cannot persist most recent save.");
        }
    }

    /**
     * Find the most recently saved flowchart.
     *
     * @return the Filename of the most recently saved flowchart
     */
    public static String loadMostRecent()
    {
        FileReader reader;
        try
        {
            String fileName = null;
            reader = new FileReader("recent.save");
            Scanner scanner = new Scanner(reader);
            // IF there is a file name
            if (scanner.hasNextLine())
            {
                fileName = scanner.nextLine();
                // IF file does not exist
                if (!new File(fileName).isFile())
                {
                    fileName = null;
                    scanner.close();
                    // clear most recent
                    saveMostRecent("");
                }
            }
            scanner.close();
            return fileName;
        }
        catch (IOException ex)
        {
            System.err.println("Cannot retrieve most recent save.");
        }


        return null;
    }

    /**
     * Protected for testing purposes.
     *
     * @param writer
     * @param flowchart
     * @throws IOException
     */
    static void saveToWriter(Writer writer, I_Flowchart flowchart) throws IOException
    {
        // FOR each requirement in flowchart.getRequirements()
        for (I_Requirement req : flowchart.getRequirements())
        {
            // Use GSON to write requirement
            writer.write(gson.toJson(req, I_Requirement.class));
            // WRITE a newline
            writer.write('\n');
        }
        // ENDFOR
    }

    /**
     * Loads a list of requirements from an inputStream. Uses DegreechartReader.
     * If stream is null, uses the default.
     *
     * @param stream source of file, or null
     * @param catalog university's catalog of course offerings.
     * @return list of requirement
     */
    static List<I_Requirement> loadFromStream(InputStream stream, I_Catalog catalog)
    {
        Gson gsonBuilder = new GsonBuilder().registerTypeAdapter(
                I_Requirement.class,
                new RequirementCreator()).registerTypeAdapter(
                I_Course.class,
                new CourseCreator(catalog.getAllCourses())).create();
        //IF stream is present THEN
        if (stream == null)
        {
            stream = Database.class.getResourceAsStream("DegreeChart.json");
        }
        Scanner sc = new Scanner(stream);
        List<I_Requirement> list = new ArrayList<I_Requirement>();

        //WHILE there is more requirement to read
        while (sc.hasNextLine())
        {
            list.add(gsonBuilder.fromJson(sc.nextLine(), I_Requirement.class));
        }
        return list;
    }

    /**
     * Protected for testing purposes.
     * Save quarter names to a GSON file Output file has the quarters.
     */
    protected static void saveQuarters(
            Writer writer, String[] quarterNames) throws IOException
    {
        // use GSON to write quarters
        writer.write(gson.toJson(quarterNames));


    }

    /**
     * Load the quarter names from the GSON file.
     *
     * @return An indexed string array of the quarter names
     */
    protected static String[] loadQuarters(Reader reader)
    {
        // use GSON to read quarters
        return gson.fromJson(reader, String[].class);
    }
}
