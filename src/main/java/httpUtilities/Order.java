package httpUtilities;

import lombok.Data;

@Data
public class Order {
    private long id;
    private long petId;
    public int quantity;
    private String shipDate;
    private Status status;

    public enum Status{
        placed,
        approved,
        delivered
    }
}
