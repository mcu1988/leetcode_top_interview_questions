package T0155_min_stack;

import java.util.Stack;

class Solu {

    static class Solution {

        // 方法1
        // 使用2个栈data和min，2个栈中的元素个数相同
        // data入栈时，min同时也入栈，如果val < min栈顶元素，min入栈val，否则入栈min栈顶元素
        // 出栈时，2个栈同时出栈
        // 时间复杂度O(N), 空间复杂度O(N)
        static class MinStack1 {
            Stack<Integer> data;
            Stack<Integer> min;

            MinStack1() {
                data = new Stack<>();
                min = new Stack<>();
            }

            public void push(int val) {
                // 入栈时，如果栈为空，或者val < min栈顶元素，min入栈val
                // 否则入栈min栈顶元素
                if(data.isEmpty() || val < min.peek()) {
                    min.push(val);
                } else {
                    min.push(min.peek());
                }
                data.push(val);
            }

            public void pop() {
                // 出栈时，2个栈同步出栈
                if(!data.isEmpty()) {
                    data.pop();
                    min.pop();
                }
            }

            public int top() {
                int result = 0;
                if(!data.isEmpty()) {
                    result = data.peek();
                }
                return result;
            }

            public int getMin() {
                int result = 0;
                if(!data.isEmpty()) {
                    result = min.peek();
                }
                return result;
            }
        }

        // 方法2
        // 使用2个栈data和min，2个栈中的元素个数可能不同
        // data入栈时，如果val <= min栈顶元素，min入栈val，否则不入栈
        // 出栈是，data栈顶元素==min栈顶元素，min出栈
        // 时间复杂度O(N), 空间复杂度O(N)
        static class MinStack {
            Stack<Integer> data;
            Stack<Integer> min;

            MinStack() {
                data = new Stack<>();
                min = new Stack<>();
            }

            public void push(int val) {
                // 入栈时，如果栈为空，或者val <= min栈顶元素，min入栈val
                if(data.isEmpty() || val <= min.peek()) {
                    min.push(val);
                }
                data.push(val);
            }

            public void pop() {
                // 出栈时，如果data栈和min栈栈顶元素相等，min出栈
                if(!data.isEmpty()) {
                    int val = data.pop();
                    if(val == min.peek()) {
                        min.pop();
                    }
                }
            }

            public int top() {
                int result = 0;
                if(!data.isEmpty()) {
                    result = data.peek();
                }
                return result;
            }

            public int getMin() {
                int result = 0;
                if(!data.isEmpty()) {
                    result = min.peek();
                }
                return result;
            }
        }

    }


    public static void main(String[] args) {
        Solution app = new Solution();

        Solution.MinStack minStack = new Solution.MinStack();

        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();
        minStack.pop();
        minStack.top();
        minStack.getMin();
    }
}
