package LeetCode;

// IMP: 滑动窗口板子题!

public class Problem_1343_NumOfSubArrays {

    public int numOfSubarrays(int[] arr, int k, int threshold) {
        if (arr == null || arr.length == 0 || arr.length < k) {
            return 0;
        }
        int N = arr.length;
        int L = 0;
        int R = 0;
        int sum = 0;
        int ans = 0;
        while (R < N) {
            sum += arr[R];
            if (R - L + 1 == k) { // NOTE: 窗口根本不可能有>k的时候
                if (sum / k >= threshold) {
                    ans++;
                }
                sum -= arr[L];
                L++;
            }
            R++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {2, 2, 2, 2, 5, 5, 5, 8};
        int k = 3, threshold = 4;
        var ans = new Problem_1343_NumOfSubArrays().numOfSubarrays(arr, k, threshold);
        System.out.println(ans);
    }
}
