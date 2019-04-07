package edu.gatech.cs2340.spacetrader;

import android.app.Application;

import org.junit.Test;

import static org.junit.Assert.*;

import edu.gatech.cs2340.spacetrader.entity.Player;
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
}
