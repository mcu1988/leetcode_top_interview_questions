# leetcode t7
- 给定一个32位带符号整数，对除符号位以外的数字逆序
- eg， 
    - x = 123, return 321
    - x = -123, return -321
        
# 思路
- 先判断正负，把数字转为正数统一处理
- 设置变脸result=0
- 每次遍历对10取模，对10取余
    - result = result * 10 + x  % 10
    - x = x / 0
- 不用考虑 result = Integer.MAX_VALUE / 10 && 当前元素 <= Integer.MAX_VALUE % 10
    - 因为Integer.MAX_VALUE / 10 = 214748364, 此时输入可能是463847412，-463847412, 不可能有其他输入
- 提前判断result * 10是否越界
- 时间复杂度O(log10(n)), 空间复杂度O(1)
