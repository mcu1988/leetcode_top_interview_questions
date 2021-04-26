package T0131_palindrome_partitioning;

import java.util.ArrayList;
import java.util.List;

class Solu {

    static class Solution {

        // 方法1
        // 递归遍历字符串，使用start标记当前遍历的位置
        // 依次尝试start开始的不同长度的字符串
        // 时间复杂度O(2^N-1 * N), 总共有2^N-1种分割，共N-1个间隔，每个间隔可以放0和1，每种分割判断回文O(N), 空间复杂度O(N)
        public List<List<String>> partition1(String s) {

            if(s == null || s.length() == 0) return new ArrayList<>();

            List<List<String>> result = new ArrayList<>();
            process(s, 0, new ArrayList<>(), result);
            return result;
        }

        public void process(String s, int start, List<String> list, List<List<String>> result) {
            // 字符串已经完全分割完，加入当前有效的分割
            if(start == s.length()) {
                result.add(new ArrayList<>(list));
                return;
            }

            // 当前分割是[start, end)
            for(int end=start+1; end<=s.length(); end++) {
                String cur = s.substring(start, end);
                if(isHuiwen(cur)) { // 当前分割有效，加入到list中，继续向下递归
                    list.add(cur);
                    process(s, end, list, result);
                    list.remove(list.size()-1); // 退递归时，删除cur
                }
            }
        }

        public boolean isHuiwen(String s) {
            int l = 0, r = s.length()-1;
            while(l < r) {
                if(s.charAt(l) != s.charAt(r)) return false;
                l++;
                r--;
            }
            return true;
        }

        // 方法2
        // 思路和方法1一致，思路1中存放在重复判断回文的情况
        // 使用缓存dp[i][j]记录i...j范围是否是回文
        // dp[i][j]的递推关系
        // 情况1：i==j, dp[i][j] = true
        // 情况2：i+1==j, dp[i][j] = s[i] == s[j]
        // 情况3：else, dp[i][j] = s[i] == s[j] && dp[i+1][j-1]
        // 时间复杂度O(2^N-1), 总共有2^N-1种分割, 空间复杂度O(N^2)
        public List<List<String>> partition(String s) {

            if(s == null || s.length() == 0) return new ArrayList<>();

            int N = s.length();

            // dp[i][j]表示 i...j范围内的string是否是回文, i<=j
            boolean[][] dp = new boolean[N][N];
            for(int i=N-1; i>=0; i--) { // i从高往低遍历
                for(int j=i; j<N; j++) { // j从低往高遍历
                    if(i == j) dp[i][j] = true; // 情况1
                    else if(i+1 == j) dp[i][j] = s.charAt(i) == s.charAt(j); // 情况2
                    else dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i+1][j-1]; // 情况3
                }
            }

            List<List<String>> result = new ArrayList<>();
            process1(s, 0, dp, new ArrayList<>(), result);
            return result;
        }

        public void process1(String s, int start, boolean[][] dp, List<String> list, List<List<String>> result) {
            // 字符串已经完全分割完，加入当前有效的分割
            if(start == s.length()) {
                result.add(new ArrayList<>(list));
                return;
            }

            // 当前分割是[start, end)
            for(int end=start; end<s.length(); end++) {
                String cur = s.substring(start, end+1);
                if(dp[start][end]) { // 当前分割有效，加入到list中，继续向下递归
                    list.add(cur);
                    process1(s, end+1, dp, list, result);
                    list.remove(list.size()-1); // 退递归时，删除cur
                }
            }
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        String s = "aab";

        app.partition(s);
        System.out.println(app.partition(s));
    }
}
