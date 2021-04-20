package T0091_decode_ways;

import java.util.Arrays;
import java.util.Stack;

class Solu {

    static class Solution {

        // 方法1
        // 使用递归函数，考虑当前值到结尾的方法数，当前值的情况
        // 情况1，当前值时0，return 0
        // 情况2，当前值是1~9，只使用当前的一个字符，继续向下匹配
        // 情况3，当前值是1，下一个值是0~9，使用2个字符，继续向下匹配
        // 情况4，当前值是2，下一个值是0~6，使用2个字符，继续向下匹配
        // 所有情况的和就是当前位置到数组结尾方法数
        public int numDecodings1(String s) {
            if(s == null || s.length() == 0) return 0;

            return process(s, 0);
        }

        public int process(String s, int index) {
            // 匹配到数组结尾，匹配到依次有效的值
            if(index == s.length()) return 1;

            int cnt = 0;
            // 情况1
            if(s.charAt(index) == '0') return 0;
            // 情况2
            if(s.charAt(index) >= '1' && s.charAt(index) <= '9') {
                cnt += process(s, index+1);
            }
            //情况3和情况4
            if(index < s.length()-1 &&
                    ( (s.charAt(index) == '1' && s.charAt(index+1) >= '0' && s.charAt(index+1) <= '9') ||
                    (s.charAt(index) == '2' && s.charAt(index+1) >= '0' && s.charAt(index+1) <= '6')
                    )) {
                cnt += process(s, index+2);
            }
            return cnt;
        }

        // 方法2
        // 思路和方法1一致
        // 方法1中有很多重复的递归调用，可以使用数组进行缓存
        public int numDecodings2(String s) {
            if(s == null || s.length() == 0) return 0;

            // dp[i]表示i...end匹配的方法数，数组的默认值是-1
            int[] dp = new int[s.length()+1];
            Arrays.fill(dp, -1);
            return process1(s, 0, dp);
        }

        public int process1(String s, int index, int[] dp) {
            // index位置已经递归过了，直接 返回
            if(dp[index] != -1) return dp[index];

            if(index == s.length()) {
                dp[index] = 1;
                return dp[index];
            }

            int cnt = 0;
            // 情况1
            if(s.charAt(index) == '0') {
                dp[index] = 0;
                return dp[index];
            }
            // 情况2
            if(s.charAt(index) >= '1' && s.charAt(index) <= '9') {
                cnt += process1(s, index+1, dp);
            }
            // 情况3和情况4
            if(index < s.length()-1 &&
                    ( (s.charAt(index) == '1' && s.charAt(index+1) >= '0' && s.charAt(index+1) <= '9') ||
                            (s.charAt(index) == '2' && s.charAt(index+1) >= '0' && s.charAt(index+1) <= '6')
                    )) {
                cnt += process1(s, index+2, dp);
            }

            dp[index] = cnt;
            return dp[index];
        }

        // 方法3
        // 动态规划，dp[i]表示i...end匹配的方法数
        // 时间复杂度O(N), 空间复杂度O(N)
        public int numDecodings3(String s) {
            if (s == null || s.length() == 0) return 0;

            // dp[i]表示i...end的方法数
            int[] dp = new int[s.length()+1];
            dp[s.length()] = 1;

            int tmp = 0;
            for(int i=s.length()-1; i>=0; i--) { // 从高往低遍历
                if(s.charAt(i) == '0') dp[i] = 0; // 情况1
                else if(s.charAt(i) >= '1' && s.charAt(i) <= '9') {
                    tmp = dp[i+1]; // 情况2
                    if(i < s.length()-1 &&
                            ( (s.charAt(i) == '1' && s.charAt(i+1) >= '0' && s.charAt(i+1) <= '9') ||
                              (s.charAt(i) == '2' && s.charAt(i+1) >= '0' && s.charAt(i+1) <= '6')
                            )) {
                        // 情况3和情况4
                        tmp += dp[i+2];
                    }
                    dp[i] = tmp;
                }
            }
            return dp[0];
        }

        // 方法4
        // dp[i]依赖于dp[i+1]和dp[i+2], 使用2个变量即可更新
        // 时间复杂度O(N), 空间复杂度O(1)
        public int numDecodings(String s) {
            if (s == null || s.length() == 0) return 0;

            int second = 1; // second表示dp[i+2]
            // first表示dp[i+1]
            int first = s.charAt(s.length()-1) >= '1' && s.charAt(s.length()-1) <= '9' ? 1 : 0;
            // cur表示dp[i]
            int cur = first;

            int tmp = 0;
            for(int i=s.length()-2; i>=0; i--) { // 从s.length()-2开始遍历，从高往低遍历
                if(s.charAt(i) == '0') cur = 0; // 情况1
                else if(s.charAt(i) >= '1' && s.charAt(i) <= '9') {
                    tmp = first; // 情况2
                    if(i < s.length()-1 &&
                            ( (s.charAt(i) == '1' && s.charAt(i+1) >= '0' && s.charAt(i+1) <= '9') ||
                                    (s.charAt(i) == '2' && s.charAt(i+1) >= '0' && s.charAt(i+1) <= '6')
                            )) {
                        // 情况3和情况4
                        tmp += second;
                    }
                    cur = tmp;
                }
                second = first;
                first = cur;
            }
            return cur;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        String s = "226";

        app.numDecodings(s);

        System.out.println(app.numDecodings(s));


    }
}
