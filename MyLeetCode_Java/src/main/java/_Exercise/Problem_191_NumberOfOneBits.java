package _Exercise;

public class Problem_191_NumberOfOneBits {

    public int hammingWeight(int n) {
        int cnt = 0;
        while (n != 0) { // NOTE: 不能用n>0, 因为会有负数
            if ((n & 1) != 0) {
                cnt++;
            }
            n >>>= 1; // NOTE: 不能带符号右移
        }
        return cnt;
    }

    public int hammingWeight1(int n) {
        int cnt = 0;
        for(int i = 0; i<=31;i++) {
            if(((n>>i) & 1) != 0) {
                cnt++;
            }
        }
        return cnt;
    }
}
