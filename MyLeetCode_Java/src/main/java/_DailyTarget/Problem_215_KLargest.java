package _DailyTarget;

import java.util.PriorityQueue;

public class Problem_215_KLargest {

    // 优先队列 N*logK
    public int findKthLargest(int[] nums, int k) {
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

    // 快速选择 O(logN)
    public int findKthLargest1(int[] nums, int k) {
        int N = nums.length;
        return process(nums, 0, nums.length - 1, N - k);
    }

    // 寻找L...R第K小
    private int process(int[] nums, int L, int R, int k) {
        while (L < R) {
            int pivot = nums[L + (int) (Math.random() * (R - L + 1))];
            int[] range = partition(nums, L, R, pivot);
            if (k < range[0]) {
                R = range[0] - 1;
            } else if (k > range[1]) {
                L = range[1] + 1;
            } else {
                return pivot;
            }
        }
        return nums[L]; // L..R只有一个数字
    }

    private int[] partition(int[] nums, int l, int r, int pivot) {
        int less = l - 1;
        int more = r + 1;
        int i = l;// 盯着的位置
        while (i < more) {
            if (nums[i] < pivot) {
                swap(nums, i++, ++less);
            } else if (nums[i] > pivot) {
                swap(nums, i, --more);
            } else {
                i++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        var ans = new Problem_215_KLargest().findKthLargest(nums, k);
        System.out.println(ans);

        var ans1 = new Problem_215_KLargest().findKthLargest1(nums, k);
        System.out.println(ans1);
    }
}
