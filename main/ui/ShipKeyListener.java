package ui;

import model.SpaceShip;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ShipKeyListener extends KeyAdapter {
    // EFFECTS: perform different task based on the key user pressed
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_SPACE:
                GameComponent.myShip.setDirection(getDirection(event));
                break;
            case KeyEvent.VK_A:
                GameComponent.myShip.fireMissile();
                break;
            case KeyEvent.VK_R:
            case KeyEvent.VK_Q:
                GameComponent.isReplay = getIsReplay(event);
                break;
            default:
        }
    }

    private SpaceShip.Direction getDirection(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_UP:
                return SpaceShip.Direction.UP;
            case KeyEvent.VK_DOWN:
                return SpaceShip.Direction.DOWN;
            case KeyEvent.VK_LEFT:
                return SpaceShip.Direction.LEFT;
            case KeyEvent.VK_RIGHT:
                return SpaceShip.Direction.RIGHT;
            case KeyEvent.VK_SPACE:
                return SpaceShip.Direction.SPEEDUP;
            default:
                return null;
        }
    }

    private int getIsReplay(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_R:
                return 1;
            case KeyEvent.VK_Q:
                return 2;
            default:
                return 0;
        }
    }

    // EFFECTS: when space key is released the ship should not move anymore
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            GameComponent.myShip.setDirection(SpaceShip.Direction.STAY);
        }
    }
}
