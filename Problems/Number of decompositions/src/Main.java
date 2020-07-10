import java.util.Scanner;

public class Main {

    public static void partition(int n) {
        partition(n, n, "");
    }
    public static void partition(int n, int max, String prefix) {

        if (n == 0) {
            System.out.println(prefix);
            return;
        }
        for (int i =1 ; i <= Math.min(max, n); i++) {
            partition(n-i, i, prefix + " " + i);
        }

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        partition(n);
    }

}
