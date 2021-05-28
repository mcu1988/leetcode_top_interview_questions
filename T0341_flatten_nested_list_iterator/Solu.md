# leetcode t341
- 实现嵌套list的flatten功能
- 系统已经实现NestedInteger接口，可以判断对象是否是整数类型，是否是list类型，以及获得整型值和list值
- eg,
    - nestedList = [[1,1],2,[1,1]], return [1,1,2,1,1]
    
    
# 方法1    
- 使用栈把加入的list按照逆序入栈
- 获取next时，如果栈顶元素是list，重复按照逆序入栈，直到拆分出整数
