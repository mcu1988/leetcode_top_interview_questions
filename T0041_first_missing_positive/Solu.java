package T0041_first_missing_positive;

import java.util.ArrayList;
import java.util.List;

class Solu {

    private Solution app;

    static class Solution {

        // 方法
        // 保持数组左边的数字时从1开始的连续正整数1,2,3...
        // 使用头尾双指针，头指针l表示[0,l-1]位置的值已经是1,2,3...的连续正整数
        // 尾指针r表示，如果整个数组都是1，2，3...的连续正整数，达到的最大值
        // 时间复杂度O(n), 空间复杂度O(1)
        // 循环结束后会破坏原数组nums
        public int firstMissingPositive(int[] nums) {
            if(nums == null) return -1;

            // l代表[0, l-1]位置上的数字已经是1,2,3...l连续的正整数
            // r表示如果数组全部是从1开始的连续正整数的情况下达到的最大值, 同时[r, end]是无效的数字区域
            int l = 0;
            int r = nums.length;

            // l < r的情况下，考察l位置的元素
            while(l < r) { // r...end是已经验证的无效数字区域，l==r时循坏终止
                if(nums[l] == l+1) {
                    // l位置 = l+1，有效， l+1
                    l++;
                } else if(nums[l] > r || nums[l] <= l || nums[nums[l] - 1] == nums[l]) {
                    // 其他情况
                    // 情况1：nums[l] > r， 即l位置的值超过了预期能达到的最大值r，说明l位置的值不是需要的，发配到r-1位置，同时，r = r-1，能保持的从1开始的连续值减1
                    // 情况2：nums[l] <= l，说明l位置的值和已经排序好的[0,l-1]位置的值重复，发配到r-1位置，同时，r = r-1，能保持的从1开始的连续值减1
                    // 情况3：l < nums[l] <= r, 如果nums[l]-1位置的值和l位置的值一样，说明出现重复值，发配到r-1位置，同时，r = r-1，能保持的从1开始的连续值减1
                    // 情况3不可能越界，因为l和r在数组范围内
                    nums[l] = nums[--r];
                } else {
                    // 情况3中，如果nums[l]-1位置的值和l位置的值不一样，把两位置的值互换，继续查看l位置的值
                    swap(nums, l, nums[l]-1);
                }
            }

            // 循环结束后，l位置的值应该填l+1，即缺少的最小的正整数是l+1
            return l+1;
        }

        public void swap(int[] nums, int a, int b) {
            int tmp = nums[a];
            nums[a] = nums[b];
            nums[b] = tmp;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {3,4,-1,1};
        app.firstMissingPositive(nums);
        System.out.println(app.firstMissingPositive(nums));
    }
}
