package T0073_set_matrix_zeroes;

import java.util.Arrays;

class Solu {

    static class Solution {

        // 方法1
        // 使用行数组和列数组标记是否置0
        // 时间复杂度O(m*n), 空间复杂度O(m+n)
        public void setZeroes1(int[][] matrix) {
            if(matrix == null || matrix.length == 0) return;

            int m = matrix.length, n = matrix[0].length;
            // 新建行和列的数组，标记是否置0
            boolean[] row = new boolean[m];
            boolean[] column = new boolean[n];

            // 遍历矩阵，标记行数组和列数组
            for(int i=0; i<m; i++) {
                for(int j=0; j<n; j++) {
                    if(matrix[i][j] == 0) {
                        row[i] = true;
                        column[j] = true;
                    }
                }
            }

            // 根据标记的行数组和列数组置0
            for(int i=0; i<m; i++) {
                for(int j=0; j<n; j++) {
                    if(row[i] || column[j]) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }

        // 方法2
        // 将方法1中的数组用矩阵第0行和第0列代替
        // 时间复杂度O(m*n), 空间复杂度O(1)
        public void setZeroes(int[][] matrix) {
            if (matrix == null || matrix.length == 0) return;

            int m = matrix.length, n = matrix[0].length;

            // 先记录第0行和第0列是否有0
            boolean isFirstRow = false, isFirstColumn = false;
            for(int i=0; i<m; i++) {
                if(matrix[i][0] == 0) {
                    isFirstColumn = true;
                    break;
                }
            }

            for(int i=0; i<n; i++) {
                if(matrix[0][i] == 0) {
                    isFirstRow = true;
                    break;
                }
            }

            // 从第1行，第1列开始遍历
            for(int i=1; i<m; i++) {
                for(int j=1; j<n; j++) {
                    if(matrix[i][j] == 0) {
                        // 用第0行和第0列标记
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }

            // 用第0行和第0列的标记来置0
            for(int i=1; i<m; i++) {
                for(int j=1; j<n; j++) {
                    if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }

            // 单独考虑第0行和第0列
            if(isFirstRow) for(int i=0; i<n; i++) matrix[0][i] = 0;
            if(isFirstColumn) for(int i=0; i<m; i++) matrix[i][0] = 0;
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

        int[][] matrix = {{1,0,3}};

        app.printMatrix(matrix);
        app.setZeroes(matrix);
        app.printMatrix(matrix);
    }
}
