package T0014_longest_common_prefix;

import org.omg.CORBA.MARSHAL;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Arrays;

class Solu {
    static class Solution {

        // 方法
        // 以第一个为最长公共串，依次比较后面的字符串与当前字符串的交集
        // 时间复杂度O(m*n), m是字符串数量, n是公共子串长度 空间复杂度O(1)
        public String longestCommonPrefix(String[] strs) {
            if(strs == null || strs.length == 0) return "";

            // 最长公共子串默认为第一个
            String result = strs[0];
            int maxCommon = result.length(), tmp = 0;
            for(int i=1; i<strs.length; i++) { // 遍历从1开始的每一个字符串
                tmp = 0;
                // 找到第一个字符串和每一个字符串的公共子串长度
                while(tmp < maxCommon && tmp < strs[i].length() && result.charAt(tmp) == strs[i].charAt(tmp)) {
                    tmp++;
                }
                // 更新长度
                maxCommon = Math.min(maxCommon, tmp);
            }
            return result.substring(0, maxCommon);
        }

    }

    public static void main(String[] args) {
        Solution app = new Solution();
        String[] strs = {"dog","racecar","car"};
        app.longestCommonPrefix(strs);
        System.out.println(app.longestCommonPrefix(strs));
    }

}
