package edu.gatech.cs2340.spacetrader.model;

import java.util.HashMap;
import java.util.Map;

public final class Model {

    /** the data repository */
    private final Repository myRepository;

    private final Map<String, Object> interactorMap;

    /** Singleton Pattern Code
     *  this allows us to get access to this class
     *  anywhere, which will allow our View models to access
     *  the "back end"  more easily
     */
    private static  final Model instance = new Model();

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        myRepository = new Repository();
        interactorMap = new HashMap<>();
        registerInteractors();
    }

    /** end Singleton Pattern */

    private void registerInteractors() {
        interactorMap.put("Player", new PlayerInteractor(myRepository));
        interactorMap.put("Market", new MarketInteractor(myRepository));
        interactorMap.put("Planet", new PlanetInteractor(myRepository));
        interactorMap.put("SolarSystem", new SolarSystemInteractor(myRepository));
    }

    public PlayerInteractor getPlayerInteractor() {
        return (PlayerInteractor) interactorMap.get("Player");
    }

    public MarketInteractor getMarketInteractor() {
        return (MarketInteractor) interactorMap.get("Market");
    }

    public PlanetInteractor getPlanetInteractor() {
        return (PlanetInteractor) interactorMap.get("Planet");
    }

    public SolarSystemInteractor getSolarSystemInteractor() {
        return (SolarSystemInteractor) interactorMap.get("SolarSystem");
    }
}
