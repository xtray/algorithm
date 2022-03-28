package BigComQues;

/**
 * 题目描述:
 * 刚进入富士康的Yassin在进行对iPhone的零件设计，众所周知，测量总是有误差的。现在Yassin需要对他的误差进行一定的计算。
 * 现在已经给定的图纸中标定了n个零件的大小，我们不妨将其记为序列A,而Yassin需要确定一个最小误差eps, 并用误差范围内的
 * 数据来从小到大生产一系列零件。
 * 具体而言，需要确定一个最小的eps,使得存在这样一个*上升*的正整数序列B,满足对于任意的1<=i<=n, 均有|Ai-Bi|<=eps
 *
 * Input:
 * 第一行一个数字n，表示图纸的个数。
 * 第二行见个正整数，表示序列A。
 * Output:
 * 一行一个正整数，为满足条件的最小的误差eps。
 *
 * SampleInput:
 * 6
 * 7 9 5 1 3 2
 * SampleOutput:
 * 6
 * 解释:
 *  对于A数组, 可以找到一个上升的B数组 3,4,5,6,7,8, 满足要求
 * Hint:
 * n<=50
 * 数据保证答案小于等于10^9
 */

public class Problem_FoxConn_MinEps {

    public int minEps(int n, int[] nums) {
        // 二分eps
        int L = 1;
        int R = (int) 1e9;
        int eps = -1;
        while (L <= R) {
            int epsMid = L + ((R - L) >> 1);
            if(checkGap(nums, epsMid)) {
                eps = epsMid;
                R = epsMid -1;
            } else {
                L = epsMid + 1;
            }
        }
        return eps;
    }

    // 验证在eps值确定的情况下, 能不能根据A数组得到一个
    // 升序的B数组
    private boolean checkGap(int[] nums, int eps) {
        // B可以选择的第一个数的范围[preLow, preHi]
        int preLow = nums[0] - eps;
        int preHi = nums[0] + eps;
        for (int i = 1; i < nums.length; i++) {
            int curLow = nums[i] - eps;
            int curHi = nums[i] + eps;
            // 前一个数抬高1得到新的范围跟当前范围取交集
            int hi = Math.min(preHi + 1, curHi);
            int low = Math.max(preLow + 1, curLow);
            if (low > hi) { // 中间任何一步没有交集false
                return false;
            }
            preHi = hi;
            preLow = low;
        }
        return true;
    }

    // 更贪心的做法?
    private boolean checkGap2(int[] nums, int eps) {
        // B可以选择的第一个数的范围[preLow, preHi]
        int preLow = nums[0] - eps;
        for (int i = 1; i < nums.length; i++) {
            int curLow = nums[i] - eps;
            int curHi = nums[i] + eps;
            if (preLow + 1 > curHi) {
                return false;
            }
            preLow = Math.max(curLow, preLow + 1);
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {7, 9, 5, 1, 3, 2};
        int N = nums.length;
        var ans = new Problem_FoxConn_MinEps().minEps(N, nums);
        System.out.println(ans);
    }
}
