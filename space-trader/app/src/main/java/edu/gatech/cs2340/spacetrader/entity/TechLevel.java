package edu.gatech.cs2340.spacetrader.entity;

public enum TechLevel {
    PreAgriculture(0), Agriculture(1), Medieval(2), Renaissance(3), Early_Industrial(4), Industrial(5), Post_Industrial(6),
    Hi_Tech(7);

    private int level;

    TechLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
