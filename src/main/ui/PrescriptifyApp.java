package ui;

import model.Medicine;
import model.Prescription;


import java.util.*;



public class PrescriptifyApp {
    private Medicine med;
    private Integer time;
    private Scanner userInput;
    private Prescription prescription;
    private Calendar calendar;


    // EFFECTS: initiates the prescription application, after which the reminder system begins.
    public PrescriptifyApp() {
        prescription = new Prescription();
        showOptions();
        begin();
        remind();

    }

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
            if (calendar.get(Calendar.HOUR_OF_DAY) < prescription.viewTime(m)) {
                System.out.println(i + "." + "Medicine name: " + m.getName() + " at time: "
                        + prescription.viewTime(m) + "00 hrs");
                i++;
            }


        }
    }

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
        System.out.println("quit: To quit the application");
    }



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
}
