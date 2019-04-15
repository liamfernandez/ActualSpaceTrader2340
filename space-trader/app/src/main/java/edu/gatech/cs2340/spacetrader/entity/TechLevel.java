package edu.gatech.cs2340.spacetrader.entity;

/**
 * defines a TechLevel
 */
public enum TechLevel {
    PreAgriculture(0), Agriculture(1), Medieval(2), Renaissance(3), Early_Industrial(4),
    Industrial(5), Post_Industrial(6),
    Hi_Tech(7);

    private final int level;

    /**
     * creates the tech level
     * @param level the level of the technology
     */
    TechLevel(int level) {
        this.level = level;
    }

    /**
     * gets the level of the technology
     * @return the number associated with the technology
     */
    public int getLevel() {
        return level;
    }

//    public void setLevel(int level) {
//        this.level = level;
//    }
}
