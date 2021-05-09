package T0198_house_robber;

class Solu {

    static class Solution {

        // 方法1
        // 建立dp数组，dp[i]表示0...i最大的数量
        // dp[i] = max(dp[i-2]+nums[i], dp[i-1])
        // 初始时设置dp[0]=0, dp[1] = nums[0]
        // 时间复杂度O(N), 空间复杂度O(N)
        public int rob1(int[] nums) {
            if(nums == null || nums.length == 0)
                return 0;

            int N = nums.length;
            int[] dp = new int[N+1];
            // 初始设置dp[0]和dp[1]
            dp[1] = nums[0];

            for(int i=1; i<N; i++) {
                dp[i+1] = Math.max(dp[i-1] + nums[i], dp[i]);
            }
            return dp[N];
        }

        // 方法2
        // 方法1中的dp[i]只依赖dp[i-1]和dp[i-2], 因此可以只用2个变量
        // 时间复杂度O(N), 空间复杂度O(1)
        public int rob(int[] nums) {
            if(nums == null || nums.length == 0)
                return 0;

            int N = nums.length;
            // first表示dp[i-2], second表示dp[i-1], third表示dp[i]
            int first = 0, second = nums[0], third = second;

            for(int i=1; i<N; i++) {
                third = Math.max(first + nums[i], second);
                first = second;
                second = third;
            }
            return third;
        }

    }


    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {1,2,3,1};
        app.rob(nums);
        System.out.println(app.rob(nums));

    }
}
