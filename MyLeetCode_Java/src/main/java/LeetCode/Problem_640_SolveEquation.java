package LeetCode;

public class Problem_640_SolveEquation {

    public String solveEquation(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        String[] ss = s.split("=");
        char[] str1 = ss[0].toCharArray();
        char[] str2 = ss[1].toCharArray();
        int[] left = process(str1);
        int[] right = process(str2);
        int x = left[0] - right[0];
        int num = right[1] - left[1];
        return x == 0 ? (num != 0 ? "No solution" : "Infinite solutions") : "x=" + num / x;
    }

    // int []
    // 0: x数目
    // 1: 数值
    private int[] process(char[] str) {
        int x = 0;
        int cur = 0;
        int num = 0;
        boolean negative = false;
        boolean numparse = false;
        for (char ch : str) {
            if (ch == 'x') {
                if (cur != 0) {
                    if (negative) {
                        cur = -cur;
                        negative = false;
                    }
                    x += cur;
                    cur = 0;
                } else {
                    if (numparse) {
                        numparse = false;
                        negative = false;
                        continue; // 0x
                    }
                    if (negative) {
                        x--;
                        negative = false;
                    } else {
                        x++;
                    }
                }
            } else if (Character.isDigit(ch)) {
                numparse = true;
                cur = cur * 10 + ch - '0';
            } else {
                numparse = false;
                if (negative && cur != 0) {
                    cur = -cur;
                    negative = false;
                }
                num += cur;
                cur = 0;
                if (ch == '-') {
                    negative = true;
                }
            }
        }
        if (cur != 0) {
            if (negative) {
                cur = -cur;
            }
            num += cur;
        }
        return new int[]{x, num};
    }

    public static void main(String[] args) {
        // String equation = "x+5-3+x=6+x-2"; // 2
        // String equation = "x=x+2"; // No solution
        // String equation = "1-x+x-x+x+2x=-99-x+x-x+x";
        String equation = "0x=0";
        var ans = new Problem_640_SolveEquation().solveEquation(equation);
        System.out.println(ans);

    }
}
