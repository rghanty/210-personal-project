package ui;

import model.Medicine;
import model.Prescription;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


// class of the frame that supports every operation related to adding medicines to a prescription.
public class AddMedsUI extends JFrame {

    private JLabel medName;
    private JLabel medTime;
    private JTextField nameField;
    private JTextField timeField;
    private JButton submit;
    private JButton cancel;
    private Prescription prescription;
    private KeyListener submitListener;
    private KeyListener cancelListener;
    private ActionListener submitActionListener;
    private ActionListener cancelActionListener;

    WindowMaker wm;

    // EFFECTS: constructs a new JFrame with a menu to add medicines at their respective times.
    public AddMedsUI(Prescription p) {
        wm = new WindowMaker();
        wm.standardWindow(this);

        this.prescription = p;
        nameField = new JTextField();
        timeField = new JTextField();

        wm.addLabelAndFields(medName,medTime,nameField,timeField,this);

        startSubmitListener();
        startCancelListener();
        actionListeners();

        addButtons();
        setFocusable(true);
    }



    // MODIFIES: this
    // EFFECTS: adds Submit and Cancel buttons at the bottom of the frame
    public void addButtons() {
        submit = new JButton("Submit");

        submit.addKeyListener(submitListener);


        wm.modifyButton(submit, 146, AddMedsUI.this);
        submit.addActionListener(submitActionListener);

        cancel = new JButton("Cancel");
        wm.modifyButton(cancel, 378, AddMedsUI.this);

        cancel.addKeyListener(cancelListener);

        cancel.addActionListener(cancelActionListener);
    }


    // EFFECTS: receives input from text fields and creates a new medicine with appropriate details.
    public void addMedicine() {
        String name = nameField.getText();
        int time = Integer.parseInt(timeField.getText());
        Medicine m = new Medicine(name, 1);
        prescription.addMedTime(m, time);
        JOptionPane.showMessageDialog(null, "Medicine " + name + " successfully added",
                "Success!", JOptionPane.PLAIN_MESSAGE);
        AddMedsUI.this.setVisible(false);
    }



    // MODIFIES: this
    // EFFECTS: makes the key listener for the submit button
    public void startSubmitListener() {
        submitListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    addMedicine();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };


    }

    // MODIFIES: this
    // EFFECTS: makes the key listener for the cancel button
    public void startCancelListener() {
        cancelListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    AddMedsUI.this.setVisible(false);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };

    }

    // MODIFIES: this
    // EFFECTS: creates action listeners for the submit and cancel buttons
    private void actionListeners() {
        submitActionListener = e -> addMedicine();
        cancelActionListener = e -> AddMedsUI.this.setVisible(false);
    }
}
