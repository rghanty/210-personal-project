package ui;

import model.Medicine;
import model.Prescription;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

// Class for the GUI for checking medicines to be consumed in the future.
public class CheckMedsUI extends JFrame {


    private Prescription prescription;
    private JLabel topLabel;
    WindowMaker wm = new WindowMaker();
    Calendar calendar;
    private JLabel medLabel;
    private JButton okButton;

    // EFFECTS: constructs the JFrame which displays medicines to be consumed.
    public CheckMedsUI(Prescription prescription) {


        this.prescription = prescription;
        wm.standardWindow(this);

        makeTopLabel();
        makeMedsLabels();
        makeOKButton();

    }

    // MODIFIES: this
    // EFFECTS: creates an OK button that will take us back to the main page.
    private void makeOKButton() {
        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CheckMedsUI.this.setVisible(false);
            }
        });
        okButton.setForeground(Color.WHITE);
        okButton.setFont(new Font("Century", Font.BOLD, 15));
        okButton.setBackground(new Color(110, 120, 250));
        okButton.setBounds(262, 442, 197, 28);
        this.getContentPane().add(okButton);
    }

    // MODIFIES: this
    // EFFECTS: displays every medicine to be consumed in the future on the JFrame
    public void makeMedsLabels() {
        int i = 1;
        int y = 100;
        for (Medicine m: prescription.presMeds()) {
            calendar = new GregorianCalendar();
            if (calendar.get(Calendar.HOUR_OF_DAY) < prescription.viewTime(m)) {
                medLabel = new JLabel(i + ". " + "Medicine name: " + m.getName() + " at time: "
                        + prescription.viewTime(m) + "00 hrs");
                medLabel.setForeground(new Color(255, 255, 255));
                medLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
                medLabel.setBounds(27, y, 600, 150);
                this.getContentPane().add(medLabel);
                i++;
                y += 50;

            }


        }
    }

    // MODIFIES: this
    // EFFECTS: adds the top label to the top of the screen.
    public void makeTopLabel() {
        topLabel = new JLabel("Medicines to consume:");
        topLabel.setForeground(new Color(255, 255, 255));
        topLabel.setFont(new Font("Georgia", Font.BOLD, 25));
        topLabel.setBounds(27, 66, 311, 100);
        this.getContentPane().add(topLabel);
    }


}
