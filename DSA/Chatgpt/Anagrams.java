import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SolutionAnagram {
    public List<Integer> findAnagrams(String s, String p) {
        
        int left = 0;
        int right = 0;
        List<Integer> result = new ArrayList<>();

        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> pMap = new HashMap<>();

        int m = s.length();
        int n = p.length();

        int required;
        int satisfied = 0;

        for(int i = 0; i<n; i++){
            char curr = p.charAt(i);
            pMap.put(curr, pMap.getOrDefault(curr,0)+1);
        }
        required = pMap.size();

        while(right<m) {
            char curr = s.charAt(right);
            sMap.put(curr, sMap.getOrDefault(curr,0)+1);
            if(pMap.containsKey(curr) && sMap.get(curr) == pMap.get(curr)) {
                satisfied++;
            }

            while(right - left + 1 > n){
                char chl = s.charAt(left++);
                if(pMap.containsKey(chl) && pMap.get(chl).equals(sMap.get(chl))){
                    satisfied--;
                }
                sMap.put(chl, sMap.get(chl)-1);
            }
            if(satisfied == required) {
                result.add(left);
            }
            


            right++;
        }
        return result;

    }
}
public class Anagrams {
    public static void main(String[] args) {
        SolutionAnagram sol = new SolutionAnagram();

        String s = "vwwvv";
        String p = "vwv";
        sol.findAnagrams(s, p);
    }
}
