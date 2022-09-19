package _DailyTarget;

public class Problem_1190_ReverseParentheses {

    public static class Info {
        public String res; // 处理的结果
        public int pos; // 处理到的位置
        public Info(String r, int p) {
            res = r;
            pos = p;
        }
    }

    // 括号表达式处理
    public String reverseParentheses(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] str = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< s.length(); ) {
            if (str[i] >= 'a' && str[i] <= 'z') {
                sb.append(str[i++]);
            } else if(str[i] == '(') {
                Info info = process(str, i + 1);
                sb.append(info.res);
                i = info.pos + 1;
            }
        }
        return sb.toString();
    }

    // 请从str[i...]往下算，遇到字符串终止位置或者右括号，就停止
    // 返回两个值，长度为2的数组
    // 0) 负责的这一段的结果是多少
    // 1) 负责的这一段计算到了哪个位置
    private Info process(char[] str, int i) {
        StringBuilder sb = new StringBuilder();
        while (i < str.length && str[i] != ')') {
            if (str[i] >= 'a' && str[i] <= 'z') {
                sb.append(str[i++]);
            } else if (str[i] == '(') {
                Info info = process(str, i + 1);
                sb.append(info.res);
                i = info.pos + 1;
            }
        }
        return new Info(sb.reverse().toString(), i);
    }
}
