package T0055_jump_game;

import java.util.ArrayList;
import java.util.List;

class Solu {

    static class Solution {

        // 方法
        // 遍历字符串，记录能跳到的最远的位置max
        // 如果max >= nums.length, return true
        // 如果当前位置i>max，return false
        // 时间复杂度O(N), 空间复杂度O(1)
        public boolean canJump(int[] nums) {
            if(nums == null || nums.length < 2) return true;

            int max = 0; // 记录能跳到的最远的位置
            for(int i=0; i<nums.length; i++) {
                if(i > max) return false; // 当前遍历位置超过了max，return false
                if(max >= nums.length-1) return true; // max>=最后一个位置时，return true
                max = Math.max(max, i + nums[i]);
            }
            return false;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {2,3,1,1,4};

        System.out.println(app.canJump(nums));

    }
}
