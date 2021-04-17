# leetcode t45 follow up
- 本题不是高频题，是T45的升级版，没有出现在lc中
- 数组中跳转
- 给定一个非负的整数数组，数组中元素表示能跳的步数，初始时位于start位置，求跳到数组end位置的最小步数
- 当前位置能到达2个位置
    - 向左跳，到达i-nums[i]
    - 向右跳，到达i+nums[i]
- start不能到达end位置时，返回-1
- start和end的索引从1开始
- eg
    - nums = [4,2,3,0,3,1,2]，return 3
    
# 思路1
- 深度优先遍历，设计递归函数返回当前位置cur到end的最小步数
- 如果同一个位置重复到达，返回-1，重复到达一定不是最优解
- 递归函数f(int[] nums, int cur, int end, int step, boolean[] isReached)
    - cur表示当前来到的位置
    - end代表目标位置
    - step代表从start跳到cur的步数
    - isReached中已经走的元素标记为true
- 递归函数实现
    - cur越界时，返回-1
    - cur是第二次到达时，返回-1
    - cur == end时，返回step
    - 判断向左走和向右走的最小步数
    - 返回

# 思路2
- 思路1中的可变参数太多，包含数组，改dp太复杂，考虑递归函数可变参数中去掉isReached数组
- 由于递归过程不能到达同一个位置2次，隐含条件：step <= nums.length-1
- 递归函数f(int[] nums, int cur, int end, int step)
    - cur表示当前来到的位置
    - end代表目标位置
    - step代表从start跳到cur的步数
- 递归函数实现
    - cur越界时，返回-1
    - step > nums.length -1，返回-1
    - cur == end时，返回step
    - 判断向左走和向右走的最小步数
    - 返回

# 思路3
- 使用广度优先遍历
- 从起始位置向左跳和向右跳，会形成一个二叉树
- 建立一个遍历的二叉树，二叉树的层数代表走的步数，根节点就是start位置，step=0
- 按层遍历二叉树，当低层出现end时就是最小的步数
- 使用map记录每个元素出现的层数