package edu.gatech.cs2340.spacetrader.entity;

import java.util.Map;

public class SolarSystem {
    private double xCord;
    private double yCord;
    private String name;
    private Map<Integer, Planet> planets;

    /**
     * Constructor for Planet
     *
     * @param planetMap Takes a map of planets with key being
     *                  their position in the SolarSystem from the star
     */
    public SolarSystem(Map<Integer, Planet> planetMap) {
        planets = planetMap;
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
}
