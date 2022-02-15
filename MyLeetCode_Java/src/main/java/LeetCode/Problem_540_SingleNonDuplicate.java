package LeetCode;

public class Problem_540_SingleNonDuplicate {

    // 由于给定数组有序 且 常规元素总是两两出现，因此如果不考虑“特殊”的单一元素的话，
    // 我们有结论：成对元素中:
    //   第一个所对应的下标必然是偶数，第二个所对应的下标必然是奇数。
    public int singleNonDuplicate(int[] nums) {
        if (nums == null || nums.length == 0 || (nums.length & 1) == 0) {
            return 0;
        }
        int N = nums.length;
        int l = 0;
        int r = N - 1;
        while (l < r) { // 至少两个数
            int mid = l + ((r - l) >> 1);
            if ((mid & 1) == 0) { // 为偶数, 正常情况, 偶数与下一个坐标值相同
                if (mid + 1 < N && nums[mid + 1] == nums[mid]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            } else { // mid 为奇数数, 正常情况, 偶数与前一个坐标值相同
                if (mid - 1 >= 0 && nums[mid - 1] == nums[mid]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
        }
        return nums[l];
    }

    // 利用按位异或的性质，可以得到 mid 和相邻的数之间的如下关系
    // 当 mid 是偶数时，mid + 1= mid ^ 1
    // 当 mid 是奇数时，mid - 1= mid ^ 1
    public int singleNonDuplicate2(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] == nums[mid ^ 1]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }


    public static void main(String[] args) {
        int[] nums = new int[]{3, 3, 7, 7, 10, 11, 11};
        var ans = new Problem_540_SingleNonDuplicate().singleNonDuplicate(nums);
        System.out.println(ans);
    }
}
