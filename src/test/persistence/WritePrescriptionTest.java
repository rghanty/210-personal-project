package persistence;


import model.Medicine;
import model.Prescription;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// some of the code and methods are modeled after CPSC210/JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Test suite for the WritePrescription class
public class WritePrescriptionTest {

    @Test
    public void testWriteNotAFile() {
        try {

            Prescription p = new Prescription();
            WritePrescription wp = new WritePrescription("./data/\12file.json");
            wp.fileOpen();
            fail("There should have been an exception");
        } catch (IOException i) {

        }
    }

    @Test
    public void testWriteEmptyFile() {
        try {
            Prescription p = new Prescription();
            WritePrescription wp = new WritePrescription("./data/testWriteToEmptyFile.json");
            wp.fileOpen();
            wp.writeTo(p);
            wp.closeWriter();

            ReadPrescription rp = new ReadPrescription("./data/testWriteToEmptyFile.json");
            p = rp.readPres();
            assertEquals(p.viewSize(), 0);

            p.addMedTime(new Medicine("m1", 1), 10);
            wp.fileOpen();
            wp.writeTo(p);
            wp.closeWriter();

            p = rp.readPres();
            assertEquals(p.viewSize(), 1);


        } catch (IOException i) {
            fail("Should not have been thrown");
        }
    }

    @Test
    public void testWriteToFile() {
        try {
            Prescription p = new Prescription();
            Medicine m1 = new Medicine("m1", 1);
            Medicine m2 = new Medicine("m2", 1);
            p.addMedTime(m1,10);
            p.addMedTime(m2,11);

            WritePrescription wp = new WritePrescription("./data/testWriteToFile.json");
            wp.fileOpen();
            wp.writeTo(p);
            wp.closeWriter();

            ReadPrescription rp = new ReadPrescription("./data/testWriteToFile.json");
            p = rp.readPres();
            assertEquals(p.viewSize(), 2);
            assertEquals(p.viewMeds().get(0),m1.getName());
            assertEquals(p.viewMeds().get(1),m2.getName());



        } catch (IOException i) {
            fail("Should not have been thrown");
        }

    }
}
