import java.util.*;
import java.io.*;

public class Profile {
    private String firstName;
    private String lastName;
    private String birthday;
    //private static int numFriends;
    //private boolean isFriend;
    //private ArrayList<Profile> friendsList = new ArrayList<Profile>();

    public Profile(String firstName, String lastName, String birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        //numFriends = 0;
        //isFriend = false;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    //private int getNumFriends() {
        //return numFriends;
    //}

    //public void printProfile() {
    //    System.out.println(getFirstName() + " " + getLastName() + "\nFriends: " + getNumFriends() + "\n");
    //}

    //public void addFriend(Profile profile) {
        //friendsList.add(profile);
        //numFriends++;
        //profile.addFriend(this.Profile;
    //}
}