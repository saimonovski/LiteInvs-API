package io.github.saimonovski.objects;


import io.github.saimonovski.GuiInstance;
import io.github.saimonovski.handlers.PaginationHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.*;

@SuppressWarnings("ALL")
public abstract class PaginationInstance extends GuiInstance implements PaginationHandler {
    private int maxItemsInPage, currentPage, pagesCount;
    private NavBarHolder navBar;
    boolean isFirstPage, isLastPage;
    int[] availableSlots;
    private final Inventory inventory;
    private final List<InventoryButton> buttons = new ArrayList<>();
    private final Map<Integer, List<InventoryButton>> integerListMap = new HashMap<>();
    private Map<Integer, InventoryButton> currentMappings = new HashMap<>();

    public PaginationInstance(int maxItemsInPage, Inventory inventory) {
        super(inventory);
        navBar = getNavbar();
        this.isFirstPage = true;
        this.isLastPage = false;
        this.maxItemsInPage = maxItemsInPage;
        this.currentPage = 1;
        this.navBar = navBar;
        this.inventory = inventory;
    }
    @Override
    public int maxItemsInPage() {

        if(availableSlots == null) {
            return maxItemsInPage;
        }else {
            return availableSlots.length;
        }
    }
    public Map<Integer, InventoryButton> getCurrentMappings(){
        return currentMappings;
    }

    public int getPagesCount(){
        if (buttons.isEmpty()) return 1;
        int count = buttons.size() / maxItemsInPage();
        return buttons.size() % maxItemsInPage() == 0 ? count : count + 1;
    }


    @Override
    public int currentPage() {
        return currentPage;
    }
    @Override
    public void setMaxItemsInPage(int maxItemsInPage){
        this.maxItemsInPage = maxItemsInPage;
    }
    public void setAvailableSlots(int...slots){
        this.availableSlots = slots;
    }

    @Override
    public void nextPage() {
        if (currentPage >= getPagesCount()){
            currentPage = getPagesCount();
            return;
        }
        currentPage++;
        isFirstPage = currentPage == 1;
        isLastPage = currentPage == getPagesCount();

    }



    @Override
    public void previousPage() {
        if(currentPage()  <= 1){
            currentPage  = 1;
            return;
        }
        currentPage--;
        isFirstPage = currentPage == 1;
        isLastPage = currentPage == getPagesCount();
    }

    @Override
    public void setPage(int page) {
        this.currentPage = page;
        if(currentPage > getPagesCount()) currentPage = getPagesCount();
        if(currentPage < 1) currentPage = 1;
        isFirstPage = currentPage == 1;
        isLastPage = currentPage == getPagesCount();
    }

    @Override
    public void setNavBar(NavBarHolder navBarHolder) {
        this.navBar = navBarHolder;
    }

    @Override
    public void onClick(InventoryClickEvent e) {
        navBar.onClick(e);
        getBackGround().handleClick(e);
        int clickedSlot = e.getRawSlot();
        InventoryButton button = currentMappings.get(clickedSlot);
        if(button == null) return;
        button.handleClick(e);
    }

    @Override
    public void addButton(InventoryButton button, int slot) {

        this.buttons.add(button);
        this.integerListMap.computeIfAbsent(slot, k -> new ArrayList<>()).add(button);

    }

    @Override
    public Inventory getInventory() {
        currentMappings.clear();
        inventory.clear();

        List<Integer> freeSlots = new ArrayList<>();
        Set<Integer> occupiedSlots = new HashSet<>();
        occupiedSlots.addAll(navBar.getOccupiedSlots());
        occupiedSlots.addAll(getBackGround().getOccupiedSlots());
        occupiedSlots.addAll(integerListMap.keySet());

        if (availableSlots != null) {
            for (int slot : availableSlots) {
                if (!occupiedSlots.contains(slot)) {
                    freeSlots.add(slot);
                }
            }
        } else {
            for (int i = 0; i < inventory.getSize(); i++) {
                if (!occupiedSlots.contains(i)) {
                    freeSlots.add(i);
                }
            }
        }
        int startIndex = (currentPage - 1) * freeSlots.size();
        int endIndex = Math.min(startIndex + freeSlots.size(), buttons.size());

        int slotIndex = 0;
      buttons:  for (int i = startIndex; i < endIndex; i++) {
            if (slotIndex < freeSlots.size()) {
                InventoryButton button = getButtonsList().get(i);
                for(var entrySet : integerListMap.entrySet()){
                    if(!entrySet.getValue().contains(button)) continue;
                    button.setItem(entrySet.getKey(),inventory);
                    currentMappings.put(entrySet.getKey(), button);
                     continue buttons;
                }
               button.setItem(freeSlots.get(slotIndex), inventory);
                currentMappings.put(slotIndex,button);
                slotIndex++;
            }
        }

        navBar.decorate(inventory);
        getBackGround().decorate(inventory);
        return inventory;
    }

    /**
     * Method to check if current page is first page
     *
     * @return <b>true</b> if this is first page and <b>false</b> if not
     */
    @Override
    public boolean isFirstPage() {
       return currentPage ==1;
    }

    /**
     * Method to check if current page is last page
     *
     * @return <b>true</b> if this is last page and <b>false</b> if not
     */
    @Override
    public boolean isLastPage() {
        return currentPage == getPagesCount();
    }


}

