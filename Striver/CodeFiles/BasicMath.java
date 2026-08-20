import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BasicMath {
    
    static long noOfDigits(long n) {

        long result = 0;

        while(n > 0){
            result++;
            n /= 10;
        }

        return result;
    }

    static long reverseNo(long n) {
        long result = 0;

        while(n > 0){
            result = result * 10 + n % 10;
            n /= 10;
        }
        return result;
    }

    static boolean palindromeNo(long n) {
        // if(n < 0) return false;
        return reverseNo(n) == n;
    }

    static int gcd(int a, int b) {
        if(a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        while(b != 0) {
            int temp = a % b;
            a =  b;
            b = temp;
        }
        return a;

    }

    static boolean armstrongNO(long n){
        
        long digits = noOfDigits(n);
        long result = 0;
        long number = n;

        while(n > 0) {
            result += Math.pow((n % 10),digits); 
            n /= 10;
        }
        return number == result;
    }

    static List<Long> divisors(long n) {
        List<Long> result = new ArrayList<>();

        for(long i = 1; i * i <= n; i++) {
            if(n % i == 0) {
                result.add(i);

                if(i != n / i) {
                    result.add(n / i);
                }
            }
        }
        return result;
    }

    static boolean isPrime(int n) {

        for(int i = 2; i * i <= n; i++) {
            if(n % i == 0) {
                return false;
            }
        }

        return true;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // int a = sc.nextInt();
        // int b = sc.nextInt();

        // int result = gcd(a, b);
        // boolean result = armstrongNO(n);
        // List<Long> result = divisors(n);
        // Collections.sort(result);

        boolean result = isPrime(n);
        System.out.println(result);
    }
}
