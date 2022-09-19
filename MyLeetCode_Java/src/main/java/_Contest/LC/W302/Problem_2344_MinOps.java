package _Contest.LC.W302;

import java.math.BigInteger;
import java.util.*;

public class Problem_2344_MinOps {

    // ref: https://leetcode.cn/problems/minimum-deletions-to-make-array-divisible/solution/zhuan-huan-tan-xin-pythonjavacgo-by-endl-vdgw/
    public int minOperations(int[] nums, int[] numsDivide) {
        int gcd = 0;
        for (int num : numsDivide) {
            gcd = gcd(gcd, num);
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (gcd % nums[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        int a = 30;
        int b = 20;
        System.out.println(gcd(30, 20));
        System.out.println(new BigInteger("00100"));
    }
}
