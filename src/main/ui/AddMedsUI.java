package ui;

import model.Medicine;
import model.Prescription;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// class of the frame that supports every operation related to adding medicines to a prescription.
public class AddMedsUI extends JFrame {

    private JLabel medName;
    private JLabel medTime;
    private JTextField nameField;
    private JTextField timeField;
    private JButton submit;
    private JButton cancel;
    private Prescription prescription;
    WindowMaker wm = new WindowMaker();

    // EFFECTS: constructs a new JFrame with a menu to add medicines at their respective times.
    public AddMedsUI(Prescription p) {


        wm.standardWindow(this);
        this.prescription = p;

        addLabelAndFields();
        addButtons();

    }

    // MODIFIES: this
    // EFFECTS: adds Submit and Cancel buttons at the bottom of the frame
    public void addButtons() {
        submit = new JButton("Submit");

        modifyButton(submit, 146);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int time = Integer.parseInt(timeField.getText());
                Medicine m = new Medicine(name, 1);
                prescription.addMedTime(m, time);
                JOptionPane.showMessageDialog(null, "Medicine " + name + " successfully added",
                        "Success!", JOptionPane.PLAIN_MESSAGE);
                AddMedsUI.this.setVisible(false);
            }
        });
        cancel = new JButton("Cancel");
        modifyButton(cancel, 378);

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddMedsUI.this.setVisible(false);
            }
        });
    }


    // MODIFIES: this, button
    // EFFECTS: adds a button on the frame at given x position
    public void modifyButton(JButton button, int x) {
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Century", Font.BOLD, 15));
        button.setBackground(new Color(110, 120, 250));
        button.setBounds(x, 420, 197, 28);
        this.getContentPane().add(button);
    }

    // REQUIRES: the medTime input must be a number between 0 and 23 (inclusive)
    // MODIFIES: this
    // EFFECTS: adds labels and text fields to receive user input
    public void addLabelAndFields() {

        medName = new JLabel("Medicine Name:");
        medName.setForeground(new Color(255, 255, 255));
        medName.setFont(new Font("Georgia", Font.BOLD, 15));
        medName.setBounds(10, 80, 146, 27);
        this.getContentPane().add(medName);

        nameField = new JTextField();
        nameField.setBounds(289, 85, 215, 19);
        this.getContentPane().add(nameField);
        nameField.setColumns(10);

        medTime = new JLabel("Medicine Time:");
        medTime.setForeground(Color.WHITE);
        medTime.setFont(new Font("Georgia", Font.BOLD, 15));
        medTime.setBounds(10, 174, 146, 50);
        this.getContentPane().add(medTime);

        timeField = new JTextField();
        timeField.setColumns(10);
        timeField.setBounds(289, 191, 215, 19);
        this.getContentPane().add(timeField);
    }
}
