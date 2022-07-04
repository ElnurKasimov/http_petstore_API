package httpUtilities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

/*
    @Override
    public String toString() {
        return "user{\n" +
                "\t\"id=\": " + id + ",\n" +
                "\t\"name\": \"" + name + "\",\n" +
                "\t\"username\": \"" + username + "\",\n" +
                "\t\"email\": \"" + email + "\",\n" +
                address.toString() +
                "\t\"phone\": \"" + phone + "\",\n" +
                "\t\"website\": \"" + website + "\",\n" +
                company.toString() + "}";
    }

 */
}
