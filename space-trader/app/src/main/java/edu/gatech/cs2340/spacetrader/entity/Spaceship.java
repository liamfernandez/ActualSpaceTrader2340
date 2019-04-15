package edu.gatech.cs2340.spacetrader.entity;

/**
 * Defines a user's spaceship
 */
public enum Spaceship {
    Gnat;

    @Override
    public String toString() {
        return this.name() + " spaceship";
    }
}
