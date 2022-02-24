package AlgoExpert;

public class Problem_000_MinNumberOfJumps {

    public static int minNumberOfJumps(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int step = 0; // 一开始站在 arr[0]
        int curRange = arr[0];
        int nextRange = -1;// 当前 curRange 范围内, 如果蹦一步能到的最大
        for (int i = 1; i < N; ) {
            if (curRange >= N - 1) break;
            while (i < N && i <= curRange) {
                if (i + arr[i] > nextRange) {
                    nextRange = i + arr[i];
                }
                i++;
            }
            step++;
            curRange = nextRange;
            nextRange = -1;
        }
        return step;
    }

    public static int minNumberOfJumps2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int step = 0;
        int cur = 0;
        int next = 0;
        for (int i = 0; i < arr.length; i++) {
            if (cur < i) {
                step++;
                cur = next;
            }
            next = Math.max(next, i + arr[i]);
        }
        return step;
    }

    public static void main(String[] args) {
        // int[] input = {1};
        int[] input = {3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3};
        var ans = minNumberOfJumps(input);
        System.out.println(ans);
    }
}
