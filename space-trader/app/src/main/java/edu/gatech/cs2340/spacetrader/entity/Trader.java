package edu.gatech.cs2340.spacetrader.entity;

/**
 * defines what a trader is
 */
public class Trader extends Character {
    /**
     * Creates a trader
     * @param name the name of trader
     * @param skill1 the skill set
     * @param skill2 the skill set
     * @param skill3 the skill set
     * @param skill4 the skill set
     */
    public Trader(String name, int skill1, int skill2, int skill3, int skill4) {
        super(name, skill1, skill2, skill3, skill4);
    }
}
