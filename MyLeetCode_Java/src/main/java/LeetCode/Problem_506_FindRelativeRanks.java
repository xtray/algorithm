package LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem_506_FindRelativeRanks {

    public String[] findRelativeRanks(int[] score) {
        if(score == null || score.length ==0) {
            return new String[]{};
        }
        int N = score.length;
        String[] ans = new String[N];
        int[] dup = score.clone();
        Arrays.sort(dup);
        Map<Integer, Integer> map = new HashMap<>();
        // 0 ~ N
        // 1 ~ N-1
        // N-1 ~ 1
        for(int i=N-1;i>=0;i--) {map.put(dup[i], N-i-1);}
        String[] rank = new String[]{"Gold Medal", "Silver Medal", "Bronze Medal"};
        for(int i=0;i<N;i++) {
            ans[i] = map.get(score[i]) <3?rank[map.get(score[i])]:String.valueOf(map.get(score[i]) + 1);
        }
        return ans;
    }
}
