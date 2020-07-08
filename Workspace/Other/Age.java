import java.util.Scanner;

public class Age {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int age;

        System.out.print("Please enter today's date (month/day/year): ");
        String date = scan.nextLine();
        Date todayDate = new Date(date);

        System.out.print("Please enter your birthdate (month/day/year): ");
        String birthday = scan.nextLine();
        Date birthDate = new Date(birthday);

        age = todayDate.getYear() - birthDate.getYear();

        while (age <= 1) {

            System.out.print(
                    "The dates you have entered are too close. You're not old enough to be able to do anything, let alone this survey");

            System.out.print("Please enter the REAL date: ");
            date = scan.nextLine();
            todayDate = new Date(date);

            System.out.print("Please enter your REAL birthday: ");
            birthday = scan.nextLine();
            birthDate = new Date(birthday);
        }

        System.out.println();

        System.out.println(todayDate.calculateAge(birthDate));

        System.out.println(todayDate.timeTilDate(birthDate));

        System.out.println();
        System.out.println(todayDate.eligibility());
        System.out.println();

        scan.close();
    }
}