package T0084_largest_rectangle_in_histogram;

import java.util.Stack;

class Solu {

    static class Solution {

        // 方法1
        // 遍历数组，以当前点为中心扩展出去，求得当前点的最大面积
        // 遍历完所有点时，得到整个数组的最大面积
        // 时间复杂度O(N^2), 空间复杂度O(1)
        public int largestRectangleArea1(int[] heights) {
            if(heights == null || heights.length == 0)
                return 0;

            int result = 0;
            int start = 0, end = 0, area = 0;
            for(int i=0; i<heights.length; i++) {
                // 从当前位置往左右两边扩展，遇到比当前位置小的停止
                start = i;
                end = i;
                while(start >= 0 && heights[start] >= heights[i]) start--;
                while(end < heights.length && heights[end] >= heights[i]) end++;
                // 计算对当前位置的面积
                area = heights[i] * (end - start - 1);
                result = Math.max(result, area);
            }
            return result;
        }


        // 方法2
        // 使用严格单调递增的单调栈，栈中存放数组的索引
        // 遍历数组，当前元素>栈顶元素，直接入栈
        // 当前元素<=栈顶元素，一直出栈，直到当前元素>栈顶元素
        // 出栈时，结算栈顶元素对应的面积，当前位置是栈顶元素右侧最近的比他小的，栈顶的下一个位置是左边最近的比他小的
        // 时间复杂度O(N), 空间复杂度O(N)
        public int largestRectangleArea2(int[] heights) {
            if (heights == null || heights.length == 0)
                return 0;

            // stack中存放数组的索引，从栈底到栈顶对应的数组的值严格递增
            Stack<Integer> stack = new Stack<>();
            // 初始时，存放的index为-1
            stack.push(-1);

            int result = 0, area = 0, cur = 0;
            for(int i=0; i<heights.length; i++) { // 从0位置开始遍历数组
                // 栈中有值，并且栈顶的值大于当前值，出栈，结算栈顶元素对应的矩形的面积
                // 当前元素等于栈顶元素，出栈，结算栈顶元素，相等的值每个都会结算，后结算的值面积大于新结算的值
                while(stack.size() > 1 && heights[i] <= heights[stack.peek()]) {
                    // 栈顶元素大小heights[stack.pop()]
                    // i位置是栈顶右边比他小的第一个位置
                    // 栈顶的下一个位置是左边比他小的第一个位置
                    // 宽度是当前值 i - 栈顶的下一个元素的索引stack.peek() - 1
                    cur = stack.pop();
                    area = (i - stack.peek() - 1) * cur;
                    result = Math.max(result, area);
                }
                // 入栈有2种情况
                // 1. 当前元素的值第一次比较就大于栈顶元素
                // 2. 当前元素的值第一次比较小于或者等于栈顶元素，一直出栈，知道满足当前元素的值大于栈顶元素
                stack.push(i);
            }

            // 循环结束后，结算栈中的元素
            while(stack.size() > 1) {
                // 剩余的元素中右边比他小的最近的位置是heights.length
                // 左边比他小的最近的位置是栈顶的下一个位置
                cur = stack.pop();
                area = (heights.length - stack.peek() - 1) * cur;
                result = Math.max(result, area);
            }

            return result;
        }

        // 方法3
        // 思路和方法2一致
        // 使用数组代替栈
        // 时间复杂度O(N), 空间复杂度O(N)
        public int largestRectangleArea(int[] heights) {
            if (heights == null || heights.length == 0)
                return 0;

            // stack中存放数组的索引，从前往后对应的数组的值严格递增
            int[] arr = new int[heights.length + 1];
            // 初始时，存放的index为-1
            arr[0] = -1;

            // 使用len标记数组当前的有效长度
            int len = 1;

            int result = 0, area = 0, cur = 0;
            for(int i=0; i<heights.length; i++) {
                while(len > 1 && heights[i] <= heights[arr[len-1]]) {
                    cur = heights[arr[len-1]];
                    len--;
                    area = cur * (i - arr[len-1] - 1);
                    result = Math.max(result, area);
                }
                arr[len++] = i;
            }

            while(len > 1) {
                cur = heights[arr[len-1]];
                len--;
                area = cur * (heights.length - arr[len-1] - 1);
                result = Math.max(result, area);
            }

            return result;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] heighs = {2,1,5,6,2,3};

        app.largestRectangleArea(heighs);

        System.out.println(app.largestRectangleArea(heighs));

    }
}
