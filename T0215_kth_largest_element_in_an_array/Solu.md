# leetcode t215
- 在数组中寻找第k大的数
- eg,
    
# 方法1
- 使用一个大小为k的最小堆
- 初始加入k个元素到堆
- 再遍历剩下的元素，当元素大于堆顶元素时，弹出堆顶元素，加入当前元素
- 时间复杂度O(N*log(k)), 空间复杂度O(k)
- java实现耗时3ms

# 方法2
- 使用快排partition过程，随机选取pivot，换分为 <pivot、=pivot、>pivot的区域
- 把第k大转换为第nums.length-k+1小
- 平均时间复杂度O(N), 使用master公式计算,和partition过程的pivot选取有关，空间复杂度O(N)
- 使用最后一个元素作为pivot，java实现耗时18ms
- 随机选取一个元素作为pivot，java实现耗时2ms

# 方法3
- bfprt算法
- 方法2的pivot是随机选取的，平均时间复杂度O(N), 最差退化为O(N^2)
- 方法3使用bfprt算法选择pivot，使时间复杂度严格收敛于O(N)
- pivot选取方法
    - 先把[l, r]范围上的数字划分为5个是数字一组, 数字5可以换成其他数字
    - 组内排序
    - 找到组内的中位数，组成新数组
    - 找到新数组的中位数
- 时间复杂度O(N), 使用master公式计算，空间复杂度O(N)
- java实现耗时1ms

