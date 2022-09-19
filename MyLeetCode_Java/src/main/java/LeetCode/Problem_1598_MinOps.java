package LeetCode;

import java.util.ArrayDeque;

public class Problem_1598_MinOps {

    public int minOperations(String[] logs) {
        if (logs == null || logs.length == 0) {
            return 0;
        }
        ArrayDeque<String> stack = new ArrayDeque<>();
        for (String s : logs) {
            if (s.equals("./")) continue;
            if (s.equals("../")) {
                if (!stack.isEmpty()) stack.pollLast();
            } else {
                stack.addLast(s);
            }
        }
        return stack.size();
    }

    public static void main(String[] args) {
        String[] logs = {"./", "../", "./"};
        var ans = new Problem_1598_MinOps().minOperations(logs);
        System.out.println(ans);
    }
}
