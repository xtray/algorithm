package LeetCode;

public class Problem_191_HammingWeight {

    public int hammingWeight(int n) {
        if (n == 0) {
            return 0;
        }
        int cnt = 0;
        for(int i = 0; i<=31;i++) {
            if(((n>>i) & 1) != 0) {
                cnt++;
            }
        }
        return cnt;
    }
}
