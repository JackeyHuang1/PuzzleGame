import javax.swing.*;

public class WelcomeFrame {
    private WelcomePanel panel;

    public WelcomeFrame() {
        JFrame frame = new JFrame("A regular game");
        if (WelcomePanel.getCleared()) {
            frame.setTitle("The first night");
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null); // auto-centers frame in screen

        // create and add panel
        panel = new WelcomePanel(frame);
        frame.add(panel);

        // display the frame
        frame.setVisible(true);

        // no thread needed here since we aren't doing animation for this frame/panel
    }
}