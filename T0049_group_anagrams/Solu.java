package T0049_group_anagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Solu {

    static class Solution {

        // 方法1
        // 把每个字符串按照26个字母出现的次数拼接起来
        // 时间复杂度O(n*k), 空间复杂度O(n)
        public List<List<String>> groupAnagrams1(String[] strs) {
            if(strs == null || strs.length == 0) return new ArrayList<>();

            // 存储  encode -> group的映射
            HashMap<String, List<String>> map = new HashMap<>();
            for(String str : strs) {
                // 每个字符串有26个字母构成
                int[] elem = new int[26];

                // 计算每个字符出现的次数
                for(int i=0; i<str.length(); i++) {
                    elem[str.charAt(i) - 'a']++;
                }

                // 按照每个字符出现的次数拼接起来
                String encode = "";
                for(int i=0; i<26; i++) {
                    encode = encode + (i + 'a') + elem[i];
                }

                // map中分组
                if(map.containsKey(encode)) {
                    map.get(encode).add(str);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(str);
                    map.put(encode, list);
                }
            }

            return new ArrayList<>(map.values());
        }

        // 方法2
        // 对每个字符串进行排序
        // 时间复杂度O(n*k*log(k)), 空间复杂度O(n)
        public List<List<String>> groupAnagrams(String[] strs) {
            if (strs == null || strs.length == 0) return new ArrayList<>();

            // 存储  encode -> group的映射
            HashMap<String, List<String>> map = new HashMap<>();
            for(String str : strs) {

                // 对每个字符串进行排序
                char[] arr = str.toCharArray();
                Arrays.sort(arr);
                String encode = new String(arr);

                // map中分组
                if(map.containsKey(encode)) {
                    map.get(encode).add(str);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(str);
                    map.put(encode, list);
                }
            }

            return new ArrayList<>(map.values());
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        app.groupAnagrams(strs);
        System.out.println(app.groupAnagrams(strs));
    }
}
