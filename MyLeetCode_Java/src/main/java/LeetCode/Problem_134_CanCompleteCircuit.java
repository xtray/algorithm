package LeetCode;

import java.util.LinkedList;

public class Problem_134_CanCompleteCircuit {


    // 1.加工出纯能值数组
    // 2.求累加和从正数点出发绕一圈
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (cost == null || cost.length == 0 || gas == null || gas.length == 0) {
            return -1;
        }
        int N = gas.length;
        int M = N << 1;
        int[] arr = new int[M];
        for (int i = 0; i < N; i++) {
            arr[i] = gas[i]  - cost[i];
            arr[i + N] = arr[i];
        }
        for (int i = 1; i < M; i++) { // 两倍长累加和数组
            arr[i] += arr[i - 1];
        }
        // 利用窗口求解
        LinkedList<Integer> w = new LinkedList<>();
        for (int i = 0; i < N; i++) { // 先形成第一个窗口
            // 窗口最小值的更新结构, list 内从小到大
            while (!w.isEmpty() && arr[w.peekLast()] >= arr[i]) {
                w.pollLast();
            }
            w.addLast(i);
        }
        boolean[] ans = new boolean[N];
        int offset = 0;
        for (int i = 0, j = N; j < M; i++, j++) {
            if (arr[w.peekFirst()] - offset >= 0) { // 窗口内最小值掉到 0 以下不是良好出发点
                ans[i] = true;
            }
            if (w.peekFirst() == i) {
                w.pollFirst();
            }
            // j 的值要进入窗口了
            while (!w.isEmpty() && arr[w.peekLast()] >= arr[j]) {
                w.pollLast();
            }
            w.addLast(j);
            offset = arr[i];
        }
        for (int i = 0; i < N; i++) {
            if (ans[i]) {
                return i;
            }
        }
        return -1;
    }

    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int N = gas.length;
        int startIndex = 0; // 选择的出发点
        int remain = 0; // 剩余待走的路
        int leftOil = 0; // 目前拥有的可以使用的汽油
        for (int i = 1;i<N;i++) { // 准备登上 i
            leftOil += gas[i-1] - cost[i-1];
            if (leftOil < remain) {
                remain = leftOil;
                startIndex = i;
            }
        }
        return startIndex;
    }

    public int canCompleteCircuit3(int[] gas, int[] cost) {
        int rest = 0, run = 0, start = 0;
        for (int i = 0; i < gas.length; i++){
            run += (gas[i] - cost[i]);
            rest += (gas[i] - cost[i]);
            if (run < 0){
                start = i + 1;
                run = 0;
            }
        }
        return rest < 0 ? -1: start;
    }

    public static void main(String[] args) {
        Problem_134_CanCompleteCircuit sl = new Problem_134_CanCompleteCircuit();
//        int[] distances = new int[]{5, 25, 15, 10, 15};
//        int[] fuel = new int[]{10, 20, 10, 0, 30};
        int[] distances = new int[]{3,4,3};
        int[] fuel = new int[]{2,3,4};
        int ans1 = sl.canCompleteCircuit2(fuel, distances);
        System.out.println(ans1);

        int ans2 = sl.canCompleteCircuit3(fuel, distances);
        System.out.println(ans2);

    }
}
