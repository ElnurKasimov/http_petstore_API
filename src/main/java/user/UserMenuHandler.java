package user;

import console.MenuService;

import java.io.IOException;

public class UserMenuHandler {
    private MenuService menuService;
    private static final int EXIT_FROM_USER_MENU = 8;

    public UserMenuHandler(MenuService menuService) {
        this.menuService = menuService;
    }

    public void launch() {
        int choiceUser;
        do {
            menuService.getMenuObjectByName("User").printMenu();
            choiceUser = menuService.getMenuObjectByName("User").makeChoice();
            switch (choiceUser) {
                case 1:
                    UserConsoleService.logInUser();
                    break;
                case 2:
                    try {
                        UserHttpService.logOutUser();
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    UserConsoleService.findUserByName();
                    break;
                case 4:
                    UserConsoleService.addUser();
                    break;
                case 5:
                    UserConsoleService.addListOfUsers();
                    break;
                case 6:
                    UserConsoleService.updateUser();
                    break;
                case 7:
                    UserConsoleService.deleteUser();
                    break;
                default:
                    System.out.println("Unappropriated number has been entered, please enter correct data.");
                    break;
            }
        } while (choiceUser != EXIT_FROM_USER_MENU);
    }

}
