# leetcode t1306
- 本题不是高频题，是T45的升级版
- 数组中跳转
- 给定一个非负的整数数组，数组中元素表示能跳的步数，初始时位于start位置，求能否跳到数组中的任意0
- 当前位置能到达2个位置
    - 向左跳，到达i-nums[i]
    - 向右跳，到达i+nums[i]
- start不能到达数组中的任意0时，返回-1
- start的索引从0开始
- eg
    - nums = [4,2,3,0,3,1,2] start = 5，return true
    
# 思路1
- 深度优先遍历，设计递归函数返回当前位置cur能否到达0
- 如果同一个位置重复到达，返回-1，重复到达一定不是最优解
- 递归函数f(int[] nums, int cur, boolean[] isReached)
    - cur表示当前来到的位置
    - isReached中已经走的元素标记为true
- 递归函数实现
    - cur越界时，返回false
    - cur是第二次到达时，返回false
    - cur位置的值时0时，返回true
    - 向左走，成功了，返回true
    - 向右走，成功了，返回true
    - 两边不能走通，返回false
- 递归函数会一直尝试，直到出现第一个走通的情况，终止尝试

# 思路2
- 使用广度优先遍历
- 从起始位置向左跳和向右跳，会形成一个二叉树
- 建立一个遍历的二叉树，二叉树的层数代表走的步数，根节点就是start位置，step=0
- 按层遍历二叉树，当低层出现end时就是最小的步数
- 使用map记录每个元素出现的层数