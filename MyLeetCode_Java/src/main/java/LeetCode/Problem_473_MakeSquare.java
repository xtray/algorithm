package LeetCode;

import java.util.Arrays;

// TAG: 状态压缩的动态规划

public class Problem_473_MakeSquare {

    // https://leetcode.cn/problems/matchsticks-to-square/solution/by-ac_oier-k8i7/
    public boolean makesquare(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int sum = 0;
        for (int m : arr) {
            sum += m;
        }
        int edge = sum % 4; // 每条边的边长
        if (sum % 4 != 0) {
            return false;
        }
        Arrays.sort(arr);
        int N = arr.length;
        int L = 0;
        int R = N - 1;
        while (L < R) {  // NOTE: 从大到小排序, 数组逆序
            int tmp = arr[L];
            arr[L++] = arr[R];
            arr[R--] = tmp;
        }
        int[] edges = new int[4];
        return dfs(0, arr, edges, sum / 4);
    }

    // 当前处理到index跟火柴, 每一个火柴都尝试去塞满4条边
    public boolean dfs(int index, int[] arr, int[] edges, int len) {
        if (index == arr.length) {
            return true;
        }
        for (int i = 0; i < edges.length; i++) { // index位置的火柴尝试赛马4条边的每一条
            edges[i] += arr[index];
            if (edges[i] <= len && dfs(index + 1, arr, edges, len)) {
                return true;
            }
            edges[i] -= arr[index];
        }
        return false;
    }

    // 暴力递归的方法
    // 数组里的火柴，必须都使用, 能不能拼出正方形
    public static boolean makesquare1(int[] arr) {
        long sum = 0;
        for (int num : arr) {
            sum += num;
        }
        if (sum % 4 != 0) {
            return false;
        }
        long len = sum / 4;
        // 最多15根
        // 00000000000.....000000000000000
        // 0 代表没有使用
        return process(arr, 0, 0, len, 4);
    }

    // 所有火柴都在arr里
    // 哪些火柴用了、哪些火柴没用，的状态都在status里
    // sum: 当前耕耘的这条边，长度已经达成了sum
    // len: 固定参数，每条边必须都达成这个长度
    // edges: 还剩几条边，没填完
    // 返回：最终能不能达成正方形的目标
    // 看似，三个可变参数status, sum, edges
    // 其实，只有一个，status
    // 15位状态，2^15 -> 32...
    public static boolean process(int[] arr, int status, long sum, long len, int edges) {
        int n = arr.length;
        if (edges == 0) {
            return status == (1 << n) - 1; // 全都是1代表成功了
        }
        // 剩下边 edges > 0
        boolean ans = false;
        for (int i = 0; i < n; i++) { // 当前可以选择的边，全试一遍！
            // 当前的i就是火柴编号！
            if ((status & (1 << i)) == 0) { // 当前位bit是0, 可以用
                if (sum + arr[i] <= len) {
                    // 当前的边已经耕耘长度sum + 当前边的长度 不能超过len！
                    if (sum + arr[i] < len) {
                        ans = process(arr, status | (1 << i), sum + arr[i], len, edges);
                    } else { // sum + arr[i] == len
                        ans = process(arr, status | (1 << i), 0, len, edges - 1);
                    }
                }
            }
            if (ans) {
                return true;
            }
        }
        return false;
    }

    // 改傻缓存
    public static boolean makesquare2(int[] arr) {
        long sum = 0;
        for (int num : arr) {
            sum += num;
        }
        if (sum % 4 != 0) {
            return false;
        }
        long len = sum / 4;
        int[] dp = new int[1 << arr.length];
        return process2(arr, 0, 0, len, 4, dp);
    }

    public static boolean process2(int[] arr, int status, long sum, long len, int edges, int[] dp) {
        int n = arr.length;
        if (edges == 0) {
            return status == (1 << n) - 1; // 全都是1代表成功了
        }
        if (dp[status] != 0) {
            return dp[status] == 1;
        }
        // 剩下边 edges > 0
        boolean ans = false;
        for (int i = 0; i < n; i++) { // 当前可以选择的边，全试一遍！
            // 当前的i就是火柴编号！
            if ((status & (1 << i)) == 0) { // 当前位bit是0, 可以用
                if (sum + arr[i] <= len) {
                    // 当前的边已经耕耘长度sum + 当前边的长度 不能超过len！
                    if (sum + arr[i] < len) {
                        ans = process2(arr, status | (1 << i), sum + arr[i], len, edges, dp);
                    } else { // sum + arr[i] == len
                        ans = process2(arr, status | (1 << i), 0, len, edges - 1, dp);
                    }
                }
            }
            if (ans) {
                break;
            }
        }
        dp[status] = ans ? 1 : -1;
        return ans;
    }

}
