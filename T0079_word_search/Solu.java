package T0079_word_search;

import java.util.ArrayList;
import java.util.List;

class Solu {

    static class Solution {

        // 方法1
        // 遍历矩阵，依次从每个位置递归匹配word
        // 递归函数中往上下左右四个方向扩展，进行匹配
        // 由于不能走重复路径，需要使用二维数组记录每个位置是否到达过
        // 时间复杂度上界O(m*n*4^k), m, n是矩阵维度，k是word的长度
        public boolean exist1(char[][] board, String word) {
            if(board == null || board.length == 0 || word == null || word.length() == 0)
                return false;

            int m = board.length, n = board[0].length;
            boolean[][] isReached = new boolean[m][n];
            for(int i=0; i<m; i++) {
                for(int j=0; j<n; j++) {
                    // 依次从每个位置开始递归
                    if(process(board, i, j, word, 0, isReached)) return true;
                }
            }
            // 所有情况都尝试完欧虎，返回false
            return false;
        }

        // 递归函数，返回从board[i][j]开始和word[k]开始是否匹配成功
        // i, j是矩阵维度, k是word来到的位置
        // isReached记录每个位置是否到达过
        public boolean process(char[][] board, int i, int j, String word, int k, boolean[][] isReached) {
            // word遍历完了，匹配成功
            if(k == word.length()) return true;
            // i， j越界，匹配失败
            if(i < 0 || i >= board.length || j < 0 || j >= board[0].length)
                return false;
            // i, j位置已经到达过，匹配失败
            if(isReached[i][j]) return false;

            // 矩阵i，j位置和word的k位置不一致，匹配失败
            if(board[i][j] != word.charAt(k)) return false;

            // 矩阵i，j位置和word的k位置一致，继续向下匹配

            // 标记i，j位置到达过
            isReached[i][j] = true;

            // 依次尝试上下左右位置，成功即返回true，不在继续尝试
            if(process(board, i-1, j, word, k+1, isReached)) return true;
            if(process(board, i+1, j, word, k+1, isReached)) return true;
            if(process(board, i, j-1, word, k+1, isReached)) return true;
            if(process(board, i, j+1, word, k+1, isReached)) return true;

            // 所有情况尝试后，退递归，i，j位置标记为没访问过
            isReached[i][j] = false;

            // 所有情况尝试后，匹配失败
            return false;
        }

        // 方法2
        // 和方法1中的思路一致
        // 由于不能走重复路径，这里不在使用微微数组标记是否走过，而是把走过的路径的值全部改为字符'0'，这样到达重复位置，匹配失败，直接返回
        // 时间复杂度上界O(m*n*4^k), m, n是矩阵维度，k是word的长度
        public boolean exist(char[][] board, String word) {
            if(board == null || board.length == 0 || word == null || word.length() == 0)
                return false;

            int m = board.length, n = board[0].length;
            for(int i=0; i<m; i++) {
                for(int j=0; j<n; j++) {
                    // 依次从每个位置开始递归
                    if(process1(board, i, j, word, 0)) return true;
                }
            }
            // 所有情况都尝试完欧虎，返回false
            return false;
        }

        // 递归函数，返回从board[i][j]开始和word[k]开始是否匹配成功
        // i, j是矩阵维度, k是word来到的位置
        // 已经遍历过的位置的值改写为'0', 第二次到达时，匹配失败，直接返回
        public boolean process1(char[][] board, int i, int j, String word, int k) {
            // word遍历完了，匹配成功
            if(k == word.length()) return true;
            // i， j越界，匹配失败
            if(i < 0 || i >= board.length || j < 0 || j >= board[0].length)
                return false;

            // 矩阵i，j位置和word的k位置不一致，匹配失败
            if(board[i][j] != word.charAt(k)) return false;

            // 矩阵i，j位置和word的k位置一致，继续向下匹配

            // 已经走过的位置标记为'0'，这样第二次到达时，就会匹配失败，直接返回
            char tmp = board[i][j];
            board[i][j] = '0';

            // 依次尝试上下左右位置，成功即返回true，不在继续尝试
            if(process1(board, i-1, j, word, k+1)) return true;
            if(process1(board, i+1, j, word, k+1)) return true;
            if(process1(board, i, j-1, word, k+1)) return true;
            if(process1(board, i, j+1, word, k+1)) return true;

            // 所有情况尝试后，退递归，i，j位置的值恢复
            board[i][j] = tmp;

            // 所有情况尝试后，匹配失败
            return false;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";

        app.exist(board, word);

        System.out.println(app.exist(board, word));

    }
}
