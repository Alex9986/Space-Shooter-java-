package model;

import ui.AsteroidDestroyer;

import java.util.Random;

// factory pattern
public class AsteroidFactory {

    public Core makeAsteroid() {
        Random rand = new Random();
        int decision = rand.nextInt(10);
        int size = 40;
        if (decision < 5) {
            return new EllipseAsteroidImpl(900,
                    Core.random(1, AsteroidDestroyer.height - size), Core.random(10, size), Core.random(10, size));
        } else {
            return new SquareAsteroidImpl(900,
                    Core.random(1, AsteroidDestroyer.height - size), Core.random(10, size), Core.random(10, size));
        }
    }
}
