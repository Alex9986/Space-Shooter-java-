package model;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Objects;

public class EllipseAsteroidImpl extends Core {

    private final Ellipse2D.Double shape;

    public EllipseAsteroidImpl(int x, int y, int width, int height) {
        super();
        shape = new Ellipse2D.Double(x,y,width,height);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: make asteroid move from right to left
    public void move() {
        shape.x -= this.velocity;
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    // EFFECTS: override the equal method
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EllipseAsteroidImpl asteroid = (EllipseAsteroidImpl) o;
        return velocity == asteroid.velocity && shape.equals(asteroid.shape) && color.equals(asteroid.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(velocity, shape, color);
    }
}
