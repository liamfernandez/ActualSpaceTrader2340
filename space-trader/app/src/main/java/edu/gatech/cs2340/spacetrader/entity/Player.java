package edu.gatech.cs2340.spacetrader.entity;

public class Player extends Character{
    private int id;
    private double credit;
    private int dificulty;
    private Spaceship spaceship;
    private Inventory inventory;
    private Planet currPlanet;
    private SolarSystem currSolarSystem;
    private final int maxItems = 2;
    private int cargoTotal = 0;
    private double fuel = 100000;

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

    public Planet getCurrPlanet() {
        return currPlanet;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getCargoTotal() {
        return cargoTotal;
    }

    public int getMaxItems() {
        return maxItems;
    }

    public SolarSystem getCurrSolarSystem() {
        return currSolarSystem;
    }

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

    /**
     * adds an item to the user inventory
     * @param item the item to add to inventory
     */
    public void addItem(MockItem item) {
        inventory.add(item);
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double subtract) {
        this.fuel = fuel - subtract;
    }
}
