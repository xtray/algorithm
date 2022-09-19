package _DailyTarget;

import java.util.PriorityQueue;

/**
 * NOTE: 两个进阶
 * 进阶 1
 * 如果数据流中所有整数都在 0 到 100 范围内，那么我们可以利用计数排序统计每一类数的数量，并使用双指针维护中位数。
 * <p>
 * 进阶 2
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，那么我们依然利用计数排序统计每一类数的数量，
 * 并使用双指针维护中位数。
 * 对于超出范围的数，我们可以单独进行处理，建立两个数组，分别记录小于 0 的部分的数的数量和
 * 大于 100 的部分的数的数量即可。
 * 当小部分时间，中位数不落在区间 [0,100] 中时，我们在对应的数组中暴力检查即可。
 */

public class Problem_JZ041_MedianInDataStream_02 {

    // 使用大顶堆, 小顶堆, 当数据空时, 优先加入到前半个大顶堆里
    static class MedianFinder {

        private PriorityQueue<Integer> pqMax; // 大顶堆
        private PriorityQueue<Integer> pqMin; // 小顶堆

        public MedianFinder() {
            pqMax = new PriorityQueue<>((o1, o2) -> o2 - o1);
            pqMin = new PriorityQueue<>();
        }

        public void addNum(int num) {
            // ...小-->大]    [小-->大...
            // 优先加入前半个大顶堆里
            if (pqMax.isEmpty() || pqMax.peek() >= num) {
                pqMax.add(num);
            } else {
                pqMin.add(num);
            }
            // 大小调整
            while (pqMax.size() - pqMin.size() > 1) {
                pqMin.add(pqMax.poll());
            }
            while (pqMin.size() - pqMax.size() > 1) {
                pqMax.add(pqMin.poll());
            }
        }

        public double findMedian() {
            if (pqMax.size() > pqMin.size()) {
                return pqMax.peek();
            } else if (pqMin.size() > pqMax.size()) {
                return pqMin.peek();
            } else {
                return ((pqMax.isEmpty() ? 0 : pqMax.peek()) + (pqMin.isEmpty() ? 0 : pqMin.peek())) / 2.0;
            }
        }
    }

    // 输入
    // ["MedianFinder","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian"]
    // [[],[6],[],[10],[],[2],[],[6],[],[5],[],[0],[],[6],[]]

    public static void main(String[] args) {
        MedianFinder sl = new MedianFinder();
        // sl.addNum(1);
        // sl.addNum(2);
        // var ans = sl.findMedian();
        // System.out.println(ans);
        // sl.addNum(3);
        // ans = sl.findMedian();
        // System.out.println(ans);

        sl.addNum(6);
        var ans = sl.findMedian();
        System.out.print(ans + " ");
        sl.addNum(10);
        ans = sl.findMedian();
        System.out.print(ans + " ");
        sl.addNum(2);
        ans = sl.findMedian();
        System.out.print(ans + " ");
        sl.addNum(6);
        ans = sl.findMedian();
        System.out.print(ans + " ");
        sl.addNum(5);
        ans = sl.findMedian();
        System.out.print(ans + " ");
        sl.addNum(0);
        ans = sl.findMedian();
        System.out.print(ans + " ");
        sl.addNum(6);
        ans = sl.findMedian();
        System.out.print(ans + " "); // 6
    }

}
