package consoleMenuService;

import java.util.HashMap;

public class FillerContent {

    public HashMap<Integer, String> fill(String name) {
        HashMap<Integer, String> contentMenu = new HashMap<>();
        switch(name) {
            case "Main":
                contentMenu.put(1, "Pet");
                contentMenu.put(2, "Store");
                contentMenu.put(3, "User");
                contentMenu.put(4, "Exit");
                break;
            case "Pet":
                contentMenu.put(1, "find pet by id");
                contentMenu.put(2, "get pet list by their status");
                contentMenu.put(3, "add a new pet to the store");
                contentMenu.put(4, "upload image of the pet");
                contentMenu.put(5, "update a pet in the store with formData");
                contentMenu.put(6, "update an existing pet");
                contentMenu.put(7, "delete the pet");
                contentMenu.put(8, "return to the previous menu");
                break;
            case "Store":
                contentMenu.put(1, "get pet inventories by status");
                contentMenu.put(2, "find purchase order by id");
                contentMenu.put(3, "place an order for a pet");
                contentMenu.put(4, "delete perchase order by id");
                contentMenu.put(5, "return to the previous menu");
                break;
            case "User":
                contentMenu.put(1, "logs user into the system");
                contentMenu.put(2, "logs out current logged in user session" );
                contentMenu.put(3, "find user by name");
                contentMenu.put(4, "create user");
                contentMenu.put(5, "create list of users with given input array");
                contentMenu.put(6, "update user");
                contentMenu.put(7, "delete user");
                contentMenu.put(8, "return to the previous menu");
        }
        return contentMenu;
    }
}
