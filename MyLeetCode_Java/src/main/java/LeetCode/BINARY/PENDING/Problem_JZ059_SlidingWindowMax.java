package LeetCode.BINARY.PENDING;

import java.util.LinkedList;

public class Problem_JZ059_SlidingWindowMax {

    public int[] maxSlidingWindow(int[] arr, int k) {
        if (arr == null || k < 1 || arr.length < k) {
            return new int[]{};
        }
        // qmax 窗口最大值的更新结构
        // 放下标
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int[] res = new int[arr.length - k + 1];
        int index = 0;
        for (int R = 0; R < arr.length; R++) {
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                qmax.pollLast();
            }
            qmax.addLast(R);
            if (qmax.peekFirst() == R - k) {
                qmax.pollFirst();
            }
            if (R >= k - 1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }
}
