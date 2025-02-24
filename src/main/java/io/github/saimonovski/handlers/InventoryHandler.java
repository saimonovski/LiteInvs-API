package io.github.saimonovski.handlers;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

/**
 * Handler for an inventory,
 * handling clicking on inventory, opening inventory, and closing inventory
 * also getting inventory
 */

public interface InventoryHandler {
    /**
     * Called when user clicks an inventory
     * @param e event called by this inventory
     */
    void onClick(InventoryClickEvent e);

    /**
     * Called when user opens Inventory
     * @param e event called by this inventory
     */
    void onOpen(InventoryOpenEvent e);

    /**
     * Called when user closing inventory
     * @param e event called by this inventory
     */
    void onClose(InventoryCloseEvent e);

    /**
     *
     * @return inventory instance of this handler
     */
    Inventory getInventory();
}
