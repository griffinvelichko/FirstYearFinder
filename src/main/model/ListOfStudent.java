package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import javax.swing.*;
import java.util.ArrayList;

public class ListOfStudent extends DefaultListModel<String> implements Writable {
    private final ArrayList<Student> studentList;

    //EFFECTS: creates an empty list of students
    public ListOfStudent() {
        this.studentList = new ArrayList<>();
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    //EFFECTS: if student of studentName is in ListOfStudent,
    //          return true, otherwise return false
    public boolean hasStudent(String studentName) {
        boolean hasStudent = false;
        for (Student s : studentList) {
            if (studentName.equals(s.getStudentName())) {
                hasStudent = true;
                break;
            }
        }
        return hasStudent;
    }

    //MODIFIES: this
    //EFFECTS: if student with the same name is not already in the list,
    //         add student to the list, otherwise do nothing
    public void addStudent(Student student, boolean isLogged) {
        if (!(hasStudent(student.getStudentName()))) {
            studentList.add(student);
            this.addElement(student.getStudentName());
            if (isLogged) {
                EventLog.getInstance().logEvent(new Event(student.getStudentName() + " was added to the list."));
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: if student with same name is in list, remove from the list, otherwise do nothing
    public void removeStudent(String name) {
        int index = 0;
        for (Student s : studentList) {
            if (name.equals(s.getStudentName())) {
                studentList.remove(s);
                this.removeElement(s.getStudentName());
                break;
            }
        }
    }

    //EFFECTS: returns length of list of student
    public int listLength() {
        return studentList.size();
    }

    public void removeAllStudents() {
        studentList.clear();
        this.removeAllElements();
        EventLog.getInstance().logEvent(new Event("All students were removed from the list."));
    }

    //EFFECTS: if Student is in studentList, return student of studentName, otherwise return null
    public Student getStudent(String name) {
        for (Student s : studentList) {
            if (name.equals(s.getStudentName())) {
                return s;
            }
        }
        return null;
    }

    //REQUIRES: residence is either 1 for Orchard Commons, 2 for Totem Park, or 3 for Place Vanier
    //EFFECTS: returns a new listOfStudents containing only students in the specified residence
    public ListOfStudent filterByResidence(int residence) {
        ListOfStudent filteredList = new ListOfStudent();
        for (Student s : studentList) {
            if (residence == s.getResidence()) {
                filteredList.addStudent(s, false);
            }
        }
        String res = "Given residence not found.";
        switch (residence) {
            case 1: res = "Orchard Commons.";
            break;
            case 2: res = "Totem Park.";
            break;
            case 3: res = "Place Vanier.";
            break;
        }
        EventLog.getInstance().logEvent(new Event("List of students was filtered by residence: " + res));
        return filteredList;
    }

    //EFFECTS: returns a new listOfStudent with only students who have interests matching the input interest
    //         returns empty list if no students were found with matching interests
    public ListOfStudent filterByInterest(String interest) {
        ListOfStudent filteredList = new ListOfStudent();
        interest = interest.toLowerCase();
        for (Student s : studentList) {
            if (interest.equals(s.getInterest1()) || interest.equals(s.getInterest2())) {
                filteredList.addStudent(s, false);
            }
        }
        if (!filteredList.isEmpty()) {
            EventLog.getInstance().logEvent(new Event("List of students filtered by interest: " + interest + "."));
        }
        return filteredList;
    }

    // Referenced JsonSerializationDemo application as provided in Phase 2 Task 3 to implement the following code
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("studentList", studentsToJson());
        return json;
    }

    // EFFECTS: returns students in this ListOfStudent as a JSON array
    private JSONArray studentsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Student s : studentList) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

    @Override
    public int getSize() {
        return listLength();
    }

    public void insertElementAt(Student s, int index) {
        this.insertElementAt(s.getStudentName(), index);
        studentList.add(index, s);
    }

    @Override
    public String remove(int index) {
        String s = getElementAt(index);
        studentList.remove(getStudent(s));
        this.removeElement(s);
        EventLog.getInstance().logEvent(new Event(s + " was removed from the list."));
        return s;
    }
}
