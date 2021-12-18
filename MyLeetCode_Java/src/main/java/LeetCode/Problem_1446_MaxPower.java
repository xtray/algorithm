package LeetCode;

public class Problem_1446_MaxPower {
    public int maxPower(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int power = 1;
        int max = 1;
        for (int i = 1; i < str.length; i++) {
            if (str[i] == str[i - 1]) {
                power++;
                max = Math.max(max, power);
            } else {
                power = 1;
            }
        }
        return max;

    }

    public static void main(String[] args) {
        String s = "tourist";
        var res = new Problem_1446_MaxPower().maxPower(s);
        System.out.println(res);
    }
}
