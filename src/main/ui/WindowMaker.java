package ui;

import javax.swing.*;
import java.awt.*;


// this class is responsible for creating a standard visual framework for a panel in the application
public class WindowMaker extends JFrame {
    private final String projLogo = "./data/ProjectLogo.PNG";


    JButton addMedButton;
    JButton saveButton;
    JButton loadButton;
    JButton quitButton;
    JButton checkMedButton;

    public WindowMaker() {

    }

    // EFFECTS: builds a standard window framework given a JFrame object.
    public void standardWindow(JFrame frame) {
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle("Prescriptify");
        frame.setBounds(100, 100, 740, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.getContentPane().setLayout(null);
        ImageIcon img = new ImageIcon(projLogo);
        frame.setIconImage(img.getImage());
        frame.getContentPane().setBackground(new Color(98, 98, 255, 255));
        frame.revalidate();
        frame.repaint();
    }

    // EFFECTS: creates the opening menu once you open the application.
    public void openingMenu(JFrame frame) {

        standardWindow(frame);

        JLabel stethLabel = new JLabel("");
        labelMaker("./data/sthetoscope.png", stethLabel);
        stethLabel.setBounds(36, 133, 180, 227);
        frame.getContentPane().add(stethLabel);

        JLabel pillLabel = new JLabel("");
        labelMaker("./data/pills.png",pillLabel);
        pillLabel.setBounds(494, 114, 261, 284);
        frame.getContentPane().add(pillLabel);

        titleLabel(frame);

        addMedButton = makeButton("Add medicine", 126, frame);
        //editPresButton = makeButton("Edit prescription", 176);
        checkMedButton = makeButton("Check medicines", 176, frame);
        saveButton = makeButton("Save prescription", 226, frame);
        loadButton = makeButton("Load prescription", 276, frame);
        quitButton = makeButton("Quit", 326, frame);
        frame.revalidate();
        frame.repaint();
    }


    // EFFECTS: makes a label with no text and the given image with source, as an icon.
    private void labelMaker(String source, JLabel label) {
        ImageIcon icon = new ImageIcon(source);
        Image image = icon.getImage();
        Image modified = image.getScaledInstance(175, 175, Image.SCALE_SMOOTH);
        icon = new ImageIcon(modified);
        label.setIcon(icon);
    }

    // EFFECTS: creates the labels that go at the top of the opening application.
    public void titleLabel(JFrame frame) {
        JLabel topLabel = new JLabel("Prescriptify");
        topLabel.setForeground(Color.WHITE);
        topLabel.setFont(new Font("Georgia", Font.BOLD, 20));
        topLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topLabel.setBounds(206, 23, 314, 35);
        frame.getContentPane().add(topLabel);

        JLabel bottomLabel = new JLabel("A personal prescription application");
        bottomLabel.setFont(new Font("Georgia", Font.BOLD, 18));
        bottomLabel.setForeground(Color.WHITE);
        bottomLabel.setBounds(206, 56, 359, 35);
        frame.getContentPane().add(bottomLabel);


    }

    // EFFECTS: makes a JButton in the given frame and the y position.
    public JButton makeButton(String title, int y, JFrame frame) {

        JButton button = new JButton(title);
        button.setBackground(new Color(120, 120, 255));
        button.setFont(new Font("Century", Font.BOLD, 15));
        button.setForeground(Color.WHITE);
        button.setBounds(262, y, 197, 28);
        frame.getContentPane().add(button);
        return button;

    }


}
