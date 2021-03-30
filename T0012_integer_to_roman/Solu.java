package T0012_integer_to_roman;

class Solu {
    static class Solution {

        // 解法1
        // 从大的单位往小的单位遍历，先堆大的单位取模
        // 取模之前需要先判断特殊数字，如4,9等
        public String intToRoman1(int num) {
            if (num < 1) return "";

            // 存储单位和数字的对应关系
            String[] units = {"M", "D", "C", "L", "X", "V", "I"};
            int[] nums = {1000, 500, 100, 50, 10, 5, 1};

            StringBuilder result = new StringBuilder();

            // startIndex表示遍历到的单位
            int startIndex = 0;

            int mod = 0;
            while (num > 0) {

                // 先判断当前是否是否需要特殊处理，如4,9,40,90,94等需要特殊处理
                num = specialNum(num, result);

                // 不需要特殊处理再对当前的单位取模
                mod = num / nums[startIndex];
                while (mod > 0) {
                    result.append(units[startIndex]);
                    mod--;
                }
                num = num % nums[startIndex];
                startIndex++;
            }
            return result.toString();
        }

        // 处理特殊数字，如94等
        public int specialNum(int num, StringBuilder result) {

            if (num >= 900 && num < 1000) {
                result.append("CM");
                num -= 900;
            } else if (num >= 400 && num < 500) {
                result.append("CD");
                num -= 400;
            } else if (num >= 90 && num < 100) {
                result.append("XC");
                num -= 90;
            } else if (num >= 40 && num < 50) {
                result.append("XL");
                num -= 40;
            } else if (num == 9) {
                result.append("IX");
                num -= 9;
            } else if (num == 4) {
                result.append("IV");
                num -= 4;
            }
            return num;
        }


        // 解法2
        // 由于题目给定的数字范围是1~3999
        // 建立表格，直接对每个十进制数查表
        public String intToRoman(int num) {
            if (num < 1) return "";

            // 数字0使用空字符串代替
            String[][] c = {
                    {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
                    {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
                    {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                    {"", "M", "MM", "MMM"}};

            StringBuilder result = new StringBuilder();

            // 依次找到每个十进制位上的数字，查表，拼接起来
            result.append(c[3][num / 1000])
                    .append(c[2][num % 1000 / 100])
                    .append(c[1][num % 100 / 10])
                    .append(c[0][num % 10]);

            return result.toString();
        }
    }


    public static void main(String[] args) {
        Solution app = new Solution();
        app.intToRoman(1994);
        System.out.println(app.intToRoman(1994));
    }

}
