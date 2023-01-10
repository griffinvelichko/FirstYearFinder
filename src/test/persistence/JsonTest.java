package persistence;

import model.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Referenced JsonSerializationDemo application as provided in Phase 2 Task 3 to implement the following code
public class JsonTest {
    protected void checkStudent(String studentName, int residence, String interest1, String interest2, String username,
                                Student student) {
        assertEquals(studentName, student.getStudentName());
        assertEquals(residence, student.getResidence());
        assertEquals(interest1, student.getInterest1());
        assertEquals(interest2, student.getInterest2());
        assertEquals(username, student.getUsername());
    }
}
