package _Codility;

import java.util.*;
;

/**
 * NumberOfDiscIntersections
 * <p>
 * Compute the number of intersections in a sequence of discs.
 *
 * https://app.codility.com/demo/results/trainingNDBHT8-N3W/
 */
public class Problem_TR604_NumberOfDiscIntersections {

    // TAG: 扫描线, 使用JDK11, 8会超时
    public int solution(int[] A) {
        int N = A.length;
        long[][] event = new long[N << 1][2];
        //     0        1
        // [开始/结束,  时间]
        for (int i = 0; i < N; i++) {
            event[i][0] = 1; // 开始
            event[i][1] = i - A[i];
            event[i + N][0] = -1; // 结束
            event[i + N][1] = (long) i + A[i];
        }
        // 应该开始在前
        Arrays.sort(event, (o1, o2) -> o1[1] != o2[1] ? (o1[1] < o2[1] ? -1 : 1) : (int) (o2[0] - o1[0]));
        int ans = 0;
        int cnt = 0; // 之前有交叉的个数
        for (long[] e : event) {
            if (e[0] == 1) { // 每一个开始到来的时候做结算
                ans += cnt;
                if (ans > (int)1e7) {
                    return -1;
                }
                cnt++;
            } else {
                // 结束, 结算
                --cnt;
            }
        }
        return ans;
    }

    public int solution1(int[] A) {
        int N = A.length;
        List<long[]> list = new ArrayList<>();
        //     0        1
        // [开始/结束,  时间]
        for (int i = 0; i < N; i++) {
            list.add(new long[]{1, i - A[i]});
            list.add(new long[]{-1, (long) i + A[i]});
        }
        // 应该开始在前
        list.sort((o1, o2) -> o1[1] != o2[1] ? (o1[1] < o2[1] ? -1 : 1) : (int) (o2[0] - o1[0]));
        int cnt = 0; // 之前有交叉的个数
        int ans = 0;
        for (long[] e : list) {
            long type = e[0];
            if (type == 1) { // 每一个开始到来的时候做结算
                ans += cnt;
                if (ans > (int) 1e7) {
                    return -1;
                }
                cnt++;
            } else {
                // 结束, 结算
                --cnt;
            }
        }
        return ans;
    }

    // TLE的更厉害了
    private static class Info {
        private int type;
        private long time;

        public Info(int ty, long ti) {
            type = ty;
            time = ti;
        }
    }

    public int solution2(int[] A) {
        int N = A.length;
        List<Info> list = new ArrayList<>();
        //     0        1
        // [开始/结束,  时间]
        for (int i = 0; i < N; i++) {
            list.add(new Info(1, i - A[i]));
            list.add(new Info(-1, (long) i + A[i]));
        }
        // 应该开始在前
        list.sort((o1, o2) -> o1.time != o2.time ? (o1.time < o2.time ? -1 : 1) : (o2.type - o1.type));
        int cnt = 0; // 之前有交叉的个数
        int ans = 0;
        for (Info e : list) {
            if (e.type == 1) { // 每一个开始到来的时候做结算
                ans += cnt;
                if (ans > (int) 1e7) {
                    return -1;
                }
                cnt++;
            } else {
                // 结束, 结算
                --cnt;
            }
        }
        return ans;
    }



    public static void main(String[] args) {
        int[] A = {1, 5, 2, 1, 4, 0}; // 11
        // int[] A = {1, Integer.MAX_VALUE, 0}; // 2
        var ans = new Problem_TR604_NumberOfDiscIntersections().solution(A);
        System.out.println(ans);
    }
}
