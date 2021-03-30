# leetcode t4
- 字符串转整数
- 字符串中可能包含无效数字
    - 空格可以包含在开头
    - 其他除（数字，-）以外的字符只能包含在结尾
    - 如果整数溢出，返回边界值
- eg， 
    - s = "42", return 42
    - s = "+42", return 42
    - s = "   -42", return -42
    - s = "4193 with words"，return 4193
    - s = "words and 987", return 0
    - s = "-91283472332", return -2147483648
        
# 思路
- 逐个遍历字符，去掉前前导空字符，在判断符号位
- 再判断连续的整数
    - 注意提前判断result * 10是否会溢出，对于溢出的情况直接返回
- 几个特殊测试例子
    - s = "-", return 0
    - s = "   ", return 0
    - s = "-+1", return 0
    - s = "-2147483647", return -2147483647
    - s = "-2147483648", return -2147483648
    - s = "-2147483649", return -2147483648
    - s = "2147483647", return 2147483647
    - s = "2147483648", return 2147483647
- 时间复杂度O(n), 空间复杂度O(1)