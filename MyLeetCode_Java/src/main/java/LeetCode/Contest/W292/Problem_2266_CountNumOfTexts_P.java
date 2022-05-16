package LeetCode.Contest.W292;

// PENDING: https://leetcode.cn/problems/count-number-of-texts/

public class Problem_2266_CountNumOfTexts_P {

    private static final int mod = (int) 1e9 + 7;
    private static final int[] numCntArray = {0, 0, 3, 3, 3, 3, 3, 4, 3, 4};

    public static int countTexts(String pressedKeys) {
        if (pressedKeys == null || pressedKeys.length() == 0) {
            return 0;
        }
        char[] str = pressedKeys.toCharArray();
        return process(str, 0);
    }

    // 从i出发的字符往后有多少可能
    private static int process(char[] str, int index) {
        int N = str.length;
        if (index == N) {
            return 1;
        }
        int cur = str[index] - '0';
        int cnt = 0;
        // 可能性:
        for (int i = index; i < index + numCntArray[cur]; i++) {
            if (str[i] == str[index]) {
                int next = process(str, i + 1) % mod;
                cnt = (cnt + next) % mod;
            } else {
                break;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        String pressedKeys = "22233";
        var ans = countTexts(pressedKeys);
        System.out.println(ans);
    }
}
