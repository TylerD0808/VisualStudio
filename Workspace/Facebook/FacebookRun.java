import java.util.*;
import java.io.*;
import java.security.cert.X509Certificate;

public class FacebookRun {
    private static File profiles = new File("/Users/TylerDeBrock/GitHub/VisualStudio/Workspace/Facebook/ProfileList.txt");
    private static File passwords = new File("/Users/TylerDeBrock/GitHub/VisualStudio/Workspace/Facebook/Passwords.txt");

    private static ArrayList<Profile> profileList = new ArrayList<Profile>();
    private static ArrayList<Security> securityList = new ArrayList<Security>();

    private static boolean validUsername = false;
    private static boolean validPassword = false;

    private static Scanner scan = new Scanner(System.in);
    private static int numLines = 0;
    private static boolean validInput = false;
    private static int numProfiles;
    private static int profileNumber;

    public static void main(String[]args) throws FileNotFoundException, IOException {
        getProfiles();
        getPasswords();

        System.out.println("1: Create New Profile\n2: Log In As Existing User");
        
        while (!validInput) {
            int x = scan.nextInt();
            if (x == 1) {
                scan.nextLine();
                newProfile();
                validInput = true;
            } else if (x == 2) {
                scan.nextLine();
                logIn();
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

    /*
    --------------------------------------------------------------------------------
    1: Reads profiles from ProfileList.txt and adds them to ArrayList "profileList"
    --------------------------------------------------------------------------------
    */
    private static void getProfiles() throws FileNotFoundException, IOException {
        numProfiles = findNumLines(profiles) / 4;
        Scanner profileScanner = new Scanner(profiles);

        for (int i = 0; i < numProfiles; i++) {
            profileScanner.nextInt();

            profileScanner.nextLine();
            String f = profileScanner.nextLine();
            String l = profileScanner.nextLine();
            String b = profileScanner.nextLine();

            profileList.add(new Profile(f, l, b));
        }
    }

    /*
    --------------------------------------------------------------------------------
    2: Reads passwords from Passwords.txt and adds them to ArrayList "securityList"
    --------------------------------------------------------------------------------
    */
    private static void getPasswords() throws FileNotFoundException, IOException {
        Scanner securityScanner = new Scanner(passwords);

        for (int i = 0; i < numProfiles; i++) {
            securityScanner.nextInt();

            securityScanner.nextLine();
            String u = securityScanner.nextLine();
            String p = securityScanner.nextLine();
            String a1 = securityScanner.nextLine();
            String a2 = securityScanner.nextLine();

            securityList.add(new Security(u, p, a1, a2));
        }
    }

    /*
    --------------------------------------------------------------------------------
    3: Logs user into the system with valid username and password
    --------------------------------------------------------------------------------
    */
    private static void logIn() throws FileNotFoundException, IOException {
        int count = 5;

        while(!validUsername) {
            System.out.print("Username: ");
            String username = scan.nextLine();

            for (int i = 0; i < numProfiles; i++) {
                if (username.equals(securityList.get(i).getUsername())) {
                    profileNumber = i;
                    validUsername = true;
                    break;
                }
            }

            if (validUsername == false) {
                System.out.println("Invalid username. Please try again");
            }
        }

        while(!validPassword) {
            System.out.print("Password: ");
            String password = scan.nextLine();

            if (password.equals(securityList.get(profileNumber).getPassword())) {
                validPassword = true;
            } else {
                count--;

                if (count == 0) {
                    securityQuestions();
                    break;
                }

                System.out.println("Invalid password. You have " + count + " more attempts");
            }
        }
    }

    private static void securityQuestions() throws FileNotFoundException, IOException {
        
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
        Scanner securityScanner = new Scanner(passwords);
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