package model;

import ui.AsteroidDestroyer;

import java.awt.*;
import java.awt.geom.Area;
import java.util.Observable;
import java.util.Random;

// common methods among a spaceship and an asteroid
public abstract class Core extends Observable {
    private Random rand = new Random();

    int velocity;
    Color color;

    Core() {
        setVelocity();
        setColor();
    }

    public abstract void move(); // move the graphics

    public abstract Shape getShape(); // get the shape

    // EFFECTS: draw the actual graphics of ship or asteroid
    public void draw(Graphics2D g) {
        g.setColor(this.color);
        g.draw(this.getShape());
        g.fill(this.getShape());
    }

    // EFFECTS: check if this object is intersects with other
    public boolean intersects(Core other) {
        Area shapeArea = new Area(this.getShape());
        Area otherArea = new Area(other.getShape());
        shapeArea.intersect(otherArea);
        return !shapeArea.isEmpty();
    }

    private void setVelocity() {
        if (AsteroidDestroyer.level.equals("Easy")) {
            this.velocity += random(2,3);
        } else if (AsteroidDestroyer.level.equals("Normal")) {
            this.velocity += random(4,5);
        } else {
            this.velocity += random(6,7);
        }
    }

    public Boolean isVisible() {
        return getX() > -1 * 40;
    }

    void setColor() {
        this.color = new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));
    }


    static int random(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    public int getX() {
        return (int) getShape().getBounds().getX();
    }

    public int getY() {
        return (int) getShape().getBounds().getY();
    }

}
