package edu.gatech.cs2340.spacetrader.model;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.SolarSystem;

public class SolarSystemInteractor extends Interactor {

    public SolarSystemInteractor(Repository repo) {
        super(repo);
    }

    public List<Planet> getPlanetsInSolarSystem() {
        List<Planet> planets = new ArrayList<>();
        planets = getRepository().getPlayer().getCurrSolarSystem().getPlanets();
        return planets;
    }

    public void travelToPlanet(Planet planet) {
        getRepository().getPlayer().setCurrPlanet(planet);
    }

}
