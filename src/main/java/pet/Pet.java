package pet;

import lombok.Builder;
import lombok.Data;
import pet.Category;
import pet.Tag;

import java.util.ArrayList;

@Data
@Builder

public class Pet {
    private long id;
    private Category category;
    private String name;
    @Builder.Default
    private ArrayList<String> photoUrls = new ArrayList<>();
    @Builder.Default
    private ArrayList<Tag> tags = new ArrayList<>();
    private String petStatus;

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
