package T0322_coin_change;

class Solu {

    static class Solution {

        // 方法1
        // 使用递归函数，依次计算每个位置开始要完成left钱数使用的最少硬币个数，向下递归
        public int coinChange1(int[] coins, int amount) {
            if(coins == null || coins.length == 0 || amount < 0)
                return -1;

            return process(coins, 0, amount);
        }

        // 递归函数表示从当前位置index开始的硬币做成left钱数的需要的最少硬币数量
        // index：当前来到的位置
        // left: 剩余的钱数
        // 如果存在有效方案，返回最少的硬币数量；没有有效方案，返回-1
        public int process(int[] coins, int index, int left) {
            // 剩余数量等于0，返回0
            if(left == 0) return 0;
            // left为负，无效方案，返回-1
            if(left < 0) return -1;

            // 硬币的种类用完了，left还是>0, 当前方案无效，返回-1
            if(index == coins.length) return -1;

            int result = -1;
            for(int i=0; i*coins[index]<=left; i++) { // 当前位置的硬币用多少个
                int tmp = process(coins, index+1, left - i*coins[index]);
                // 所有方案取最小
                if(tmp != -1) {
                    if(result != -1) result = Math.min(result, tmp+i);
                    else result = tmp + i;
                }
            }

            return result;
        }

        // 方法2
        // 方法1中的递归函数有很多次重复计算
        // 使用数组缓存结果，减少重复计算
        // 使用递归函数，依次计算每个位置要使用的硬币的个数，向下递归
        public int coinChange2(int[] coins, int amount) {
            if(coins == null || coins.length == 0 || amount < 0)
                return -1;

            int[][] dp = new int[coins.length+1][amount+1];

            return process1(coins, 0, amount, dp);
        }

        // 递归函数表示从当前位置index开始的硬币做成left钱数的需要的最少硬币数量
        // index：当前来到的位置
        // left: 剩余的钱数
        public int process1(int[] coins, int index, int left, int[][] dp) {

            if(dp[index][left] != 0) return dp[index][left];

            // 剩余数量等于0，返回当前有效方案的cnt
            if(left == 0) {
                dp[index][left] = 0;
                return dp[index][left];
            }
            // left为负，无效方案，返回-1
            if(left < 0) {
                dp[index][left] = -1;
                return dp[index][left];
            }

            // 硬币的种类用完了，left还是>0, 当前方案无效，返回-1
            if(index == coins.length) {
                dp[index][left] = -1;
                return dp[index][left];
            }

            int result = -1;
            for(int i=0; i*coins[index]<=left; i++) { // 当前位置的硬币用多少个
                int tmp = process1(coins, index+1, left - i*coins[index], dp);
                // 所有方案取最小
                if(tmp != -1) {
                    if(result != -1) result = Math.min(result, tmp+i);
                    else result = tmp + i;
                }
            }

            dp[index][left] = result;
            return dp[index][left];
        }

        // 方法3
        // 使用dp，dp[i][j]表示从数组i开始的硬币组成钱数j的最少硬币数量
        // 转移方程 dp[i][j] = min( dp[i+1][j-k*nums[i]] + k)
        // 转移方程进一步简化 dp[i][j] = min( dp[i+1][j-nums[i]] + 1， dp[i+1][j])
        // 时间复杂度O(N*aim), 空间复杂度O(N*aim)
        public int coinChange3(int[] coins, int amount) {
            if(coins == null || coins.length == 0 || amount < 0)
                return -1;

            if(amount == 0) return 0;

            int N = coins.length;
            int[][] dp = new int[N+1][amount+1];

            // 初始状态
            dp[N][0] = 0;
            for(int i=1; i<=amount; i++) dp[N][i] = -1;
            for(int i=0; i<=N; i++) dp[i][0] = 0;

            // 行从后往前遍历，列从前往后遍历
            for(int i=N-1; i>=0; i--) {
                for(int j=1; j<=amount; j++) {
                    dp[i][j] = dp[i+1][j];
                    if(j - coins[i] >= 0 && dp[i][j - coins[i]] != -1)
                        dp[i][j] = dp[i][j] == -1 ? dp[i][j - coins[i]] + 1
                                : Math.min(dp[i][j], dp[i][j - coins[i]] + 1);
                }
            }
            return dp[0][amount];
        }

        // 方法4
        // 思路和方法3一致
        // 转移方程进一步简化 dp[i][j] = min( dp[i+1][j-nums[i]] + 1， dp[i+1][j])
        // 对状态进一步压缩，dp[i][j]只依赖于dp[i+1]行，把dp压缩到一行数据
        // 时间复杂度O(N*aim), 空间复杂度O(aim)
        public int coinChange(int[] coins, int amount) {
            if(coins == null || coins.length == 0 || amount < 0)
                return -1;

            if(amount == 0) return 0;

            int N = coins.length;
            int[] dp = new int[amount+1];

            // 初始状态， dp[N][0] = 0
            dp[0] = 0;
            for(int i=1; i<=amount; i++) dp[i] = -1;

            // 行从后往前遍历，列从前往后遍历
            for(int i=N-1; i>=0; i--) {
                for(int j=1; j<=amount; j++) {
                    if(j - coins[i] >= 0 && dp[j - coins[i]] != -1)
                        dp[j] = dp[j] == -1 ? dp[j - coins[i]] + 1
                                : Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
            return dp[amount];
        }


    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] coins = {1,2,5};
        int amount = 11;

        app.coinChange1(coins, amount);

        System.out.println(app.coinChange(coins, amount));

    }
}
