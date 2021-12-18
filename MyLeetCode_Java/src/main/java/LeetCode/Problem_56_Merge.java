package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_56_Merge {

    public int[][] merge(int[][] intervals) {
        if(intervals == null||intervals.length == 0) {
            return new int[][]{};
        }
        Arrays.sort(intervals, (a,b)-> a[0] - b[0]);
        int s = intervals[0][0];
        int e = intervals[0][1];
        List<int[]> ans = new ArrayList<>();
        for(int i = 1;i<intervals.length;i++){
            if(intervals[i][0]>e) { // 新组开始时间>=上一组结束时间, 结算
                ans.add(new int[]{s, e});
                s = intervals[i][0];
                e = intervals[i][1];
            } else {
                e = Math.max(e, intervals[i][1]);
            }
        }
        ans.add(new int[]{s, e});
        int[][]res = new int[ans.size()][2];
        for(int i = 0; i< ans.size();i++){
            res[i] = ans.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,4},{4,5}};
        Problem_56_Merge sl = new Problem_56_Merge();
        int[][] ans = sl.merge(arr);
        for(int[] num : ans) {
            System.out.printf("[%d->%d]\t", num[0],num[1]);
        }
        System.out.println();
    }
}
