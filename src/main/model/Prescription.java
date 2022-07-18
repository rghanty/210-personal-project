package model;


import sun.awt.image.ImageWatched;

import java.util.*;

// Represents a doctor's prescription consisting of a Medicines and their corresponding consumption
// time during the day.
public class Prescription {
    private LinkedHashMap<Medicine, Integer> prescription;

    // EFFECTS: creates a prescription object with a hashmap consisting of key-value pairs of medicines
    // and their corresponding times of consumption in 24-hour format
    public Prescription() {
        prescription = new LinkedHashMap<>();
    }

    // REQUIRES: time should be an hour of the day in 24-hour format, or:
    //            0 <= time < 24
    // MODIFIES: this
    // EFFECTS: Adds a medicine and its corresponding time of consumption to the prescription.
    public void addMedTime(Medicine med, Integer time) {
        prescription.put(med, time);
    }

    // MODIFIES: this
    // EFFECTS: removes the given medicine from the prescription.
    public boolean checkOffMed(Medicine med) {
        if (prescription.containsKey(med)) {
            prescription.remove(med);
            return true;
        }
        return false;
    }

    // EFFECTS: returns a list of medicine names from the prescription in alphabetical order.
    public ArrayList<String> viewMeds() {
        ArrayList<String> meds = new ArrayList<>();
        for (Medicine med: prescription.keySet()) {
            meds.add(med.getName());

        }

        return meds;
    }

    // REQUIRES: 0 <= time < 24
    // MODIFIES: this
    // EFFECTS: changes the corresponding time of a medicine.
    public boolean changeTime(Medicine med, Integer time) {
        if (prescription.containsKey(med)) {
            prescription.replace(med, time);
            return true;
        }
        return false;
    }

    // EFFECTS: returns the corresponding time of a given medicine.
    public Integer viewTime(Medicine med) {
        return prescription.get(med);


    }

    // EFFECTS: returns size of the prescription.
    public int viewSize() {
        return prescription.size();
    }
}
