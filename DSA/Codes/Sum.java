import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution3sum {
    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int left = 0;
        int right = nums.length - 1;

        for(int i = 0; i<nums.length - 2; i++) {
            if(i>0 && nums[i] == nums[i-1]){
                continue;
            }
            left = i+1;
            right = nums.length - 1;
            while(left < right) {
                if(nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                }
                else if(nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                }
                else {
                    result.add(Arrays.asList(nums[i],nums[left], nums[right]));
                    left++;
                    right--;
                }

            }
        }
        return result;
    }
}

public class Sum {
    public static void main(String[] args) {
        Solution3sum sol = new Solution3sum();
        int[] arr = {-1,0,1,2,-1,-4};
        sol.threeSum(arr);
    }
}
