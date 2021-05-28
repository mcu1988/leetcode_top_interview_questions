package T0289_game_of_life;

class Solu {

    static class Solution {


        // 方法1
        // 每个格子里的类型是整型，利用整型的倒数第2个bit存放新生成的信息
        // 依次遍历矩阵，查看每个格子周围8个格子中1的个数
        // 时间复杂度O(M*N), 空间复杂度O(1)
        public void gameOfLife(int[][] board) {
            if(board == null || board.length == 0 || board[0].length == 0)
                return;

            int m = board.length, n = board[0].length;
            for(int i=0; i<m; i++) {
                for(int j=0; j<n; j++) {
                    int cnt = cntOne(board, i, j);
                    // 把倒数第2个bit设置为1
                    if(((board[i][j] == 0) && cnt == 3)
                        || (board[i][j] == 1 && (cnt == 2 || cnt == 3))) {
                        board[i][j] |= 2;
                    }
                }
            }

            // 第2个位置的状态更新到第1个位置
            for(int i=0; i<m; i++) {
                for(int j=0; j<n; j++) {
                    board[i][j] >>= 1;
                }
            }

        }

        // 统计周围8个格子中1的个数
        public int cntOne(int[][] board, int i, int j) {
            int cnt = 0;
            cnt += isOne(board, i-1, j-1) ? 1 : 0;
            cnt += isOne(board, i-1, j) ? 1 : 0;
            cnt += isOne(board, i-1, j+1) ? 1 : 0;
            cnt += isOne(board, i, j-1) ? 1 : 0;
            cnt += isOne(board, i, j+1) ? 1 : 0;
            cnt += isOne(board, i+1, j-1) ? 1 : 0;
            cnt += isOne(board, i+1, j) ? 1 : 0;
            cnt += isOne(board, i+1, j+1) ? 1 : 0;
            return cnt;
        }

        public boolean isOne(int[][] board, int i, int j) {
            return i >=0 && i < board.length && j >= 0 && j < board[0].length
                    && ((board[i][j] & 1) == 1);
        }

        public void printMatrix(int[][] board) {
            for(int i=0; i<board.length; i++) {
                for(int j=0; j<board[0].length; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[][] board = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};

        app.printMatrix(board);
        app.gameOfLife(board);
        app.printMatrix(board);
    }
}
