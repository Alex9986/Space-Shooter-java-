package model;

import java.util.*;

public class AsteroidMap implements Iterable<Core> {

    private Map<Integer, Core> asteroidMap;
    private int numberOfAsteroid = 0;
    private AsteroidFactory asteroidFactory;


    public AsteroidMap() {
        asteroidMap = new HashMap<>();
        asteroidFactory = new AsteroidFactory();
    }

    // MODIFIES: this
    // EFFECTS: generate a new asteroid and add to asteroid hash map
    public void generateAsteroid() {
        Core asteroid = asteroidFactory.makeAsteroid();
        asteroidMap.put(numberOfAsteroid, asteroid);
        numberOfAsteroid++;
    }

    // MODIFIES: this
    // EFFECTS: remove the invalid asteroid(hit by missile or spaceship or off bound)
    public void removeAsteroid(Core asteroid) {
        for (Map.Entry<Integer, Core> entry : asteroidMap.entrySet()) {
            if (entry.getValue().equals(asteroid)) {
                asteroidMap.remove(entry.getKey());
                break;
            }
        }
    }

    public void clear() {
        asteroidMap.clear();
    }

    // EFFECTS: get the iterator of the asteroid map
    @Override
    public Iterator<Core> iterator() {
        ArrayList<Core> asteroidSet = new ArrayList<>();
        for (Integer integer : asteroidMap.keySet()) {
            asteroidSet.add(asteroidMap.get(integer));
        }
        return asteroidSet.iterator();
    }
}