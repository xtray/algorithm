package LeetCode.MianshiJindian;

public class Problem_0104_PermutePalindrome {

    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        char[] str = s.toCharArray();
        int[] cnt = new int[256];
        // 长度为偶数, 不允许cnt有奇数
        // 长度为奇数, 允许有且只有一个奇数
        int N = str.length;
        for (char ch : str) {
            cnt[ch]++;
        }
        boolean isOdd = N % 2 != 0;
        int oddCnt = 0;
        for (int i = 0; i < 256; i++) {
            if (cnt[i] % 2 != 0) {
                oddCnt++;
            }
        }
        return (isOdd && oddCnt == 1) || (!isOdd && oddCnt == 0);
    }
}
