package edu.gatech.cs2340.spacetrader.entity;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class SolarSystem {
    private double xCord;
    private double yCord;
    private String name;
    private Map<Integer, Planet> planets;
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
     * @param positionFromStar
     * @param p Planet object
     */
    public void addPlanet(int positionFromStar, Planet p) {
        planets.put(positionFromStar, p);
    }

    public double getxCord() {
        return xCord;
    }

    public void setxCord(double xCord) {
        this.xCord = xCord;
    }

    public double getyCord() {
        return yCord;
    }

    public void setyCord(double yCord) {
        this.yCord = yCord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
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
