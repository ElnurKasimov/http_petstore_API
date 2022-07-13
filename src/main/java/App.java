import consoleMenuService.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import httpUtilities.*;
import pet.PetMenuService;
import store.Order;
import store.StoreService;
import user.User;
import user.UserService;

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
                                StoreService.getInventory();
                                break;
                            case 2:
                                System.out.println("Обратите внимание, что номер заказа должен быть в пределах от 1 до 10. иначе будет выброшена ошибка ввода.");
                                System.out.print("Введите id : ");
                                Scanner sc22 = new Scanner(System.in);
                                long orderId22 = sc22.nextLong();
                                try {
                                    if (CommonUtilities.isObjectExist("pet/order", orderId22) == 200) {
                                        Order order = StoreService.getOrderByID("store", orderId22);
                                        System.out.println(order);
                                    } else {
                                        System.out.println("Заказа с таким id не существует");
                                    }
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 3:
                                System.out.println("Введите, пожалуйста, данные по заказу, который надо разместить.");
                                Order newOrder = StoreService.inputAllDataOfOrder();
                                try {
                                    CommonUtilities.createNewObject("pet/order", newOrder);
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 4:
                                System.out.print("Введите, пожалуйста, данные по заказу, который желаете удалить из базы.");
                                Scanner sc24 = new Scanner(System.in);
                                System.out.print("id домашнего животного (1 - 9) : ");
                                long orderId24 = sc24.nextLong();
                                CommonUtilities.deleteObject("pet/order/" + orderId24, StoreService.getOrderByID("store", orderId24));
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
                                System.out.print("Введите имя пользователя : ");
                                Scanner sc31 = new Scanner(System.in);
                                String nameForLogIn = sc31.nextLine();
                                System.out.print("Введите пароль пользователя : ");
                                String passwordForLogIn = sc31.nextLine();
                                UserService.logsUser(nameForLogIn, passwordForLogIn);
                                break;
                            case 2:
                                UserService.logsOutUser();
                                break;
                            case 3:
                                System.out.print("Введите userName пользователя : ");
                                Scanner sc33 = new Scanner(System.in);
                                String userName33 = sc33.nextLine();
                                try {
                                    if (CommonUtilities.isObjectExist("user", userName33) == 200) {
                                        User user = UserService.getUserByUsername(userName33);
                                        System.out.println(user);
                                    } else {
                                        System.out.println("Пользователя с таким именем не существует");
                                    }
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 4:
                                System.out.println("Введите, пожалуйста, данные по пользователю.");
                                User newUser = UserService.inputAllDataOfUser();
                                try {
                                    CommonUtilities.createNewObject("user", newUser);
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 5:
                                List<User> usersForTest = UserService.createListOfUsersForTest();
                                try {
                                    UserService.createListOfNewUsers(usersForTest);
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 6:
                                System.out.print("Введите, пожалуйста, имя пользователя, которого желаете обновить : ");
                                Scanner sc36 = new Scanner(System.in);
                                String nameToUpdate = sc36.nextLine();
                                try {
                                    if (CommonUtilities.isObjectExist("user", nameToUpdate) == 200) {
                                        System.out.println("Введите, пожалуйста, все данные по пользователю, которого желаете обновить.");
                                        User userToUpdate = UserService.inputAllDataOfUser();
                                        CommonUtilities.updateObject("user/" + nameToUpdate, userToUpdate);
                                    } else {
                                        System.out.println("Пользователя с таким именем не существует");
                                    }
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 7:
                                System.out.print("Введите, пожалуйста, имя пользователя, которого желаете удалить из системы : ");
                                Scanner sc37 = new Scanner(System.in);
                                String nameToDelete = sc37.nextLine();
                                try {
                                    if (CommonUtilities.isObjectExist("user", nameToDelete) == 200) {
                                        User userToDelete = UserService.getUserByUsername(nameToDelete);
                                        CommonUtilities.deleteObject("user/" + nameToDelete, userToDelete);
                                    } else {
                                        System.out.println("Пользователя с таким именем не существует");
                                    }
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                        }
                    } while (choiceUser != 8);
                    break;

            }
        } while (choice != 4);
    }
}
