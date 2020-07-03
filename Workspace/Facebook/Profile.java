import java.util.*;

public class Profile {
    private String firstName;
    private String lastName;
    private int age;
    private static int numFriends;
    private boolean isFriend;
    //private ArrayList<Profile> friendsList = new ArrayList<Profile>();

    public Profile(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        //numFriends = 0;
        //isFriend = false;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    private int getNumFriends() {
        return numFriends;
    }

    public void printProfile() {
        System.out.println(getFirstName() + " " + getLastName() + "\nFriends: " + getNumFriends() + "\n");
    }

    public void addFriend(Profile profile) {
        //friendsList.add(profile);
        numFriends++;
        //profile.addFriend(this.Profile;
    }
}