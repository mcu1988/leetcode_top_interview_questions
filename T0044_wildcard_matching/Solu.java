package T0044_wildcard_matching;

class Solu {

    static class Solution {

        // 方法1
        // 使用递归
        // 递归判断s字符串i位置和p位置j字符串位置的比较
        // 设计递归函数f(s, i, p, j)，表示s字符串i...end和p字符串j...end位置是否匹配
        public boolean isMatch1(String s, String p) {
            // p为空，s不为空时，返回false
            if((p == null || p.length() == 0) && (s.length() != 0)) return  false;

            boolean result =  process(s, 0, p, 0);
            return result;
        }

        // 递归函数返回s字符串i...end和p字符串j...end字符串是否匹配
        // i表示s字符串的i位置
        // j表示p字符串的j位置
        // 当递归到s[0...end]和p[0...end]有一个情况返回true时，递归就结束，否则就一直尝试所有情况，所有情况都尝试后，仍然匹配失败，就返回false
        public boolean process(String s, int i, String p, int j) {
            if(i == s.length()) {
                // s和p都到了最后一个位置，匹配成功
                if(j == p.length()) return true;
                else if(p.charAt(j) != '*') return false; // s字符串到了最后一个位置，p的j位置不是*，匹配失败
                else return process(s, i, p, j+1); // s字符串到了最后一个位置，p的j位置是*, 继续判断s[i]和p[j+1]
            }

            // s没有结束，p结束了，匹配失败
            if(j == p.length()) return false;

            // s和p都没有结束

            // p位置不是*
            if(p.charAt(j) != '*') {
                // s[i]和p[j]匹配成功，继续匹配s[i+1]和p[j+1]，否则匹配失败
                if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                    return process(s, i+1, p, j+1);
                } else return false;
            }

            // p位置是*
            // *可以抵消s中i...end的字符
            for(int k=i; k<=s.length(); k++) {
                // 只要有一种情况返回true，就代表i位置和j位置匹配成功，否则就一直尝试
                if(process(s, k, p, j+1)) return true;
            }

            // 所以情况都尝试后，没有提前返回true，代表匹配失败，返回false
            return false;
        }


        // 方法2
        // 在方法1中使用数组缓存递归结果，因为方法1中存在很多重复的递归调用
        public boolean isMatch2(String s, String p) {
            if((p == null || p.length() == 0) && (s.length() != 0)) return  false;

            // 使用数组存储递归结果，0代表为调用过，1代表匹配成功，2代表匹配失败
            int[][] map = new int[s.length()+1][p.length()+1];
            int result =  process1(s, 0, p, 0, map);
            return result == 1;
        }

        // 使用数组存储已经递归过得结果
        // 返回时，先对数组赋值，在返回
        public int process1(String s, int i, String p, int j, int[][] map) {

            // 不等于0，代表已经递归过，直接返回
            if(map[i][j] != 0) return map[i][j];

            if(i == s.length()) {
                if(j == p.length()) {
                    // s和p都到了最后一个位置，匹配成功
                    map[i][j] = 1;
                    return map[i][j];
                } else if(p.charAt(j) != '*') {
                    // s字符串到了最后一个位置，p的j位置不是*，匹配失败
                    map[i][j] = 2;
                    return map[i][j];
                } else {
                    // s字符串到了最后一个位置，p的j位置是*, 继续判断s[i]和p[j+1]
                    map[i][j] = process1(s, i, p, j+1, map);
                    return map[i][j];
                }
            }

            // s没有结束，p结束了，匹配失败
            if(j == p.length()) {
                map[i][j] = 2;
                return map[i][j];
            }

            // s和p都没有结束

            // p位置不是*
            if(p.charAt(j) != '*') {
                // s[i]和p[j]匹配成功，继续匹配s[i+1]和p[j+1]，否则匹配失败
                if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                    map[i][j] = process1(s, i+1, p, j+1, map);
                    return map[i][j];
                } else {
                    map[i][j] = 2;
                    return map[i][j];
                }
            }

            // p位置是*
            // *可以抵消s中i...end的字符
            for(int k=i; k<=s.length(); k++) {
                map[i][j] = process1(s, k, p, j+1, map);
                if(map[i][j] == 1) return map[i][j];
            }

            // 所以情况都尝试后，没有提前返回true，代表匹配失败，返回false
            return map[i][j];
        }

        // 方法3
        // 使用dp表，dp[i][j]表示s[i...end][j...end]是否匹配
        // 分情况递推dp表
        // 时间复杂度O(M*N), 空间复杂度O(M*N)
        public boolean isMatch3(String s, String p) {
            if ((p == null || p.length() == 0) && (s.length() != 0)) return false;

            // 建立dp表，dp[i][j]代表s[i...end]和p[j...end]是否匹配
            boolean[][] dp = new boolean[s.length()+1][p.length()+1];
            dp[s.length()][p.length()] = true; // s和p都结束时，代表匹配成功
            for(int i=p.length()-1; i>=0; i--) { // 从后往前遍历
                // s结束时，如果p[i]!='*'，返回false，否则dp[s.length()][i] = dp[s.length()][i+1]
                dp[s.length()][i] = p.charAt(i) == '*' ? dp[s.length()][i+1] : false;
            }

            // s[i...end]和p[j...end]的匹配情况
            // 情况1，p[j] != '*', 如果s[i]和p[j]位置不匹配，那么dp[i][j] = false, 否则dp[i][j] = dp[i+1][j+1]
            // 情况2，p[j] == '*', 如果*匹配0次，dp[i][j] = dp[i][j+1],
            //                        匹配1次，dp[i][j] = dp[i+1][j+1],
            //                        匹配>=2次，可以把p[j]看成是**的格式，第一个*和p[i]抵消，p[j]还是*，dp[i][j] = dp[i+1][j]
            //                        3种情况综合 dp[i][j] = dp[i][j+1] || dp[i+1][j+1] || dp[i+1][j] = dp[i][j+1] || dp[i+1][j]， 因为dp[i+1][j]依赖于dp[i+1][j+1]
            // dp[i][j]依赖于dp[i][j+1]， dp[i+1][j]，dp[i+1][j+1]， 因此变量i从高往低遍历，j从高往低遍历
            for(int i=s.length()-1; i>=0; i--) { //
                for(int j=p.length()-1; j>=0; j--) {
                    if(p.charAt(j) != '*') {
                        // 情况1，p[j] != '*'时，s[i]和p[j]不匹配，dp[i][j]=false, 否则dp[i][j] = dp[i+1][j+1]
                        dp[i][j] = (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') ? dp[i+1][j+1] : false;
                    } else {
                        // 情况2，dp[i][j] = '*'
                        dp[i][j] = dp[i+1][j] || dp[i][j+1];
                    }
                }
            }
            return dp[0][0];
        }

        // 方法4
        // 把方法3中的dp表压缩到一维
        // dp[i][j]依赖于dp[i][j+1]， dp[i+1][j]，dp[i+1][j+1]，因此递归过程中只需要记住这3个值，使用到下一行的值，使用1维dp表即可
        // 时间复杂度O(M*N), 空间复杂度O(N)
        public boolean isMatch(String s, String p) {
            if ((p == null || p.length() == 0) && (s.length() != 0)) return false;

            // 建立dp表，dp[i][j]代表s[i...end]和p[j...end]是否匹配, 这里相当于把行索引去掉了，只标记了当前行的状态
            boolean[] dp = new boolean[p.length()+1];
            dp[p.length()] = true; // s和p都结束时，代表匹配成功
            for(int i=p.length()-1; i>=0; i--) { // 从后往前遍历
                // s结束时，如果p[i]!='*'，返回false，否则dp[s.length()][i] = dp[s.length()][i+1]
                dp[i] = p.charAt(i) == '*' ? dp[i+1] : false;
            }

            // s[i...end]和p[j...end]的匹配情况
            // 情况1，p[j] != '*', 如果s[i]和p[j]位置不匹配，那么dp[i][j] = false, 否则dp[i][j] = dp[i+1][j+1]
            // 情况2，p[j] == '*', 如果*匹配0次，dp[i][j] = dp[i][j+1],
            //                        匹配1次，dp[i][j] = dp[i+1][j+1],
            //                        匹配>=2次，可以把p[j]看成是**的格式，第一个*和p[i]抵消，p[j]还是*，dp[i][j] = dp[i+1][j]
            //                        3种情况综合 dp[i][j] = dp[i][j+1] || dp[i+1][j+1] || dp[i+1][j] = dp[i][j+1] || dp[i+1][j]， 因为dp[i+1][j]依赖于dp[i+1][j+1]
            // dp[i][j]依赖于dp[i][j+1]， dp[i+1][j]，dp[i+1][j+1]， 因此变量i从高往低遍历，j从高往低遍历

            // 使用变量记住dp[i+1][j+1]的状态
            boolean rightDown = false;
            for(int i=s.length()-1; i>=0; i--) {
                // 初始时，rightDown = dp[i+1][p.length()]的值
                rightDown = i == s.length()-1 ? true : false;
                for(int j=p.length()-1; j>=0; j--) {
                    boolean tmp = dp[j]; // 先保存dp[i+1][j]
                    if(p.charAt(j) != '*') {
                        // 情况1，p[j] != '*'时，s[i]和p[j]不匹配，dp[i][j]=false, 否则dp[i][j] = dp[i+1][j+1]
                        dp[j] = (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') ? rightDown : false;
                    } else {
                        // 情况2，dp[i][j] = '*'
                        dp[j] = dp[j] || dp[j+1];
                    }
                    rightDown = tmp; // 更新给rightDown
                }
            }
            return dp[0];
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        String s = "bbbbbbbabbaabbabbbbaaabbabbabaaabbababbbabbbabaaabaab";
        String p = "b*b*ab**ba*b**b***bba";

        app.isMatch(s, p);
        System.out.println(app.isMatch(s, p));
    }
}
