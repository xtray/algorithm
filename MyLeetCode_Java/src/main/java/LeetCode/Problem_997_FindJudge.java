package LeetCode;

public class Problem_997_FindJudge {
    // 注意特列, n =1 trust = {}, 有一个人的时候,默认是法官
    public int findJudge(int n, int[][] trust) {
        if (n < 1) {
            return -1;
        }
        return getJudge(n, trust);
    }

    // 所有人相信法官, 法官入度 n-1
    // 法官不相信任何人, 出度 0
    // 先假设所有人都是法官
    private int getJudge(int n, int[][] trust) {
        int[] in = new int[n+1];
        int[] out = new int[n+1];
        for(int[] arr : trust) {
            out[arr[0]]++;
            in[arr[1]]++;
        }
        for(int i=1;i<=n;i++) {
            if(in[i]==n-1&&out[i]==0) { // 有一个人的时候(n =1 trust = {})这个也成立
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] trust = new int[][]{{1, 2}, {2,3}};
        int ans = new Problem_997_FindJudge().findJudge(n, trust);
        System.out.println(ans);
    }
}
