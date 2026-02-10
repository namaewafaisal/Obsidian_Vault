import java.util.Arrays;
import java.time.Duration;
import java.time.Instant;
public class DynamicProgramming{

    public static long fib(long n, long[] table){
        if(n == 0) return table[(int) n] = 0;
        if(n == 1) return table[(int) n] = 1;
        if(table[(int) n] != -1) return table[(int) n];
        long result = fib(n-1, table) + fib(n-2, table);

        System.out.print(result + " ");
        return  table[(int) n] = result;
    }

    public static void main(String[] args){
        Instant start = Instant.now();
        long n = 1000;
        long[] table = new long[(int) (n+1)];
        Arrays.fill(table,-1);
        System.out.println(fib(n,table));
        for (long i : table) {
            System.out.print(i + " ");
        }
        System.out.println();
        Instant end = Instant.now();
        Duration d = Duration.between(start, end);
        System.out.println(d.toMillis() + " ms");

    }
}