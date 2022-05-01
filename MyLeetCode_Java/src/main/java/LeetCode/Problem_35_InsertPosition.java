package LeetCode;

public class Problem_35_InsertPosition {

    // NOTE: R = mid, 这种不调整的, 注意上面while边界为L < R, 否则会死循环

    // 查找>=target的最左位置
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int N = nums.length;
        int L = 0;
        int R = N - 1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (nums[mid] > target) {
                R = mid - 1;
            } else if (nums[mid] < target) {
                L = mid + 1;
            } else {
                return mid;
            }
        }
        // 跳出循环的时候
        return L;
    }

    // 查找>=target的最左位置
    public int searchInsert1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int N = nums.length;
        int L = 0;
        int R = N - 1;
        int pos = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (nums[mid] >= target) {
                pos = mid;
                R = mid - 1;
            } else if (nums[mid] < target) {
                L = mid + 1;
            }
        }
        // 跳出循环的时候
        return pos==-1?L:pos;
    }

    // 在arr上，找满足>=value的最左位置
    public static int searchInsert2(int[] arr, int value) {
        int L = 0;
        int R = arr.length - 1;
        int index = -1; // 记录最左的对号
        while (L <= R) { // 至少一个数的时候
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index==-1?arr.length:index;
    }

    public static void main(String[] args) {
        // int[] nums = {1, 3, 5, 6};
        // int target = 5;

        int[] nums = {1, 3, 5, 6};
        int target = 7;

        // int[] nums = {1};
        // int target = 0;

        // int[] nums = {1, 3};
        // int target = 2;

        var ans = new Problem_35_InsertPosition().searchInsert(nums, target);
        System.out.println(ans);

        var ans2 = new Problem_35_InsertPosition().searchInsert2(nums, target);
        System.out.println(ans2);
    }
}
