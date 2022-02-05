package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_1414_FindMinFibNumbers {

    private static List<Integer> list = new ArrayList<>();

    // 打表
    static {
        int a = 1;
        int b = 1;
        list.add(1);
        while (b <= (int) 1e9) {
            int c = a + b;
            a = b;
            b = c;
            list.add(c);
        }
    }

    public int findMinFibonacciNumbers(int k) {

        int ans = 0;
        while (k != 0) {
            int L = 0;
            int R = list.size() - 1;
            // 找<=k 最右的位置/最大的数
            int pos = -1; // 记录最右的对号
            while (L <= R) {
                int mid = L + ((R - L) >> 1);
                if (list.get(mid) <= k) {
                    pos = mid;
                    L = mid + 1;
                } else {
                    R = mid - 1;
                }
            }
            k -= list.get(pos);
            ans++;
        }
        return ans;
    }


    public int findMinFibonacciNumbers1(int k) {
        List<Integer> fib = new ArrayList<Integer>();
        int a = 1, b = 1;
        fib.add(a);
        fib.add(b);

        while (a + b <= k) {
            int c = a + b;
            fib.add(c);
            a = b;
            b = c;
        }

        int ans = 0;

        for (int i = fib.size() - 1; i >= 0 && k > 0; i--) {
            while (k >= fib.get(i)) {
                k -= fib.get(i);
                ans++;
            }
        }

        return ans;
    }

}
