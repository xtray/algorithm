package _Exercise;

public class Problem_473_MakeSquare {

    public boolean makesquare(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int N = arr.length;
        long sum = 0;
        for (int num : arr) {
            sum += num;
        }
        if (sum % 4 != 0) {
            return false;
        }
        long len = sum >> 2;
        return process(arr, 0, 0, len, 4);
    }

    // status: 使用边的情况, 用位信息表示, 0: 没有用, 1: 用过
    // sum: 当前凑够的边长
    // len: 固定参数, 每条边长度
    // edges: 还剩几条边要凑
    public static boolean process(int[] arr, int status, long sum, long len, int edges) {
        int N = arr.length;
        if (edges == 0) {
            return status == (1 << N) - 1;
        }
        boolean ans = false;
        // 还没凑满, 剩下边数edges > 0
        for (int i = 0; i < N; i++) { // 尝试每一条可用的边,去凑当前的边
            if ((status & 1 << i) == 0) {
                if (sum + arr[i] <= len) { // 可以使用当前边
                    if (sum + arr[i] < len) {
                        ans = process(arr, status | 1 << i, sum + arr[i], len, edges);
                    } else {
                        // 当前边凑齐了, 去凑吓一跳边
                        ans = process(arr, status | 1 << i, 0, len, edges - 1);
                    }
                }
            }
            if (ans) {
                break; // 有任何一个支路走通, 返回
            }
        }
        return ans;
    }

    public boolean makesquare2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int N = arr.length;
        long sum = 0;
        for (int num : arr) {
            sum += num;
        }
        if (sum % 4 != 0) {
            return false;
        }
        long len = sum >> 2;
        int[] dp = new int[1 << N];
        return process2(arr, 0, 0, len, 4, dp);
    }

    // status: 使用边的情况, 用位信息表示, 0: 没有用, 1: 用过
    // sum: 当前凑够的边长
    // len: 固定参数, 每条边长度
    // edges: 还剩几条边要凑
    public static boolean process2(int[] arr, int status, long sum, long len, int edges, int[] dp) {
        int N = arr.length;
        if (edges == 0) {
            return status == (1 << N) - 1;
        }
        if (dp[status] != 0) {
            return dp[status] == 1;
        }
        boolean ans = false;
        // 还没凑满, 剩下边数edges > 0
        for (int i = 0; i < N; i++) { // 尝试每一条可用的边,去凑当前的边
            if ((status & 1 << i) == 0) {
                if (sum + arr[i] <= len) { // 可以使用当前边
                    if (sum + arr[i] < len) {
                        ans = process2(arr, status | 1 << i, sum + arr[i], len, edges, dp);
                    } else {
                        // 当前边凑齐了, 去凑吓一跳边
                        ans = process2(arr, status | 1 << i, 0, len, edges - 1, dp);
                    }
                }
            }
            if (ans) {
                break; // 有任何一个支路走通, 返回
            }
        }
        dp[status] = ans ? 1 : -1;
        return ans;
    }
}
