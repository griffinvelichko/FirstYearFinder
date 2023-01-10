package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfStudentTest {
    private ListOfStudent testList;
    private Student test1;
    private Student test2;
    private Student test3;


    @BeforeEach
    void runBefore() {
        test1 = new Student("Bob", 1, "math", "running", "@username");
        test2 = new Student("Matt", 2, "grades", "running", "@math");
        test3 = new Student("Greg", 1, "business", "grades", "@greg");

        testList = new ListOfStudent();
    }

    @Test
    void testListOfStudentConstructor() {
        assertEquals(0,testList.listLength());
    }

    @Test
    void testAddSingleStudent() {
        testList.addStudent(test1, true);
        assertEquals(1, testList.listLength());
    }

    @Test
    void testAddMultipleStudents() {
        testList.addStudent(test1, true);
        testList.addStudent(test2, true);
        testList.addStudent(test3, true);

        assertEquals(3, testList.listLength());
    }

    @Test
    void testAddStudentAlreadyThere() {
        testList.addStudent(test1, true);
        testList.addStudent(test3, true);
        testList.addStudent(test1, true);

        assertEquals(2, testList.listLength());
        assertTrue(testList.hasStudent("Bob"));
        assertTrue(testList.hasStudent("Greg"));
    }

    @Test
    void testListLength() {
        testList.addStudent(test1, true);
        testList.addStudent(test2, true);
        testList.addStudent(test3, true);

        assertEquals(3, testList.getStudentList().size());
        assertEquals(3, testList.listLength());
    }

    @Test
    void testListLengthEmpty() {
        assertEquals(0,testList.getStudentList().size());
        assertEquals(0,testList.listLength());
    }

    @Test
    void testRemoveStudent() {
        testList.addStudent(test1, true);
        assertEquals(1, testList.listLength());

        testList.removeStudent("Bob");
        assertEquals(0, testList.listLength());
    }

    @Test
    void testRemoveMultipleStudents() {
        testList.addStudent(test1, true);
        testList.addStudent(test2, true);
        testList.addStudent(test3, true);
        assertEquals(3, testList.listLength());

        testList.removeStudent("Bob");
        assertEquals(2, testList.listLength());
        testList.removeStudent("Greg");
        assertEquals(1, testList.listLength());
        testList.removeStudent("Matt");
        assertEquals(0,testList.listLength());
    }

    @Test
    void testRemoveStudentNotThere() {
        testList.addStudent(test1, true);
        testList.addStudent(test2, true);
        assertEquals(2, testList.listLength());

        testList.removeStudent("Greg");
        assertEquals(2, testList.listLength());
    }

    @Test
    void testRemoveStudentEmptyList(){
        assertEquals(0, testList.listLength());

        testList.removeStudent("Greg");

        assertEquals(0, testList.listLength());
    }

    @Test
    void testHasStudentTrue() {
        testList.addStudent(test1, true);
        testList.addStudent(test2, true);
   //     assertEquals(2, testList.listLength()););

        boolean torf = testList.hasStudent("Matt");
        assertTrue(torf);
    }

    @Test
    void testHasStudentFalse() {
        testList.addStudent(test1, true);
        testList.addStudent(test2, true);
     //   assertEquals(2, testList.listLength()););

        boolean torf = testList.hasStudent("Greg");
        assertFalse(torf);
    }

    @Test
    void testGetStudentHasStudent() {
        testList.addStudent(test1, true);
        testList.addStudent(test2, true);
        testList.addStudent(test3, true);
        assertEquals(3, testList.listLength());
        assertEquals(3, testList.getSize());

        assertEquals(test2, testList.getStudent("Matt"));
    }

    @Test
    void testGetStudentNoStudentInList() {
        testList.addStudent(test1, true);
        testList.addStudent(test2, true);
        assertEquals(2, testList.listLength());
        assertEquals(2, testList.getSize());

        assertNull(testList.getStudent("George"));
    }

    @Test
    void testGetStudentEmptyList(){
        assertEquals(0,testList.listLength());
        assertEquals(0, testList.getSize());

        assertNull(testList.getStudent("Greg"));
    }

    @Test
    void testFilterByResidence() {
        testList.addStudent(test1, true);
        testList.addStudent(test2, true);
        testList.addStudent(test3, true);
        assertEquals(3, testList.listLength());

        ListOfStudent residenceSearchTest = testList.filterByResidence(1);
        assertEquals(2, residenceSearchTest.listLength());
        assertTrue(residenceSearchTest.hasStudent("Bob"));
        assertTrue(residenceSearchTest.hasStudent("Greg"));
    }

    @Test
    void testFilterByResidenceNoneThere() {
        testList.addStudent(test1, true);
        testList.addStudent(test2, true);
        testList.addStudent(test3, true);
        assertEquals(3, testList.listLength());

        ListOfStudent residenceSearchTest = testList.filterByResidence(3);
        assertEquals(0, residenceSearchTest.listLength());
    }

    @Test
    void testFilterByInterestMultipleSameInterest2() {
        testList.addStudent(test1, true);
        testList.addStudent(test2, true);
        testList.addStudent(test3, true);
        assertEquals(3, testList.listLength());

        ListOfStudent interestSearchTest = testList.filterByInterest("running");
        assertEquals(2,interestSearchTest.listLength());
        assertTrue(interestSearchTest.hasStudent("Bob"));
        assertTrue(interestSearchTest.hasStudent("Matt"));
    }

    @Test
    void testFilterByInterestMultipleDifferentInterestHolder() {
        testList.addStudent(test1, true);
        testList.addStudent(test2, true);
        testList.addStudent(test3, true);
        assertEquals(3, testList.listLength());

        ListOfStudent interestSearchTest = testList.filterByInterest("grades");
        assertEquals(2,interestSearchTest.listLength());
        assertTrue(interestSearchTest.hasStudent("Matt"));
        assertTrue(interestSearchTest.hasStudent("Greg"));
    }

    @Test
    void testFilterByInterestNoneThere() {
        testList.addStudent(test1, true);
        testList.addStudent(test2, true);
        testList.addStudent(test3, true);
        assertEquals(3, testList.listLength());

        ListOfStudent interestSearchTest = testList.filterByInterest("lacrosse");
        assertEquals(0,interestSearchTest.listLength());
    }

    @Test
    void testInsertElementAt() {
        testList.addStudent(test1, true);
        testList.addStudent(test2, true);
        assertEquals(2, testList.listLength());

        testList.insertElementAt(test3, 1);
        assertTrue(testList.hasStudent(test3.getStudentName()));
    }

    @Test
    void testRemoveAtIndex() {
        testList.addStudent(test1, true);
        testList.addStudent(test2, true);
        assertEquals(2, testList.listLength());

        testList.insertElementAt(test3, 1);
        assertTrue(testList.hasStudent(test3.getStudentName()));

        testList.remove(1);
        assertFalse(testList.hasStudent(test3.getStudentName()));
    }

    @Test
    void testRemoveAllStudents() {
        testList.addStudent(test1, true);
        testList.addStudent(test2, true);
        testList.addStudent(test3, true);

        assertEquals(3, testList.listLength());

        testList.removeAllStudents();

        assertEquals(0, testList.listLength());
    }

}
