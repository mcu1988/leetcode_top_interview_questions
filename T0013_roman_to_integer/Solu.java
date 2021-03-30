package T0013_roman_to_integer;

import java.util.HashMap;

class Solu {
    static class Solution {

        // 解法
        // 依次遍历字符串，查表得到对应数值
        // 注意先判断连续2个字符是否有效，再判断1个字符是否有效
        public int romanToInt(String s) {
            if (s == null || s.length() == 0) return 0;

            // 存储单位和数字的对应关系
            HashMap<String, Integer> map = new HashMap<>();
            map.put("M", 1000);
            map.put("D", 500);
            map.put("C", 100);
            map.put("L", 50);
            map.put("X", 10);
            map.put("V", 5);
            map.put("I", 1);
            map.put("IV", 4);
            map.put("IX", 9);
            map.put("XL", 40);
            map.put("XC", 90);
            map.put("CD", 400);
            map.put("CM", 900);

            int len = s.length();
            int index = 0;
            int result = 0;
            while (index < len) {
                // 先判断连续2个字母
                if(index+1 < len && map.containsKey(s.substring(index, index+2))) {
                    result += map.get(s.substring(index, index+2));
                    index += 2;
                } else if (map.containsKey(s.substring(index, index+1))) {
                    // 再判断连续一个字母
                    result += map.get(s.substring(index, index+1));
                    index += 1;
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {
        Solution app = new Solution();
        app.romanToInt("LVIII");
        System.out.println(app.romanToInt("MCMXCIV"));
    }



}
