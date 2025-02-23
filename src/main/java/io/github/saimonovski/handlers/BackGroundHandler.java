package io.github.saimonovski.handlers;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public interface BackGroundHandler {
    void decorate(Inventory inventory);
    Map<Integer, ItemStack> getBackGroundItems();
    void addItem(int slot, ItemStack itemStack);
    default void handleClick(InventoryClickEvent event){
        if (getBackGroundItems().containsKey(event.getSlot())){
            event.setCancelled(true);
        }
    }
}
