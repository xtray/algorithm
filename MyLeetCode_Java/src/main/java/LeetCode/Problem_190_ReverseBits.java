package LeetCode;

public class Problem_190_ReverseBits {

    public int reverseBits(int n) {

        int i = 0;
        int j = 31;
        while (i < j) {
            boolean onei = ((n >> i) & 1) != 0;
            boolean onej = ((n >> j) & 1) != 0;
            if (onei ^ onej) { // 不等
                n = onei ? n & (~(1 << i)) : n | (1 << i);
                n = onej ? n & (~(1 << j)) : n | (1 << j);
            }
            i++;
            j--;
        }
        return n;
    }

    public int reverseBits1(int n) {
        int rev = 0;
        for (int i = 0; i < 32 && n != 0; ++i) {
            rev |= (n & 1) << (31 - i);
            n >>>= 1;
        }
        return rev;
    }

    private static final int M1 = 0x55555555; // 01010101010101010101010101010101
    private static final int M2 = 0x33333333; // 00110011001100110011001100110011
    private static final int M4 = 0x0f0f0f0f; // 00001111000011110000111100001111
    private static final int M8 = 0x00ff00ff; // 00000000111111110000000011111111

    public int reverseBits2(int n) {
        n = n >>> 1 & M1 | (n & M1) << 1;
        n = n >>> 2 & M2 | (n & M2) << 2;
        n = n >>> 4 & M4 | (n & M4) << 4;
        n = n >>> 8 & M8 | (n & M8) << 8;
        return n >>> 16 | n << 16;
    }


}
