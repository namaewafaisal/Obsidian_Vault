
import java.util.Scanner;

public class Patterns {
    
    static void pattern1(int n) {
        for (int i = 0; i < n; i++){
            for(int j = 0; j<n; j++) {
                System.out.print("*");
            }
            System.out.println();
        }    
        
    }

    static void pattern2(int n) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j<=i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void pattern3(int n) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j<=i; j++) {
                System.out.print(j+1);
            }
            System.out.println();
        }
    }
    static void pattern4(int n) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j<=i; j++) {
                System.out.print(i+1);
            }
            System.out.println();
        }
    }

    static void pattern5(int n) {
        for(int i = 0; i<n; i++) {
            for(int j = 0; j < n-i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    static void pattern6(int n) {
        for(int i = 0; i<n; i++) {
            for(int j = 0; j < n-i; j++) {
                System.out.print(j+1);
            }
            System.out.println();
        }
    }
    static void pattern7(int n) {
        for(int i = 1; i<=n; i++) {
            for(int j = 1; j<=n-i; j++){
                System.out.print(" ");
            }
            for(int j = 1; j <= i*2 -1; j++) {
                System.out.print("*");
            }
            for(int j = 1; j <= n-i; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    
    static void pattern8(int n) {
        for(int i = n; i>=1; i--) {
            for(int j = 1; j<=n-i; j++){
                System.out.print(" ");
            }
            for(int j = 1; j <= i*2 -1; j++) {
                System.out.print("*");
            }
            for(int j = 1; j <= n-i; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    
    static void pattern9(int n) {
        pattern7(n);
        pattern8(n);
    }

    static void pattern10(int n) {
        for(int i = 1; i<=(n*2)-1; i++) {
            int stars = n - Math.abs(n-i);
            for(int j = 1; j <= stars; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    
    static void pattern11(int n) {
        for(int i = 1; i<=n; i++) {
            for(int j = 1; j <=i; j++) {
                if(i%2 == j%2){
                    System.out.print(1);
                }
                else {
                    System.out.print(0);
                }
            }
            System.out.println();
        }
    }

    static void pattern12(int n) {
        for(int i = 1; i<=n; i++) {
            for(int j = 1; j<=i; j++){
                System.out.print(j);
            }
            for(int j = 1; j <= (n-i)*2; j++) {
                System.out.print(" ");
            }
            for(int j = i; j >= 1; j--) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    static void pattern13(int n) {
        int k = 1;
        for(int i = 1; i<=n; i++) {
            for(int j = 1; j <=i; j++) {
                System.out.print(k + " ");
                k++;
            }
            System.out.println();
        }
    }
    
    static void pattern14(int n) {
        char c = 'A';
        for(int i = 1; i<=n; i++) {
            char temp = c;
            for(int j = 1; j <=i; j++) {
                System.out.print(temp);
                temp++;
            }
            System.out.println();
        }
    }

    static void pattern15(int n) {
        char c = 'A';
        for(int i = n; i>= 1; i--) {
            char temp = c;
            for(int j = 1; j <=i; j++) {
                System.out.print(temp);
                temp++;
            }
            System.out.println();
        }
    }

    static void pattern16(int n) {
        char c = 'A';
        for(int i = 1; i<= n; i++) {
            for(int j = 1; j <=i; j++) {
                System.out.print(c);
            }
            System.out.println();
            c++;
        }
    }

    static void pattern17(int n) {
        for(int i = 1; i<=n; i++) {

            for(int j = 1; j <= n-i; j++) {
                System.out.print(" ");
            }
            char c = 'A';
            for(int j = 1; j <= (i * 2) - 1; j++) {
                System.out.print(c);
                c += j >= (i*2)/2 ? -1 : 1;
            }
            for(int j = 1; j <= n-i; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    
    static void pattern18(int n) {
        for(int i = 1; i<=n; i++) {
            char c = (char) ('A' +  n  - i);
            char temp = c;
            for(int j = 1; j <=i; j++) {
                System.out.print((char) (c - 1 + j) + " ");
                
            }
            System.out.println();
        }
    }
    
    static void pattern19(int n) {
        for(int i = 0; i<(n*2)+1; i++) {
            if(n == i) continue;
            int stars = Math.abs(n-i);
            for(int j = 0; j < stars; j++) {
                System.out.print("*");
            }
            for(int j = 0; j < (n - stars) * 2; j++) {
                System.out.print(" ");
            }
            for(int j = 0; j < stars; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void pattern20(int n) {
        for(int i = 0; i<(n*2)+1; i++) {
            // if(n == i) continue;
            int stars = n - Math.abs(n-i);
            for(int j = 0; j < stars; j++) {
                System.out.print("*");
            }
            for(int j = 0; j < (n - stars) * 2; j++) {
                System.out.print(" ");
            }
            for(int j = 0; j < stars; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void pattern21(int n) {
        for(int i = 1; i<=n; i++) {
            
            for(int j = 1; j <= n; j++) {
                if(i == 1 || i == n || j == 1 || j == n) {
                    System.out.print("*");
                }
                else {
                    System.out.print(" ");
                }
            }
            
            System.out.println();
        }
    }
    
    static void pattern22(int n) {
        int top = 1;
        int bottom = 2*n -1;
        int left = 1;
        int right = bottom;
        for(int i = 1; i< 2*n; i++) {
            
            for(int j = 1; j < 2*n; j++) {
                int number = n - Math.min(Math.min((j - left),(right - j)), Math.min(i - top, bottom - i));
                System.out.print(number + " ");
            }
            
            System.out.println();
        }
    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // pattern1(n);
        // pattern2(n);
        // pattern3(n);
        // pattern4(n);
        pattern22(n);
    }
}
