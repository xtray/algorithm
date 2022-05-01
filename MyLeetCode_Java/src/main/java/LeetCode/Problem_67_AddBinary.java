package LeetCode;

public class Problem_67_AddBinary {

    public String addBinary(String a, String b) {
        if (a == null || a.length() == 0) {
            return b;
        }
        if (b == null || b.length() == 0) {
            return a;
        }
        char[] str1 = a.toCharArray();
        char[] str2 = b.toCharArray();
        StringBuilder sb = new StringBuilder();
        int i = str1.length - 1;
        int j = str2.length - 1;
        int carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int num1 = i < 0 ? 0 : (str1[i] - '0');
            int num2 = j < 0 ? 0 : (str2[j] - '0');
            // 0, --> 0
            // 1, --> 1
            // 2, --> 0
            // 3, --> 1
            int sum = num1 + num2 + carry;
            if (sum >= 2) {
                carry = 1;
            } else {
                carry = 0;
            }
            if((sum&1)!=0) {
                sb.append("1");
            } else {
                sb.append("0");
            }
            i--;
            j--;
        }
        return  sb.reverse().toString();
    }

    public static void main(String[] args) {
        String a = "1";
        String b = "1";
        var ans = new Problem_67_AddBinary().addBinary(a,b);
        System.out.println(ans);
    }
}
