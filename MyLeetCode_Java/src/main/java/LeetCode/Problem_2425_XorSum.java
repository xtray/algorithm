package LeetCode;

public class Problem_2425_XorSum {

    public int xorAllNums(int[] nums1, int[] nums2) {
        int N = nums1.length;
        int M = nums2.length;
        if (N % 2 == 0 && M % 2 == 0) return 0;
        int xor1 = 0;
        int xor2 = 0;
        for (int num : nums1) xor1 ^= num;
        for (int num : nums2) xor2 ^= num;

        if ((N & 1) != 0 && (M & 1) != 0) {
            return xor1 ^ xor2;
        }
        if ((N & 1) != 0 && (M & 1) == 0) {
            return xor2;
        }
        if ((N & 1) == 0 && (M & 1) != 0) {
            return xor1;
        }
        return 0;
    }
}
