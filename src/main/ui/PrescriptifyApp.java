package ui;


import model.Event;
import model.EventLog;
import model.Medicine;
import model.Prescription;
import persistence.ReadPrescription;
import persistence.WritePrescription;


import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

// some of the code and methods are modeled after CPSC210/JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// class for the application's graphical user interface.
public class PrescriptifyApp extends JFrame {
    private String fileLocation = "./data/prescriptions.json";
    private Medicine med;
    private Integer time;
    private Scanner userInput;
    private Prescription prescription;
    private Calendar calendar;
    private ReadPrescription reader = new ReadPrescription(fileLocation);
    private WritePrescription writer = new WritePrescription(fileLocation);

    private ActionListener addMedListener;
    private ActionListener alterMedListener;
    private ActionListener checkMedListener;
    private ActionListener saveListener;
    private ActionListener loadListener;
    private ActionListener quitListener;
    private KeyListener addKeyListener;
    private KeyListener alterKeyListener;
    private KeyListener checkKeyListener;
    private KeyListener saveKeyListener;
    private KeyListener loadKeyListener;
    private KeyListener quitKeyListener;
    private WindowMaker wm;




    // EFFECTS: initiates the prescription application
    public PrescriptifyApp() {
        prescription = new Prescription();

        wm = new WindowMaker();

        wm.openingMenu(this);
        buttonListeners();
        wm.addMedButton.addKeyListener(makeAddKeyListener());
        wm.alterMedButton.addKeyListener(makeAlterKeyListener());
        wm.saveButton.addKeyListener(makeSaveKeyListener());
        wm.loadButton.addKeyListener(makeLoadKeyListener());
        wm.checkMedButton.addKeyListener(makeCheckKeyListener());
        wm.quitButton.addKeyListener(makeQuitKeyListener());

        wm.addMedButton.addActionListener(addMedListener);
        wm.alterMedButton.addActionListener(alterMedListener);
        wm.saveButton.addActionListener(saveListener);
        wm.loadButton.addActionListener(loadListener);
        wm.checkMedButton.addActionListener(checkMedListener);
        wm.quitButton.addActionListener(quitListener);




        //showOptions();
        //begin();
        //remind();

    }



    // EFFECTS: implements an action listener for each button in the main panel.
    public void buttonListeners() {
        addMedListener = e -> new AddMedsUI(prescription);
        alterMedListener = e -> new AlterMedsUI(prescription);
        saveListener = e -> {
            savePrescription();
            JOptionPane.showMessageDialog(null, "Saved medicines to file", "Save",
                    JOptionPane.PLAIN_MESSAGE);
        };
        loadListener = e -> {
            loadPrescription();
            JOptionPane.showMessageDialog(null, "Loaded Medicine info from file", "Load",
                    JOptionPane.PLAIN_MESSAGE);
        };
        checkMedListener = e -> new CheckMedsUI(prescription);
        quitListener = e -> {
            printLog(EventLog.getInstance());
            new RemindUI(prescription);
            this.dispose();

        };

    }

    // EFFECTS: creates the add Key listener to open AddMedsUI whenever enter is pressed.
    public KeyListener makeAddKeyListener() {
        addKeyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    new AddMedsUI(prescription);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        return addKeyListener;
    }

    // EFFECTS: creates the alter Key listener to open AlterMedsUI whenever enter is pressed.
    public KeyListener makeAlterKeyListener() {
        alterKeyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    new AlterMedsUI(prescription);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        return alterKeyListener;
    }

    // EFFECTS: creates the check Key listener to open CheckMedsUI whenever enter is pressed.
    public KeyListener makeCheckKeyListener() {
        checkKeyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    new CheckMedsUI(prescription);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        return checkKeyListener;

    }

    // EFFECTS: creates the save Key listener to save the prescription whenever enter is pressed.
    public KeyListener makeSaveKeyListener() {
        saveKeyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    savePrescription();
                    JOptionPane.showMessageDialog(null, "Saved medicines to file", "Save",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        return saveKeyListener;

    }

    // EFFECTS: creates the load Key listener to load the prescription whenever enter is pressed.
    public KeyListener makeLoadKeyListener() {
        loadKeyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loadPrescription();
                    JOptionPane.showMessageDialog(null,
                            "Loaded Medicine info from file", "Load",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        return loadKeyListener;

    }

    // EFFECTS: creates the quit Key listener to quit and start the reminder system whenever enter is pressed.
    public KeyListener makeQuitKeyListener() {
        quitKeyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    printLog(EventLog.getInstance());
                    new RemindUI(prescription);
                    PrescriptifyApp.this.dispose();

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        return quitKeyListener;

    }

    // EFFECTS: prints out all the event descriptions to the console
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n\n");
        }

    }



// ------------------------------ The console ui methods start here --------------------------------------------------

    // EFFECTS: initiates the reminder system.
    private void remind() {
        System.out.println("____________Reminder System Active_____________");

        ArrayList<Medicine> medList = prescription.medsAsList();
        while (medList.size() != 0) {
            calendar = new GregorianCalendar();
            for (int i = 0; i < medList.size(); i++) {
                if ((prescription.viewTime(medList.get(i)).equals(calendar.get(Calendar.HOUR_OF_DAY)))
                        && (calendar.get(Calendar.MINUTE) == 0)) {
                    System.out.println("Time to take " + medList.get(i).getName() + "!!!");
                    medList.remove(medList.get(i));
                } else {
                    continue;
                }
            }
        }
        System.out.println("All reminders given, if you want to add more medicines, run the program again.");
    }


    // REQUIRES: userInput should be a string.
    // MODIFIES: this
    // EFFECTS: takes a user input and processes it
    private void begin() {
        userInput = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter your desired keyword:");
            String option = userInput.next();
            if (option.equals("quit")) {
                break;
            } else if (option.equals("add")) {
                fillPrescription();

            } else if (option.equals("edit")) {
                editPrescription();

            } else if (option.equals("check")) {
                checkMeds();

            } else if (option.equals("save")) {
                savePrescription();

            } else if (option.equals("load")) {
                loadPrescription();
            } else {
                System.out.println("Please enter a valid option.");

            }
        }
        System.out.println("\nThank you for using Prescriptify!!!");
    }

    // EFFECTS: prints out a list of medicines in the prescription to be taken in the future and their times.
    private void checkMeds() {

        System.out.println("________________________________");
        System.out.println("The given medicines to be consumed are: ");
        int i = 1;
        for (Medicine m: prescription.presMeds()) {
            calendar = new GregorianCalendar();
            if (calendar.get(Calendar.HOUR_OF_DAY) < prescription.viewTime(m)) {
                System.out.println(i + "." + "Medicine name: " + m.getName() + " at time: "
                        + prescription.viewTime(m) + "00 hrs");
                i++;
            }


        }
    }

    // REQUIRES: userInput must be a string.
    // MODIFIES: this
    // EFFECTS: presents a menu of edit options and performs an operation based on user input.
    private void editPrescription() {
        showEditOptions();
        userInput = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter the desired keyword:");
            String option = userInput.next();
            if (option.equals("remove")) {
                removeMed();
            } else if (option.equals("alter")) {
                alterMed();
            } else if (option.equals("view")) {
                viewMed();
            } else if (option.equals("quit")) {
                System.out.println("Returning to the main page...");
                break;
            } else {
                System.out.println("Please enter a valid option");
            }
        }
    }

    // REQUIRES: userInput should be a string.
    // EFFECTS: prints the time of the user input medicine.
    private void viewMed() {
        System.out.println("Specify the name of the medicine you want to check the time of:");
        String medname = userInput.next();
        for (Medicine m: prescription.presMeds()) {
            if (m.getName().equals(medname)) {
                System.out.println("The medicine " + medname + " is to be consumed at " + prescription.viewTime(m)
                        + "00 hrs");
            }
        }


    }

    // REQUIRES: userInput for medname should be a string, an integer for timeInit and an integer for timeFinal.
    // MODIFIES: this
    // EFFECTS: changes the time of a user input medicine to a user input time.
    private void alterMed() {
        System.out.println("Specify the name of the medicine you want to alter:");
        String medname = userInput.next();
        System.out.println("Specify the time of the provided medicine:");
        int timeInit = userInput.nextInt();
        System.out.println("Specify the time you want to change to:");
        int timeFinal = userInput.nextInt();
        if (prescription.changeTime(medname, timeInit,timeFinal)) {
            System.out.println("The time of medicine " + medname + " was successfully changed from "
                    + timeInit + "00 hrs to " + timeFinal + "00 hrs");
        } else {
            System.out.println("This medicine does not exist");
        }
    }

    // REQUIRES: userInput should be a string for medname and an integer for medtime.
    // MODIFIES: this
    // EFFECTS: removes a medicine with a user input name and time from the prescription
    private void removeMed() {
        System.out.println("Specify the name of the medicine you want to remove:");
        String medname = userInput.next();
        System.out.println("Specify the time of the provided medicine.");
        int medtime = userInput.nextInt();
        if (prescription.checkOffMed(medname,medtime)) {
            System.out.println("Medicine " + medname + " was successfully removed");
        } else {
            System.out.println("Medicine does not exist.");
        }

    }


    // EFFECTS: displays the edit menu with user options
    private void showEditOptions() {
        System.out.println("_______Edit Menu_______");
        System.out.println("Please use the following keywords to perform the desired operation on your prescription.");
        System.out.println("\n");
        System.out.println("remove: To remove a medicine from your prescription.");
        System.out.println("alter: To change the time of a given medicine.");
        System.out.println("view: To view the time of a given medicine.");
        System.out.println("quit: To exit the edit menu.");
    }

    // EFFECTS: displays a list of keywords the user can choose to interact with the system.
    private void showOptions() {
        System.out.println("_______Welcome to Prescriptify!_______");
        System.out.println("\n");
        System.out.println("Use the following keywords to interact with the application: ");
        System.out.println("add: To add medicines to your prescription");
        System.out.println("edit: To modify your prescription");
        System.out.println("check: To check upcoming medicines for consumption and their timings");
        System.out.println("save: To save the contents of the current prescription to the file.");
        System.out.println("load: To load the contents of the prescription from the file");
        System.out.println("quit: To quit the application");
    }


    // REQUIRES: userInput should be an integer for num, a string for name, an integer for freq, an integer for time.
    // MODIFIES: this
    // EFFECTS: fills out a prescription with user given medicines, their dosages and timings.
    public void fillPrescription() {

        userInput = new Scanner(System.in);
        System.out.println("How many medicines do you want to add to the prescription?");
        int num = userInput.nextInt();
        if (num < 1) {
            System.out.println("The number of medicines should be at least 1");
            fillPrescription();
        } else {

            for (int i = 1; i <= num; i++) {
                System.out.println("Enter the name of the medicine: ");
                String name = userInput.next();
                System.out.println("Enter the number of times to consume this medicine in the day: ");
                int freq = userInput.nextInt();

                for (int j = 1; j <= freq; j++) {
                    med = new Medicine(name, freq);
                    System.out.println("Enter the time to consume this medicine (between 0 and 23): ");
                    time = userInput.nextInt();
                    prescription.addMedTime(med, time);
                }

            }
            System.out.println("Medicine(s) successfully added!");
        }
    }

    // EFFECTS: saves the prescription to a file.
    public void savePrescription() {
        try {
            writer.fileOpen();
            writer.writeTo(prescription);
            writer.closeWriter();
            System.out.println("Saved medicines to file " + fileLocation);

        } catch (FileNotFoundException f) {
            System.out.println("Sorry, the file with " + fileLocation + " was not found");
        } catch (NullPointerException n) {
            System.out.println("Sorry, there was nothing to write to file");
        }

    }

    // MODIFIES: this
    // EFFECTS: loads the contents of the prescription from the file.
    public void loadPrescription() {
        try {
            prescription = reader.readPres();
            System.out.println("Loading prescription contents from " + fileLocation);
        } catch (IOException i) {
            System.out.println("Sorry, the required operation could not be performed.");
        }

    }

}