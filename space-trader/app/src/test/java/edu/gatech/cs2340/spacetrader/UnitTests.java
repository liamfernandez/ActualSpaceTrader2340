package edu.gatech.cs2340.spacetrader;

import android.app.Application;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import edu.gatech.cs2340.spacetrader.entity.Item;
import edu.gatech.cs2340.spacetrader.entity.MockItem;
import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.model.MarketInteractor;
import edu.gatech.cs2340.spacetrader.model.Repository;
import edu.gatech.cs2340.spacetrader.viewmodels.SignInViewModel;

public class UnitTests {
    @Test
    public void testValidatePlayer() {
        SignInViewModel s = new SignInViewModel(new Application());
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
    public void testBuyMockItem() {
        List<MockItem> tooMany = new ArrayList<>();
        Item[] f = Item.values();
        Player defaultPlayer = new Player("TestGuy", 0, 0, 0, 16);
        Repository spaceInCargoAndProperMoney = new Repository(defaultPlayer, tooMany);
        MockItem testItemToBuy = new MockItem(f[0], 100, 0);
        for (int i = 0; i <= 11; i++) {
            MockItem toAdd = new MockItem(f[i % 10], 0, 0);
            tooMany.add(toAdd);
        }
        Repository tooMuchInCargoToBuy = new Repository(defaultPlayer, tooMany);
        MarketInteractor testOfTooMuchCargo = new MarketInteractor(tooMuchInCargoToBuy);
        MarketInteractor testOfEnoughCargo = new MarketInteractor(spaceInCargoAndProperMoney);
        assertEquals("Should be false because cargo is at capacity and can't add more", false, testOfTooMuchCargo.buyMockItem(testItemToBuy));
        assertEquals("Should be true because Cargo has enough space", true, testOfEnoughCargo.buyMockItem(testItemToBuy));
        defaultPlayer.setCredit(0);
        assertEquals("Should be false because player does not have enough money to buy the item", false, testOfEnoughCargo.buyMockItem(testItemToBuy));
        defaultPlayer.setCredit(10000);
        assertEquals("Should be true because Player has enough money and cargo space", true, testOfEnoughCargo.buyMockItem(testItemToBuy));
    }
}
