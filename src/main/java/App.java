import consoleMenuService.*;

import java.io.IOException;

import pet.PetMenuService;
import store.StoreHttpService;
import store.StoreMenuService;
import user.UserHttpService;
import user.UserMenuService;

public class App {
    private static final int EXIT_FROM_MAIN_NENU = 4;

    private static final int EXIT_FROM_STORE_NENU = 5;
    private static final int EXIT_FROM_USER_NENU = 8;

    public static void main(String[] args) throws IOException, InterruptedException {
        MenuService menuService = new MenuService();
        menuService.createTopLevelOfMenu();
        PetMenuService petMenuService = new PetMenuService();
        PetMenuHandler petMenuHandler = new PetMenuHandler(menuService);

        int choice;
        do {
            menuService.getMenuObjectByName("Main").printMenu();
            choice = menuService.getMenuObjectByName("Main").makeChoice();
            switch (choice) {
                case 1:
                    petMenuHandler.launch();
                    break;
                case 2:
                    int choiceStore;
                    do {
                        menuService.getMenuObjectByName("Store").printMenu();
                        choiceStore = menuService.getMenuObjectByName("Store").makeChoice();
                        switch (choiceStore) {
                            case 1:
                                StoreHttpService.getInventory();
                                break;
                            case 2:
                                StoreMenuService.findOrderById();
                                break;
                            case 3:
                                StoreMenuService.placeOrder();
                                break;
                            case 4:
                                StoreMenuService.deleteOrder();
                            default:
                                System.out.println("Введено неверное значение - пожалуйста, повторите");
                                break;
                        }
                    } while (choiceStore != EXIT_FROM_STORE_NENU);
                    break;
                case 3:
                    int choiceUser;
                    do {
                        menuService.getMenuObjectByName("User").printMenu();
                        choiceUser = menuService.getMenuObjectByName("User").makeChoice();
                        switch (choiceUser) {
                            case 1:
                                UserMenuService.logInUser();
                                break;
                            case 2:
                                UserHttpService.logsOutUser();
                                break;
                            case 3:
                                UserMenuService.findUserByName();
                                break;
                            case 4:
                                UserMenuService.addUser();
                                break;
                            case 5:
                                UserMenuService.addListOfUsers();
                                break;
                            case 6:
                                UserMenuService.updateUser();
                                break;
                            case 7:
                                UserMenuService.deleteUser();
                            default:
                                System.out.println("Введено неверное значение - пожалуйста, повторите");
                                break;
                        }
                    } while (choiceUser != EXIT_FROM_USER_NENU);
                    break;
                default:
                    System.out.println("Введено неверное значение - пожалуйста, повторите");
                    break;
            }
        } while (choice != EXIT_FROM_MAIN_NENU);
    }


}
