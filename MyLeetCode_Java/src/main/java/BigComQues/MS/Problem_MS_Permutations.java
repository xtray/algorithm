package BigComQues.MS;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/discuss/interview-question/1885995/Microsoft-Phone-Interview
 * Given an input string of size 2n, consisting of letter's "a" and "b" only,
 * generate all the different permutation. Input string will always be even
 * size and the number of "a" and "b" will be n.
 * Eg:
 * input: "aabb"
 * output: aabb,abab,abba,bbaa,baba,baab ...
 * <p>
 * input:"ab"
 * output: "ab","ba";
 * <p>
 * input: "aaabbb"
 * output: ...
 */

public class Problem_MS_Permutations {

    public List<String> getAllPermutations(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        process(str, 0, ans);
        return ans;
    }

    // 当前处理到index位置
    // 从index开始到结束执行交换操作
    private void process(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str, 0, str.length));
            return;
        }
        // 没到结尾位置
        // i..end位置的字符都来到index位置
        boolean[] used = new boolean[2]; // 记录a,b有没有来到过index位置
        for (int i = index; i < str.length; i++) {
            if(!used[str[i]-'a']) {
                used[str[i]-'a'] = true;
                swap(str, index, i);
                process(str, index + 1, ans);
                swap(str, index, i);
            }
        }
    }

    private void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }

    public static void main(String[] args) {
        String s = "aabb";
        var ans = new Problem_MS_Permutations().getAllPermutations(s);
        System.out.println(ans);
    }

}
