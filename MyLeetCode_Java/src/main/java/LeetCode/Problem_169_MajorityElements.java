package LeetCode;

public class Problem_169_MajorityElements {

    public int majorityElement(int[] nums) {
        int N = nums.length;
        int hp = 0;
        int candidate = 0;
        for (int num : nums) {
            if (hp == 0) {
                candidate = num;
                hp = 1;
            } else if (num == candidate) {
                hp++;
            } else {
                hp--;
            }
        }
        if (hp == 0) {
            return Integer.MIN_VALUE;
        }
        hp = 0;
        for (int num : nums) {
            if (num == candidate) {
                hp++;
            }
        }
        if (hp > N / 2) {
            return candidate;
        }
        return Integer.MIN_VALUE;
    }
}
