
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

    static void reverseArray(int start, int end, int[] arr) {
        if(start >= end) {
            return;
        }
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
        reverseArray(start + 1, end - 1, arr);
    }

    static boolean isPalindrome(int i, int[] arr) {
        int n = arr.length;
        if(i >= n/2) {
            return true;
        }
        if(arr[i] != arr[n-i-1]) {
            return false;
        }
        return isPalindrome(i + 1, arr);

    }

    static void display(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    static int fib(int n) {
        if(n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input
        int n = sc.nextInt();
        System.out.println("Input : " + n);
        
        //Start timer
        long startTime = System.nanoTime();

        // Do actions

        int result = fib(n);

        // End Timer
        long endTime = System.nanoTime();
        long durationNs = endTime - startTime;
        double durationMs = durationNs / 1_000_000.0;
        
        System.out.println("Output : " + result);
            
        System.out.println("Elapsed time: " + durationMs + " ms");        // String str = sc.next();
        
    }
}
