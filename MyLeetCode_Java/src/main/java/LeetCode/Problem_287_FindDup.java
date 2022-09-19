package LeetCode;

public class Problem_287_FindDup {

    // 找到第一个入环节点
    // 0位置的数作为出发点
    // 怎么通过有限的几个变量找一个单链表的第一个入环节节点的问题
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return -1;
        }
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (slow != fast) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }

    // https://www.lintcode.com/problem/633/solution/16662
    // O(N*logN)
    // f(x) = y: <= x的数有y个, 正常是一条斜率为1的线, 即<=x的数有x个
    // 当斜率为0时: 即 有缺失的数字
    // 当斜率>1时: 有重复的数字
    public int findDuplicate1(int[] nums) {
        int N = nums.length;
        int L = 1;
        int R = N;
        int ans = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (count(nums, mid) > mid) { // y> x, 发现多余的数, 继续往左找
                ans = mid;
                R = mid - 1;
            } else { // <= mid, 有数字缺失
                L = mid + 1;
            }
        }
        return ans;
    }

    // 统计<=target的数有几个
    private int count(int[] nums, int target) {
        int cnt = 0;
        for (int item : nums) {
            if (item <= target) {
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] nums = {5,3,2,4,4,1};
        var ans = new Problem_287_FindDup().findDuplicate(nums);
        System.out.println(ans);
    }
}
