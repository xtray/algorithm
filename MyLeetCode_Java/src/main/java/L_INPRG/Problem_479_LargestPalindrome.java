package L_INPRG;

public class Problem_479_LargestPalindrome {

    // NOTE: 多看
    public int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }
        // 枚举最大的前一半
        int max = (int) Math.pow(10, n) - 1; // N位数里的最大数
        for (int i = max; i >= 0; i--) { // 枚举前一半
            long num = i; // 前一半
            long post = i; // 后一半
            // 利用回文串特性构造出具实际的回文数值 num
            while (post != 0) {
                num = num * 10 + (post % 10);
                post /= 10;
            }
            // 检查 num 能否分解成数位为 n 的数对(a,b)
            // 枚举其中一个大的即可
            for (long j = max; j * j >= num; j--) {
                if (num % j == 0) { // 如果整除, 代表找到a,b中大的那个数
                    return (int) (num % 1337);
                }
            }
        }
        return -1;
    }

}
