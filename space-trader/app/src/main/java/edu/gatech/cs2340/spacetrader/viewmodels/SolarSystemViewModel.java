package edu.gatech.cs2340.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.SolarSystemInteractor;

/**
 * connects the solar system view with the model
 */
public class SolarSystemViewModel extends AndroidViewModel {

    private SolarSystemInteractor interactor;

    /**
     * creates an instance of the class
     * @param application the application to create the isntance for
     */
    public SolarSystemViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getSolarSystemInteractor();
    }

    /**
     * gets all of the planets in the solar system
     * @return the list of planets in the solar system
     */
    public List<Planet> getPlanetsInSolarSystem() {
        return interactor.getPlanetsInSolarSystem();
    }

    /**
     * has the player travel to a planet
     * @param planet the planet to travel to
     */
    public void travelToPlanet(Planet planet) {
        interactor.travelToPlanet(planet);
    }
}
