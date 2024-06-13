import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class WelcomePanel extends JPanel implements ActionListener, ChangeListener {

    private JButton submitButton;
    private JFrame enclosingFrame;
    private JSlider slider;

    public WelcomePanel(JFrame frame) {
        enclosingFrame = frame;
        submitButton = new JButton("Start");
        slider = new JSlider(1, 15);
        add(submitButton);
        add(slider);
        submitButton.addActionListener(this);
        slider.addChangeListener(this);

    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.BLUE);
        g.drawString("Tetris Game", 40, 30);
        g.drawString("Current Speed: " + slider.getValue(), 210, 50);
        g.drawString("Recommended Speed for First Run is 5", 160, 70);
        g.drawString("Controls", 50, 120);
        g.drawString("Left Arrow for Left", 50, 140);
        g.drawString("Right Arrow for Right", 50, 160);
        g.drawString("Down Arrow for Down", 50, 180);
        g.drawString("Z for Clockwise Rotation", 50, 200);
        g.drawString("X for Counter-Clockwise Rotation", 50, 220);
        g.drawString("All Inputs are TAP only. No Holding Keys", 50, 240);
        submitButton.setLocation(50, 50);
        repaint();
    }

    // ACTIONLISTENER INTERFACE METHODS
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button == submitButton) {
                int speed = slider.getValue();
                MainFrame f = new MainFrame(speed);
                enclosingFrame.setVisible(false);

            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
