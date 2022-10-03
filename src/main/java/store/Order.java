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
    private String complete;

    public enum Status{
        placed,
        approved,
        delivered
    }

}
