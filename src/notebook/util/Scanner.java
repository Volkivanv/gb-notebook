package notebook.util;

public class Scanner {
    public static String prompt(String message) {
        java.util.Scanner in = new java.util.Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
