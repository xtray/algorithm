package L_INPRG;

import java.util.ArrayList;
import java.util.List;

public class Problem_386_LexicalOrder {

    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            process(i, n, ans);
        }
        return ans;
    }

    // 当前处理cur为头的树, 最大数字限制limit, 前序遍历
    void process(int cur, int limit, List<Integer> ans) {
        if (cur > limit) {
            return;
        }
        ans.add(cur); // 前序遍历
        for (int i = 0; i <= 9; i++) { // 每一层分十个叉展开做前序遍历
            process(cur * 10 + i, limit, ans);
        }
    }

    public List<Integer> lexicalOrder2(int n) {
        List<Integer> ans = new ArrayList<>();
        process2(n, ans);
        return ans;
    }

    // NOTE: 多看, 比较难理解
    // 以cur为头构建trie
    private void process2(int n, List<Integer> ans) {
        int cur = 1;
        // 共有 n 个数需要被处理
        for (int i = 0; i < n; i++) { // 控制循环次数为n
            ans.add(cur);
            if (cur * 10 <= n) { // 优先拓展到下一行 + 0
                // 在满足条件的前提下，我们优先在 cur 的后面添加 0
                cur *= 10;
            } else { // 如果不能拓展到下一行, 就本行++, 直到碰到一下两个条件:
                     // 1. cur % 10 == 9 : 处理到这一层最后一个数了
                     // 2. cur + 1 > n: 这一层没到最后一个数, 提前碰到n了
                     // 以上两种情况都需要往回推到上一层
                while (cur % 10 == 9 || cur + 1 > n) {
                    cur /= 10;
                }
                cur++;
            }
        }
    }

    public static void main(String[] args) {
        int n = 20;
        var ans = new Problem_386_LexicalOrder().lexicalOrder2(n);
        for (int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static class Problem_1539_FindKthPostive_F {
        // public int findKthPositive(int[] arr, int k) {
        //     if(arr == null || arr.length ==0) {
        //         return -1;
        //     }
        //     int N = arr.length;
        //     int range = arr[N-1];
        //     if(range - N >= k) { // 二分, 找缺失的第k个数
        //         int L = 1;
        //         int R = range;
        //         while (L <= R) {
        //             int mid = L + ((R - L)>>1);
        //             if(arr[])
        //
        //         }
        //
        //     } else {
        //         return N + k;
        //     }
        //
        // }
    }
}
