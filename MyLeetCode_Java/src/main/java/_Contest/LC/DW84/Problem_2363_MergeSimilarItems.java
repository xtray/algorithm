package _Contest.LC.DW84;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_2363_MergeSimilarItems {

    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(items1, (o1, o2) -> o1[0] - o2[0]);
        Arrays.sort(items2, (o1, o2) -> o1[0] - o2[0]);
        int N = items1.length;
        int M = items2.length;
        int i = 0;
        int j = 0;
        while (i < N || j < M) {
            List<Integer> cur = new ArrayList<>();
            if (i < N && j < M) {
                if (items1[i][0] < items2[j][0]) {
                    cur.add(items1[i][0]);
                    cur.add(items1[i][1]);
                    i++;
                } else if (items1[i][0] > items2[j][0]) {
                    cur.add(items2[j][0]);
                    cur.add(items2[j][1]);
                    j++;
                } else {
                    cur.add(items1[i][0]);
                    cur.add(items1[i][1] + items2[j][1]);
                    i++;
                    j++;
                }
            } else if (i < N) {
                cur.add(items1[i][0]);
                cur.add(items1[i][1]);
                i++;
            } else {
                cur.add(items2[j][0]);
                cur.add(items2[j][1]);
                j++;
            }
            ans.add(cur);
        }
        return ans;
    }
}
