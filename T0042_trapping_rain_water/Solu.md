# leetcode t42
- 下雨后的最大蓄水量
- 给定一个元素为非负整数的数组，求整个数组的蓄水量
- 数组长度0 <= n <= 3 * 104
- eg,
    - height = [0,1,0,2,1,0,1,3,2,1,2,1], return 6
        - ![](./imgs/1.png)
    
# 思路1
- 考虑当前点上存储的水量
- 使用个辅助数组，分别存放左边和右边的最大值，从左往右遍历，得到<=i位置的最大值，从右往左遍历，得到>=i的最大值
- 当前点的蓄水量 min(left, right) - height[i]
- 0和len-1位置没有水量，把[1...len-2]位置的蓄水量相加就是结果
- 时间复杂度O(N), 空间复杂度O(N)

# 思路2
- 思路和方法1一致, 方法2只需要遍历数组一次，并且不需要辅助数组
- 当前位置的蓄水量需要知道左边和右边最大值数组中的最小值
- 使用左右两个指针，初始时，l=1, r=len-2, 使用两个最大值leftMax，rightMax，分别记录左边[0...l]和右边[r...len-1]的最大值
- 找到leftMax和rightMax较小的那个，假设leftMax较小，那么l位置右边的最大值>=rightMax, l位置的最小高度是leftMax, l位置可以结算, l++, 更新leftMax
- l > r时循坏结束
- 时间复杂度O(N), 空间复杂度O(1)