/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.calpoly.razsoftware;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author jeremy
 */
public class FakeRequirement implements I_Requirement
{
    private final I_Course course;
    private boolean completed;

    public FakeRequirement(I_Course course)
    {
        this.course = course;
    }

    public I_Course getCourse()
    {
        return course;
    }

    public String getDeptDirections()
    {
        return "DeptDir";
    }

    public Set<I_Course> getFulfillmentOptions()
    {
        HashSet<I_Course> set = new HashSet<I_Course>();
        set.add(course);
        return set;
    }

    public int getQuarter()
    {
        return 1;
    }

    public String getTitle()
    {
        return "ReqTitle";
    }

    public boolean inSequence(HashSet<Contingent> prior)
    {
        return true;
    }

    public boolean isEligible(HashSet<Contingent> prior)
    {
        return true;
    }

    public boolean isMutuallyExclusive()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setCourse(I_Course selected)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setDeptDirections(String setString)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isCompleted()
    {
        return completed;
    }

    public void setCompleted(boolean completed)
    {
        this.completed = completed;
    }

    public Set<Contingent> getPreRequisites()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setPreRequisites(Set<Contingent> preRequisites)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setCourse(Course selected)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setQuarter(int quarter)
    {
    }
}
