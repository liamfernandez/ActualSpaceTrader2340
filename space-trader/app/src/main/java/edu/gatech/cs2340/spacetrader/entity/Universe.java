package edu.gatech.cs2340.spacetrader.entity;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Class defines the Game's Universe
 */
public class Universe {
    private final List<SolarSystem> systems;

    /**
     * creates the default solar system
     */
    public Universe() {
        systems = new ArrayList<>();
        Map<Integer, Planet> s1Map = new HashMap<>();
        s1Map.put(1, new Planet("Drake", TechLevel.PreAgriculture, Resources.POORSOIL));
        s1Map.put(2, new Planet("Josh", TechLevel.Agriculture, Resources.MINERALPOOR));
        systems.add(new SolarSystem(s1Map, 1, 1, "Meganville"));
        Map<Integer, Planet> s2Map = new HashMap<>();
        s2Map.put(1, new Planet("Bert", TechLevel.Medieval, Resources.RICHFAUNA));
        s2Map.put(2, new Planet("Ernie", TechLevel.Medieval, Resources.RICHSOIL));
        systems.add(new SolarSystem(s2Map, 10, 3, "The Street"));
        Map<Integer, Planet> s3Map = new HashMap<>();
        s3Map.put(1, new Planet("Carly", TechLevel.Hi_Tech, Resources.LOTSOFWATER));
        s3Map.put(2, new Planet("Sam", TechLevel.Hi_Tech, Resources.DESERT));
        systems.add(new SolarSystem(s3Map, 20, 7, "iCarly"));
        Map<Integer, Planet> s4Map = new HashMap<>();
        s4Map.put(1, new Planet("Wiz", TechLevel.Renaissance, Resources.LOTSOFHERBS));
        s4Map.put(2, new Planet("Snoop", TechLevel.Renaissance, Resources.WEIRDMUSHROOMS));
        systems.add(new SolarSystem(s4Map, 20, 29, "Buddy land"));
        Map<Integer, Planet> s5Map = new HashMap<>();
        s5Map.put(1, new Planet("Ariana", TechLevel.Post_Industrial, Resources.LIFELESS));
        s5Map.put(2, new Planet("Pete", TechLevel.Post_Industrial, Resources.ARTISTIC));
        systems.add(new SolarSystem(s5Map, 30, 30, "Break town"));
        largeLog("APP", this.toString());
    }

    private void universeHelper() {
        Map<Integer, Planet> s6Map = new HashMap<>();
        s6Map.put(1, new Planet("Sparrow", TechLevel.Industrial, Resources.RICHSOIL));
        s6Map.put(2, new Planet("Blackbeard", TechLevel.Industrial, Resources.MINERALRICH));
        systems.add(new SolarSystem(s6Map, 45, 35, "Niceopolis"));
        Map<Integer, Planet> s7Map = new HashMap<>();
        s7Map.put(1, new Planet("Chesik", TechLevel.PreAgriculture, Resources.LIFELESS));
        s7Map.put(2, new Planet("Showalter", TechLevel.PreAgriculture, Resources.LIFELESS));
        systems.add(new SolarSystem(s7Map, 60, 40, "Spiketopia"));
        Map<Integer, Planet> s8Map = new HashMap<>();
        s8Map.put(1, new Planet("Cosmoland", TechLevel.Hi_Tech, Resources.LOTSOFHERBS));
        s8Map.put(2, new Planet("Wandapolis", TechLevel.Industrial, Resources.LOTSOFWATER));
        systems.add(new SolarSystem(s8Map, 70, 85, "Oddparentia"));
        Map<Integer, Planet> s9Map = new HashMap<>();
        s9Map.put(1, new Planet("Garth", TechLevel.Post_Industrial, Resources.WEIRDMUSHROOMS));
        s9Map.put(2, new Planet("Wayne", TechLevel.Post_Industrial, Resources.LOTSOFHERBS));
        systems.add(new SolarSystem(s9Map, 90, 45, "Waynes World"));
        Map<Integer, Planet> s10Map = new HashMap<>();
        s10Map.put(1, new Planet("Seth", TechLevel.Hi_Tech, Resources.WARLIKE));
        s10Map.put(2, new Planet("Franco", TechLevel.Hi_Tech, Resources.WARLIKE));
        systems.add(new SolarSystem(s10Map, 100, 100, "Pineappleappalis"));
    }
    /**
     * Returns a HashMap of all systems in range
     *
     * @param currrentSystem System to search from
     * @param shipRange Radius to search in
     * @return HashMap of SolarSystems; key is the distance, value is the SolarSystem
     */
    public HashMap<Double, SolarSystem> getSystemsInRange(SolarSystem currrentSystem,
                                                          double shipRange) {
        SortedMap<Double, SolarSystem> distanceOfSystems = new TreeMap<>();

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
    private static double computeSeparation(SolarSystem s1, SolarSystem s2) {
        return Math.sqrt(
                Math.pow(s1.getxCord() - s2.getxCord(), 2)
                        + Math.pow(s1.getyCord() - s2.getyCord(), 2)
        );
    }

    /**
     *
     * @return systems
     */
    public List<SolarSystem> getAllSystems() {
        return systems;
    }

    /**
     * Adds a SolarSystem to the Universe
     *
     * @param s Solar System to add
     */
    public void addSystem(SolarSystem s) {
        systems.add(s);
    }

    /**
     * overides to string
     * @return a string representing the universe
     */
    @Override
    @NonNull
    public String toString() {
        StringBuilder representation = new StringBuilder();
        representation.append("The universe that you are playing in is composed of ");
        representation.append(systems.size());
        representation.append(" Solar Systems. That are as follows: ");
        for (SolarSystem s : systems) {
            representation.append(s.toString());
            representation.append(", ");
        }
        representation.append(".");
        return representation.toString();
    }

    /**
     * a method to log a large entry
     * @param tag the tag
     * @param content the content
     */
    public static void largeLog(String tag, String content) {
        if (content.length() > 4000) {
            //Log.d(tag, content.substring(0, 4000));
            largeLog(tag, content.substring(4000));
        } else {
            //Log.d(tag, content);
        }
    }

    /**
     * gets the starting planet
     * @return Planet the starting planet for the game
     */
    public Planet getStartingPlanet() {
        SolarSystem s = systems.get(0);
        return s.getPlanet(1);
    }

    /**
     * Returns the starting solar system to begin the game
     * @return the solar system
     */
    public SolarSystem getStartingSolarSystem() {
        return systems.get(0);
    }

    /**
     * creates the mock items
     * @return the list of mock items
     */
    public List<MockItem> initMockItems() {
        List<MockItem> mockList = new ArrayList<>();
        mockList.add(new MockItem(Item.WATER, 30, 30));
        mockList.add(new MockItem(Item.FURS, 250,250));
        mockList.add(new MockItem(Item.FOOD, 100, 100));
        mockList.add(new MockItem(Item.ORE, 350, 350));
        mockList.add(new MockItem(Item.GAMES, 250, 250));
        mockList.add(new MockItem(Item.FIREARMS, 1250, 1250));
        mockList.add(new MockItem(Item.MEDICINE, 650, 650));
        mockList.add(new MockItem(Item.MACHINES, 900, 900));
        mockList.add(new MockItem(Item.NARCOTICS, 3500, 3500));
        mockList.add(new MockItem(Item.ROBOTS, 5000, 5000));
        return mockList;
    }
}
