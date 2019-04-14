package edu.gatech.cs2340.spacetrader.entity;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * solar system class
 */
public class SolarSystem {
    private double xCord;
    private double yCord;
    private String name;
    private final Map<Integer, Planet> planets;
    private double distance;

    /**
     * Constructor for Solar System
     *
     * @param planetMap Takes a map of planets with key being
     *                  their position in the SolarSystem from the star
     * @param xCord the x coordinate of the SolarSystem in the universe
     * @param yCord the y coordinate of the SolarSystem in the universe
     * @param name the name of the solarSystem
     */
    public SolarSystem(Map<Integer, Planet> planetMap, int xCord, int yCord, String name) {
        planets = planetMap;
        this.xCord = xCord;
        this.yCord = yCord;
        this.name = name;
    }

    /**
     *
     * @return The number of Planets in the System
     */
    public int getNumPlanets() {
        return planets.size();
    }

    /**
     * Adds a planet to the SolarSystem
     *
     * @param positionFromStar the position away from the star
     * @param p Planet object
     */
    public void addPlanet(int positionFromStar, Planet p) {
        planets.put(positionFromStar, p);
    }

    /**
     *
     * @return xcord
     */
    public double getxCord() {
        return xCord;
    }

    /**
     *
     * @param xCord sets where the x cord is
     */
    public void setxCord(double xCord) {
        this.xCord = xCord;
    }

    /**
     *
     * @return ycord
     */
    public double getyCord() {
        return yCord;
    }

    /**
     *
     * @param yCord ycord
     */
    public void setyCord(double yCord) {
        this.yCord = yCord;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param distance distance
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     *
     * @return distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     *
     * @return list of planets
     */
    public List<Planet> getPlanets() {
        List<Planet> planetList = new ArrayList<>();
        for (int i : planets.keySet()) {
            planetList.add(planets.get(i));
        }
        return planetList;
    }
    /**
     * the tostring method
     * @return the string representation of a Solar System
     */
    @Override
    @NonNull
    public String toString() {
        StringBuilder rep = new StringBuilder();
        rep.append(name);
        rep.append(" is located at (");
        rep.append(xCord);
        rep.append(",");
        rep.append(yCord);
        rep.append(") and has ");
        rep.append(planets.size());
        rep.append(" planets: ");
        for (Planet p : planets.values()) {
            rep.append(p.toString());
            rep.append(", ");
        }
        rep.append(".");
        return rep.toString();
    }

    /**
     * gets the planet that is i units from the star
     * @param i the number planet that we want
     * @return the ith planet in the solar system
     */
    public Planet getPlanet(int i) {
        return planets.get(i);
    }
}
