package io.github.saimonovski.handlers;

import io.github.saimonovski.objects.InventoryButton;
import io.github.saimonovski.objects.NavBarHolder;

import java.util.List;

public interface PaginationHandler extends InventoryHandler{
    List<InventoryButton> getButtonsList();
  default  void setButton(InventoryButton button){
        getButtonsList().add(button);
    }
    default void removeButton(InventoryButton button){
    getButtonsList().remove(button);
    }
  default void removeButton(int index){
    getButtonsList().remove(index);
  }
    int maxItemsInPage();
    int currentPage();

  void setMaxItemsInPage(int maxItemsInPage);

  void nextPage();
    void previousPage();
    void setPage(int page);
    void setNavBar(NavBarHolder navBarHolder);

  /**
   * Method to check if current page is first page
   * @return <b>true</b> if this is first page and <b>false</b> if not
   */
  boolean isFirstPage();
  /**
   * Method to check if current page is last page
   * @return <b>true</b> if this is last page and <b>false</b> if not
   */
    boolean isLastPage();
  public  NavBarHolder getNavbar();



}
