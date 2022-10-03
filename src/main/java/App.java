import console.*;

import java.io.IOException;

import pet.PetMenuHandler;
import store.StoreMenuHandler;
import user.UserMenuHandler;

public class App {
    private static final int EXIT_FROM_MAIN_MENU = 4;

    public static void main(String[] args) {
        MenuService menuService = new MenuService();
        PetMenuHandler petMenuHandler = new PetMenuHandler(menuService);
        StoreMenuHandler storeMenuHandler = new StoreMenuHandler(menuService);
        UserMenuHandler userMenuHandler = new UserMenuHandler(menuService);
        menuService.createTopLevelOfMenu();
        int choice;
        do {
            menuService.getMenuObjectByName("Main").printMenu();
            choice = menuService.getMenuObjectByName("Main").makeChoice();
            switch (choice) {
                case 1:
                    petMenuHandler.launch();
                    break;
                case 2:
                    storeMenuHandler.launch();
                    break;
                case 3:
                     userMenuHandler.launch();
                    break;
                default:
                    System.out.println("Unappropriated number has been entered, please enter correct data.");
                    break;
            }
        } while (choice != EXIT_FROM_MAIN_MENU);
    }

}
