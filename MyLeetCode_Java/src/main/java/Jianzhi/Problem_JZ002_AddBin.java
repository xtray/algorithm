package Jianzhi;

public class Problem_JZ002_AddBin {

    public String addBinary(String a, String b) {
        if (a == null || a.length() == 0) {
            return b;
        }
        if (b == null || b.length() == 0) {
            return a;
        }
        char[] str1 = a.toCharArray();
        char[] str2 = b.toCharArray();
        StringBuilder ans = new StringBuilder();
        int hiCarry = 0;
        int pos1 = str1.length - 1;
        int pos2 = str2.length - 1;
        while (pos1 >= 0 && pos2 >= 0) {
            int num1 = str1[pos1] - '0';
            int num2 = str2[pos2] - '0';
            int sum = num1 + num2 + hiCarry;
            hiCarry = sum >= 2 ? 1 : 0;
            ans.append((sum & 1) != 0 ? '1' : '0');
            pos1--;
            pos2--;
        }
        while (pos1 >= 0) {
            int num = str1[pos1] - '0';
            int sum = num + hiCarry;
            hiCarry = sum >= 2 ? 1 : 0;
            ans.append((sum & 1) != 0 ? '1' : '0');
            pos1--;
        }
        while (pos2 >= 0) {
            int num = str2[pos2] - '0';
            int sum = num + hiCarry;
            hiCarry = sum >= 2 ? 1 : 0;
            ans.append((sum & 1) != 0 ? '1' : '0');
            pos2--;
        }
        if (hiCarry == 1) {
            ans.append('1');
        }
        return ans.reverse().toString();
    }

    public String addBinary2(String a, String b) {
        char[] str1 = a.toCharArray();
        char[] str2 = b.toCharArray();
        StringBuilder ans = new StringBuilder();
        int hiCarry = 0;
        int pos1 = str1.length - 1;
        int pos2 = str2.length - 1;
        while (pos1 >= 0 || pos2 >= 0 || hiCarry == 1) {
            int num1 = pos1 < 0 ? 0 : str1[pos1] - '0';
            int num2 = pos2 < 0 ? 0 : str2[pos2] - '0';
            int sum = num1 + num2 + hiCarry;
            hiCarry = sum >= 2 ? 1 : 0;
            ans.append((sum & 1) != 0 ? '1' : '0');
            pos1--;
            pos2--;
        }
        return ans.reverse().toString();
    }

    public static void main(String[] args) {
        // String a = "11";
        // String b = "10";
        String a = "1010";
        String b = "1011";
        var ans = new Problem_JZ002_AddBin().addBinary2(a, b);
        System.out.println(ans);
    }
}
