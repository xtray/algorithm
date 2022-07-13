package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_1345_JumpGameV {


    // 从左往右的尝试模型
    // 每次在 i 位置有 3 个可能性尝试
    public int minJumps(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        return process(arr, 0, 0);
    }

    // 当前来到 index 位置, 已经走了 step 步
    // 返回从 index 到最后一个数的最小步数
    private int process(int[] arr, int step, int index) {
        int N = arr.length;
        if (index == N - 1) {
            return step;
        }
        if (step >= N - 1) { // 最多 N-1 步
            return step;
        }
        // 可能性 1: 去 i+1
        int p1 = process(arr, step + 1, index + 1);
        // 可能性 2: 去 i-1
        int p2 = Integer.MAX_VALUE;
        if (index - 1 >= 0) {
            p2 = process(arr, step + 1, index - 1);
        }
        // 可能性 3: 找相等的所有数
        int p3 = Integer.MAX_VALUE;
        for (int i = index + 2; i < N; i++) {
            if (arr[i] == arr[index]) {
                p3 = Math.min(p3, process(arr, step + 1, i));
            }
        }
        return Math.min(p1, Math.min(p2, p3));
    }


    // BFS
    public int minJumps2(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int N = arr.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.computeIfAbsent(arr[i], key -> new ArrayList<>()).add(i);
        }
        boolean[] visited = new boolean[N];
        int[] queue = new int[N];
        int L = 0;
        int R = 0;
        queue[R++] = 0;
        visited[0] = true;
        int jump = 0;
        while (L != R) {
            int end = R;
            while (L < end) {
                int cur = queue[L];
                if (cur == N - 1) {
                    return jump;
                }
                // 可能性1: 去左侧
                if (cur >= 1 && !visited[cur - 1]) {
                    visited[cur - 1] = true;
                    queue[R++] = cur - 1;
                }
                // 可能性2: 去右侧
                if (cur + 1 < N && !visited[cur + 1]) {
                    visited[cur + 1] = true;
                    queue[R++] = cur + 1;
                }
                // 可能性3: 去相等值
                for (int next : map.get(arr[cur])) {
                    if (!visited[next]) {
                        visited[next] = true;
                        queue[R++] = next;
                    }
                }
                // map.remove(arr[cur]); // NOTE: 会造成空指针!!
                map.get(arr[cur]).clear(); // NOTE: 重要优化!!
                L++;
            }
            jump++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{100, -23, -23, 404, 100, 23, 23, 23, 3, 404};
        var ans = new Problem_1345_JumpGameV().minJumps(arr);
        System.out.println(ans);

        var ans2 = new Problem_1345_JumpGameV().minJumps2(arr);
        System.out.println(ans2);
    }
}
