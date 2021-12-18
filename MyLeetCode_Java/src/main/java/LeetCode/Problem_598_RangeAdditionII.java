package LeetCode;

public class Problem_598_RangeAdditionII {

    public int maxCount(int m, int n, int[][] ops) {

        int N = ops.length;
        int x= m;
        int y= n;

        for (int i =0; i<N;i++) {
            x = Math.max(x, ops[i][0]);
            y = Math.max(y, ops[i][1]);
        }

        return x*y;
    }
}
