package _Contest.LC.W308;

public class Problem_2391_MinTimeToCollectGarbage {

    public int garbageCollection(String[] garbage, int[] travel) {
        int N = garbage.length;
        // [ end, cnt]
        int[][] event = new int[3][2]; // M, P, G
        for (int i = 0; i < 3; i++) {
            event[i][0] = -1;
            event[i][1] = 0;
        }
        for (int i = 0; i < N; i++) {
            for (char ch : garbage[i].toCharArray()) {
                if (ch == 'M') {
                    event[0][0] = i; // 每次更新结束时间
                    event[0][1]++;
                }
                if (ch == 'P') {
                    event[1][0] = i; // 每次更新结束时间
                    event[1][1]++;
                }
                if (ch == 'G') {
                    event[2][0] = i; // 每次更新结束时间
                    event[2][1]++;
                }
            }
        }
        int[] sum = new int[N + 1];
        for (int i = 2; i <= N; i++) {
            sum[i] = sum[i - 1] + travel[i - 2];
        }
        int cost = 0;
        for (int i = 0; i < 3; i++) {
            int e = event[i][0];
            int c = event[i][1];
            if (e != -1) {
                cost += sum[e + 1];
                cost += c;
            }
        }
        return cost;
    }

    public static void main(String[] args) {
        String[] garbage = {"G", "P", "GP", "GG"};
        int[] travel = {2, 4, 3};
        var ans = new Problem_2391_MinTimeToCollectGarbage().garbageCollection(garbage, travel);
        System.out.println(ans);
    }
}
