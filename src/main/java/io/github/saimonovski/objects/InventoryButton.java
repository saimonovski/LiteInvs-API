package io.github.saimonovski.objects;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


import java.util.UUID;
import java.util.function.Consumer;

public class InventoryButton {
    private  Consumer<InventoryClickEvent> consumer;
    private ItemStack icon;
    private final UUID identifier;

    protected InventoryButton(Consumer<InventoryClickEvent> consumer, ItemStack icon) {
        this.consumer = consumer;
        this.icon = icon;
        this.identifier = UUID.randomUUID();
    }

    public InventoryButton setConsumer(Consumer<InventoryClickEvent> consumer) {
        this.consumer = consumer;
        return this;
    }

    public InventoryButton setIcon(ItemStack icon) {
        this.icon = icon;
        return this;
    }
    public static InventoryButton of(ItemStack icon, Consumer<InventoryClickEvent> consumer){
        return new InventoryButton(consumer,icon);
    }
    public static InventoryButton empty(ItemStack icon){
        return of(icon, e -> {});
    }
    public void handleClick(InventoryClickEvent event){
        event.setCancelled(true);
        consumer.accept(event);
    }
    public void setItem(int slot,Inventory inventory){
        inventory.setItem(slot, icon);
    }


}
