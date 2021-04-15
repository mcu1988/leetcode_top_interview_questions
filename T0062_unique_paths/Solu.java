package T0062_unique_paths;

class Solu {

    static class Solution {

        // 方法1
        // 动态规划
        // 建立二维表 dp = new int[m][n], dp[i][j]表示（0，0）走到（i，j）的方法数
        // 递推关系式 dp[i][j] = dp[i-1][j] + dp[i][j-1]
        // 初始时第0行和第0列值是1
        // 时间复杂度O(m*n), 空间复杂度O(m*n)
        public int uniquePaths1(int m, int n) {
            if(m < 1 || n < 1) return 0;

            // 简历二维dp表，dp[i][j]表示（0，0）走到（i，j）的方法数
            int[][] dp = new int[m][n];
            // 第0行和第0列初始为1
            for(int i=0; i<n; i++) dp[0][i] = 1;
            for(int i=0; i<m; i++) dp[i][0] = 1;

            // 从第1行第1列开始遍历
            for(int i=1; i<m; i++) {
                for(int j=1; j<n; j++) {
                    // 当前位置的方法数时左边和上边的方法数和
                    dp[i][j] = dp[i][j-1] + dp[i-1][j];
                }
            }
            return dp[m-1][n-1];
        }


        // 方法2
        // 方法1中的空间压缩为1行
        // 递推关系式 dp[i][j] = dp[i-1][j] + dp[i][j-1]
        // dp[i][j]依赖于左边和右边的值，因此可以把dp压缩为1维
        // 时间复杂度O(m*n), 空间复杂度O(m)
        public int uniquePaths(int m, int n) {
            if(m < 1 || n < 1) return 0;

            // 简历二维dp表，dp[i][j]表示（0，0）走到（i，j）的方法数
            // 压缩为1行，也可以压缩为1列
            int[] dp = new int[n];
            // 第0行初始为1
            for(int i=0; i<n; i++) dp[i] = 1;

            // 从第1行第1列开始遍历
            for(int i=1; i<m; i++) {
                for(int j=1; j<n; j++) {
                    // 当前位置的方法数时左边和上边的方法数和
                    dp[j] = dp[j-1] + dp[j];
                }
            }
            return dp[n-1];
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int m = 3, n = 7;

        System.out.println(app.uniquePaths(m, n));

    }
}
