package io.github.saimonovski.handlers;

import io.github.saimonovski.objects.InventoryButton;

import java.util.List;

/**
 * Interface extending InventoryHandler, adding pagination system
 * Classes that implementing this interface  have to had specified pagination system.
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted"})
public interface PaginationHandler extends InventoryHandler{
  /**
   * method to return list of buttons
   * @return list of inventory buttons
   */
    List<InventoryButton> getButtonsList();

  /**
   * Method to add a button to a button list
   * @param button button to add
   */
  default  void setButton(InventoryButton button){
        getButtonsList().add(button);
    }

  /**
   *  method to remove button from a button list
   * @param button button to remove from buttons
   */
  default void removeButton(InventoryButton button){
    getButtonsList().remove(button);
    }

  /**
   * Method to remove button with specified index on the list
   * @param index index to remove a button
   */
  default void removeButton(int index){
    getButtonsList().remove(index);
  }

  /**
   *
   * @return how many items can be in one page
   */
    int maxItemsInPage();

  /**
   *
   * @return a number of page where you currently are
   */
  int currentPage();

  /**
   *
   * @param maxItemsInPage sets amount of how many items can be in one page
   */
  void setMaxItemsInPage(int maxItemsInPage);

  /**
   * Method to handle action of going to next page
   */
  void nextPage();
  /**
   * Method to handle action of going to previous page
   */
    void previousPage();
  /**
   * Method to set the current page
   */
    void setPage(int page);
  /**
   * Method to set a NavBar instance
   * @see NavBarHolder
   */
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

  /**
   * Method to get the NavBarHolder instance
   * @return NavBarHolder
   * @see NavBarHolder
   */
   NavBarHolder getNavbar();



}
