package persistence;

import org.json.JSONObject;

// Interface for toJson() method implementation in all model classes
// CREDIT: code template from WorkRoomApp from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public interface Writable {

    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
