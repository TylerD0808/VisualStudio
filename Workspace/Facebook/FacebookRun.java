import java.util.*;
import java.io.*;

public class FacebookRun {
    private static ArrayList<Profile> profileList = new ArrayList<Profile>();
    private static Scanner scan = new Scanner(System.in);
    private static int numLines = 0;

    public static void main(String[]args) throws FileNotFoundException, IOException {
        System.out.println("1: Create New Profile\n2: Edit Profile");

        if (scan.nextInt() == 1) {
            scan.nextLine();
            newProfile();
        } else if (scan.nextInt() == 2) {
            editProfile();
        } else if (scan.nextInt() == 3) {
            printAllProfiles();
        }
    }

    private static void newProfile() throws FileNotFoundException, IOException {
        File file = new File("/Users/TylerDeBrock/GitHub/VisualStudio/Workspace/Facebook/ProfileList.txt");
        Scanner fileScanner = new Scanner(file);
        FileWriter f = new FileWriter(file, true);
        int x;
        
        findNumLines();

        System.out.print("\nPlease enter your first name: ");
        String firstName = scan.nextLine();

        System.out.print("Please enter your last name: ");
        String lastName = scan.nextLine();

        System.out.print("Please enter your birthday (mm/dd/yy): ");
        String birthday = scan.nextLine();

        NewProfile profile = new NewProfile(firstName, lastName, birthday);
        System.out.println("Is this correct? (y/n)");
        if (scan.next().charAt(0) != ('y')) {
            editProfile();
        }

        x = (numLines / 4) + 1;

        f.append(firstName + "\n" + lastName + "\n" + birthday + "\n" + x + "\n");
        f.flush();


    }

    private static int findNumLines() throws FileNotFoundException, IOException {
        File file = new File("/Users/TylerDeBrock/GitHub/VisualStudio/Workspace/Facebook/ProfileList.txt");
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