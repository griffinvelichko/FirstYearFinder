package persistence;

import org.json.JSONObject;

// Referenced JsonSerializationDemo application as provided in Phase 2 Task 3 to implement the following code
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
