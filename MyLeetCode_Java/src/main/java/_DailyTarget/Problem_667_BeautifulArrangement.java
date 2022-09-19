package _DailyTarget;

public class Problem_667_BeautifulArrangement {

    // https://leetcode.cn/problems/beautiful-arrangement-ii/solution/you-by-capital-worker-rnwi/
    // https://leetcode.cn/problems/beautiful-arrangement-ii/solution/by-ac_oier-lyns/

    // 初始值为1, 凑差值为k, k-1, k-2...1
    // 从首位开始按照「升序」间隔排列，次位开始按照「降序」间隔排列有n-1种, 用间隔升降序排列凑k-1种, 剩下直接升序间隔1(1种)
    public int[] constructArray(int n, int k) {
        int[] nums = new int[n];
        nums[0] = 1;
        // gap 从 1~k, 先填写k个
        for (int i = 1, gap = k; i <= k; i++, gap--) {
            if ((i & 1) == 1) { // 奇数
                nums[i] = nums[i - 1] + gap;
            } else {
                nums[i] = nums[i - 1] - gap;
            }
        }
        int idx = k + 1;
        while (idx < n) {
            nums[idx] = idx + 1;
            idx++;
        }
        return nums;
    }
}
