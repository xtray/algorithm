package LeetCode;

import java.util.HashSet;
import java.util.Set;

public class Problem_2299_StrongPasswordChecker {

    private static final String specStr = "!@#$%^&*()-+";

    public boolean strongPasswordCheckerII(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        char[] str = password.toCharArray();
        boolean lower = false;
        boolean upper = false;
        boolean digit = false;
        boolean special = false;
        Set<Character> specSet = new HashSet<>();
        for (char ch : specStr.toCharArray()) {
            specSet.add(ch);
        }
        Character pre = null;
        for (int i = 0; i < str.length; i++) {
            if (str[i] >= 'a' && str[i] <= 'z') {
                lower = true;
            } else if (str[i] >= 'A' && str[i] <= 'Z') {
                upper = true;
            } else if (str[i] >= '0' && str[i] <= '9') {
                digit = true;
            } else if (specSet.contains(str[i])) {
                special = true;
            }
            if (pre != null && pre == str[i]) {
                return false;
            }
            pre = str[i];
        }
        return lower && upper && digit && special;
    }

    public static void main(String[] args) {
        // String password = "IloveLe3tcode!";
        // String password = "Me+You--IsMyDream!";
        String password = "Me+You6-IsMyDream!";
        var ans = new Problem_2299_StrongPasswordChecker().strongPasswordCheckerII(password);
        System.out.println(ans);
    }
}
