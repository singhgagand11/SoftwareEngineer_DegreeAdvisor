package edu.calpoly.razsoftware;

import java.io.*;
import java.util.Arrays;
import junit.framework.TestCase;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jrabaino
 */
public class DatabaseTest extends TestCase
{
    private static I_Requirement requirement = new FakeRequirement(new FakeCourse())
    {
        @Override
        public I_Course getCourse()
        {
            return new FakeCourse();
        }

        @Override
        public Set<I_Course> getFulfillmentOptions()
        {
            return new HashSet<I_Course>();
        }

        @Override
        public int getQuarter()
        {
            return 0;
        }

        @Override
        public String getTitle()
        {
            return "CRS 1";
        }
    };
    private static I_Flowchart flowchart = new FakeFlowchart();

    @Test
    public void testSaveToWriter() throws IOException
    {
        StringWriter sw = new StringWriter();
        Database.saveToWriter(sw, flowchart);
        assertEquals("{\"course\":{\"units\":4,\"completed\":false},\"c"
                + "ompleted\":false,\"fulfillmentOptions\":[{\"unit"
                + "s\":4,\"completed\":false}]}\n", sw.toString());
    }

    @Test
    public void testMostRecent()
    {
        String fileName = getClass().getResource("DegreeChart.json").getPath();
        Database.saveMostRecent(fileName);
        assertEquals(fileName, Database.loadMostRecent());
        try
        {
            FileWriter writer = new FileWriter("recent.save");
            fileName = "DatabaseTest2.json";
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
        StringBufferInputStream sr = new StringBufferInputStream("{\"requireme"
                + "ntName\":\"CSC 101\",\"fulfillmentOptions\":[{\"dep"
                + "t\":[\"CSC\"],\"number\":101}],\"mutuallyExclusi"
                + "ve\":true,\"preRequisites\":[],\"quarter\":1}\n");

        final I_Course course = new FakeCourse();
        List<I_Requirement> list = Database.loadFromStream(sr, new FakeCatalog()
        {
            @Override
            public CourseList getAllCourses()
            {
                CourseList list = super.getAllCourses();
                list.add(course);
                return list;
            }
        });
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
}
