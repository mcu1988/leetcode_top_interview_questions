package T0128_longest_consecutive_sequence;

import java.util.*;

class Solu {

    static class Solution {

        // 思路
        // 使用set记录所有数字
        // 依次遍历每个数字，计算每个数字能够扩出去的数量，并从set中删除已经扩展过的数字
        // 时间复杂度O(N), 空间复杂度O(N)
        public int longestConsecutive(int[] nums) {
            if (nums == null || nums.length == 0) return 0;

            // 使用set记录数组
            HashSet<Integer> set = new HashSet<>();
            for(int num : nums) set.add(num);

            int result = 0;
            for(int num : nums) {
                if(set.contains(num)) { // 遍历每个数字，找到新的数字，往左右两边扩展
                    int cnt = 0, tmp = num;
                    // 往左右两边扩展，并计数，已经扩展出去的数字从set中删除
                    while(set.contains(tmp)) {
                        cnt++;
                        set.remove(tmp);
                        tmp--;
                    }
                    tmp = num + 1;
                    while(set.contains(tmp)) {
                        cnt++;
                        set.remove(tmp);
                        tmp++;
                    }
                    result = Math.max(result, cnt);
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {100,4,200,1,3,2};

        System.out.println(app.longestConsecutive(nums));
    }
}
