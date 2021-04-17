package T0066_plus_one;

import java.util.Arrays;

class Solu {

    static class Solution {

        // 方法
        // 从后往前遍历，遇到等于9的数字，当前位置0，<9的数字，当前Wie加1，返回
        // 遍历过程中，如果还没返回，证明原始都是9，返回10000...的形式
        // 时间复杂度O(N)
        public int[] plusOne(int[] digits) {
            if(digits == null || digits.length == 0) return null;

            int[] result = Arrays.copyOf(digits, digits.length);

            for(int i=digits.length-1; i>=0; i--) { // 从后往前遍历
                // 遇到<9的数组，加1后，直接返回
                if(result[i] < 9) {
                    result[i]++;
                    return result;
                }
                // == 9的数字，当前位置0
                result[i] = 0;
            }

            // 循坏中没有返回，证明原始数字是9999...的形式，返回10000...的形式
            int[] jinweiResult = new int[digits.length+1];
            jinweiResult[0] = 1;
            return jinweiResult;
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] digits = {9, 9};

        System.out.println(app.plusOne(digits));

    }
}
