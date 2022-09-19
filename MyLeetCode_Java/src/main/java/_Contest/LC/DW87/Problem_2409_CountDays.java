package _Contest.LC.DW87;

public class Problem_2409_CountDays {

    private static final int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int a = getDay(arriveAlice), b = getDay(leaveAlice);
        int c = getDay(arriveBob), d = getDay(leaveBob);
        return Math.max(0, Math.min(b, d) - Math.max(a, c)) + 1;
    }

    private int getDay(String arriveAlice) {
        String[] ss = arriveAlice.split("-");
        int m = Integer.parseInt(ss[0]);
        int d = Integer.parseInt(ss[1]);
        int day = 0;
        for (int i = 1; i < m; i++) day += days[i];
        return day + d;
    }

    public static void main(String[] args) {
        String arriveAlice = "08-15", leaveAlice = "08-18", arriveBob = "08-16", leaveBob = "08-19";
        var ans = new Problem_2409_CountDays().countDaysTogether(arriveAlice, leaveAlice, arriveBob, leaveBob);
        System.out.println(ans);
    }
}
