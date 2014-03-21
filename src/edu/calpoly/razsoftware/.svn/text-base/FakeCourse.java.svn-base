/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.calpoly.razsoftware;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author jeremy
 */
class FakeCourse implements I_Course
{
    public FakeCourse()
    {
    }

    public int compareTo(I_Course c)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Set<Set<I_Course>> getCoRequisites()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<String> getDept()
    {
        return Arrays.asList("XXX");
    }

    public String getDescription()
    {
        return "desc";
    }

    public String getName()
    {
        return "name";
    }

    public int getNumber()
    {
        return 101;
    }

    public Set<Set<I_Course>> getPreRequisites()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getPreRequisitesString()
    {
        return "pres";
    }

    public Set<Set<Contingent>> getPrereqs()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getTitle()
    {
        return "title";
    }

    private int units = 4;
    public void setUnits(int i)
    {
        units = i;
    }

    public int getUnits()
    {
        return units;
    }

    public boolean inSequence(HashSet<Contingent> prior)
    {
        return true;
    }

    public boolean isClass(String dept, int num)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isComplete()
    {
        return completed;
    }

    public boolean isEligible(HashSet<Contingent> prior)
    {
        return true;
    }
    private boolean completed;

    public boolean preRecsMet(Set<I_Course> coursesTaken)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setCoRequisites(Set<Set<I_Course>> in)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setCompleted(boolean value)
    {
        completed = value;
    }

    public void setPreRequisites(Set<Set<I_Course>> in)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setDept(List<String> dept)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTitle(String title)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setEligible(boolean value)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
