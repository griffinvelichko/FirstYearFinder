//package ui;
//
//import model.ListOfStudent;
//import model.Student;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Scanner;
//
//public class FinderApp {
//
//    private static final String JSON_STORE = "./data/students.json";
//    private ListOfStudent generalList;
//    private Scanner userInput;
//    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;
//
//    //EFFECTS: runs the application
//    public FinderApp() {
//        runApplication();
//    }
//
//    //EFFECTS: runs initialization of program, runs first display of list and processes user input for next action.
//    private void runApplication() {
//        String input;
//
//        init();
//        displayGeneralList();
//
//        while (true) {
//            displayCommands();
//            input = userInput.next();
//
//            if (!input.equals("quit")) {
//                processCommand(input);
//            } else {
//                System.out.println("Do You Want To Save Your Database of Students? y (yes) or n (no)");
//                input = userInput.next();
//                processFinalSave(input);
//                break;
//            }
//        }
//        System.out.println("Good Luck Finding Those First Years!");
//    }
//
//    private void processFinalSave(String input) {
//        if (input.equals("y")) {
//            saveListOfStudent();
//        } else if (input.equals("n")) {
//            System.out.println("Your Most Recent Database Was Not Saved.");
//        } else {
//            System.out.println("Unknown Response, Your Data Was Automatically Saved");
//            saveListOfStudent();
//        }
//    }
//
//    //MODIFIES: this
//    //EFFECTS: Initializes application by creating general list of students
//    //         and adds students who signed up for beta program.
//    private void init() {
//        generalList = new ListOfStudent();
//        Student beta1 = new Student("Grady",
//                2, "skiing", "japan", "@gradyboger");
//        Student beta2 = new Student("Jacob",
//                2, "backcountry", "skiing", "@jacobsurks31");
//        Student beta3 = new Student("Oli",
//                1, "trees", "skiing", "@oli_olson");
//        Student beta4 = new Student("Harper",
//                2, "skiing", "hiking", "@harper.lea");
//        Student beta5 = new Student("Ben",
//                1, "skiing", "california", "@wen_berner");
//        Student beta6 = new Student("Griffin",
//                2, "skiing", "bucs", "@g.velichko13");
//        generalList.addStudent(beta1);
//        generalList.addStudent(beta2);
//        generalList.addStudent(beta3);
//        generalList.addStudent(beta4);
//        generalList.addStudent(beta5);
//        generalList.addStudent(beta6);
//        userInput = new Scanner(System.in);
//        jsonWriter = new JsonWriter(JSON_STORE);
//        jsonReader = new JsonReader(JSON_STORE);
//    }
//
//    //EFFECTS: prints out a list of students
//    //         displays each student's name, residence, interests and instagram
//    private void displayList(ListOfStudent l) {
//        for (Student s : l.getStudentList()) {
//            System.out.println("\nStudent: " + s.getStudentName());
//            System.out.println("\tLives in: " + ubcResidence(s.getResidence()));
//            System.out.println("\tInterests: " + s.getInterest1() + " & " + s.getInterest2());
//            System.out.println("\tInstagram Username: " + s.getUsername());
//        }
//    }
//
//    //EFFECTS: displays command bar to user
//    private void displayCommands() {
//        System.out.println("\nPlease Enter One of The Commands Listed Below:");
//        System.out.println("\tadd - Add New Student");
//        System.out.println("\tremove - Remove Current Student");
//        System.out.println("\tresidence - Filter By Residence");
//        System.out.println("\tinterest - Filter By Interest");
//        System.out.println("\tdisplay - Displays List Of All Students");
//        System.out.println("\tsave - Saves File For A Later Date");
//        System.out.println("\tload - Load Saved File Into Application");
//        System.out.println("\tquit - Terminate Application\n");
//    }
//
//    //MODIFIES: this
//    //EFFECTS: takes in users input and runs appropriate method
//    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
//    private void processCommand(String input) {
//        switch (input) {
//            case "add":
//                addStudent();
//                break;
//            case "remove":
//                removeStudent();
//                break;
//            case "residence":
//                filterByResidence();
//                break;
//            case "interest":
//                filterByInterest();
//                break;
//            case "display":
//                displayGeneralList();
//                break;
//            case "save":
//                saveListOfStudent();
//                break;
//            case "load":
//                loadListOfStudent();
//                break;
//            default:
//                System.out.println("\nPlease Enter A Correct Command: ");
//        }
//    }
//
//    //EFFECTS: displays generalList
//    //         returns back to command display bar
//    private void displayGeneralList() {
//        System.out.println("\nList Of All Student Profiles: ");
//        displayList(generalList);
//    }
//
//    //MODIFIES: this
//    //EFFECTS: creates Student from user input
//    //         if no student with same name exists in the generalList
//    //         adds it to the generalList, otherwise notifies user to delete student with same name
//    //         returns back to command display bar
//    private void addStudent() {
//        String name;
//        int residence;
//        String interest1;
//        String interest2;
//        String username;
//
//        System.out.println("\nPlease Enter Student Name: ");
//        name = userInput.next();
//        if (generalList.hasStudent(name)) {
//            System.out.println("\nStudent With This Name Already Exists, Remove Current Student Before Continuing.");
//        } else {
//            System.out.println("\nEnter Your Residence Number(1-Orchard Commons, 2-TotemPark, 3-Place Vanier): ");
//            residence = userInput.nextInt();
//            System.out.println("\nEnter Your First Interest: ");
//            interest1 = userInput.next();
//            System.out.println("\nEnter Your Second Interest: ");
//            interest2 = userInput.next();
//            System.out.println("\nEnter Your Instagram Username: ");
//            username = userInput.next();
//            generalList.addStudent(new Student(name, residence, interest1, interest2, username));
//        }
//    }
//
//    //MODIFIES: this
//    //EFFECTS: takes a student name from user
//    //         if name matches student in generalList, removes the student,
//    //         otherwise advises student no student with same name exists
//    //         returns back to command display bar
//    private void removeStudent() {
//        System.out.println("\nPlease Enter The Student Name You Want To Remove: ");
//        String name = userInput.next();
//        if (generalList.hasStudent(name)) {
//            generalList.removeStudent(name);
//            System.out.println(name + " was successfully removed from the list.");
//        } else {
//            System.out.println("\nThere is no student in the list with this name.");
//        }
//    }
//
//    //MODIFIES: this
//    //EFFECTS: creates and displays filtered list based on user residence choice
//    private void filterByResidence() {
//        System.out.println("\nPlease Enter The Residence Number You'd Like To Filter By");
//        System.out.println("\n(1-Orchard Commons, 2-Totem Park, 3-Place Vanier): ");
//        int residence = userInput.nextInt();
//        ListOfStudent filteredList = generalList.filterByResidence(residence);
//        displayList(filteredList);
//    }
//
//    //MODIFIES: this
//    //EFFECTS: creates and displays filtered list based on user interest choice
//    //         returns back to command display bar
//    private void filterByInterest() {
//        System.out.println("\nPlease Enter The Interest You'd Like To Filter By: ");
//        String interest = userInput.next();
//        ListOfStudent filteredList = generalList.filterByInterest(interest);
//        displayList(filteredList);
//    }
//
//    //REQUIRES: int is either 1, 2 or 3
//    //EFFECTS: returns residence name associated with input integer
//    private String ubcResidence(int i) {
//        if (i == 1) {
//            return "Orchard Commons";
//        } else if (i == 2) {
//            return "Totem Park";
//        } else if (i == 3) {
//            return "Place Vanier";
//        } else {
//            return null;
//        }
//    }
//
//    // EFFECTS: saves the ListOfStudent to file
//    private void saveListOfStudent() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(generalList);
//            jsonWriter.close();
//            System.out.println("Saved Database of Students to " + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads ListOfStudent from file
//    private void loadListOfStudent() {
//        try {
//            generalList = jsonReader.read();
//            System.out.println("Loaded Database of Students from " + JSON_STORE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//    }
//}
