import java.util.*;
import java.io.*;

public class FacebookRun {
    private static File passwords = new File("/Users/TylerDeBrock/GitHub/VisualStudio/Workspace/Facebook/Passwords.txt");
    private static File profiles = new File("/Users/TylerDeBrock/GitHub/VisualStudio/Workspace/Facebook/ProfileList.txt");

    private static ArrayList<Profile> profileList = new ArrayList<Profile>();
    private static Scanner scan = new Scanner(System.in);
    private static int numLines = 0;
    private static boolean validInput = false;
    private static boolean validUsername = false;
    private static int numProfiles;

    public static void main(String[]args) throws FileNotFoundException, IOException {
        getProfiles();
        //getPasswords();

        System.out.println("1: Create New Profile\n2: Log In As Existing User");
        
        while (!validInput) {
            int x = scan.nextInt();
            if (x == 1) {
                scan.nextLine();
                newProfile();
                validInput = true;
            } else if (x == 2) {
                scan.nextLine();
                login();
                validInput = true;
            } else {
                System.out.println("Invalid submission. Please try again");
            }
        }
        
        System.out.println("1: Edit Profile\n2:");

        if (scan.nextInt() == 1) {
            editProfile();
        } else if (scan.nextInt() == 2) {
            printAllProfiles();
        }
    }

    private static void getProfiles() throws FileNotFoundException, IOException{
        numProfiles = findNumLines(profiles) / 4;
        Scanner profileScanner = new Scanner(profiles);

        for (int i = 0; i < numProfiles; i++) {
            scan.nextInt();

            scan.nextLine();
            String f = scan.nextLine();
            String l = scan.nextLine();
            String b = scan.nextLine();

            profileList.add(new Profile(f, l, b));
        }
    }

    private static void login() throws FileNotFoundException, IOException {
        System.out.print("Username: ");
        String username = scan.nextLine();

        System.out.print("Password: ");
        String password = scan.nextLine();
    }

    private static void newProfile() throws FileNotFoundException, IOException {
        FileWriter f = new FileWriter(profiles, true);
        int x;
        
        findNumLines(profiles);

        System.out.print("\nPlease enter your first name: ");
        String firstName = scan.nextLine();

        System.out.print("Please enter your last name: ");
        String lastName = scan.nextLine();

        System.out.print("Please enter your birthday (mm/dd/yy): ");
        String birthday = scan.nextLine();

        System.out.print("Please enter your preferred username: ");
        String newUsername = scan.nextLine();
        checkUsername();
        while (!validUsername) {
            System.out.print("This username is taken. Please enter a new username: ");
            newUsername = scan.nextLine();
            checkUsername();
        }

        System.out.print("Please enter a password: ");
        String newPassword = scan.nextLine();

        MyProfile profile = new MyProfile(firstName, lastName, birthday);
        System.out.println("Is this correct? (y/n)");
        if (scan.next().charAt(0) != ('y')) {
            editProfile();
        }

        x = (numLines / 4) + 1;

        f.append(firstName + "\n" + lastName + "\n" + birthday + "\n" + x + "\n");
        f.flush();
    }

    private static boolean checkUsername() throws FileNotFoundException, IOException{
        Scanner s = new Scanner(passwords);
        int y;

        findNumLines(passwords);
        y = (numLines / 3) + 1;

        return validUsername;
    }

    private static int findNumLines(File file) throws FileNotFoundException, IOException {
        Scanner fileScanner = new Scanner(file);

        while(fileScanner.hasNextLine()) {
            numLines++;
            fileScanner.nextLine();
        }

        return numLines;
    }

    private static void editProfile() {
        
    }

    private static void printAllProfiles() {
        //for (int i = 0; i < profileList.size(); i++) {
            //profileList.get(i).printProfile();
        //}
    }
}