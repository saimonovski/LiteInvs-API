package io.github.saimonovski;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class GuiListener implements Listener {
    private final GuiManager manager;

    public GuiListener(GuiManager manager, JavaPlugin plugin) {
        this.manager = manager;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onClick(InventoryClickEvent e){
        this.manager.handleClick(e);
    }
    @EventHandler
    public void onOpen(InventoryOpenEvent e){
        this.manager.handleOpen(e);
    }
    @EventHandler
    public void onClick(InventoryCloseEvent e){
        this.manager.handleClose(e);
    }
}
