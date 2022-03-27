package LeetCode;

import java.util.LinkedList;

// IMP: 非常重要的基本模型, 多写多练

public class Problem_239_SlidingWindowMax {

    public int[] maxSlidingWindow(int[] arr, int k) {
        if (arr == null || k < 1 || arr.length < k) {
            return new int[]{};
        }
        // qmax 窗口最大值的更新结构
        // 放下标
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int N = arr.length;
        int[] res = new int[N - k + 1];
        int idx = 0;
        for (int R = 0; R < N; R++) {
            // 队列头部是最大值--->尾部最小值
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                qmax.pollLast();
            }
            qmax.addLast(R); // 添加的是下标
            if (qmax.peekFirst() == R - k) {
                qmax.pollFirst();
            }
            if (R >= k - 1) { // 开始形成第一个窗口
                res[idx++] = arr[qmax.peekFirst()]; // 队列头部是最大值
            }
        }
        return res;
    }
}
