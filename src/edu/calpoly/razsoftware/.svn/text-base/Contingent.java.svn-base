package edu.calpoly.razsoftware;
import java.util.HashSet;
import java.util.Set;
/**
 * Contingent items are Courses or Requirements.
 * They can be in sequence (or not).
 * They can be eligible to be taken (or not).
 *
 *
 * 99% of the time we assess whether the flowchart is in the correct sequence
 * by checking if all the courses follow their prerequisite courses. E.g., 102 has to
 * follow 101.
     * But consider that in some special cases, like 315, the prereq could actually
     * be a requirement (not a course), e.g. Comp Dsg & Assy Lang which itself could be
     * fulfilled by one of two courses.
     * Similarly, there are a few GE requirements that list other GE's as prereqs:
     * ENGL 149 (to fulfill GE A2) shows Prerequisite: Completion of GE Area A1
 * To deal with these unusual cases, this Interface defines the methods that
 * will allow flowchart validating to occur regardless of whether the dependency
 * chain involves courses or requirements.
  * @author jdalbey
 */
public interface Contingent
{
    /**  Evaluate if the Contingent is in the correct sequence (follows all prereqs)
     * @param prior list of all the requirements prior to this one in the flowchart
     * @return true if this item appears after all its prereqs
     */
    public boolean inSequence(HashSet<Contingent> prior);
    /** Evaluate if all prereqs have been satisfied (completed).
     * @param prior list of all the requirements prior to this one in the flowchart
     * @return true if all of this item's prereqs are satisfied.
     */
    public boolean isEligible(HashSet<Contingent> prior);
    

    
    
}

