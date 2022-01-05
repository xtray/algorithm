package LeetCode;

public class Problem_1576_ModifyString {

    public String modifyString(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] str = s.toCharArray();
        // 枚举每一个字符, 看左右两侧
        // 当前来到 i 位置做判断, i 之前已经处理过了
        for (int i = 0; i < str.length; i++) {
            char left = i == 0 ? 0 : str[i - 1];
            char cur = str[i];
            char right = i == str.length - 1 ? 0 : str[i + 1];
            if (cur == '?' && right != '?') {
                str[i] = getChar(left, right);
            } else if (cur == '?') {
                str[i] = getChar(left, '0');
            }
        }

        return String.valueOf(str);
    }

    // 得到一个跟 left, right 不相等的字符
    // 最多遍历 3 个字符就足够了
    private char getChar(char left, char right) {
        for (char ch = 'a'; ; ch++) {
            if (ch != left && ch != right) {
                return ch;
            }
        }
    }

    public static void main(String[] args) {
        String s = "??yw?ipkj?";
        var ans = new Problem_1576_ModifyString().modifyString(s);
        System.out.println(ans);
    }
}
