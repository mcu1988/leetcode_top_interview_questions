package T0028_Implement_strStr;

class Solu {

    static class Solution {

        // 方法1
        // haystack字符串和needle字符串一个个匹配
        // 时间复杂度，O(n * m)，n是haystack长度，m是needle长度，空间复杂度O(1)
        public int strStr1(String haystack, String needle) {
            // 如果needle为空，直接返回0，题目要求
            if(needle == null || needle.length() == 0) return 0;
            // 如果haystack为空 或者 haystack的长度小于needle的长度，返回-1
            if(haystack == null || haystack.length() < needle.length()) return -1;

            // 从0开始遍历haystack
            for(int i=0; i<=haystack.length() - needle.length(); i++) {
                int j = 0;
                while(j < needle.length()) {
                    // haystack和needle的位置一个个匹配
                    if(haystack.charAt(i+j) != needle.charAt(j)) {
                        break;
                    }
                    j++;
                }
                // 如果匹配成功，直接返回i
                if(j == needle.length()) return i;
            }

            // 遍历结束后，仍然没有返回，说明匹配不成功，返回-1
            return -1;
        }

        // 方法2
        // KMP算法
        // 时间复杂度，O(n)，空间复杂度O(1)
        public int strStr(String haystack, String needle) {
            // 如果needle为空，直接返回0，题目要求
            if (needle == null || needle.length() == 0) return 0;
            // 如果haystack为空 或者 haystack的长度小于needle的长度，返回-1
            if (haystack == null || haystack.length() < needle.length()) return -1;

            return KMP(haystack, needle);
        }

        public int KMP(String haystack, String needle) {

            int[] nexts = getNexts(needle);

            // i表示haystack的比较位置，j表示needle的比较位置
            int i = 0, j = 0;
            while(i < haystack.length() && j < needle.length()) {
                if(haystack.charAt(i) == needle.charAt(j)) {
                    // 当前位置匹配上，i和j都加1
                    i++;
                    j++;
                } else if(j == 0) {
                    // 没匹配上，如果j=0，代表第1个就没匹配上，i加1，从下一个继续开头开始匹配
                    i++;
                } else {
                    // 否则j继续沿着nexts数组向前跳，i位置保持不动，i位置和nexts[j]位置相比较
                    j = nexts[j];
                }
            }
            return j == needle.length() ? i - j : -1;
        }

        public int[] getNexts(String needle) {
            if(needle == null || needle.length() == 0) return null;
            if(needle.length() == 1) return new int[] {-1};
            if(needle.length() == 2) return new int[] {-1, 0};

            // 准备一个与needle长度相等的nexts数组
            int[] nexts = new int[needle.length()];
            nexts[0] = -1;
            nexts[1] = 0;
            // 0位置和1位置的值已经确定，从2位置开始遍历
            int index = 2;
            // i-1位置的next数组的值,初始为i=2时，i-1=1位置上的next数组的值0
            int preNext = 0;

            while(index < needle.length()) { // 从2位置开始遍历
                // 如果i-1位置和 i-1所对应的nexts数组位置的值相等，nexts[i] = next[i-1] + 1;
                if(needle.charAt(index-1) == needle.charAt(preNext)) {
                    // 拆解为nexts[i] = next[i-1] + 1; i++; next[i-1] =next[i-2] + 1, 即上一个preNext+1
                    nexts[index++] = ++preNext;
                } else if(preNext == 0) {
                    // 如果preNext == 0时，第0个位置和第i-1位置的值不相等，nexts[i] = 0
                    nexts[index++] = 0;
                } else {
                    // 否则沿着nexts数组往前跳，直到preNext = 0，进入下一轮循环，此时比较数组第0个位置和第i-1位置的值是否相等
                    preNext = nexts[preNext];
                }
            }
            return nexts;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        String haystack = "aaab";
        String needle = "ab";
        app.strStr(haystack, needle);
        System.out.println(app.strStr(haystack, needle));


    }
}
