package LeetCode;

public class Problem_693_AlternateBits {

    public boolean hasAlternatingBits(int n) {
        if (n == 0) {
            return false;
        }
        boolean pre = (1 & n) != 0;
        n >>= 1;
        while (n != 0) {
            boolean cur = (n & 1) != 0;
            if (pre == cur) {
                return false;
            }
            pre = cur;
            n >>= 1;
        }
        return true;
    }

    public boolean hasAlternatingBits1(int n) {
        int pre = -1;
        while (n != 0) {
            int cur = n & 1;
            if ((pre ^ cur) == 0) {
                return false;
            }
            pre = cur;
            n >>= 1;
        }
        return true;
    }


    // IMP: 判断一个二进制数是否全部位1的技巧
    public boolean hasAlternatingBits2(int n) {
        if (n == 0) {
            return false;
        }
        int a = n & (n >> 1);
        // 将 a 与 a+1 按位与，当且仅当 a 的二进制表示全为 1 时，结果为 0
        return (a & (a + 1)) == 0;
    }

    public static void main(String[] args) {
        int n = 5;
        var ans = new Problem_693_AlternateBits().hasAlternatingBits(n);
        System.out.println(ans);
    }
}
