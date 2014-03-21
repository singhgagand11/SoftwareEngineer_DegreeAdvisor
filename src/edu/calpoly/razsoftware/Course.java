package edu.calpoly.razsoftware;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

/**
 * Represents a course the user can take. A course has at least one dept that it
 * is listed as, a number representing its course number, a number of units, a
 * title, a description, and a representation of its prerequisites.
 *
 * Revision: fixed isEligible to correctly check if prereqs set is meet.
 * Revision author: gasingh
 *
 * @author msvanbee jtbiddle
 * @version $Revision: 200 $
 */
public class Course implements I_Course
{
    /*
     * Represents the padding in pixles between courses
     */
    private static final int kPADDING = 4;

    /*
     * Represents the department that offers this course. A course can be
     * "cross-listed" under more than one department.
     */
    private List<String> dept;

    /*
     * Represnts the course number of this Course. Ex: 102 for CPE102
     */
    private int number;

    /*
     * Represnts the number of units for this Course. Ex: 4 for CPE 308
     */
    private int units;

    /*
     * Represents the title of this Course, e.g. "Discrete Math"
     */
    private String title;

    /*
     * Represents a description of the Course
     */
    private String description;

    /*
     * Represents a set of set of courses that are prerequisites for this Course
     * . The bottom set is "AND" and the top set is "OR".
     */
    private Set<Set<I_Course>> preRequisites;

    /*
     * Represents a set of set of courses that are corequisites for this Course
     * . The bottom set is "AND" and the top set is "OR".
     */
    private Set<Set<I_Course>> coRequisites;
    /**
     * Indicates if this course has been completed
     */
    private boolean completed;
    // DEFECT - needed satisfied field to override prereqs.
    private boolean satisfied;

    /**
     * Constructs a new Course used to represent a course the user can take
     *
     * @param dept The course tag for the dept
     * @param number The course number
     * @param units The number of units the course is worth
     * @param title The title of the course
     * @param description The description of the course
     */
    public Course(List<String> dept, int number, int units, String title,
                  String description)
    {
        this.dept = dept;
        this.number = number;
        this.units = units;
        this.title = title;
        this.description = description;
        this.completed = false;
        preRequisites = new HashSet<Set<I_Course>>();
        coRequisites = new HashSet<Set<I_Course>>();
    }

    /**
     * Constructs a new Course used to represent a course the user can take
     *
     * @param dept The course tag for the dept
     * @param number The course number
     */
    public Course(List<String> dept, int number)
    {
        this.dept = dept;
        this.number = number;
        preRequisites = new HashSet<Set<I_Course>>();
        coRequisites = new HashSet<Set<I_Course>>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(I_Course c)
    {
        return this.toString().compareTo(c.toString());
    }

    /**
     * Used to check if the course exists
     *
     * @param department Which department department the course is in
     * @param num The number for the class
     * @return returns true if the course exists, otherwise false
     */
    @Override
    public boolean isClass(String department, int num)
    {
        // IF num = the course's number THEN
        if (num == number)
        {
            // FOR each of the course's depts
            for (String deptString : this.dept)
            {
                // IF the dept is equal to dept (case-insensitive) THEN
                if (deptString.toUpperCase().equals(department.toUpperCase()))
                {
                    return true;
                }// ENDIF
            }// ENDFOR
        }// ENDIF


        return false;
    }

    /**
     * Accessor to find out if this course has been completed ("taken").
     *
     * @return true if this course has been completed, false otherwise
     */
    public boolean isComplete()
    {
        //Return the value of the "completed" boolean
        return completed;
    }

    /**
     * Sets this course either as completed or not completed
     *
     * @param value true if this course has been completed, false if it hasn't
     */
    public void setCompleted(boolean value)
    {
        //Set the "completed" boolean to the value of "value"
        completed = value;
    }

    /**
     * Evaluate if this Course is in the correct sequence (follows all prereqs)
     *
     * @param prior list of all the requirements prior to this one in the
     * flowchart
     * @return true if this item appears after all its prereqs
     */
    @Override
    public boolean inSequence(HashSet<Contingent> prior)
    {
        //INIT A "retValid" boolean to return to false
        boolean retValid = false;

        //Defect #305 needed to check if satisfied was checked so that prereqs
        // would override sequence statistics
        if(satisfied)
        {
            return true;
        }
        
        //Defect # 302
        //INIT an array that contains all of the prior requirements
        ArrayList<Contingent> allPriors = new ArrayList(Arrays.asList(prior.toArray()));


        //FOR each requriement in the prior list DO
        for (Contingent prereq : prior)
        {
            //If the requriement is not a single course
            if (prereq.getClass() == Requirement.class)
            {
                //Add all fulfillment options as being prior to this requirement
                allPriors.addAll(
                        ((Requirement) prereq).getFulfillmentOptions());
            }
        }

        //FOR each set of courses in prerequisites
        for (Set<I_Course> setCourses : preRequisites)
        {
            //IF all of the prerequisites are in the list of prior courses DO
            if (allPriors.containsAll(setCourses))
            {
                //SET the "retValid" boolean to true
                retValid = true;
            }

            //END IF
        }
        //END LOOP

        //IF there are no prerequisites DO
        if (preRequisites.isEmpty())
        {
            //SET "retValid" boolean to true
            retValid = true;
        }

        //RETURN "retValid" boolean that indicates this requirement is in sequence
        return retValid;
    }

    /**
     * Evaluate if all prereqs have been satisfied (completed).
     *
     * @param prior list of all the requirements prior to this one in the
     * flowchart
     * @return true if all of this item's prereqs are satisfied.
     */
    @Override
    public boolean isEligible(HashSet<Contingent> prior)
    {
        //IF the course is statisfied THEN
        if (satisfied)
        {
            //RETURN true
            return true;
        }
        //ENDIF


        //IF there are no prerequisites THEN
        if (preRequisites.isEmpty())
        {
            //RETURN true since the course is eligible
            return true;
        }
        //ENDIF

        //FOR each set of ANDed courses in prerequisites
        for (Set<I_Course> courseSet : preRequisites)
        {

            //SET reqToMeet to number of prereq's to meet
            int reqToMeet = courseSet.size();
            //FOR each course in the current set of courses DO
            for (I_Course currentCourse : courseSet)
            {

                //IF prior courses contains the current course and the
                //currentCourse is completed
                if (prior.contains(currentCourse) && currentCourse.isComplete())
                {
                    //decrement to reqToMeet
                    reqToMeet--;
                }
                //ENDIF
            }
            //END-FOR

            //IF all prereq's are meet for the current set THEN
            if (reqToMeet == 0)
            {
                //RETURN true
                return true;
            }
            //ENDIF
        }//END-FOR

        //return false since the course is not eligible
        return false;
    }

    /**
     * TODO I think this method will be obsolete because it now happens in
     * Flowchart. Used to check if the prereqs for a class has been met
     *
     * @param coursesTaken The courses the user has taken
     * @return returns true if the prerecs have been met, otherwise false
     */
    @Override
    public boolean preRecsMet(Set<I_Course> coursesTaken)
    {
        // INITIALIZE preRecMet to false
        boolean preRecMet = false;
        // IF the set of prerequisites is not empty THEN
        if (preRequisites != null && preRequisites.size() > 0)
        {
            // FOR each set of courses in the set of prerequisites
            for (Set<I_Course> courseSet : preRequisites)
            {
                // SET preRecMet to true
                preRecMet = true;
                // FOR each course in the set of courses
                for (I_Course prereqCourse : courseSet)
                {
                    // IF the set of courses taken does not contain the course
                    // THEN
                    if (!coursesTaken.contains(prereqCourse))
                    {
                        // SET preRecMet to false
                        preRecMet = false;
                    }// ENDIF
                }

                // IF preRecMet THEN
                if (preRecMet)
                {
                    return true;
                }
                // ENDIF
            } // ENDFOR

            return false;
        }
        else
        {
            return true;
        } // ENDIF
    }

    /**
     * used to obtain a reference to the set of corequisites
     *
     * @return set of coRequisites
     */
    @Override
    public Set<Set<I_Course>> getCoRequisites()
    {
        return coRequisites;
    }

    /**
     * Sets which courses should be taken in conjuction with the course
     *
     * @param in The set of courses needed to be taken together
     */
    @Override
    public void setCoRequisites(Set<Set<I_Course>> in)
    {
        coRequisites = in;
    }

    /**
     * Gets the title of a course
     *
     * @return Returns a string with the title of a course
     */
    @Override
    public String getTitle()
    {
        return title;
    }

    /**
     * Accessor to the course identifier (Dept and number)
     *
     * @return the department and number of this course, for example "CSC101"
     */
    public String getName()
    {
        return dept.get(0) + number;
    }

    /**
     * Gets the description of a course
     *
     * @return returns a string with the description of the course
     */
    @Override
    public String getDescription()
    {
        return description;
    }

    /**
     * Gets the dept the course falls under
     *
     * @return Returns the deptss the course falls under
     */
    @Override
    public List<String> getDept()
    {
        return dept;
    }

    /**
     * Returns the number that goes before the dept tag
     *
     * @return Returns an int with the number of the course
     */
    @Override
    public int getNumber()
    {
        return number;
    }

    /**
     * Gets the set of prerequisites for a course
     *
     * @return returns a set with the prerequisites
     */
    @Override
    public Set<Set<I_Course>> getPreRequisites()
    {
        return preRequisites;
    }

    /**
     * Gets the prereqs in the form of a string
     *
     * @return The prereqs in the form of a string
     */
    @Override
    public String getPreRequisitesString()
    {
        // INITIALIZE preRecStr to the empty string
        String preRecStr = "";

        // IF the set of prerequisites is not empty THEN
        if (preRequisites.size() > 0)
        {
            // FOR each set of courses in the set of prerequisites
            for (Set<I_Course> prereqSet : preRequisites)
            {
                // FOR each course in the set of courses
                for (I_Course prereqCourse : prereqSet)
                {
                    // CONCATENATE the title of the course and an ampersand to
                    // preRecStr
                    preRecStr += prereqCourse.toString() + "&";
                }// ENDFOR
                // IF preRecStr is not an empty string THEN
                if (preRecStr.length() != 0)
                {
                    // REMOVE the last character(&) of preRecStr
                    preRecStr = preRecStr.substring(0, preRecStr.length() - 1);
                } // ENDIF

                // CONCATENATE " or " with preRecStr
                preRecStr += " or ";

            } // ENDFOR


        }
        // IF preRecStr is not an empty string THEN
        if (preRecStr.length() != 0)
        {
            // REMOVE the last " or " from preRecStr
            preRecStr = preRecStr.substring(0, preRecStr.length() - kPADDING);
        } // ENDIF
        return preRecStr;
    }

    /**
     * Sets the prereqs according to the input
     *
     * @param in The prereqs for a course
     */
    @Override
    public void setPreRequisites(Set<Set<I_Course>> in)
    {
        preRequisites = in;
    }

    /**
     * Gets the units of a course
     *
     * @return an Int with the amount of units a course is worth
     */
    @Override
    public int getUnits()
    {
        return units;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return dept.get(0) + number;
    }

    /**
     * Getter for this courses prereqs
     * @return set, set of prerequisites of this course
     */
    public Set<Set<Contingent>> getPrereqs()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * sSet the course's department
     * @param dept Course department
     */
    public void setDept(List<String> dept)
    {
        this.dept = dept;
    }

    /**
     * SetTitle
     * @param title Title of the course
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Set the course's units
     * @param units The units of the course
     */
    public void setUnits(int units)
    {
        //SET this course's units to units
        this.units = units;
    }

    /**
     * Sets the eligibility of the course
     * @param value stating whether course is eligible to be taken or not
     */
    public void setEligible(boolean value)
    {
        //SET this course's satisfies to value
        satisfied = value;
    }
}
