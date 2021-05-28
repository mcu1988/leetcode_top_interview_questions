package T0287_find_the_duplicate_number;

import java.util.*;

class Solu {

    static class Solution {


        // 方法1
        // 使用二分法确定重复的元素在哪个位置
        // 确定mid后，遍历一次数组，统计<=mid的数量num
        // cnt等于mid，说明在右边
        // cnt>mid，在左边
        // cnt < mid在右边
        // 时间复杂度O(N*log(N)), 空间复杂度O(1)
        public int findDuplicate1(int[] nums) {
            if(nums == null || nums.length == 0)
                return -1;

            int l = 1, r = nums.length-1;
            while(l < r) {
                int mid = (l + r) / 2;
                int cnt = 0;
                // 遍历整个数组，统计<=mid的数量
                for(int num : nums) {
                    if(num <= mid) cnt++;
                }
                // cnt等于mid，说明在右边
                // cnt>mid，在左边
                // cnt < mid在右边
                if(cnt <= mid) l = mid + 1;
                else r = mid;
            }
            return l;
        }

        // 方法2
        // 数组取值范围刚好在数组下标范围内，按照nums[nums[i]]不断读取，把数组串成链表
        // 由于数组有重复值，因此链表有环，找到环的入口
        // 时间复杂度O(N), 空间复杂度O(1)
        public int findDuplicate(int[] nums) {
            if(nums == null || nums.length == 0)
                return -1;

            // 两指针从0位置出发，各先往前走一个位置
            int slow = nums[0], fast = nums[nums[0]];
            while(slow != fast) {
                slow = nums[slow];
                fast = nums[nums[fast]];
            }

            // 快指针重新回到0位置，两指针每次走一步，相等时就是环的入口
            // 快指针重新回到0位置，两指针每次走一步，相等时就是环的入口
            fast = 0;
            while(slow != fast) {
                slow = nums[slow];
                fast = nums[fast];
            }
            return slow;
        }


    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {2,2,2,2,2};

        app.findDuplicate(nums);
    }
}
