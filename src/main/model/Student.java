package model;

import org.json.JSONObject;
import persistence.Writable;

public class Student implements Writable {
    private final String studentName;
    private final int residence;
    private final String interest1;
    private final String interest2;
    private final String username;


    //REQUIRES: studentName is unique from every other student,
    //          residence is either 1 for Orchard Commons, 2 for Totem Park, or 3 for Place Vanier
    //EFFECTS: creates a Student with unique studentName, one of 3 residences,
    //         2 interests, and an Instagram username
    public Student(String studentName, int residence, String interest1, String interest2, String username) {
        this.studentName = studentName;
        this.residence = residence;
        this.interest1 = interest1.toLowerCase();
        this.interest2 = interest2.toLowerCase();
        this.username = username;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getResidence() {
        return residence;
    }

    public String getInterest1() {
        return interest1;
    }

    public String getInterest2() {
        return interest2;
    }

    public String getUsername() {
        return username;
    }

    // Referenced JsonSerializationDemo application as provided in Phase 2 Task 3 to implement the following code
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("studentName", studentName);
        json.put("residence", residence);
        json.put("interest1", interest1);
        json.put("interest2", interest2);
        json.put("username", username);
        return json;
    }
}
