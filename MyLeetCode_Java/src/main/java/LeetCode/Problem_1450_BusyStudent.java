package LeetCode;

public class Problem_1450_BusyStudent {

    // 差分
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int N = startTime.length;
        int[] diff = new int[1000 + 2];
        for (int i = 0; i < N; i++) {
            int s = startTime[i];
            int e = endTime[i];
            diff[s] += 1;
            diff[e + 1] += -1;
        }
        for (int i = 1; i <= 1000; i++) {
            diff[i] += + diff[i - 1];
        }
        return diff[queryTime];
    }

    public static void main(String[] args) {
        int[] startTime = {1, 2, 3};
        int[] endTime = {3, 2, 7};
        int queryTime = 4;
        var ans = new Problem_1450_BusyStudent().busyStudent(startTime, endTime, queryTime);
        System.out.println(ans);
    }
}
