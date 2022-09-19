package _Contest.LC.DW75;

public class Problem_2220_MinBitFlip {
    public int minBitFlips(int start, int goal) {
        int ans = 0;
        for (int i = 0; i <= 31; i++) {
            int mask = 1 << i;
            if (((start & mask) ^ (goal & mask)) != 0) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // int start = 10;
        // int goal = 7;
        int start = 3;
        int goal = 4;
        var ans = new Problem_2220_MinBitFlip().minBitFlips(start, goal);
        System.out.println(ans);
    }
}
