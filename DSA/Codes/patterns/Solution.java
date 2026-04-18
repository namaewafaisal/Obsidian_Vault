import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Solution {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {

            // move right
            for (int col = left; col <= right; col++) {
                result.add(matrix[top][col]);
            }
            top++;

            // move down
            for (int row = top; row <= bottom; row++) {
                result.add(matrix[row][right]);
            }
            right--;

            // move left
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    result.add(matrix[bottom][col]);
                }
                bottom--;
            }

            // move up
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    result.add(matrix[row][left]);
                }
                left++;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};
        List<Integer> lst = spiralOrder(arr);
        Iterator it = lst.iterator();
        while(it.hasNext()){
            System.out.print(it.next());
        }
    }
}