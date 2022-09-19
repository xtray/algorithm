package _Exercise;

import java.util.PriorityQueue;

public class Problem_215_KthLargest {

    // 小根堆: O(N*logK)
    public int findKthLargest1(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        int N = nums.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.add(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    // 快速选择算法 O(N)
    // public int findKthLargest(int[] nums, int k) {
    //     if (nums == null || nums.length == 0) {
    //         return Integer.MIN_VALUE;
    //     }
    //     int N = nums.length;
    //
    // }
}
