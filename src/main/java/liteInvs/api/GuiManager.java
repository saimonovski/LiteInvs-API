package liteInvs.api;

import liteInvs.api.handlers.InventoryHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Saimonovski
 *  A class to manage opened inventories and their holders, this is singleton so creating instance is only by
 * <code>GuiManager.getInstance()</code>
 */
public class GuiManager {
    /**
     * singleton instance of this manager, always represent only one instance of this class
     */
    private static GuiManager manager;
    private final JavaPlugin plugin;
    /**
     * A method to get instance of manager (singleton)
     * @return a instance of gui manager
     * <b> Before first time using the manager you have to register it in your main class by </b>
     *<b> <code>GuiManager.register(JavaPlugin javaPlugin); method</code> </b>
     */
    public static  GuiManager getInstance(){
    if(manager == null){throw new RuntimeException("Manager is not specified, look to the documentation");
    }
    return manager;
    }

    /**
     * A method uses to register manager with all listeners
     * @param javaPlugin plugin to register in it
     */
    public static void register(JavaPlugin javaPlugin){
        manager = new GuiManager(javaPlugin);
new GuiListener(manager,javaPlugin);
    }

    /**
     * The constructor is private because can exist only one instance of this class
     */
    private GuiManager(JavaPlugin plugin){
        this.plugin = plugin;
    }
    private final Map<UUID, InventoryHandler> registeredMembers = new HashMap<>();


    /**
     *  If you don't wanna more use your inventory to any of custom holders you can just unregister it here
     * @param player player who is signed to this inventory
     */
    public void unregisterInventory(Player player)
    {
        this.registeredMembers.remove(player.getUniqueId());
    }

    /**
     * This method will automatically open inventory and register it to the activeInventories
     * @param player player to open inventory
     * @param inventoryHandler handler to register inventory and getInventory from
     */
    public void openInventory(Player player, InventoryHandler inventoryHandler){
        Bukkit.getScheduler().runTask(plugin,() -> {
            player.openInventory(inventoryHandler.getInventory());
            registeredMembers.put(player.getUniqueId(), inventoryHandler);
        });
    }

    /**
     * This method is for handle clicking, will find <b>InventoryHandler</b> and next make on the <b>onClick()</b>
     * method
     * @param event event to handle this method
     */
    public void handleClick(InventoryClickEvent event){
        InventoryHandler handler = registeredMembers.get(event.getWhoClicked().getUniqueId());
        if(handler == null) return;

        handler.onClick(event);
    }
    /**
     * This method is for handle opening inventory, will find <b>InventoryHandler</b> and next make on the <b>onOpen()</b>
     * method
     * @param event event to handle this method
     */
    public void handleOpen(InventoryOpenEvent event){
        InventoryHandler handler = registeredMembers.get(event.getPlayer().getUniqueId());
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
        InventoryHandler handler = registeredMembers.get(event.getPlayer().getUniqueId());
        if(handler == null) return;
        this.unregisterInventory((Player) event.getPlayer());
        handler.onClose(event);
    }



}
