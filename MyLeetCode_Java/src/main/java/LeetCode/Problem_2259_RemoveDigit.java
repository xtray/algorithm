package LeetCode;

public class Problem_2259_RemoveDigit {

    public String removeDigit(String number, char digit) {

        // 尝试移除每一个相同的digit, 找字典序最大的
        String ans = "";
        StringBuilder sb = new StringBuilder();
        int N = number.length();
        for (int i = 0; i < N; i++) {
            if (number.charAt(i) == digit) {
                String newNum = getNewNum(number, i);
                if (newNum.compareTo(ans) > 0) {
                    ans = newNum;
                }
            }
        }
        return ans;
    }

    private String getNewNum(String str, int i) {
        if (i == 0) {
            return str.substring(1);
        }
        if (i == str.length() - 1) {
            return str.substring(0, str.length() - 1);
        }
        return str.substring(0, i) + str.substring(i + 1);
    }

    public static void main(String[] args) {
        String number = "731131";
        char digit = '3';
        var ans = new Problem_2259_RemoveDigit().removeDigit(number, digit);
        System.out.println(ans);

    }
}
