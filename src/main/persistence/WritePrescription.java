package persistence;

import model.Prescription;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// some of the code and methods are modeled after CPSC210/JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// class to write details for the prescription to the file.
public class WritePrescription {
    private static final int INDENT_FACTOR = 5;
    private PrintWriter printWriter;
    private String writeTo;

    // EFFECTS: creates a writer to write JSON data to the file with given location.
    public WritePrescription(String writeTo) {
        this.writeTo = writeTo;
    }

    // MODIFIES: this
    // EFFECTS: opens writer and throws a FileNotFoundException if file with given location cannot be written to.
    public void fileOpen() throws FileNotFoundException {
        printWriter = new PrintWriter(new File(writeTo));
    }

    // MODIFIES: this
    // EFFECTS: writes the details of the prescription to the JSON file.
    public void writeTo(Prescription p) throws NullPointerException {
        JSONObject jobject = p.convertToJson();
        printWriter.print(jobject.toString(INDENT_FACTOR));
    }

    // MODIFIES: this
    // EFFECTS: closes the file writer.
    public void closeWriter() {
        printWriter.close();
    }



}
