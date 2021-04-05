package T0033_search_in_rotated_sorted_array;

class Solu {

    static class Solution {

        // 方法
        // 先确定mid落在那边，[l,mid] [mid, r]有一边是有序的
        // 时间复杂度，O(log(n))，空间复杂度O(1)
        public int search(int[] nums, int target) {

            if(nums == null || nums.length == 0) return -1;

            int l = 0, r = nums.length - 1, mid = 0;

            while(l <= r) {
                // 避免数组长度太大，(l + r) / 2溢出
                mid = ((r - l) >> 1) + l;
                if(nums[mid] == target) return mid;
                if(nums[mid] >= nums[l]) { // nums[mid] = nums[l]时，只能说明左边有序
                    // [l...mid]有序
                    // target落在左边，就去左边查找，否则去右边查找
                    if(target >= nums[l] && target < nums[mid]) r = mid - 1;
                    else l = mid + 1;
                } else {
                    // [mid, r]有序
                    // target落在右边就去右边查找，否则去左边查找
                    if(target > nums[mid] && target <= nums[r]) l = mid + 1;
                    else r = mid - 1;
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {5, 2};
        int target = 2;
        app.search(nums, target);
        System.out.println(app.search(nums, target));


    }
}
