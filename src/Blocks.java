import java.awt.*;

public class Blocks {
    private Block[] block = new Block[4];
    private Block[] temp = new Block[4];
    private int rot;
    private boolean drops;
    private boolean leftCollision;
    private boolean rightCollision;;
    private boolean current;

    public Blocks(Color color) {
        current = true;
        block[0] = new Block(color);
        block[1] = new Block(color);
        block[2] = new Block(color);
        block[3] = new Block(color);
        rot = 1;
        // to check rotations
        temp[0] = new Block(color);
        temp[1] = new Block(color);
        temp[2] = new Block(color);
        temp[3] = new Block(color);
    }

    public Block[] getBlocks() {
        return block;
    }

    public Block[] getTemp() {
        return temp;
    }

    public int getRot() {
        return rot;
    }

    public boolean getActive() {
        return current;
    }

    public void setInactive() {
        current = false;
    }

    public void setActive() {
        current = true;
    }

    public void placeBlock(int x, int y) { }

    public void drawBlock(Graphics g) {
        block[0].draw(g);
        block[1].draw(g);
        block[2].draw(g);
        block[3].draw(g);
    }

    public void drop() {
        block[0].setY(block[0].getY() + 21);
        block[1].setY(block[1].getY() + 21);
        block[2].setY(block[2].getY() + 21);
        block[3].setY(block[3].getY() + 21);
    }

    public void moveLeft() {
        if (current) {
            block[0].setX(block[0].getX() - 21);
            block[1].setX(block[1].getX() - 21);
            block[2].setX(block[2].getX() - 21);
            block[3].setX(block[3].getX() - 21);
        }
    }

    public void moveRight() {
        if (current) {
            block[0].setX(block[0].getX() + 21);
            block[1].setX(block[1].getX() + 21);
            block[2].setX(block[2].getX() + 21);
            block[3].setX(block[3].getX() + 21);
        }
    }

    public void moveDown() {
        block[0].setY(block[0].getY() + 21);
        block[1].setY(block[1].getY() + 21);
        block[2].setY(block[2].getY() + 21);
        block[3].setY(block[3].getY() + 21);
    }

    public void adjustRotLeft() {
        rot--;
        if (rot == 0) {
            rot = 4;
        }
    }

    public void adjustRotRight() {
        rot++;
        if (rot == 5) {
            rot = 1;
        }
    }

    public void rotation() {
        if (checkCollision() && current) {
            block[0].setX(temp[0].getX());
            block[0].setY(temp[0].getY());
            block[1].setX(temp[1].getX());
            block[1].setY(temp[1].getY());
            block[2].setX(temp[2].getX());
            block[2].setY(temp[2].getY());
            block[3].setX(temp[3].getX());
            block[3].setY(temp[3].getY());
        }
    }

    private void pieceCollision() {
        for (int i = 0; i < GraphicsPanel.line.size(); i++) {
            int x = GraphicsPanel.line.get(i).getX();
            int y = GraphicsPanel.line.get(i).getY();
            for (int j = 0; j < getBlocks().length; j++) {
                if (getBlocks()[j].getX() - 21 == x && getBlocks()[j].getY() == y) {
                    leftCollision = true;
                } else if (getBlocks()[j].getX() + 21 == x && getBlocks()[j].getY() == y) {
                    rightCollision = true;
                } else if (getBlocks()[j].getX() == x && getBlocks()[j].getY() + 21 == y) {
                    drops = true;
                }
            }
        }
    }

    public boolean checkCollision() {
        leftCollision = false;
        rightCollision = false;
        drops = false;
        pieceCollision();
        for (Block a : temp) {
            if (a.getX() < 244) {
                leftCollision = true;
            }
            if (a.getX() > 446) {
                rightCollision = true;
            }
            if (a.getY() > 420) {
                drops = true;
                current = false;
            }
        }
        if (!leftCollision && !rightCollision && !drops) {
            return true;
        } return false;
    }


    public void rotation1() {}
    public void rotation2() {}
    public void rotation3() {}
    public void rotation4() {}

}
