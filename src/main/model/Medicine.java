package model;

import org.json.JSONObject;

// Represents a Medicine with a name and a dosage, i.e, amount of times a medicine should be consumed during the day.
public class Medicine {
    private String name;
    private int dosage;

    // EFFECTS: creates a Medicine object with a name and a dosage number.
    public Medicine(String name, int dosage) {
        this.name = name;
        this.dosage = dosage;
    }

    // MODIFIES: this
    // EFFECTS: sets the Medicine object's name to the given name.
    public void setName(String name) {
        this.name = name;
    }

    // REQUIRES: dosage > 0
    // MODIFIES: this
    // EFFECTS: sets the Medicine object's dosage to the given dosage.
    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    // EFFECTS: returns the dosage of the Medicine object.
    public int getDosage() {
        return dosage;
    }

    // EFFECTS: returns the name of the Medicine object.
    public String getName() {
        return name;
    }

    // EFFECTS: creates a medicine JSONObject.
    public JSONObject medToJson(int time) {
        JSONObject medJson = new JSONObject();
        medJson.put("Medicine Name:", name);
        medJson.put("Medicine Dosage:",dosage);
        medJson.put("Medicine Time:",time);
        return medJson;
    }
}
