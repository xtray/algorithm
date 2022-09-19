package _Exercise;

public class Problem_581_ShortestUnsortedSubarray {

    /**
     * 从左往右: 左边遍历过的最大值<=当前值, 打对号, 否则叉号, 记录X号最右的位置
     * 从右往左: 右边遍历过的最小值>=当前值, 打对号, 否则叉号, 记录X号最左的位置
     * 两个叉号直接的范围是需要进行排序
     */
    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int mostRight = -1;
        int leftMax = nums[0];
        for (int i = 1; i < N; i++) {
            if (nums[i] < leftMax) {
                mostRight = i;
            } else {
                leftMax = nums[i];
            }
        }
        if (mostRight == -1) {
            return 0;
        }
        int mostLeft = -1;
        int rightMin = nums[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            if (nums[i] > rightMin) {
                mostLeft = i;
            } else {
                rightMin = nums[i];
            }
        }
        return mostRight - mostLeft + 1;
    }

    public static void main(String[] args) {
        int[] nums = {2,6,4,8,10,9,15};
        var ans = new Problem_581_ShortestUnsortedSubarray().findUnsortedSubarray(nums);
        System.out.println(ans);
    }
}
