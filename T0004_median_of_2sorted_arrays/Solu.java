package T0004_median_of_2sorted_arrays;

import java.util.HashMap;

class Solu {
    static class Solution {

        // 解法1
        // 使用2个下标分别遍历2个数组, 使用全局索引all代表已经排序的数字数量
        // 找到数组的上中位数，eg, 如果m+n=3, 上中位数为1，找到排序后索引1的位置直接返回
        //                     如果m+n=4, 上中位数为1，找到排序后索引为1，2的位置的平均数返回
        // 时间复杂度O(m+n), 空间复杂度O(1)
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if(nums1 == null || nums2 == null) return 0;

            int m = nums1.length, n = nums2.length;
            // index1，index2分别代表数组1、2的下标索引
            // all代表已经排序的数字数量
            int index1 = 0, index2 = 0, all = 0;
            double result = 0.0;
            // 找到上中位数索引
            int upMid = (m + n -1) >> 1;
            // m+n是奇数还是偶数
            boolean isOdd = ((m + n) & 1)  == 1;
            // 中位数分4种情况
            // 1. 数组1和数组2遍历都没越界时，找到了中位数
            // 2. 数组1和数组2有一个越界了，中位数刚好在越界数组的边界上, eg, num1=[1,2],num2=[3,4], 中位数是num1[1]+nums2[0]
            // 3. 数组1和数组2有一个越界，中位数在剩下的一个数组上
            // 不可能2个数组都越界，因为寻找的是中位数，不可能同时让2个数组越界
            while(index1 < m && index2 < n) { // 2个数组都不越界
                if(nums1[index1] <= nums2[index2]) { // 数组1小于等于数组2
                    if(all == upMid) { // 当前位置是上中位数的位置
                        if(isOdd) {
                            // 奇数直接返回
                            result = nums1[index1];
                        } else {
                            if(index1 + 1 < m) {
                                // 偶数返回上中位数和上中位数下一个位置的数的平均数
                                // 情况1
                                result = (nums1[index1] +
                                             Math.min(nums1[index1+1], nums2[index2])
                                         ) / 2.0;
                            } else {
                                // 情况2
                                // 如果数组1越界，代表数组1的最后一个元素和数组2的index2位置元素的平均值就是返回值
                                result = (nums1[index1] + nums2[index2]) / 2.0;
                            }
                        }
                        break;
                    }
                    // 数组1小，所以数组1的索引继续增加，数组2索引不变
                    index1++;
                } else { // 数组1大于数组2，同理
                    if(all == upMid) {
                        if(isOdd) {
                            result = nums2[index2];
                        } else {
                            if(index2 +1 < n) {
                                result = (nums2[index2] +
                                            Math.min(nums2[index2+1], nums1[index1])
                                          ) / 2.0;
                            } else {
                                result = (nums2[index2] + nums1[index1]) / 2.0;
                            }
                        }
                        break;
                    }
                    // 数组2小，所以数组2的索引继续增加，数组1索引不变
                    index2++;
                }
                // 已经排序的数字数量加1
                all++;
            }
            // 情况3，数组1遍历越界，并且中位数只可能在数组2上
            if(index1 == m) {
                if(isOdd) result = nums2[upMid-m];
                else result = (nums2[upMid-m] + nums2[upMid-m+1]) / 2.0;
            }
            // 情况3，数组2遍历越界，并且中位数只可能在数组1上
            if(index2 == n) {
                if(isOdd) result = nums1[upMid-n];
                else result = (nums1[upMid-n] + nums1[upMid-n+1]) / 2.0;
            }
            return result;
        }

    }

    // 解法2
}
