package LeetCode;

public class Problem_2202_MaxTop {

    /**
     * 贪心取出前 k - 1个元素，第 k 次操作可以
     * 将已经移除的最大的元素放回栈顶，或
     * 取出第 k 个元素，此时栈顶为第 k 个元素
     */

    // https://leetcode-cn.com/problems/maximize-the-topmost-element-after-k-moves/solution/by-feilue-om3r/
    public int maximumTop(int[] nums, int k) {
        int N  = nums.length;
        if(N == 1 && (k&1)!=0) {
            return -1;
        }

        int idx = 0;
        int max = -1;
        while (idx < Math.min(N, k - 1)) {
            max = Math.max(max, nums[idx]);
            idx++;
        }
        if(k > N) {
            return max;
        }
        if(k == N) {
            return max;
        }
        // k < N
        return Math.max(max, nums[k]);
    }

    public static void main(String[] args) {
        // int[] nums = {91, 98, 17, 79, 15, 55, 47, 86, 4, 5, 17, 79, 68, 60, 60, 31, 72, 85, 25, 77, 8, 78, 40, 96, 76, 69, 95, 2, 42, 87, 48, 72, 45, 25, 40, 60, 21, 91, 32, 79, 2, 87, 80, 97, 82, 94, 69, 43, 18, 19, 21, 36, 44, 81, 99};
        // int k = 2; // 91
        // int[] nums = {35,43,23,86,23,45,84,2,18,83,79,28,54,81,12,94,14,0,0,29,94,12,13,1,48,85,22,95,24,5,73,10,96,97,72,41,52,1,91,3,20,22,41,98,70,20,52,48,91,84,16,30,27,35,69,33,67,18,4,53,86,78,26,83,13,96,29,15,34,80,16,49};
        // int k = 15; // 94
        // int[] nums = {5, 2, 2, 4, 0, 6};
        // int k = 4;
        // int[] nums = {68,76,53,73,85,87,58,24,48,59,38,80,38,65,90,38,45,22,3,28,11};
        // int k = 1;

        // int[] nums = {18};
        // int k = 3;
        int[] nums = {0};
        int k = 1000000000;
        var ans = new Problem_2202_MaxTop().maximumTop(nums, k);
        System.out.println(ans);
    }
}
