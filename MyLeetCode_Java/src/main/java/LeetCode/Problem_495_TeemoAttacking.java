package LeetCode;

public class Problem_495_TeemoAttacking {


    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if(timeSeries == null || timeSeries.length == 0 || duration ==0) {
            return 0;
        }
        int overLap = 0;
        int end = -1;
        for(int time : timeSeries) {
            if (end >= time) {
                overLap += end - time +1;
            }
            end = time + duration - 1;
        }
        return timeSeries.length*duration - overLap;
    }

    public static void main(String[] args) {
        Problem_495_TeemoAttacking sl = new Problem_495_TeemoAttacking();
        int[] ts = new int[]{1,2};
        int duration = 2;
        int ans = sl.findPoisonedDuration(ts, duration);
        System.out.println(ans);
    }
}
