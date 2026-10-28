
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class UpdateMatrix {

    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    mat[i][j] = -1;
                } else {
                    q.add(new int[] {i, j});
                }
            }
        }

        int[][] directions = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
        };

        while (!q.isEmpty()) {
            int[] cell = q.poll();

            for (int[] dir : directions) {
                int nr = dir[0] + cell[0];
                int nc = dir[1] + cell[1];

                if (nr >= 0 && nr < m &&
                    nc >= 0 && nc < n &&
                    mat[nr][nc] == -1) {

                    mat[nr][nc] = mat[cell[0]][cell[1]] + 1;
                    q.add(new int[] {nr, nc});
                }
            }
        }

        return mat;
    }

    public static void main(String[] args) {
        UpdateMatrix obj = new UpdateMatrix();
        Scanner sc = new Scanner(System.in);

        int m = 3;
        int n = 3;

        // Option 1: Read the matrix from console
        /*
        int[][] mat = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = sc.nextInt();
            }
        }
        */

        // Option 2: Hardcode the matrix for debugging
        int[][] mat = {
            {0, 0, 0},
            {0, 1, 0},
            {1, 1, 1}
        };

        System.out.println("Input matrix:");
        printMatrix(mat);

        int[][] result = obj.updateMatrix(mat);

        System.out.println("\nDistance matrix:");
        printMatrix(result);

        sc.close();
    }

    public static void printMatrix(int[][] mat) {
        for (int[] row : mat) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
