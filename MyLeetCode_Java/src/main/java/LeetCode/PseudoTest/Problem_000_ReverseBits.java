package LeetCode.PseudoTest;

public class Problem_000_ReverseBits {

    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if((n & (1<<i)) !=0)  { // 第i位是1
                ans |= 1<<(31-i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = -3;
        var ans = new Problem_000_ReverseBits().reverseBits(n);
        System.out.println(ans);
    }
}
