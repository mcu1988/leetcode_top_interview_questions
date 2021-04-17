package T0070_climbing_stairs;

import java.util.Arrays;

class Solu {

    static class Solution {

        // 方法1
        // dp, dp[i]表示到达i位置的方式
        // 递推公式 dp[i] = dp[i-1] + dp[i-2]
        // 时间复杂度O(N), 空间复杂度O(N)
        public int climbStairs1(int n) {
            if(n <= 0) return 0;

            if(n == 1) return 1;

            int[] dp = new int[n];
            dp[0] = 1;
            dp[1] = 2;

            for(int i=2; i<n; i++) {
                dp[i] = dp[i-1] + dp[i-2];
            }
            return dp[n-1];
        }

        // 方法2
        // 压缩方法1的空间
        // 递推公式 dp[i] = dp[i-1] + dp[i-2]， 使用2个变量记录即可
        // 时间复杂度O(N), 空间复杂度O(1)
        public int climbStairs2(int n) {
            if(n <= 0) return 0;

            if(n == 1) return 1;

            // n = 1时的取值
            int first = 1;
            // n == 2时的取值
            int second = 2;
            // result默认为n=2时的结果
            int result = second;
            for(int i=2; i<n; i++) {
                result = first + second;
                first = second;
                second = result;
            }
            return result;
        }

        // 思路3
        // 斐波拉切数列的快速幂解法
        // 使用矩阵表达出递推关系式
        // 使用快速幂解决矩阵的n次方问题
        public int climbStairs(int n) {
            if(n <= 0) return 0;
            if(n == 1) return 1;
            if(n == 2) return 2;

            int mi = n - 1; // 矩阵的指数幂
            int[][] result = {{1, 0}, {0, 1}}; // 初始化为单位矩阵
            int[][] jie = {{1, 1}, {1, 0}}; // 阶数
            for(; mi>0; mi>>=1) { // 幂二分拆解
                if((mi & 1) == 1) { // 当前位是1
                    result = matrixMulti(result, jie);
                }
                jie = matrixMulti(jie, jie); // 阶数每次平方，1，2，4，8递增
            }
            return result[0][0] + result[1][0];
        }

        // 2个2阶矩阵相乘
        public int[][] matrixMulti(int[][] a, int[][] b) {
            int m1 = a.length, n1 = a[0].length;
            int m2 = b.length, n2 = b[0].length;

            if(n1 != m2) return null;

            int[][] result = new int[m1][n2];
            for(int i=0; i<m1; i++) {
                for(int j=0; j<n2; j++) {
                    for(int k=0; k<n1; k++) {
                        result[i][j] += a[i][k] * b[k][j];
                    }
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int n = 3;

        app.climbStairs(n);

        System.out.println(app.climbStairs(n));

    }
}
