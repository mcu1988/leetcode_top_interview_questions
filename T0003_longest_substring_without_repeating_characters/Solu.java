package T0003_longest_substring_without_repeating_characters;

import java.util.HashMap;

class Solu {
    static class Solution {

        // 解法1
        // 使用2个指针滑动，指针之间的字符串使用map存储次数，r滑动出现重复时，滑动l
        // 时间复杂度O(n), 空间复杂度O(n)
        public int lengthOfLongestSubstring(String s) {
            if(s == null || s.length() == 0) return 0;

            char[] arr = s.toCharArray();
            int len = arr.length;
            int l = 0, r = 0;
            int result = 0;
            // 记录字符 -> 该字符出现的最新索引
            HashMap<Character, Integer> map = new HashMap<> ();
            while(r < len) {
                if(map.containsKey(arr[r])) {
                    int tmp = map.get(arr[r]);
                    if(tmp >= l) { // [l, r]范围内出现了重复
                        // r向右扩展遇到了重复值，更新result
                        result = Math.max(result, r - l);
                        l = tmp + 1;
                    }
                }
                map.put(arr[r], r);
                r++;
            }
            // 更新r = len时的长度
            result = Math.max(result, r - l);
            return result;
        }
    }

    // 解法2
    // 利用dp, dp[i]表示以i位置结尾能扩展出去的字符串长度
    // dp[i] = min(dp[i-1] + 1, i - arr[i]上次出现的位置);
    // 时间复杂度O(n), 空间复杂度O(n)
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;

        char[] arr = s.toCharArray();
        int len = arr.length;

        // 从1开始遍历
        int pre = 1, cur = 1; // pre表示i-1位置能扩展出去的长度， cur表示当前位置能扩展出去的长度
        HashMap<Character, Integer> map = new HashMap<>(); // 存放 字符 -> 该字符最新出现的索引的映射
        map.put(arr[0], 0); // 初始放入位置0的元素
        int tmpIndex = 0;
        int result = 1; // 返回的结果
        for(int i=1; i<len; i++) { // 从1开始遍历
            // 当前位置上一次出现的位置在哪里，如果没有出现过，取值是-1
            tmpIndex = map.getOrDefault(arr[i], -1);
            // pre+1表示i位置向左能扩展出去的长度，假设i-1能扩展出去的字符串不包含arr[i]
            // [tmpIndex+1, i]表示当前位置i向左能扩展出去的长度
            // 2者取最小
            cur = Math.min(pre + 1, i - tmpIndex);
            result = Math.max(result, cur);
            pre = cur;
            // 更新arr[i]的最新位置
            map.put(arr[i], i);
        }
        return result;
    }
}
