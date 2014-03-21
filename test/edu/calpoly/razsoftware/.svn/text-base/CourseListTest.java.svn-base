package edu.calpoly.razsoftware;

import junit.framework.TestCase;
import com.google.common.collect.ImmutableSet;
import java.io.IOException;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

/**
 *
 * @author light, gdonegan
 */
public class CourseListTest extends TestCase
{
    public CourseList list;
    public I_Course cpe102, cpe103, csc141;
    public Collection<I_Course> collection = new HashSet<I_Course>();
    
    
    public void setUp()
    {
        list =
                new CourseList(CourseList.class.getResourceAsStream("Catalog.json"));
        cpe102 = list.lookUp("CPE", 102);
        cpe103 = list.lookUp("CPE", 103);
        csc141 = list.lookUp("CSC", 141);
        collection.add(cpe102);
        collection.add(cpe103);
        collection.add(csc141);

    }
    @Test
    public void testDependenciesShouldReferToSameObjects() throws IOException
    {
        
        assertEquals(1, cpe103.getPreRequisites().size());
        assertEquals(2, cpe103.getPreRequisites().iterator().next().size());

        for (I_Course c : cpe103.getPreRequisites().iterator().next())
        {
            if (c.getDept().get(0).equals("CPE") && c.getNumber() == 102)
            {
                assertSame(cpe102, c);
            }
            else if (c.getDept().get(0).equals("CSC") && c.getNumber() == 141)
            {
                assertSame(csc141, c);
            }
        }

        assertEquals(ImmutableSet.of(ImmutableSet.of(cpe102, csc141)),
                     cpe103.getPreRequisites());
    }
    
    @Test
    public void testContains()
    {
        System.out.println("TEST: contains");
        assertTrue(list.contains(cpe102));    
    }
    
    @Test
    public void testAddRemove()
    {
        System.out.println("Test: Add/Remove");
        list.remove(cpe102);
        assertFalse(list.contains(cpe102));
        list.add(cpe102);
        assertTrue(list.contains(cpe102));
    }
    
    @Test
    public void testAddMultiple()
    {
        System.out.println("TEST: Add/Remove Multiple");
        list.removeAll(collection);
        assertFalse(list.contains(cpe102));
        assertFalse(list.contains(cpe103));
        assertFalse(list.contains(csc141));
        list.addAll(collection);
        assertTrue(list.contains(cpe102));
        assertTrue(list.contains(cpe103));
        assertTrue(list.contains(csc141));
        
    }
}
