package edu.gatech.cs2340.spacetrader.model;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Inventory;
import edu.gatech.cs2340.spacetrader.entity.Item;
import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.entity.Universe;

public class PlayerInteractor extends Interactor {

    public PlayerInteractor(Repository repo) {
        super(repo);
    }

    public Player getPlayer() {
        return getRepository().getPlayer();
    }

    public void addPlayerToBackEnd (Player p) {
        getRepository().addPlayer(p);
    }

    public void createUniverse() {
        Universe newUniverse = new Universe();
        getRepository().addUniverse(newUniverse);
        getRepository().addSeller();
    }

    /**
     * Method to make a player sell and item. Will first check
     * if they're able to, and if so, execute the sale by
     * 1) removing item(s) from players inventory
     * 2) adding the value of them to player's price
     *
     * Players are unable to sell if MLTP of item isn't good
     * or if they have insufficient quantity
     *
     * @param player Player selling the item
     * @param item Item to be sold
     * @param quantity Quantity of Item to be sold
     * @param planet Planet item being sold on
     * @return true if sale executed, false if sale invalid
     */

    public boolean sellItem(Player player, Item item, int quantity, Planet planet) {
        if (planet != null && planet.getTechLevel().getLevel() < item.getMTLP()) {
            return false;
        }
        Inventory inventory = player.getInventory();
        if (!inventory.contains(item)) {
            return false;
        } else if (inventory.getQuantity(item) < quantity) {
            return false;
        } else {
            inventory.remove(item, quantity);
            if (planet == null) {
                player.editCredit(Store.getSpaceTradePrice(item));
                return true;
            } else {
                player.editCredit(Store.getMarketPrice(item, planet) * quantity);
                return true;
            }
        }
    }


    /**
     * Overloaded method for selling to a spacetrader
     *
     * Will not check MTLP of item, and will price according to
     * space prices, not planet prices
     *
     * See other sellItem method for more details
     *
     * @param player Player selling the item
     * @param item Item to be sold
     * @param quantity Quantity of Item to be sold
     */
    public boolean sellItem(Player player, Item item, int quantity) {
        return sellItem(player, item, quantity, null);
    }

    /**
     * Method to make player purchase an item.
     * Will confirm the player has sufficient funds, and
     * the seller actually has enough in stock. Purchase executed by:
     * 1) Removing item(s) from seller
     * 2) Adding item(s) to player inventory
     * 3) Adjusting player credit accordingly
     *
     * If seller has insufficient stock, sale will not
     * go through.
     *
     * @param player Player purchasing item
     * @param item Item they are purchasing
     * @param seller Seller of the item
     */
    public boolean purchaseItem(Player player, Item item, int quantity, DisplayableSeller seller) {
        if (seller.getQuantityForSale(item) >= quantity
                && seller.getPrice(item) * quantity <= player.getCredit()) {
            for (int i = 0; i < quantity; i++) {
                seller.remove(item);
            }
            player.getInventory().add(item, quantity);
            player.editCredit(-1 * seller.getPrice(item) * quantity);
            return true;
        } else {
            return false;
        }
    }
}
