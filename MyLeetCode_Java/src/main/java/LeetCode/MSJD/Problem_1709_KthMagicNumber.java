package LeetCode.MSJD;

import java.util.HashSet;
import java.util.*;

public class Problem_1709_KthMagicNumber {

    public int getKthMagicNumber(int k) {
        if (k == 1) {
            return 1;
        }
        int[] nums = new int[]{3, 5, 7};

        // 每次取出最小值进行2,3,5发散
        // NOTE: 会溢出!!
        PriorityQueue<Long> pq = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        pq.add(1L);
        set.add(1L);
        int cnt = 0;
        while (true) {
            cnt++; // 取出的最小值计数
            long cur = pq.poll(); // 每次取出最小值
            if (cnt == k) {
                return (int) cur;
            }
            for (int num : nums) {
                long next = cur * num;
                if (!set.contains(next)) {
                    set.add(next);
                    pq.add(next);
                }
            }
        }
    }


    // 多路归并
    // https://leetcode.cn/problems/get-kth-magic-number-lcci/solution/di-k-ge-shu-jiu-shi-xiang-bu-tong-wei-he-san-zhi-z/
    public int getKthMagicNumber1(int k) {
        if (k == 1) {
            return 1;
        }
        int[] ans = new int[k + 1];
        ans[0] = 1;
        int p3 = 0;
        int p5 = 0;
        int p7 = 0;
        int idx = 1; // 下一个要算的数
        while (idx <= k) {
            int a = ans[p3] * 3;
            int b = ans[p5] * 5;
            int c = ans[p7] * 7;
            int min = Math.min(a, Math.min(b, c));
            ans[idx++] = min;
            if (min == a) p3++;
            if (min == b) p5++;
            if (min == c) p7++;
        }
        return ans[k - 1];
    }

    public int getKthMagicNumber2(int k) {
        int[] numList = new int[k + 1];
        int p3 = 0, p5 = 0, p7 = 0;
        numList[0] = 1;
        for (int i = 1; i < k; i++) {
            numList[i] = Math.min(Math.min(numList[p3] * 3, numList[p5] * 5), numList[p7] * 7);
            if (numList[i] == numList[p3] * 3) p3++;
            if (numList[i] == numList[p5] * 5) p5++;
            if (numList[i] == numList[p7] * 7) p7++;
        }
        return numList[k - 1];
    }


    public static void main(String[] args) {
        int k = 643;
        var ans = new Problem_1709_KthMagicNumber().getKthMagicNumber1(k);
        System.out.println(ans);
    }
}
