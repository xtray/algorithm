package Jianzhi;

import java.util.PriorityQueue;

public class Problem_JZ041_MedianFinder {

    /**
     * initialize your data structure here.
     */
    private PriorityQueue<Integer> bigHeap;
    private PriorityQueue<Integer> smallHeap;

    public Problem_JZ041_MedianFinder() {
        bigHeap = new PriorityQueue<>((o1, o2) -> o2-o1);
        smallHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (smallHeap.isEmpty()) {
            smallHeap.add(num);
        } else {
            if (num < smallHeap.peek()) {
                bigHeap.add(num);
            } else {
                smallHeap.add(num);
            }
        }
        balance();
    }

    private void balance() {
        while (smallHeap.size() - bigHeap.size() > 1) {
            bigHeap.add(smallHeap.poll());
        }
        while (bigHeap.size() - smallHeap.size() > 1) {
            smallHeap.add(bigHeap.poll());
        }
    }

    public double findMedian() {
        if (smallHeap.size() > bigHeap.size()) {
            return smallHeap.peek();
        } else if (smallHeap.size() < bigHeap.size()) {
            return bigHeap.peek();
        } else {
            return ((!smallHeap.isEmpty() ? smallHeap.peek() : 0) + (!bigHeap.isEmpty() ? bigHeap.peek() : 0)) / 2.0;
        }
    }

    public static void main(String[] args) {
        Problem_JZ041_MedianFinder sl = new Problem_JZ041_MedianFinder();
        // sl.addNum(1);
        // sl.addNum(2);
        // System.out.println(sl.findMedian());
        // sl.addNum(3);
        // System.out.println(sl.findMedian());

        // sl.addNum(2);
        // System.out.println(sl.findMedian());
        // sl.addNum(3);
        // System.out.println(sl.findMedian());

        sl.addNum(-1);
        System.out.println(sl.findMedian());
        sl.addNum(-2);
        System.out.println(sl.findMedian());
        sl.addNum(-3);
        System.out.println(sl.findMedian());
        sl.addNum(-4);
        System.out.println(sl.findMedian());
        sl.addNum(-5);
        System.out.println(sl.findMedian());

    }
}
