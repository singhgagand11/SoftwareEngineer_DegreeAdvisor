/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.calpoly.razsoftware;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author jeremy
 */
public class FakeFlowchart implements I_Flowchart
{
    public void addQuarter(int index)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void build()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void buildFromDatabase()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteQuarter(int index)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int findCurrentQuarter()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Class getColumnClass(int c)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getColumnCount()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getColumnName(int column)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<I_Requirement> getList(int col)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getNumQuarters()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<I_Requirement> getRequirements()
    {
        return Arrays.asList((I_Requirement)new FakeRequirement(new FakeCourse()));
    }

    public int getRowCount()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public I_Requirement getValueAt(int row, int col)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean inSequence(I_Requirement req, int col)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isEligible(I_Requirement req, int col)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int moveCourse(int fromRow, int fromCol, int destRow, int destCol)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int moveCourseToColEnd(int fromRow, int fromCol, int destCol)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void renameQuarter(int index, String name)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setValueAt(Object value, int row, int col)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int unitsCompleted()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateRequirement(I_Requirement req)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public HashSet<Contingent> buildPriorCourses(int col, boolean considerCompletion)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String[] getQuarterNames()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
