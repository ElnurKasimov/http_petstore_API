import consoleMenuService.*;

import java.io.IOException;

import pet.PetMenuService;
import store.StoreHttpService;
import store.StoreMenuService;
import user.UserHttpService;
import user.UserMenuService;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        MenuService menuService = new MenuService();
        menuService.createTopLevelOfMenu();
        int choice;
        do {
            menuService.getMenuObjectByName("Main").printMenu();
            choice = menuService.getMenuObjectByName("Main").makeChoice();
            switch (choice) {
                case 1:
                    int choicePet;
                    do {
                        menuService.getMenuObjectByName("Pet").printMenu();
                        choicePet = menuService.getMenuObjectByName("Pet").makeChoice();
                        switch (choicePet) {
                            case 1:
                                PetMenuService.findPetById();
                                break;
                            case 2:
                                PetMenuService.printLIstOfPetsByStatus();
                                break;
                            case 3:
                                PetMenuService.addPetToDatabase();
                                break;
                            case 4:
                                PetMenuService.uploadPetPhoto();
                                break;
                            case 5:
                                PetMenuService.updatePetByFormData();
                                break;
                            case 6:
                                PetMenuService.updatePetAllData();
                                break;
                            case 7:
                                PetMenuService.deletePet();
                        }
                    } while (choicePet != 8);
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
                        }
                    } while (choiceStore != 5);
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
                        }
                    } while (choiceUser != 8);
                    break;
            }
        } while (choice != 4);
    }
}
