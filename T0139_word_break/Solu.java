package T0139_word_break;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class Solu {

    static class Solution {

        // 方法1
        // 递归遍历原始字符串，考虑cur开始到结束位置能不能被分割
        // 时间复杂度O(2^N-1), 空间复杂度O(N)
        public boolean wordBreak1(String s, List<String> wordDict) {

            if(s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0)
                return false;

            HashSet<String> set = new HashSet<>(wordDict);

            return process(s, 0, set);
        }

        // 递归函数，返回从start...end能否使用wordDict分割
        public boolean process(String s, int start, HashSet<String> set) {
            if(start == s.length()) return true;

            // 依次尝试从start...end的字符串
            for(int end=start; end<s.length(); end++) {
                String cur = s.substring(start, end+1);
                if(set.contains(cur)) { // 如果start...end在wordDict中，继续向下递归
                    if(process(s, end+1, set)) return true; // 有一条路走通后，直接向上层返回，不再尝试其他
                }
            }
            // 所有end都尝试后，分割失败
            return false;
        }


        // 方法2
        // 思路和方法1一致，方法1中有很多重复的递归，考虑使用缓存，减少递归调用
        // dp[i]表示i...end能不能分割成功
        // 时间复杂度O(2^N-1), 空间复杂度O(N)
        public boolean wordBreak2(String s, List<String> wordDict) {

            if(s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0)
                return false;

            HashSet<String> set = new HashSet<>(wordDict);
            int[] dp = new int[s.length()+1];

            return process1(s, 0, set, dp) == 1;
        }

        // 递归函数，返回从start...end能否使用wordDict分割
        public int process1(String s, int start, HashSet<String> set, int[] dp) {
            if(dp[start] != 0) return dp[start];

            if(start == s.length()) {
                dp[start] = 1;
                return dp[start];
            }

            // 依次尝试从start...end的字符串
            for(int end=start; end<s.length(); end++) {
                String cur = s.substring(start, end+1);
                if(set.contains(cur)) { // 如果start...end在wordDict中，继续向下递归
                    dp[end+1] = process1(s, end+1, set, dp);
                    if(dp[end+1] == 1) return dp[end+1]; // 有一条路走通后，直接向上层返回，不再尝试其他
                }
            }
            // 所有end都尝试后，分割失败
            dp[start] = 2;
            return dp[start];
        }

        // 方法3
        // 思路和方法1一致，方法1中有很多重复的递归，考虑使用缓存，减少递归调用
        // dp[i]表示i...end能不能分割成功
        // 时间复杂度O(N^2), 空间复杂度O(N)
        public boolean wordBreak(String s, List<String> wordDict) {

            if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0)
                return false;

            int N = s.length();
            // dp[i]表示i...N能不能被有效分割
            boolean[] dp = new boolean[N+1];
            // 初始时i=N, 字符串为空，可以被分割
            dp[N] = true;

            HashSet<String> set  = new HashSet<>(wordDict);

            for(int start=N-1; start>=0; start--) { //dp[i] 依赖于后面额dp，因此，start从高往低遍历
                for(int end=start; end<N; end++) { // 从当前字符往后截取
                    String cur = s.substring(start, end+1); // 获得当前字符串
                    if(set.contains(cur)) dp[start] |= dp[end+1]; // 当前字符串有效，dp[start]取决于dp[end+1]能不能有效分割
                }
            }
            return dp[0];
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
