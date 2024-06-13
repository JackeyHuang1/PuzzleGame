import java.awt.*;

public class Z2 extends Blocks{
    public Z2() {
        super(Color.GREEN);
    }

    public void placeBlock(int x, int y) {
        getBlocks()[0].setX(x);
        getBlocks()[0].setY(y);
        getBlocks()[1].setX(x-21);
        getBlocks()[1].setY(y);
        getBlocks()[2].setX(x);
        getBlocks()[2].setY(y-21);
        getBlocks()[3].setX(x+21);
        getBlocks()[3].setY(y-21);
    }

    public void rotation1() {
        getTemp()[0].setX(getBlocks()[0].getX());
        getTemp()[0].setY(getBlocks()[0].getY());
        getTemp()[1].setX(getBlocks()[0].getX()-21);
        getTemp()[1].setY(getBlocks()[0].getY());
        getTemp()[2].setX(getBlocks()[0].getX());
        getTemp()[2].setY(getBlocks()[0].getY()-21);
        getTemp()[3].setX(getBlocks()[0].getX()+21);
        getTemp()[3].setY(getBlocks()[0].getY()-21);

        rotation();
    }
    public void rotation2() {
        getTemp()[0].setX(getBlocks()[0].getX());
        getTemp()[0].setY(getBlocks()[0].getY());
        getTemp()[1].setX(getBlocks()[0].getX());
        getTemp()[1].setY(getBlocks()[0].getY()-21);
        getTemp()[2].setX(getBlocks()[0].getX()+21);
        getTemp()[2].setY(getBlocks()[0].getY());
        getTemp()[3].setX(getBlocks()[0].getX()+21);
        getTemp()[3].setY(getBlocks()[0].getY()+21);

        rotation();
    }

    public void rotation3() {
        rotation1();
    }

    public void rotation4() {
        rotation2();
    }
}
