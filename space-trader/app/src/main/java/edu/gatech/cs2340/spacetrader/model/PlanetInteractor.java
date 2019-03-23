package edu.gatech.cs2340.spacetrader.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.SolarSystem;
import edu.gatech.cs2340.spacetrader.entity.Universe;

public class PlanetInteractor extends Interactor {

    public PlanetInteractor(Repository repo) {
        super(repo);
    }

    public void travelToSolarSystem(SolarSystem solarSystem) {
        getRepository().getPlayer().setCurrSolarSystem(solarSystem);
        getRepository().getPlayer().setFuel((int) solarSystem.getDistance());
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
            getRepository().getPlayer().setCurrSolarSystem(solarSystem);
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

        return inRangeSolarSystems;
    }
}
