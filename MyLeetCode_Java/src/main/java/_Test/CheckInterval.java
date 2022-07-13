package _Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckInterval {

    public int[][] intervalIntersection(int[] firstList, int[] secondList) {
        int N = firstList.length;
        int M = secondList.length;
        int len = Math.max(N, M);
        int[] cnt = new int[len];

        for (int i = 0; i < len; i++) {
            if (i < N && firstList[i] == 0) {
                cnt[i]++;
            }
            if (i < M && secondList[i] == 0) {
                cnt[i]++;
            }
        }
        List<int[]> list = new ArrayList<>();
        int start = -1;
        int end = -1;
        for (int i = 0; i < len; i++) {
            if (start == -1 && cnt[i] == 2) {
                start = i;
            } else if (start != -1 && cnt[i] != 2) {
                end = i - 1;
            }
            if (start != -1 && end != -1) {
                list.add(new int[]{start, end});
                start = -1;
                end = -1;
            }
        }
        if (start != -1) {
            list.add(new int[]{start, len-1});
        }
        int[][] ans = new int[list.size()][2];
        int idx = 0;
        for (int[] gap : list) {
            ans[idx++] = gap;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr1 = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 0, 0, 0, 3, 3, 0, 0, 4, 0};
        int[] arr2 = {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 4, 4, 0};
        var ans = new CheckInterval().intervalIntersection(arr1, arr2);
        for (int[] num : ans) {
            System.out.print("(" + num[0] + ", " + num[1] + ")   ");
        }
        System.out.println();
    }
}
