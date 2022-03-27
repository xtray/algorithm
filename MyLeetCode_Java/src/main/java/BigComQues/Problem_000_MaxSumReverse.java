package BigComQues;

/**
 * 一个经典问题，即对于一个数组找出其和最大的子数组。
 * 现在允许你在求解该问题之前翻转这个数組的连续一段
 * 如翻转(1,2,3,4,5,6)的第三个到第五个元素組成的子数组得到的是(1,2,5,4,3,6)，
 * 则翻转后该数组的最大子段和最大能达到多少？
 */

public class Problem_000_MaxSumReverse {

    public static int maxSumReverse(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        // dp'[i]: 以i开头的最大子数组累加和
        int[] prefix = new int[N];
        prefix[N - 1] = arr[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            int p1 = arr[i];
            int p2 = arr[i] + prefix[i + 1];
            prefix[i] = Math.max(p1, p2);
        }
        // 以每个i位置做为分割i
        // 求i开头的最大子数组累加和 +
        // 0~i-1 的最大子数组累加和
        int ans = prefix[0];
        int maxSuffix = arr[0];
        for(int i = 1; i< N; i++) {
            ans = Math.max(ans, maxSuffix + prefix[i]);
            int cur = arr[i] + Math.max(0, maxSuffix);
            maxSuffix = Math.max(maxSuffix, cur);
        }
        ans = Math.max(ans, maxSuffix);
        return ans;
    }
}
