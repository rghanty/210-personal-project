package persistence;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import model.Prescription;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// some of the code and methods are modeled after CPSC210/JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// test suite for the ReadPrescription class
public class ReadPrescriptionTest {

    @Test
    public void testFileDoesNotExist() {
        try {
            ReadPrescription rp = new ReadPrescription("./data/nonExistentFile.json");
            Prescription p = rp.readPres();
            fail("Exception should have been caught");
        } catch (IOException i) {

        }

    }

    @Test
    public void testReadFromEmptyPres() {
        try {
            ReadPrescription rp = new ReadPrescription("./data/emptyPres.json");
            Prescription p = rp.readPres();
            assertEquals(p.viewSize(),0);

        } catch (IOException i) {
            fail("Exception should not have been caught");
        }
    }

    @Test
    public void testReadFromFile() {
        try {
            ReadPrescription rp = new ReadPrescription("./data/testReadFromFile.json");
            Prescription p = rp.readPres();
            assertEquals(p.viewSize(),2);
            assertEquals(p.medsAsList().get(0).getName(),"abc");
            assertEquals(p.medsAsList().get(1).getName(),"bcd");
            assertEquals(p.medsAsList().get(0).getDosage(),1);
            assertEquals(p.medsAsList().get(1).getDosage(),1);
            assertEquals(p.viewTime(p.medsAsList().get(0)),20);
            assertEquals(p.viewTime(p.medsAsList().get(1)),21);
        } catch (IOException i) {
            fail("Exception should no have been caught");
        }
    }
}
