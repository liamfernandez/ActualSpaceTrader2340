package edu.gatech.cs2340.spacetrader.model;

import java.util.Random;

import edu.gatech.cs2340.spacetrader.entity.Item;
import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.Resources;
import java.util.List;
import java.util.ArrayList;


public class Store {

    private static Item[] allItems = Item.values();

    /**
     * Utility class for getting the market
     * price of an item. Includes random variation.
     * Price is also adjusted according to the planet's resources.
     *
     * @param item Item price is desired for
     * @param planet Planet item is being sold on
     * @return Price for item, random within variance tolerance
     */
    public static double getMarketPrice(Item item, Planet planet) {

        double price = item.getBasePrice();

        Random random = new Random();

        double IPL = item.getIPL();
        double var = item.getVar();

        Resources IE = item.getIE();
        Resources CR = item.getCR();
        Resources ER = item.getER();

        price = price + IPL * planet.getTechLevel().getLevel();


        if (IE == planet.getResource()) {
            price *= 3.0;
        } else if (CR == planet.getResource()) {
            price *= 1.0/3.0;
        } else if (ER == planet.getResource()) {
            price *= 1.5;
        }

        double randomAdjustmentPercentage = random.nextDouble() * var * 2.0 / 100.00;
        randomAdjustmentPercentage = randomAdjustmentPercentage - var;

        return price * (1.0 + randomAdjustmentPercentage);

    }

    /**
     * A utility method to get a list of the
     * items that should be sold at that planet's
     * market
     * @param planet The planet that we're calculating the supply for
     * @return the items to be sold at that planet's market
     */
    public static List<Item> getMarketItems(Planet planet) {
        List<Item> marketSupply = new ArrayList<>();
        int techLevel = planet.getTechLevel().getLevel();
        for (Item x: allItems) {
            if (techLevel >= x.getMTLP()) {
                int amountToAdd = 4;
                if (techLevel == x.getTTP()) {
                    amountToAdd = 8; //if TTP is matched then double the amount to add
                }
                for (int i = 0; i < amountToAdd; i++) {
                    marketSupply.add(x);
                }
            }
        }
        return marketSupply;
    }

    /**
     * Utility class for getting the space trader
     * price for an item. Includes random variation
     *
     * @param item Item price is desired for
     * @return Price for the item, random within MTL and MTH limits
     */
    public static double getSpaceTradePrice(Item item) {

        double min = item.getMTL();
        double max = item.getMTH();

        double range = max - min;

        Random random = new Random();
        double priceAdded = random.nextDouble() * range;

        return min + range;
    }

}
