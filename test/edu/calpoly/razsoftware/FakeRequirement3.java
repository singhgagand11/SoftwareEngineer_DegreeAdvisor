package edu.calpoly.razsoftware;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author gagandeep
 */
public class FakeRequirement3 implements I_Requirement
{
    private Course course;
    private boolean sequence;
    private boolean eligible;

    public FakeRequirement3()
    {
        course = new FakeCourse3();
    }

    public FakeRequirement3(Course crs)
    {
        new FakeRequirement3();
    }

    public Course getCourse()
    {
        return course;
    }

    public String getDeptDirections()
    {
        return "Dep Dir";
    }

    public Set<I_Course> getFulfillmentOptions()
    {
        HashSet hs = new HashSet<I_Course>();
        for (int i = 0; i < 10; i++)
        {
            hs.add(new FakeCourse3());
        }
        return hs;
    }

    public int getQuarter()
    {
        return 0;
    }

    public String getTitle()
    {
        return "";
    }

    public boolean inSequence(HashSet<Contingent> prior)
    {
        return sequence;
    }

    public void setSequence()
    {
        sequence = true;
    }

    public boolean isEligible(HashSet<Contingent> prior)
    {
        return eligible;
    }

    public void setEligible()
    {
        eligible = true;
    }

    public boolean isMutuallyExclusive()
    {
        return false;
    }

    public void setCourse(I_Course selected)
    {
    }

    public void setDeptDirections(String setString)
    {
    }

    public String toString()
    {
        return "FakeReq";
    }

    public boolean isCompleted()
    {
        return true;
    }

    public void setCompleted(boolean completed)
    {
    }

    public Set<Contingent> getPreRequisites()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setPreRequisites(Set<Contingent> preRequisites)
    {
    }

    public void setQuarter(int quarter)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}