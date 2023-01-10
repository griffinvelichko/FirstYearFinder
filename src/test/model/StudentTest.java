package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    private Student testStudent;

    @BeforeEach
    void runBefore() {
        testStudent = new Student("Test Name",
                1, "soccer", "studying", "@username");
    }

    @Test
    void testStudentConstructor() {
        assertEquals("Test Name", testStudent.getStudentName());
        assertEquals(1, testStudent.getResidence());
        assertEquals("soccer", testStudent.getInterest1());
        assertEquals("studying", testStudent.getInterest2());
        assertEquals("@username", testStudent.getUsername());
    }
}
