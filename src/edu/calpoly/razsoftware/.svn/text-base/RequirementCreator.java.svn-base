package edu.calpoly.razsoftware;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

/**
 * Constructs a Requirement from a JSon file.
 * Adapter class to convert Requirement from JSOn to I_Requirement
 * @author jeremy
 */
public class RequirementCreator implements InstanceCreator<I_Requirement>,
                                           JsonDeserializer<I_Requirement>,
                                           JsonSerializer<I_Requirement>
{
    /**
     * Constructs an empty requirement.
     * @param type type of instance
     * @return an empty requirement
     */
    public I_Requirement createInstance(Type type)
    {
        return new Requirement(null, 0);
    }

    /**
     * Turns a Json element into a requirement.
     * @param je element to deserialize
     * @param type type of element (I_Requirement.class)
     * @param jdc deserialization context
     * @return a I_Requirement which was deserialized
     * @throws JsonParseException
     */
    public I_Requirement deserialize(JsonElement je,
                                     Type type,
                                     JsonDeserializationContext jdc) throws
            JsonParseException
    {
        // turn unknown element into json object
        JsonObject jo = je.getAsJsonObject();
        // IF given an empty requirement
        if (jo.get("requirementName") == null)
        {
            return null;
        }
        // SAVE requirementName from json object
        String name = jo.get("requirementName").getAsString();
        // SAVE mutuallyExclusive from json object
        boolean exclusive = jo.get("mutuallyExclusive").getAsBoolean();
        // SAVE quarter from json object
        int quarter = jo.get("quarter").getAsInt();
        // INIT set for fulfillment options
        HashSet<I_Course> courseSet = new HashSet<I_Course>();

        //INIT set for prerequisites
        HashSet<Contingent> preRequisites = new HashSet<Contingent>();

        // SAVE fulfillmentOptions array from json object
        JsonArray courses = jo.get("fulfillmentOptions").getAsJsonArray();

        // SAVE prerequisites array from json object
        JsonArray preReqs = jo.get("preRequisites").getAsJsonArray();

        // FOR each element in the options array
        for (JsonElement course : courses)
        {
            // ADD the deserialized course to the options set
            courseSet.add((I_Course) jdc.deserialize(course, I_Course.class));
        }

        // FOR each requirement in the preReqs array
        for (JsonElement req : preReqs)
        {
            // ADD the deserialized requirement to the prereqs set
            preRequisites.add((Contingent) jdc.deserialize(req, I_Requirement.class));
        }

        // INIT new requirement
        Requirement requirement = new Requirement(name, courseSet, exclusive, quarter);

        // SAVE selectedCourse as JsonObject
        JsonElement eSelectedCourse = jo.get("selectedCourse");
        if (eSelectedCourse != null)
        {
            I_Course selectedCourse = jdc.deserialize(eSelectedCourse, I_Course.class);
            requirement.setCourse(selectedCourse);
            requirement.setCompleted(selectedCourse.isComplete());
        }

        // IF there are dept directions
        if (jo.get("deptDirections") != null)
        {
            requirement.setDeptDirections(jo.get("deptDirections").getAsString());
        }

        // IF there are dept directions
        if (jo.get("completed") != null)
        {
            requirement.setCompleted(jo.get("completed").getAsBoolean());
            I_Course course = requirement.getCourse();
            if (course != null)
            {
                course.setCompleted(requirement.isCompleted());
            }
        }

        // IF there are prereqs
        if (preRequisites != null)
        {
            requirement.setPreRequisites(preRequisites);
        }

        // RETURN a new requirement using json values
        return requirement;
    }

    /**
     * Turns a requirement into a Json element.
     * @param t requirement to serialize
     * @param type type of requirement (I_Requirement.class)
     * @param jsc serialization context
     * @return a Json element
     */
    public JsonElement serialize(I_Requirement t,
                                 Type type, JsonSerializationContext jsc)
    {
        JsonObject jo = jsc.serialize(t).getAsJsonObject();
        JsonElement je = jsc.serialize(t.getFulfillmentOptions(), Set.class);
        jo.add("fulfillmentOptions", je);
        // IF there is a selected course
        if (t.getCourse() != null)
        {
            // SET the requirement as
            jo.addProperty("completed", t.getCourse().isComplete());
        }
        return jo;
    }
}
