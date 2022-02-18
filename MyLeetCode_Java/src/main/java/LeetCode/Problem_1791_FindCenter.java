package LeetCode;

public class Problem_1791_FindCenter {

    // 统计所有点的入度
    public int findCenter(int[][] edges) {
        if (edges == null || edges.length == 0 || edges[0] == null || edges[0].length == 0) {
            return -1;
        }
        int N = edges.length; // n - 1
        int[] ind = new int[N + 2]; // 0 下标弃而不用
        for (int i = 0; i < N; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            ind[from]++;
            ind[to]++;
        }
        for(int i = 1; i<=N+1;i++) {
            if(ind[i]==N){
                return i;
            }
        }
        return -1;
    }

    // 中心节点必然出现在所有的 edges[i]edges[i] 中，因此使用前两条边即可确定答案
    public int findCenter1(int[][] edges) {
        int a = edges[0][0], b = edges[0][1];
        if (a == edges[1][0] || a == edges[1][1]) return a;
        else return b;
    }

    public static void main(String[] args) {
        // int[][] edges = {{1,2},{2,3},{4,2}};
        int[][] edges = {{1,2},{5,1},{1,3},{1,4}};
        var ans = new Problem_1791_FindCenter().findCenter(edges);
        System.out.println(ans);
    }
}
