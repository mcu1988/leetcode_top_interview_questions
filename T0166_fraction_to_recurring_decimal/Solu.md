# leetcode t166
- 把两整数结果转为字符串
- 如果出现循坏小数，把循坏部分用括号括起来
- 输入整数范围 int最小值 -> int最大值
- eg,
    - numerator = 1, denominator = 2, return "0.5"
    - numerator = 2, denominator = 1, return "2"
    - numerator = 2, denominator = 3, return "0.(6)"

# 方法
- 根据正负，添加"-"
- 根据结尾是否是小数，添加"."
- 数字全部转为正数处理
- 负数最小值转为正数时会越界，使用long存储
- 小数部分每次把余数放大10倍，继续相除，计算余数
- 使用map记录 余数 -> 索引的映射