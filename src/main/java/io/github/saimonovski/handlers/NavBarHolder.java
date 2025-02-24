package io.github.saimonovski.handlers;


import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

/**
 * Holder of navbar used in PaginationSystem
 * Handles clicking on buttons to go to next page or previous page
 * @see PaginationHandler
 */

public interface NavBarHolder {
    /**
     *
     * @return get slot where is placed <b>nextPageItem();</b>
     */
    int getNextPageSlot();

    /**
     * Method to get item which after change page to next
     * @return instance of PaginationHandler
     * @see PaginationHandler
     */

    PaginationHandler getPaginationHandler();

    /**
     * Method to get item which after change page to previous
     * @return return a slot where is placed <b>previousPageItem();</b>
     */
    int getPreviousPageSlot();

    /**
     *
     * @return returns an item to get nextPage after clicking on it
     */

    ItemStack nextPageItem();

    /**
     *
     * @return returns an item to get nextPage after clicking on
     */

    ItemStack previousPageItem();

    /**
     * should have action for going to next page and previous page using PaginationHandler
     * @see PaginationHandler
     * @param e event that has been called
     */
    void onClick(InventoryClickEvent e);

    /**
     * Method to decorate inventory with buttons
     * @param inventory inventory to decorate
     */

    void decorate(Inventory inventory);

    /**
     * Method to get slots that has been taken by buttons
     * @return taken slots
     */

    Collection<Integer> getOccupiedSlots();
}