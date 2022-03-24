package LeetCode;

public class Problem_1588_SumOddSubs {

    // 前缀和的解法
    public int sumOddLengthSubarrays(int[] arr) {

        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] prefixSum = new int[N + 1];
        // NOTE: 前缀和 位置+1的定义, 避免判0开头的情况
        for (int i = 0; i < N; i++) {
            prefixSum[i + 1] = arr[i] + prefixSum[i];
        }
        int sum = 0;
        for (int L = 0; L < N; L++) {
            for (int len = 1; L + len <= N; len += 2) {
                int R = L + len - 1;
                sum += prefixSum[R + 1] - prefixSum[L];
            }
        }
        return sum;
    }

    // 数学的解法
    // 计算每个元素的出现次数
    // ref: https://leetcode-cn.com/problems/sum-of-all-odd-length-subarrays/solution/suo-you-qi-shu-chang-du-zi-shu-zu-de-he-yoaqu/
    // ref: https://leetcode-cn.com/problems/sum-of-all-odd-length-subarrays/solution/gong-shui-san-xie-yi-ti-shuang-jie-qian-18jq3/
    // arr[i] 作为某个奇数子数组的成员的充要条件为：其所在奇数子数组左右两边元素个数奇偶性相同。
    public int sumOddLengthSubarrays2(int[] arr) {

        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            int leftCount = i;
            int rightCount = N - i - 1;
            int leftOdd = (leftCount + 1) / 2;
            int rightOdd = (rightCount + 1) / 2;
            int leftEven = leftCount / 2 + 1; // 要算上0, 加上左右两边不选也属于合法的偶数个数方案数
            int rightEven = rightCount / 2 + 1; // 要算上0
            sum += arr[i] * (leftOdd * rightOdd + leftEven * rightEven);
        }
        return sum;
    }
}
