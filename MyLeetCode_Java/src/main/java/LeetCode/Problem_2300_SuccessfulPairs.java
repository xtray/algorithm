package LeetCode;

import java.util.Arrays;

public class Problem_2300_SuccessfulPairs {

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        if (spells == null || spells.length == 0) {
            return new int[0];
        }
        int N = spells.length;
        int[] ans = new int[N];
        Arrays.sort(potions);
        for (int i = 0; i < N; i++) {
            ans[i] = getNum(spells[i], potions, success);
        }
        return ans;
    }

    private int getNum(int spell, int[] potions, long success) {
        int N = potions.length;
        long aim = (success + spell - 1) / spell;
        int pos = nearestIndex(potions, aim);
        return pos == -1 ? 0 : N - pos;
    }

    // 在arr上，找满足>=value的最左位置
    public static int nearestIndex(int[] arr, long value) {
        int L = 0;
        int R = arr.length - 1;
        int index = -1; // 记录最左的对号
        while (L <= R) { // 至少一个数的时候
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }
}
