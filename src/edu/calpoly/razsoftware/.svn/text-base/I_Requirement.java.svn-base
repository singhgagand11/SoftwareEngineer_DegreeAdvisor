/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.calpoly.razsoftware;

import java.util.HashSet;
import java.util.Set;

/**
 * Interface for Requirement
 * @author jeremy
 */
public interface I_Requirement extends Contingent
{
    /**
     * Accesssor to the selected Course
     * @return course that fulfills this requirement
     */
    I_Course getCourse();

    /**
     * A getter for the department directions field
     * @return The department's directs to ais the user in selecting a course
     */
    String getDeptDirections();

    /**
     * Accesssor to the courses that can fulfill the requirement
     * @return Gives the courses that can fulfill the requirement
     */
    Set<I_Course> getFulfillmentOptions();

    /**
     * Accesssor to the suggested quarter
     * @return Gives the quarter in which the class should be taken
     */
    int getQuarter();

    /**
     * Accesssor to the name of the requirement
     * @return Gives a string for requirements to fulfill prereqs for a course
     */
    String getTitle();

    /**
     * Evaluate if this Requirement is in the correct sequence (follows all prereqs)
     * @param prior list of all the requirements prior to this one in the flowchart
     * @return true if this item appears after all its prereqs
     */
    boolean inSequence(HashSet<Contingent> prior);

    /**
     * Evaluate if all prereqs have been satisfied (completed).
     * @param prior list of all the requirements prior to this one in the flowchart
     * @return true if all of this item's prereqs are satisfied.
     */
    boolean isEligible(HashSet<Contingent> prior);

    /**
     * Accessor to mutuallyExclusive
     * @return Checks if course fulfills multiple requirements
     */
    boolean isMutuallyExclusive();

    /**
     * Mutator to assign the selected Course
     * @param selected course to set this course to
     */
    void setCourse(I_Course selected);

    /**
     * A setter to set the department directions of the requirements
     * The department directions are a comment to help the user select
     * courses in the given requirement; for example
     *
     * @param setString department direction of the requirement
     */
    void setDeptDirections(String setString);

    /**
     * {@inheritDoc}
     */
    String toString();

    /** Get whether this requirement has been met
     *
     * @return true if this Requirement has been met
     */
    public boolean isCompleted();

    /**Set whether this requirement has been met
     *
     * @param completed whether this requirement has been met
     */
    public void setCompleted(boolean completed);

    /** Getter method for the requirement's prerequisites
     *
     * @return The set of requirements that must be fulfilled before
     * the user is eligible to complete this one.
     */
    public Set<Contingent> getPreRequisites();

    /** Setter method for the requirement's prerequisites
     *
     * @param preRequisites The set of requirements that must be fulfilled before
     * the user is eligible to complete this one.
     */
    public void setPreRequisites(Set<Contingent> preRequisites);

    /**
     * Sets the quarter the requirement should be taken 
     * @param quarter when the requirement should be taken
     */
    public void setQuarter(int quarter);
}
