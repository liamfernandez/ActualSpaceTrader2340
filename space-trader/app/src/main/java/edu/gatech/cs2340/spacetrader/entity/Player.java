package edu.gatech.cs2340.spacetrader.entity;

public class Player {
    private int id;
    private int credit;
    private String name;
    private int dificulty;
    private int skill1;
    private int skill2;
    private int skill3;
    private int skill4;

    public Player(String n, int s1, int s2, int s3, int s4) {
        name = n;
        skill1 = s1;
        skill2 = s2;
        skill3 = s3;
        skill4 = s4;
        credit = 1000;
    }

    @Override
    public String toString() {
        return name + "has " + skill1 + " points in skill 1, " + skill2
                + " points in skill 2, " + skill3 + " points in skill 3, "
                + skill4 + " points in skill4";
    }

    /**
     * sets the ID for the player
     * @param id the id that will be set
     */
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSkill1(int skill1) {
        this.skill1 = skill1;
    }

    public void setSkill2(int skill2) {
        this.skill2 = skill2;
    }

    public void setSkill3(int skill3) {
        this.skill3 = skill3;
    }

    public void setSkill4(int skill4) {
        this.skill4 = skill4;
    }

    public String getName() {
        return name;
    }

    public int getSkill1() {
        return skill1;
    }

    public int getSkill2() {
        return skill2;
    }

    public int getSkill3() {
        return skill3;
    }

    public int getSkill4() {
        return skill4;
    }
}
