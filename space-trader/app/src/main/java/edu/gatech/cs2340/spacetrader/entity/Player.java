package edu.gatech.cs2340.spacetrader.entity;

/**
 * @author squad
 * @version 0.01
 */
public class Player extends Character{
    private int id;
    private double credit;
    private int dificulty;
    private Spaceship spaceship;
    private Inventory inventory;
    private Planet currPlanet;
    private SolarSystem currSolarSystem;
    private final int maxItems = 10;
    private int cargoTotal = 0;
    private double fuel = 100000;
    private boolean hasMercenary;

    /**
     * Constructor
     * @param n the name
     * @param s1 skill 1 value
     * @param s2 skill 2 value
     * @param s3 skill 3 value
     * @param s4 skill 4 value
     */
    public Player(String n, int s1, int s2, int s3, int s4) {
        super(n,s1, s2, s3, s4);
        name = n;
        skill1 = s1;
        skill2 = s2;
        skill3 = s3;
        skill4 = s4;
        credit = 1000;
        spaceship = Spaceship.Gnat;
        inventory = new Inventory();
        //solarSystem = new SolarSystem(null, 0, 0,"initial");
    }

    /**
     * to string
     * @return the string
     */
    @Override
    public String toString() {
        return name + " has " + skill1 + " points in skill 1, " + skill2
                + " points in skill 2, " + skill3 + " points in skill 3, "
                + skill4 + " points in skill4 with a " + spaceship;
    }

    /**
     * sets the ID for the player
     * @param id the id that will be set
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Setter
     * @param name what to set it to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets the player's credit
     * @param credit the credit to set
     */
    public void setCredit(double credit) {
        this.credit = credit;
    }

    /**
     * sets the player's fuel
     * @param fuel the fuel to set
     */
    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    /**
     * Set skill 1
     * @param skill1 the skill
     */
    public void setSkill1(int skill1) {
        this.skill1 = skill1;
    }

    /**
     * Set skill 2
     * @param skill2 skill 2 to set it to
     */
    public void setSkill2(int skill2) {
        this.skill2 = skill2;
    }

    /**
     * setter for skill 3
     * @param skill3 the skill
     */
    public void setSkill3(int skill3) {
        this.skill3 = skill3;
    }

    /**
     * Setter for skill 4
     * @param skill4 the skill
     */
    public void setSkill4(int skill4) {
        this.skill4 = skill4;
    }

    /**
     * getter for name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * the get skill
     * @return skill 1
     */
    public int getSkill1() {
        return skill1;
    }

    /**
     * skill 2 getter
     * @return skill 2
     */
    public int getSkill2() {
        return skill2;
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
     * @return skill 4
     */
    public int getSkill4() {
        return skill4;
    }

    /**
     *
     * @return the curr planet
     */
    public Planet getCurrPlanet() {
        return currPlanet;
    }

    /**
     *
     * @return the inventory object of the player
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * setter for inventory
     * @param inventory the inventory
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     *
     * @return cargo total
     */
    public int getCargoTotal() {
        return cargoTotal;
    }

    /**
     *
     * @return the max items
     */
    public int getMaxItems() {
        return maxItems;
    }

    /**
     *
     * @return the current solar system
     */
    public SolarSystem getCurrSolarSystem() {
        return currSolarSystem;
    }

    /**
     *
     * @param solarSystem set the system
     */
    public void setCurrSolarSystem(SolarSystem solarSystem) {
        this.currSolarSystem = solarSystem;
    }

    /**
     * Edits the value of the player's credit
     *
     * @param moneyToAdd Will add this amount to credit
     * @return The player's credit after edit
     */
    public double editCredit(double moneyToAdd) {
        credit = credit + moneyToAdd;
        return credit;
    }

    /**
     *
     * @return Player's credit
     */
    public double getCredit() {
        return credit;
    }

    /**
     * sets the current planet of the Player
     * @param newCurrPlanet the new planet that the player is on
     */
    public void setCurrPlanet(Planet newCurrPlanet) {
        currPlanet = newCurrPlanet;
    }
<<<<<<< HEAD
//
=======

>>>>>>> Dev
//    /**
//     * adds an item to the user inventory
//     * @param item the item to add to inventory
//     */
//    public void addItem(MockItem item) {
//        inventory.add(item);
//    }

    /**
     * Getter for Fuel
     * @return the amount of Fuel
     */
    public double getFuel() {
        return fuel;
    }

    /**
     * Subtracts fuel when traveling
     * @param subtract the amount to subtract
     */
    public void subtractFuel(double subtract) {
        this.fuel = fuel - subtract;
    }

    public void setHasMercenary(boolean bool) {
        hasMercenary = bool;
    }
}
