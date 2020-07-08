import java.util.Scanner;

public class Interview {
    public static void main(String[] args) {
       
        Scanner scan = new Scanner(System.in);

        System.out.print("Please enter your name: ");
        String name = scan.nextLine();

        System.out.print("Please enter your age: ");
        int age = scan.nextInt();

        System.out.print("Please enter your grade: ");
        int grade = scan.nextInt();

        System.out.print("Please enter your GPA: ");
        double gpa = scan.nextDouble();

        System.out.print("Please enter your class rank: ");
        int rank = scan.nextInt();

        System.out.println();
        System.out.println("Name:\t" + name + "\nAge:\t" + age + "\nGrade:\t " + 
            grade + "\nGPA:\t" + gpa + "\nRank:\t" + rank);
    }
}