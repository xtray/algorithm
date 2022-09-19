package LeetCode;


// PRIORITY

public class Problem_2407_LISII_P {


    public int lengthOfLIS(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] ends = new int[N];
        ends[0] = arr[0];
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        int max = 1;
        for (int i = 1; i < N; i++) {
            l = 0;
            r = right;
            // > 某个数最左位置
            while (l <= r) {
                m = (l + r) / 2;
                if (arr[i] > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            // right = Math.max(right, l);
            // if (l > 0 && arr[i] - ends[l - 1] <= k) {
            //     ends[l] = arr[i];
            // } else if (l == 0) {
            //     ends[l] = arr[i];
            // }
            //
            // max = Math.max(max, l + 1);

            right = Math.max(right, l);
            ends[l] = arr[i];
            max = Math.max(max, l + 1);

        }
        // int cnt = max;
        // for (int i = 1; i < max; i++) {
        //     if (ends[i] - ends[i - 1] > k) {
        //         cnt--;
        //     }
        // }
        return max;
    }

    // [10,3,20,2,16,12]
    // 4
    public static void main(String[] args) {
        int[] nums = {10, 3, 20, 2, 16, 12};
        int k = 4; // 2
        var ans = new Problem_2407_LISII_P().lengthOfLIS(nums, k);
        System.out.println(ans);
    }
}
