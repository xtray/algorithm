package LeetCode;

public class Problem_1217_MinCost {

    public int minCostToMoveChips(int[] position) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < position.length; i++) {
            int cost = 0;
            for (int j = 0; j < position.length; j++) {
                if (j != i) {
                    boolean isEven = Math.abs(position[j] - position[i]) % 2 == 0;
                    if (!isEven) {
                        cost++;
                    }
                }
            }
            ans = Math.min(ans, cost);
        }
        return ans;
    }

    public int minCostToMoveChips1(int[] position) {
        int odd = 0;
        int even = 0;
        for (int pos : position) {
            if ((pos & 1) != 0) {
                odd++;
            } else {
                even++;
            }
        }
        return Math.min(odd, even);
    }

    public static void main(String[] args) {
        int[] position = {1, 2, 3};
        var ans = new Problem_1217_MinCost().minCostToMoveChips(position);
        System.out.println(ans);
    }
}
