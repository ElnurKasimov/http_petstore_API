package httpUtilities;

import lombok.Builder;
import lombok.Data;
import java.util.ArrayList;

@Data
@Builder

public class Pet {
    private long id;
    private Category category;
    private String name;
    private ArrayList<String> photoUrls = new ArrayList<>();
    private ArrayList<Tag> tags = new ArrayList<>();
    PetStatus petStatus;

    public enum PetStatus {
        available,
        pending,
        sold
    }

    @Override
    public String toString() {
        return "Pet{\n" +
                "\t\"id=\": \"" + id + "\",\n" +
                "\t\"category\": \"" + category.toString() + "\",\n" +
                "\t\"name\": \"" + name + "\",\n" +
                "\t\"photos\": \"" + photoUrls.toString() + "\",\n" +
                "\t\"tags\": \"" + tags.toString() + "\",\n" +
                "\t\"status\": \"" + petStatus + "\",\n" + "}";
    }
}
