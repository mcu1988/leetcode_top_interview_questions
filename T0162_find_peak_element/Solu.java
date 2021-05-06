package T0162_find_peak_element;

class Solu {

    static class Solution {

        // 方法1
        // 判断nums[i] > nums[i-1] && nums[i] > nums[i+1]
        // 遇到第一个波峰，返回索引
        // 时间复杂度O(N), 空间复杂度O(1)
        public int findPeakElement1(int[] nums) {
            if(nums == null || nums.length == 0) return -1;

            for(int i=0; i<nums.length; i++) {
                if(i-1 >= 0 && nums[i] <= nums[i-1]) continue;
                if(i+1 < nums.length && nums[i] <= nums[i+1]) continue;
                return i;
            }
            return -1;
        }

        // 方法2
        // 判断nums[i] > nums[i-1] && nums[i] > nums[i+1]
        // 由于相邻元素不相等，nums[0] > nums[-1], 如果nums[0] > nums[1], return 0, 否则nums[0] < nums[1], 继续遍历1位置
        // 只用判断nums[i] > nums[i+1]
        // 时间复杂度O(N), 空间复杂度O(1)
        public int findPeakElement2(int[] nums) {
            if(nums == null || nums.length == 0) return -1;

            for(int i=0; i<nums.length-1; i++) {
                if(nums[i] > nums[i+1])
                    return i;
            }
            // 退出循坏时，nums[N-1] > nums[N-2], 初始条件nums[N-1] > nums[N], 返回N-1
            return nums.length-1;
        }

        // 方法3
        // 由于相邻的值不相等，数组必存在有效解，二分判断
        // 当nums[mid] < nums[mid-1], 说明左边必须在一个波峰，反之，右边必存在一个波峰
        // 判断nums[mid] > nums[mid-1] && nums[i] > nums[mid+1], 返回mid就是答案
        // 时间复杂度O(log(N)), 空间复杂度O(1)
        public int findPeakElement(int[] nums) {
            if (nums == null || nums.length == 0) return -1;

            int l = 0, r = nums.length-1, mid = 0;
            while(l < r) { // l==r时就是答案，此时l可能等于0或者等于N-1
                mid = (l + r) >> 1;
                // mid最大等于N-1, 最小等于0
                // 当mid = N-1时，l==r,返回
                // 当mid=0,时可能是l=0, r=1的情况，此时mid-1越界，因此这里要判断mid>0
                if(mid > 0 && nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1])
                    return mid;
                else if(nums[mid] > nums[mid+1]) r = mid -1; // nums[mid] < nums[mid-1], 左边必存在波峰, 这里兼容mid=0的情况
                else l = mid + 1; // nums[mid] < nums[mid+1], 右边会出现波峰
            }
            return l;
        }

    }


    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {2,1};
        app.findPeakElement(nums);
        System.out.println(app.findPeakElement(nums));
    }
}
