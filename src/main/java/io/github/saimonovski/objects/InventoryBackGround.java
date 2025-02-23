package io.github.saimonovski.objects;

import io.github.saimonovski.handlers.BackGroundHandler;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public  class InventoryBackGround implements BackGroundHandler {
    private final Map<Integer, ItemStack> background = new HashMap<>();


    @Override
    public void decorate(Inventory inventory) {
        background.forEach(inventory::setItem);
    }

    @Override
    public Map<Integer, ItemStack> getBackGroundItems() {
        return background;
    }

    @Override
    public void addItem(int slot, ItemStack itemStack) {
            background.put(slot,itemStack);
    }

    public Collection<Integer> getOccupiedSlots() {
        return background.keySet();
    }
}
