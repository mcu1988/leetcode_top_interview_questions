# leetcode t46
- 数组的全排列
- 给定一个整数数组，输出元素的全排列
- 数组中不包含重复元素
- eg,
    - nums = [1,2,3], return [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
        
# 思路
- 使用变量index意义遍历数组的每一个位置
- 当前位置可以与index...end位置的元素进行交换
- 当前位置确定后，继续看下一个位置
- 设计递归函数f(int[] nums, int index, List<Integer> list, List<List<Integer> result>)
    - index表达当前要处理的位置
    - list表示已经处理的元素的集合
    - result表示结果集
- 时间复杂度O(N!), 空间复杂度O(N)