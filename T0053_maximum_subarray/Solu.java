package T0053_maximum_subarray;

class Solu {

    static class Solution {

        // 方法1
        // 建立数组的前缀和数组preSum
        // 使用2层循环确定start和end位置，preSum[j] - preSum[i]就是子数组和
        // 时间复杂度O(n^2), 空间复杂度O(n)
        public int maxSubArray1(int[] nums) {
            if(nums == null || nums.length == 0) return 0;

            // preSum[0] = 0
            int[] preSum = new int[nums.length + 1];
            for(int i=1; i<preSum.length; i++) {
                preSum[i] = preSum[i-1] + nums[i-1];
            }

            int result = Integer.MIN_VALUE, tmp = 0;
            for(int i=0; i<nums.length; i++) {
                for(int j=i; j<nums.length; j++) {
                    // 计算[i, j]范围上的和
                    tmp = preSum[j+1] - preSum[i];
                    if(tmp > result) result = tmp;
                }
            }
            return result;
        }

        // 方法2
        // 考虑以当前位置i结尾的子数组最大和
        // 如果以i-1位置结尾的子数组的最大和preSum<=0, 那么当前位置i结尾的最大和就是nums[i], 否则是preSum+nums[i]
        // 时间复杂度O(n), 空间复杂度O(1)
        public int maxSubArray(int[] nums) {
            if (nums == null || nums.length == 0) return 0;

            int preSum = 0, curSum = 0, result = Integer.MIN_VALUE;
            for(int i=0; i<nums.length; i++) {
                curSum = preSum > 0 ? preSum + nums[i] : nums[i];
                result = Math.max(result, curSum);
                preSum = curSum;
            }
            return result;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {5,4,-1,7,8};
        System.out.println(app.maxSubArray(nums));

    }
}
