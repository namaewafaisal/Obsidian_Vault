import java.util.*;
public class CharMain {
    public static void main(String[] args) {
        Solution1 sol = new Solution1();
        String s = "xyzzaz";
        System.out.println(sol.countGoodSubstrings(s));
    }
    
}


class Solution1 {
    public int countGoodSubstrings(String s) {
        Map<Character, Integer> freq = new HashMap<>();

        int left = 0, right = 0;
        int count = 0;
        int size = s.length();
        int k = 3;

        while(right < size) {
            char r = s.charAt(right);
            freq.put(r,freq.getOrDefault(r,0)+1);

            if(right + 1 - left == k){
                if(freq.size() == k) {
                    count++;
                }
                char l = s.charAt(left);
                freq.put(l,freq.get(l) - 1);
                if(freq.get(l) == 0)
                    freq.remove(l);
                left++;
            }
            right++;
        } 
        return count;
    }
}