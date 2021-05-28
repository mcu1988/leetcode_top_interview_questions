package T0315_count_of_smaller_numbers_after_self;

import java.util.ArrayList;
import java.util.List;

class Solu {

    static class Solution {

        // 方法1
        // 依次遍历每个位置，找到后面比他小的元素数量
        // 时间复杂度O(N^2), 空间复杂度O(N)
        public List<Integer> countSmaller1(int[] nums) {
            if(nums == null || nums.length == 0)
                return new ArrayList<>();

            List<Integer> result = new ArrayList<>();
            for(int i=0; i<nums.length; i++) {
                int cnt = 0;
                for(int j=i+1; j<nums.length; j++) {
                    if(nums[j] < nums[i]) cnt++;
                }
                result.add(cnt);
            }
            return result;
        }

        // 方法2
        // 使用归并排序，从小到大排序
        // 归并排序的merge过程，合并左右两部分有序子数组时，从后往前合并
        // 如果遇到左边的p1位置比右边的p2位置大，那么p1位置可以结算，p2及其左边的位置都比p1位置小
        // 初始时每个位置的cnt是0，递归合并时，会不断更新每个位置的cnt
        // 由于排序会打乱数组元素的位置，使用Info结构存储数组的值和index
        // 时间复杂度O(N*log(N)), 空间复杂度O(N)
        public List<Integer> countSmaller(int[] nums) {
            if (nums == null || nums.length == 0)
                return new ArrayList<>();

            List<Integer> result = new ArrayList<>();

            // 原始数组转info数组
            Info[] arr = new Info[nums.length];
            for(int i=0; i<nums.length; i++) {
                result.add(0);
                arr[i] = new Info(nums[i], i);
            }

            // 归并排序
            mergeSort(arr, 0, arr.length-1, result);

            return result;
        }

        class Info {
            int value;
            int index;
            Info(int value, int index) {
                this.value = value;
                this.index = index;
            }
        }

        public void mergeSort(Info[] arr, int l, int r, List<Integer> result) {
            if(l == r) return;

            int mid = (l + r) / 2;
            mergeSort(arr, l, mid, result);
            mergeSort(arr, mid+1, r, result);
            merge(arr, l, mid, r, result);
        }

        public void merge(Info[] arr, int l, int mid, int r, List<Integer> result) {
            Info[] help = new Info[r-l+1];

            // 2个子数组从后往前遍历
            int p1 = mid, p2 = r, index = r-l;
            while(p1 >= l && p2 >= mid+1) { // 2个数组都没后遍历完
                if(arr[p1].value > arr[p2].value) {
                    // 左边的大于右边的，更新左边的p1位置的值
                    // p1原始的局部取值加上当前的取值  p2 - mid
                    result.set(arr[p1].index, result.get(arr[p1].index) + p2 - mid);
                    help[index--] = arr[p1--];
                } else {
                    // 左边的<=右边的，不用计数
                    help[index--] = arr[p2--];
                }
            }

            // p2完结时，p1没有完结的情况
            while(p1 >= l) {
                help[index--] = arr[p1--];
            }

            // p1完结时，p2没有完结的情况
            while(p2 >= mid+1) {
                help[index--] = arr[p2--];
            }

            // 辅助数组的值更新到原数组
            for(int i=0; i<help.length; i++) arr[l+i] = help[i];
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {5,2,6,1};

        app.countSmaller(nums);

        System.out.println(app.countSmaller(nums));

    }
}
