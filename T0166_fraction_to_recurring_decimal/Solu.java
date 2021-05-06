package T0166_fraction_to_recurring_decimal;

import java.util.HashMap;

class Solu {

    static class Solution {

        // 方法
        // 根据正负，添加"-"
        // 根据结尾是否是小数，添加"."
        // 数字全部转为正数处理
        // 负数最小值转为正数时会越界，使用long存储
        // 小数部分每次把余数放大10倍，继续相除，计算余数
        // 使用map记录 余数 -> 索引的映射
        public String fractionToDecimal(int numerator, int denominator) {
            if(denominator == 0) return "";
            if(numerator == 0) return "0";

            StringBuilder sb = new StringBuilder();
            // 结果为负数，先添加"-"
            if((numerator < 0) ^ (denominator < 0)) sb.append("-");

            // 转为正数处理，转为long处理，因为负数最小值转为正数时越界，需要使用long存储
            long nume = numerator < 0 ? -1L*numerator : numerator;
            long deno = denominator < 0 ? -1L*denominator : denominator;

            // 添加小数点前的整数部分
            sb.append(nume / deno);

            nume = nume % deno;

            // 结尾为整数，直接返回
            if(nume == 0) return sb.toString();

            // 结尾为小数，添加小数点
            sb.append(".");

            // map记录 余数 -> 字符串位置的映射
            HashMap<Long, Integer> map = new HashMap<>();
            map.put(nume, sb.length());

            while(nume != 0) {
                // 余数每次放大10倍
                nume *= 10;
                sb.append(nume / deno);

                // 继续计算余数
                nume = nume % deno;
                if(map.containsKey(nume)) {
                    // 如果余数出现重复，对循环部分添加括号
                    sb.insert(map.get(nume), "(");
                    sb.append(")");
                    break;
                } else {
                    map.put(nume, sb.length());
                }
            }
            return sb.toString();
        }
    }


    public static void main(String[] args) {

        Solution app = new Solution();

        int numerator = -1, denominator = Integer.MIN_VALUE;
        app.fractionToDecimal(numerator, denominator);

    }
}
