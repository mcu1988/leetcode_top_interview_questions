package T0200_number_of_islands;

class Solu {

    static class Solution {

        // 方法
        // 遍历矩阵，遇到1，计数加1，并且把这个1周围的1全部改成2
        // 时间复杂度O(M*N), 空间复杂度O(M*N)
        public int numIslands(char[][] grid) {
            if(grid == null || grid.length == 0)
                return 0;

            int m = grid.length, n = grid[0].length;
            int result = 0;
            for(int i=0; i<m; i++) { // 遍历矩阵
                for(int j=0; j<n; j++) {
                    if(grid[i][j] == '1') { // 遇到1，计数加1，并且把周围的1全部变成2
                        result += 1;
                        process(grid, i, j);
                    }
                }
            }
            return result;
        }

        // 从当前位置的1开始往上下左右遍历，把周围的1全部改成2
        public void process(char[][] grid, int i, int j) {
            // 索引越界，返回
            if(i < 0 || i >= grid.length || j < 0 || j>= grid[0].length)
                return;

            // 遇到不是1的位置，返回
            if(grid[i][j] != '1') return;

            grid[i][j] = '2';

            // 遍历上下左右
            process(grid, i-1, j);
            process(grid, i+1, j);
            process(grid, i, j-1);
            process(grid, i, j+1);
        }

    }


    public static void main(String[] args) {

        Solution app = new Solution();

        char[][] grid = {{'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}};

        System.out.println(app.numIslands(grid));

    }
}
