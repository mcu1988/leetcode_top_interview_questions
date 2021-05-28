package T0341_flatten_nested_list_iterator;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

class Solu {

    static class Solution {

        public interface NestedInteger {

            // @return true if this NestedInteger holds a single integer, rather than a nested list.
            public boolean isInteger();

            // @return the single integer that this NestedInteger holds, if it holds a single integer
            // Return null if this NestedInteger holds a nested list
            public Integer getInteger();

            // @return the nested list that this NestedInteger holds, if it holds a nested list
            // Return empty list if this NestedInteger holds a single integer
            public List<NestedInteger> getList();
        }

        // 方法1
        // 使用栈把加入的list按照逆序入栈
        // 获取next时，如果栈顶元素是list，重复按照逆序入栈，直到拆分出整数
        public static class NestedIterator implements Iterator<Integer> {

            // 初始化栈
            Stack<NestedInteger> stack;

            public NestedIterator(List<NestedInteger> nestedList) {
                stack = new Stack<>();
                listReverseToStack(nestedList);
            }

            @Override
            public Integer next() {
                if(!hasNext()) return null;
                return stack.pop().getInteger();
            }

            @Override
            public boolean hasNext() {
                // 如果栈顶元素是list，打散入栈，知道栈顶元素是int类型
                while(!stack.isEmpty() && !stack.peek().isInteger()) {
                    listReverseToStack(stack.pop().getList());
                }
                return !stack.isEmpty();
            }

            // list元素逆序入栈，这样出栈时，list的第1个元素先出栈
            public void listReverseToStack(List<NestedInteger> nestedList) {
                for(int i=nestedList.size()-1; i>=0; i--) {
                    stack.push(nestedList.get(i));
                }
            }
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();



    }
}
