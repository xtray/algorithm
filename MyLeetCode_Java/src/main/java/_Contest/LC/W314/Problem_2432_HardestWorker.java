package _Contest.LC.W314;

public class Problem_2432_HardestWorker {

    public int hardestWorker(int n, int[][] logs) {
        int time = 0;
        int minTime = 0;
        int minId = n;
        int pre = 0;
        for (int[] log : logs) {
            time = log[1] - pre;
            if (time > minTime || time == minTime && log[0] < minId) {
                minTime = time;
                minId = log[0];
            }
            pre = log[1];
        }
        return minId;
    }

}
