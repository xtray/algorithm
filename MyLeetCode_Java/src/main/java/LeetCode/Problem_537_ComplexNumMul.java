package LeetCode;

public class Problem_537_ComplexNumMul {

    public String complexNumberMultiply(String num1, String num2) {
        if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0) {
            return "";
        }
        int[] cn1 = getComplexNum(num1);
        int[] cn2 = getComplexNum(num2);
        return process(cn1, cn2);
    }

    // 返回String 表达的结果
    private String process(int[] cn1, int[] cn2) {
        int a = cn1[0];
        int b = cn1[1];
        int c = cn2[0];
        int d = cn2[1];
        int real = a * c - b * d;
        int complex = a * d + c * b;
        return real + "+" + complex + "i";
    }

    // 返回数组表示的复数
    // int[0]: 实部
    // int[1]: 虚部
    private int[] getComplexNum(String num) {
        if (num == null || num.length() == 0) {
            return new int[]{};
        }
        String[] numStr = num.substring(0, num.length() - 1).split("\\+");
        int[] ans = new int[2];
        ans[0] = Integer.parseInt(numStr[0]);
        ans[1] = Integer.parseInt(numStr[1]);
        return ans;
    }

    public static void main(String[] args) {
        // String num1 = "1+-1i", num2 = "1+-1i";
        String num1 = "1+0i", num2 = "1+0i";
        var ans = new Problem_537_ComplexNumMul().complexNumberMultiply(num1, num2);
        System.out.println(ans);
    }
}
