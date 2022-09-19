package LeetCode;

import java.util.ArrayDeque;
import java.util.LinkedList;

// IMP: 单调队列, 非常重要的板子题, 多写多练

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

    public int[] maxSlidingWindow2(int[] arr, int k) {
        if (arr == null || k < 1 || arr.length < k) {
            return new int[]{};
        }
        int N = arr.length;
        int[] ans = new int[N - k + 1];
        // 单调队列, 头部是最大值 大-->小
        ArrayDeque<Integer> qmax = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            // 1. 往里进数(需要判断是否符合条件)
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
                qmax.pollLast();
            }
            qmax.addLast(i);
            // 2. 该出的出
            if (qmax.peekFirst() == i - k) { // 上一步刚加入数, 不可能为空
                qmax.pollFirst();
            }
            // 3. 收集答案
            if (i - k + 1 >= 0) { // k最小为1, 窗口至少有1个数
                ans[i - k + 1] = arr[qmax.peekFirst()];
            }
        }
        return ans;
    }


}
