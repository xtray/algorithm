package LeetCode;

public class Problem_1688_NumberOfMatches {

    // 暴力
    public int numberOfMatches(int n) {
        if (n <= 0) {
            return 0;
        }
        return process(n);
    }

    // n 个队伍一共需要多少场比赛
    private int process(int n) {
        if (n <=2) {
            return 1;
        }
        // n>1
        int ans = 0;
        if ((n & 1) == 1) { // 奇数
            ans = ((n - 1) >> 1) + process(((n - 1) >> 1) + 1);
        } else {
            ans = (n >> 1) + process(n >> 1);
        }
        return ans;
    }

    // n 支队伍，每场比赛淘汰一个队伍，所以需要 n-1n−1 场比赛，淘汰 n-1n−1 个队伍，最终剩下一个队伍。
    // 在每一场比赛中，输的队伍无法晋级，且不会再参加后续的比赛。由于最后只决出一个获胜队伍，
    // 因此就有 n-1n−1 个无法晋级的队伍，也就是会有 n-1n−1 场比赛。
    public int numberOfMatches1(int n) {
        return n - 1;
    }

    public static void main(String[] args) {
        int n = 14;
        var ans = new Problem_1688_NumberOfMatches().numberOfMatches(n);
        System.out.println(ans);

        int times = 200;
        for(int i = 1; i<times;i++) {
            var res = new Problem_1688_NumberOfMatches().numberOfMatches(i);
            System.out.println(i + ": " +res);
        }
    }
}
