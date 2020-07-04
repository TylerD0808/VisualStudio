import java.util.*;
import java.io.*;

public class NewProfile extends Profile {
    public NewProfile(String firstName, String lastName, String birthday) {
        super(firstName, lastName, birthday);
        printNewProfile();       
    }

    public void printNewProfile() {
        System.out.println("\nNew Profile: " + getFirstName() + " " + getLastName() + "\nAge: " + getBirthday() + "\n");
    }
}