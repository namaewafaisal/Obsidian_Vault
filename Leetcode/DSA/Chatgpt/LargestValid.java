import java.util.HashMap;
import java.util.Map;

class Solution2 {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        int right = 0, left = 0;
        int len = 0;
        int size = s.length();

        while(right < size) {
            char r = s.charAt(right);
            freq.put(r,freq.getOrDefault(r,0)+1);

            while(freq.get(r) > 1) {
                char l = s.charAt(left++);
                freq.put(l, freq.get(l)-1);
                if(freq.get(l) == 0) {
                    freq.remove(l);
                }
            }
            len = Math.max(len, right + 1 - left);
            right++;

            // if(right - left  == freq.size()) {
            //     if(right - left + 1 == freq.size()){
            //         len = Math.max(len,freq.size());
            //     }
            //     right++;
                
            // }
            // else{
                
            //    // right++;
            // }
        }
        return len;
    }


}

public class LargestValid {
    public static void main(String[] args) {
        Solution2 sol = new Solution2();
        System.out.println(sol.lengthOfLongestSubstring("bbbklbbbpol"));
    }
}
