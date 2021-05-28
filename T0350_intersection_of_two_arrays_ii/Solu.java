package T0350_intersection_of_two_arrays_ii;

import java.util.*;

class Solu {

    static class Solution {

        // 方法1
        // 把一个数组的频次统计出来
        // 遍历另外一个数组，查看每个元素在频次map中出现的次数，每统计1次，对应的频次减1
        // 时间复杂度O(M+N), 空间复杂度O(M+N)
        public int[] intersect(int[] nums1, int[] nums2) {
            if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0)
                return new int[] {};

            // 统计nums1的频次
            HashMap<Integer, Integer> nums1CntMap = new HashMap<>();
            for(int num : nums1) nums1CntMap.put(num, nums1CntMap.getOrDefault(num, 0)+1);

            // 遍历nums2, 统计交集
            List<Integer> resultList = new ArrayList<>();
            for(int num : nums2) {
                if(nums1CntMap.getOrDefault(num, 0) > 0) {
                    resultList.add(num);
                    nums1CntMap.put(num, nums1CntMap.get(num)-1);
                }
            }

            int[] result = new int[resultList.size()];
            for(int i=0; i<resultList.size(); i++) result[i] = resultList.get(i);

            return result;
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums1 = {1,2,2,1};
        int[] nums2 = {2,2};

        app.intersect(nums1, nums2);

    }
}
