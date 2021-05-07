# leetcode t179
- 给定正和0的数组，求拼接成的最大整数
- eg,
    - nums = [10,2], return 210
    - nums = [0，0，0], return 0
    
# 方法
- 把数组转string数组
- 把数组按拼接序排序
- 时间复杂度O(N*log(N)), 空间复杂度(N)