package LeetCode.Contest.DW76;

public class Problem_6061_WaysToBuyPencils {

    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        int maxPen = total / cost1;
        long ans = 0;
        for (int i = 0; i <= maxPen; i++) {
            ans += (total - (long) i * cost1) / cost2 + 1;
        }
        return ans;
    }


    public static void main(String[] args) {
        // int total = 20, cost1 = 10, cost2 = 5;
        // int total = 5, cost1 = 10, cost2 = 10;
        int total = 1000000, cost1 = 1, cost2 = 1;
        var ans = new Problem_6061_WaysToBuyPencils().waysToBuyPensPencils(total, cost1, cost2);
        System.out.println(ans);
    }
}
