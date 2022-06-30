package httpUtilities;

import lombok.Data;

@Data
public class ApiResponce {
        private int code;
        private String type;
        private String message;
}
