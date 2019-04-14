package edu.gatech.cs2340.spacetrader.entity;

import android.support.annotation.NonNull;


public class Planet {
    private final String name;
    private final TechLevel techLevel;
    private final Resources resources;
    /**
     * creates the planet object
     * @param name the name of the planet
     * @param techLevel the tech Level of the planet
     * @param resources the resources of the planet
     */
    public Planet(String name, TechLevel techLevel, Resources resources) {
        this.name = name;
        this.techLevel = techLevel;
        this.resources = resources;
    }

    /**
     * the tostring method
     * @return the string representation of a planet
     */
    @Override
    @NonNull
    public String toString() {
        return name + " (techlevel: " + techLevel + ", resources: " + resources + ")";
    }

    public Resources getResource() {
        return resources;
    }

//    public void setResource(Resources resource) {
//        this.resources = resource;
//    }

    public TechLevel getTechLevel(){
        return techLevel;
    }

//    public void setTechLevel(TechLevel techLevel) {
//        this.techLevel = techLevel;
//    }

    public String getName() {
        return name;
    }
}
