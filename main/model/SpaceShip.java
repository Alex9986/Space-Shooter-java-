package model;

import java.awt.geom.Rectangle2D;

public interface SpaceShip {

    // a set of all possible directions
    enum Direction {

        STAY(0), UP(-1), DOWN(1), LEFT(-2), RIGHT(2), SPEEDUP(3);

        public final int directionInt; // field of the enum direction

        Direction(int direction) { // constructor of the direction
            this.directionInt = direction;
        }

    }

    void setDirection(Direction newDirection);

    String getDirection();

    void setMovementBounds(Rectangle2D movementBounds);
}
