package T0048_rotate_image;

import java.util.ArrayList;
import java.util.List;

class Solu {

    static class Solution {

        // 方法
        // 把矩阵拆分成一圈圈
        // 时间复杂度O(n^2), 空间复杂度O(1)
        public void rotate(int[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0 || matrix.length != matrix[0].length)
                return;

            int m = matrix.length;
            // 分别记录上下左右的位置
            int left = 0, right = m - 1, up = 0, down = m - 1;

            while(left <= right) {
                edgeRotate(matrix, left++, right--, up++, down--);
            }
        }

        // 一圈一圈旋转
        public void edgeRotate(int[][] matrix, int left, int right, int up, int down) {

            int tmp = 0;
            // 没圈的每个元素旋转
            for(int i=0; i<right-left; i++) {
                tmp = matrix[up][left+i];
                matrix[up][left+i] = matrix[down-i][left];
                matrix[down-i][left] = matrix[down][right-i];
                matrix[down][right-i] = matrix[up+i][right];
                matrix[up+i][right] = tmp;
            }
        }

        public void printMatrix(int[][] matrix) {
            for(int i=0; i<matrix.length; i++) {
                for(int j=0; j<matrix[0].length; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};
        app.printMatrix(matrix);
        app.rotate(matrix);
        app.printMatrix(matrix);
    }
}
