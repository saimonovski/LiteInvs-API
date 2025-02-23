package io.github.saimonovski;

import io.github.saimonovski.handlers.InventoryHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * A class to manage opened inventories and their holders, this is singleton so creating instance is only by
 * <code>GuiManager.getInstance()</code>
 */
public class GuiManager {
    private static GuiManager manager;

    /**
     * A method to get instance of manager (singleton)
     * @return a instance of gui manager 
     */
    public static  GuiManager getInstance(){
        return manager != null ? manager : new GuiManager();
    }
    private GuiManager(){}
    private final Map<Inventory, InventoryHandler> activeInventories = new HashMap<>();
    public void registerInventory(Inventory inventory, InventoryHandler handler){
        this.activeInventories.put(inventory, handler);
    }
    public void unregisterInventory(Inventory inventory){
        this.activeInventories.remove(inventory);
    }
    public void openInventory(Player player, InventoryHandler inventoryHandler){
        this.activeInventories.put(inventoryHandler.getInventory(), inventoryHandler);
        player.openInventory(inventoryHandler.getInventory());
    }
    public void handleClick(InventoryClickEvent event){
        InventoryHandler handler = activeInventories.get(event.getInventory());
        if(handler == null) return;

        handler.onClick(event);
    }
    public void handleOpen(InventoryOpenEvent event){
        InventoryHandler handler = activeInventories.get(event.getInventory());
        if(handler == null) return;

        handler.onOpen(event);
    }
    public void handleClose(InventoryCloseEvent event){
        Inventory inventory = event.getInventory();
        InventoryHandler handler = activeInventories.get(inventory);
        if(handler == null) return;
        this.unregisterInventory(inventory);
        handler.onClose(event);
    }



}
