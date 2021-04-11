package T0046_permutations;

import java.util.ArrayList;
import java.util.List;

class Solu {

    static class Solution {

        // 方法
        // 依次确定每个位置上的值
        // 设计递归函数，记录递归调用的深度
        // 时间复杂度O(n!), 空间复杂度O(n)
        public List<List<Integer>> permute(int[] nums) {
            if(nums == null || nums.length == 0) return new ArrayList<>();

            List<List<Integer>> result = new ArrayList<>();

            process(nums, 0, new ArrayList<>(), result);

            return result;
        }

        // 递归查看index位置
        // index表示当前递归到的位置
        // list表示已经处理的元素的集合
        // result表示结果集
        public void process(int[] nums, int index, List<Integer> list, List<List<Integer>> result) {
            if(index == nums.length) {
                // list是引用类型，需要新建，再加入到result
                result.add(new ArrayList<>(list));
            }

            for(int i=index; i<nums.length; i++) {
                swap(nums, index, i);
                list.add(nums[index]);
                process(nums, index+1, list, result);
                list.remove(index);
                swap(nums, index, i);
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

        int[] nums = {1, 2, 3};
        System.out.println(app.permute(nums));
    }
}
