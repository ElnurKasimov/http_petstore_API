package httpUtilities;

import lombok.Data;
import java.util.ArrayList;

@Data
public class Pet {
    private long id;
    private Category category;
    private String name;
    private ArrayList<String> photoUrls = new ArrayList<>();
    private ArrayList<String> tags = new ArrayList<>();
    PetStatus petStatus;

    public enum PetStatus {
        available,
        pending,
        sold
    }

}
