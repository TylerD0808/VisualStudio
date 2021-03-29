import java.util.*;
import java.io.*;

public class StringConverter {
    private static File file = new File("/Users/TylerDeBrock/GitHub/VisualStudio/Workspace/Helping Mama/OriginalList.txt");
    private static File withoutDr = new File("/Users/TylerDeBrock/GitHub/VisualStudio/Workspace/Helping Mama/ListWithoutDr.txt");
    private static File drFile = new File("/Users/TylerDeBrock/GitHub/VisualStudio/Workspace/Helping Mama/Dr.txt");

    public static void main (String[]args) throws FileNotFoundException, IOException {
        /*String name = "Tyler Andrew DeBrock";
        System.out.println(name + "\n");
        int endIndex = findSpace(name);
        while (endIndex != -1) {
            System.out.println(name.substring(0, endIndex));
            name = name.substring(endIndex + 1);
            endIndex = findSpace(name);
        }
        System.out.println(name);*/
        List<Integer> drList = doctorIndex(file);
        //printDr(drList);
    }

    private static int findSpace(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                return s.indexOf(' ');
            }
        }
        return -1;
    }

    private static List<Integer> doctorIndex(File file) throws FileNotFoundException, IOException {
        FileWriter f = new FileWriter(withoutDr, true);
        FileWriter d = new FileWriter(drFile, true);
        Scanner scan = new Scanner(file);
        List<Integer> list = new ArrayList<Integer>();
        int count = 0;
        while (scan.hasNextLine()) {
            String s = scan.next();
            if (s.equalsIgnoreCase("dr.")) {
                list.add(count);
                d.append(s + "\n");
                String a = scan.nextLine();
                a = a.substring(1);
                f.append(a);
                count = 0;
            } else {
                f.append(s);
                f.append(scan.nextLine());
                d.append("\n");
            }
            f.append("\n");
            f.flush();
            d.flush();
            count++;
        }
        System.out.println(list);
        return list;
    }

    private static void printDr(List<Integer> list) throws FileNotFoundException, IOException {
        FileWriter f = new FileWriter(drFile, true);
        int count = 0;
        for (Integer i : list) {
            while (count != i) {
                f.append("\n");
                i--;
            }
            f.append("Dr.");
        }
        f.flush();
    }
}
