# leetcode t200
- 0,1矩阵中岛的数量
- eg,
    - ![](./imgs/1.png)
    
# 方法1
- 遍历矩阵，遇到1，计数加1，并且把这个1周围的1全部改成2
- 时间复杂度O(M*N), 空间复杂度O(M*N)
