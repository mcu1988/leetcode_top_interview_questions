package T0038_count_and_say;

import java.util.ArrayList;
import java.util.List;

class Solu {

    private Solution app;

    static class Solution {

        // 方法
        // 循环，先解决前面的n-1个数，得到n-1位置的值，再解决n位置的值
        public String countAndSay(int n) {
            if(n < 1) return "";
            if(n == 1) return "1";

            String result = "1";
            int cnt = 0;

            for(int i=2; i<=n; i++) { // n=1直接返回"1"， cong n=2开始遍历
                StringBuilder sb = new StringBuilder();
                cnt = 1;
                // 从j=1开始遍历n-1的result，判断j位置和j-1位置是否一致
                for(int j=1; j<result.length(); j++) {
                    if(result.charAt(j) == result.charAt(j-1)) {
                        // j位置和j-1位置一致， j-1位置的元素数量cnt++
                        cnt++;
                    } else {
                        // 不一致，结算j-1位置的元素，j位置的袁术出现次数为1
                        sb.append(cnt);
                        sb.append(result.charAt(j-1));
                        cnt = 1;
                    }
                }
                // 循环结束，结算最后一个位置的字符串
                sb.append(cnt);
                sb.append(result.charAt(result.length()-1));

                // 更细result
                result = sb.toString();
            }
            return result;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        app.countAndSay(4);
        System.out.println(app.countAndSay(4));
    }
}
