package edu.gatech.cs2340.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.entity.SolarSystem;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.PlanetInteractor;
import edu.gatech.cs2340.spacetrader.model.PlayerInteractor;
import edu.gatech.cs2340.spacetrader.views.MainActivity;

public class PlanetViewModel extends AndroidViewModel {

    private PlanetInteractor interactor;
    private PlayerInteractor playerInteractor;

    public PlanetViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getPlanetInteractor();
        playerInteractor = Model.getInstance().getPlayerInteractor();
    }

    public List<SolarSystem> getInRangeSolarSystems() {
        return interactor.getInRangeSolarSystems();
    }

    public Planet getCurrentPlanet() {
        return interactor.getCurrentPlanet();
    }

    public void travelToSolarSystem(SolarSystem solarSystem) {
        interactor.travelToSolarSystem(solarSystem);
    }

    public int getFuel() {
        return interactor.getFuel();
    }

    public void updateExistingPlayer() {
        playerInteractor.updateExistingPlayer();
    }
}
