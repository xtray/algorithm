package LeetCode;

public class Problem_769_MaxChunksToSorted {

    /**
     * 首先找到从左块开始最小块的大小。如果前 k 个元素为 [0, 1, ..., k-1]，
     * 可以直接把他们分为一个块。当我们需要检查 [0, 1, ..., n-1] 中前 k+1 个元素是不是 [0, 1, ..., k]
     * 的时候，只需要检查其中最大的数是不是 k 就可以了。
     */
    public int maxChunksToSorted(int[] arr) {
        int ans = 0, max = 0;
        for (int i = 0; i < arr.length; ++i) {
            max = Math.max(max, arr[i]);
            if (max == i) ans++;
        }
        return ans;
    }


    public int maxChunksToSorted1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] right = new int[N];
        right[N - 1] = arr[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            right[i] = Math.min(right[i+1], arr[i]);
        }
        int maxVal = arr[0];
        int minVal = -1;
        int ans = 1; // 至少是一块
        // 以左边的最大值为视角的做法
        for (int i = 0; i < N - 1; i++) {
            maxVal = Math.max(maxVal, arr[i]);
            minVal = right[i + 1];
            if (minVal >= maxVal) {
                ans++;
            }
        }
        return ans;
    }

    public int maxChunksToSorted2(int[] arr) {
        int N = arr.length;
        int[] mins = new int[N];
        // i ~ 最后位置上，最小值！
        // 5  | 6...
        // 17 | 18...
        mins[N - 1] = arr[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            mins[i] = Math.min(arr[i], mins[i + 1]);
        }
        int ans = 1;
        int max = arr[0];
        // 以右边的最小值为视角的做法
        for (int i = 1; i < N; i++) {
            if (max <= mins[i]) {
                ans++;
            }
            max = Math.max(max, arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] nums = new int[]{5, 4, 3, 2, 1}; //1
        // int[] nums = new int[]{1, 1, 0, 0, 1}; //2
        int[] nums = new int[]{2, 1, 3, 4, 4}; //4
        var ans = new Problem_769_MaxChunksToSorted().maxChunksToSorted(nums);
        System.out.println(ans);

    }
}
