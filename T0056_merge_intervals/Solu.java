package T0056_merge_intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solu {

    static class Solution {

        // 方法
        // 把数组按照start位置从小到大排序
        // 遍历区间，一个个合并区间
        // 时间复杂度O(N*log(N)), 空间复杂度O(1)
        public int[][] merge(int[][] intervals) {
            // 数组为空或者只有一个区间，直接返回
            if(intervals == null || intervals.length < 2) return intervals;

            // 按照start从小到大排序
            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

            // 目前还没结束的区间起止位置，初始时在区间0
            int preEnd = intervals[0][1], preStart = intervals[0][0];

            List<List<Integer>> list = new ArrayList<>();
            for(int i=1; i<intervals.length; i++) { // 从第1个区间开始遍历
                // 当前区间开始位置比preEnd小，合并区间，当前区间结束位置可能比preEnd小，eg, [[1,4],[2,3]]
                if(intervals[i][0] <= preEnd) preEnd = Math.max(preEnd, intervals[i][1]);
                else {
                    // 当前区间和待完结的区间不连续
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(preStart);
                    tmp.add(preEnd);
                    // 结束待完结的区间
                    list.add(tmp);
                    // 更新preStart和preEnd为当前区间的起止位置
                    preStart = intervals[i][0];
                    preEnd = intervals[i][1];
                }
            }

            // 循环借宿，添加最后一个待完结的区间
            List<Integer> tmp = new ArrayList<>();
            tmp.add(preStart);
            tmp.add(preEnd);
            list.add(tmp);

            int[][] result = new int[list.size()][2];
            for(int i=0; i<list.size(); i++) {
                result[i][0] = list.get(i).get(0);
                result[i][1] = list.get(i).get(1);
            }

            return result;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};

        System.out.println(app.merge(intervals));

    }
}
