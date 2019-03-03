package edu.gatech.cs2340.spacetrader.entity;

public class Planet {

    private Resources resource;
    private TechLevel techLevel;

    public Resources getResource() {
        return resource;
    }

    public void setResource(Resources resource) {
        this.resource = resource;
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }

    public void setTechLevel(TechLevel techLevel) {
        this.techLevel = techLevel;
    }
}
