package Contest.LC.DW79;

import java.util.Arrays;

public class Problem_2285_MaxTotalImportanceOfRoads {

    public long maximumImportance(int n, int[][] roads) {
        int[][] point = new int[n][2];
        // int[2] : val, out
        for (int[] road : roads) {
            int from = road[0];
            int to = road[1];
            point[from][0] = from;
            point[from][1]++;
            point[to][0] = to;
            point[to][1]++;
        }
        Arrays.sort(point, (o1, o2) -> o2[1] - o1[1]); // 按出度从大到小排序
        int val = n;
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            if(point[i][1] != 0) {
                rank[point[i][0]] = val--;
            }
        }
        long ans = 0;
        for (int[] road : roads) {
            int from = road[0];
            int to = road[1];
            ans += rank[from] + rank[to];
        }
        return ans;
    }

    public static void main(String[] args) {
       // int n = 5;
       // int[][] roads = {{0,1},{1,2},{2,3},{0,2},{1,3},{2,4}}; //43
       //  int n = 5;
       //  int[][] roads = {{0,3},{1,3},{2,4}}; // 20
        int n = 5;
        int[][] roads = {{0,1}}; // 20
       var ans = new Problem_2285_MaxTotalImportanceOfRoads().maximumImportance(n, roads);
        System.out.println(ans);
    }
}
