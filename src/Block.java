import java.awt.*;

public class Block {
    private Color color;
    private int x;
    private int y;

    public Block(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, 20, 20);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 20, 20);
    }
}
