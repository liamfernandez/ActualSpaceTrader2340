package edu.gatech.cs2340.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.SolarSystemInteractor;


public class SolarSystemViewModel extends AndroidViewModel {

    private SolarSystemInteractor interactor;

    public SolarSystemViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getSolarSystemInteractor();
    }

    public List<Planet> getPlanetsInSolarSystem() {
        return interactor.getPlanetsInSolarSystem();
    }

    public void travelToPlanet(Planet planet) {
        interactor.travelToPlanet(planet);
    }
}
