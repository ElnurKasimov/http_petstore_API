package store;

import console.MenuService;

import java.io.IOException;

public class StoreMenuHandler {
    private MenuService menuService;
    private static final int EXIT_FROM_STORE_MENU = 5;

    public  StoreMenuHandler(MenuService menuService) {
        this.menuService = menuService;
    }

    public void launch() {
        int choiceStore;
        do {
            menuService.getMenuObjectByName("Store").printMenu();
            choiceStore = menuService.getMenuObjectByName("Store").makeChoice();
            switch (choiceStore) {
                case 1:
                    try {
                        StoreHttpService.getInventory();
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    StoreConsoleService.findOrderById();
                    break;
                case 3:
                    StoreConsoleService.placeOrder();
                    break;
                case 4:
                    StoreConsoleService.deleteOrder();
                default:
                    System.out.println("Unappropriated number has been entered, please enter correct data.");
                    break;
            }
        } while (choiceStore != EXIT_FROM_STORE_MENU);
    }

}
