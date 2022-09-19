package _Codility;

import java.util.ArrayDeque;

/**
 * Fish
 *
 * N voracious fish are moving along a river. Calculate how many fish are alive.
 *
 * https://app.codility.com/demo/results/trainingZF82JK-7YJ/
 */

public class Problem_TR702_Fish {

    public int solution(int[] A, int[] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0 || A.length != B.length) {
            return 0;
        }
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int N = A.length;
        for (int i = 0; i < N; i++) {
            int num = A[i];
            num = B[i] == 0 ? num : -num; // 0 上 +,  1 下 -
            if (stack.isEmpty() ||
                    !(stack.peekLast() < 0 && num > 0)) {
                stack.addLast(num);
                continue;
            }
            boolean save = false;
            while (!stack.isEmpty() && stack.peekLast() < 0 && num > 0) {
                if (-stack.peekLast() < num) {
                    stack.pollLast();
                    save = true;
                } else {
                    save = false;
                    break;
                }
            }
            if (save) {
                stack.addLast(num);
            }
        }
        return stack.size();
    }

    public static void main(String[] args) {
        int[] A = {4, 3, 2, 1, 5};
        int[] B = {0, 1, 0, 0, 0}; // 2
        var ans = new Problem_TR702_Fish().solution(A, B);
        System.out.println(ans);
    }
}
