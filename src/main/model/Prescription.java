package model;




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
    public boolean checkOffMed(String medname, Integer time) {
        for (Medicine m : prescription.keySet()) {
            if ((m.getName().equals(medname)) && viewTime(m).equals(time)) {
                prescription.remove(m);
                return true;
            }
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
    public boolean changeTime(String med, Integer timeInit, Integer timeFinal) {
        for (Medicine m: prescription.keySet()) {
            if (m.getName().equals(med) && (viewTime(m) == timeInit)) {
                prescription.replace(m, timeFinal);
                return true;
            }
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

    // EFFECTS: returns a set of Medicine objects in the prescription
    public Set<Medicine> presMeds() {
        return prescription.keySet();

    }

    // EFFECTS: returns an array list of Medicine objects in the prescription.
    public ArrayList<Medicine> medsAsList() {
        ArrayList<Medicine> listMeds = new ArrayList<>();
        for (Medicine m: prescription.keySet()) {
            listMeds.add(m);
        }
        return listMeds;
    }
}
