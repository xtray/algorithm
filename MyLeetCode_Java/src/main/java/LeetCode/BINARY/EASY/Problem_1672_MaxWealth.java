package LeetCode.BINARY.EASY;

public class Problem_1672_MaxWealth {

    public int maximumWealth(int[][] accounts) {
        if(accounts == null || accounts.length == 0|| accounts[0] == null || accounts[0].length ==0) {
            return 0;
        }
        int max = 0;
        for(int[] ac: accounts) {
            int sum = 0;
            for(int a: ac) {
                sum+=a;
            }
            max = Math.max(max, sum);
        }
        return max;
    }
}
