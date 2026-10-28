
import java.util.Scanner;

public class Enclaves {
    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if((i == 0 || i == m-1 || j == 0 || j == n-1) && grid[i][j] == 1){
                    dfs(i,j,grid);
                }
            }
        }
        int count = 0;
        for(int i = 1; i < m-1; i++) {
            for(int j = 1; j < n-1; j++) {
                if(grid[i][j] == 1){
                    count++;
                }
            }
        }
        return count;
    }
    public void dfs(int r, int c, int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if(r < 0 || r >= m) return;
        if(c < 0 || c >= n) return;
        if(grid[r][c] == 0) return;

        grid[r][c] = 0;
        dfs(r - 1,c, grid);
        dfs(r + 1,c, grid);
        dfs(r,c - 1, grid);
        dfs(r,c + 1, grid);
    }
    
    public static void main(String[] args) {
        Enclaves obj = new Enclaves();
        Scanner sc = new Scanner(System.in);
        int m = 4;
        int n = 4;
        // int[][] grid = new int[m][n];

        // for (int i = 0; i < m; i++) {
        //     for (int j = 0; j < n; j++) {
        //         grid[i][j] = sc.nextInt();
        //     }
        // }
        int[][] grid = {
            {0, 0, 0, 0},
            {1, 0, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0}
        };

        System.out.println(obj.numEnclaves(grid));
    }
}