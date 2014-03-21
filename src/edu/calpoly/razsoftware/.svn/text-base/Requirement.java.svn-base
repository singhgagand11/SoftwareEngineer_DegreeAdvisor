package edu.calpoly.razsoftware;

import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;

/**
 * The Requirement class holds the set of courses that can be taken to fulfill a
 * requirement. A Requirement also has a name, whether it is mutually exclusive,
 * a department description, and which quarter the college recommends you take
 * the option in.
 *
 * @author rlight jtbiddle
 * @version $Revision: 200 $
 */
public class Requirement implements I_Requirement
{
    /**
     * The description of the course option as it appears in the flowchart.
     */
    private String requirementName;
    /**
     * The set of courses that can fulfill the option.
     */
    private Set<I_Course> fulfillmentOptions;
    /**
     * The set of requirements that must be fulfilled before the user is
     * eligible to complete this one.
     */
    private Set<Contingent> preRequisites;
    /**
     * Whether or not fulfilling this option precludes using the course for
     * fulfilling another course option.
     */
    private boolean mutuallyExclusive;
    /**
     * The recommended quarter to take the course option in. 1 is fall quarter
     * of freshman year.
     */
    private int quarter;
    /**
     * A specific course that the student has chosen to fulfull this
     * requirement.
     */
    private I_Course selectedCourse;
    /**
     * Directions to help the user select a course for this requirement
     *
     */
    private String deptDirections;

    /**
     * A getter for the department directions field
     *
     * @return The department's directs to ais the user in selecting a course
     */
    @Override
    public String getDeptDirections()
    {
        return deptDirections;
    }

    /**
     * A setter to set the department directions of the requirements The
     * department directions are a comment to help the user select courses in
     * the given requirement; for example
     *
     * @param setString The department directions description
     */
    public void setDeptDirections(String setString)
    {
        deptDirections = setString;
    }

    /**
     * Constructs an new option that only holds one course
     *
     * @param onlyCourse the course in this option
     * @param quarterToTake the quarter the flowchart recommends for the course
     */
    public Requirement(I_Course onlyCourse, int quarterToTake)
    {
        this.requirementName = onlyCourse.toString();
        this.fulfillmentOptions = new TreeSet<I_Course>();
        this.fulfillmentOptions.add(onlyCourse);
        this.mutuallyExclusive = true;
        this.quarter = quarterToTake;
        this.selectedCourse = onlyCourse;
        this.deptDirections = "";
        this.preRequisites = new HashSet<Contingent>();
    }

    /**
     * Constructs a new option that holds multiple courses
     *
     * @param name The name of the requirement
     * @param options the courses that can fulfill this requirement
     * @param mutuallyExclusive does this fulfills multiple requirements or not
     * @param quarterToTake the quarter the flowchart recommends for the course
     */
    public Requirement(String name, Set<I_Course> options,
                       boolean mutuallyExclusive, int quarterToTake)
    {
        this.requirementName = name;
        this.fulfillmentOptions = options;
        this.mutuallyExclusive = mutuallyExclusive;
        this.quarter = quarterToTake;
        //IF there are one way to fulfill the requirement THEN
        if (fulfillmentOptions.size() == 1)
        {
            this.selectedCourse = fulfillmentOptions.toArray(new I_Course[0])[0];
        }
        //ENDIF
        this.deptDirections = "";
        this.preRequisites = new HashSet<Contingent>();

    }

    /**
     * Constructs a NEW requirement with deptDirections and prereqs included
     *
     * @param name The name of the requirement
     * @param options the courses that can fulfill this requirement
     * @param mutuallyExclusive does this fulfills multiple requirements or not
     * @param quarterToTake the quarter the flowchart recommends for the course
     * @param prereqs the prerequisites of the given requirement
     * @param deptDir the special deptDirections associated with the requirement
     */
    public Requirement(String name,
                       Set<I_Course> options,
                       boolean mutuallyExclusive,
                       int quarterToTake,
                       Set<Contingent> prereqs,
                       String deptDir)
    {
        this.requirementName = name;
        this.fulfillmentOptions = options;
        this.mutuallyExclusive = mutuallyExclusive;
        this.quarter = quarterToTake;
        this.preRequisites = prereqs;
        this.deptDirections = deptDir;
        this.selectedCourse = null;
    }

    /**
     * Accessor to mutuallyExclusive
     *
     * @return Checks if course fulfills multiple requirements
     */
    @Override
    public boolean isMutuallyExclusive()
    {
        return mutuallyExclusive;
    }

    /**
     * Accesssor to the courses that can fulfill the requirement
     *
     * @return Gives the courses that can fulfill the requirement
     */
    @Override
    public Set<I_Course> getFulfillmentOptions()
    {
        return fulfillmentOptions;
    }

    /**
     * Accesssor to the name of the requirement
     *
     * @return Gives a string for requirements to fulfill prereqs for a course
     */
    @Override
    public String getTitle()
    {
        return requirementName;
    }

    /**
     * Accesssor to the selected Course
     *
     * @return course that fulfills this requirement
     */
    @Override
    public I_Course getCourse()
    {
        return selectedCourse;
    }

    /**
     * Mutator to assign the selected Course
     *
     * @param selected The course the user is taking to fulfill this requirement
     */
    public void setCourse(I_Course selected)
    {
        selectedCourse = selected;
    }

    /**
     * Accesssor to the suggested quarter
     *
     * @return Gives the quarter in which the class should be taken
     */
    @Override
    public int getQuarter()
    {
        return quarter;
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

        //IF all of the prerequisites are in the list of prereqs DO
        if (prior.containsAll(preRequisites))
        {
            //SET the "retValid" boolean to true
            retValid = true;
        }
        //END IF

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
        //INIT to false A "retValid" boolean that we'll return
        boolean retValid = true;

        //IF there are no prerequisites DO
        if (preRequisites.isEmpty())
        {
            //RETURN true
            return retValid;
        }

        //FOR each set of ANDed courses in prerequisites
        for (Contingent current : preRequisites)
        {
            //IF the current course isn't in the list of prior courses THEN
            if (!prior.contains(current))
            {
                //SET the "validReq" boolean to false
                retValid = false;
            }
            //ELSE
            else
            {
                //IF the current prereq is a requirement and it hasn't been fulfilled
                if (current.getClass().equals(Requirement.class)
                        && !((Requirement) current).isCompleted())
                {
                    //SET the "validReq" boolean to false
                    retValid = false;
                }
                //END IF
                //ELSE IF the current prereq is a course and it hasn't been fulfilled
                else if (current.getClass().equals(Course.class)
                        && !((Course) current).isComplete())
                {
                    //SET the "validReq" boolean to false
                    retValid = false;
                }
                //END IF
            }
            //END IF
        }
        //END LOOP

        //return "retValid" boolean that indicates this requirement is eligible
        return retValid;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        // INITIALIZE req to the empty string
        String req = "";
        String orSeparator = " or ";

        // IF the set of fulfillment is not empty THEN
        if (fulfillmentOptions.size() > 0)
        {
            // FOR each course in the set of fulfillment options
            for (I_Course fulfilmentCourse : fulfillmentOptions)
            {
                // CONCATENATE the course's string representation with " or "
                // and req
                req += fulfilmentCourse.toString() + orSeparator;
            }// ENDFOR

            // REMOVE the last " or " from the string
            req = req.substring(0, req.length() - orSeparator.length());
        } // ENDIF

        return req;
    }
    /**
     * Whether this requirement has been met
     */
    private boolean completed;

    /**
     * Returns whether this requirement has been fulfilled
     *
     * @return true if this requiremeqnt has been completed
     */
    public boolean isCompleted()
    {
        return completed;
    }

    /**
     * Set whether this requirement has been fulfilled
     *
     * @param completed boolean whether this requirement has been completed
     */
    public void setCompleted(boolean completed)
    {
        this.completed = completed;
    }

    /**
     * Returns this requirment's prerequisites
     *
     * @return the set of requirements and courses that must be fulfilled before
     * the user is eligible to take this requirement
     */
    public Set<Contingent> getPreRequisites()
    {
        return preRequisites;
    }

    /**
     * Set the prerequisite courses and requirements for this requirement
     *
     * @param preRequisites the set of requirements and courses that must be
     * fulfilled before the user is eligible to take this requirement
     */
    public void setPreRequisites(Set<Contingent> preRequisites)
    {
        this.preRequisites = preRequisites;
    }

    /**
     * Tester function used to print out information to the console
     * @return debug messages
     */
    public String debug()
    {
        return super.toString();
    }

    /**
     * Sets the quarter the requirement should be taken
     * @param quarter when the requirement should be taken
     */
    public void setQuarter(int quarter)
    {
        this.quarter = quarter;
    }
}
