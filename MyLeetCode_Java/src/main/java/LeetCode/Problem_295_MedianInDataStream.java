package LeetCode;

import java.util.PriorityQueue;

/**
 * NOTE: 两个进阶
 * 进阶 1
 * 如果数据流中所有整数都在 0 到 100 范围内，那么我们可以利用计数排序统计每一类数的数量，并使用双指针维护中位数。
 *
 * 进阶 2
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，那么我们依然利用计数排序统计每一类数的数量，
 * 并使用双指针维护中位数。
 * 对于超出范围的数，我们可以单独进行处理，建立两个数组，分别记录小于 0 的部分的数的数量和
 * 大于 100 的部分的数的数量即可。
 * 当小部分时间，中位数不落在区间 [0,100] 中时，我们在对应的数组中暴力检查即可。
 */

public class Problem_295_MedianInDataStream {

    class MedianFinder {

        private PriorityQueue<Integer> pqMax; // 大顶堆
        private PriorityQueue<Integer> pqMin; // 小顶堆

        public MedianFinder() {
            pqMax = new PriorityQueue<>((a, b) -> b - a);
            pqMin = new PriorityQueue<>((a, b) -> a - b);
        }

        public void addNum(int num) {
            if (pqMax.isEmpty()) {
                pqMax.add(num);
            } else {
                if (pqMax.peek() >= num) {
                    pqMax.add(num);
                } else {
                    pqMin.add(num);
                }
            }
            balance();
        }

        public double findMedian() {
            if (pqMax.size() == pqMin.size()) {
                return (double) (pqMax.peek() + pqMin.peek()) / 2;
            } else {
                return pqMax.size() > pqMin.size() ? pqMax.peek() : pqMin.peek();
            }
        }

        private void balance() {
            if (pqMax.size() == pqMin.size() + 2) {
                pqMin.add(pqMax.poll());
            }
            if (pqMax.size() == pqMin.size() - 2) {
                pqMax.add(pqMin.poll());
            }
        }

        private void balance2() {
            while (pqMin.size() - pqMax.size() > 1) {
                pqMax.add(pqMin.poll());
            }
            while (pqMax.size() - pqMin.size() > 1) {
                pqMin.add(pqMax.poll());
            }
        }
    }
}
