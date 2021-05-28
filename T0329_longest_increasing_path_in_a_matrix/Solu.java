package T0329_longest_increasing_path_in_a_matrix;

class Solu {

    static class Solution {

        // 方法1
        // 遍历二维矩阵，以当前位置为出发点，向四周遍历，找到四周的最大值，再加上1作为当前位置的最大递增长度
        // 使用二维数组，dp[i][j]表示从位置i，j出发的最长递增长度
        // 时间复杂度O(M*N), 空间复杂度O(M*N)
        public int longestIncreasingPath(int[][] matrix) {
            if(matrix == null || matrix.length == 0)
                return 0;

            int m = matrix.length, n = matrix[0].length;
            int result = 0;
            int[][] dp = new int[m][n];
            for(int i=0; i<m; i++) { // 遍历二维数组的每一个位置
                for(int j=0; j<n; j++) {
                    result = Math.max(result, process(matrix, i, j, dp));
                }
            }
            return result;
        }

        // 递归函数，返回i，j位置开始的最长递增序列的长度
        // dp[i][j]表示从i，j位置开始的最长递增序列的长度
        public int process(int[][] matrix, int i, int j, int[][] dp) {

            // dp[i][j]已经遍历过了，直接返回答案
            if(dp[i][j] != 0) return dp[i][j];

            // 向四周遍历找到最大值
            int result = 0;
            if(i-1 >= 0 && matrix[i-1][j] > matrix[i][j]) result = Math.max(result, process(matrix, i-1, j, dp));
            if(i+1 < matrix.length && matrix[i+1][j] > matrix[i][j]) result = Math.max(result, process(matrix, i+1, j, dp));
            if(j-1 >= 0 && matrix[i][j-1] > matrix[i][j]) result = Math.max(result, process(matrix, i, j-1, dp));
            if(j+1 < matrix[0].length && matrix[i][j+1] > matrix[i][j]) result = Math.max(result, process(matrix, i, j+1, dp));

            dp[i][j] = result + 1;
            return result + 1;
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[][] matrix = {{9,9,4},{6,6,8},{2,1,1}};
        app.longestIncreasingPath(matrix);
        System.out.println(app.longestIncreasingPath(matrix));

    }
}
