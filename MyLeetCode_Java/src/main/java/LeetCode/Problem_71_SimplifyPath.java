package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Problem_71_SimplifyPath {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return path;
        }
        char[] str = path.toCharArray();
        LinkedList<String> stack = new LinkedList<>();

        StringBuilder sb = new StringBuilder();
        for (char ch : str) {
            if(ch == '/') { // 碰到 / 开始结算
                if(sb.length()!=0) {
                    if(sb.toString().equals("..")) {
                        stack.pollLast();
                    } else if(!sb.toString().equals(".")) {
                        stack.addLast(sb.toString());
                    }
                    sb.delete(0, sb.length());
                }
            } else {
                sb.append(ch);
            }
        }
        if(sb.length()!=0) { // 最后的一块, 如果没有/ 没有结算, 单独结算处理
            if(sb.toString().equals("..")) {
                stack.pollLast();
            } else if(!sb.toString().equals(".")) {
                stack.addLast(sb.toString());
            }
            sb.delete(0, sb.length());
        }
        while(!stack.isEmpty()) {
            sb.append("/");
            sb.append(stack.pollFirst());
        }
        return sb.length() == 0? "/":sb.toString(); // 避免 /..的情况
    }

    public static void main(String[] args) {
        // String path = "/a/./b/../../c/";
        // String path = "/...";
        // String path = "/home//foo/";
        String path = "/..";
        var ans = new Problem_71_SimplifyPath().simplifyPath(path);
        System.out.println(ans);
    }

}
