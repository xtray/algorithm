package LeetCode;

import java.util.Arrays;

// IMP: 数学: 三角形两边之和大于第三边

public class Problem_611_TriangleNumber {

    // 时间复杂度：O(N^3)
    public int triangleNumber0(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int ans = 0;
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (nums[i] + nums[j] > nums[k]) ans++;
                }
            }
        }
        return ans;
    }


    // 时间复杂度：O(N^2 * logN)
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int N = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                // a, b, c
                // a + b > c
                int L = j + 1;
                int R = N - 1;
                int K = j; // or K = -1
                while (L <= R) {
                    int mid = L + ((R - L) >> 1);
                    if (nums[i] + nums[j] > nums[mid]) {
                        K = mid;
                        L = mid + 1;
                    } else {
                        R = mid - 1;
                    }
                }
                // ans += k == -1? 0 : K - j;
                ans += K - j;
            }
        }
        return ans;
    }

    // https://leetcode.cn/problems/valid-triangle-number/solution/you-xiao-san-jiao-xing-de-ge-shu-by-leet-t2td/
    public int triangleNumber1(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int N = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < N; i++) {
            // i, j...k
            // num[i] + nums[j] > nums[k]
            // 固定i, 随着j增加, num[i] + nums[j]增加, 那么满足条件的k也在同向增大,
            // 如果到下一个i, num[i] + nums[j]进一步增大, k会进一步增大, k不回退
            int k = i;
            for (int j = i + 1; j < N; j++) {
                while (k + 1 < N && nums[k + 1] < nums[i] + nums[j]) {
                    k++;
                }
                ans += Math.max(k - j, 0);
            }
        }
        return ans;
    }
}
