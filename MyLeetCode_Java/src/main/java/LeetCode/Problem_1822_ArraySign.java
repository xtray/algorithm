package LeetCode;

public class Problem_1822_ArraySign {

    public int arraySign(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int minus = 0;
        for (int num : nums) {
            if (num == 0) {
                return 0;
            }
            if (num < 0) {
                minus++;
            }
        }
        return (minus & 1) == 0 ? 1 : -1;
    }
}
