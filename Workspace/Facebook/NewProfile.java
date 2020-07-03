import java.util.*;

public class NewProfile extends Profile {
    private String firstName;
    private String lastName;
    private int age;

    public NewProfile(String firstName, String lastName, int age) {
        super(firstName, lastName, age);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;        
    }

    public void printNewProfile() {
        System.out.println("New Profile: " + getFirstName() + " " + getLastName() + "\nAge: " + getAge() + "\n");
    }
}