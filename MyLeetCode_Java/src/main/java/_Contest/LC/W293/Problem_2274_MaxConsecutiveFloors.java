package _Contest.LC.W293;

import java.util.Arrays;

public class Problem_2274_MaxConsecutiveFloors {

    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int ans = 0;

        int cur = 0;
        int idx = 0;
        int N = special.length;
        while (special[idx] < bottom) {
            idx++;
        }
        if (special[idx] == bottom) {
            cur = bottom + 1;
        } else {
            cur = bottom;
        }

        for (int i = idx; i < N; i++) {
            int gap = getGap(cur, special[i]);
            cur = special[i] + 1;
            ans = Math.max(ans, gap);
        }
        int gap = getGap(cur, top + 1);
        ans = Math.max(ans, gap);
        return ans;
    }

    // [cur, end)
    private int getGap(int cur, int end) {
        if (end <= cur) return 0;
        return end - cur;
    }


    public int maxConsecutive2(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int ans = 0;
        int cur = bottom;
        for (int sp : special) {
            if (sp == bottom) {
                cur = bottom + 1;
            } else {
                ans = Math.max(ans, sp - cur);
                cur = sp + 1;
            }
            // sp< bottom || sp > top 都会跳过
        }
        return Math.max(ans, top - cur + 1); //  NOTE: 不要忘了最后一个
    }

    public static void main(String[] args) {
        int bottom = 2, top = 9;
        int[] special = {4, 6};
        // int bottom = 6, top = 8;
        // int[] special = {7, 6, 8};
        var ans = new Problem_2274_MaxConsecutiveFloors().maxConsecutive2(bottom, top, special);
        System.out.println(ans);
    }
}
