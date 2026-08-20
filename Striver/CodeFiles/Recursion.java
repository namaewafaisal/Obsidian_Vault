
import java.util.Scanner;

public class Recursion {

    static void printText(String s, int count, int current) {
        if(current == count) return;
        System.out.println(s);
        printText(s, count, current+1);
    }

    static void print1ToN(int count, int n) {
        if(n < count) return;
        System.out.println(count);
        print1ToN(count + 1, n);
    }

    static void printNTo1(int count, int n) {
        if(n < count) return;
        printNTo1(count + 1, n);
        System.out.println(count);
    }

    static void print1ToNBacktrack(int count) {
        if(count < 1) return;
        print1ToNBacktrack(count-1);
        System.out.println(count);
    }

    static void printNTo1Backtrack(int count) {
        if(count < 1) return;
        System.out.println(count);
        printNTo1Backtrack(count - 1);
    }


    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // String str = sc.next();
        int n = sc.nextInt();

        printNTo1Backtrack(n);
    }
}
