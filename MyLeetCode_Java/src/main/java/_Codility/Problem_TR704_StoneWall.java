package _Codility;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * StoneWall
 *
 * Cover "Manhattan skyline" using the minimum number of rectangles.
 *
 * https://app.codility.com/demo/results/trainingWMUJTZ-FR8/
 */

public class Problem_TR704_StoneWall {

    // TAG: 单调栈
    public int solution(int[] H) {
        if (H == null || H.length == 0) {
            return 0;
        }
        int N = H.length;
        // 存的是下标
        ArrayDeque<Integer> stack = new ArrayDeque<>();// 找右边小于自己离自己最近的
        int[] right = new int[N];
        Arrays.fill(right, N);
        stack.addLast(0);
        for (int i = 1; i < N; i++) {
            while (!stack.isEmpty() && H[i] < H[stack.peekLast()]) {
                int top = stack.pollLast();
                right[top] = i;
            }
            stack.addLast(i);
        }
        int cnt = 0;
        // [高度, 结束]
        ArrayDeque<int[]> stack2 = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            while (!stack2.isEmpty() && i >= stack2.peekLast()[1]) {
                stack2.pollLast();
            }
            int rightPos = right[i];
            if (stack2.isEmpty() || (i < stack2.peekLast()[1] && H[i] > stack2.peekLast()[0])) {
                cnt++;
                stack2.addLast(new int[]{H[i], rightPos});
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] H = new int[]{8, 8, 5, 7, 9, 8, 7, 4, 8};
        var ans = new Problem_TR704_StoneWall().solution(H);
        System.out.println(ans);
    }
}
