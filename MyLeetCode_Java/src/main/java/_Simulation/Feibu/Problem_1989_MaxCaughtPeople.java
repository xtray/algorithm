package _Simulation.Feibu;

public class Problem_1989_MaxCaughtPeople {


    // TAG: 贪心, 紧着左侧的0优先处理
    public int catchMaximumAmountofPeople(int[] team, int dist) {
        int N = team.length;
        int i = 0; // 当前盯着的位置
        int j = 0; // 当前处理0来到的位置
        int cnt = 0;
        while (i < N) {
            if (team[i] == 1) {
                int down = Math.max(0, i - dist);
                int up = Math.min(i + dist, N - 1);
                while (j <= up) {
                    if (team[j] == 0 && j >= down) {
                        cnt++;
                        j++; // 去下一个位置
                        break;
                    }
                    j++;
                }
            }
            i++;
        }
        return cnt;
    }
}
