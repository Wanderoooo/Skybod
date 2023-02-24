package persistence;

import model.Pilot;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// Represents a reader that reads pilot from JSON data stored in file
// CREDIT: code template from WorkRoomApp from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
        private String source;

        // EFFECTS: constructs reader to read from source file
        public JsonReader(String source) {
            this.source = source;
        }

        // EFFECTS: reads pilot from file and returns it;
        // throws IOException if an error occurs reading data from file
        public Pilot read() throws IOException {
            String jsonData = readFile(source);
            JSONObject jsonObject = new JSONObject(jsonData);
            return parsePilot(jsonObject);
        }

        // EFFECTS: reads source file as string and returns it
        private String readFile(String source) throws IOException {
            StringBuilder contentBuilder = new StringBuilder();

            try (Stream<String> stream = Files.lines( Paths.get(source), StandardCharsets.UTF_8)) {
                stream.forEach(s -> contentBuilder.append(s));
            }

            return contentBuilder.toString();
        }

        // EFFECTS: parses pilot from JSON object and returns it
        private Pilot parsePilot(JSONObject jsonObject) {
            String name = jsonObject.getString("name");
            JSONArray ratings = jsonObject.getJSONArray("ratings");
            int mednum = jsonObject.getInt("medical#");
            // ...
            // ...
            // ... to get all fields for Pilot

            Pilot p = new Pilot();
            p.setName(name);
            p.setRatings(ratings); // <- how to convert JSONarray back to HashSet<>?
            // ...
            // ...
            // ... to set all fields for Pilot

            return p;
        }

        // MODIFIES: wr
        // EFFECTS: parses thingies from JSON object and adds them to workroom
        private void addThingies(WorkRoom wr, JSONObject jsonObject) {
            JSONArray jsonArray = jsonObject.getJSONArray("thingies");
            for (Object json : jsonArray) {
                JSONObject nextThingy = (JSONObject) json;
                addThingy(wr, nextThingy);
            }
        }

        // MODIFIES: wr
        // EFFECTS: parses thingy from JSON object and adds it to workroom
        private void addThingy(WorkRoom wr, JSONObject jsonObject) {
            String name = jsonObject.getString("name");
            Category category = Category.valueOf(jsonObject.getString("category"));
            Thingy thingy = new Thingy(name, category);
            wr.addThingy(thingy);
        }
    }

}
