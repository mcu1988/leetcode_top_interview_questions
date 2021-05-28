package T0295_find_median_from_data_stream;

import java.util.PriorityQueue;
import java.util.Queue;

class Solu {

    static class Solution {


        // 方法1
        // 使用最大堆和最小堆，如果总个数是偶数，2个堆的元素个数相等，如果是奇数，最大堆的元素个数比最小堆多一个
        // 最大堆的存放前半部分，堆顶是前半部分的最大值；最小堆存放后半部分，堆顶元素是后半部分的最小值
        // 新元素加入的时候，先加入最小堆，再把最小堆的堆顶弹到最大堆，保证最大堆的元素总是比最小堆多，如果最大堆比最小堆多2个元素，把最大堆对顶元素弹回最小堆
        // 取median时，如果总个数是偶数，取各自的堆顶元素，取平均值；奇数时，取最大堆的堆顶元素
        // 时间复杂度O(N*log(N)), 空间复杂度O(N)
        static class MedianFinder {

            Queue<Integer> maxHeap;
            Queue<Integer> minHeap;

            public MedianFinder() {
                maxHeap = new PriorityQueue<>((a, b) -> b - a);
                minHeap = new PriorityQueue<>();
            }

            public void addNum(int num) {
                // 先加入最小堆，把堆顶元素弹回最大堆
                minHeap.add(num);
                maxHeap.add(minHeap.poll());
                // 最大堆比最小堆多2个元素时，把最大堆的堆顶弹回最小堆
                if(maxHeap.size() - minHeap.size() > 1) {
                    minHeap.add(maxHeap.poll());
                }
            }

            public double findMedian() {
                double result = 0;
                // 偶数时，取各自的堆顶元素，取平均值
                // 奇数时，取最大堆的堆顶元素
                if(maxHeap.size() == minHeap.size()) {
                    result = (maxHeap.peek() + minHeap.peek()) / 2.0;
                } else {
                    result = maxHeap.peek();
                }
                return result;
            }
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        Solution.MedianFinder medianFinder = new Solution.MedianFinder();

        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }
}
