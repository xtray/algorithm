package _Exercise;



public class Problem_1106_BoolExpr {

    public class Info {
        public boolean res;
        public int index;

        public Info(boolean r, int i) {
            res = r;
            index = i;
        }
    }

    public boolean parseBoolExpr(String s) {
        return process(s.toCharArray(), 0, -1).res;
    }

    // 返回从i位置到结束的表达式值
    // opType:  -1: 保持, 0 : 反, 1: 与, 2:或
    // Note: !的括号内只能有一个字符
    private Info process(char[] str, int i, int opType) {
        int N = str.length;
        Boolean cur = null;
        while (i < N && str[i] != ')') {
            if (str[i] == 't' || str[i] == 'f') {
                boolean tmp = str[i] == 't';
                if (cur == null) {
                    cur = tmp;
                }
                if (opType == 1) {
                    cur &= tmp;
                } else if (opType == 2) {
                    cur |= tmp;
                } else if (opType == 0) {
                    cur = !tmp; // 取反的括号内只有一个
                }
                i++;
            } else if (str[i] == ',') {
                i++;
            } else { // !, |, &
                Info info = process(str, i + 2, str[i] == '!' ? 0 : (str[i] == '&' ? 1 : 2));
                boolean res = info.res;
                if (cur == null) {
                    cur = res;
                }
                if (opType == 1) {
                    cur &= res;
                } else if (opType == 2) {
                    cur |= res;
                } else if (opType == 0) {
                    cur = !res;
                }
                i = info.index + 1;
            }
        }
        return new Info(cur, i);
    }

    public static void main(String[] args) {
        // String s = "|(&(t,f,t),!(t))"; // false
        String s = "|(f,f,f,t)"; // true
        // String s = "!(&(f,t))"; // true
        // String s = "&(t,t,t)"; // true
        var ans = new Problem_1106_BoolExpr().parseBoolExpr(s);
        System.out.println(ans);
    }
}
