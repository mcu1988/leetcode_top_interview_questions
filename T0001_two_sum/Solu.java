package T0001_two_sum;

import java.util.HashMap;

class Solu {

    // 使用HashMap缓存，时间复杂度O(n), 空间复杂度O(n)
    public int[] twoSum(int[] nums, int target) {
        if(nums == null || nums.length == 0) return new int[] {-1, -1};

        // 存放整数 -> index的映射
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] result = {-1, -1};
        for(int i=0; i<nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;
                break;
            }
            map.put(nums[i], i);
        }
        return result;
    }

}