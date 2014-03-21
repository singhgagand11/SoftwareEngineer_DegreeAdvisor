package edu.calpoly.razsoftware;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import junit.framework.TestCase;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joe Walsh <jkwalsh@calpoly.edu>
 */
public class DatabaseIntTest extends TestCase
{
    private I_Catalog catalog;
    private I_Flowchart flowchart;

    public DatabaseIntTest()
    {

        CourseList courses = new CourseList();
        I_Course course = new Course(Arrays.asList("CSC"), 101);

        courses.add(course);
        List<I_Requirement> reqs = new ArrayList<I_Requirement>();

        reqs.add(new Requirement(course, 1));
        catalog = new Catalog(courses);
        flowchart = new Flowchart(catalog, reqs);

    }

    @Test
    public void testSaveToWriter() throws IOException
    {

        StringWriter sw = new StringWriter();
        Database.saveToWriter(sw, flowchart);
        assertEquals(
                "{\"requirementName\":\"CSC101\",\"fulfillmentOptions\":["
                + "{\"dept\":[\"CSC\"],\"number\":101,\"units\":0,\"pre"
                + "Requisites\":[],\"coRequisites\":[],\"completed\":fal"
                + "se,\"satisfied\":false}],"
                + "\"preRequisites\":[],\"mutuallyExclusive\":true,\"quarter"
                + "\":1,\"selectedCourse\":{\"dept\":[\"CSC\"],\"number\":101,"
                + "\"units\":0,\"preRequisites\":[],\"coRequisites\":[],\""
                + "completed\":false,\"satisfied\":false},\"deptDirections\":\"\","
                + "\"completed\":false}\n", sw.toString());
    }

    @Test
    public void testMostRecent()
    {
        String fileName = Database.class.getResource("Catalog.json").getPath();
        Database.saveMostRecent(fileName);
        assertEquals(fileName, Database.loadMostRecent());
        try
        {
            FileWriter writer = new FileWriter("recent.save");
            fileName = "NonExistentFile.json";
            writer.write(fileName);
            writer.close();
            assertNull(Database.loadMostRecent());
        }
        catch (IOException ex)
        {
            fail("Cannot persist most recent save.");
        }
    }

    @Test
    public void testLoadFromStream()
    {
        //Requirement Quarter FullfillmentOptionsSet CourseSelection
        StringBufferInputStream sr =
                new StringBufferInputStream("{\"requirementName\":\"CSC 101\",\""
                + "fulfillmentOptions\":[{\"dept\":[\"CSC\"],\"number\":101}],"
                + "\"mutuallyExclusive\":true,\"quarter\":1,\"preRequisites\":[]}\n");

        List<I_Requirement> list = Database.loadFromStream(sr, catalog);

        assertEquals("CSC101", list.get(0).getCourse().toString());
    }

    @Test
    public void testLoadQuarters()
    {
        StringReader sr = new StringReader("[\"A\",\"B\"]");
        String[] quarters = Database.loadQuarters(sr);
        assertEquals(2, quarters.length);
        assertEquals("A", quarters[0]);
        assertEquals("B", quarters[1]);
    }

    @Test
    public void testSaveQuarters()
    {
        StringWriter sw = new StringWriter();
        try
        {
            Database.saveQuarters(sw, new String[]
                    {
                        "A", "B"
                    });
        }
        catch (IOException ex)
        {
            fail("String writer failed..");
        }
        assertEquals("[\"A\",\"B\"]", sw.toString());
    }

    @Test
    public void testSaveFlowchart() throws IOException
    {

        FileWriter writer = new FileWriter("recent.save");
        String fileName = "";
        writer.write(fileName);
        writer.close();

        Catalog c = new Catalog(new CourseList(CourseList.class.getResourceAsStream("Catalog.json")));
        Flowchart f = new Flowchart(c);
        List<I_Requirement> rs = f.getRequirements();

        Database.saveFlowchart("asdf.us", f);
        rs = Database.loadChart("asdf.us", c);
    }
}
