import consoleMenuService.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import httpUtilities.*;


public class App {
    public static void main(String[] args) throws IOException {

        MenuService menuService = new MenuService();
        menuService.create();

        int choice;
        do {
            menuService.get("Main").printMenu();
            choice = menuService.get("Main").makeChoice();
            switch (choice) {
                case 1:
                    int choicePet;
                    do {
                        menuService.get("Pet").printMenu();
                        choicePet = menuService.get("Pet").makeChoice();
                        switch (choicePet) {
                            case 1:
                                System.out.print("Введите id :");
                                Scanner sc11 = new Scanner(System.in);
                                long id = sc11.nextLong();
                                try {
                                    Pet pet = PetService.getPetByID(id);
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 2:
                                System.out.print("12");
                                break;
                            case 3:
                                System.out.print("13");
                                break;
                            case 4:
                                System.out.print("14");
                                break;
                            case 5:
                                System.out.print("15");
                                break;
                            case 6:
                                System.out.print("16");
                                break;
                            case 7:
                                System.out.print("17");
                        }

                    } while (choicePet != 8);
                    break;
                case 2:
                    int choiceStore;
                    do {
                        menuService.get("Store").printMenu();
                        choiceStore = menuService.get("Store").makeChoice();
                        switch (choiceStore) {
                            case 1:
                                /*System.out.print("Введите id :");
                                Scanner sc21 = new Scanner(System.in);
                                long id = sc21.nextLong();
                                try {
                                    Order order = StoreService.getOrderID("order", id);
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }

                                 */
                                break;
                            case 2:
                                System.out.print("Введите id :");
                                Scanner sc22 = new Scanner(System.in);
                                long id = sc22.nextLong();
                                try {
                                    Order order = StoreService.getOrderByID("order", id);
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 3:
                                System.out.print("23");
                                break;
                            case 4:
                                System.out.print("24");
                        }
                    } while (choiceStore != 5);
                    break;
                case 3:
                    int choiceUser;
                    do {
                        menuService.get("User").printMenu();
                        choiceUser = menuService.get("User").makeChoice();
                        switch (choiceUser) {
                            case 1:
                                System.out.println("31");
                                break;
                            case 2:
                                System.out.print("32");
                                break;
                            case 3:
                                System.out.print("Введите userNameСоздание пользователя : ");
                                Scanner sc33 = new Scanner(System.in);
                                String userName = sc33.nextLine();
                                try {
                                    UserService.getUserByUsername(userName);
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 4:
                                System.out.print("Создание пользователя. ");
                                User testUser = User.builder().
                                        username("Test").
                                        firstName("ivan").
                                        lastName("Ivanov").
                                        email("test@test.com").
                                        password("test1").
                                        phone("056-123-45-67").
                                        userStatus(1).
                                        build();
                                try {
                                    UserService.createNewUser(testUser);
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }

                                break;
                            case 5:
                                System.out.print("35");
                                break;
                            case 6:
                                System.out.print("36");
                                break;
                            case 7:
                                System.out.print("37");
                        }
                    } while (choiceUser != 8);
                    break;

            }
        } while (choice != 4);
    }
}
