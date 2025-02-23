package io.github.saimonovski.objects;


import io.github.saimonovski.GuiManager;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;


public abstract class NavBarInstance implements NavBarHolder {


    @Override
    public void onClick(InventoryClickEvent e) {
        int slot = e.getRawSlot();
        if(slot == getNextPageSlot() && !getPaginationHandler().isLastPage()){
            e.setCancelled(true);
            getPaginationHandler().nextPage();
            e.getWhoClicked().closeInventory();
        } else if (slot == getPreviousPageSlot() && !getPaginationHandler().isFirstPage()) {
            e.setCancelled(true);
            getPaginationHandler().previousPage();
            e.getWhoClicked().closeInventory();
        }
        if(e.getWhoClicked() instanceof Player player){
            GuiManager.getInstance().openInventory(player, getPaginationHandler());
        }
    }

    @Override
    public void decorate(Inventory inventory) {
        if(!getPaginationHandler().isLastPage()) {
            inventory.setItem(getNextPageSlot(), nextPageItem());
        }
        if(!getPaginationHandler().isFirstPage()) {
            inventory.setItem(getPreviousPageSlot(), previousPageItem());
        }
    }

}
