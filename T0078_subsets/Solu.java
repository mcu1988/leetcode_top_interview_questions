package T0078_subsets;

import java.util.ArrayList;
import java.util.List;

class Solu {

    static class Solution {


        // 使用递归函数，变量index位置可以添加进结果也可以不添加进结果
        // 时间复杂度O(2^N)
        public List<List<Integer>> subsets(int[] nums) {

            if(nums == null || nums.length == 0) return new ArrayList<>();

            List<List<Integer>> result = new ArrayList<>();
            process(nums, 0, new ArrayList<>(), result);
            return result;

        }

        // 递归函数
        // index表示当前来到的位置
        // list表示当前路径上的结果
        // result表示最终结果集
        // index位置可以选择要或者不要
        public void process(int[] nums, int index, List<Integer> list, List<List<Integer>> result) {
            if(index == nums.length) {
                result.add(new ArrayList<>(list));
                return;
            }

            // 不添加index
            process(nums, index+1, list, result);

            // 添加index
            list.add(nums[index]);
            process(nums, index+1, list, result);
            // 退递归时，恢复list
            list.remove(list.size() - 1);

        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {1,2,3};

        app.subsets(nums);

        System.out.println(app.subsets(nums));

    }
}
