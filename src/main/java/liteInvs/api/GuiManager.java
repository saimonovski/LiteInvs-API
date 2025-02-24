package liteInvs.api;

import liteInvs.api.handlers.InventoryHandler;
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
    /**
     * singleton instance of this manager, always represent only one instance of this class
     */
    private static GuiManager manager;

    /**
     * A method to get instance of manager (singleton)
     * @return a instance of gui manager
     */
    public static  GuiManager getInstance(){
    if(manager == null){new GuiManager();
    }
    return manager;
    }

    /**
     * The constructor is private because can exist only one instance of this class
     */
    private GuiManager(){}
    private final Map<Inventory, InventoryHandler> activeInventories = new HashMap<>();

    /**
     * A method to register your inventory to a manager, recommended to use is<b> <code>openInventory(Player
     * player,
     * InventoryHandler inventoryHandler) </code> </b> because that method will automatically open and register your
     * inventory
     * @param inventory wchich should be register
     * @param handler for it will be signed inventory object
     */
    public void registerInventory(Inventory inventory, InventoryHandler handler){
        this.activeInventories.put(inventory, handler);
    }

    /**
     *  If you don't wanna more use your inventory to any of custom holders you can just unregister it here
     * @param inventory inventory to unregister
     */
    public void unregisterInventory(Inventory inventory){
        this.activeInventories.remove(inventory);
    }

    /**
     * This method will automatically open inventory and register it to the activeInventories
     * @param player player to open inventory
     * @param inventoryHandler handler to register inventory and getInventory from
     */
    public void openInventory(Player player, InventoryHandler inventoryHandler){
        this.activeInventories.put(inventoryHandler.getInventory(), inventoryHandler);
        player.openInventory(inventoryHandler.getInventory());
    }

    /**
     * This method is for handle clicking, will find <b>InventoryHandler</b> and next make on the <b>onClick()</b>
     * method
     * @param event event to handle this method
     */
    public void handleClick(InventoryClickEvent event){
        InventoryHandler handler = activeInventories.get(event.getInventory());
        if(handler == null) return;

        handler.onClick(event);
    }
    /**
     * This method is for handle opening inventory, will find <b>InventoryHandler</b> and next make on the <b>onOpen()</b>
     * method
     * @param event event to handle this method
     */
    public void handleOpen(InventoryOpenEvent event){
        InventoryHandler handler = activeInventories.get(event.getInventory());
        if(handler == null) return;

        handler.onOpen(event);
    }
    /**
     * This method is for handle closing, will find <b>InventoryHandler</b> and next make on the <b>onClose()</b>
     * method
     * @param event event to handle this method
     */
    public void handleClose(InventoryCloseEvent event){
        Inventory inventory = event.getInventory();
        InventoryHandler handler = activeInventories.get(inventory);
        if(handler == null) return;
        this.unregisterInventory(inventory);
        handler.onClose(event);
    }



}
