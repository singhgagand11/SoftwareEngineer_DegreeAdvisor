package edu.calpoly.razsoftware;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Used in ControllerTest
 * @author Joe Walsh <jkwalsh@calpoly.edu>
 */
public class FakeCourse1 implements I_Course
{
    private boolean isComplete = false;
    private List<String> depts;
    private int number;
    private int units;
    private String title;
    private String desc;
    
    public FakeCourse1(List<String> dept, int number, int units, String title,
                  String description)
    {
        depts = dept;
        this.number = number;
        this.units = units;
        this.title = title;
        this.desc = description;
    }
    public int compareTo(I_Course c)
    {
        return 0;
    }

    public Set<Set<I_Course>> getCoRequisites()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<String> getDept()
    {
        return depts;
    }

    public String getDescription()
    {
        return desc;
    }

    public String getName()
    {
        return title;
    }

    public int getNumber()
    {
        return number;
    }

    public Set<Set<I_Course>> getPreRequisites()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getPreRequisitesString()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Set<Set<Contingent>> getPrereqs()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getTitle()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getUnits()
    {
        return units;
    }

    public boolean inSequence(HashSet<Contingent> prior)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isClass(String dept, int num)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isComplete()
    {
        return isComplete;
    }

    public boolean isEligible(HashSet<Contingent> prior)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

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
        isComplete = value;
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

    public void setUnits(int units)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setEligible(boolean value)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
