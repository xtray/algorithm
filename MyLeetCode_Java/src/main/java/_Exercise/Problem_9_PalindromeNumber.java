package _Exercise;

public class Problem_9_PalindromeNumber {

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int len = getNumLen(x);
        int L = 0;
        int R = len - 1;
        int lowBase = 1;
        int hiBase = (int) (Math.pow(10, len - 1));

        while (L < R) {
            int low = (x / lowBase) % 10;
            int hi = (x / hiBase) % 10;
            if (low != hi) {
                return false;
            }
            lowBase *= 10;
            hiBase /= 10;
            L++;
            R--;
        }
        return true;
    }

    private int getNumLen(int num) {
        int cnt = 0;
        while (num != 0) {
            cnt++;
            num /= 10;
        }
        return cnt;
    }

    // 把数字整个翻转和原数比较
    public boolean isPalindrome1(int x) {
        if (x < 0) {
            return false;
        }
        int reverse = 0;
        int cur = x;
        while (cur != 0) {
            reverse = reverse * 10 + cur % 10;
            cur /= 10;
        }
        return reverse == x;
    }

    // 把数字翻转一半和原数比较, 但是这种做法必须过滤掉末尾是0的, 同时需要去除0~9
    public boolean isPalindrome2(int x) {
        if (x >= 0 && x <= 9) {
            return true;
        }
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        int reverse = 0;
        while (x > reverse) {
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }
        // 循环出来时, 如果x位数是奇数, reverse > x, 多一个中间的数

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == reverse || x == reverse / 10;
    }

    public static void main(String[] args) {
        // int num = 121;
        int num = 100;
        var ans = new Problem_9_PalindromeNumber().isPalindrome2(num);
        System.out.println(ans);
    }
}
