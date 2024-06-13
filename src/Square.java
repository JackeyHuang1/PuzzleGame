import java.awt.*;

public class Square extends Blocks {

    public Square() {
        super(Color.YELLOW);
    }

    public void placeBlock(int x, int y) {
        getBlocks()[0].setX(x);
        getBlocks()[0].setY(y);
        getBlocks()[1].setX(x + 21);
        getBlocks()[1].setY(y + 21);
        getBlocks()[2].setX(x);
        getBlocks()[2].setY(y + 21);
        getBlocks()[3].setX(x + 21);
        getBlocks()[3].setY(y);
    }


}
