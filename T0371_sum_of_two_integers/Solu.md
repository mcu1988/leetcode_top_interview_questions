# leetcode t350
- 2个整数之和
- 不能使用+和-操作符
- 输入整数有0、正、负
- eg,
    - a = 1, b = 2, return 3
    
    
# 方法1    
- 使用位运算
- a ^ b是 不带进位的结果
- (a & b) << 1是进位结果
- 不带进位的结果再和进位结果相加
- 循环，直到进位变成0