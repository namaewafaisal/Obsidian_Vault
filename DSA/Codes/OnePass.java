class Solution {
    public void sortColors(int[] nums) {
        // Territory boundaries
        int low = 0;          // Next available spot for a 0
        int mid = 0;          // The scanning "eye"
        int high = nums.length - 1; // Next available spot for a 2

        while (mid <= high) {
            if (nums[mid] == 0) {
                // Belong at the front (Red Zone)
                swap(nums, mid, low);
                low++;
                mid++;
            } 
            else if (nums[mid] == 1) {
                // Already in the middle (White Zone)
                mid++;
            } 
            else { // nums[mid] == 2
                // Belong at the back (Blue Zone)
                swap(nums, mid, high);
                high--;
                // IMPORTANT: We do NOT increment mid here.
                // We need to examine the element we just swapped from the back.
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
public class OnePass {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {1,0,2,0,1,2};
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        sol.sortColors(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
