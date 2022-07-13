package LeetCode.JZOffer;

// IMP: 约瑟夫环, 剃刀感觉的函数, 高频题, 多看!!

import java.util.ArrayDeque;
import java.util.Queue;

// NOTE: 本题是0~N-1

public class Problem_JZ62_JosephRing {

    public int lastRemaining(int n, int m) {
        return findTheWinner(n, m) - 1;
    }

    public int findTheWinner(int n, int m) {
        if (n == 1) {
            return 1;
        }
        return (findTheWinner(n - 1, m) + m - 1) % n + 1;
    }

    public int findTheWinner1(int n, int m) {
        int winner = 1;
        for (int i = 2; i <= n; i++) {
            winner = (winner + m - 1) % i + 1;
        }
        return winner;
    }

    // 模拟的做法, 时间复杂度：O(nm)
    public int findTheWinner2(int n, int m) {
        if (n == 1) {
            return 1;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            queue.offer(i); // 存储最初的老的编号
        }
        while (queue.size() > 1) {
            for (int i = 1; i < m; i++) {
                queue.offer(queue.poll()); // 最初的老的编号一直不变, 用队列维持顺序
            }
            queue.poll(); // i == m, 杀掉的人
        }
        return queue.peek();
    }


}
