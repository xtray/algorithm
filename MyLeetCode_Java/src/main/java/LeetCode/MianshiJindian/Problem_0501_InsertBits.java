package LeetCode.MianshiJindian;

public class Problem_0501_InsertBits {

    public int insertBits(int N, int M, int i, int j) {
        for (int b = i; b <= j; b++) {
            if ((M & (1 << (b - i))) != 0) {
                N |= 1 << b;
            }
        }
        return N;
    }
}
