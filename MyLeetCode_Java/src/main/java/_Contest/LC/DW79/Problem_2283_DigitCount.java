package _Contest.LC.DW79;

public class Problem_2283_DigitCount {

    public boolean digitCount(String num) {
        char[] str = num.toCharArray();
        int N = str.length;

        int[] cnt = new int[10];
        for (char ch : str) {
            cnt[ch - '0']++;
        }
        for (int i = 0; i < N; i++) {
            if (cnt[i] != str[i] - '0') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String num = "1210";
        // String num = "030";
        var ans = new Problem_2283_DigitCount().digitCount(num);
        System.out.println(ans);
    }
}
