package _Codility;

/**
 * Nesting
 *
 * Determine whether a given string of parentheses (single type) is properly nested.
 *
 * https://app.codility.com/demo/results/trainingNSTJF6-YAY/
 */

public class Problem_TR703_Nesting {
    // 堆栈方法同701, 这里用括号匹配法则
    // 任何一个前缀和左括号都比右括号多, 当字符串结束时, 左括号跟右括号一样多
    public int solution(String S) {
        if (S == null || S.length() == 0) {
            return 1;
        }
        char[] str = S.toCharArray();
        int cnt = 0;
        for (char ch : str) {
            if (ch == '(') {
                cnt++;
            } else {
                cnt--;
            }
            if (cnt < 0) {
                return 0;
            }
        }
        return cnt == 0 ? 1 : 0;
    }
}
