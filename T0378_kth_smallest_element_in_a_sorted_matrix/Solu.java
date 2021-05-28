package T0378_kth_smallest_element_in_a_sorted_matrix;

import java.util.PriorityQueue;

class Solu {

    static class Solution {

        // 方法1
        //
        public int kthSmallest(int[][] matrix, int k) {
            if(matrix == null || matrix.length == 0 || k < 1)
                return -1;

            int m = matrix.length, n = matrix[0].length;
            PriorityQueue<Info> minHeap = new PriorityQueue<>(k, (a, b) -> a.value - b.value);
            // 最大堆中初始加入每行的第一个元素，如果行数row > k, 只加入前k行
            for(int i=0; i<m && i<k; i++) minHeap.add(new Info(matrix[i][0], i, 0));

            int index = 0;
            while(index < k-1) {
                Info min = minHeap.poll();
                int row = min.row;
                int column = min.column;
                if(column < n-1) minHeap.add(new Info(matrix[row][column+1], row, column+1));
                index++;

            }

            return minHeap.poll().value;
        }

        public class Info{
            int value;
            int row;
            int column;
            Info(int value, int row, int column) {
                this.value = value;
                this.row = row;
                this.column = column;
            }
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[][] matrix = {{1,5,9},{10,11,13},{12,13,15}};
        int k = 8;

        app.kthSmallest(matrix, k);
        System.out.println(app.kthSmallest(matrix, k));

    }
}
