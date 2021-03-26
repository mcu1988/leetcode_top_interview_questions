package T0004_median_of_2sorted_arrays;

import java.util.HashMap;

class Solu {
    static class Solution {

        // 解法1
        // 使用2个下标分别遍历2个数组, 使用全局索引all代表已经排序的数字数量
        // 找到数组的上中位数，eg, 如果m+n=3, 上中位数为1，找到排序后索引1的位置直接返回
        //                     如果m+n=4, 上中位数为1，找到排序后索引为1，2的位置的平均数返回
        // 时间复杂度O(m+n), 空间复杂度O(1)
        public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
            if (nums1 == null || nums2 == null) return 0;

            int m = nums1.length, n = nums2.length;
            // index1，index2分别代表数组1、2的下标索引
            // all代表已经排序的数字数量
            int index1 = 0, index2 = 0, all = 0;
            double result = 0.0;
            // 找到上中位数索引
            int upMid = (m + n - 1) >> 1;
            // m+n是奇数还是偶数
            boolean isOdd = ((m + n) & 1) == 1;
            // 中位数分4种情况
            // 1. 数组1和数组2遍历都没越界时，找到了中位数
            // 2. 数组1和数组2有一个越界了，中位数刚好在越界数组的边界上, eg, num1=[1,2],num2=[3,4], 中位数是num1[1]+nums2[0]
            // 3. 数组1和数组2有一个越界，中位数在剩下的一个数组上
            // 不可能2个数组都越界，因为寻找的是中位数，不可能同时让2个数组越界
            while (index1 < m && index2 < n) { // 2个数组都不越界
                if (nums1[index1] <= nums2[index2]) { // 数组1小于等于数组2
                    if (all == upMid) { // 当前位置是上中位数的位置
                        if (isOdd) {
                            // 奇数直接返回
                            result = nums1[index1];
                        } else {
                            if (index1 + 1 < m) {
                                // 偶数返回上中位数和上中位数下一个位置的数的平均数
                                // 情况1
                                result = (nums1[index1] +
                                        Math.min(nums1[index1 + 1], nums2[index2])
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
                    if (all == upMid) {
                        if (isOdd) {
                            result = nums2[index2];
                        } else {
                            if (index2 + 1 < n) {
                                result = (nums2[index2] +
                                        Math.min(nums2[index2 + 1], nums1[index1])
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
            if (index1 == m) {
                if (isOdd) result = nums2[upMid - m];
                else result = (nums2[upMid - m] + nums2[upMid - m + 1]) / 2.0;
            }
            // 情况3，数组2遍历越界，并且中位数只可能在数组1上
            if (index2 == n) {
                if (isOdd) result = nums1[upMid - n];
                else result = (nums1[upMid - n] + nums1[upMid - n + 1]) / 2.0;
            }
            return result;
        }


        // 解法2
        // 使用二分法，返回上中位数
        // 设计minK返回2数组第k小的值
        // 设计getUpMid返回2等长数组上中位数
        // m+n为奇数，调用minK 1次，否则调用minK 2次
        // 时间复杂度O(log(min(m,n))), 空间复杂度O(1)
        // minK返回第k小的数有3种情况
        // 情况1：k 比2数组长度都小，O(log(k)) < O(log(min(m,n)))
        // 情况2：k位于2个数组长度之间，O(log(short length)) = O(log(min(m,n)))
        // 情况3：k比2个数组长度都大，O(log(k- long length)) < O(log(min(m,n)))
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if (nums1 == null || nums2 == null) return 0;

            int m = nums1.length, n = nums2.length;
            // 数组上中位数，从1开始计数
            int upMid = (m + n + 1) >> 1;
            boolean isOdd = ((m + n) & 1) == 1;

            // 数组1是空，根据数组2长度的奇偶，返回上中位数
            if(m == 0) {
                return isOdd ? nums2[upMid-1] * 1.0 : (nums2[upMid-1] + nums2[upMid]) / 2.0;
            }

            // 数组2是空，同理
            if(n == 0) {
                return isOdd ? nums1[upMid-1] * 1.0 : (nums1[upMid-1] + nums1[upMid]) / 2.0;
            }

            double result = 0;
            if (isOdd) {
                // m+n是奇数，直接返回上中位数
                result = minK(nums1, nums2, upMid) * 1.0;
            } else {
                // m+n是偶数，调用2次f函数，返回上中位数和下中位数的平均值
                result = (minK(nums1, nums2, upMid) + minK(nums1, nums2, upMid + 1)) / 2.0;
            }
            return result;
        }

        // 返回2个有序数组第k小的元素，k从1开始计数
        public int minK(int[] nums1, int[] nums2, int k) {
            int m = nums1.length, n = nums2.length;

            // 重新找到长数组和短数组
            int[] shorts = m <= n ? nums1 : nums2;
            int[] longs = shorts == nums1 ? nums2 : nums1;

            int result = 0;
            if (k <= shorts.length) {
                // k比2个数组长度都小，直接 取2个数组前k个数，返回上中位数k位置的值
                result = getUpMid(nums1, 0, k - 1, nums2, 0, k - 1);
            } else if (k <= longs.length) {
                // k比短数组大，比长数组小
                // 单独判断长数组k - shorts.length - 1位置的值 >= 短数组最后一个值
                // yes， 直接返回
                // no, 调用g函数，短数组所有值，长数组[k - shorts.length~k-1], 返回上中位数
                if (longs[k - shorts.length - 1] >= shorts[shorts.length - 1]) {
                    result = longs[k - shorts.length - 1];
                } else {
                    result = getUpMid(shorts, 0, shorts.length - 1, longs, k - shorts.length, k-1);
                }
            } else if (k <= m + n) {
                // k比2个数组长度都大
                // 单独判断短数组k - longs.length - 1位置的值 >= 长数组最后一个值
                // 单独判断长数组k - shorts.length - 1位置的值 >= 短数组最后一个值
                // 最后，调用 g函数，短数组[k - longs.length ~ shorts.length-1], 长数组【k - shorts.length ~ longs.length-1]，返回上中位数
                if (shorts[k - longs.length - 1] >= longs[longs.length - 1]) {
                    result = shorts[k - longs.length - 1];
                } else if (longs[k - shorts.length - 1] >= shorts[shorts.length - 1]) {
                    result = longs[k - shorts.length - 1];
                } else {
                    result = getUpMid(shorts, k - longs.length, shorts.length-1, longs,k - shorts.length, longs.length-1);
                }
            }
            return result;
        }

        // 返回2个等长有序数组的上中位数
        public int getUpMid(int[] nums1, int l1, int r1, int[] nums2, int l2, int r2) {

            int m = r1 - l1 + 1;

            // 递归终止条件，2数组只有1个数时，直接判断
            if(m == 1) {
                return nums1[l1] <= nums2[l2] ? nums1[l1] : nums2[l2];
            }

            // 数组上中位数，从0开始计数
            int upMid1 = l1 + (m - 1) / 2;
            int upMid2 = l2 + (m - 1) / 2;

            // 分别找到上中位数小的数组 和 大的数组
            int[] midSmall = nums1[upMid1] <= nums2[upMid2] ? nums1 : nums2;
            int[] midLarge = midSmall == nums1 ? nums2 : nums1;

            // 分别重新确定l, upMid, r的值
            int upMidSmall = midSmall == nums1 ? upMid1 : upMid2;
            int upMidLarge = midLarge == nums1 ? upMid1 : upMid2;
            int lSmall = midSmall == nums1 ? l1 : l2;
            int lLarge = midLarge == nums1 ? l1 : l2;
            int rSmall = midSmall == nums1 ? r1 : r2;
            int rLarge = midLarge == nums1 ? r1 : r2;


            int result = 0;
            if (nums1[upMid1] == nums2[upMid2]) {
                // 中位数相等，直接返回
                result = nums1[upMid1];
            } else {
                if (m % 2 == 0) {
                    // nums1长度为偶数情况
                    // 递归调用g函数，可能的范围是midSmall数组[upMidSmall + 1 ~ rSmall], midLarge数组[lLarge ~ upMidLarge]
                    result = getUpMid(midSmall, upMidSmall + 1, rSmall, midLarge, lLarge, upMidLarge);
                } else {
                    // nums1长度为奇数情况
                    // 先判断midSmall数组上中位数 >= midLarge数组upMidLarge - 1位置的值
                    // yes, 直接返回
                    // no, 递归调用g函数，可能的范围是midSmall数组[upMidSmall + 1 ~ rSmall], midLarge数组[lLarge ~ upMidLarge-1]
                    if (midSmall[upMidSmall] >= midLarge[upMidLarge - 1]) {
                        result = midSmall[upMidSmall];
                    } else {
                        result = getUpMid(midSmall, upMidSmall + 1, rSmall, midLarge, lLarge, upMidLarge - 1);
                    }
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3};
        int[] nums2 = {4,5,6};

        Solution app = new Solution();
        System.out.println(app.findMedianSortedArrays(nums1, nums2));

    }
}
