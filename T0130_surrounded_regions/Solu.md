# leetcode t130
- 把矩阵中被包围的值改变
- 给定只包含'O'和'X'的矩阵，把被'X'包含的'O'改为'X'
- eg
    - ![](./imgs/1.png)

# 思路
- 边界上的'O'无法被包围，只需要把边界上的'O'拓展出去, 'O'改为'A'
- 剩下的'O'是可以被包围的
- 重新遍历数组，把'O'改为'X'，把'A'改为'O'
- 时间复杂度O(M*N), 空间复杂度O(1)
