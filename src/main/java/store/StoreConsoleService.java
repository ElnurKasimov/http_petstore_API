package store;

import com.google.gson.Gson;
import httpUtilities.CommonUtilities;

import java.util.Scanner;

public class StoreConsoleService {
    private static final int STATUS_CODE_OK = 200;
    public static void findOrderById() {
        System.out.println("Take into account that the order number has to be in a range from 1 till 10. Otherwise an exception will be thrown.");
        System.out.print("Enter id : ");
        Scanner sc = new Scanner(System.in);
        long orderId = sc.nextLong();
        try {
            if ((CommonUtilities.isObjectExist("store/order", orderId) / STATUS_CODE_OK) == 1) {
                Order order = StoreHttpService.getOrderByID("order", orderId);
                Gson gson = new Gson();
                System.out.println(gson.toJson(order));
            } else {
                System.out.println("There is no order with such id");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Order inputAllDataOfOrder(){
        System.out.print("order id  (from1 to 10) : ");
        Scanner sc = new Scanner(System.in);
        long idPet = sc.nextLong();
        System.out.print("pet id : ");
        long petId = sc.nextLong();
        System.out.print("quantity : ");
        int quantity = sc.nextInt();
        System.out.print(" shipDate(yyyy-mm-dd) : ");
        String shipDate = sc.nextLine();
        if(shipDate.equals("")) shipDate = sc.nextLine();
        System.out.print("order status (placed, approved, delivered) : ");
        String status = sc.nextLine();
        return Order.builder().
                id(idPet).
                petId(petId).
                quantity(quantity).
                shipDate(shipDate).
                STATUS(Order.Status.valueOf(status)).
                complete("true").
                build();
    }
    public static void placeOrder() {
        System.out.println("Enter, please, order data You want to place.");
        Order newOrder = inputAllDataOfOrder();
        try {
            CommonUtilities.createNewObject("store/order", newOrder);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deleteOrder() {
        System.out.print("Enter, please, order data You want to delete ( from 1 to 10) : ");
        Scanner sc = new Scanner(System.in);
        System.out.print("order id  : ");
        long orderId = sc.nextLong();
        try {
            CommonUtilities.deleteObject("store/order/" + orderId, StoreHttpService.getOrderByID("order", orderId));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
