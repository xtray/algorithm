package LeetCode;

public class Problem_171_ExcelNumber {

    public int titleToNumber(String col) {
        if (col == null || col.length() == 0) {
            return 0;
        }
        char[] str = col.toCharArray();
        int N = str.length;
        int ans = 0;
        for (char c : str) {
            ans = ans * 26 + c - 'A' + 1;
        }
        return ans;
    }

    // NOTE: 从小到大的方法
    public int titleToNumber1(String col) {
        if (col == null || col.length() == 0) {
            return 0;
        }
        char[] str = col.toCharArray();
        int N = str.length;
        int ans = 0;
        int mul = 1;
        for (int i = N - 1; i >= 0; i--) {
            int cur = str[i] - 'A' + 1;
            ans += cur * mul;
            mul *= 26;
        }
        return ans;
    }

    public static void main(String[] args) {
        // String col = "AB";
        String col = "FXSHRXW";
        var ans = new Problem_171_ExcelNumber().titleToNumber(col);
        System.out.println(ans);

        var ans1 = new Problem_171_ExcelNumber().titleToNumber1(col);
        System.out.println(ans1);
    }
}
