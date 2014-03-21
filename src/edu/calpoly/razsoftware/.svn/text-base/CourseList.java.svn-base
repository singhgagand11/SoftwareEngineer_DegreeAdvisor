package edu.calpoly.razsoftware;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.AbstractListModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Represents a list of easily referenceable courses that can be constructed
 * from various sources. The list of courses represents a catalog for courses
 * available or courses that have been taken. <p> Operations: <ul> <li>A
 * CourseList can be the catalog of courses.<br> <li>A CourseList can be the
 * list of courses that a user has taken. </ul>
 *
 * @author aspurgin
 * @version $Revision: 200 $
 */
public class CourseList extends AbstractListModel
{
    /**
     * contains all courses in this list
     */
    private Set<I_Course> courses;

    /**
     * blank constructor, instantiates private local classes
     */
    public CourseList()
    {
        courses = new TreeSet<I_Course>();
    }

    /**
     * constructs a catalog from a collection of Course objects. Behaves similar
     * to Collection constructor
     *
     * @param in collection of courses @postcondition inner set contains
     * references to supplied courses
     */
    public CourseList(Collection<I_Course> in)
    {
        this();
        // FOR every course in the collection
        for (I_Course course : in)
        {
            // add course to the catalog
            courses.add(course);
        }
        // signal the controller
        this.fireContentsChanged(this, 0, courses.size());
    }

    /**
     * constructs a CourseList from a JSON input stream
     *
     * @precondition JSON stream must be correctly formatted @precondition JSON
     * objects from stream much match Course class
     *
     * @param input the JSON input stream
     */
    public CourseList(InputStream input)
    {
        this();
        Gson gson = new GsonBuilder().registerTypeAdapter(
                I_Course.class,
                new CourseCreator(this)).create();
        //Gson gson = new Gson();
        Scanner streamReader = null;


        streamReader = new Scanner(input);

        // WHILE input has another line DO
        while (streamReader.hasNextLine())
        {
            // PARSE a course from the current line
            I_Course addMe = gson.fromJson(streamReader.nextLine(), I_Course.class);
            // IF the list does not have a course with the major and number THEN
            if (lookUp(addMe.getDept().get(0), addMe.getNumber()) == null)
            {
                // ADD the course to the list
                courses.add(addMe);
            } // ENDIF
        } // ENDWHILE

        // DETERMINE references for prerequisites
        completePointers();
    }

    /**
     * Returns true if the set contains the course passed as a parameter
     *
     * @param c the course to look for
     * @return true if the list contains the course
     */
    public boolean contains(I_Course c)
    {
        return courses.contains(c);
    }

    /**
     * adds a single course into the set, fires the model update
     *
     * @param c the course
     */
    public void add(I_Course c)
    {
        courses.add(c);
        this.fireContentsChanged(this, 0, courses.size());
    }

    /**
     * adds all courses in a collection, fires the model update
     *
     * @param addedCourses a collection of courses
     */
    public void addAll(Collection<I_Course> addedCourses)
    {
        // FOR each Course in the collection
        for (I_Course course : addedCourses)
        {
            // add the course to the list
            add(course);
        }
        this.fireContentsChanged(this, 0, courses.size());
    }

    /**
     * removes a course from the set, fires the model update
     *
     * @param c the course to be removed
     */
    public void remove(I_Course c)
    {
        courses.remove(c);
        this.fireContentsChanged(this, 0, courses.size());
    }

    /**
     * removes a collection of courses from the set
     *
     * @param removedCourses a collection of courses
     */
    public void removeAll(Collection<I_Course> removedCourses)
    {
        // FOR each Course in the collection
        for (I_Course course : removedCourses)
        {
            // remove the course from the list
            remove(course);
        }
        filtered = null;

        this.fireContentsChanged(this, 0, courses.size());
    }

    /**
     * clears the CourseList, notifies the model
     * @postcondition Courselist is now empty
     */
    public void clear()
    {
        courses = new TreeSet<I_Course>();
        filtered = null;

        this.fireContentsChanged(this, 0, courses.size());
    }

    /**
     * gives a reference of the current catalog
     *
     * @return the catalog
     */
    public Set<I_Course> getCourses()
    {
        //RETURN this course
        return this.courses;

    }

    /**
     * replaces course keys attained from JSON file with class references from
     * CourseList. This MUST be done before attempting to use with any decision
     * making code
     * @precondition courselist is not empty
     * @postcondition all courses' requisites are now pointers to other courses
     */
    private void completePointers()
    {
        // INITIALIZE coursearray to the contents of the course list
        I_Course[] coursearray = new I_Course[0];
        coursearray = courses.toArray(coursearray);
        // FOR each course in coursearray
        for (I_Course course : coursearray)
        {
            // GET the prerequisites of the course
            Set<Set<I_Course>> preR = course.getPreRequisites();
            // INITIALIZE newPreR to the empty set
            Set<Set<I_Course>> newPreR = new HashSet<Set<I_Course>>();
            reformatPreReqs(preR, coursearray, course, newPreR);
            course.setPreRequisites(newPreR);

            /*
             * workin with the coreqs
             */

            // pseudocode is same as above section, just working with different
            // part
            Set<Set<I_Course>> coR = course.getCoRequisites();
            Set<Set<I_Course>> newCoR = new HashSet<Set<I_Course>>();
            reformatCoReqs(preR, coR, coursearray, course, newCoR);
            course.setCoRequisites(newCoR);
        }
    }

    /**
     * reformats the corequisites to conform to the correct structure where each
     * top level set needs to have at least one completed set inside. this
     * method was refactored out of a part of another method to reduce
     * cyclomatic complexity
     *
     * @param coR coreq data structure
     * @param coursearray array of courses being used to build references
     * @param course current course being worked with
     * @param newCoR new corequisite structure
     */
    private void reformatCoReqs(Set<Set<I_Course>> preR,
                                Set<Set<I_Course>> coR,
                                I_Course[] coursearray,
                                I_Course course,
                                Set<Set<I_Course>> newCoR)
    {
        //IF the prerequisite structure is not null THEN
        if (preR != null)
        {
            //FOR each set of courses in the corequisites
            for (Set<I_Course> sc : coR)
            {
                HashSet<I_Course> sub = new HashSet<I_Course>();
                //FOR each Course in the set
                for (I_Course c1 : sc)
                {
                    boolean found = false;
                    //FOR each course
                    for (I_Course lookup : coursearray)
                    {
                        // IF the coursenumber of the current course does not
                        //equal the Course number of the currently looked up
                        // course THEN
                        if (lookup.getNumber() != course.getNumber()
                                && lookup.getNumber() == c1.getNumber())
                        {
                            //FOR every major string in the course
                            for (String major : lookup.getDept())
                            {
                                //IF the cource major contains the major string
                                if (c1.getDept().contains(major))
                                {
                                    sub.add(lookup);
                                    found = true;
                                    break;
                                }
                            }
                        }
                        //IF the course has been found, THEN
                        if (found)
                        {
                            break;
                        }
                    }
                }
                newCoR.add(sub);
            }
        }
    }

    /**
     * reformats the prerequisites to conform to the correct structure where
     * each top level set needs to have at least one completed set inside. this
     * method was refactored out of a part of another method to reduce
     * cyclomatic complexity
     *
     * @param preR prereq data structure
     * @param coursearray array of courses being used to build references
     * @param course current course being worked with
     * @param newPreR new prerequisite structure
     */
    private void reformatPreReqs(Set<Set<I_Course>> preR,
                                 I_Course[] coursearray,
                                 I_Course course,
                                 Set<Set<I_Course>> newPreR)
    {
        // IF the course has prerequisites
        if (preR != null)
        {
            // FOR each 'or' set of courses in the prerequisites of the
            // course
            for (Set<I_Course> sc : preR)
            {
                // INITIALIZE sub to the empty set
                HashSet<I_Course> sub = new HashSet<I_Course>();
                // FOR each course in the set of 'or' courses
                for (I_Course c1 : sc)
                {
                    // INITIALIZE found to false
                    boolean found = false;
                    // FOR each course in coursearray
                    for (I_Course lookup : coursearray)
                    {
                        // IF the coursenumber of the current course does
                        // not equal
                        // the Course number of the currently looked up
                        // course THEN
                        if (lookup.getNumber() != course.getNumber()
                                && lookup.getNumber() == c1.getNumber())
                        {
                            // FOR every major String in the lookup course
                            for (String majorString : lookup.getDept())
                            {
                                // IF the course to be looked up contains
                                // the string
                                // from the lookup course THEN
                                if (c1.getDept().contains(majorString))
                                {
                                    // add lookup to sub
                                    sub.add((Course) lookup);
                                    // SET found to TRUE
                                    found = true;
                                    break;
                                }
                                // ENDIF
                            }
                            // ENDFOR
                        }
                        // ENDIF

                        // IF found is true
                        if (found)
                        {
                            break;
                        }
                        // ENDIF
                    }
                    // ENDFOR
                }
                // ENDFOR
                newPreR.add(sub);
            }
            // ENDFOR
        } // ENDIF
    }

    /**
     * returns a full course object from the course list based off the key of
     * that course. a key is simply one of the majors and the number
     *
     * @param major abbreviation of major
     * @param number course number
     * @return the full course reference
     */
    public I_Course lookUp(String major, int number)
    {
        // FOR each item in the catalog
        for (I_Course course : courses)
        {
            // IF the number matches the desired number THEN
            if (course.getNumber() == number)
            {
                // FOR every major in that course
                for (String majorString : course.getDept())
                {
                    // IF the major is the same as the lookup THEN
                    if (major.equalsIgnoreCase(majorString))
                    {
                        return course;
                    }
                }
                // ENDFOR
            }
            // ENDIF
        }
        // ENDFOR
        return null;
    }

    /**
     * Allow the Course List to be used to represent both a filtered and and
     * un-filtered list
     *
     * @return the correct length depending on the type of list
     */
    @Override
    public int getSize()
    {
        // IF filtered is null THEN
        if (filtered == null)
        {
            return courses.size();
        }
        // ELSE
        else
        {
            return filtered.size();
        }
        // ENDIF
    }
    /**
     * list of filtered classes, populated when a filter is requested
     */
    private List<I_Course> filtered;

    /**
     * Accessor to the filtered list of courses
     *
     * @return the filtered list of courses
     */
    List<I_Course> getFiltered()
    {
        return filtered;

    }

    /**
     * Allow the Course List to be used to represent both a filtered and and
     * un-filtered list
     *
     * @param i the index of the element to return
     * @return the correct element depending on the type of list
     */
    @Override
    public Object getElementAt(int i)
    {
        // IF filtered is null
        if (filtered == null)
        {
            return courses.toArray()[i];
        }
        // ELSE
        else
        {
            return filtered.get(i);
        }
        // ENDIF

    }

    /**
     * Filters the Courses that don't match the given string out of the list
     * that this model presents to it's listeners
     *
     * @param text The text to filter by
     */
    public void filterList(String text)
    {
        System.out.println("Filtering");
        // INITIALIZE filtered as new list
        filtered = new ArrayList<I_Course>();

        filtered = new ArrayList<I_Course>();
        // FOR every course in the list
        for (I_Course course : courses)
        {
            // IF text is not empty
            if (!text.equals(""))
            {
                // IF the course contains the text, THEN
                if (course.toString().toLowerCase().contains(text.toLowerCase()))
                {
                    // add c to the filtered list
                    filtered.add(course);
                }
            }//ELSE everything matches
            else
            {
                filtered.add(course);
            }// ENDIF
        }// ENDFOR

        this.fireContentsChanged(this, 0, filtered.size());

    }
}
