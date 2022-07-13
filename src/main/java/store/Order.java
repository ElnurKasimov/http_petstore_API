package store;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Order {
    private long id;
    private long petId;
    public int quantity;
    private String shipDate;
    private Status STATUS;

    public enum Status{
        placed,
        approved,
        delivered
    }
    @Override
    public String toString() {
        return "Order{\n" +
                "\t\"id=\": \"" + id + "\",\n" +
                "\t\"petId=\": \"" + petId + "\",\n" +
                "\t\"quantity\": \"" + quantity + "\",\n" +
                "\t\"shipDate\": \"" + shipDate + "\",\n" +
                "\t\"status\": \"" + STATUS + "\",\n" + "}";
    }
}
