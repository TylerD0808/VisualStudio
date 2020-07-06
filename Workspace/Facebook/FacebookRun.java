import java.util.*;
import java.io.*;
import java.security.cert.X509Certificate;

public class FacebookRun {
    private static File profiles = new File("/Users/TylerDeBrock/GitHub/VisualStudio/Workspace/Facebook/ProfileList.txt");
    private static File passwords = new File("/Users/TylerDeBrock/GitHub/VisualStudio/Workspace/Facebook/Passwords.txt");
    private static File securityQuestions = new File("/Users/TylerDeBrock/GitHub/VisualStudio/Workspace/Facebook/SecurityQuestions.txt");

    private static ArrayList<Profile> profileList = new ArrayList<Profile>();
    private static ArrayList<Security> securityList = new ArrayList<Security>();

    private static boolean validUsername = false;
    private static boolean validInput = false;

    private static Scanner scan = new Scanner(System.in);
    private static int numLines = 0;
    private static int numProfiles;
    private static int profileNumber;

    public static void main(String[]args) throws FileNotFoundException, IOException {
        getProfiles();
        getPasswords();

        startMenu();
        
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
            String f = profileScanner.nextLine();
            String l = profileScanner.nextLine();
            String b = profileScanner.nextLine();

            profileList.add(new Profile(f, l, b));

            profileScanner.nextLine();
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
            String u = securityScanner.nextLine();
            String p = securityScanner.nextLine();
            String a1 = securityScanner.nextLine();
            String a2 = securityScanner.nextLine();

            securityList.add(new Security(u, p, a1, a2));

            securityScanner.nextLine();
        }
    }

    /*
    --------------------------------------------------------------------------------
    3: Prints start menu. Either creates new profile or logs user in
    --------------------------------------------------------------------------------
    */
    private static void startMenu() throws FileNotFoundException, IOException {
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

        validInput = false;
        System.out.println();
    }

    /*
    --------------------------------------------------------------------------------
    4: Logs user into the system with valid username and password
    --------------------------------------------------------------------------------
    */
    private static void logIn() throws FileNotFoundException, IOException {
        System.out.println();
        
        while (!validInput) {
            System.out.print("Username: ");
            String username = scan.nextLine();

            for (int i = 0; i < numProfiles; i++) {
                if (username.equals(securityList.get(i).getUsername())) {
                    profileNumber = i;
                    validInput = true;
                    break;
                }
            }

            if (validInput == false) {
                System.out.println("\nInvalid username. Please try again");
            }
        }

        countdown(5, "Password", 0);
    }

    /*
    --------------------------------------------------------------------------------
    5: Compares answer to expectedAnswer
    --------------------------------------------------------------------------------
    */
    private static void securityQuestions() throws FileNotFoundException, IOException {
        int questionNumber = Integer.parseInt(securityList.get(profileNumber).getAnswer1().substring(0, 1));
        String expectedAnswer = securityList.get(profileNumber).getAnswer1().substring(2);

        System.out.println(securityQuestionLocator(questionNumber));
        
        countdown(3, "Answer", 1);

        questionNumber = Integer.parseInt(securityList.get(profileNumber).getAnswer2().substring(0, 1));
        expectedAnswer = securityList.get(profileNumber).getAnswer2().substring(2);

        System.out.println("\n" + securityQuestionLocator(questionNumber));

        countdown(3, "Answer", 1);

        System.out.println("\nYour password: " + securityList.get(profileNumber).getPassword() + "\nYou better not get this wrong again");
        System.out.print("Password: ");
        String password = scan.nextLine();

        if (!password.equals(securityList.get(profileNumber).getPassword())) {
            System.out.println("\nI can't believe it. I JUST gave you your password. You did this to yourself....");
            System.exit(0);
        }
    
        validInput = true;
    }

    /*
    --------------------------------------------------------------------------------
    6: Given a question number, this returns the security question as a String
    --------------------------------------------------------------------------------
    */
    private static String securityQuestionLocator(int questionNumber) throws FileNotFoundException, IOException {
        Scanner securityQuestionScanner = new Scanner(securityQuestions);

        for (int i = 0; i < questionNumber; i++) {
            securityQuestionScanner.nextLine();
        }
        
        return securityQuestionScanner.nextLine().substring(2);
    }

    /*
    --------------------------------------------------------------------------------
    7: Checks if 2 Strings are the same regardless of capitalization. Returns boolean
    --------------------------------------------------------------------------------
    */
    private static boolean match(String answer1, String answer2) throws FileNotFoundException, IOException {
        return answer1.equalsIgnoreCase(answer2);
    }

    /*
    --------------------------------------------------------------------------------
    8: Counts down from given number, name, and number representing a specific action
    --------------------------------------------------------------------------------
    */
    private static void countdown(int count, String name, int action) throws FileNotFoundException, IOException { 
        Security getSecurity = securityList.get(profileNumber);
        String s;
        count--;
        validInput = false;
        
        while (!validInput) {
            System.out.print(name + ": ");
            s = scan.nextLine();

            if (name.equals("Password")) {
                if (s.equals(getSecurity.getPassword())) {
                    validInput = true;
                } else {
                    count(count--, name, action);
                }
            } else if (name.equals("Answer")) {
                if (match(getSecurity.getAnswer1().substring(2), s) || match(getSecurity.getAnswer2().substring(2), s)) {
                    validInput = true;
                } else {
                    count(count--, name, action);
                }
            }
        }

        validInput = false;
    }

    /*
    --------------------------------------------------------------------------------
    9: Actual function of countdown. Provides final action if count == 0
    --------------------------------------------------------------------------------
    */
    private static void count(int count, String name, int action) throws FileNotFoundException, IOException {
        if (count > 0) {
            System.out.print("\nInvalid " + name.toLowerCase() + ". You have " + count + " more attempt");
            if (count > 1) {
                System.out.println("s");
            } else {
                System.out.println();
            }
        } else if (action == 0) {
            System.out.println("\nYou clearly forgot your password. Please answer these security questions instead:");
            securityQuestions();
        } else if (action == 1) {
            System.out.print("Well, you tried");
            System.exit(0);
        }
    }

    private static void newProfile() throws FileNotFoundException, IOException {
        FileWriter f = new FileWriter(profiles, true);
        Scanner securityQuestionScanner = new Scanner(securityQuestions);
        //int x;
        
        findNumLines(profiles);

        System.out.print("\nPlease enter your first name: ");
        String firstName = scan.nextLine();

        System.out.print("Please enter your last name: ");
        String lastName = scan.nextLine();

        System.out.print("Please enter your birthday (mm/dd/yyyy): ");
        String birthday = scan.nextLine();

        while (!validInput) {
            checkBirthday(birthday);
            birthday = scan.nextLine();
        }

        validInput = false;

        System.out.print("Please enter your preferred username: ");
        String newUsername = scan.nextLine();
        
        while (!checkUsername(newUsername)) {
            System.out.print("This username is taken. Please enter a new username: ");
            newUsername = scan.nextLine();
        }

        while (!validInput) {
            System.out.print("Please enter a password: ");
            String newPassword = scan.nextLine();
            System.out.print("Please re-enter your password: ");
            String passwordCheck = scan.nextLine();
            if (!passwordCheck.equals(newPassword)) {
                System.out.println("\nYour passwords did not match");
            } else {
                validInput = true;
            }
        }
        
        validInput = false;
        System.out.println("\n");

        //printSecurityQuestions(0);
        /*for (int i = 1; i < 11; i++) {
            System.out.println(i + ": " + securityQuestionScanner.nextLine().substring(2));
        }*/

        System.out.print("\nPlease select a security question to answer: ");
        int selection1 = scan.nextInt();
        scan.nextLine();
        System.out.print("Answer: ");
        String answer1 = scan.nextLine();

        //printSecurityQuestions(1);
        /*for (int i = 1; i < selection1; i++) {
            System.out.println(i + ": " + securityQuestionScanner.nextLine().substring(2));
        }

        for (int i = 0; i )*/

        System.out.print("\nPlease select another security question to answer: ");
        int selection2 = scan.nextInt();
        scan.nextLine();
        System.out.print("Answer: ");
        String answer2 = scan.nextLine();

        MyProfile profile = new MyProfile(firstName, lastName, birthday);
        System.out.println("Is this correct? (y/n)");
        if (scan.next().charAt(0) != ('y')) {
            editProfile();
        }

        //x = (numLines / 4) + 1;

        //f.append(firstName + "\n" + lastName + "\n" + birthday + "\n" + x + "\n");
        f.flush();
    }

    private static boolean checkBirthday(String birthday) throws FileNotFoundException, IOException {
        if (birthday.length() == 10) {
            String birthdayCheck = birthday.substring(2,3) + birthday.substring(5, 6);
            
            if (birthdayCheck.equals("//")) {
                System.out.print("X");
                return false;
            } else {
                return true;
            }
        } else {
            System.out.print("\nPlease write your birthday in the proper format (mm/dd/yyyy): ");
        }
        
        return false;
    }

    /*
    --------------------------------------------------------------------------------
    12: Determines if a given username is valid. Returns true if username is unique
    --------------------------------------------------------------------------------
    */
    private static boolean checkUsername(String username) throws FileNotFoundException, IOException {
        for (int i = 0; i < numProfiles; i++) {
            if (securityList.get(i).getUsername().equals(username)) {
                return false;
            }
        }

        return true;
    }

    private static int findNumLines(File file) throws FileNotFoundException, IOException {
        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNextLine()) {
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