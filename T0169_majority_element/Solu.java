package T0169_majority_element;

import java.util.HashMap;

class Solu {

    static class Solution {

        // 方法
        // 出现最多的次数超过了 N / 2
        // 设置一个变量记录最多的次数，当遇到不同的元素时减1，减到0时，出现最多的元素更换
        // 最终遍历结束时，出现最多的元素的cnt > 0
        // 时间复杂度O(N), 空间复杂度(1)
        public int majorityElement(int[] nums) {
            if(nums == null || nums.length == 0)
                return -1;

            // cur表示当前个数最多个元素，cnt表示cur的次数
            int cnt = 1, cur = nums[0];
            for(int i=1; i<nums.length; i++) { // 从1开始遍历
                // 遇到不同元素减1，相同元素加1
                if(nums[i] != cur) cnt--;
                else cnt++;
                // 减到-1时，更新出现次数最多的元素
                if(cnt == -1) {
                    cur = nums[i];
                    cnt = 1;
                }
            }
            return cur;
        }
    }


    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {3,2,3};
        app.majorityElement(nums);
        System.out.println(app.majorityElement(nums));

    }
}
