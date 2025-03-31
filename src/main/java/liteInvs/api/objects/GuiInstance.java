package liteInvs.api.objects;


import liteInvs.api.handlers.BackGroundHandler;
import liteInvs.api.handlers.InventoryHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;


import javax.annotation.Nonnull;
import java.util.Map;

/**
 * This is gui instance representing InventoryHandler, to use it your class have to extend it
 */
@SuppressWarnings("unused")
public abstract class GuiInstance implements InventoryHandler {

    private final Inventory inventory;

    /**
     * Constructor
     * @param inventory represents an empty inventory where you can add more buttons. Inventory can be a various type (ChestInventory, AnvilInventory etc.)
     */
    public GuiInstance( Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Method to get mapping of lot to button
     * @return <b><code>Map of Integer and InventoryButton  </code></b> where Integer represents a slot where InventoryButton is placed
     * @see InventoryButton
     */

    public abstract  Map<Integer, InventoryButton> getButtons();

    /**
     * Method to add a button to an inventory
     * @param button button to add
     * @param slot slot where button should be placed <b>If this slot is currently taken slot will be overwritten ! (Not when this is slot of background)</b>
     * @see BackGroundHandler
     * @see InventoryButton
     */
    public void addButton(InventoryButton button, int slot){
        getButtons().put(slot,button);
    }

    /**
     * Method to get the inventory background<b> cannot be null !!!</b>
     * <i>If you want to not set the background just return object of empty background items</i>
     * @see InventoryBackGround
     * @return non-null object of InventoryBackGround
     */
    @Nonnull
    public abstract InventoryBackGround getBackGround();

    /**
     * Checking if clicked slot is button or background slot and starting an action specified by its instance
     * @param e event called by this inventory
     */

    @Override
    public void onClick(InventoryClickEvent e) {
        getBackGround().handleClick(e);
        if(e.isCancelled()) return;
        InventoryButton button = getButtons().get(e.getRawSlot());
        if(button == null) return;
        button.handleClick(e);
    }

    /**
     *
     * @return Inventory with set background and buttons on specified slots
     */

    @Override
    public Inventory getInventory() {
        getBackGround().decorate(inventory);
        getButtons().forEach((slot, button) -> button.setItem(slot, inventory));
        return inventory;
    }
}