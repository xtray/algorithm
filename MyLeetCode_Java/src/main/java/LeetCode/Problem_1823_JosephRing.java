package LeetCode;

// IMP: 约瑟夫环, 剃刀感觉的函数, 高频题, 多看!!

import java.util.ArrayDeque;
import java.util.Queue;

public class Problem_1823_JosephRing {

    public int findTheWinner(int n, int k) {
        if (n == 1) {
            return 1;
        }
        return (findTheWinner(n - 1, k) + k - 1) % n + 1;
    }

    public int findTheWinner1(int n, int k) {
        int winner = 1;
        for (int i = 2; i <= n; i++) {
            winner = (winner + k - 1) % i + 1;
        }
        return winner;
    }

    // 模拟的做法, 时间复杂度：O(nk)
    public int findTheWinner2(int n, int k) {
        if (n == 1) {
            return 1;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            queue.offer(i); // 存储最初的老的编号
        }
        while (queue.size() > 1) {
            for (int i = 1; i < k; i++) {
                queue.offer(queue.poll()); // 最初的老的编号一直不变, 用队列维持顺序
            }
            queue.poll(); // i == k, 杀掉的人
        }
        return queue.peek();
    }


}
