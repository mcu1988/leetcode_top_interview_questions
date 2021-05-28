package T0300_longest_increasing_subsequence;

class Solu {

    static class Solution {

        // 方法1
        // 使用dp数组，dp[i]表示以nums[i]结尾的最长递增子序列的长度
        // 考虑当前位置nums[i], 依次与0~i-1位置相比较，记录最大长度
        // 如果nums[i] > nums[j]， i位置是dp[j]+1，否则是1
        // 使用最大值变量不断与dp[i]比较，并更新
        // 时间复杂度O(N^2), 空间复杂度O(N)
        public int lengthOfLIS1(int[] nums) {
            if(nums == null || nums.length == 0)
                return 0;

            int N = nums.length;
            // dp[i]表示以nums[i]结尾的最长递增子序列的长度
            int[] dp = new int[N];
            dp[0] = 1;

            int result = 1;
            for(int i=1; i<N; i++) { // 从1位置开始遍历
                for(int j=0; j<i; j++) { // 与0~i-1位置相比较
                    // 如果nums[i] > nums[j]， i位置是dp[j]+1，否则是1
                    dp[i] = Math.max(dp[i], nums[i] > nums[j] ? dp[j]+1 : 1);
                }
                result = Math.max(result, dp[i]);
            }
            return result;
        }

        // 方法2
        // 使用dp数组，dp[i]表示长度为i的数组的子序列的末尾值，dp数组的值是递增的
        // 考虑当前位置nums[i], 使用二分法查找nums[i]在dp数组的位置j, j位置的原始值是长度相等但比nums[i]大的值，更新dp[j]位置的值 = nums[i]
        // dp数组中同一长度的位置，使用尾部值更小的来覆盖前面的
        // 最终dp数组的有效的不为0的长度就是最长递增子序列
        // 时间复杂度O(N*log(N)), 空间复杂度O(N)
        public int lengthOfLIS(int[] nums) {
            if(nums == null || nums.length == 0)
                return 0;

            int N = nums.length;
            // dp[i]表示长度为i的数组的子序列的末尾值，dp数组的值是递增的
            // 初始dp[1]=nums[0], dp[0]位置弃用
            int[] dp = new int[N+1];
            dp[1] = nums[0];
            int maxLen = 1;

            for(int i=1; i<N; i++) { // 从1开始遍历
                // 找到nums[i]对应的长度的位置index，更新该位置的值为nums[i]
                // 如果nums[i]比数组中所有值都小，返回index=1
                // 如果nums[i]等于数组中的某个值，返回index=相等位置的索引
                // 如果nums[i]介于数组中两个值之间，返回index= 比nums[i]大的第一个位置的索引
                // 如果nums[i]比数组中所有值都大，返回index=maxLen+1
                int index = binarySearch(dp, 1, maxLen, nums[i]);
                dp[index] = nums[i];
                // 最长长度有更新，maxLen++
                if(index == maxLen+1) maxLen++;
            }

            return maxLen;
        }

        // 二分搜索
        // dp数组是严格递增的
        // l, r是二分搜索的区间
        // v是目标值
        public int binarySearch(int[] dp, int l, int r, int v) {
            int mid = 0;
            while(l <= r) {
                mid = (l + r) / 2;
                if(dp[mid] == v) return mid;
                else if(dp[mid] < v) l = mid + 1;
                else r = mid - 1;
            }
            return l;
        }


    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {3,5,6,2,5,4,19,5,6,7,12};

        app.lengthOfLIS(nums);

        System.out.println(app.lengthOfLIS(nums));

    }
}
