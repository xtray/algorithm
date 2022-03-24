package LeetCode.Jianzhi;


public class Problem_JZ053_II_MissingNumber {

    // 这个解法不需要数组有序, 本题是有序的
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }
        int N = nums.length;
        for (int i = 0; i < N; ) {
            if (nums[i] == i || nums[i] > N - 1) {
                i++;
            } else {
                swap(nums, i, nums[i]);
            }
        }
        for (int i = 0; i < N; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return N;
    }

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // 因为是有序的
    public int missingNumber0(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return N;
    }

    // 二分

    /**
     * 因为数组是有序的，如果缺少的数字不是n-1的话，假设缺少的数字是i，
     * 那么从索引为i开始nums[i] != i，而对于小于i之前的所有索引j都有nums[j]=j。根据这个就可以做二分。
     * IMP: 注意二分, ans == -1的返回值判断!
     */
    public int missingNumber2(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }
        int N = nums.length;
        int L = 0;
        int R = N - 1;
        int ans = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (nums[mid] == mid) {
                L = mid + 1;
            } else {
                ans = mid;
                R = mid - 1;
            }
        }
        return ans != -1 ? ans : N;
    }

    public int missingNumber3(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }
        int N = nums.length;
        int L = 0;
        int R = N - 1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (nums[mid] == mid) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return L;
    }

    public static void main(String[] args) {
        // int[] nums = {0,3,1};
        // int[] nums = {0,2,5};
        int[] nums = {0};
        var ans = new Problem_JZ053_II_MissingNumber().missingNumber2(nums);
        System.out.println(ans);
    }
}
