package edu.gatech.cs2340.spacetrader.entity;

/**
 *
 */
public abstract class Character {
    String name;
//    int age;
//    boolean evil;
    int skill1;
    int skill2;
    int skill3;
    int skill4;

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name name to set
     */
    public void setName(String name) {
        this.name = name;
    }

//    public boolean isEvil() {
//        return evil;
//    }
//
//    public void setEvil(boolean evil) {
//        this.evil = evil;
//    }

    /**
     *
     * @return the skill1
     */
    public int getSkill1() {
        return skill1;
    }

    /**
     *
     * @param skill1 skill 1
     */
    public void setSkill1(int skill1) {
        this.skill1 = skill1;
    }

    /**
     *
     * @return skill 2
     */
    public int getSkill2() {
        return skill2;
    }

    /**
     *
     * @param skill2 skill 2
     */
    public void setSkill2(int skill2) {
        this.skill2 = skill2;
    }

    /**
     *
     * @return skill 3
     */
    public int getSkill3() {
        return skill3;
    }

    /**
     *
     * @param skill3 skill 3
     */
    public void setSkill3(int skill3) {
        this.skill3 = skill3;
    }

    /**
     *
     * @return skill 4
     */
    public int getSkill4() {
        return skill4;
    }

    /**
     *
     * @param skill4 skill 4
     */
    public void setSkill4(int skill4) {
        this.skill4 = skill4;
    }

    /**
     * Constructor for character
     * @param name the name
     * @param skill1 skill 1
     * @param skill2 skill 2
     * @param skill3 skill 3
     * @param skill4 skill 4
     */
    protected Character(String name, int skill1, int skill2, int skill3, int skill4) {
        this.name = name;
        //this.evil = evil;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
        this.skill4 = skill4;
    }
}
