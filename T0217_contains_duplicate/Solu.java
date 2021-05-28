package T0217_contains_duplicate;


import java.util.*;

class Solu {

    static class Solution {

        // 方法1
        // 遍历数组，使用set记录出现过的元素
        // 如果set包含当前元素，返回true
        // 时间复杂度O(N), 空间复杂度O(N)
        public boolean containsDuplicate1(int[] nums) {
            if (nums == null || nums.length < 2) return false;

            HashSet<Integer> set = new HashSet<>();
            for (int num : nums) {
                if (set.contains(num)) return true;
                set.add(num);
            }
            return false;
        }

        // 方法2
        // 利用堆排序
        // 时间复杂度O(N*log(N)), 空间复杂度O(1)
        public boolean containsDuplicate(int[] nums) {
            if (nums == null || nums.length < 2) return false;

            // 堆排序
            heapSort(nums);

            // 检查排序后的相邻元素是否一样
            for(int i=1; i<nums.length; i++) {
                if(nums[i] == nums[i-1]) return true;
            }

            return false;
        }

        public void heapSort(int[] nums) {

            int N = nums.length;

            // 从后往前建立大根堆
            for(int i=N-1; i>=0; i--) {
                heapify(nums, i, N);
            }

            // 交换大根堆堆顶元素和数组末尾元素，重新进行heapify，调整成大根堆
            while(N > 1) {
                swap(nums, 0, --N);
                heapify(nums,0, N);
            }
        }

        public void heapify(int[] nums, int index, int heapSize) {
            int left = 2 * index + 1;
            while(left < heapSize) {
                // 先找到左右节点中较大的一个
                int largeIndex = ( ((left+1) < heapSize) && nums[left+1] > nums[left])
                        ? left + 1
                        : left;
                // 左右最大的和根节点进行比较
                largeIndex = nums[largeIndex] > nums[index] ? largeIndex : index;

                // 根节点>=左右节点，不需要再调整
                if(largeIndex == index) break;

                swap(nums, largeIndex, index);

                // 更新根节点和左孩子索引
                index = largeIndex;
                left = 2 * index + 1;
            }
        }

        public void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {3,2,1,5,6,4};

        app.containsDuplicate(nums);
        System.out.println(app.containsDuplicate(nums));
    }
}
