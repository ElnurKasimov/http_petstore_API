package user;

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


    @Override
    public String toString() {
        return "user{\n" +
                "\t\"id=\": " + id + ",\n" +
                "\t\"username\": \"" + username + "\",\n" +
                "\t\"firstName\": \"" + firstName + "\",\n" +
                "\t\"lastName\": \"" + lastName + "\",\n" +
                "\t\"email\": \"" + email + "\",\n" +
                "\t\"password\": \"" + password + "\",\n" +
                "\t\"phone\": \"" + phone + "\",\n" +
                "\t\"userStatus\": \"" + userStatus + "\",\n" + "}";
    }

}
