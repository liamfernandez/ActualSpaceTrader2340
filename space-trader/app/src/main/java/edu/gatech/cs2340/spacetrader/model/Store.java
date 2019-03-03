package edu.gatech.cs2340.spacetrader.model;

import java.util.Random;

import edu.gatech.cs2340.spacetrader.entity.Item;
import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.Resources;

public class Store {

    private static Item[] allItems = {};

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
