package edu.gatech.cs2340.spacetrader.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Universe {
    private List<SolarSystem> systems;

    public Universe() {
        //implement default solar systems
    }

    /**
     * Returns a HashMap of all systems in range
     *
     * @param currrentSystem System to search from
     * @param shipRange Radius to search in
     * @return HashMap of SolarSystems; key is the distance, value is the SolarSystem
     */
    HashMap<Double, SolarSystem> getSystemsInRange(SolarSystem currrentSystem, double shipRange) {
        TreeMap<Double, SolarSystem> distanceOfSystems = new TreeMap<>();

        for (SolarSystem s :systems) {
            distanceOfSystems.put(computeSeparation(currrentSystem, s), s);
        }
        HashMap<Double, SolarSystem> inRangeSystems = new HashMap<>();
        for (Map.Entry<Double, SolarSystem> entry : distanceOfSystems.entrySet()) {
            if (entry.getKey() <= shipRange) {
                inRangeSystems.put(entry.getKey(), entry.getValue());
            }
        }
        return inRangeSystems;
    }

    /**
     * Computes the straight line distance between two SolarSystems
     *
     * @param s1 System 1
     * @param s2 System 2
     * @return Straight ine distance between s1 & s2
     */
    private double computeSeparation(SolarSystem s1, SolarSystem s2) {
        return Math.sqrt(
                Math.pow(s1.getxCord() - s2.getxCord(), 2)
                        + Math.pow(s1.getyCord() - s2.getyCord(), 2)
        );
    }

    /**
     * Adds a SolarSystem to the Universe
     *
     * @param s Solar System to add
     */
    public void addSystem(SolarSystem s) {
        systems.add(s);
    }
}
