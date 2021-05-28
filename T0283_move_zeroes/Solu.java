package T0283_move_zeroes;

import java.util.*;

class Solu {

    static class Solution {


        // 方法1
        // 遍历一遍，把非0值放在左边，剩下的全部置0
        // 使用left记录左边非0值结束的位置
        // 时间复杂度O(N), 空间复杂度O(N)
        public void moveZeroes1(int[] nums) {
            if(nums == null || nums.length < 2)
                return;
            int left = 0;
            // 非0值放在左边
            for(int i=0; i<nums.length; i++) {
                if(nums[i] != 0) nums[left++] = nums[i];
            }

            // 剩下的全部置0
            for(int i=left; i<nums.length; i++) {
                nums[i] = 0;
            }
        }

        // 方法2
        // 使用left记录左边非0值结束的位置
        // 遍历一遍，遇到非0值，把非0值与left位置交换
        // 时间复杂度O(N), 空间复杂度O(N)
        public void moveZeroes(int[] nums) {
            if (nums == null || nums.length < 2)
                return;

            int left = 0;
            for(int i=0; i<nums.length; i++) {
                if(nums[i] != 0) {
                    int tmp = nums[left];
                    nums[left++] = nums[i];
                    nums[i] = tmp;
                }
            }
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {0,1,0,3,12};

        app.moveZeroes(nums);
    }
}
