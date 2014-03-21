package edu.calpoly.razsoftware.systemtests;

import edu.calpoly.razsoftware.FlowcharterApp;
import org.uispec4j.DefaultTableCellValueConverter;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;
import org.uispec4j.Table;

/**
 * Purpose: To demonstrate that flowchart populates a list or requirements in
 * each cell at startup.
 * @author gagandeep
 */
public class TestCase2 extends UISpecTestCase
{
    Window window;
    Table table;

    @Override
    protected void setUp() throws Exception
    {
        setAdapter(new org.uispec4j.interception.MainClassAdapter(
                FlowcharterApp.class, new String[0]));
        this.window = this.getMainWindow();
        this.table = window.getTable();

    }

    public void testCaseFour()
    {
        assertEquals(7, table.getRowCount());
        assertEquals(12, table.getColumnCount());


    }

    public void testColumn0()
    {
        String courseName = (String) table.getContentAt(0, 0);
        String expected = "<HTML>CSC101</HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(1, 0);
        expected = "<HTML>MATH141</HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(2, 0);
        expected = "<HTML><small>???</small><small>? GE A1</small></HTML>";
        assertEquals(expected, courseName);
    }

    public void testColumn1()
    {
        String courseName = (String) table.getContentAt(0, 1);
        String expected = "<HTML>CSC102</HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(1, 1);
        expected = "<HTML>CSC141</HTML>";
        assertEquals(expected, courseName);


        courseName = (String) table.getContentAt(2, 1);
        expected = "<HTML>MATH142</HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(3, 1);
        expected = "<HTML><small>???</small><small>? GE A2</small></HTML>";
        assertEquals(expected, courseName);
    }

    public void testColumn2()
    {
        String courseName = (String) table.getContentAt(0, 2);
        String expected = "<HTML>CSC103</HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(1, 2);
        expected = "<HTML>CPE129</HTML>";
        assertEquals(expected, courseName);


        courseName = (String) table.getContentAt(2, 2);
        expected = "<HTML>CPE169</HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(3, 2);
        expected = "<HTML>ENGL149</HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(4, 2);
        expected = "<HTML><small>???</small><small>? GE D4</small></HTML>";
        assertEquals(expected, courseName);
    }

    public void testColumn3()
    {
        String courseName = (String) table.getContentAt(0, 3);
        String expected = "<HTML><small>???</small><small>? ASSEM LANG</small></HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(1, 3);
        expected = "<HTML>STAT321</HTML>";
        assertEquals(expected, courseName);


        courseName = (String) table.getContentAt(2, 3);
        expected = "<HTML><small>???</small><small>? SCIENCE</small></HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(3, 3);
        expected = "<HTML><small>???</small><small>? GE D1</small></HTML>";
        assertEquals(expected, courseName);
    }

    public void testColumn4()
    {
        String courseName = (String) table.getContentAt(0, 4);
        String expected = "<HTML><small>???</small><small>? SCIENCE 2</small></HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(1, 4);
        expected = "<HTML>CSC357</HTML>";
        assertEquals(expected, courseName);


        courseName = (String) table.getContentAt(2, 4);
        expected = "<HTML>CSC315</HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(3, 4);
        expected = "<HTML><small>???</small><small>? GE C2</small></HTML>";
        assertEquals(expected, courseName);
    }

    public void testColumn5()
    {
        String courseName = (String) table.getContentAt(0, 5);
        String expected = "<HTML><small>???</small><small>? SCIENCE 3</small></HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(1, 5);
        expected = "<HTML>CSC453</HTML>";
        assertEquals(expected, courseName);


        courseName = (String) table.getContentAt(2, 5);
        expected = "<HTML>CSC349</HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(3, 5);
        expected = "<HTML>BIO213</HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(4, 5);
        expected = "<HTML>BRAE213</HTML>";
        assertEquals(expected, courseName);
    }

    public void testColumn6()
    {
        String courseName = (String) table.getContentAt(0, 6);
        String expected = "<HTML><small>???</small><small>? SOFT ENG</small></HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(1, 6);
        expected = "<HTML><small>???</small><small>? TECH ELEC</small></HTML>";
        assertEquals(expected, courseName);


        courseName = (String) table.getContentAt(2, 6);
        expected = "<HTML><small>???</small><small>? MATH/STAT</small></HTML>";
        assertEquals(expected, courseName);
    }

    public void testColumn7()
    {
        String courseName = (String) table.getContentAt(0, 7);
        String expected = "<HTML>CSC309</HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(1, 7);
        expected = "<HTML>CSC430</HTML>";
        assertEquals(expected, courseName);


        courseName = (String) table.getContentAt(2, 7);
        expected = "<HTML><small>???</small><small>? MATH/STAT</small></HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(3, 7);
        expected = "<HTML><small>???</small><small>? GE C1</small></HTML>";
        assertEquals(expected, courseName);
    }

    public void testColumn8()
    {
        String courseName = (String) table.getContentAt(0, 8);
        String expected = "<HTML>CSC300</HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(1, 8);
        expected = "<HTML>CSC431</HTML>";
        assertEquals(expected, courseName);


        courseName = (String) table.getContentAt(2, 8);
        expected = "<HTML><small>???</small><small>? SUPPORT</small></HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(3, 8);
        expected = "<HTML><small>???</small><small>? GE D3</small></HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(4, 8);
        expected = "<HTML><small>???</small><small>? USCP</small></HTML>";
        assertEquals(expected, courseName);
    }

    public void testColumn9()
    {
        String courseName = (String) table.getContentAt(0, 9);
        String expected = "<HTML><small>???</small><small>? TECH ELEC</small></HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(1, 9);
        expected = "<HTML>CSC445</HTML>";
        assertEquals(expected, courseName);


        courseName = (String) table.getContentAt(2, 9);
        expected = "<HTML><small>???</small><small>? TECH ELEC</small></HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(3, 9);
        expected = "<HTML><small>???</small><small>? GE C3</small></HTML>";
        assertEquals(expected, courseName);

    }

    public void testColumn10()
    {
        String courseName = (String) table.getContentAt(0, 10);
        String expected = "<HTML>CSC491</HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(1, 10);
        expected = "<HTML><small>???</small><small>? TECH ELEC</small></HTML>";
        assertEquals(expected, courseName);


        courseName = (String) table.getContentAt(2, 10);
        expected = "<HTML><small>???</small><small>? GE B</small></HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(3, 10);
        expected = "<HTML><small>???</small><small>? GE C4</small></HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(4, 10);
        expected = "<HTML><small>???</small><small>? GWR</small></HTML>";
        assertEquals(expected, courseName);

    }

    public void testColumn11()
    {
        String courseName = (String) table.getContentAt(0, 11);
        String expected = "<HTML>CSC492</HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(1, 11);
        expected = "<HTML><small>???</small><small>? TECH ELEC</small></HTML>";
        assertEquals(expected, courseName);


        courseName = (String) table.getContentAt(2, 11);
        expected = "<HTML><small>???</small><small>? SUPPORT</small></HTML>";
        assertEquals(expected, courseName);

        courseName = (String) table.getContentAt(3, 11);
        expected = "<HTML><small>???</small><small>? GE D2</small></HTML>";
        assertEquals(expected, courseName);


    }

    private final class ColorConverter extends DefaultTableCellValueConverter
    {
        @Override
        public Object getValue(int row, int column, java.awt.Component renderedComponent, java.lang.Object modelObject)
        {
            return renderedComponent.getBackground();
        }
    }
}
