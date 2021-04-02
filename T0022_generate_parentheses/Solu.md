# leetcode t22
- 给定正整数n，表示小括号的对数，返回所有可能额合法小括号排序
- eg， 
    - n = 3, return ["((()))","(()())","(())()","()(())","()()()"]
        
# 思路
- 总共是一个2n的字符串，考虑每个位置上放什么括号
- 设计递归函数f(Stings s, int index, int leftUsed, int rightUsed, int total, List<String> result)
    - s表示前0~index-1已经生成的字符串
    - index表示当前要考虑的字符串位置
    - leftUsed和rightUsed表示的左已经使用的左括号数量和右括号数量
    - total表示总的括号数量
- f实现
    - 当index == 2*total时，添加s到结果集，返回
    - 当total - leftUsed > 0时，添加左括号，因为什么时候都可以添加左括号，只要左括号还有剩余
    - 当leftUsed - rightUsed > 0时，添加右括号，只有左括号数量大于右括号数量时，可以添加右括号
- 总共生成的括号数量是第n项卡特兰数, O(4^n/(n * sqrt(n))), n是括号长度，时间复杂度，O(n) * O(4^n/(n * sqrt(n))) = O(4^n/(sqrt(n)))，空间复杂度O(n)
