# leetcode t189
- 把数组后k个数字迁移到前面，前面的数字迁移到后面
- 0 <= k <= 10^5, k有可能大于数组长度
- eg,
    - ![](./imgs/1.png)
    
# 方法
- 先把整个数组逆序，再逆序前k个数字得到前半部分，再逆序后半部分
- k > N时，先对N取余
- 时间复杂度O(N), 空间复杂度(1))