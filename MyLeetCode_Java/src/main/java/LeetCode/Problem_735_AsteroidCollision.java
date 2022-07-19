package LeetCode;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Problem_735_AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int as : asteroids) {
            boolean ok = true;
            while (ok && !stack.isEmpty() && stack.peekFirst() > 0 && as < 0) {
                int peek = stack.peekFirst();
                if (peek > -as) {
                    ok = false;
                } else if (peek < -as) {
                    stack.pollFirst();
                } else { // 同归于尽
                    stack.pollFirst();
                    ok = false;
                }
                // // 合并上面逻辑
                // if (peek >= -as) {
                //     ok = false;
                // }
                // if (peek <= -as) {
                //     stack.pollFirst();
                // }
            }
            if (ok) {
                stack.addFirst(as);
            }
        }
        int[] ans = new int[stack.size()];
        int idx = stack.size() - 1;
        while (!stack.isEmpty()) {
            ans[idx--] = stack.pollFirst();
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] as = new int[]{-2,-1,1,2};
        int[] as = new int[]{1, -2, -2, -2};
        var ans = new Problem_735_AsteroidCollision().asteroidCollision(as);
        System.out.println(Arrays.toString(ans));

    }
}
