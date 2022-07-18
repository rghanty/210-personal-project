package ui;

import model.Medicine;
import model.Prescription;

import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //new PrescriptifyApp();

        Prescription p;
        Medicine m1 = new Medicine("Paracetamol",1);
        Medicine m2 = new Medicine("PAN-D", 1);
        Medicine m3 = new Medicine("ABC", 1);

        p = new Prescription();
        p.addMedTime(m1,20);
        p.addMedTime(m2,10);
        p.addMedTime(m3, 15);

        System.out.println(p.viewMeds());


       //Date date = new Date();

        //GregorianCalendar gcalendar = new GregorianCalendar();
        //System.out.println(gcalendar.get(Calendar.HOUR_OF_DAY));
    }
}
