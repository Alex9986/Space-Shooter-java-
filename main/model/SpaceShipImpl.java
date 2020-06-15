package model;

import ui.AsteroidDestroyer;
import ui.GameComponent;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;

public class SpaceShipImpl extends Core implements SpaceShip {
    private final int stay = Direction.STAY.directionInt;
    private final Polygon shape;
    private int health;
    private int kills;
    private Direction direction;
    private Rectangle2D movementBounds;
    private ArrayList<MissileImpl> missiles;

    public SpaceShipImpl(int health, int x, int y) {
        this.health = health;
        this.kills = 0;
        shape = new Polygon(
                new int[] {0,10,10,20,10,30,45,30,20,10,10,10,10,20,10,0},
                new int[] {5,10,0,10,10,10,13,15,15,25,15,10,15,15,15,20}, 16);
        shape.translate(x, y);
        this.direction = Direction.STAY;
        setColor();
        missiles = new ArrayList<>();
    }

    // EFFECTS: get the health of ship
    public int getHealth() {
        return health;
    }

    // EFFECTS: get the kills of ship
    public int getKills() {
        return kills;
    }

    public ArrayList<MissileImpl> getMissiles() {
        return this.missiles;
    }

    // EFFECTS: get the status of the ship
    public String toString() {
        return "Health: " + this.getHealth() + "\nKills: " + this.getKills()
                + "\nDirection: " + this.getDirection();
    }

    // MODIFIES: this
    // EFFECTS: reduce ship's health based on different level
    public void gotHit() {
        if (AsteroidDestroyer.level.equals("Hard")) {
            this.health -= 34; // 3
        } else if (AsteroidDestroyer.level.equals("Normal")) {
            this.health -= 25; // 4
        } else {
            this.health -= 20; // 5
        }
    }

    public void addKills() {
        this.kills++;
    }

    // MODIFIES: this
    // EFFECTS: fire a new missile
    public void fireMissile() {
        MissileImpl missile = new MissileImpl(this,4);
        missiles.add(missile);
        GameComponent.playMusic("missile firing.wav");
    }

    @Override
    // MODIFIES: this
    // EFFECTS: set the direction of the ship
    public void setDirection(Direction newDirection) {
        this.direction = newDirection;
    }

    @Override
    // EFFECTS: get the current direction
    public String getDirection() {
        return "Direction:" + this.direction + "(" + this.direction.directionInt + ")";
    }

    @Override
    // MODIFIES: this
    // EFFECTS: set the movement bounds of the ship
    public void setMovementBounds(Rectangle2D movementBounds) {
        this.movementBounds = movementBounds;
    }

    @Override
    // EFFECTS: draw the actual ship
    public void draw(Graphics2D g) {
        g.setColor(Color.red);
        g.fillPolygon(shape);
        g.setColor(Color.green);
        if (this.getY() > 4) {
            g.fillRect(this.getX(),this.getY() - 4,this.getHealth() / 3,4);
        } else {
            g.fillRect(this.getX(),(int) this.shape.getBounds().getHeight() + 4,this.getHealth() / 3,4);
        }
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Kills: " + this.getKills(),8,20);
    }

    @Override
    // EFFECTS: move the space ship
    public void move() {
        if (this.direction.directionInt == -1 || this.direction.directionInt == 1) {
            shape.translate(stay, this.direction.directionInt);
        } else if (this.direction.directionInt == -2 || this.direction.directionInt == 2
                || this.direction.directionInt == 3) {
            shape.translate(this.direction.directionInt, stay);
        } else { // stay
            shape.translate(stay, stay);
        }
        handleBoundary();
    }

    private void handleBoundary() {
        // top bound
        if (shape.getBounds().getY() < 0) {
            shape.translate(stay, Direction.DOWN.directionInt);
        //bottom bound
        } else if (shape.getBounds().getY() + shape.getBounds().getHeight() > movementBounds.getHeight()) {
            shape.translate(stay,Direction.UP.directionInt);
        // left bound
        } else if (shape.getBounds().getX() < 0) {
            shape.translate(Direction.RIGHT.directionInt,stay);
        // right bound
        } else if (shape.getBounds().getX() > movementBounds.getWidth() - shape.getBounds().getWidth()) {
            shape.translate(Direction.LEFT.directionInt,stay);
        }
    }

    @Override
    // EFFECTS: get the shape
    public Shape getShape() {
        return shape;
    }

}
