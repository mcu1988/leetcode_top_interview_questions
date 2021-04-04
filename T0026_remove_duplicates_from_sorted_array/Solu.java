package T0026_remove_duplicates_from_sorted_array;

class Solu {

    static class Solution {

        // 方法
        // 时间复杂度，O(n)，空间复杂度O(1)
        public int removeDuplicates(int[] nums) {
            if(nums == null || nums.length == 0) return 0;

            // 数组长度>=1, 最少包含1个元素，初始result=1
            int result = 1;
            for(int i=1; i<nums.length; i++) { // 从1开始遍历
                // 数组有序，当前元素和前一个元素不同，就代表新增加一个不同的元素
                if(nums[i] != nums[i-1]) nums[result++] = nums[i];
            }
            return result;
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(app.removeDuplicates(nums));


    }
}
