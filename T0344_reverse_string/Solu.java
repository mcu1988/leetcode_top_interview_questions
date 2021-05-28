package T0344_reverse_string;

class Solu {

    static class Solution {

        // 方法
        // 两边对换
        // 时间复杂度O(N), 空间复杂度O(1)
        public void reverseString(char[] s) {
            if(s == null || s.length < 2)
                return;

            for(int i=0, j=s.length-1; i<j; i++, j--) {
                char tmp = s[i];
                s[i] = s[j];
                s[j] = tmp;
            }
        }

        public void printArr(char[] arr) {
            for(char v : arr) System.out.print(v + " ");
            System.out.println();
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        char[] s = {'h','e','l','l','o'};

        app.printArr(s);
        app.reverseString(s);
        app.printArr(s);

    }
}
