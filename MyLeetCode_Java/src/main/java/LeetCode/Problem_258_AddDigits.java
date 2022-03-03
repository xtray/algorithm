package LeetCode;

public class Problem_258_AddDigits {

    public int addDigits(int num) {
        if (num <= 0) {
            return 0;
        }
        int ans = 0;
        do {
            ans = 0;
            while (num != 0) {
                ans += num % 10;
                num /= 10;
            }
            num = ans;
        } while (ans > 9);
        return ans;
    }

    // https://leetcode-cn.com/problems/add-digits/solution/ge-wei-xiang-jia-by-leetcode-solution-u4kj/
    public int addDigits2(int num) {
        return (num - 1) % 9 + 1;
    }

    /**
     * num 不是 9 的倍数时，其数根即为 num 除以 9 的余数。
     * num 是 9 的倍数时, num ==0, 则数根是 0
     * num 是 9 的倍数时, num >0, 则数根是 9
     */
    public int addDigits3(int num) {
        if (num <= 0) {
            return 0;
        }
        return num % 9 == 0 ? 9 : num % 9;
    }

    public static void main(String[] args) {
        int num = Integer.MAX_VALUE;
        var ans = new Problem_258_AddDigits().addDigits(num);
        System.out.println(ans);
    }
}
