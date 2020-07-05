import java.util.*;
import java.io.*;

public class MyProfile extends Profile {
    public MyProfile(String firstName, String lastName, String birthday) {
        super(firstName, lastName, birthday);
        printMyProfile();       
    }

    public void printMyProfile() {
        System.out.println("\nNew Profile: " + getFirstName() + " " + getLastName() + "\nAge: " + getBirthday() + "\n");
    }
}