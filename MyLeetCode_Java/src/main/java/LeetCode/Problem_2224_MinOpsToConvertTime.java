package LeetCode;

public class Problem_2224_MinOpsToConvertTime {

    public int convertTime(String current, String correct) {

        int[] curTime = getTime(current);
        int[] aimTime = getTime(correct);
        // 1.先看分钟 MM
        // 2.目标分钟小, 从Hour 借1下来
        if (aimTime[1] < curTime[1]) {
            aimTime[0]--;
            aimTime[1] += 60;
        }
        // H2>=H1, M2>=M1
        int gap = 0;
        // 1.H的差距, 直接减
        gap = aimTime[0] - curTime[0];
        // 2.M的差距, 从大到小取
        gap += getMGaps(curTime[1], aimTime[1]);
        return gap;
    }

    // 得到from-->to的步数
    // from >= to
    private int getMGaps(int from, int to) {
        int[] steps = new int[]{15, 5, 1};
        int gap = to - from;
        int ans = 0;
        int idx = 0;
        while (gap > 0) {
            if (gap >= steps[idx]) {
                ans += gap / steps[idx];
                gap %= steps[idx];
            }
            idx++;
        }
        return ans;
    }

    private int[] getTime(String current) {
        String[] cur = current.split(":");
        int[] res = new int[2];
        res[0] = Integer.parseInt(cur[0]);
        res[1] = Integer.parseInt(cur[1]);
        return res;
    }

    public static void main(String[] args) {
        // String current = "02:30", correct = "04:35";
        // String current = "11:00", correct = "11:01";
        String current = "02:30", correct = "04:30";
        var ans = new Problem_2224_MinOpsToConvertTime().convertTime(current, correct);
        System.out.println(ans);
    }
}
