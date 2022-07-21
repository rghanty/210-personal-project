package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PrescriptionTest {

    Prescription p;
    Medicine m1;
    Medicine m2;

    @BeforeEach
    public void setup(){
        p = new Prescription();
        m1 = new Medicine("Paracetamol",1);
        m2 = new Medicine("PAN-D", 2);


    }
    @Test
    public void testPrescription(){
        assertEquals(p.viewSize(),0);
        assertEquals(p.viewMeds().size(),0);
    }

    @Test
    public void testAddMedTime() {
        assertFalse(p.viewMeds().contains(m1.getName()));
        p.addMedTime(m1,21);
        assertTrue(p.viewMeds().contains(m1.getName()));
        p.addMedTime(m2,20);
        assertTrue(p.viewMeds().contains(m2.getName()));


    }

    @Test
    public void testCheckOffMed() {
        p.addMedTime(m1,20);
        assertTrue(p.checkOffMed(m1.getName(),20));
        assertFalse(p.checkOffMed(m2.getName(),2));
        p.addMedTime(m2,20);
        assertTrue(p.checkOffMed(m2.getName(),20));
        p.addMedTime(m1,10);
        p.addMedTime(m2,20);
        assertTrue(p.checkOffMed(m1.getName(),10));
        assertFalse(p.checkOffMed(m2.getName(),15));
    }

    @Test
    public void testViewMeds() {
        ArrayList<String> meds = new ArrayList<>();
        Medicine m3 = new Medicine("ABC",1);
        p.addMedTime(m1,20);
        p.addMedTime(m2,20);
        p.addMedTime(m3, 19);

        ArrayList<String> premeds = p.viewMeds();
        assertEquals(premeds, Arrays.asList("Paracetamol", "PAN-D", "ABC"));
        }






    @Test
    public void testChangeTime() {
        assertFalse(p.changeTime("Paracetamol",20, 10));
        p.addMedTime(m1, 10);
        p.addMedTime(m2,20);
        assertTrue(p.changeTime(m1.getName(),10, 20));
        assertFalse(p.changeTime(m2.getName(),15,20));

    }

    @Test
    public void testViewTime() {
        p.addMedTime(m1,10);
        assertEquals(p.viewTime(m1),10);
        p.addMedTime(m2,20);
        assertEquals(p.viewTime(m2),20);
    }

    @Test
    public void testViewSize() {
        p.addMedTime(m1, 20);
        p.addMedTime(m2, 12);
        assertEquals(p.viewSize(),2);
        Medicine m3 = new Medicine("ABC", 2);
        p.addMedTime(m3, 14);
        assertEquals(p.viewSize(),3);
    }

    @Test
    public void testPresMeds() {
        p.addMedTime(m1,10);
        p.addMedTime(m2,20);
        Set<Medicine> test = new HashSet<>();
        test.add(m1);
        test.add(m2);
        assertEquals(p.presMeds(), test);
    }

    @Test
    public void testMedsAsList() {
        p.addMedTime(m1, 10);
        p.addMedTime(m2,20);
        ArrayList<Medicine> meds = new ArrayList<>();
        meds.add(m1);
        meds.add(m2);
        assertEquals(p.medsAsList(),meds);
    }


}