import java.util.*;
import java.io.*;

public class Security {
    private String username;
    private String password;
    private String answer1;
    private String answer2;

    public Security(String username, String password, String answer1, String answer2) {
        this.username = username;
        this.password = password;
        this.answer1 = answer1;
        this.answer2 = answer2;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }
}