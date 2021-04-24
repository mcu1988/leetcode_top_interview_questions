# leetcode t125
- 判断字符串是否回文
- 给定包含可打印ascii字符的字符串，只考虑字母和数字，忽略字母的大小写，求是否是回文
- eg
    - "A man, a plan, a canal: Panama"， return true
    - 特殊情况， "0P", return false

# 思路
- 使用左右2个指针遍历
- 跳过非字母的字符比较
- 时间复杂度O(N), 空间复杂度O(1)