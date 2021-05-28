package T0268_missing_number;

import java.util.*;

class Solu {

    static class Solution {


        // 方法1
        // 利用异或操作的特性，把0~n的结果异或，再异或上数组所有的值，可以求出缺失值
        // 时间复杂度O(N), 空间复杂度O(1)
        public int missingNumber(int[] nums) {
            if(nums == null || nums.length == 0)
                return -1;
            int N = nums.length;
            int result = N; // 初始为N
            for(int i=0; i<N; i++) {
                result = result ^ i ^nums[i];
            }
            return result;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {3,0,1};

        app.missingNumber(nums);
        System.out.println(app.missingNumber(nums));
    }
}
