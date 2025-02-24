package io.github.saimonovski.objects;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


import java.util.UUID;
import java.util.function.Consumer;

/**
 * This is button which can be placed in inventory with an ItemStack icon and Consumer the action
 */
@SuppressWarnings("unused")
public class InventoryButton {
    private  Consumer<InventoryClickEvent> consumer;
    private ItemStack icon;
    private final UUID identifier = UUID.randomUUID();
    protected InventoryButton(Consumer<InventoryClickEvent> consumer, ItemStack icon) {
        this.consumer = consumer;
        this.icon = icon;

    }

    /**
     * Sets an action of this item which will be called after clicked on this item
     * @param consumer action to run after click
     * @return this
     */
    public InventoryButton setConsumer(Consumer<InventoryClickEvent> consumer) {
        this.consumer = consumer;
        return this;
    }

    /**
     * Sets an icon which will be placed in the inventory.
     * @param icon itemstack to set
     * @return this
     */

    public InventoryButton setIcon(ItemStack icon) {
        this.icon = icon;
        return this;
    }

    /**
     * Method to create object of this class
     * @param icon itemstack which will  represents a button in inventory
     * @param consumer action to take after clicking
     * @return object of this class
     */
    public static InventoryButton of(ItemStack icon, Consumer<InventoryClickEvent> consumer){
        return new InventoryButton(consumer,icon);
    }

    /**
     * Creates a object of this class but button will not contains action
     * @param icon item to set
     * @return Object of this class
     */
    public static InventoryButton empty(ItemStack icon){
        return of(icon, e -> {});
    }

    /**
     * Method to handle click, this method cancelling the click because item cannot be taken and run the action of button
     * @param event event
     */
    public void handleClick(InventoryClickEvent event){
        event.setCancelled(true);
        consumer.accept(event);
    }

    /**
     * Method to set item in inventory
     * @param slot slot to set
     * @param inventory inventory where item should be placed
     */
    public void setItem(int slot,Inventory inventory){
        inventory.setItem(slot, icon);
    }


}
