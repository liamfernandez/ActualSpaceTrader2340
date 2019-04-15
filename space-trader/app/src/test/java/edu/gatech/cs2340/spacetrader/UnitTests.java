package edu.gatech.cs2340.spacetrader;

import android.app.Application;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

import edu.gatech.cs2340.spacetrader.entity.Item;
import edu.gatech.cs2340.spacetrader.entity.MockItem;
import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.entity.SolarSystem;
import edu.gatech.cs2340.spacetrader.entity.Universe;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.MarketInteractor;
import edu.gatech.cs2340.spacetrader.model.MySQLTalker;
import edu.gatech.cs2340.spacetrader.model.Repository;
import edu.gatech.cs2340.spacetrader.viewmodels.SignInViewModel;

public class UnitTests {

    Repository repository;
    SignInViewModel s;

    @Before
    public void setUp() {
        repository = Model.getInstance().getMarketInteractor().getRepository();
        s = new SignInViewModel(new Application());
    }

    @Test
    public void testValidatePlayer() {
        Player tooFewSkills = new Player("henry", 0, 0, 0, 0);
        Player tooManySkills = new Player("henry", 16, 16, 16, 16);
        Player justRightSkills = new Player("henry", 16, 0, 0, 0);
        Player justRightSkills2 = new Player("henry", 0, 16, 0, 0);
        Player justRightSkills3 = new Player("henry", 16, 0, 16, 0);
        Player justRightSkills4 = new Player("henry", 16, 0, 0, 16);
        Player justRightSum = new Player("henry:,", 4, 4, 4, 4);
        assertEquals("not checking if it is too few than 16", false, s.validateTest(tooFewSkills));
        assertEquals("not checking if it is more than 16", false, s.validateTest(tooManySkills));
        assertEquals("it should be okay if only one is 16 and the rest are 0", true, s.validateTest(justRightSkills));
        assertEquals("it should be okay if only one is 16 and the rest are 0", true, s.validateTest(justRightSkills2));
        assertEquals("it should be okay if only one is 16 and the rest are 0", true, s.validateTest(justRightSkills3));
        assertEquals("it should be okay if only one is 16 and the rest are 0", true, s.validateTest(justRightSkills4));
        assertEquals("it should be okay if all add up to 16", true, s.validateTest(justRightSum));
    }

    @Test
    public void testDownloadPlayer() {
        String[] existingPlayers = {"MATT", "p", "porter", "qwe"};
        String[] nonexistantPlayers = {"noPlayer1", "noPlayer2", "noPlayer3", "noPlayer4"};

        boolean exists = true;
        try {
            repository.downloadPlayer(nonexistantPlayers[0]);
        } catch (Repository.PlayerNotFoundException exception) {
            assertEquals(existingPlayers[0] + " does not exist!", exception.getMessage());
            exists = false;
        }
        assertFalse(exists);

        Player p = new Player(" ",16,0,0,0);
        Player dummy;

        try {
            p = repository.downloadPlayer(existingPlayers[0]);
            assertEquals(p.getName(), existingPlayers[0]);
            exists = true;
        } catch (Repository.PlayerNotFoundException exception) {
            //assertEquals(existingPlayers[0] + " does not exist!", exception.getMessage());
        }
        assertTrue(exists);

        assertEquals(p.getCurrPlanet().getName(), "Ernie");
        assertEquals((int) p.getCredit(), 662);
        assertEquals(p.getSkill1(), 16);
        assertEquals(p.getSkill2(), 0);
        assertEquals(p.getSkill3(), 0);
        assertEquals(p.getSkill4(), 0);
        assertTrue(p.getInventory().contains(Item.FURS));
        assertTrue(p.getInventory().contains(Item.WATER));
        assertEquals((int) p.getFuel(), 99991);
        assertEquals(p.getCurrSolarSystem().getName(), "The Street");

    }

    @Test
    public void testBuyMockItem() {
        List<MockItem> tooMany = new ArrayList<>();
        Item[] f = Item.values();
        Player defaultPlayer = new Player("TestGuy", 0, 0, 0, 16);
        Repository spaceInCargoAndProperMoney = new Repository(defaultPlayer, new ArrayList<>());
        MockItem testItemToBuy = new MockItem(f[0], 100, 0);
        for (int i = 0; i <= 11; i++) {
            MockItem toAdd = new MockItem(f[i % 10], 0, 0);
            tooMany.add(toAdd);
        }
        Repository tooMuchInCargoToBuy = new Repository(defaultPlayer, tooMany);
        MarketInteractor testOfTooMuchCargo = new MarketInteractor(tooMuchInCargoToBuy);
        MarketInteractor testOfEnoughCargo = new MarketInteractor(spaceInCargoAndProperMoney);
        assertEquals("Should be false because cargo is at capacity and can't add more", false, testOfTooMuchCargo.buyMockItem(testItemToBuy, 1));
        assertEquals("Should be true because Cargo has enough space", true, testOfEnoughCargo.buyMockItem(testItemToBuy, 1));
        defaultPlayer.setCredit(0);
        assertEquals("Should be false because player does not have enough money to buy the item", false, testOfEnoughCargo.buyMockItem(testItemToBuy, 1));
        defaultPlayer.setCredit(10000);
        assertEquals("Should be true because Player has enough money and cargo space", true, testOfEnoughCargo.buyMockItem(testItemToBuy, 1));
    }

    @Test
    public void testGetInRangeSolarSystems() {
        //test after universe is created
        Universe universe = new Universe();

        Map<Double, SolarSystem> inRangeSystemsMap = new HashMap<>();
        List<SolarSystem> inRangeSolarSystems = new ArrayList<>();
        List<SolarSystem> expectedInRangeSolarSystems = new ArrayList<>();
        List<SolarSystem> allSolarSystems = universe.getAllSystems();

        //check when can't visit any other solar System
        inRangeSystemsMap = universe.getSystemsInRange(universe.getStartingSolarSystem(), 0);
        for (Double key : inRangeSystemsMap.keySet()) {
            inRangeSolarSystems.add(inRangeSystemsMap.get(key));
        }
        expectedInRangeSolarSystems.add(universe.getStartingSolarSystem());
        assertEquals("message", expectedInRangeSolarSystems, inRangeSolarSystems);

        //check when can only visit the nearest solar system
        inRangeSolarSystems.clear();
        inRangeSystemsMap = universe.getSystemsInRange(universe.getStartingSolarSystem(), 12);
        for (Double key : inRangeSystemsMap.keySet()) {
            inRangeSolarSystems.add(inRangeSystemsMap.get(key));
        }
        expectedInRangeSolarSystems.add(allSolarSystems.get(1));
        assertEquals("message", expectedInRangeSolarSystems, inRangeSolarSystems);

        //check when can visit all solar systems
        inRangeSolarSystems.clear();
        inRangeSystemsMap = universe.getSystemsInRange(universe.getStartingSolarSystem(), 1000000);
        for (Double key : inRangeSystemsMap.keySet()) {
            inRangeSolarSystems.add(inRangeSystemsMap.get(key));
        }
        expectedInRangeSolarSystems = allSolarSystems;
        assertEquals("message", expectedInRangeSolarSystems, inRangeSolarSystems);
    }

}
