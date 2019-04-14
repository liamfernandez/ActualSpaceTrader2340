package edu.gatech.cs2340.spacetrader.model;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.entity.SolarSystem;

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
        Repository r = getRepository();
        Player p = r.getPlayer();
        SolarSystem s = p.getCurrSolarSystem();
        planets = s.getPlanets();
        return planets;
    }

    /**
     * travels to a planet
     * @param planet the planet to travel to
     */
    public void travelToPlanet(Planet planet) {
        Repository r = getRepository();
        Player p = r.getPlayer();
        p.setCurrPlanet(planet);
    }

}
