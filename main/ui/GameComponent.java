package ui;

import model.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.Iterator;

public class GameComponent extends JComponent {

    static SpaceShipImpl myShip;
    private AsteroidMap asteroidMap;
    private Timer [] timer;
    private ShipKeyListener shipKeyListener;
    static int isReplay;

    GameComponent() {
        asteroidMap = new AsteroidMap();
        timer = new Timer[2];
        timer[0] = setGameTimer();
        timer[1] = setAsteroidTimer();
        shipKeyListener = new ShipKeyListener();
    }

    // EFFECTS: start the game
    void start() {
        myShip = new SpaceShipImpl(100, 10,AsteroidDestroyer.height / 3);
        myShip.setMovementBounds(new Rectangle2D.Double(0,0,getWidth(),getHeight()));
        timer[0].start();
        timer[1].start();
        playMusic(getMusic());
        addKeyListener(shipKeyListener);
    }

    private void replay() {
        if (isReplay == 2) {
            gameOver();
        } else if (isReplay == 1) {
            timer[0].stop();
            timer[1].stop();
            asteroidMap.clear();
            isReplay = 0;
            removeKeyListener(shipKeyListener);
            start();
        }
    }

    // effects:  create a timer that updates game each 20 milliseconds
    private Timer setGameTimer() {
        return new Timer(10, ae -> update());
    }

    private Timer setAsteroidTimer() {
        int delay;
        if (AsteroidDestroyer.level.equals("Hard")) {
            delay = 150;
        } else if (AsteroidDestroyer.level.equals("Normal")) {
            delay = 200;
        } else {
            delay = 250;
        }
        return new Timer(delay, e -> asteroidMap.generateAsteroid());
    }

    // EFFECTS: paint everything
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,AsteroidDestroyer.width,AsteroidDestroyer.height);
        for (Core asteroid : asteroidMap) {
            asteroid.draw(g2);
        }
        if (isGameOver()) {
            paintString(g);
        } else {
            myShip.draw(g2);
            for (MissileImpl missile : myShip.getMissiles()) {
                missile.draw(g2);
            }
            isReplay = 0;
        }
    }

    private void paintString(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 200));
        g.drawString("Wasted ! ", 50, 300);
        g.setFont(new Font("Arial", Font.BOLD,50));
        g.drawString("R for replay; Q for quit",100,400);
    }

    private void moveAsteroid() {
        for (Core asteroid : asteroidMap) {
            asteroid.move();
            checkCollision(asteroid);
        }
    }

    private void checkCollision(Core asteroid) {
        if (!asteroid.isVisible()) { // off bound
            asteroidMap.removeAsteroid(asteroid);
        } else if (asteroid.intersects(myShip)) { // hit by ship
            asteroidMap.removeAsteroid(asteroid);
            myShip.gotHit();
            myShip.addKills();
        }
        // hit by missile
        removeInvalidMissile(asteroid);
    }

    private void removeInvalidMissile(Core asteroid) {
        Iterator<MissileImpl> missileIterator = myShip.getMissiles().iterator();
        while (missileIterator.hasNext()) {
            MissileImpl missile = missileIterator.next();
            if (missile.intersects(asteroid) && asteroid.intersects(missile)) {
                asteroidMap.removeAsteroid(asteroid);
                myShip.addKills();
                if (AsteroidDestroyer.level.equals("Hard")) {
                    missileIterator.remove();
                }
            } else if (!missile.isVisible()) {
                missileIterator.remove();
            }
        }
    }

    private String getMusic() {
        switch (AsteroidDestroyer.level) {
            case "Easy":
                return "level1.wav";
            case "Normal":
                return "level2.wav";
            case "Hard":
                return "level3.wav";
            default:
                return "";
        }
    }

    // EFFECTS: play music
    public static void playMusic(String fileName) {
        //https://www.youtube.com/watch?v=3q4f6I5zi2w
        InputStream music;
        try {
            music = new FileInputStream(new File(fileName));
            AudioStream audioStream = new AudioStream(music);
            AudioPlayer.player.start(audioStream);
        } catch (Exception e) {
            System.out.println("Error");
        }

    }

    private Boolean isGameOver() {
        return myShip.getHealth() <= 0;
    }

    private void gameOver() {
        timer[0].stop();
        timer[1].stop();
        removeKeyListener(shipKeyListener); // remove the ship key listener
        System.out.println("Thank For Playing This Game!");
        System.exit(0);
    }

    // EFFECTS: update the details of the game
    private void update() {
        this.requestFocusInWindow(); // https://stackoverflow.com/questions/34937116/why-my-java-keylistener-doesnt-work-for-my-ubuntu
        myShip.move();
        for (MissileImpl missile : myShip.getMissiles()) {
            missile.move();
        }
        moveAsteroid();
        if (isGameOver()) {
            replay();
        }
        repaint();
    }
}
