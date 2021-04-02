package T0010_regular_expression_matching;

class Solu {
    static class Solution {

        // 方法1 递归实现
        // 先检查字符串有效性
        // 设计递归函数process返回si到结束和pi到结束能够成功匹配
        // 时间复杂度O(m*n), 空间复杂度O(1)
        public boolean isMatch1(String s, String p) {
            if(s == null || p == null) return false;

            // 检查字符串有效性
            if(!isValid(s, p)) return false;

            // 调用递归函数
            return process(s, 0, p, 0);

        }

        // 返回si到结束和pi到结束能够成功匹配
        // pi=0时，pi位置的元素不是*，因为初始字符串有效
        // 函数调用时保证了pi不会是*
        public boolean process(String s, int si, String p, int pi) {
            // s字符串结束
            if(si == s.length()) {
                // p字符串也结束，匹配成功
                if(pi == p.length()) return true;
                // 否则如果pi+1位置的字符是*，继续看pi+2是否能匹配
                if(pi+1 < p.length() && p.charAt(pi+1) == '*') {
                    return process(s, si, p, pi+2);
                }
                // pi+1 == length或者pi+1位置不是*
                return false;
            }

            // p字符串结束
            if(pi == p.length()) {
                // 如果s字符串结束，匹配成功
                return si == s.length();
            }

            // pi+1位置的字符串不是*，即pi+1越界或者pi+1不是*
            // 只能看si和pi能否匹配上
            if(pi+1 >= p.length() || p.charAt(pi+1) != '*') {
                // si和pi能匹配上，并且后面的字符串能匹配上
                return (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.') && process(s, si+1, p, pi+1);
            }

            // pi+1位置的字符串是*， si和pi位置不匹配
            if(s.charAt(si) != p.charAt(pi) && p.charAt(pi) != '.') {
                // pi,pi+1的 *字符串匹配0次，查看后面是否能匹配上
                return process(s, si, p, pi+2);
            }

            // pi+1位置的字符串是*， si和pi位置匹配
            // 先考虑pi,pi+1的 *字符串匹配0次
            if(process(s, si, p, pi+2)) return true;

            // 再考虑pi,pi+1的 *字符串匹配多次
            while(si < s.length() && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.')) {
                if(process(s, si+1, p, pi+2)) return true;
                si++;
            }
            return false;
        }

        // 检查字符串s和p的有效性
        public boolean isValid(String s, String p) {
            // 检查字符串s, s中不能包含 . 和 *
            for(int i=0; i<s.length(); i++) {
                if(s.charAt(i) == '.' || s.charAt(i) == '*')
                    return false;
            }

            // 检查字符串p，p中*不能开头，多个*不能连在一起
            for(int i=0; i<p.length(); i++) {
                if(p.charAt(i) == '*' && (i == 0 || p.charAt(i-1) == '*'))
                    return false;
            }
            return true;
        }


        // 方法2 缓存版本的递归
        // 使用二维数组存储si和pi的调用结果
        public boolean isMatch2(String s, String p) {
            if(s == null || p == null) return false;

            // 检查字符串有效性
            if(!isValid(s, p)) return false;

            // 初始值0，代表该位置为遍历过
            // dp[i][j] == 1代表能匹配
            // dp[i][j] == 2代表不能匹配
            // si的范围0~s.length()， pi的范围0~p.length()
            int[][] dp = new int[s.length()+1][p.length()+1];

            // 调用递归函数
            return process1(s, 0, p, 0, dp) == 1;

        }

        // 返回si到结束和pi到结束能够成功匹配
        // pi=0时，pi位置的元素不是*，因为初始字符串有效
        // 函数调用时保证了pi不会是*
        public int process1(String s, int si, String p, int pi, int[][] dp) {

            // 检查si和pi位置是否遍历过
            if(dp[si][pi] != 0) return dp[si][pi];

            // s字符串结束
            if(si == s.length()) {
                // p字符串也结束，匹配成功
                if(pi == p.length()) {
                    dp[si][pi] = 1;
                    return dp[si][pi];
                }
                // 否则如果pi+1位置的字符是*，继续看pi+2是否能匹配
                if(pi+1 < p.length() && p.charAt(pi+1) == '*') {
                    dp[si][pi] = process1(s, si, p, pi+2, dp);
                    return dp[si][pi];
                }
                // pi+1 == length或者pi+1位置不是*
                dp[si][pi] = 2;
                return dp[si][pi];
            }

            // p字符串结束
            if(pi == p.length()) {
                // 如果s字符串结束，匹配成功
                dp[si][pi] = si == s.length() ? 1 : 2;
                return dp[si][pi];
            }

            // pi+1位置的字符串不是*，即pi+1越界或者pi+1不是*
            // 只能看si和pi能否匹配上
            if(pi+1 >= p.length() || p.charAt(pi+1) != '*') {
                // si和pi能匹配上，并且后面的字符串能匹配上
                dp[si][pi] = (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.') && process1(s, si+1, p, pi+1, dp) == 1 ? 1 : 2;
                return dp[si][pi];
            }

            // pi+1位置的字符串是*， si和pi位置不匹配
            if(s.charAt(si) != p.charAt(pi) && p.charAt(pi) != '.') {
                // pi,pi+1的 *字符串匹配0次，查看后面是否能匹配上
                dp[si][pi] = process1(s, si, p, pi+2, dp);
                return dp[si][pi];
            }

            // pi+1位置的字符串是*， si和pi位置匹配
            // 先考虑pi,pi+1的 *字符串匹配0次
            if(process1(s, si, p, pi+2, dp) == 1) {
                dp[si][pi] = 1;
                return dp[si][pi];
            }

            // 再考虑pi,pi+1的 *字符串匹配多次
            while(si < s.length() && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.')) {
                if(process1(s, si+1, p, pi+2, dp) == 1) {
                    dp[si][pi] = 1;
                    return dp[si][pi];
                };
                si++;
            }

            dp[si][pi] = 2;
            return dp[si][pi];
        }

        // 方法3，动态规划
        // 建立二维dp表，dp[i][j]表示s[i...end]和p[j...end]是匹配
        // 时间复杂度O(m*n), 空间复杂度O(m*n)
        public boolean isMatch(String s, String p) {
            if (s == null || p == null) return false;

            int m = s.length(), n = p.length();
            // si范围是0~m, pi范围是0~n
            // dp[i][j]表示s[i...end]和p[j...end]是匹配
            boolean[][] dp = new boolean[m+1][n+1];

            // s字符串到尾部时的边界值填充
            dp[m][n] = true; // s和p都结束时时true
            for(int i=n-2; i>=0; i--) { // 从后往前遍历，因为dp表示的是后缀字符串是否匹配
                // 如果当前字符串的下一个字符是*, 消去i和i+1位置的值，匹配0次
                if(p.charAt(i+1) == '*') {
                    dp[m][i] = dp[m][i+2];
                }
                // 下一个字符串不是*时，结果是false
            }

            //
            for(int si=m-1; si>=0; si--) {
                for(int pi=n-1; pi>=0; pi--) {
                    // si位置和pi位置的字符串能匹配上
                    if(s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.') {

                        // 查看pi+1位置是否是*
                        if(pi+1 < n && p.charAt(pi+1) == '*') {
                            // 是*，依次匹配0，1，>=2次
                            // 匹配>=2次的情况，p中a*...写成aa*...的形式，和s中的a...第一个a抵消，剩下的看si+1和pi是否匹配
                            dp[si][pi] = dp[si][pi+2] // *匹配0次
                                         || dp[si+1][pi+2] // *匹配1次
                                         || dp[si+1][pi]; // *匹配多次
                        } else {
                            // pi+1不是*的情况
                            dp[si][pi] = dp[si+1][pi+1];
                        }
                    } else {
                        // si位置和pi位置的字符串能匹配不上
                        // 查看下一个字符是否是*
                        if(pi+1 < n && p.charAt(pi+1) == '*') {
                            dp[si][pi] = dp[si][pi+2];
                        }
                        // pi+1不是*， false
                    }
                }
            }
            return dp[0][0];
        }

        }

    public static void main(String[] args) {
        Solution app = new Solution();
        app.isMatch("ab", ".*");
        System.out.println(app.isMatch("ab", ".*"));
    }

}
