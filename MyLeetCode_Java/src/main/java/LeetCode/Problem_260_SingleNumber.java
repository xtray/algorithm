package LeetCode;

public class Problem_260_SingleNumber {

    // 给定一个整数数组 nums，其中恰好有两个元素只出现一次，
    // 其余所有元素均出现两次。 找出只出现一次的那两个元素。
    // 你可以按 任意顺序 返回答案。
    // 异或，英文为exclusive OR，缩写成xor异或（xor）
    public int[] singleNumber(int[] nums) {

        if (nums == null || nums.length == 0) {
            return new int[]{};
        }

        int xor = 0;
        for (int num: nums) {
            xor ^= num;
        }
        // 获取最右侧的 1
        int mostRightOne = xor & (-xor);
        int oneXor = 0;
        for (int num: nums) {
            if ((num & mostRightOne) != 0) {
                oneXor ^= num;
            }
        }
        return new int[]{oneXor, xor^oneXor};
    }
}
