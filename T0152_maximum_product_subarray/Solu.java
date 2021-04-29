package T0152_maximum_product_subarray;

import java.util.HashMap;
import java.util.Stack;

class Solu {

    static class Solution {

        // 方法
        // 记录以i位置结尾的子数组的最小值和最大值
        // 负数和负数的成绩可能变成最大值，因此，要同时记录最小值和最大值
        // 当前位置结尾的子数组的最小值可能有3种情况：只有当前元素；min * nums[i]; max * nums[i]
        // 最大值同理
        // 时间复杂度O(N), 空间复杂度O(1)
        public int maxProduct(int[] nums) {
            if(nums == null || nums.length == 0)
                return 0;

            int result = nums[0];
            // min, max记录以i位置结尾的最小值和最大值，初始值是第一个元素
            int min = nums[0], max = nums[0];
            for(int i=1; i<nums.length; i++) {
                int a = min * nums[i];
                int b = nums[i];
                int c = max * nums[i];
                min = min3(a, b, c);
                max = max3(a, b, c);
                // 负数和负数可能得到最大值，因此要同时记录最小值和最大值
                result = Math.max(result, max);
            }
            return result;
        }

        public int max3(int a, int b, int c) {
            return Math.max(Math.max(a, b), c);
        }

        public int min3(int a, int b, int c) {
            return Math.min(Math.min(a, b), c);
        }

    }


    public static void main(String[] args) {
        Solution app = new Solution();

        int[] nums = {2,3,-2,4};

        app.maxProduct(nums);

        System.out.println(app.maxProduct(nums));
    }
}
