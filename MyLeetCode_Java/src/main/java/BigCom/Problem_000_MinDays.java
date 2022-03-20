package BigCom;


/**
 * 求完成任务的最小天数
 * 描述;给出一个一维任务数组A，数组内容是任务的紧急程度，另有一个整数M。一天可以完成多个任务，
 * 每个任务必须按顺序完成，一天内完成的任意两个任务的之间紧急程度差值不能大于M,求完成所有任务的最小天数。
 * 例如：A=[1,12,10,4,5,2]  M=2，答案是4
 */
public class Problem_000_MinDays {

    public static int getMinDays(int[] nums, int M) {
        if (nums == null || nums.length <= 1 || M < 0) {
            return -1;
        }
        int min = nums[0];
        int max = nums[0];
        int ans = 0;
        for (int i = 1; i < nums.length; i++) {
            // 任意两个任务的之间紧急程度差值不能大于M
            // [max-M..min....max..min+M]
            // min + M 上界
            // max - M 下届
            if (nums[i] > min + M || nums[i] < max - M) {
                ans++;
                max = nums[i];
                min = nums[i];
            } else {
                max = Math.max(max, nums[i]);
                min = Math.min(min, nums[i]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 12, 10, 4, 5, 2};
        int M = 2;
        var ans = getMinDays(nums, M);
        System.out.println(ans);
    }


}
