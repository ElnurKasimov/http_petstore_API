package user;

import com.google.gson.Gson;
import httpUtilities.CommonUtilities;

import java.util.List;
import java.util.Scanner;

public class UserMenuService {
    public static void logInUser() {
        System.out.print("Введите имя пользователя : ");
        Scanner sc = new Scanner(System.in);
        String nameForLogIn = sc.nextLine();
        System.out.print("Введите пароль пользователя : ");
        String passwordForLogIn = sc.nextLine();
        try {
            UserHttpService.logsUser(nameForLogIn, passwordForLogIn);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static User inputAllDataOfUser () {
        Scanner sc = new Scanner(System.in);
        System.out.print("id пользователя :");
        long userId = sc.nextLong();
        System.out.print("userName пользователя :");
        String userName = sc.nextLine();
        if(userName.equals("")) userName = sc.nextLine();
        System.out.print("имя пользователя :");
        String firstName = sc.nextLine();
        System.out.print("фамилия пользователя :");
        String lastName = sc.nextLine();
        System.out.print("e-mail пользователя :");
        String email = sc.nextLine();
        System.out.print("пароль пользователя :");
        String password = sc.nextLine();
        System.out.print("телефон пользователя :");
        String phone = sc.nextLine();
        System.out.print("статус пользователя :");
        int userStatus = sc.nextInt();
        return User.builder().
                id(userId).
                username(userName).
                firstName(firstName).
                lastName(lastName).
                email(email).
                password(password).
                phone(phone).
                userStatus(userStatus).
                build();
    }
    public static void findUserByName() {
        System.out.print("Введите userName пользователя : ");
        Scanner sc = new Scanner(System.in);
        String userName = sc.nextLine();
        try {
            if (CommonUtilities.isObjectExist("user", userName) == 200) {
                User user = UserHttpService.getUserByUsername(userName);
                Gson gson = new Gson();
                System.out.println(gson.toJson(user));
            } else {
                System.out.println("Пользователя с таким именем не существует");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void addUser() {
        System.out.println("Введите, пожалуйста, данные по пользователю.");
        User newUser = inputAllDataOfUser();
        try {
            CommonUtilities.createNewObject("user", newUser);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void addListOfUsers() {
        List<User> usersForTest = UserHttpService.createListOfUsersForTest();
        try {
            UserHttpService.createListOfNewUsers(usersForTest);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void updateUser() {
        System.out.print("Введите, пожалуйста, имя пользователя, которого желаете обновить : ");
        Scanner sc = new Scanner(System.in);
        String nameToUpdate = sc.nextLine();
        try {
            if (CommonUtilities.isObjectExist("user", nameToUpdate) == 200) {
                System.out.println("Введите, пожалуйста, все данные по пользователю, которого желаете обновить.");
                User userToUpdate = inputAllDataOfUser();
                CommonUtilities.updateObject("user/" + nameToUpdate, userToUpdate);
            } else {
                System.out.println("Пользователя с таким именем не существует");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deleteUser() {
        System.out.print("Введите, пожалуйста, имя пользователя, которого желаете удалить из системы : ");
        Scanner sc = new Scanner(System.in);
        String nameToDelete = sc.nextLine();
        try {
            if (CommonUtilities.isObjectExist("user", nameToDelete) == 200) {
                User userToDelete = UserHttpService.getUserByUsername(nameToDelete);
                CommonUtilities.deleteObject("user/" + nameToDelete, userToDelete);
            } else {
                System.out.println("Пользователя с таким именем не существует");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
