package edu.gatech.cs2340.spacetrader.model;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Planet;

/**
 *  allows the solar system to do things with other classes
 */
public class SolarSystemInteractor extends Interactor {
    /**
     * creates instance of the class
     * @param repo the repository class associated with the game
     */
    public SolarSystemInteractor(Repository repo) {
        super(repo);
    }

    /**
     * gets the planets in the solar system
     * @return the list of planets
     */
    public List<Planet> getPlanetsInSolarSystem() {
        List<Planet> planets;
        planets = getRepository().getPlayer().getCurrSolarSystem().getPlanets();
        return planets;
    }

    /**
     * travels to a planet
     * @param planet the planet to travel to
     */
    public void travelToPlanet(Planet planet) {
        getRepository().getPlayer().setCurrPlanet(planet);
    }

}
