package Contest.LCP.LCUP2022;

import java.util.Arrays;

public class Problem_001_Gem {

    public int giveGem(int[] gem, int[][] operations) {
        for(int[] op : operations) {
            int from = op[0];
            int to = op[1];
            int half = gem[from] >>1;
            gem[from] -= half;
            gem[to] += half;
        }
        Arrays.sort(gem);
        return gem[gem.length - 1] - gem[0];
    }

    public static void main(String[] args) {
        // int[] gem = {3,1,2};
        // int[][] operations = {{0,2}, {2,1}, {2,0}};
        // int[] gem = {100,0,50,100};
        // int[][] operations = {{0,2}, {0,1}, {3,0}, {3,0}};
        int[] gem = {0,0,0,0};
        int[][] operations = {{1,2}, {3,1}, {1,2}};
        var ans = new Problem_001_Gem().giveGem(gem, operations);
        System.out.println(ans);
    }
}
