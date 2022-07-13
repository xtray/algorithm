package LeetCode;

import java.util.ArrayList;
import java.util.List;


/**
 * Day 1027
 * https://leetcode-cn.com/problems/remove-invalid-parentheses/
 * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
 * 返回所有可能的结果。答案可以按 任意顺序 返回。
 */

public class Problem_301_RemoveInvalidParentheses {

    /**
     * @param s: 待处理括号字符串
     * @return 所有结果 List
     */
    public static List<String> removeInvalidParentheses(String s) {
        /**
         * 有效的括号
         * 在任何前缀上左括号数量大于右括号数量
         */
        List<String> ans = new ArrayList<>();
        int checkIdx = 0;
        int deleteIdx = 0;
        remove(s, ans, checkIdx, deleteIdx, new char[]{'(', ')'});
        return ans;
    }

    public static void remove(String s, List<String> ans, int checkIdx, int deleteIdx, char[] par) {

        for (int count = 0, i = checkIdx; i< s.length(); i++) {
            if ( s.charAt(i) == par[0] ) {
                count++;
            }
            // 还有其他字符, 所以要明确判断括号, 不能用 else
            if (s.charAt(i) == par[1]){ // 右括号
                count--;
            }
            if (count < 0) {
                for(int j = deleteIdx; j <=i; j++) {
                    if (s.charAt(j) == par[1] && (j == deleteIdx || s.charAt(j-1) != par[1]) ){
                        remove(s.substring(0, j) + s.substring(j+1, s.length()), ans, i, j, par);
                    }
                }
                return;
            }
        }

        String reversed = new StringBuffer(s).reverse().toString();
        if (par[0] == '(') {
            remove(reversed, ans, 0 ,0 , new char[]{')','('});
        } else {
            // 翻转两次变回原字符串
            ans.add(reversed);
        }
    }

    public static void main(String[] args) {
        String s1 = "(()";
        List<String> ans = removeInvalidParentheses(s1);
        for (String str : ans) {
            System.out.println(str);
        }
    }

}
