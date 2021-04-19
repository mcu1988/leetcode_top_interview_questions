package T0075_sort_colors;

import java.util.Arrays;

class Solu {

    static class Solution {

        // 方法
        // 荷兰国旗问题，使用左右两个指针l, r
        // 表示<=l区域是0， >=r区域是2，中间区域是1
        // 初始时 l = -1, r = nums.length
        // 遍历数组，当前元素i，分3种情况
        // 情况1，nums[i] == 1, i++
        // 情况2，nums[i] == 0, l++, 交换i位置和l位置的1，i++
        // 情况3，nums[i] == 2, r--, 交换i位置的2和r位置，i不变
        // 时间复杂度O(N), 空间复杂度O(1)
        public void sortColors(int[] nums) {
            if(nums == null || nums.length < 2) return;

            int l = -1, r = nums.length;
            for(int i=0; i<r;) { // i==r时，循环结束
                // 情况1
                if(nums[i] == 1) i++;
                else if(nums[i] == 0) swap(nums, i++, ++l); // 情况2
                else swap(nums, i, --r); // 情况3
            }
        }

        public void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

        public void printArr(int[] nums) {
            for(int i=0; i<nums.length; i++) {
                System.out.print(nums[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {2,0,2,1,1,0};

        app.printArr(nums);
        app.sortColors(nums);
        app.printArr(nums);
    }
}
