//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class GraphicsPanel extends JPanel implements KeyListener, MouseListener, ActionListener {
//    private BufferedImage background;
//    private boolean[] pressedKeys;
//    private Timer timer;
//    private int time;
//
//    public GraphicsPanel(String name) {
//        try {
//            background = ImageIO.read(new File("src/background.png"));
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//        pressedKeys = new boolean[128];
//        time = 0;
//        timer = new Timer(1000, this);
//        timer.start();
//        addKeyListener(this);
//        addMouseListener(this);
//        setFocusable(true);
//        requestFocusInWindow();
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);  // just do this
//        g.drawImage(background, 0, 0, null);  // the order that things get "painted" matter; we put background down first
//
//        // draw score
//        g.setFont(new Font("Courier New", Font.BOLD, 24));
//        g.drawString("Time: " + time, 20, 70);
//
//
//
//    // ----- KeyListener interface methods -----
//    public void keyTyped(KeyEvent e) { } // unimplemented
//
//    public void keyPressed(KeyEvent e) {
//        // see this for all keycodes: https://stackoverflow.com/questions/15313469/java-keyboard-keycodes-list
//        // A = 65, D = 68, S = 83, W = 87, left = 37, up = 38, right = 39, down = 40, space = 32, enter = 10
//        int key = e.getKeyCode();
//        pressedKeys[key] = true;
//    }
//
//    public void keyReleased(KeyEvent e) {
//        int key = e.getKeyCode();
//        pressedKeys[key] = false;
//    }
//
//    // ----- MouseListener interface methods -----
//    public void mouseClicked(MouseEvent e) { }  // unimplemented; if you move your mouse while clicking,
//    // this method isn't called, so mouseReleased is best
//
//    public void mousePressed(MouseEvent e) { } // unimplemented
//
//    public void mouseReleased(MouseEvent e) {
//    }
//
//    public void mouseEntered(MouseEvent e) { } // unimplemented
//
//    public void mouseExited(MouseEvent e) { } // unimplemented
//
//    // ACTIONLISTENER INTERFACE METHODS: used for buttons AND timers!
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() instanceof Timer) {
//            time++;
//        }
//    }
//}
