package T0242_valid_anagram;

import java.util.*;

class Solu {

    static class Solution {


        // 方法1
        // 使用数组记录s中每个字母出现的次数
        // 查看t中祖母出现次数是否一致
        // 时间复杂度O(N), 空间复杂度O(26)
        public boolean isAnagram(String s, String t) {
            if(s == null || t == null || s.length() != t.length())
                return false;

            int[] cnt = new int[26];
            for(int i=0; i<s.length(); i++) {
                cnt[s.charAt(i) - 'a']++;
                cnt[t.charAt(i) - 'a']--;
            }
            for(int i=0; i<26; i++) {
                if(cnt[i] != 0) return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        String s = "anagram", t = "nagaram";

        app.isAnagram(s, t);
        System.out.println(app.isAnagram(s, t));
    }
}
