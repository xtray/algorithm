package LeetCode;

public class Problem_12_IntToRoman {

    private static final String[][] exp = {
            {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
            {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
            {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
            {"", "M", "MM", "MMM"}
    };

    // I  V  X  L  C   D   M
    // 1  5  10 50 100 500 1000
    // 当前比下一个小就表示负数
    // 当前比下一个大就表示正数
    // n / 对应mask % 10 取到对应的表示符号, 如果某一位上是0就用""空字符串拼
    public String intToRoman(int n) {
        StringBuilder sb = new StringBuilder();
        sb.append(exp[3][n / 1000 % 10]); // 千位
        sb.append(exp[2][n / 100 % 10]); // 百位
        sb.append(exp[1][n / 10 % 10]); // 十位
        sb.append(exp[0][n % 10]); // 个位
        return sb.toString();
    }
}
