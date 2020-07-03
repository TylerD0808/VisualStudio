import java.util.Scanner;
import java.util.*;

public class FacebookRun {
    private static ArrayList<Profile> profileList = new ArrayList<Profile>();

    public static void main(String[]args) {
        Scanner scan = new Scanner(System.in);

        newProfile();

        System.out.println("1: Create Another Profile\n2: Edit Profile");

        if (scan.nextInt() == 1) {
            newProfile();
        } else if (scan.nextInt() == 2) {
            editProfile();
        } else if (scan.nextInt() == 3) {
            printAllProfiles();
        }
    }

    private static void newProfile() {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nPlease enter your first name: ");
        String firstName = scan.nextLine();

        System.out.print("Please enter your last name: ");
        String lastName = scan.nextLine();

        System.out.print("Please enter your age: ");
        int age = scan.nextInt();

        NewProfile profile = new NewProfile(firstName, lastName, age);
        profileList.add(profile);
        System.out.println();
        profile.printNewProfile();
    }

    private static void editProfile() {
        
    }

    private static void printAllProfiles() {
        for (int i = 0; i < profileList.size(); i++) {
            profileList.get(i).printProfile();
        }
    }
}
    