package T0044_wildcard_matching;

class Solu {

    static class Solution {

        long a = 0;

        public boolean isMatch1(String s, String p) {
            if((p == null || p.length() == 0) && (s.length() != 0)) return  false;

            boolean result =  process(s, 0, p, 0);
            System.out.println(a);
            return result;
        }

        public boolean process(String s, int i, String p, int j) {
            a ++;
            if(i == s.length()) {
                if(j == p.length()) return true;
                else if(p.charAt(j) != '*') return false;
                else return process(s, i, p, j+1);
            }

            if(j == p.length()) return false;

            if(p.charAt(j) != '*') {
                if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                    return process(s, i+1, p, j+1);
                } else return false;
            }

            for(int k=i; k<=s.length(); k++) {
                if(process(s, k, p, j+1)) return true;
            }
            return false;
        }


        public boolean isMatch2(String s, String p) {
            if((p == null || p.length() == 0) && (s.length() != 0)) return  false;

            int[][] map = new int[s.length()+1][p.length()+1];
            int result =  process1(s, 0, p, 0, map);
            System.out.println(a);
            return result == 1;
        }

        public int process1(String s, int i, String p, int j, int[][] map) {

            if(map[i][j] != 0) return map[i][j];
            a++;

            if(i == s.length()) {
                if(j == p.length()) {
                    map[i][j] = 1;
                    return map[i][j];
                }
                else if(p.charAt(j) != '*') {
                    map[i][j] = 2;
                    return map[i][j];
                } else {
                    map[i][j] = process1(s, i, p, j+1, map);
                    return map[i][j];
                }
            }

            if(j == p.length()) {
                map[i][j] = 2;
                return map[i][j];
            }

            if(p.charAt(j) != '*') {
                if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                    map[i][j] = process1(s, i+1, p, j+1, map);
                    return map[i][j];
                } else {
                    map[i][j] = 2;
                    return map[i][j];
                }
            }

            for(int k=i; k<=s.length(); k++) {
                map[i][j] = process1(s, k, p, j+1, map);
                if(map[i][j] == 1) return map[i][j];
            }
            return map[i][j];
        }

        public boolean isMatch(String s, String p) {
            if ((p == null || p.length() == 0) && (s.length() != 0)) return false;

            boolean[][] dp = new boolean[s.length()+1][p.length()+1];
            dp[s.length()][p.length()] = true;
            for(int i=p.length()-1; i>=0; i--) {
                dp[s.length()][i] = p.charAt(i) == '*' ? dp[s.length()][i+1] : false;
            }

            for(int i=s.length()-1; i>=0; i--) {
                for(int j=p.length()-1; j>=0; j--) {
                    if(p.charAt(j) != '*') {
                        dp[i][j] = dp[i][j] || ((s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') ? dp[i+1][j+1] : false);
//                        if(dp[i][j]) continue;
                    } else {
//                        for(int k=i; k<=s.length(); k++) {
//                            dp[i][j] = dp[i][j] || dp[k][j+1];
//                            if(dp[i][j]) break;
//                        }
                        dp[i][j] = dp[i][j] || dp[i+1][j] || dp[i][j+1];
                    }
                }
            }
            return dp[0][0];
        }


    }

    public static void main(String[] args) {

        Solution app = new Solution();

//        String s = "bbbbbbbabbaabbabbbbaaabbabbabaaabbababbbabbbabaaabaab";
//        String p = "b*b*ab**ba*b**b***bba";

        String s = "adceb";
        String p = "*a*b";

        app.isMatch(s, p);
        System.out.println(app.isMatch(s, p));
    }
}
