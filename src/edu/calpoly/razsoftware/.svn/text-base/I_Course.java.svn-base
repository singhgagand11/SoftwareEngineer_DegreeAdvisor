/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.calpoly.razsoftware;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Interface for Courses 
 * @author jeremy
 */
public interface I_Course extends Comparable<I_Course>, Contingent
{
    /**
     * {@inheritDoc}
     */
    int compareTo(I_Course c);

    /**
     * used to obtain a reference to the set of corequisites
     * @return set of coRequisites
     */
    Set<Set<I_Course>> getCoRequisites();

    /**
     * Gets the dept the course falls under
     * @return Returns the deptss the course falls under
     */
    List<String> getDept();

    /**
     * Gets the description of a course
     * @return returns a string with the description of the course
     */
    String getDescription();

    /**
     * Accessor to the course identifier (Dept and number)
     * @return name of the course
     */
    String getName();

    /**
     * Returns the number that goes before the dept tag
     * @return Returns an int with the number of the course
     */
    int getNumber();

    /**
     * Gets the set of prerequisites for a course
     * @return returns a set with the prerequisites
     */
    Set<Set<I_Course>> getPreRequisites();

    /**
     * Gets the prereqs in the form of a string
     * @return The prereqs in the form of a string
     */
    String getPreRequisitesString();

    /**
     * Gets list, list of prereqs where outer list has and dependency and
     * inner list has or dependency
     * @return list list of prerequisites
     */
    Set<Set<Contingent>> getPrereqs();

    /**
     * Gets the title of a course
     * @return Returns a string with the title of a course
     */
    String getTitle();

    /**
     * Gets the units of a course
     * @return an Int with the amount of units a course is worth
     */
    int getUnits();

    /**
     * Evaluate if this Course is in the correct sequence (follows all prereqs)
     * @param prior list of all the requirements prior to this one in the flowchart
     * @return true if this item appears after all its prereqs
     */
    boolean inSequence(HashSet<Contingent> prior);

    /**
     * Used to check if the course exists
     * @param dept Which dept department the course is in
     * @param num The number for the class
     * @return returns true if the course exists, otherwise false
     */
    boolean isClass(String dept, int num);

    /**
     * Accessor to find out if this course has been completed ("taken").
     * @return true if courses is complete, otherwise returns false
     */
    boolean isComplete();

    /**
     * Evaluate if all prereqs have been satisfied (completed).
     * @param prior list of all the requirements prior to this one in the flowchart
     * @return true if all of this item's prereqs are satisfied.
     */
    boolean isEligible(HashSet<Contingent> prior);

    /**
     * TODO I think this method will be obsolete because it now happens in Flowchart.
     * Used to check if the prereqs for a class has been met
     * @param coursesTaken The courses the user has taken
     * @return returns true if the prerecs have been met, otherwise false
     */
    boolean preRecsMet(Set<I_Course> coursesTaken);

    /**
     * Sets which courses should be taken in conjuction with the course
     * @param in The set of courses needed to be taken together
     */
    void setCoRequisites(Set<Set<I_Course>> in);

    /**
     * Sets this course either as completed or not completed
     *
     * @param value true if this course has been completed, false if it hasn't
     */
    void setCompleted(boolean value);

    /**
     * Sets the eligibility of the course
     *
     * @param value true if this course is eligible, false if it hasn't
     */
    void setEligible(boolean value);

    /**
     * Sets the prereqs according to the input
     * @param in The prereqs for a course
     */
    void setPreRequisites(Set<Set<I_Course>> in);

    /**
     * {@inheritDoc}
     */
    String toString();

    /**
     * sSet the course's department
     * @param dept Course department
     */
    void setDept(List<String> dept);

    /**
     * SetTitle
     * @param title Title of the course
     */
    void setTitle(String title);

    /**
     * Set the course's units
     * @param units The units of the course
     */
    void setUnits(int units);
}
