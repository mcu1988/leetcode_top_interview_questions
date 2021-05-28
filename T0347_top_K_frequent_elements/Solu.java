package T0347_top_K_frequent_elements;

import java.util.*;

class Solu {

    static class Solution {

        // 方法1
        // 使用HashMap统计词频
        // 使用大小为k的最小堆，获得前k个词频大的词
        // 时间复杂度O(N*log(K)), 空间复杂度O(N)
        public int[] topKFrequent1(int[] nums, int k) {
            if(nums == null || nums.length == 0 || k <= 0 || k > nums.length)
                return new int[] {};

            HashMap<Integer, Integer> cntMap = new HashMap<>();
            // 统计词频
            for(int num : nums) cntMap.put(num, cntMap.getOrDefault(num, 0) + 1);

            // 建立大小为k的最小堆，依据词频排序
            Queue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(k, (a, b) -> a.getValue() - b.getValue());

            // 词频依次加入堆，当堆的大小超过k时，每次弹出一个最小值
            for(Map.Entry<Integer, Integer> pair : cntMap.entrySet()) {
                minHeap.add(pair);
                if(minHeap.size() > k) minHeap.poll();
            }

            // 取词频最大的k个数字
            int[] result = new int[k];
            int index = 0;
            while(!minHeap.isEmpty()) result[index++] = minHeap.poll().getKey();

            return result;
        }

        // 方法2
        // 使用HashMap统计词频
        // 使用长度为N的List<List>，把词频当索引，把单词放在对应的位置，实现词频的排序
        // 通词频的单词放入一个list内
        // 逆序读数组，取词频最大的k个值
        // 时间复杂度O(N), 空间复杂度O(N)
        public int[] topKFrequent(int[] nums, int k) {
            if(nums == null || nums.length == 0 || k <= 0 || k > nums.length)
                return new int[] {};

            HashMap<Integer, Integer> cntMap = new HashMap<>();
            // 统计词频
            for(int num : nums) cntMap.put(num, cntMap.getOrDefault(num, 0) + 1);

            // 初始化词频list
            List<List<Integer>> cntList = new ArrayList<>(nums.length+1);
            for(int i=0; i<= nums.length; i++) cntList.add(new ArrayList<>());

            // 按词频放入list对应位置
            for(Map.Entry<Integer, Integer> pair : cntMap.entrySet()) {
                cntList.get(pair.getValue()).add(pair.getKey());
            }

            // 倒序取钱k个词
            int[] result = new int[k];
            for(int i=nums.length, index=0; index < k; i--) {
                for(int v : cntList.get(i)) result[index++] = v;
            }
            return result;
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {1,1,1,2,2,3};
        int k = 2;

        app.topKFrequent(nums, k);

    }
}
