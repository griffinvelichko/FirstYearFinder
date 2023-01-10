package persistence;

import model.ListOfStudent;
import model.Student;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Referenced JsonSerializationDemo application as provided in Phase 2 Task 3 to implement the following code
class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            ListOfStudent los = new ListOfStudent();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyListOfStudent() {
        try {
            ListOfStudent los = new ListOfStudent();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLOS.json");
            writer.open();
            writer.write(los);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLOS.json");
            los = reader.read();
            assertEquals(0, los.listLength());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralList() {
        try {
            ListOfStudent los = new ListOfStudent();
            los.addStudent(new Student("Bob",1, "skiing",
                    "sledding", "bobgram"), true);
            los.addStudent(new Student("Greg",2, "skiing",
                    "math", "greggram"), true);
            los.addStudent(new Student("Matt",3, "eating",
                    "skiing", "mattgram"), true);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralList.json");
            writer.open();
            writer.write(los);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralList.json");
            los = reader.read();
            List<Student> students = los.getStudentList();
            assertEquals(3, students.size());
            checkStudent("Bob",1, "skiing", "sledding",
                    "bobgram", students.get(0));
            checkStudent("Greg", 2, "skiing", "math",
                    "greggram", students.get(1));
            checkStudent("Matt", 3, "eating", "skiing",
                    "mattgram", students.get(2));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}