import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Text extends JPanel implements MouseListener {
    private BufferedImage box;
    public Text() {
        try {
            box = ImageIO.read(new File("src/TextBox.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // just do this
        g.drawImage(box, 0, 0, null);
        g.setFont(new Font("Courier New", Font.BOLD, 24));
        try {
            displayText(g, "hi");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void displayText(Graphics g, String name) throws Exception {
        File file = new File("src/Dialogue");

        // pass the path to the file as a parameter
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            g.drawString(sc.nextLine(), 100, 100);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
