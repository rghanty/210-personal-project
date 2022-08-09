package ui;

import model.Prescription;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// class that supports every operation related to altering the time of a medicine
public class AlterMedsUI extends JFrame {
    WindowMaker wm = new WindowMaker();
    private Prescription prescription;
    private JButton submit;
    private JButton cancel;
    private KeyListener submitListener;
    private KeyListener cancelListener;

    private JLabel name;
    private JTextField nameField;
    private JLabel time;
    private JTextField timeField;
    private JLabel finalTime;
    private JTextField finalTimeField;

    // EFFECTS: constructs a JFrame for the AlterMedsUI
    public AlterMedsUI(Prescription p) {

        wm.standardWindow(this);
        this.prescription = p;

        makeLabelsAndFields();
        makeSubmitKeyListener();
        makeCancelKeyListener();
        makeSubmitButton();
        makeCancelButton();
    }

    // MODIFIES: this
    // EFFECTS: creates the labels in the frame
    private void makeLabelsAndFields() {
        nameField = new JTextField();
        timeField = new JTextField();
        wm.addLabelAndFields(name, time, nameField, timeField, this);

        finalTime = new JLabel("Final Medicine Time (0-23):");
        finalTime.setForeground(Color.WHITE);
        finalTime.setFont(new Font("Georgia", Font.BOLD, 15));
        finalTime.setBounds(10, 280, 250, 50);
        this.getContentPane().add(finalTime);

        finalTimeField = new JTextField();
        finalTimeField.setColumns(10);
        finalTimeField.setBounds(289, 297, 215, 19);
        this.getContentPane().add(finalTimeField);



    }

    // MODIFIES: this
    // EFFECTS: creates the submit button and its action listener
    private void makeSubmitButton() {
        submit = new JButton("Submit");
        submit.addKeyListener(submitListener);
        wm.modifyButton(submit, 146, AlterMedsUI.this);
        this.getContentPane().add(submit);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterMedicine();
            }
        });
    }


    // MODIFIES: this
    // EFFECTS: creates the cancel button and its action listener
    private void makeCancelButton() {
        cancel = new JButton("Cancel");
        cancel.addKeyListener(cancelListener);
        wm.modifyButton(cancel, 378, AlterMedsUI.this);
        this.getContentPane().add(cancel);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlterMedsUI.this.setVisible(false);
            }
        });

    }


    // EFFECTS: makes the enter key listener for the submit button
    private void makeSubmitKeyListener() {
        submitListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    alterMedicine();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };

    }


    // EFFECTS: changes the time of a user input medicine from the initial time to the final time.
    private void alterMedicine() {
        String name = nameField.getText();
        int initTime = Integer.parseInt(timeField.getText());
        int finalTime = Integer.parseInt(finalTimeField.getText());
        if (prescription.changeTime(name, initTime, finalTime)) {
            JOptionPane.showMessageDialog(null, name + "'s time changed from "
                    + initTime + " to "
                    + finalTime + " successfully.");
            AlterMedsUI.this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Medicine does not exist");
        }

    }


    // EFFECTS: makes the enter key listener for the cancel button
    public void makeCancelKeyListener() {
        cancelListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    AlterMedsUI.this.setVisible(false);
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }
}