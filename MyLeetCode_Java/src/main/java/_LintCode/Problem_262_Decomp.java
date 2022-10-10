package _LintCode;

public class Problem_262_Decomp {


    public class Info {
        public String res;
        public int index;

        public Info(String r, int i) {
            res = r;
            index = i;
        }
    }

    public String decompressString(String message) {
        if (message == null || message.length() == 0) {
            return message;
        }
        return f(message.toCharArray(), 0).res;
    }

    public Info f(char[] str, int i) {
        int N = str.length;

        int cur = 0;
        StringBuilder sb = new StringBuilder();
        StringBuilder tmp = new StringBuilder();
        while (i < N && str[i] != ']') {
            if (str[i] >= 'A' && str[i] <= 'Z') {
                tmp.append(str[i++]);
            } else if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + str[i++] - '0';
            } else if (str[i] == '|') {
                i++;
            } else if (str[i] == '[') {
                Info info = f(str, i + 1);
                tmp.append(info.res);
                i = info.index + 1;
            }
        }
        if (cur != 0) {
            sb.append(tmp.toString().repeat(cur));
        } else {
            sb.append(tmp);
        }
        return new Info(sb.toString(), i);
    }

    public static void main(String[] args) {
        // String s = "HG[3|B[2|CA]]F"; // HGBCACABCACABCACAF
        String s = "HG[3|B[2|CA[2|R]]TC]F";
        var ans = new Problem_262_Decomp().decompressString(s);
        System.out.println(ans);
    }

}
