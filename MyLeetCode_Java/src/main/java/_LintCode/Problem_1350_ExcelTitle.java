package _LintCode;

public class Problem_1350_ExcelTitle {

    // https://www.lintcode.com/problem/1350/solution/56964
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            int a0 = (n - 1) % 26 + 1;
            sb.append((char) (a0 - 1 + 'A'));
            n = (n - a0) / 26;
        }
        return sb.reverse().toString();
    }

    // https://www.lintcode.com/problem/1350/solution/56966
    // 从1开始的的26进制, -1调整回0~25的标准进制
    public String convertToTitle1(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            sb.append((char) ((n - 1) % 26 + 'A'));
            n = (n-1)/26;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        int n = 29;
        var ans = new Problem_1350_ExcelTitle().convertToTitle(n);
        System.out.println(ans);
    }
}
