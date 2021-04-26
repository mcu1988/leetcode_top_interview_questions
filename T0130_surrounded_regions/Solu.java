package T0130_surrounded_regions;

class Solu {

    static class Solution {

        // 方法
        // 边界上的'O'无法被包围，只需要把边界上的'O'拓展出去, 'O'改为'A'
        // 剩下的'O'是可以被包围的
        // 重新遍历数组，把'O'改为'X'，把'A'改为'O'
        // 时间复杂度O(M*N), 空间复杂度O(1)
        public void solve(char[][] board) {

            // 行和列不超过2行的直接返回
            if (board == null || board.length < 3 || board[0].length < 3) return;

            int m = board.length, n = board[0].length;

            // 递归遍历第一列和最后一列的'O'，从该点往周围扩展，将'O'改为'A'
            for(int i=0; i<m; i++) {
                if(board[i][0] == 'O') process(board, i, 0);
                if(board[i][n-1] == 'O') process(board, i, n-1);
            }

            // 递归遍历第一行和最后一行的'O', 从该点往周围扩展，将'O'改为'A'
            for(int i=0; i<n; i++) {
                if(board[0][i] == 'O') process(board, 0, i);
                if(board[m-1][i] == 'O') process(board, m-1, i);
            }

            // 把中心的'O'改为'X'
            // 把边界的'A'，改为'O'
            for(int i=0; i<m; i++) {
                for(int j=0; j<n; j++) {
                    if(board[i][j] == 'O') board[i][j] = 'X';
                    if(board[i][j] == 'A') board[i][j] = 'O';
                }
            }

        }

        public void process(char[][] board, int i, int j) {

            // 索引越界，返回
            if(i < 0 || i >= board.length || j < 0 || j >= board[0].length)
                return;

            // 遇到'X'，返回
            // 遇到已经遍历过的'A'，直接返回
            if(board[i][j] == 'X' || board[i][j] == 'A') return;

            // 把边界上的'O'变成'A'
            board[i][j] = 'A';

            // 遍历上下左右的位置
            process(board, i-1, j);
            process(board, i+1, j);
            process(board, i, j-1);
            process(board, i, j+1);
        }

        public void printMatrix(char[][] board) {
            int m = board.length, n = board[0].length;

            for(int i=0; i<m; i++){
                for(int j=0; j<n; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};

        app.printMatrix(board);
        app.solve(board);
        app.printMatrix(board);
    }
}
