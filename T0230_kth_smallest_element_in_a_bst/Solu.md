# leetcode t230
- 搜索二叉树的第k小的数字
- k从1开始计数
- eg,
    - ![](./imgs/1.png)
    
# 方法1
- 中序遍历，到第k个时，返回
- 时间复杂度O(k), 空间复杂度O(1)