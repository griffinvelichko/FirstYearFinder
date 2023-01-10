package persistence;

import model.ListOfStudent;
import model.Student;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Referenced JsonSerializationDemo application as provided in Phase 2 Task 3 to implement the following code
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfStudent los = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyListOfStudent() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLOS.json");
        try {
            ListOfStudent los = reader.read();
            assertEquals(0, los.listLength());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralList.json");
        try {
            ListOfStudent los = reader.read();
            List<Student> students = los.getStudentList();
            assertEquals(3, students.size());
            checkStudent("Bob",1, "skiing", "sledding",
                    "bobgram", students.get(0));
            checkStudent("Greg", 2, "skiing", "math",
                    "greggram", students.get(1));
            checkStudent("Matt", 3, "eating", "skiing",
                    "mattgram", students.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}