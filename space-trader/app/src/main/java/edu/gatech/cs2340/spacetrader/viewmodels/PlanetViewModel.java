package edu.gatech.cs2340.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.SolarSystem;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.PlanetInteractor;

public class PlanetViewModel extends AndroidViewModel {

    private PlanetInteractor interactor;

    public PlanetViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getSolarSystemInteractor();
    }

    public List<SolarSystem> getInRangeSolarSystems() {
        return interactor.getInRangeSolarSystems();
    }

    public void travelToSolarSystem(SolarSystem solarSystem) {
        interactor.travelToSolarSystem(solarSystem);
    }
}
