package AlgoExpert;

public class Problem_000_CountInversions {

    // 求数组中的逆序对数量
    public int countInversions(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    // 请把arr[L..R]排有序
    // l...r N
    // T(N) = 2 * T(N / 2) + O(N)
    // O(N * logN)
    private static int process(int[] arr, int L, int R) {
        if (L == R) { // base case
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        int ans = 0;
        ans += process(arr, L, mid);
        ans += process(arr, mid + 1, R);
        ans += merge(arr, L, mid, R);
        return ans;
    }

    private static int merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        int ans = 0;
        while (p1 <= M && p2 <= R) {
            ans += arr[p1] > arr[p2] ? (M - p1 + 1) : 0;
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 要么p1越界了，要么p2越界了
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }
}
