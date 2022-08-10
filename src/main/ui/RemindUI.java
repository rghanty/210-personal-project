package ui;

import model.Medicine;
import model.Prescription;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

// class for the reminder system UI.
public class RemindUI extends JFrame {


    private WindowMaker wm;
    private Prescription prescription;
    private Calendar calendar;
    private ArrayList<Medicine> medList;

// EFFECTS: creates the reminder system application with the same window framework and initiates the reminder system.
    public RemindUI(Prescription p) {
        wm = new WindowMaker();


        wm.standardWindow(this);
        this.prescription = p;

        medList = p.medsAsList();
        addLabels();
        initReminder();





    }

    // EFFECTS: initiates the reminder system and creates a pop-up message every time it's time to take a medicine
    private void initReminder() {
        new Thread(() -> {
            while (medList.size() != 0) {
                calendar = new GregorianCalendar();
                for (int i = 0; i < medList.size(); i++) {
                    if ((prescription.viewTime(medList.get(i)).equals(calendar.get(Calendar.HOUR_OF_DAY)))
                            && (calendar.get(Calendar.MINUTE) == 0)
                    ) {
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null, "Time to take Medicine "
                                + medList.get(i).getName() + " !!", "Reminder", JOptionPane.INFORMATION_MESSAGE);
                        medList.remove(medList.get(i));
                    } else {
                        continue;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "All reminders given. If you want"
                            + " to add more medicines, restart the app.", "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        }).start();
    }

    // MODIFIES: this
    // EFFECTS: adds labels to the remindFrame
    private void addLabels() {
        JLabel titleLabel = new JLabel("Reminder System Active");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 25));
        titleLabel.setBounds(220, 41, 329, 58);
        this.getContentPane().add(titleLabel);

        JLabel watchLabel = new JLabel("");
        ImageIcon icon = new ImageIcon("./data/stopwatch.png");
        Image image = icon.getImage();
        Image modified = image.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(modified);
        watchLabel.setIcon(icon);
        watchLabel.setBounds(240, 109, 269, 270);
        this.getContentPane().add(watchLabel);
    }
}
