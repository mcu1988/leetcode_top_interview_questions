package T0179_largest_number;

import java.util.Arrays;
import java.util.HashMap;

class Solu {

    static class Solution {

        // 方法
        // 把数组转string数组
        // 把数组按拼接序排序
        // 时间复杂度O(N*log(N)), 空间复杂度(N)
        public String largestNumber(int[] nums) {
            if(nums == null || nums.length == 0)
                return "";

            String[] arr = new String[nums.length];
            for(int i=0; i<nums.length; i++) {
                arr[i] = String.valueOf(nums[i]);
            }

            Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));

            // 如果排序后的第一个位置是0，那么整个数组都是0， 返回"0"
            if(arr[0] == "0") return "0";

            String result = "";
            for(String str : arr) {
                result += str;
            }

            return result;
        }
    }


    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {10,2};
        app.largestNumber(nums);
        System.out.println(app.largestNumber(nums));

    }
}
