package LeetCode;

import java.util.PriorityQueue;
import java.util.TreeMap;

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

    static class MedianFinder {

        private PriorityQueue<Integer> pqMax; // 大顶堆
        private PriorityQueue<Integer> pqMin; // 小顶堆

        public MedianFinder() {
            pqMax = new PriorityQueue<>((a, b) -> b - a);
            pqMin = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if (pqMax.isEmpty()) {
                pqMax.add(num); // 优先加到前半个里
            } else {
                if (num > pqMax.peek()) {
                    pqMin.add(num);
                } else {
                    pqMax.add(num);
                }
            }
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
            } else if (pqMax.size() < pqMin.size()) {
                return pqMin.peek();
            } else { // 相等
                if (pqMax.isEmpty()) {
                    return 0;
                }
                return (pqMax.peek() + pqMin.peek()) / 2.0;
            }
        }
    }


    // 对于1%采用哨兵机制，在常规的0~100区间两侧分别维护一个有序序列
    class MedianFinder1 {

        private TreeMap<Integer, Integer> head = new TreeMap<>(); // 一区
        private TreeMap<Integer, Integer> tail = new TreeMap<>(); // 三区
        private int[] arr = new int[101];
        private int a, b, c;

        public void addNum(int num) {
            if (num >= 0 && num <= 100) {
                arr[num]++;
                b++;
            } else if (num < 0) {
                head.put(num, head.getOrDefault(num, 0) + 1);
                a++;
            } else {
                tail.put(num, tail.getOrDefault(num, 0) + 1);
                c++;
            }
        }

        public double findMedian() {
            int size = a + b + c;
            if (size % 2 == 0) { // 偶数
                return (find(size / 2) + find(size / 2 + 1)) / 2.0;
            }
            return find(size / 2 + 1);
        }

        // 返回第N小
        private int find(int n) {
            if (n <= a) { // 在第一区间
                for (int num : head.keySet()) {
                    n -= head.get(num);
                    if (n <= 0) {
                        return num;
                    }
                }
            } else if (n <= a + b) {
                n -= a;
                for (int i = 0; i <= 100; i++) {
                    n -= arr[i];
                    if (n <= 0) {
                        return i;
                    }
                }
            } else {
                n -= a + b;
                for (int num : tail.keySet()) {
                    n -= tail.get(num);
                    if (n <= 0) {
                        return num;
                    }
                }
            }
            return -1; // never
        }
    }


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
