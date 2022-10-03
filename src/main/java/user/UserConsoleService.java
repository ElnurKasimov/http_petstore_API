package user;

import com.google.gson.Gson;
import httpUtilities.CommonUtilities;

import java.util.List;
import java.util.Scanner;

public class UserConsoleService {
    private static final int STATUS_CODE_OK = 200;

    public static void logInUser() {
        System.out.print("Enter username : ");
        Scanner sc = new Scanner(System.in);
        String nameForLogIn = sc.nextLine();
        System.out.print("Enter password: ");
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
        System.out.print(" user id :");
        long userId = sc.nextLong();
        System.out.print("userName :");
        String userName = sc.nextLine();
        if(userName.equals("")) userName = sc.nextLine();
        System.out.print("first name :");
        String firstName = sc.nextLine();
        System.out.print("last name :");
        String lastName = sc.nextLine();
        System.out.print("e-mail :");
        String email = sc.nextLine();
        System.out.print("password :");
        String password = sc.nextLine();
        System.out.print("phone number :");
        String phone = sc.nextLine();
        System.out.print("status (only digits) :");
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
        System.out.print("Enter userName : ");
        Scanner sc = new Scanner(System.in);
        String userName = sc.nextLine();
        try {
            if ((CommonUtilities.isObjectExist("user", userName) / STATUS_CODE_OK) == 1) {
                User user = UserHttpService.getUserByUsername(userName);
                Gson gson = new Gson();
                System.out.println(gson.toJson(user));
            } else {
                System.out.println("There is no user with such userName");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void addUser() {
        System.out.println("Enter, please, data of user.");
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
        System.out.print("Enter, please, userName for user You want to update : ");
        Scanner sc = new Scanner(System.in);
        String nameToUpdate = sc.nextLine();
        try {
            if ((CommonUtilities.isObjectExist("user", nameToUpdate) / STATUS_CODE_OK) == 1) {
                System.out.println("Enter, please, all data for user You want to update.");
                User userToUpdate = inputAllDataOfUser();
                CommonUtilities.updateObject("user/" + nameToUpdate, userToUpdate);
            } else {
                System.out.println("There is no user with such userName");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deleteUser() {
        System.out.print("Enter, please, userName for user You want to delete : ");
        Scanner sc = new Scanner(System.in);
        String nameToDelete = sc.nextLine();
        try {
            if ((CommonUtilities.isObjectExist("user", nameToDelete) / STATUS_CODE_OK)  == 1) {
                User userToDelete = UserHttpService.getUserByUsername(nameToDelete);
                CommonUtilities.deleteObject("user/" + nameToDelete, userToDelete);
            } else {
                System.out.println("There is no user with such userName");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
