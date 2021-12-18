package LeetCode;

public class Problem_709_ToLowerCase {

    public String toLowerCase(String s) {
        if(s == null || s.length() ==0) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        char[] str = s.toCharArray();
        for(char ch : str) {
            if(ch >='A' && ch <= 'Z') {
                sb.append((char)(ch - 'A' + 'a'));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }


    // 大写字母 ASCII范围为 [65,90]：
    // 小写字母 ASCII 码范围为[97,122]。\


    public String toLowerCase2(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (ch >= 65 && ch <= 90) {
                ch |= 32;
            }
            sb.append(ch);
        }
        return sb.toString();
    }

}
