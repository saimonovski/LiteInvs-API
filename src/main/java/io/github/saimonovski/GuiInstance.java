package io.github.saimonovski;


import io.github.saimonovski.objects.InventoryBackGround;
import io.github.saimonovski.objects.InventoryButton;
import io.github.saimonovski.handlers.InventoryHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;


import javax.annotation.Nonnull;
import java.util.Map;

public abstract class GuiInstance implements InventoryHandler {
    private Inventory inventory;


    public GuiInstance( Inventory inventory) {
        this.inventory = inventory;
    }

    public abstract  Map<Integer, InventoryButton> getButtons();
    public void addButton(InventoryButton button, int slot){
        getButtons().put(slot,button);
    }
    @Nonnull
    public abstract InventoryBackGround getBackGround();

    @Override
    public void onClick(InventoryClickEvent e) {
        getBackGround().handleClick(e);
        InventoryButton button = getButtons().get(e.getRawSlot());
        button.handleClick(e);
    }


    @Override
    public Inventory getInventory() {
        getBackGround().decorate(inventory);
        getButtons().forEach((slot, button) -> button.setItem(slot, inventory));
        return inventory;
    }
}
