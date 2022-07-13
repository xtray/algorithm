package LeetCode.JZOffer;

public class Problem_JZ51_ReversePairs {

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return process(nums, 0, nums.length - 1);
    }

    private int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int M = L + ((R - L) >> 1);
        return process(arr, L, M) +
                process(arr, M + 1, R) +
                merge(arr, L, M, R);
    }

    private int merge(int[] arr, int L, int M, int R) {
        int p1 = M;
        int p2 = R;
        int[] help = new int[R - L + 1];
        int idx = R - L;
        int ans = 0;
        while (p1 >= L && p2 >= M + 1) {
            if (arr[p1] > arr[p2]) {
                help[idx--] = arr[p1--];
                ans += p2 - M;
            } else { // <=
                help[idx--] = arr[p2--];
            }
        }
        while (p1 >= L) {
            help[idx--] = arr[p1--];
        }
        while (p2 >= M + 1) {
            help[idx--] = arr[p2--];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {7, 5, 6, 4};
        var ans = new Problem_JZ51_ReversePairs().reversePairs(nums);
        System.out.println(ans);
    }
}
