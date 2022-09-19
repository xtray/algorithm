package LeetCode;

import java.util.Arrays;

public class Problem_2410_MaxMatch {

    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int cnt = 0;
        int N = players.length;
        int lower = 0;
        for (int i = 0; i < N; i++) {
            int match = getEqualOrUpper(trainers, lower, players[i]);
            if (match != -1) {
                cnt++;
                lower = match + 1;
            }
        }
        return cnt;
    }

    // 找到>=p最左侧的
    private int getEqualOrUpper(int[] nums, int lower, int target) {
        if (nums == null || nums.length == 0) return -1;
        int N = nums.length;
        int L = lower;
        int R = N - 1;
        int ans = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (nums[mid] >= target) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return ans;
    }

    public int matchPlayersAndTrainers1(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int cnt = 0;
        int N = players.length;
        int M = trainers.length;
        for (int i = 0, j = 0; i < N && j < M; i++) {
            while (j < M && players[i] > trainers[j]) {
                j++; // 跳过所有的弱鸡教练
            }
            if (j < M) { // 必然有 players[i] <= trainers[j]
                cnt++;
                j++; // j号教练被用了
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        // int[] players = {2, 1};
        // int[] trainers = {2, 2};
        int[] players = {4, 7, 9};
        int[] trainers = {2, 8, 8, 8};
        var ans = new Problem_2410_MaxMatch().matchPlayersAndTrainers(players, trainers);
        System.out.println(ans);

        var ans1 = new Problem_2410_MaxMatch().matchPlayersAndTrainers1(players, trainers);
        System.out.println(ans1);

    }

}
