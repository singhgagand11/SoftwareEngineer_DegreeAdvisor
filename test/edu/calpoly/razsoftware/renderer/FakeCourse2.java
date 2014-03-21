/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.calpoly.razsoftware.renderer;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import edu.calpoly.razsoftware.*;
/**
 *
 * @author gagandeep
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 

import java.util.*;

/**
 *
 * @author gagandeep
 */
public class FakeCourse2 extends Course
{

    public FakeCourse2()
    {
       super(new LinkedList<String>(), 1, 1, "Fake Course", "Fake Course");
    }
    
    static java.util.List<String> depts = new java.util.LinkedList<String>();

    static{
        depts.add("Fake");
        depts.add("Course");
    }
 
        
    public int compareTo(I_Course c)
    {
        return 0;
    }

    public Set<Set<I_Course>> getCoRequisites()
    {
        return new HashSet<Set<I_Course>>();
    }

    public List<String> getDept()
    {
        return FakeCourse2.depts;
    }

    public String getDescription()
    {
        return "Fake Description";
    }

    public String getName()
    {
        return "FakeCourse";
    }

    public int getNumber()
    {
        return 4;
    }

    public Set<Set<I_Course>> getPreRequisites()
    {
        return new HashSet<Set<I_Course>>();
    }

    public String getPreRequisitesString()
    {
        return "FakeCourse Preq";
                
    }

    public Set<Set<Contingent>> getPrereqs()
    {
        return null;
    }

    public String getTitle()
    {
        return "FakeCourse";
    }

    public int getUnits()
    {
        return 4;
    }

   
    public boolean inSequence(HashSet<Contingent> prior)
    {
        return false;
    }

    public boolean isClass(String dept, int num)
    {
        return false;
    }

    public boolean isComplete()
    {
        return false;
    }

   
    public boolean isEligible(HashSet<Contingent> prior)
    {
        return false;
    }

    public boolean preRecsMet(Set<I_Course> coursesTaken)
    {
        return false;
    }

    public void setCoRequisites(Set<Set<I_Course>> in)
    {
        
    }

    public void setCompleted(boolean value)
    {
        
    }

    public void setPreRequisites(Set<Set<I_Course>> in)
    {
        
    }

    public int compareTo(Course c)
    {
        return 0;
    }
    
    public String toString()
    {
        return "Fake Course |";
    }

 
}