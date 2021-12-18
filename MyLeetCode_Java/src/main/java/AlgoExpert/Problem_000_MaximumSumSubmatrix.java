package AlgoExpert;

public class Problem_000_MaximumSumSubmatrix {


    public static int maximumSumSubmatrix(int[][] matrix, int size) {
        if (matrix == null || matrix.length == 0 ||
                matrix[0] == null || matrix[0].length == 0 ||
                size == 0 || matrix.length < size) {
            return 0;
        }
        // 以每一行做为底部
        int[] help = new int[matrix[0].length];
        for (int j = 0; j < matrix[0].length; j++) {
            for (int i = 0; i < size - 1; i++) {
                help[j] += matrix[i][j];
            }
        }
        int max = Integer.MIN_VALUE;

        for (int i = size - 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                help[j] += matrix[i][j];
            }
            int ans = maxSumSubArray(help, size);
            max = Math.max(max, ans);
            for (int j = 0; j < matrix[0].length; j++) {
                help[j] -= matrix[i - size + 1][j];
            }
        }
        return max;
    }

    private static int maxSumSubArray(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k == 0) {
            return Integer.MIN_VALUE;
        }
        int L = 0;
        int R = arr.length - 1;
        int sum = 0;
        while (L < k - 1) {
            sum += arr[L++];
        }
        int max = Integer.MIN_VALUE;
        while (L <= R) {
            sum += arr[L++];
            max = Math.max(max, sum);
            sum -= arr[L - k];
        }
        return max;
    }

    // 数组中长度为 k 的子数组的最大累加和
    // 返回取得最大值的 L..R
    private static int[] maxSumSubArray2(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k == 0) {
            return new int[]{};
        }
        int L = 0;
        int R = arr.length - 1;
        int sum = 0;
        while (L < k - 1) {
            sum += arr[L++];
        }
        int[] res = new int[]{};
        int max = Integer.MIN_VALUE;
        while (L <= R) {
            sum += arr[L++];
            if (sum > max) {
                max = sum;
                res = new int[]{L - k, L - 1};
            }
            sum -= arr[L - k];
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{-2, 3, 4, -1, 5, -7};
//        int[] ans = maxSumSubArray2(arr, 3);
//        System.out.printf("%2d,%2d\n", ans[0], ans[1]);

        int[][] matrix = new int[][]{
                {5, 3, -1, 5},
                {-7, 3, 7, 4},
                {12, 8, 0, 0},
                {1, -8, -8, 2}
        };
        int size = 2;
        int res = maximumSumSubmatrix(matrix, size);
        System.out.println(res);
    }
}
