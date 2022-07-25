package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// tests for methods in the Medicine class.
public class MedicineTest {

    Medicine m;
    @BeforeEach
    public void setup() {
        m = new Medicine("ABC", 1);
    }

    @Test
    public void testMedicine() {
        assertEquals(m.getName(), "ABC");
        assertEquals(m.getDosage(),1);
    }

    @Test
    public void testSetName() {
        m.setName("FGH");
        assertEquals(m.getName(), "FGH");
    }

    @Test
    public void testSetDosage() {
        m.setDosage(2);
        assertEquals(m.getDosage(),2);
    }

    @Test
    public void testGetName() {
        assertEquals(m.getName(),"ABC");
    }

    @Test
    public void testGetDosage() {
        assertEquals(m.getDosage(), 1);
    }
}
