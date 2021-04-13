package T0054_spiral_matrix;

import java.util.ArrayList;
import java.util.List;

class Solu {

    static class Solution {

        // 方法
        // 时间复杂度O(m*n), 空间复杂度O(1)
        public List<Integer> spiralOrder(int[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return new ArrayList<>();

            int m = matrix.length, n = matrix[0].length;
            int left = 0, right = n-1, up = 0, down = m-1;

            List<Integer> result = new ArrayList<>();
            // 矩形分圈，循环要求上边小于下边 并且 左边小于右边
            while(left <= right && up <= down) {
                edgeOrder(matrix, up++, down--, left++, right--, result);
            }
            return result;
        }

        // 一圈圈打印矩阵
        public void edgeOrder(int[][] matrix, int up, int down, int left, int right, List<Integer> result) {
            // 只剩下一行
            if(up == down) {
                for(int i=left; i<=right; i++) result.add(matrix[up][i]);
                return;
            }
            // 只剩下一列
            if(left == right) {
                for(int i=up; i<=down; i++) result.add(matrix[i][left]);
                return;
            }

            // 打印上边
            for(int i=left; i<right; i++) result.add(matrix[up][i]);
            // 打印右边
            for(int i=up; i<down; i++) result.add(matrix[i][right]);
            // 打印下边
            for(int i=right; i>left; i--) result.add(matrix[down][i]);
            // 打印左边
            for(int i=down; i>up; i--) result.add(matrix[i][left]);
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[][] matrix = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}};
        System.out.println(app.spiralOrder(matrix));

    }
}
