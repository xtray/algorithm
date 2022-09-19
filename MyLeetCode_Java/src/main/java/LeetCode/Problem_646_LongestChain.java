package LeetCode;

import java.util.Arrays;

public class Problem_646_LongestChain {

    // 按第二个数排序
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (o1, o2) -> o1[1] - o2[1]);
        int cnt = 0;
        int preEnd = Integer.MIN_VALUE;
        for (int i = 0; i < pairs.length; i++) {
            int curStart = pairs[i][0];
            int curEnd = pairs[i][1];
            if (curStart > preEnd) {
                cnt++;
                preEnd = curEnd; // 需要抬高
            }
        }
        return cnt;
    }
}
