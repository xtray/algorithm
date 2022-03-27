package LeetCode;

public class Problem_34_SearchRange {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int N = nums.length;
        int[] ans = new int[2];
        int L = 0;
        int R = N - 1;
        // 找>= target最左的位置
        int pos = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (nums[mid] < target) {
                L = mid + 1;
            } else if (nums[mid] >= target) {
                pos = mid;
                R = mid - 1;
            }
        }
        if (pos == -1 || nums[pos] != target) {
            return new int[]{-1, -1};
        }
        ans[0] = pos;
        while (pos < N && nums[pos] == target) {
            pos++;
        }
        ans[1] = pos - 1;
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        var ans = new Problem_34_SearchRange().searchRange(nums, target);
        System.out.println(ans[0] + "," + ans[1]);
    }
}
