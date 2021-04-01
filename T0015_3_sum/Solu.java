package T0015_3_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solu {
    static class Solution {

        // 方法
        // 先对数组排序
        // 先确定一个数字，问题转换为2个数的和为固定值的问题
        // 2个数的和为固定值的问题使用左右2个首尾指针
        // 时间复杂度O(n^2), 空间复杂度O(1)
        public List<List<Integer>> threeSum(int[] nums) {
            if(nums == null || nums.length < 3) return new ArrayList<>();

            int len = nums.length;

            // 先对数组排序
            Arrays.sort(nums);

            int i = 0, l = 0, r = len-1;
            List<List<Integer>> result = new ArrayList<>();

            while(i < len-2) { // 先确定第1个元素

                // 如果第一个数字大于0，后面的数字会比第一个数字大，累加和不满足条件
                if(nums[i] > 0) break;

                // 二元组的起始位置
                l = i+1;
                r = len-1;

                int tmp = 0 - nums[i];
                // 寻找剩下的二元组
                while(l < r) {
                    // 二元组中第一个元素大于0，第二个元素比第一个元素大，累加和不满足条件
                    if(nums[l] > tmp) break;

                    if(nums[l] + nums[r] < tmp) l++;
                    else if(nums[l] + nums[r] == tmp) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[l]);
                        list.add(nums[r]);
                        result.add(list);
                        // 跳过l位置相同的元素
                        l++;
                        while(l < r && nums[l] == nums[l-1]) l++;
                    } else if(nums[l] + nums[r] > tmp) r--;
                }

                // 先跳过相同数字，停留在相同数字的最后一个数字
                i++;
                while(i < len-2 && nums[i] == nums[i-1]) i++;
            }
            return result;
        }

    }

    public static void main(String[] args) {
        Solution app = new Solution();
        int[] nums = {1,1,-2};
        app.threeSum(nums);
        System.out.println(app.threeSum(nums));
    }

}
