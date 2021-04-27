package T0140_word_break_ii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class Solu {

    static class Solution {

        // 方法
        // 递归遍历原始字符串，考虑cur开始到结束位置能不能被分割
        // 时间复杂度O(2^N-1), 空间复杂度O(N)
        public List<String> wordBreak(String s, List<String> wordDict) {

            if(s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0)
                return new ArrayList<>();

            HashSet<String> set = new HashSet<>(wordDict);

            List<String> result = new ArrayList<>();
            process(s, 0, "", result, set);
            return result;
        }

        // 递归函数，返回从start...end能否使用wordDict分割
        public void process(String s, int start, String seped, List<String> result, HashSet<String> set) {
            if(start == s.length()) {
                result.add(seped);
                return;
            }

            // 依次尝试从start...end的字符串
            for(int end=start; end<s.length(); end++) {
                String cur = s.substring(start, end+1);
                if(set.contains(cur)) { // 如果start...end在wordDict中，继续向下递归
                    if(start == 0) process(s, end+1, cur, result, set); // 如果是从0开始，不需要加空格
                    else process(s, end+1, seped + " " + cur, result, set);
                }
            }
        }

    }


    public static void main(String[] args) {
        Solution app = new Solution();

        String s = "leetcode";
        String[] tmp = {"leet","code"};
        List<String> wordDict = Arrays.asList(tmp);

        app.wordBreak(s, wordDict);

        System.out.println(app.wordBreak(s, wordDict));

    }
}
