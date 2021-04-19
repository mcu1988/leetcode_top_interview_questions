package T0076_minimum_window_substring;

class Solu {

    static class Solution {

        // 方法
        // 使用长度为256的数组记录t中每个字符出现的次数
        // 使用变量remainCnt记录剩余待匹配的字符数
        // 从前往后遍历s，当remainCnt==0时，使用start变量从前往后遍历，直到start位置的字符出现次数为0，
        // 此时[start...end]窗口内刚好包含了t，记录并更新结果的起始位置和长度
        // 时间复杂度O(N), N是s的长度，空间复杂度O(256)
        public String minWindow(String s, String t) {
            if(s == null || s.length() == 0 || t == null || t.length() == 0 || s.length() < t.length()) return "";

            // 使用256的数组记录每个字符出现的次数
            int[] cnt = new int[256];
            for(int i=0; i<t.length(); i++) {
                cnt[t.charAt(i)]++;
            }

            // 剩余待匹配字符的数量
            int remainCnt = t.length();

            // start和end确定一个窗口
            int start = 0, end = 0, resultStart = 0, len = Integer.MAX_VALUE;

            while(end < s.length()) {
                // 当前字符时待匹配的次数 > 0时，代表字符是t中的字符，并且该字符还没匹配完，remainCnt--
                if(cnt[s.charAt(end)] > 0) remainCnt--;
                // s中出现的字符次数都减1
                // 不在t中的字符cnt变为负数，t中的字符，如果出现次数比需要的次数多，也会变为负数
                cnt[s.charAt(end)]--;

                // t中已经匹配完
                while(remainCnt == 0) {

                    // 找到start的次数为0的点，确定窗口，更新result
                    // 如果start位置不在t中，cnt为负数
                    // 如果start位置不在t中，cnt可能为负数或者0，为0时start刚好是窗口的起始位置
                    // 为负数时，证明该字符出现的次数超过了需要的次数，继续让start向后遍历，直到start位置的cnt是0
                    if(cnt[s.charAt(start)] == 0) {
                        // [start...end]是目标窗口
                        if(end - start + 1 < len) {
                            len = end - start + 1;
                            resultStart = start;
                        }
                        remainCnt++;
                    }
                    cnt[s.charAt(start)]++;
                    start++;
                }
                end++;
            }

            return len == Integer.MAX_VALUE ? "" : s.substring(resultStart, resultStart + len);
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {2,0,2,1,1,0};

        String s = "bba", t = "ab";

        app.minWindow(s, t);

        System.out.printf(app.minWindow(s, t));
    }
}
