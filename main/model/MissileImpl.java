package model;

import ui.AsteroidDestroyer;

import java.awt.*;

public class MissileImpl extends Core {

    private Rectangle shape;
    private final int velocity;

    public MissileImpl(SpaceShipImpl spaceShip, int velocity) {
        int x = spaceShip.getX() + (int)spaceShip.getShape().getBounds().getWidth();
        int y = spaceShip.getY() + (int)spaceShip.getShape().getBounds().getHeight() / 2
                - getHeight(AsteroidDestroyer.level) / 2;
        shape = new Rectangle(x, y, getWidth(AsteroidDestroyer.level), getHeight(AsteroidDestroyer.level));
        this.velocity = velocity;
    }

    // EFFECTS: get the width of missile based on different level
    public int getWidth(String level) {
        int width = 0;
        switch (level) {
            case "Easy":
                width = 30;
                break;
            case "Normal":
                width = 20;
                break;
            case "Hard":
                width = 10;
                break;
            default:
        }
        return width;

    }

    // EFFECTS: get the height of missile based on different level
    public int getHeight(String level) {
        int height = 0;
        switch (level) {
            case "Easy":
                height = 9;
                break;
            case "Normal":
                height = 6;
                break;
            case "Hard":
                height = 3;
                break;
            default:
        }
        return height;

    }

    @Override
    // EFFECTS: draw the space ship
    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.draw(shape);
        g.fill(shape);
    }

    @Override
    public void move() {
        shape.x += velocity;
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public Boolean isVisible() {
        return shape.getX() < AsteroidDestroyer.width;
    }
}
