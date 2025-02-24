package liteInvs.api.handlers;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

/**
 * Handler of background
 */
@SuppressWarnings("unused")
public interface BackGroundHandler {
    /**
     * Method to decorate the inventory with background items
     * @param inventory inventory to decorate
     */
    void decorate(Inventory inventory);

    /**
     * method to get background items
     * @return map of items where int represent a slot where item is stored
     */
    Map<Integer, ItemStack> getBackGroundItems();

    /**
     * Method to add item to a background
     * @param slot slot to add
     * @param itemStack itemstack to add
     */
    void addItem(int slot, ItemStack itemStack);

    /**
     * Method for handling clicking in this inventory, should block taking items out from an inventory.
     * @param event event that has been called
     */
    default void handleClick(InventoryClickEvent event){
        if (getBackGroundItems().containsKey(event.getSlot())){
            event.setCancelled(true);
        }
    }
}
