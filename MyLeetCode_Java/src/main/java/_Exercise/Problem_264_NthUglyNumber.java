package _Exercise;

import java.lang.reflect.Array;
import java.util.*;

public class Problem_264_NthUglyNumber {

    private static final int[] nums = new int[]{2, 3, 5};

    // 优先队列
    public int nthUglyNumber(int n) {
        if (n == 1) {
            return 1;
        }
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
            if (cnt == n) {
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


    // https://leetcode.cn/problems/ugly-number-ii/solution/gong-shui-san-xie-yi-ti-shuang-jie-you-x-3nvs/
    // 更好理解的: https://leetcode.cn/problems/ugly-number-ii/solution/by-lfool-tn3y/
    // 多路归并, 三指针
    public int nthUglyNumber1(int n) {
        // ans 用作存储已有丑数（从下标 1 开始存储，第一个丑数为 1）
        int[] ans = new int[n + 1];
        ans[1] = 1;
        int p2 = 1;
        int p3 = 1;
        int p5 = 1;
        int idx = 2;
        while (idx <= n) {
            // 对于每一个丑数 n，均可以向外衍生出三个与之对应的丑数：n * 2, n * 3, n * 5
            // * 2 的对到 p2链表里
            // * 3 的对到 p3链表里
            // * 5 的对到 p5链表里
            // 相当于三条链表
            int a = ans[p2] * 2;
            int b = ans[p3] * 3;
            int c = ans[p5] * 5;
            // 将三个有序序列中的最小一位存入「已有丑数」序列，并将其下标后移
            int min = Math.min(a, Math.min(b, c));
            // 由于可能不同有序序列之间产生相同丑数，因此只要一样的丑数就跳过
            // 指针后移 (同时具有去重的效果)
            if (min == a) p2++;
            if (min == b) p3++;
            if (min == c) p5++;
            ans[idx++] = min;
        }
        return ans[n];
    }

    public static void main(String[] args) {
        int n = 10;
        var ans = new Problem_264_NthUglyNumber().nthUglyNumber(n);
        System.out.println(ans);
    }
}
