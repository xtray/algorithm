package LeetCode;

import java.util.Arrays;

public class Problem_2405_OptimalPartition {

    public int partitionString(String s) {
        boolean[] map = new boolean[26]; // 上一次出现位置
        int cnt = 0;
        int idx = 0;
        for (char ch : s.toCharArray()) {
            if (map[ch - 'a']) {
                Arrays.fill(map, false);
                cnt++;
            }
            map[ch - 'a'] = true;
            idx++;
        }
        return cnt + 1;
    }

    public static void main(String[] args) {
        String s = "a";
        var ans = new Problem_2405_OptimalPartition().partitionString(s);
        System.out.println(ans);
    }
}
