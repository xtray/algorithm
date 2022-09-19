package LeetCode;

import java.util.*;

public class Problem_1408_StringMatch {

    public List<String> stringMatching0(String[] words) {
        List<String> list = new ArrayList<>(Arrays.asList(words));
        list.sort((o1, o2) -> o1.length() - o2.length());

        int N = words.length;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (list.get(j).contains(list.get(i))) {
                    set.add(list.get(i));
                }
            }
        }

        return new ArrayList<>(set);
    }


    // KMP
    public List<String> stringMatching(String[] words) {
        List<String> ans = new ArrayList<>();

        if (words == null || words.length == 0) {
            return ans;
        }
        // 枚举每一个串(除去自身), 看看有没有别的串包含, 找到一个就break
        for (String s1 : words) {
            for (String s2 : words) {
                if(!s1.equals(s2) && getIndexOf(s2, s1) != -1) {
                    ans.add(s1);
                    break;
                }
            }
        }
        return ans;
    }

    public static int getIndexOf(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() < 1 || s1.length() < s2.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int x = 0; // str1里比对到的位置
        int y = 0; // str2里比对到的位置
        // O(M), M<=N
        int[] next = getNextArray(str2);
        // O(N)
        while (x < str1.length && y < str2.length) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == str2.length ? x - y : -1;
    }

    // next数组
    private static int[] getNextArray(char[] str2) {
        if (str2.length == 1) {
            return new int[]{-1}; // 任何字符串0位置都是-1
        }
        int[] next = new int[str2.length];
        next[0] = -1; // 人为规定
        next[1] = 0;
        int i = 2; // 目前开始求next数组值的位置
        int cn = 0; // 跟i位置比对的位置, i开始2位置, 前一个是1位置, 上面的next数组是0, cn: count?
        while (i < str2.length) {
            if (str2[i - 1] == str2[cn]) { // 匹配成功的时候
                next[i++] = ++cn;
            } else if (cn > 0) { // 没配成功, cn还能继续往左跳
                cn = next[cn];
            } else { // 没配成功, cn不能往左跳
                next[i++] = 0;
            }
        }
        return next;
    }

    public List<String> stringMatching2(String[] ss) {
        List<String> ans = new ArrayList<>();
        int n = ss.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (ss[j].indexOf(ss[i]) >= 0) {
                    ans.add(ss[i]);
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // String[] words = {"leetcoder", "leetcode", "od", "hamlet", "am"};
        String[] words = {"leetcode","et","code"};
        var ans = new Problem_1408_StringMatch().stringMatching(words);
        System.out.println(ans);

        var ans2 = new Problem_1408_StringMatch().stringMatching2(words);
        System.out.println(ans2);
    }
}
