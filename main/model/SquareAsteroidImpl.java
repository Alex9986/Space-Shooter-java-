package model;

import java.awt.*;

public class SquareAsteroidImpl extends Core {

    private final Rectangle shape;

    public SquareAsteroidImpl(int x, int y, int width, int height) {
        super();
        shape = new Rectangle(x,y,width,height);
    }

    @Override
    // MODIFIES: this
    public void move() {
        shape.x -= velocity;
    }

    @Override
    public Shape getShape() {
        return this.shape;
    }

}
