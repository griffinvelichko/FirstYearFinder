package persistence;

import model.Event;
import model.EventLog;
import model.ListOfStudent;
import model.Student;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Referenced JsonSerializationDemo application as provided in Phase 2 Task 3 to implement the following code
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ListOfStudent from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfStudent read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("Load saved list of students."));
        return parseListOfStudent(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ListOfStudent from JSON object and returns it
    private ListOfStudent parseListOfStudent(JSONObject jsonObject) {
        ListOfStudent los = new ListOfStudent();
        addStudents(los, jsonObject);
        return los;
    }

    // MODIFIES: los
    // EFFECTS: parses students from JSON object and adds them to generalList
    private void addStudents(ListOfStudent los, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("studentList");
        for (Object json : jsonArray) {
            JSONObject nextStudent = (JSONObject) json;
            addStudent(los, nextStudent);
        }
    }

    // MODIFIES: los
    // EFFECTS: parses student from JSON object and adds it to generalList
    private void addStudent(ListOfStudent los, JSONObject jsonObject) {
        String studentName = jsonObject.getString("studentName");
        int residence = jsonObject.getInt("residence");
        String interest1 = jsonObject.getString("interest1");
        String interest2 = jsonObject.getString("interest2");
        String username = jsonObject.getString("username");
        Student student = new Student(studentName, residence, interest1, interest2, username);
        los.addStudent(student, false);
    }
}
