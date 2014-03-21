package edu.calpoly.razsoftware.renderer;

import edu.calpoly.razsoftware.Contingent;
import edu.calpoly.razsoftware.Course;
import edu.calpoly.razsoftware.I_Course;
import edu.calpoly.razsoftware.I_Requirement;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author gagandeep
 */
public class FakeRequirement2 implements I_Requirement
{
    private I_Course course;
    private boolean sequence;
    private boolean eligible;
    private boolean completed;
    private int numOfCrs;

    public FakeRequirement2()
    {
    }

    public I_Course getCourse()
    {
        return course;
    }

    public String getDeptDirections()
    {
        return "CSC";
    }

    public Set<I_Course> getFulfillmentOptions()
    {
        HashSet hs = new HashSet<I_Course>();
        hs.add(course);
        return hs;

    }

    public int getQuarter()
    {
        return 0;
    }

    public String getTitle()
    {
        return "FakeReqTitle";
    }

    public boolean inSequence(HashSet<Contingent> prior)
    {
        return sequence;
    }

    public void setSequence()
    {
        sequence = !sequence;
    }

    public boolean isEligible(HashSet<Contingent> prior)
    {
        return eligible;
    }

    public void setEligible()
    {
        eligible = !eligible;
    }

    public boolean isMutuallyExclusive()
    {
        return false;
    }

    public void setCourse(I_Course selected)
    {
        this.course = selected;
    }

    public void setDeptDirections(String setString)
    {
    }

    public String toString()
    {
       return "FakeReq or FakeReq";
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

    public void setQuarter(int quarter)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}