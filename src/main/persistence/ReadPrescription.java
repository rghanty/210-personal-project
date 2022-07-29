package persistence;


import model.Medicine;
import model.Prescription;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// some of the code and methods are modeled after CPSC210/JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// class for a JSON reader that reads the prescription from the file
public class ReadPrescription {
    private String fileLocation;

    // EFFECTS: creates a reader with a given file location
    public ReadPrescription(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    // EFFECTS: returns a prescription that was read from the file and throws an IO exception if file could not be read.
    public Prescription readPres() throws IOException {
        String sourceFile = makeString(fileLocation);
        JSONObject presJson = new JSONObject(sourceFile);
        return makePres(presJson);

    }

    // EFFECTS: returns the source file as a string.
    private String makeString(String fileLocation) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        try (Stream<String> stringStream = Files.lines(Paths.get(fileLocation), StandardCharsets.UTF_8)) {
            stringStream.forEach(s -> stringBuilder.append(s).append("\n"));
        }

        return stringBuilder.toString();
    }

    // EFFECTS: returns a Prescription object read from the JSON object
    private Prescription makePres(JSONObject presJson) {
        Prescription p = new Prescription();
        JSONArray medsJson = presJson.getJSONArray("Medicines");
        for (Object medJson: medsJson) {
            JSONObject med = (JSONObject) medJson;
            String medname = med.getString("Medicine Name:");
            int dosage = med.getInt("Medicine Dosage:");
            int time = med.getInt("Medicine Time:");
            Medicine m = new Medicine(medname,dosage);
            p.addMedTime(m,time);
        }
        return p;
    }
}
