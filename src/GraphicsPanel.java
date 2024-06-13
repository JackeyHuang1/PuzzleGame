import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel implements KeyListener, MouseListener, ActionListener {
    private BufferedImage background;
    private boolean[] pressedKeys;
    private Timer timer;
    private Timer timer2;
    private BufferedImage grid;
    private BufferedImage next;
    private Blocks block;
    private boolean drops;
    private boolean leftCollision;
    private boolean rightCollision;
    private ArrayList<Blocks> nextPieces;
    public static ArrayList<Block> line = new ArrayList<>();
    private boolean gameOver;
    private int score;
    private int lines;
    private int lineCount;
    private Clip songClip;

    public GraphicsPanel(int speed) {
        playMusic("src/tetris_theme.wav");
        lines = 0;
        score = 0;
        gameOver = false;
        drops = false;
        leftCollision = false;
        rightCollision = false;
        try {
            background = ImageIO.read(new File("src/bg.png"));
            grid = ImageIO.read(new File("src/grid.png"));
            next = ImageIO.read(new File("src/next.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        pressedKeys = new boolean[128];
        timer = new Timer(1000/speed*5/3, this);
        timer.start();
        timer2 = new Timer(500, this);

        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocusInWindow();

        nextPieces = new ArrayList<>();
        block = pickPiece();
        block.placeBlock(329, 41);
    }

    public void playMusic(String input) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(input).getAbsoluteFile());
            songClip = AudioSystem.getClip();
            songClip.open(audioInputStream);
            songClip.loop(Clip.LOOP_CONTINUOUSLY);
            songClip.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void playFail() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/fail_sound.wav").getAbsoluteFile());
            songClip = AudioSystem.getClip();
            songClip.open(audioInputStream);
            songClip.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Blocks pickPiece() {
        int num = (int) (Math.random() * 7);
        if (num == 0) {
            return new L1();
        } else if (num == 1) {
            return new L2();
        } else if (num == 2) {
            return new Line();
        } else if (num == 3) {
            return new Square();
        } else if (num == 4) {
            return new T();
        } else if (num == 5) {
            return new Z1();
        } else {
            return new Z2();
        }
    }

    public void checkCollision() {
        leftCollision = false;
        rightCollision = false;
        drops = false;
        pieceCollision();
        for (Block a: block.getBlocks()) {
            if (a.getX() - 21 < 245){
                leftCollision = true;
            }
            if (a.getX() + 21 > 445){
                rightCollision = true;
            }
            if (a.getY() + 21 > 420){
                drops = true;
            }
        }
        if (drops) {
            timer2.start();
        }
    }

    private void pieceCollision() {
        for (int i = 0; i < GraphicsPanel.line.size(); i++) {
            int x = GraphicsPanel.line.get(i).getX();
            int y = GraphicsPanel.line.get(i).getY();
            for (int j = 0; j < block.getBlocks().length; j++) {
                if (block.getBlocks()[j].getX() - 21 == x && block.getBlocks()[j].getY() == y) {
                    leftCollision = true;
                } else if (block.getBlocks()[j].getX() + 21 == x && block.getBlocks()[j].getY() == y) {
                    rightCollision = true;
                } else if (block.getBlocks()[j].getX() == x && block.getBlocks()[j].getY() + 21 == y) {
                    drops = true;
                }
            }
        }
    }

    public void checkGrounded() {
        if (!block.getActive()) {
            line.add(block.getBlocks()[0]);
            line.add(block.getBlocks()[1]);
            line.add(block.getBlocks()[2]);
            line.add(block.getBlocks()[3]);

            if (block.getBlocks()[0].getX() == 329 && block.getBlocks()[0].getY() == 41) {
                gameOver = true;
                songClip.stop();
                songClip.close();
                playFail();

            }

            block = nextPieces.get(0);
            block.setActive();
            block.placeBlock(329, 41);
            nextPieces.remove(0);
            deleteLine();
        }
    }

    public void deleteLine() {
        int count = 0;
        int x = 245;
        int y = 20;
        lineCount = 0;
        while (x < 455 && y < 440) {
            for (int i = 0; i < line.size(); i++) {
                if (line.get(i).getX() == x && line.get(i).getY() == y) {
                    count++;
                }
            }
            x += 21;
            if (x == 455) {
                if (count == 10) {
                    for (int i = line.size() - 1; i >= 0; i--) {
                        if (line.get(i).getY() == y) {
                            line.remove(i);
                        }
                    }

                    lineCount++;
                    lines++;

                    for (int i = 0; i < line.size(); i++) {
                        if (line.get(i).getY() < y) {
                            line.get(i).setY(line.get(i).getY() + 21);
                        }
                    }
                }

                count = 0;
                x = 245;
                y += 21;
            }
        }
        if (lineCount == 1) {
            score += 40;
        } else if (lineCount == 2) {
            score += 100;
        } else if (lineCount == 3) {
            score += 300;
        } else if (lineCount == 4) {
            score += 800;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // just do this
        if (nextPieces.size() < 4) {
            nextPieces.add(pickPiece());
        }
        g.drawImage(background, 0, 0, null);
        if (!gameOver) {
            g.drawImage(grid, 245, 20, null);
            g.drawImage(next, 500, 20, null);
            for (int i = 0; i < nextPieces.size(); i++) {
                nextPieces.get(i).placeBlock(560, (100 + (i * 80)));
                nextPieces.get(i).drawBlock(g);
            }
            for (int i = 0; i < line.size(); i++) {
                line.get(i).draw(g);
            }
            g.setFont(new Font("Courier New", Font.BOLD, 22));
            g.setColor(Color.WHITE);
            g.drawString("Score: " + score, 10, 50);
            g.drawString("Lines Cleared:" + lines, 10, 80);
            block.drawBlock(g);
            if (pressedKeys[37]) {
                if (!leftCollision) {
                    block.moveLeft();
                }
                pressedKeys[37] = false;
            } else if (pressedKeys[39]) {
                if (!rightCollision) {
                    block.moveRight();
                }
                pressedKeys[39] = false;
            } else if (pressedKeys[40]) {
                if (!drops) {
                    block.moveDown();
                }
                pressedKeys[40] = false;
            } else if (pressedKeys[90]) {
                if (block.getRot() == 1) {
                    block.rotation4();
                }
                if (block.getRot() == 2) {
                    block.rotation1();
                }
                if (block.getRot() == 3) {
                    block.rotation2();
                }
                if (block.getRot() == 4) {
                    block.rotation3();
                }
                block.adjustRotLeft();
                pressedKeys[90] = false;
            } else if (pressedKeys[88]) {
                if (block.getRot() == 1) {
                    block.rotation2();
                }
                if (block.getRot() == 2) {
                    block.rotation3();
                }
                if (block.getRot() == 3) {
                    block.rotation4();
                }
                if (block.getRot() == 4) {
                    block.rotation1();
                }
                block.adjustRotRight();
                pressedKeys[88] = false;
            }
            checkCollision();
        } else {
            Font font = new Font("Arial", Font.BOLD, 36);
            g.setColor(Color.WHITE);
            g.setFont(font);
            g.drawString("You Lost! Current Score is " + score, 20, 200);
            g.drawString("Click to restart at same speed", 20, 300);
            g.drawString("Restart the program to change speed", 20,400);
        } requestFocusInWindow();
    }

    // ----- KeyListener interface methods -----
    public void keyTyped(KeyEvent e) { } // unimplemented

    public void keyPressed(KeyEvent e) {
        // see this for all keycodes: https://stackoverflow.com/questions/15313469/java-keyboard-keycodes-list
        // A = 65, D = 68, S = 83, W = 87, left = 37, up = 38, right = 39, down = 40, space = 32, enter = 10
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        pressedKeys[key] = true;
    }

    // ----- MouseListener interface methods -----
    public void mouseClicked(MouseEvent e) { }  // unimplemented; if you move your mouse while clicking,
    // this method isn't called, so mouseReleased is best

    public void mousePressed(MouseEvent e) { } // unimplemented

    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && gameOver) {
            gameOver = false;
            line = new ArrayList<>();
            score = 0;
            lines = 0;
            playMusic("src/tetris_theme.wav");
        }
    }

    public void mouseEntered(MouseEvent e) { } // unimplemented

    public void mouseExited(MouseEvent e) { } // unimplemented

    // ACTIONLISTENER INTERFACE METHODS: used for buttons AND timers!
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Timer) {
            if (!drops) {
                block.drop();
            }
            if (e.getSource() == timer2) {
                timer2.stop();
                if (drops) {
                    block.setInactive();
                }
                checkGrounded();
            }
        }
    }
}
