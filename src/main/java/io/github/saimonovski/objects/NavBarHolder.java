package io.github.saimonovski.objects;


import io.github.saimonovski.handlers.PaginationHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;


public interface NavBarHolder {
    int getNextPageSlot();

    PaginationHandler getPaginationHandler();

    int getPreviousPageSlot();

    ItemStack nextPageItem();

    ItemStack previousPageItem();

    void onClick(InventoryClickEvent e);

    void decorate(Inventory inventory);

    Collection<Integer> getOccupiedSlots();
}