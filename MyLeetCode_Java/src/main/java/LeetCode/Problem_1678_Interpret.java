package LeetCode;

public class Problem_1678_Interpret {

    public String interpret(String command) {
        if (command == null || command.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char[] str = command.toCharArray();
        int N = str.length;
        int idx = 0;
        while (idx < N) {
            if (str[idx] == 'G') {
                sb.append('G');
                idx++;
            } else if (idx + 1 < N && (str[idx] == '(' && str[idx + 1] == ')')) {
                sb.append('o');
                idx += 2;
            } else if (idx + 3 < N &&
                    (str[idx] == '(' && str[idx + 1] == 'a' && str[idx + 2] == 'l' && str[idx + 3] == ')')) {
                sb.append("al");
                idx += 4;
            }
        }
        return sb.toString();
    }
}
