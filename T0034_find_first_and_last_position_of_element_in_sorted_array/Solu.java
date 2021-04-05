package T0034_find_first_and_last_position_of_element_in_sorted_array;

class Solu {

    static class Solution {

        // 方法1
        // 时间复杂度O(log(n))，最坏情况下O(n), 空间复杂度O(1)
        public int[] searchRange1(int[] nums, int target) {

            if(nums == null || nums.length == 0) return new int[] {-1, -1};

            int l = 0, r = nums.length - 1, mid = 0;

            while(l <= r) {
                mid = ((r -l) >> 1) + l;
                if(target == nums[mid]) {
                    int start = mid, end = mid;
                    while(start >=0 && nums[start] == nums[mid]) start--;
                    while(end < nums.length && nums[end] == nums[mid]) end++;
                    return new int[] {start+1, end-1};
                } else if(target < nums[mid]) r = mid - 1;
                else l = mid + 1;
            }

            return new int[] {-1, -1};
        }

        // 方法2
        // 设计一个函数f，返回target所在的最后一个位置
        // 调用f(nums, target)返回target的最后一个位置end
        // 调用f(nums, target-1)返回target-1的最后一个位置start
        // [start+1, end]是所求答案
        // 当target不存在时，start = end, return [-1, -1]
        // 时间复杂度，O(log(n))，空间复杂度O(1)
        public int[] searchRange2(int[] nums, int target) {

            if (nums == null || nums.length == 0) return new int[]{-1, -1};

            // 返回<=target的最后一个位置
            int end = seachSmallerOrEqual(nums, target);
            // 返回<=target-1的最后一个位置
            // start + 1就是target的起始位置
            int start = seachSmallerOrEqual(nums, target-1);

            // target不存在时，start = end
            if(start == end) return new int[] {-1, -1};

            return new int[] {start+1, end};

        }

        // 方法3
        // 设计一个函数f，返回target所在的最后一个位置end
        // 设计一个函数g，返回target所在的第一位置start
        // [start, end]就是答案
        // 如果tartget不存在，那么start或者end可能越界或者nums[start] != nums[end], 返回[-1, -1]
        // 时间复杂度，O(log(n))，空间复杂度O(1)
        public int[] searchRange3(int[] nums, int target) {

            if (nums == null || nums.length == 0) return new int[]{-1, -1};

            // 若果target存在，返回<=target的最后一个位置，否则返回<target的最后一个位置
            int end = seachSmallerOrEqual(nums, target);
            // 若果target存在，返回>=target的最后一个位置，否则返回>target的第一个位置
            // start + 1就是target的起始位置
            int start = seachLargerOrEqual(nums, target);

            // target不存在时，start或者end可能越界或者nums[start] != nums[end]
            if(start < 0 || start >= nums.length || end < 0 || end >= nums.length || nums[start] != nums[end]) return new int[] {-1, -1};

            return new int[] {start, end};

        }

        // 返回 <= target的最后一个位置
        // 如果target存在，返回target的最后一个位置，否则返回< target的最后一个位置
        public int seachSmallerOrEqual(int[] nums, int target) {
            int l = 0, r = nums.length - 1, mid = 0;

            while(l <= r) {
                mid = ((r -l) >> 1) + l;
                if(nums[mid] <= target) l = mid + 1;
                else r = mid - 1;
            }
            return r;
        }

        // 返回 >= target的第一个位置
        // 如果target存在，返回target的第一个位置，否则返回 > target的第一个位置
        public int seachLargerOrEqual(int[] nums, int target) {
            int l = 0, r = nums.length - 1, mid = 0;

            while(l <= r) {
                mid = ((r -l) >> 1) + l;
                if(nums[mid] >= target) r = mid - 1;
                else l = mid + 1;
            }
            return l;
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {5,7,7,8,8,10};
        int target = 0;
        app.searchRange3(nums, target);
        System.out.println(app.searchRange3(nums, target));


    }
}
