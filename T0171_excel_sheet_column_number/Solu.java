package T0171_excel_sheet_column_number;

import java.util.HashMap;

class Solu {

    static class Solution {

        // 方法
        // 从左往右遍历，每次让高位 * 26
        // 时间复杂度O(N), 空间复杂度(1)
        public int titleToNumber(String columnTitle) {
            if(columnTitle == null || columnTitle.length() == 0)
                return 0;

            int result = 0;
            for(int i=0; i<columnTitle.length(); i++) {
                result = result * 26 + (columnTitle.charAt(i) - 'A' + 1);
            }
            return result;
        }
    }


    public static void main(String[] args) {

        Solution app = new Solution();

        String columnTitle = "AA";
        app.titleToNumber(columnTitle);
        System.out.println(app.titleToNumber(columnTitle));

    }
}
