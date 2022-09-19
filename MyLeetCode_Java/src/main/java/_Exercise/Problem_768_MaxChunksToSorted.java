package _Exercise;

public class Problem_768_MaxChunksToSorted {

    public int maxChunksToSorted(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        // right[i]: i...N-1 之间最小值
        int[] right = new int[N];
        right[N - 1] = arr[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            right[i] = Math.min(right[i+1], arr[i]);
        }
        // 0...i之间的最大值
        int maxVal = arr[0];
        int minVal = -1;
        int ans = 1; // 至少是一块
        // 以左边的最大值为视角的做法
        // 如果左边遍历过的最大值 <= 右边没遍历过的最小值就可以切分, 否则不能切
        for (int i = 0; i < N - 1; i++) {
            maxVal = Math.max(maxVal, arr[i]); // 0~i之间的最大值
            minVal = right[i + 1]; // i+1~N-2之间的最小值
            if (minVal >= maxVal) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] nums = {5,4,3,2,1}; // 1
        int[] nums = {0,2,1,4,3}; // 3
        var ans = new Problem_768_MaxChunksToSorted().maxChunksToSorted(nums);
        System.out.println(ans);
    }
}
