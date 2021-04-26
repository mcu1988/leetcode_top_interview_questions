package T0136_single_number;

import java.util.ArrayList;
import java.util.List;

class Solu {

    static class Solution {

        // 方法
        // 2个相同的数字异或结果是0，0和任意数字异或结果是其本身
        // 把数组所有数字异或起来，就是出现1次的数字
        // 时间复杂度O(N^2), 空间复杂度O(1)
        public int singleNumber(int[] nums) {
            if(nums == null || nums.length == 0) return Integer.MIN_VALUE;

            int result = 0;
            for(int num : nums) result ^= num;
            return result;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {2,2,1};

        app.singleNumber(nums);
        System.out.println(app.singleNumber(nums));
    }
}
