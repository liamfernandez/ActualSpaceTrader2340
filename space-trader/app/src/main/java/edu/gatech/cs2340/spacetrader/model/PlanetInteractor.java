package edu.gatech.cs2340.spacetrader.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.SolarSystem;
import edu.gatech.cs2340.spacetrader.entity.Universe;

public class PlanetInteractor extends Interactor {

    public PlanetInteractor(Repository repo) {
        super(repo);
    }

    public void travelToSolarSystem(SolarSystem solarSystem) {
        getRepository().getPlayer().setCurrSolarSystem(solarSystem);
        getRepository().getPlayer().subtractFuel((int) solarSystem.getDistance());
    }

    public List<SolarSystem> getInRangeSolarSystems() {
        int x;
        int y;
        double fuel;
        SolarSystem solarSystem;

        Universe universe = getRepository().getUniverse();
        fuel = getRepository().getPlayerFuel();

        if (getRepository().getPlayer().getCurrSolarSystem() == null) {
            solarSystem = universe.getStartingSolarSystem();
            Planet startingPlanet = solarSystem.getPlanet(1);
            getRepository().getPlayer().setCurrSolarSystem(solarSystem);
            getRepository().getPlayer().setCurrPlanet(startingPlanet);
        } else {
            solarSystem = getRepository().getPlayer().getCurrSolarSystem();
        }


        HashMap<Double, SolarSystem> map = universe.getSystemsInRange(solarSystem, fuel);

        List<SolarSystem> inRangeSolarSystems = new ArrayList<>();

        for (Double key : map.keySet()) {

            SolarSystem s = map.get(key);
            s.setDistance(key);
            inRangeSolarSystems.add(s);
        }

        Collections.sort(inRangeSolarSystems, (a, b) -> (int) a.getDistance() - (int) b.getDistance());
        return inRangeSolarSystems;
    }

    public Planet getCurrentPlanet() {
        SolarSystem solarSystem;
        Universe universe = getRepository().getUniverse();

        if (getRepository().getPlayer().getCurrPlanet() == null) {
            solarSystem = universe.getStartingSolarSystem();
            Planet startingPlanet = solarSystem.getPlanet(1);
            getRepository().getPlayer().setCurrPlanet(startingPlanet);

        }
        return getRepository().getPlayer().getCurrPlanet();
    }
}
