# leetcode t204
- 给定n，判断<n的质数数量
- eg,
    - n = 10, return 4

    
# 方法1
- Sieve of Eratosthenes, 参考https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
- 假设n是质数，那么n的倍数不是质数，可以清除
- 从1遍历到sqrt(n)，使用数组标记每个数字是否是质数
- 时间复杂度O(n*log(log(n))), 空间复杂度O(n)
- java实现耗时27ms

# 方法2
- 思路和方法1一致
- 倍数筛选时，从i^2开始，每次增加2i, 减少筛选次数
- i从3开始遍历，每次增加2
- 时间复杂度O(n*log(log(n))), 空间复杂度O(n)
- java实现耗时16ms

