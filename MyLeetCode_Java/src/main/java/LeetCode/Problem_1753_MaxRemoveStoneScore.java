package LeetCode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Problem_1753_MaxRemoveStoneScore {

    public int maximumScore(int a, int b, int c) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        pq.add(a);
        pq.add(b);
        pq.add(c);
        int ans = 0;
        while (pq.size() > 1) {
            int one = pq.poll();
            int two = pq.poll();
            if (--one > 0) pq.add(one);
            if (--two > 0) pq.add(two);
            ans++;
        }
        return ans;
    }

    public int maximumScore1(int a, int b, int c) {
        int[] arr = new int[]{a, b, c};
        Arrays.sort(arr);
        if (arr[2] > arr[0] + arr[1]) return arr[0] + arr[1];
        return Arrays.stream(arr).sum() >> 1;
    }
}
