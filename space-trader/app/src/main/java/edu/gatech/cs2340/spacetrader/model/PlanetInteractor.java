package edu.gatech.cs2340.spacetrader.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.entity.SolarSystem;
import edu.gatech.cs2340.spacetrader.entity.Universe;

/**
 *
 */
public class PlanetInteractor extends Interactor {

    /**
     *
     * @param repo repository
     */
    public PlanetInteractor(Repository repo) {
        super(repo);
    }

    /**
     *
     * @param solarSystem ss
     */
    public void travelToSolarSystem(SolarSystem solarSystem) {
        Repository r = getRepository();
        Player p  = r.getPlayer();
        p.setCurrSolarSystem(solarSystem);
        p.subtractFuel((int) solarSystem.getDistance());
    }

    /**
     *
     * @return list of solar systems
     */
    public List<SolarSystem> getInRangeSolarSystems() {
        double fuel;
        SolarSystem solarSystem;
        Repository r = getRepository();
        Universe universe = r.getUniverse();
        fuel = r.getPlayerFuel();
        Player p = r.getPlayer();
        if (p.getCurrSolarSystem() == null) {
            solarSystem = universe.getStartingSolarSystem();
            Planet startingPlanet = solarSystem.getPlanet(1);
            p.setCurrSolarSystem(solarSystem);
            p.setCurrPlanet(startingPlanet);
        } else {
            solarSystem = p.getCurrSolarSystem();
        }


        HashMap<Double, SolarSystem> map = universe.getSystemsInRange(solarSystem, fuel);

        List<SolarSystem> inRangeSolarSystems = new ArrayList<>();

        for (Double key : map.keySet()) {

            SolarSystem s = map.get(key);
            if (s != null) {
                s.setDistance(key);
            }
            inRangeSolarSystems.add(s);
        }

        Collections.sort(inRangeSolarSystems, (a, b) ->
                (int) a.getDistance() - (int) b.getDistance());
        return inRangeSolarSystems;
    }

    /**
     *
     * @return current planet
     */
    public Planet getCurrentPlanet() {
        SolarSystem solarSystem;
        Repository r = getRepository();
        Universe universe = r.getUniverse();
        Player p = r.getPlayer();

        if (p.getCurrPlanet() == null) {
            solarSystem = universe.getStartingSolarSystem();
            Planet startingPlanet = solarSystem.getPlanet(1);
            p.setCurrPlanet(startingPlanet);

        }
        return p.getCurrPlanet();
    }

    /**
     *
     * @return fuel
     */
    public int getFuel() {
        Repository r = getRepository();
        Player p = r.getPlayer();
        return (int) p.getFuel();
    }
}
