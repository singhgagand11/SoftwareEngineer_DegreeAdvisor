package edu.calpoly.razsoftware;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author jeremy
 */
class CourseCreator implements InstanceCreator<I_Course>, JsonDeserializer<I_Course>
{
    private CourseList list;

    CourseCreator(CourseList list)
    {
        this.list = list;
    }

    public I_Course createInstance(Type type)
    {
        return new Course(Arrays.asList("CSC"), 0, 0, "title", "desc");
    }

    public I_Course deserialize(JsonElement je,
                                Type type,
                                JsonDeserializationContext jdc) throws
            com.google.gson.JsonParseException
    {
        // turn unknown element into object
        JsonObject jo = je.getAsJsonObject();

        // IF dept is not set, and is an invalid course
        if (jo.get("dept") == null)
        {
            // create a dummy course
            return createInstance(type);
        }

        // ELSE dept is set, is a course     
        JsonArray ja = jo.get("dept").getAsJsonArray();
        int number = get(jo, "number", 0);

        I_Course course = null;
        // INIT list of department names, to use in lookup/creation
        ArrayList<String> depts = new ArrayList<String>();
        // FOR each json department name
        for (JsonElement dept : ja)
        {
            // ADD department as string
            depts.add(dept.getAsString());
        }

        // check if course already exists in the list of courses
        if (list != null)
        {
            course = list.lookUp(depts.get(0), number);
        }

        // IF course already exists in the list of courses
        if (course != null)
        {
            // RETURN course from list of courses
            return course;
        }
        // ELSE initial creation of course
        else
        {
            // INIT a course using json values
            course = new Course(
                    depts,
                    get(jo, "number", 0),
                    get(jo, "units", 4),
                    get(jo, "name", get(jo, "title", "Title")),
                    get(jo, "description", "Desc"));
        }

        course.setCompleted(get(jo, "completed", false));

        // GET prereqs from json object
        JsonElement nullCheck = jo.get("preRequisites");
        // IF prereqs are listed
        if (nullCheck != null)
        {
            // SET prereqs using json values
            course.setPreRequisites(getReqs(nullCheck.getAsJsonArray(), type, jdc));
        }
        // GET coreqs from json object
        nullCheck = jo.get("coRequisites");
        // IF coreqs are listed
        if (nullCheck != null)
        {
            // SET coreqs using json values
            course.setCoRequisites(getReqs(nullCheck.getAsJsonArray(), type, jdc));
        }

        // RETURN created or retrieved course
        return course;

    }

    private Set<Set<I_Course>> getReqs(JsonArray outside, Type type,
                                       JsonDeserializationContext jdc)
            throws JsonParseException
    {
        // INIT a set for OR reqs
        Set<Set<I_Course>> sets = new HashSet<Set<I_Course>>();

        // FOR each element in the outer set
        for (JsonElement inside : outside)
        {
            // INIT a set for AND reqs
            HashSet<I_Course> set = new HashSet<I_Course>();
            // FOR each element in the inner set
            for (JsonElement req : inside.getAsJsonArray())
            {
                // ADD it to the AND reqs
                set.add(deserialize(req, type, jdc));
            }
            // ADD the inner set to the outer set
            sets.add(set);
        }
        // RETURN requisites set
        return sets;
    }

    private int get(JsonObject jo, String element, int def)
    {
        // GET given element from josn object
        JsonElement je = jo.get(element);
        // IF the element exists
        if (je != null)
        {
            // RETURN it as an integer
            return je.getAsInt();
        }
        // RETURN the default
        return def;
    }

    private String get(JsonObject jo, String element, String def)
    {
        // GET given element from josn object
        JsonElement je = jo.get(element);
        // IF the element exists
        if (je != null)
        {
            // RETURN it as an string
            return je.getAsString();
        }
        // RETURN the default
        return def;
    }

    private boolean get(JsonObject jo, String element, boolean def)
    {
        // GET given element from josn object
        JsonElement je = jo.get(element);
        // IF the element exists
        if (je != null)
        {
            // RETURN it as an boolean
            return je.getAsBoolean();
        }
        // RETURN the default
        return def;
    }
}
