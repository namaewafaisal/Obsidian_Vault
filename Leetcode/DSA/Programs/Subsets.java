import java.util.ArrayList;
import java.util.List;

public class Subsets {
    static void subsets(int[] nums, int index, List<Integer> curr) {
        if (index == nums.length) {
            System.out.println(curr);
            return;
        }

        // include
        curr.add(nums[index]);
        subsets(nums, index + 1, curr);

        // undo
        curr.remove(curr.size() - 1);

        // exclude
        subsets(nums, index + 1, curr);
    }
    public static void main(String[] args) {
        subsets(new int[]{1,2,3}, 0, new ArrayList<>());
    }

}