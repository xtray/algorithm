package _LintCode;

public class Problem_419_RomanToInt {

    // I  V  X  L  C   D   M
    // 1  5  10 50 100 500 1000
    // 当前比下一个小就表示负数
    // 当前如果大于等于下一个就表示正数
    // 每一位出一个数, 累加起来就是答案
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int N = s.length();
        int[] nums = new int[N];
        int idx = 0;
        for (char ch : s.toCharArray()) {
            switch (ch) {
                case 'I':
                    nums[idx++] = 1;
                    break;
                case 'V':
                    nums[idx++] = 5;
                    break;
                case 'X':
                    nums[idx++] = 10;
                    break;
                case 'L':
                    nums[idx++] = 50;
                    break;
                case 'C':
                    nums[idx++] = 100;
                    break;
                case 'D':
                    nums[idx++] = 500;
                    break;
                case 'M':
                    nums[idx++] = 1000;
                    break;
                default:
                    break;
            }
        }
        int sum = 0;
        for (int i = 0; i < N - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                nums[i] = -nums[i];
            }
            sum += nums[i];
        }
        // NOTE: 不要忘了最后一位 N-1
        sum += nums[N - 1];
        return sum;
    }
}
